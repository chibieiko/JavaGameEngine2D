package com.ebingine.resources;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Enables music and sounds in the game.
 * <p>
 * Only supports .wav files.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Audio {

    /**
     * The audio clip to play.
     */
    private Clip clip;

    /**
     * The path where the .wav file can be found.
     */
    private String path;

    /**
     * Creates the audio clip.
     *
     * @param pathToWavFile the path where the .wav file can be found
     */
    public Audio(String pathToWavFile) {
        path = pathToWavFile;
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File(path).getAbsoluteFile())) {
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the audio clip.
     */
    public void play() {
        clip.start();

        if (!clip.isRunning()) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    /**
     * Plays the audio clip in a continuous loop.
     */
    public void playIndefinitely() {
        if (!clip.isRunning()) {
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stops the audio clip.
     */
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Indicates whether the audio clip is running or not.
     *
     * @return true if running, false otherwise
     */
    public boolean isPlaying() {
        return clip.isRunning();
    }

    /**
     * Disposes the audio clip.
     */
    public void dispose() {
        clip.flush();
    }
}
