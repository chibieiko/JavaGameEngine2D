package com.ebingine.gameObjects;

import java.io.Serializable;

/**
 * Handles variables for all objects extending Sprite.
 * <p>
 * Provides a constructor and set and get methods for all variables.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public abstract class Sprite extends GameObject implements Serializable {

    /**
     * Informs how much to advance on the x coordinate level when Player moves.
     */
    private float speedX;

    /**
     * Informs how much to advance on the y coordinate level when Player moves.
     */
    private float speedY;

    /**
     * Indicates whether the sprite is alive or not.
     */
    private boolean alive;

    /**
     * Indicates whether the sprite can move or not.
     */
    private boolean canMove;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX coordinate x
     * @param coordinateY coordinate y
     * @param height sprite's height
     * @param width sprite's width
     */
    public Sprite(int coordinateX, int coordinateY, int width, int height) {
        setX(coordinateX);
        setY(coordinateY);
        super.setHeight(height);
        super.setWidth(width);
        speedX = 10;
        speedY = 10;
        alive = true;
        canMove = true;
    }

    /**
     * Determines in what way the sprite moves.
     *
     * @param delta game's delta time
     */
    public abstract void move(double delta);

    /**
     * Gets speed for coordinate level x.
     *
     * @return speed x
     */
    public float getSpeedX() {
        return speedX;
    }

    /**
     * Sets speed for coordinate level x.
     *
     * @param speedX new speed x
     */
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    /**
     * Gets speed for coordinate level y.
     *
     * @return speed y
     */
    public float getSpeedY() {
        return speedY;
    }

    /**
     * Sets speed for coordinate level y.
     *
     * @param speedY new speed y
     */
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    /**
     * Indicates whether sprite is alive or not.
     *
     * @return true when alive, false when dead
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets sprite alive or not alive.
     *
     * @param alive new alive state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Indicates whether the sprite can move.
     *
     * @return true when sprite can move, false otherwise
     */
    public boolean canMove() {
        return canMove;
    }

    /**
     * Enables sprite to move or disables movement.
     *
     * @param canMove true if sprite can move, false otherwise
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
