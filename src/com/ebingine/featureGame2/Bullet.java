package com.ebingine.featureGame2;

import com.ebingine.gameObjects.Sprite;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1218
 * @since 1.7
 */
public class Bullet extends Sprite {
    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param width       int width
     * @param height      int height
     */
    public Bullet(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {

    }

    @Override
    public void move(double delta) {

    }
}
