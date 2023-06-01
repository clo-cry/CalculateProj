package beta.test.L;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.SourceDataLine;

public class simpleSoundPlayer {

    //defining the byte buffer
    private static final int BUFFER_SIZE = 4096;


    public void play(String filePath) {
        File soundFile = new File(filePath);
        try {
            // conversing the audio file to a stream
            AudioInputStream sampleStream = AudioSystem.getAudioInputStream(soundFile);

            AudioFormat formatAudio = sampleStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatAudio);

            SourceDataLine theAudioLine = (SourceDataLine) AudioSystem.getLine(info);

            theAudioLine.open(formatAudio);

            theAudioLine.start();

            byte[] bufferBytes = new byte[BUFFER_SIZE];
            int readBytes = -1;

            while ((readBytes = sampleStream.read(bufferBytes)) != -1) {
                theAudioLine.write(bufferBytes, 0, readBytes);
            }

            theAudioLine.drain();
            theAudioLine.close();
            sampleStream.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported file.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Line not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Experienced an error.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String thePath = "path to your audio file here";
        String fileName = "test.wav";
        String thePath = new File("").getAbsolutePath() + "/Test/src/beta/test/L/" + fileName;
        simpleSoundPlayer player = new simpleSoundPlayer();
        player.play(thePath);
    }

}