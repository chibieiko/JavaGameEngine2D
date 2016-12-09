package com.ebingine.tiled;

import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1206
 * @since 1.7
 */
public class Layer {

    String name;
    int width;
    int height;
    ArrayList<Tile> tiles = new ArrayList<>();

    public Layer(String name, int width, int height,
                 ArrayList<Tile> tiles) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
