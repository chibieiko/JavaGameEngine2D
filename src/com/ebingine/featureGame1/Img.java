package com.ebingine.featureGame1;

import com.ebingine.gameObjects.GameObject;
import com.ebingine.utils.Drawable;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1122
 * @since 1.7
 */
public class Img extends GameObject implements Drawable {

    public Img(Image image, int x, int y, int width,
               int height) {
        super.setImg(image);
        super.setX(x);
        super.setY(y);
        super.setWidth(width);
        super.setHeight(height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getImg(), (int) getX(), (int) getY(), getWidth(),
                getHeight(), null);
    }
}
