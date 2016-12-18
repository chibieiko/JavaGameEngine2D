package com.ebingine.resources;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1218
 * @since 1.7
 */
public class Texture implements Drawable {

    private BufferedImage image;
    private int width;
    private int height;
    private int x;
    private int y;

    public Texture(String path) {
        image = null;
        try {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
            x = 0;
            y = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Texture(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 0;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void dispose() {
        if (image != null) {
            image.flush();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addToRender() {
        GameContainer.drawables.add(this);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, width, height, null);
    }
}
