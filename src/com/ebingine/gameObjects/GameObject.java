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

    public void setEllipse(float x, float y, float width, float height) {
        this.ellipse = new Ellipse2D.Float(x, y, width, height);
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

    public void setRectangle(int x, int y, int width, int height) {
        this.rectangle = new Rectangle2D.Float(x, y, width, height);
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
        if (rectangle != null)
            updateRect((int) x, (int) rectangle.getY());

        if (ellipse != null) {
            updateEllipse(x, (float) ellipse.getY());
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        if (rectangle != null)
            updateRect((int) rectangle.getX(), (int) y);

        if (ellipse != null)
            updateEllipse((float) ellipse.getX(), y);
    }

    public abstract void move(double delta);

    public void updateRect(int x, int y) {
        if (rectangle != null) {
            rectangle.setRect(x, y, rectangle.getWidth(), rectangle.getHeight());
        }
    }

    public void updateEllipse(float x, float y) {
        if (ellipse != null) {
            ellipse.setFrame(x, y, ellipse.getWidth(), ellipse.getHeight());
        }
    }

    public boolean collidesWith(Rectangle2D rect) {
        boolean collision = false;
        if (rectangle != null && rectangle.intersects(rect))
            collision = true;

        if (!collision && ellipse != null && ellipse.intersects(rect))
            collision = true;

        return collision;
    }

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

    // Checks collisions between two ellipses, meant for two circles.
    public boolean collidesWith(Ellipse2D circle) {
        boolean collision = false;

        if (rectangle != null && circle.intersects(rectangle))
            collision = true;

        if (ellipse != null) {
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

        /*
        System.out.println("My x: " + ellipse.getX());
        System.out.println("My y: " + ellipse.getY());
        System.out.println("x: " + circle.getX());
        System.out.println("y: " + circle.getY());*/

        return collision;
    }


}
