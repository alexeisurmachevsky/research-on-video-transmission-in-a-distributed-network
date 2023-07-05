import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.Dimension
import javax.swing.JFrame


@Composable
@Preview
fun App() {
    var textS by remember { mutableStateOf("SERVER") }
    var textC by remember { mutableStateOf("CONNECT") }
    var textSt by remember { mutableStateOf("START TRANSLATION") }

    MaterialTheme {
        Column{
            Button(onClick = {  //SERVER
                textS = "process..."
                server()

            }) {
                Text(textS)

            }

            Button(onClick = {  //CLIENT
                textC = "process..."
                client()
            }) {
                Text(textC)

            }

            Button(onClick = { //TRANSLATOR
                textSt = "process..."
                translator()
            }) {
                Text(textSt)

            }



        }

    }
}



fun main() = application {
    val ws = rememberWindowState()
    ws.size = DpSize(250.dp, 400.dp)
    Window(onCloseRequest = ::exitApplication, resizable = true, state =ws) {
        App()

    }
}



public fun VideoPlayer(file: String) {
    val mediaPlayerComponent = EmbeddedMediaPlayerComponent()
    // Открыть окно плеера
    JFrame.setDefaultLookAndFeelDecorated(true)
    val frame = JFrame("Kotlin Compose Video Player")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(mediaPlayerComponent)
    frame.size = Dimension(800, 600)
    frame.isVisible = true

    // Воспроизвести видео
    mediaPlayerComponent.mediaPlayer().media().play(file)
}

fun server(){
    val s = Server().start()
    r().m()
}

fun client() {
    val c = Client(port=12345).client()
   // VideoPlayer("loadedVideos\\f.mp4")

}

fun translator(){
    val t = Client(port=12345).translator()
}