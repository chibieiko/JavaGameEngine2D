package com.ebingine.utils;

import com.ebingine.GameContainer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1124
 * @since 1.7
 */
public class Camera {

    // Camera settings.
    private int viewportSizeX;
    private int viewportSizeY;
    private int x;
    private int y;
    // Used to check whether it is necessary to draw a game object or not.
    private Rectangle2D.Float rectangle;

    public Camera(GameContainer gc) {
        viewportSizeX = 800;
        viewportSizeY = 600;
        tieToScreenMaxBounds();
        x = viewportSizeX / 2;
        y = viewportSizeY / 2;
        rectangle = new Rectangle2D.Float(x, y, viewportSizeX,
                viewportSizeY);
    }

    public void tieToScreenMaxBounds() {
        // Resize game to fit screens that are smaller than the specified width
        // and height in the game container.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenMax = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

        // Height of the task bar.
        int taskbarSize = screenSize.height - screenMax.height;

        float ratio = (float) viewportSizeY / (float) viewportSizeX;

        if (screenMax.getWidth() < viewportSizeX ||
                screenMax.getHeight() - taskbarSize < viewportSizeY) {
            setViewportSizeY((int) (Math.floor(screenMax.getHeight()))
                    - taskbarSize);
            setViewportSizeX((int) Math.floor(screenMax.getHeight() / ratio));
        }
    }

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
        rectangle.setRect(x, y, rectangle.getWidth(), rectangle.getHeight());
    }

    public int getViewportSizeX() {
        return viewportSizeX;
    }

    public void setViewportSizeX(int viewportSizeX) {
        this.viewportSizeX = viewportSizeX;
    }

    public int getViewportSizeY() {
        return viewportSizeY;
    }

    public void setViewportSizeY(int viewportSizeY) {
        this.viewportSizeY = viewportSizeY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        rectangle.setRect(x, y, rectangle.getWidth(), rectangle.getHeight());
    }

    public void setY(int y) {
        this.y = y;
        rectangle.setRect(x, y, rectangle.getWidth(), rectangle.getHeight());
    }

    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D.Float rectangle) {
        this.rectangle = rectangle;
    }
}
