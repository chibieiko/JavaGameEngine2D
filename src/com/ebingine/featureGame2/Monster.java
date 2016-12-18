package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Animation;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.tiled.ObjectLayer;
import com.ebingine.tiled.TiledMap;
import com.ebingine.tiled.TiledObject;
import com.ebingine.utils.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1215
 * @since 1.7
 */
public class Monster extends Sprite {

    private transient TiledMap tiled;
    private float gravity = 0.1f;
    private float fallDown = 0;
    private transient TiledObject ground;
    private transient TiledObject leftBorder;
    private transient TiledObject rightBorder;
    private transient ObjectLayer platforms;
    private transient Animation walk;
    private transient Texture currentFrame;
    private boolean flip = true;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param width       int width
     * @param height      int height
     */
    public Monster(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setRectangle(coordinateX, coordinateY, width, height);
        setSpeedX(75);
        setSpeedY(5);
        createWalkAnimation();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (flip) {
            g2d.drawImage(currentFrame.getImage(), (int) getX() + getWidth(),
                    (int) getY(),
                    -getWidth(),
                    getHeight(), null);
        } else {
            g2d.drawImage(currentFrame.getImage(), (int) getX(), (int) getY(),
                    getWidth(),
                    getHeight(), null);
        }
    }

    @Override
    public void move(double delta) {
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

    public void createWalkAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.monster.getImage(), 4, 1);
        walk = new Animation(10d / 60d, images);
        walk.start();
        currentFrame = walk.getKeyFrame();
    }

    public void updateAnimations(double delta) {
        walk.update(delta);
        currentFrame = walk.getKeyFrame();
    }

    public void setTiled(TiledMap tiled) {
        this.tiled = tiled;
        ground = tiled.getObject("border-bottom");
        leftBorder = tiled.getObject("border-left");
        rightBorder = tiled.getObject("border-right");
        platforms = tiled.getObjectLayer("platform-obj");
    }

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
