package io.nekohasekai.sagernet.fmt.ssh

import moe.matsuri.nb4a.SingBoxOptions
import moe.matsuri.nb4a.utils.listByLineOrComma

fun buildSingBoxOutboundSSHBean(bean: SSHBean): SingBoxOptions.Outbound_SSHOptions {
    io.nekohasekai.sagernet.ktx.Logs.i("SSH Config Check: payload='${bean.payload}' proxyHost='${bean.proxyHost}' proxyPort='${bean.proxyPort}' useTls='${bean.useTls}' sni='${bean.sni}'")
    return SingBoxOptions.Outbound_SSHOptions().apply {
        type = "ssh"
        if (bean.payload?.isNotBlank() == true || bean.proxyHost?.isNotBlank() == true || bean.useTls == true) {
            SSHInjector.start(
                bean.proxyHost ?: "",
                bean.proxyPort ?: 0,
                bean.payload ?: "",
                bean.sni ?: "",
                bean.useTls == true,
                bean.serverAddress,
                bean.serverPort ?: 22
            )
            server = "127.0.0.1"
            server_port = SSHInjector.localPort
        } else {
            server = bean.serverAddress
            server_port = bean.serverPort
        }
        user = bean.username
        if (bean.publicKey.isNotBlank()) {
            host_key = bean.publicKey.listByLineOrComma()
        }
        when (bean.authType) {
            SSHBean.AUTH_TYPE_PRIVATE_KEY -> {
                private_key = bean.privateKey
                private_key_passphrase = bean.privateKeyPassphrase
            }
            else -> {
                password = bean.password
            }
        }
    }
}
