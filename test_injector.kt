import java.io.BufferedInputStream
import java.io.ByteArrayInputStream

fun main() {
    val input = "SSH-2.0-OpenSSH_8.9p1\r\n".toByteArray()
    val bufferedInput = BufferedInputStream(ByteArrayInputStream(input))
    bufferedInput.mark(10)
    val header = ByteArray(4)
    val readBytes = bufferedInput.read(header)
    bufferedInput.reset()
    if (readBytes >= 4 && String(header) == "HTTP") {
        println("Is HTTP")
    } else {
        println("Is NOT HTTP, starts with ${String(header)}")
    }
}
