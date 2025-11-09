import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public static void play(String path){
        try{
            File soundFile = new File(path);
            if(!soundFile.exists()) {
                System.out.println("Sound file not found: " + soundFile.getAbsolutePath());
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e ){
            e.printStackTrace(); // for debugging
        }
    }
}
