package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Sprite;

import java.awt.*;

/**
 * The player of the game.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Player extends Sprite {

    /**
     * Constructor sets player's variable values.
     *
     * @param coordinateX coordinate x
     * @param coordinateY coordinate y
     * @param height height
     * @param width width
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

    /**
     * Moves the player accordingly.
     *
     * @param delta game's delta time
     */
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

    /**
     * Moves player2 with different keys than the player 1.
     *
     * @param delta game's delta time
     */
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

    /**
     * Draws the player.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getTexture().getImage(), (int) getX(), (int) getY(),
                getWidth(), getHeight(), null);
    }
}
