package com.ebingine.gameObjects;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;
import com.ebingine.resources.Texture;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Holds useful variables and methods for game objects.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public abstract class GameObject implements Drawable, Serializable {

    /**
     * Contains height of the game object.
     */
    private int height;

    /**
     * Contains width of the game object.
     */
    private int width;

    /**
     * Contains the texture of the game object.
     */
    private transient Texture texture;

    /**
     * Position x.
     */
    private float x;

    /**
     * Position y.
     */
    private float y;

    /**
     * Game object's rectangle.
     */
    private Rectangle2D.Float rectangle;

    /**
     * Game object's ellipse.
     */
    private Ellipse2D.Float ellipse;

    /**
     * Adds the game objects to the drawables array so it will be drawn.
     */
    public void addDrawable() {
        synchronized (GameContainer.drawables) {
            GameContainer.drawables.add(this);
        }
    }

    /**
     * Returns game object's ellipse.
     *
     * @return the ellipse of the game object
     */
    public Ellipse2D getEllipse() {
        return ellipse;
    }

    /**
     * Sets a new ellipse for the game object.
     *
     * @param x new coordinate x
     * @param y new coordinate y
     * @param width new width
     * @param height new height
     */
    public void setEllipse(float x, float y, float width, float height) {
        this.ellipse = new Ellipse2D.Float(x, y, width, height);
    }

    /**
     * Returns the game object's texture.
     *
     * @return the texture of the game object
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Sets a new texture for the game object.
     *
     * @param texture the new texture
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Returns the game object's rectangle.
     *
     * @return game object's rectangle
     */
    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }

    /**
     * Sets a new rectangle for the game object.
     *
     * @param x new position x
     * @param y new position y
     * @param width new width
     * @param height new height
     */
    public void setRectangle(int x, int y, int width, int height) {
        this.rectangle = new Rectangle2D.Float(x, y, width, height);
    }

    /**
     * Gets height.
     *
     * @return game object's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return game object's width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets position x.
     *
     * @return game object's position x
     */
    public float getX() {
        return x;
    }

    /**
     * Sets x and updates rectangle and ellipse if they exist.
     *
     * @param x new x position.
     */
    public void setX(float x) {
        this.x = x;

        if (rectangle != null)
            updateRect((int) x, (int) rectangle.getY());

        if (ellipse != null) {
            updateEllipse(x, (float) ellipse.getY());
        }
    }

    /**
     * Gets position y.
     *
     * @return game object's position y
     */
    public float getY() {
        return y;
    }

    /**
     * Sets y and updates rectangle and ellipse if they exist.
     *
     * @param y new y position.
     */
    public void setY(float y) {
        this.y = y;

        if (rectangle != null)
            updateRect((int) rectangle.getX(), (int) y);

        if (ellipse != null)
            updateEllipse((float) ellipse.getX(), y);
    }

    /**
     * Determines how the game object moves.
     *
     * @param delta game's delta time
     */
    public abstract void move(double delta);

    /**
     * Updates game object's rectangle's position.
     *
     * @param x new position x
     * @param y new position y
     */
    public void updateRect(int x, int y) {
        if (rectangle != null) {
            rectangle.setRect(x, y, rectangle.getWidth(),
                    rectangle.getHeight());
        }
    }

    /**
     * Updates game object's ellipse's position.
     *
     * @param x new position x
     * @param y new position y
     */
    public void updateEllipse(float x, float y) {
        if (ellipse != null) {
            ellipse.setFrame(x, y, ellipse.getWidth(), ellipse.getHeight());
        }
    }

    /**
     * Checks collisions between game object and rectangle.
     *
     * If game object has a rectangle, checks collision between that rectangle
     * and the other rectangle. If there was no collision and game object has
     * an ellipse, then checks collisions between ellipse and the other
     * rectangle.
     *
     * @param rect the other rectangle to check collisions with
     * @return true if collision detected, otherwise false
     */
    public boolean collidesWith(Rectangle2D rect) {
        boolean collision = false;

        if (rectangle != null && rectangle.intersects(rect)) {
            collision = true;
        }

        if (!collision && ellipse != null && ellipse.intersects(rect)) {
            collision = true;
        }

        return collision;
    }

    /**
     * Checks collisions between game object and another rectangle.
     *
     * Game maker can choose whether to check collision between game object's
     * rectangle and the other rectangle or between game object's ellipse and
     * the other rectangle.
     *
     * @param rect the other rectangle to check collisions with
     * @param myRectangle true if checking collision with game object's
     *                    rectangle, false if with ellipse
     * @return true if collision detected, otherwise false
     */
    public boolean collidesWith(Rectangle2D rect, boolean myRectangle) {
        boolean collision = false;

        if (myRectangle) {
            if (rectangle != null && rectangle.intersects(rect))
                collision = true;
        } else {
            if (ellipse != null && ellipse.intersects(rect))
                collision = true;
        }

        return collision;
    }

    /**
     * Checks collisions between game objects rectangle and another ellipse.
     *
     * If game object has a rectangle, checks collision between that rectangle
     * and the other ellipse. If there was no collision and game object has
     * an ellipse, then checks collisions between ellipse and the other
     * ellipse. Collision detection between ellipses is optimized for circles.
     *
     * @param circle the ellipse to check collisions with
     * @return true if collision detected, otherwise false
     */
    public boolean collidesWith(Ellipse2D circle) {
        boolean collision = false;

        if (rectangle != null && circle.intersects(rectangle)) {
            collision = true;
        }

        if (ellipse != null && !collision) {
            int coordX = (int) ellipse.getX() + width/2;
            int coordY = (int) ellipse.getY() + height/2;
            int radius = width / 2;

            int coordX2 = (int) (circle.getX() + circle.getWidth() / 2);
            int coordY2 = (int) (circle.getY() + circle.getHeight() / 2);
            int radius2 = (int) circle.getWidth() / 2;

            int distanceX = Math.abs(coordX - coordX2);
            int distanceY = Math.abs(coordY - coordY2);

            int distance = (int) Math.sqrt(distanceX * distanceX + distanceY *
                    distanceY);

            int collisionDistance = radius + radius2;

            if (distance < collisionDistance)
                collision = true;
        }

        return collision;
    }
}
