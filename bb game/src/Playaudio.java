import java.io.File;
import javax.sound.sampled.*;
public class Playaudio {
    Clip clip;
    public void Audio(String Path) {
        try {
            File file = new File(Path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat Format = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, Format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();

        }
        catch (Exception e) {
            System.out.println("An error occurred while playing the song");
        }
    }
    public void stop() {
        clip.stop();
    }
}
