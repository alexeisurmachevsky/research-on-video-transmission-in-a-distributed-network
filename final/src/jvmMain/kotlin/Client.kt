import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.properties.Delegates

class Client(host:String = "localhost", port:Int) {
    private val serverAddress = InetAddress.getByName("localhost")
    private val serverPort = 12345
    private val bufferSize = 1024*1024
    private val buffer = ByteArray(bufferSize)
    private var clientPort by Delegates.notNull<Int>()
    private var clientAddress by Delegates.notNull<InetAddress>()
    private lateinit var responsePacket:DatagramPacket
    private val clientSocket = DatagramSocket()
    private fun conn() {

        // Отправляем запрос на получение адреса и порта
        val request = "z".toByteArray()
        val requestPacket = DatagramPacket(request, request.size, serverAddress, serverPort)
        clientSocket.send(requestPacket)

        // Ждем ответа от сервера с адресом и портом клиента
        responsePacket = DatagramPacket(buffer, bufferSize)
        clientSocket.receive(responsePacket)
        clientAddress = InetAddress.getByName(String(responsePacket.data, 0, responsePacket.length).removePrefix("/").split(":")[0])
        clientPort = String(responsePacket.data, 0, responsePacket.length).removePrefix("/").split(":")[1].toInt()
        println("Received response from server with client address: $clientAddress:$clientPort")
    }

    fun translator(){
        conn()
        File("output").mkdir()
        Thread{
            val recorder = Recorder()


            recorder.startRec("output\\f.mp4")
            val message = File("output\\f.mp4").readBytes()

            val messagePacket = DatagramPacket(message, message.size, clientAddress, clientPort)

            clientSocket.send(messagePacket)

        }.start()

    }

    fun client(){
        conn()
        Thread {
            val fullVid = mutableListOf<ByteArray >()
            var i = 0

            File("loadedVideos").mkdir()
            val rp = DatagramPacket(ByteArray(bufferSize), bufferSize)
            clientSocket.receive(rp)
            fullVid.add(rp.data.copyOf(rp.length))
            File("loadedVideos\\f.mp4").writeBytes(rp.data)
            VideoPlayer("loadedVideos\\f.mp4")


        }.start()
    }
}

fun toAloneList(eList:List<ByteArray>): MutableList<Byte> {
    val oList = mutableListOf<Byte>()

    eList.forEach {
        it.forEach {x->
            oList.add(x)
        }
    }

    return oList
}