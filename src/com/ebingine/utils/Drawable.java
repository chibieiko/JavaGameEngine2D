package com.ebingine.utils;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1121
 * @since 1.7
 */
// For objects, pictures, anything you want to draw.
public class Drawable {
    Image img;
    float x;
    float y;
    int width;
    int height;

    public Drawable(Image img, float x, float y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
