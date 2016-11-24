package com.ebingine.utils;

import com.ebingine.GameContainer;

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

    // ---- Camera settings --------
    private int viewportSizeX;
    private int viewportSizeY;
    private int offsetMaxX;
    private int offsetMaxY;
    private int offsetMinX;
    private int offsetMinY;
    private int camX;
    private int camY;
    // ------------------------------

    public Camera(GameContainer gc) {
        viewportSizeX = gc.getWidth();
        viewportSizeY = gc.getHeight();
        offsetMaxX = gc.getWidth() - viewportSizeX;
        offsetMaxY = gc.getHeight() - viewportSizeY;
        offsetMinX = 0;
        offsetMinY = 0;
        camX = 0;
        camY = 0;
    }

    public void update(int x, int y) {
        if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        } else {
            camX = x -viewportSizeX /2;
        }

        if (camY > offsetMaxY) {
            camY = offsetMaxY;
        } else if (camY < offsetMinY) {
            camY = offsetMinY;
        } else {
            camY = y -viewportSizeY /2;
        }
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
}
