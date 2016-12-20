package com.ebingine.tiled;

/**
 * Contains information of a tile in a tiled map layer.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Tile {

    /**
     * The tile's image coordinate in TiledMap's images array.
     */
    private int imageCoordinate;

    /**
     * Position x on the map.
     */
    private int x;

    /**
     * Position y on the map.
     */
    private int y;

    /**
     * Creates the tile.
     *
     * @param imageCoordinate a tile's image coordinate in TiledMap's
     *                        images array
     * @param x position x on the map
     * @param y position y on the map
     */
    public Tile(int imageCoordinate, int x, int y) {
        this.imageCoordinate = imageCoordinate;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns tile's image's coordinate.
     *
     * @return tile's image's coordinate
     */
    public int getImageCoordinate() {
        return imageCoordinate;
    }

    /**
     * Returns position x.
     *
     * @return the position x on the map
     */
    public int getX() {
        return x;
    }

    /**
     * Returns position y.
     *
     * @return the position y on the map
     */
    public int getY() {
        return y;
    }
}
