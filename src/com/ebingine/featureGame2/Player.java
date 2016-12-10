package com.ebingine.featureGame2;

import com.ebingine.gameObjects.Sprite;
import com.ebingine.tiled.ObjectLayer;
import com.ebingine.tiled.TiledMap;
import com.ebingine.tiled.TiledObject;
import com.ebingine.utils.Input;

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

    private TiledMap tiled;
    private float gravity = 0.1f;
    private float velocity = 5f;
    private float currentVelocity = velocity;
    private float fallDown = 0;
    private boolean jumped = false;
    TiledObject ground;
    TiledObject leftBorder;
    TiledObject rightBorder;
    ObjectLayer platforms;

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
        setImg(AssetManager.rosette);
        setRectangle(coordinateX, coordinateY, width, height);
        setSpeedX(75);
        setSpeedY(5);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getImg(), (int) getX(), (int) getY(), getWidth(),
                getHeight(), null);
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

        if (Input.keyPressed("A") && !collidesWith(leftBorder.getRectangle())) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (Input.keyPressed("D") && !collidesWith(rightBorder.getRectangle())) {
            setX(getX() + (getSpeedX() * (float) delta));
        }

        if (Input.keyTyped("SPACE")) {
            jumped = true;
        }
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
