package com.ebingine.resources;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1211
 * @since 1.7
 */
public class Animation {

    private Texture[] frames;
    private double time;
    private double timeLimiter;
    private double frameCount;
    private int currentFrame;
    private boolean stopped;
    private int totalFrames;
    private boolean loopFull;

    public Animation(double time, Texture[] frames) {
        if (time <= 0) {
            throw new RuntimeException("Invalid time: " + time);
        }

        this.time = time;
        this.frames = frames;
        timeLimiter = 0;
        frameCount = 0;
        currentFrame = 0;
        stopped = true;
        totalFrames = 0;
        loopFull = false;
    }

    public void start() {
        if (!stopped) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        stopped = true;
    }

    public void reset() {
        stopped = true;
        frameCount = 0;
        currentFrame = 0;
    }

    public Texture getKeyFrame() {
        return frames[currentFrame];
    }

    public void update(double stateTime) {
        if (!stopped) {
            frameCount += stateTime;
            if (frameCount > time) {
                if (currentFrame < frames.length - 1) {
                    currentFrame++;
                } else {
                    currentFrame = 0;
                }

                timeLimiter += stateTime;
                frameCount = 0;
                // Enables access to the info whether animation loop has
                // looped once.
                if (!loopFull) {
                    totalFrames++;
                    if (totalFrames == frames.length) {
                        loopFull = true;
                    }
                }
            }
        }
    }

    public boolean isLoopFull() {
        return loopFull;
    }
}
