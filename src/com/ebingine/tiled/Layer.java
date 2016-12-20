package com.ebingine.tiled;

import java.util.ArrayList;

/**
 * Tiled map's layer.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Layer {

    /**
     * The name of the layer.
     */
    private String name;

    /**
     * The width of the layer.
     */
    private int width;

    /**
     * The height of the layer.
     */
    private int height;

    /**
     * Holds all the tiles belonging to this layer.
     */
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Creates the layer.
     *
     * @param name the name of the layer
     * @param width the width of the layer
     * @param height the height of the layer
     * @param tiles the tiles belonging to this layer
     */
    public Layer(String name, int width, int height,
                 ArrayList<Tile> tiles) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    /**
     * Returns the name of the layer.
     *
     * @return the name of the layer
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the width of the layer.
     *
     * @return the width of the layer
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the layer.
     *
     * @return the height of the layer
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the tiles belonging to this layer.
     *
     * @return the tiles of the layer
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
