package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.resources.Animation;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.tiled.ObjectLayer;
import com.ebingine.tiled.TiledMap;
import com.ebingine.tiled.TiledObject;
import com.ebingine.resources.Texture;

import java.awt.*;

/**
 * A monster that can kill the player.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Monster extends Sprite {

    /**
     * Determines how strong the pull of gravity is.
     */
    private float gravity;

    /**
     * Determines falling down speed.
     */
    private float fallDown;

    /**
     * The ground of the tiled map.
     */
    private transient TiledObject ground;

    /**
     * The left border of the tiled map.
     */
    private transient TiledObject leftBorder;

    /**
     * The left border of the tiled map.
     */
    private transient TiledObject rightBorder;

    /**
     * The platforms of the tiled map.
     */
    private transient ObjectLayer platforms;

    /**
     * Animation for walking.
     */
    private transient Animation walk;

    /**
     * The frame of the monster to be drawn.
     */
    private transient Texture currentFrame;

    /**
     * Indicates which way to draw the monster.
     */
    private boolean flip;

    /**
     * Constructor sets monster's variable values.
     *
     * @param coordinateX coordinate x
     * @param coordinateY coordinate y
     * @param width width
     * @param height height
     */
    public Monster(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setRectangle(coordinateX, coordinateY, width, height);
        setSpeedX(75);
        setSpeedY(5);
        gravity = 0.1f;
        fallDown = 0;
        flip = true;
        createWalkAnimation();
        addDrawable();
    }

    /**
     * Draws the monster depending on flip and isAlive() states.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (flip && isAlive()) {
            g2d.drawImage(currentFrame.getImage(), (int) getX() + getWidth(),
                    (int) getY(),
                    -getWidth(),
                    getHeight(), null);
        } else if (flip && !isAlive()) {
            g2d.drawImage(currentFrame.getImage(), (int) getX() + getWidth(),
                    (int) getY() + getHeight(),
                    -getWidth(),
                    -getHeight(), null);
        } else if (!flip && isAlive()) {
            g2d.drawImage(currentFrame.getImage(), (int) getX(), (int) getY(),
                    getWidth(),
                    getHeight(), null);
        } else if (!flip && !isAlive()) {
            g2d.drawImage(currentFrame.getImage(), (int) getX(),
                    (int) getY() + getHeight(),
                    getWidth(),
                    -getHeight(), null);
        }
    }

    /**
     * Moves the monster accordingly.
     *
     * @param delta game's delta time
     */
    @Override
    public void move(double delta) {
        if (isAlive()) {
            if (collidesWith(ground.getRectangle()) || checkPlatforms()) {
                fallDown = 0;
                // Creates gravity.
            } else {
                setY(getY() + fallDown);
                fallDown += gravity;
            }

            if (collidesWith(leftBorder.getRectangle()) ||
                    collidesWith(rightBorder.getRectangle())) {
                setSpeedX(-getSpeedX());
                setX(getX() - (getSpeedX() * (float) delta));
                flip = !flip;
            } else {
                setX(getX() - (getSpeedX() * (float) delta));
            }

            updateAnimations(delta);
        }
    }

    /**
     * Creates walking animation.
     */
    private void createWalkAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.monster.getImage(), 4, 1);
        walk = new Animation(10d / 60d, images);
        walk.start();
        currentFrame = walk.getKeyFrame();
    }

    /**
     * Updates animation state.
     *
     * @param delta game's delta time
     */
    public void updateAnimations(double delta) {
        walk.update(delta);
        currentFrame = walk.getKeyFrame();
    }

    /**
     * Initiates tiled map objects.
     *
     * @param tiled a tiled map
     */
    public void setTiled(TiledMap tiled) {
        ground = tiled.getObject("border-bottom");
        leftBorder = tiled.getObject("border-left");
        rightBorder = tiled.getObject("border-right");
        platforms = tiled.getObjectLayer("platform-obj");
    }

    /**
     * Checks if monster collides with platforms.
     *
     * @return true if collision, false otherwise
     */
    public boolean checkPlatforms() {
        boolean platformCollision = false;
        for (int i = 0; i < platforms.getTiledObjects().size(); i++) {
            if (collidesWith(platforms.getTiledObjects().get(i)
                    .getRectangle())) {
                platformCollision = true;
            }
        }

        return platformCollision;
    }
}
