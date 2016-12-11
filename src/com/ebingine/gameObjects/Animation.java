package com.ebingine.gameObjects;

import java.awt.image.BufferedImage;

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

    BufferedImage keyFrame;
    BufferedImage[] frames;
    double time;
    double timeLimiter = 0;
    double frameCount = 0;
    int currentFrame = 0;
    boolean stopped;

    public Animation(double time, BufferedImage[] frames) {
        if (time <= 0) {
            throw new RuntimeException("Invalid time: " + time);
        }

        this.time = time;
        this.frames = frames;
        stopped = true;
    }

    public void start() {
        if (!stopped)
            return;

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

    public BufferedImage getKeyFrame() {
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
