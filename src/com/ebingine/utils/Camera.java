package com.ebingine.utils;

import com.ebingine.GameContainer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Shows what is visible of the game world.
 * <p>
 * Has a default size of 800x600.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Camera {

    /**
     * The width of the camera viewport.
     */
    private int viewportSizeX;

    /**
     * The height of the camera viewport.
     */
    private int viewportSizeY;

    /**
     * Camera's position x.
     */
    private int x;

    /**
     * Camera's position y.
     */
    private int y;

    /**
     * Used to check whether a game object is showing on camera or not.
     */
    private Rectangle2D.Float rectangle;

    /**
     * Initializes camera to a default size of 800x600.
     *
     * The camera's rectangle is created to be slightly bigger than the
     * camera to ensure everything gets drawn.
     *
     * @param gc a game container
     */
    public Camera(GameContainer gc) {
        viewportSizeX = 800;
        viewportSizeY = 600;
        tieToScreenMaxBounds();
        x = 0;
        y = 0;
        rectangle = new Rectangle2D.Float(x - viewportSizeX / 4,
                y - viewportSizeY / 4,
                viewportSizeX + viewportSizeX / 2,
                viewportSizeY + viewportSizeY / 2);
    }

    /**
     * Scales down camera's viewport size if it exceeds device's screen size.
     */
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

    /**
     * Sets a new position for the camera.
     *
     * @param x new position x
     * @param y new position y
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Returns camera's viewport width.
     *
     * @return the width of the viewport
     */
    public int getViewportSizeX() {
        return viewportSizeX;
    }

    /**
     * Sets a new width for the camera's viewport.
     *
     * @param viewportSizeX the new width
     */
    public void setViewportSizeX(int viewportSizeX) {
        this.viewportSizeX = viewportSizeX;
    }

    /**
     * Returns camera's viewport height.
     *
     * @return the height of the viewport
     */
    public int getViewportSizeY() {
        return viewportSizeY;
    }

    /**
     * Sets a new height for the camera's viewport.
     *
     * @param viewportSizeY the new height
     */
    public void setViewportSizeY(int viewportSizeY) {
        this.viewportSizeY = viewportSizeY;
    }

    /**
     * Returns camera's position x.
     *
     * @return the position x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns camera's position y.
     *
     * @return the position y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a new position x.
     *
     * Updates camera's rectangle's position x.
     *
     * @param x new position x
     */
    public void setX(int x) {
        this.x = x;
        rectangle.setRect(x - viewportSizeX / 4,
                rectangle.getY(),
                rectangle.getWidth(),
                rectangle.getHeight());
    }

    /**
     * Sets a new position y.
     *
     * Updates camera's rectangle's position y.
     *
     * @param y new position y
     */
    public void setY(int y) {
        this.y = y;
        rectangle.setRect(rectangle.getX(), y - viewportSizeY / 4,
                rectangle.getWidth(),
                rectangle.getHeight());
    }

    /**
     * Returns camera's rectangle.
     *
     * @return the rectangle of the camera
     */
    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }
}
