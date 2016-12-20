package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.resources.Animation;
import com.ebingine.resources.Audio;
import com.ebingine.resources.Texture;

import java.awt.*;

/**
 * Player's bullet.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Bullet extends Sprite {

    /**
     * Explosion's animation.
     */
    private transient Animation explosion;

    /**
     * The current frame to be drawn.
     */
    private transient Texture currentFrame;

    /**
     * Indicates whether the bullet has collided with something.
     */
    private boolean collision;

    /**
     * Indicates the direction to which the bullet travels to.
     */
    private boolean flip;

    /**
     * The explosion sound.
     */
    private Audio explode;

    /**
     * Constructor sets bullet's variable values.
     *
     * @param coordinateX coordinate x
     * @param coordinateY coordinate y
     * @param width width
     * @param height height
     * @param flip the direction to which the bullet will travel
     */
    public Bullet(int coordinateX, int coordinateY, int width, int height,
                  boolean flip) {
        super(coordinateX, coordinateY, width, height);
        setRectangle(coordinateX, coordinateY, width, height);
        setSpeedX(200);
        setTexture(AssetManager.bullet);
        explode = new Audio(
                "src/com/ebingine/featureGame2/assets/explosionSound.wav");
        currentFrame = getTexture();
        createExplosionAnimation();
        collision = false;
        setAlive(true);
        this.flip = flip;
    }

    /**
     * Draws the bullet.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(currentFrame.getImage(), (int) getX(), (int) getY(),
                getWidth(),
                getHeight(), null);
    }

    /**
     * Moves the bullet according to flip.
     *
     * @param delta game's delta time
     */
    @Override
    public void move(double delta) {
        if (flip) {
            setX(getX() - (getSpeedX() * (float) delta));
        } else {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }

    /**
     * Creates the explosion animation.
     */
    private void createExplosionAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.explosion.getImage(), 8, 4);
        explosion = new Animation(0.1d / 60d, images);
        explosion.start();
    }

    /**
     * Updates the animation's state.
     *
     * @param delta game's delta time
     */
    public void updateAnimations(double delta) {
        if (!explosion.isLoopFull()) {
            explosion.update(delta);
            currentFrame = explosion.getKeyFrame();
        } else {
            setAlive(false);
        }
    }

    /**
     * Returns whether bullet is colliding with something.
     *
     * @return true if colliding, otherwise false
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Sets a new collision state.
     *
     * @param collision new collision state
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * Play's the explosion sound.
     */
    public void playExplodeSound() {
        explode.play();
    }
}
