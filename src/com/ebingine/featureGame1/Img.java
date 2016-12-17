package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.utils.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1122
 * @since 1.7
 */
public class Img implements Drawable {

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    public Img(BufferedImage image, int x, int y, int width,
               int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        GameContainer.drawables.add(this);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, width, height, null);
    }
}
