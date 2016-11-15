package com.ebingine.gameObjects;



import java.awt.*;

/**
 * Handles variables for all objects extending Sprite.
 * <p>
 * Provides a constructor and set and get methods for all variables.
 * </p>
 *
 * @author Erika Sankari
 * @version 2016.1114
 * @since 1.7
 */
public abstract class Sprite extends GameObject{

    /**
     * Contains coordinate x value.
     */
    private int coordinateX;

    /**
     * Contains coordinate y value.
     */
    private int coordinateY;

    /**
     * Informs how much to advance on the x coordinate level when player moves.
     */
    private int speedX;

    /**
     * Informs how much to advance on the y coordinate level when player moves.
     */
    private int speedY;

    /**
     * Indicates whether sprite is alive or not.
     */
    private boolean alive;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param height int height
     * @param width int width
     */
    public Sprite(int coordinateX, int coordinateY, int height, int width) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        super.setHeight(height);
        super.setWidth(width);
        speedX = 10;
        speedY = 10;
        alive = true;
    }

    /**
     * Gets coordinate x.
     *
     * @return int coordinateX
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Sets coordinate x.
     *
     * @param coordinateX int coordinate x
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Gets coordinate y.
     *
     * @return int coordinateY
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Sets coordinate y.
     *
     * @param coordinateY int coordinate y
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Gets speed for coordinate level x.
     *
     * @return int speedX
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Sets speed for coordinate level x.
     *
     * @param speedX int speedX
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * Gets speed for coordinate level y.
     *
     * @return int speedY
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Sets speed for coordinate level y.
     *
     * @param speedY int speedY
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    /**
     * Indicates whether sprite is alive or not.
     *
     * @return boolean alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets sprite alive or not alive.
     *
     * @param alive boolean alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
