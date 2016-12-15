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
    private boolean playing;

    public Audio(String pathToWavFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    new File(pathToWavFile).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            playing = false;
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
        playing = true;
    }

    public void playIndefinitely() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
        playing = true;
    }

    public void playWithLoopCount(int loopcount) {
        clip.loop(loopcount);
    }

    public void stop() {
        playing = false;
        clip.stop();
    }

    public boolean isPlaying() {
        return playing;
    }
}
