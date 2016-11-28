package com.ebingine.gameObjects;

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
public abstract class Sprite extends GameObject {

    /**
     * Informs how much to advance on the x coordinate level when Player moves.
     */
    private float speedX;

    /**
     * Informs how much to advance on the y coordinate level when Player moves.
     */
    private float speedY;

    /**
     * Indicates whether sprite is alive or not.
     */
    private boolean alive;

    boolean canMove;

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param height int height
     * @param width int width
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

    public abstract void move(double delta);

    /**
     * Gets speed for coordinate level x.
     *
     * @return int speedX
     */
    public float getSpeedX() {
        return speedX;
    }

    /**
     * Sets speed for coordinate level x.
     *
     * @param speedX int speedX
     */
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    /**
     * Gets speed for coordinate level y.
     *
     * @return int speedY
     */
    public float getSpeedY() {
        return speedY;
    }

    /**
     * Sets speed for coordinate level y.
     *
     * @param speedY int speedY
     */
    public void setSpeedY(float speedY) {
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

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
