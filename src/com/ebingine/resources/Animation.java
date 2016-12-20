package com.ebingine.resources;

/**
 * Enables animations for game objects and images.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Animation {

    /**
     * The frames of the animation.
     */
    private Texture[] frames;

    /**
     * The speed of the animation.
     */
    private double time;

    /**
     * Used to check when to change frames.
     */
    private double frameCount;

    /**
     * The current frame of the animation.
     */
    private int currentFrame;

    /**
     * Indicates whether the animation has been stopped.
     */
    private boolean stopped;

    /**
     * The count of all the frames in the animation.
     */
    private int totalFrames;

    /**
     * Indicates if animation has looped at least once.
     */
    private boolean loopFull;

    /**
     * Creates the animation.
     *
     * @param time the speed of the animation
     * @param frames the frames of the animation
     */
    public Animation(double time, Texture[] frames) {
        if (time <= 0) {
            throw new RuntimeException("Invalid time: " + time);
        }

        this.time = time;
        this.frames = frames;
        frameCount = 0;
        currentFrame = 0;
        stopped = true;
        totalFrames = 0;
        loopFull = false;
    }

    /**
     * Starts the animation.
     */
    public void start() {
        if (!stopped) {
            return;
        }

        stopped = false;
    }

    /**
     * Stops the animation.
     */
    public void stop() {
        stopped = true;
    }

    /**
     * Resets the animation.
     */
    public void reset() {
        stopped = true;
        frameCount = 0;
        currentFrame = 0;
    }

    /**
     * Returns the animation's current texture.
     *
     * @return current texture
     */
    public Texture getKeyFrame() {
        return frames[currentFrame];
    }

    /**
     * Updates the animation loop.
     *
     * @param stateTime the game loop's delta time
     */
    public void update(double stateTime) {
        if (!stopped) {
            frameCount += stateTime;
            if (frameCount > time) {
                if (currentFrame < frames.length - 1) {
                    currentFrame++;
                } else {
                    currentFrame = 0;
                }

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

    /**
     * Tells whether the animation has looped at least once.
     */
    public boolean isLoopFull() {
        return loopFull;
    }
}
