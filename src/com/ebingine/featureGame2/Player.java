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
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.7
 */
public class Player extends Sprite {

    private transient TiledMap tiled;
    private float gravity = 0.1f;
    private float velocity = 5f;
    private float currentVelocity = velocity;
    private float fallDown = 0;
    private boolean jumped = false;
    private transient TiledObject ground;
    private transient TiledObject leftBorder;
    private transient TiledObject rightBorder;
    private transient ObjectLayer platforms;
    private transient Animation walk;
    private transient double statetime = 0;
    private transient Texture currentFrame;
    private boolean walking = false;
    private boolean flip = false;
    private float boostSpeed;
    private boolean boost;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param width       int width
     * @param height      int height
     */
    public Player(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setTexture(AssetManager.rosette);
        setRectangle(coordinateX, coordinateY, width, height);
        setSpeedX(75);
        boostSpeed = getSpeedX() * 2;
        boost = false;
        setSpeedY(5);
        currentFrame = getTexture();
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
        if (!jumped) {
            if (collidesWith(ground.getRectangle()) || checkPlatforms()) {
                fallDown = 0;
                // Creates gravity.
            } else {
                setY(getY() + fallDown);
                fallDown += gravity;
            }
        }

        if (isAlive()) {

            if (GameContainer.input.keyPressed("W")) {
                boost = true;
            } else {
                boost = false;
            }

            walking = false;
            if (GameContainer.input.keyPressed("A") &&
                    !collidesWith(leftBorder.getRectangle())) {
                if (boost) {
                    setX(getX() - (boostSpeed * (float) delta));
                } else {
                    setX(getX() - (getSpeedX() * (float) delta));
                }

                walking = true;
                flip = true;
            }

            if (GameContainer.input.keyPressed("D") &&
                    !collidesWith(rightBorder.getRectangle())) {
                if (boost) {
                    setX(getX() + (boostSpeed * (float) delta));
                } else {
                    setX(getX() + (getSpeedX() * (float) delta));
                }

                walking = true;
                flip = false;
            }

            if (GameContainer.input.keyTyped("SPACE")) {
                jumped = true;
            }

            if (walking) {
                updateAnimations(delta);
            } else {
                currentFrame = getTexture();
            }
        } else {
            currentFrame = AssetManager.rosetteDead;
        }
    }

    public void createWalkAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.rosetteWalk.getImage(), 4, 1);
        walk = new Animation(10d / 60d, images);
        walk.start();
    }

    public void updateAnimations(double delta) {
        walk.update(delta);
        currentFrame.setImage(walk.getKeyFrame().getImage());
    }

    public void jump() {
        if (jumped) {
            if (currentVelocity <= 0 && collidesWith(ground.getRectangle())
                    || currentVelocity <= 0 && checkPlatforms()) {
                jumped = false;
                currentVelocity = velocity;
            } else {
                setY(getY() - currentVelocity);
                currentVelocity -= gravity;
            }
        }
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
