package io.nekohasekai.sagernet.fmt.ssh

import io.nekohasekai.sagernet.ktx.Logs
import kotlinx.coroutines.*
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import javax.net.ssl.SNIHostName
import javax.net.ssl.SSLParameters
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import kotlin.random.Random

object SSHInjector {

    private var serverSocket: ServerSocket? = null
    private var job: Job? = null
    var localPort: Int = 0
        private set

    fun start(
        proxyHost: String,
        proxyPort: Int,
        payload: String,
        sni: String,
        useTls: Boolean,
        sshHost: String,
        sshPort: Int
    ) {
        stop()
        
        localPort = Random.nextInt(20000, 60000)
        
        job = GlobalScope.launch(Dispatchers.IO) {
            try {
                serverSocket = ServerSocket(localPort)
                Logs.i("Started local proxy server on port $localPort")
                
                while (isActive) {
                    val clientSocket = serverSocket?.accept() ?: break
                    Logs.i("Accepted connection from sing-box")
                    launch {
                        handleClient(clientSocket, proxyHost, proxyPort, payload, sni, useTls, sshHost, sshPort)
                    }
                }
            } catch (e: Exception) {
                Logs.e("Server socket error: ${e.message}", e)
            }
        }
    }

    private suspend fun handleClient(
        clientSocket: Socket,
        proxyHost: String,
        proxyPort: Int,
        payload: String,
        sni: String,
        useTls: Boolean,
        sshHost: String,
        sshPort: Int
    ) = withContext(Dispatchers.IO) {
        var remoteSocket: Socket? = null
        try {
            val targetHost = if (proxyHost.isNotBlank()) proxyHost else sshHost
            val targetPort = if (proxyPort > 0) proxyPort else sshPort
            
            Logs.i("Connecting to target: $targetHost:$targetPort (TLS: $useTls)")
            
            // Resolve IP using underlying network to bypass VPN tunnel deadlock
            val isIp = targetHost.matches(Regex("^[0-9.]+$|^[0-9a-fA-F:]+$"))
            val ipAddress = if (isIp) {
                targetHost
            } else {
                val network = io.nekohasekai.sagernet.SagerNet.underlyingNetwork
                val resolved = network?.getAllByName(targetHost)?.firstOrNull()?.hostAddress 
                    ?: java.net.InetAddress.getAllByName(targetHost).firstOrNull()?.hostAddress
                    ?: targetHost
                Logs.i("Resolved $targetHost to $resolved")
                resolved
            }

            // 1. Connect to target
            remoteSocket = if (useTls) {
                val factory = SSLSocketFactory.getDefault() as SSLSocketFactory
                val sslSocket = factory.createSocket() as SSLSocket
                
                // Protect socket from VPN loop
                io.nekohasekai.sagernet.database.DataStore.vpnService?.protect(sslSocket)
                
                // Set SNI
                val sniHost = if (sni.isNotBlank()) sni else targetHost
                Logs.i("Setting SNI to: $sniHost")
                val params = SSLParameters()
                params.serverNames = listOf(SNIHostName(sniHost))
                sslSocket.sslParameters = params
                
                sslSocket.connect(InetSocketAddress(ipAddress, targetPort), 10000)
                sslSocket.startHandshake()
                sslSocket
            } else {
                val socket = Socket()
                io.nekohasekai.sagernet.database.DataStore.vpnService?.protect(socket)
                socket.connect(InetSocketAddress(ipAddress, targetPort), 10000)
                socket
            }
            Logs.i("Connected to remote server successfully")

            // 2. Send payload if any
            if (payload.isNotBlank()) {
                val formattedPayload = payload
                    .replace("[host_port]", "$sshHost:$sshPort")
                    .replace("[host]", sshHost)
                    .replace("[port]", sshPort.toString())
                    .replace("[protocol]", "HTTP/1.1")
                    .replace("[crlf]", "\r\n")
                    .replace("\\r", "\r")
                    .replace("\\n", "\n")
                
                Logs.i("Injecting payload:\n$formattedPayload")
                remoteSocket.outputStream.write(formattedPayload.toByteArray())
                remoteSocket.outputStream.flush()
                
                // Start Client -> Remote forwarding immediately to prevent deadlock
                // Some servers wait for client data (e.g., SSH banner) before responding
                val job1 = kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                    try {
                        copyStream(clientSocket.inputStream, remoteSocket.outputStream, "Client -> Remote")
                    } catch (e: Exception) {}
                }
                
                // Read proxy response ONLY if it's an HTTP response
                if (formattedPayload.contains("HTTP/", ignoreCase = true)) {
                    val input = java.io.BufferedInputStream(remoteSocket.inputStream)
                    input.mark(10)
                    val header = ByteArray(4)
                    val readBytes = input.read(header)
                    input.reset()
                    
                    if (readBytes >= 4 && String(header).uppercase().startsWith("HTTP")) {
                        Logs.i("HTTP response detected from proxy, stripping headers...")
                        var buf = ByteArray(4)
                        var index = 0
                        while (true) {
                            val b = input.read()
                            if (b == -1) break
                            buf[index % 4] = b.toByte()
                            index++
                            if (index >= 4) {
                                if (buf[(index - 4) % 4] == '\r'.code.toByte() &&
                                    buf[(index - 3) % 4] == '\n'.code.toByte() &&
                                    buf[(index - 2) % 4] == '\r'.code.toByte() &&
                                    buf[(index - 1) % 4] == '\n'.code.toByte()) {
                                    Logs.i("Consumed HTTP proxy response headers")
                                    break
                                }
                            }
                        }
                    } else {
                        Logs.i("Proxy response does not start with HTTP (starts with ${String(header)}). Proceeding directly to splice.")
                    }
                    
                    // Proceed to splice Remote -> Client with the BufferedInputStream
                    val job2 = kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                        try {
                            copyStream(input, clientSocket.outputStream, "Remote -> Client")
                        } catch (e: Exception) {}
                    }
                    job1.join()
                    job2.join()
                    return@withContext
                }
                
                // If not HTTP, just splice Remote -> Client directly
                val job2 = kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                    try {
                        copyStream(remoteSocket.inputStream, clientSocket.outputStream, "Remote -> Client")
                    } catch (e: Exception) {}
                }
                job1.join()
                job2.join()
                return@withContext
            }

            // 3. Splice streams (for non-payload cases)
            val job1 = kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                try {
                    copyStream(clientSocket.inputStream, remoteSocket.outputStream, "Client -> Remote")
                } catch (e: Exception) {}
            }
            val job2 = kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                try {
                    copyStream(remoteSocket.inputStream, clientSocket.outputStream, "Remote -> Client")
                } catch (e: Exception) {}
            }
            
            job1.join()
            job2.join()

        } catch (e: Exception) {
            Logs.e("Connection error: ${e.message}", e)
        } finally {
            try { clientSocket.close() } catch (_: Exception) {}
            try { remoteSocket?.close() } catch (_: Exception) {}
            Logs.i("Connection closed")
        }
    }

    private fun copyStream(input: InputStream, output: OutputStream, direction: String) {
        try {
            val buffer = ByteArray(8192)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } != -1) {
                output.write(buffer, 0, bytesRead)
                output.flush()
            }
        } catch (e: Exception) {
            Logs.e("Relay error ($direction): ${e.message}")
        }
    }

    fun stop() {
        job?.cancel()
        job = null
        try {
            serverSocket?.close()
        } catch (e: Exception) {
            Logs.e("Error closing server socket: ${e.message}")
        }
        serverSocket = null
        localPort = 0
        Logs.i("Stopped local proxy server")
    }
}
