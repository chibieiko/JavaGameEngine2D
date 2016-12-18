package com.ebingine.gameObjects;

import com.ebingine.utils.Texture;

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
    private double timeLimiter = 0;
    private double frameCount = 0;
    private int currentFrame = 0;
    private boolean stopped;

    public Animation(double time, Texture[] frames) {
        if (time <= 0) {
            throw new RuntimeException("Invalid time: " + time);
        }

        this.time = time;
        this.frames = frames;
        stopped = true;
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
            }
        }
    }
}
