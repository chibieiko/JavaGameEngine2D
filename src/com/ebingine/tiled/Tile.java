package com.ebingine.tiled;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1208
 * @since 1.7
 */
public class Tile {
    private int imageCoordinate;
    private int x;
    private int y;

    public Tile(int imageCoordinate, int x, int y) {
        this.imageCoordinate = imageCoordinate;
        this.x = x;
        this.y = y;
    }

    public int getImageCoordinate() {
        return imageCoordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
