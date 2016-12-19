package com.ebingine.resources;

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
    private String path;

    /**
     * Supports only .wav file types.
     *
     * @param pathToWavFile
     */
    public Audio(String pathToWavFile) {
        path = pathToWavFile;
        makeClip();
    }

    private void makeClip() {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File(path).getAbsoluteFile())) {
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
        System.out.println(clip.isRunning());
        if (!clip.isRunning()) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public void playIndefinitely() {
        if (!clip.isRunning()) {
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip.isRunning();
    }

    public void dispose() {
        clip.flush();
    }
}
