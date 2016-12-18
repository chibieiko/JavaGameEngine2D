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

    private Clip clip;
    private boolean playing;

    /**
     * Supports only .wav file types.
     *
     * @param pathToWavFile
     */
    public Audio(String pathToWavFile) {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File(pathToWavFile).getAbsoluteFile())) {
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            playing = false;
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!playing) {
            clip.start();
        }

        playing = true;
    }

    public void playIndefinitely() {
        if (!playing) {
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }

        playing = true;
    }

    public void playWithLoopCount(int loopcount) {
        if (!playing) {
            clip.loop(loopcount);
        }
    }

    public void stop() {
        if (playing) {
            playing = false;
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public void dispose() {
        clip.flush();
    }
}
