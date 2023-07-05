import java.net.DatagramPacket
import java.net.DatagramSocket

class Server(private val serverPort: Int = 12345) {
    fun start() {
        Thread {
            val bufferSize = 9000
            val buffer = ByteArray(bufferSize)

            val serverSocket = DatagramSocket(serverPort)
            println("Server started on port $serverPort")

            val clients = mutableListOf<DatagramPacket>()
            while (true) {
                val requestPacket = DatagramPacket(buffer, bufferSize)
                serverSocket.receive(requestPacket)
                clients.add(requestPacket)
                val clientAddress = requestPacket.address
                val clientPort = requestPacket.port
                println("Received request from $clientAddress:$clientPort")

                if (clients.size == 2) {
                    println("2con")
                    val response1 = "${clients[1].address}:${clients[1].port}".toByteArray()
                    val response2 = "${clients[0].address}:${clients[0].port}".toByteArray()
                    val responsePacket1 = DatagramPacket(response1, response1.size, clients[0].address, clients[0].port)
                    val responsePacket2 = DatagramPacket(response2, response2.size, clients[1].address, clients[1].port)
                    serverSocket.send(responsePacket1)
                    serverSocket.send(responsePacket2)
                }

            }
        }.start()
    }
}