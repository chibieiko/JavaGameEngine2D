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
    private int offsetMaxX;
    private int offsetMaxY;
    private int offsetMinX;
    private int offsetMinY;
    private int camX;
    private int camY;

    // Used to check whether it is necessary to draw a game object or not.
    private Rectangle2D.Float rectangle;

    public Camera(GameContainer gc) {
        viewportSizeX = 800;
        viewportSizeY = 600;

        tieToScreenMaxBounds();

        offsetMaxX = gc.getWidth() - viewportSizeX;
        offsetMaxY = gc.getHeight() - viewportSizeY;
        offsetMinX = 0;
        offsetMinY = 0;
        camX = 0;
        camY = 0;
        rectangle = new Rectangle2D.Float(camX, camY, viewportSizeX, viewportSizeY);
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

    public void update(int x, int y) {
        /*if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        } else {*/
            camX = x - viewportSizeX /2;
        //}

        /*if (camY > offsetMaxY) {
            camY = offsetMaxY;
        } else if (camY < offsetMinY) {
            camY = offsetMinY;
        } else {*/
            camY = y - viewportSizeY /2;
       // }
    }

    public void updateOffSets(int width, int height) {
        setOffsetMaxX(width - viewportSizeX);
        setOffsetMaxY(height - viewportSizeY);
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

    public int getOffsetMaxX() {
        return offsetMaxX;
    }

    public void setOffsetMaxX(int offsetMaxX) {
        this.offsetMaxX = offsetMaxX;
    }

    public int getOffsetMaxY() {
        return offsetMaxY;
    }

    public void setOffsetMaxY(int offsetMaxY) {
        this.offsetMaxY = offsetMaxY;
    }

    public int getOffsetMinX() {
        return offsetMinX;
    }

    public void setOffsetMinX(int offsetMinX) {
        this.offsetMinX = offsetMinX;
    }

    public int getOffsetMinY() {
        return offsetMinY;
    }

    public void setOffsetMinY(int offsetMinY) {
        this.offsetMinY = offsetMinY;
    }

    public int getCamX() {
        return camX;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }

    public Rectangle2D.Float getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D.Float rectangle) {
        this.rectangle = rectangle;
    }
}
