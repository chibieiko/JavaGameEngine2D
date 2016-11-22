package com.ebingine.gameObjects;


import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1114
 * @since 1.7
 */
public abstract class GameObject implements Drawable {

    /**
     * Contains height value.
     */
    private int height;

    /**
     * Contains width value.
     */
    private int width;

    /**
     * Contains the image of the sprite.
     */
    private Image img;
    private float x;
    private float y;

    private Rectangle2D.Float rectangle;

    private Ellipse2D.Float ellipse;

    public GameObject() {
        GameContainer.drawables.add(this);
    }

    public Ellipse2D getEllipse() {
        return ellipse;
    }

    public void setEllipse(Ellipse2D.Float ellipse) {
        this.ellipse = ellipse;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D.Float rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Gets height.
     *
     * @return int height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height int height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return int width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width int width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
