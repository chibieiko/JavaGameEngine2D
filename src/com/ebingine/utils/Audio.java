package com.ebingine.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1213
 * @since 1.7
 */
public class Audio {

    Clip clip;

    public Audio(String pathToWavFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    new File(pathToWavFile).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void playIndefinitely() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void playWithLoopCount(int loopcount) {
        clip.loop(loopcount);
    }

    public void stop() {
        clip.stop();
    }
}
