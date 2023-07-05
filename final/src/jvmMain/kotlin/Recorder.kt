import org.bytedeco.javacv.FFmpegFrameRecorder
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.Java2DFrameConverter
import org.bytedeco.opencv.global.opencv_cudacodec.MPEG4
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage

class Recorder {

    fun startRec(filedir:String) {
        val recorder = FFmpegFrameRecorder(filedir, 100, 50)
        recorder.videoCodec = MPEG4
        recorder.format = "mp4"
        recorder.frameRate = 12.0
        recorder.start()

        val start = System.currentTimeMillis()

        while (System.currentTimeMillis() - start < 1000) {
            val frame = getScreenshot()
            recorder.record(frame)
        }

        recorder.stop()
        recorder.release()
    }

    private fun getScreenshot(): Frame {
        val robot = Robot()
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        val screenRectangle = Rectangle(screenSize)
        val image: BufferedImage = robot.createScreenCapture(screenRectangle)
        val converter = Java2DFrameConverter()
        return converter.convert(image)
    }
}