package com.ebingine.resources;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Texture holds an image and the image's information
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Texture implements Drawable {

    /**
     * The image of the texture.
     */
    private BufferedImage image;

    /**
     * The width of the texture.
     */
    private int width;

    /**
     * The height of the texture.
     */
    private int height;

    /**
     * Position x.
     */
    private int x;

    /**
     * Position y.
     */
    private int y;

    /**
     * Creates the texture.
     *
     * @param path the path where the image file can be found
     */
    public Texture(String path) {
        image = null;
        try {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
            x = 0;
            y = 0;
        } catch (IOException e) {
            System.out.println("Could not read image file");
            e.printStackTrace();
        }
    }

    /**
     * Creates the texture with a ready image.
     *
     * @param image the image of the texture
     */
    public Texture(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 0;
    }

    /**
     * Returns the textures image.
     *
     * @return the image of the texture
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets a new image for the texture.
     *
     * @param image the new image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Disposes the image.
     */
    public void dispose() {
        if (image != null) {
            image.flush();
        }
    }

    /**
     * Returns the textures width.
     *
     * @return the width of the texture
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets a new width for the texture.
     *
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the textures height.
     *
     * @return the height of the texture
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets a new height for the texture.
     *
     * @param height the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets a new position for the texture.
     *
     * @param x new position x
     * @param y new position y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets a new size for the texture.
     *
     * @param width new width
     * @param height new height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns position x.
     *
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns position y.
     *
     * @return y position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a new position x for the texture.
     *
     * @param x new position x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets a new position y for the texture.
     *
     * @param y new position y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adds the texture to be drawn.
     */
    public void addToRender() {
        GameContainer.drawables.add(this);
    }

    /**
     * Draws the texture.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, width, height, null);
    }
}
