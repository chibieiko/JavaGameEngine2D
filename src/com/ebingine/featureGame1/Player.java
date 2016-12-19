package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Sprite;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1116
 * @since 1.7
 */
public class Player extends Sprite {

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param height      int height
     * @param width       int width
     */
    public Player(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setTexture(AssetManager.player);
        setSpeedY(300);
        setSpeedX(300);
        setEllipse((int) getX(), (int) getY(), getWidth(),
                getHeight());
        addDrawable();
    }

    @Override
    public void move(double delta) {

        if (GameContainer.input.keyPressed("W") &&
                getY() > 0) {
            setY(getY() - (getSpeedY() * (float) delta));
        }

        if (GameContainer.input.keyPressed("S") &&
                getY() < GameContainer.height - getHeight()) {
            setY(getY() + (getSpeedY() * (float) delta));
        }

        if (GameContainer.input.keyPressed("A") && getX() > 0) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (GameContainer.input.keyPressed("D") &&
                getX() < GameContainer.width - getWidth()) {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }

    public void moveP2(double delta) {
        if (GameContainer.input.keyPressed("UP") &&
                getY() > 0) {
            setY(getY() - (getSpeedY() * (float) delta));
        }

        if (GameContainer.input.keyPressed("DOWN") &&
                getY() < GameContainer.height - getHeight()) {
            setY(getY() + (getSpeedY() * (float) delta));
        }

        if (GameContainer.input.keyPressed("LEFT") && getX() > 0) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (GameContainer.input.keyPressed("RIGHT") &&
                getX() < GameContainer.width - getWidth()) {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getTexture().getImage(), (int) getX(), (int) getY(),
                getWidth(), getHeight(), null);
    }
}
