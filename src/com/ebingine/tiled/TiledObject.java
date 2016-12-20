package com.ebingine.tiled;

import java.awt.geom.Rectangle2D;

/**
 * An object inside the tiled map.
 * <p>
 * Holds the detailed information of one object created in the tiled map.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class TiledObject {

    /**
     * The id of the object.
     */
    private int id;

    /**
     * The name of the object.
     */
    private String name;

    /**
     * Position x.
     */
    private int x;

    /**
     * Position y.
     */
    private int y;

    /**
     * The width of the object.
     */
    private int width;

    /**
     * The height of the object.
     */
    private int height;

    /**
     * The objects rectangle.
     */
    private Rectangle2D rectangle;

    /**
     * Initializes the object and creates a rectangle for it.
     *
     * @param id the id of the object
     * @param name the name of the object
     * @param x position x
     * @param y position y
     * @param width the width of the object
     * @param height the width of the object
     */
    public TiledObject(int id, String name, int x, int y,
                       int width, int height) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle2D.Float(x, y, width, height);
    }

    /**
     * Returns the object's id.
     *
     * @return the id of the object
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the object's name.
     *
     * @return the name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the object's position x.
     *
     * @return position x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the object's position y.
     *
     * @return position y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the objects width.
     *
     * @return the width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the objects height.
     *
     * @return the height of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the object's rectangle.
     *
     * @return the rectangle of the object
     */
    public Rectangle2D getRectangle() {
        return rectangle;
    }
}
