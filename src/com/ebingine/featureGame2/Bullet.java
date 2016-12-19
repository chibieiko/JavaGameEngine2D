package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.resources.Animation;
import com.ebingine.resources.Audio;
import com.ebingine.resources.Texture;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1218
 * @since 1.7
 */
public class Bullet extends Sprite {

    private transient Animation explosion;
    private transient Texture currentFrame;
    private boolean collision;
    private boolean flip;
    private Audio explode;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param width       int width
     * @param height      int height
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

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(currentFrame.getImage(), (int) getX(), (int) getY(),
                getWidth(),
                getHeight(), null);
    }

    @Override
    public void move(double delta) {
        if (flip) {
            setX(getX() - (getSpeedX() * (float) delta));
        } else {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }

    public void createExplosionAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.explosion.getImage(), 8, 4);
        explosion = new Animation(0.1d / 60d, images);
        explosion.start();
    }

    public void updateAnimations(double delta) {
        if (!explosion.isLoopFull()) {
            explosion.update(delta);
            currentFrame = explosion.getKeyFrame();
        } else {
            setAlive(false);
        }
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void playExplodeSound() {
        explode.play();
    }
}