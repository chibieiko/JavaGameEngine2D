package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.tiled.Layer;
import com.ebingine.tiled.TiledMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A tiled layer containing all the trees of the game.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Trees extends GameObject {

    /**
     * A tiled map.
     */
    private TiledMap tiled;

    /**
     * The tiled map layer containing all the tree tiles.
     */
    private Layer trees;

    /**
     * All the tiles of the layer.
     */
    private ArrayList<BufferedImage> tileImages;

    /**
     * Creates an object of the trees layer.
     *
     * @param tiled a tiled map
     */
    public Trees(TiledMap tiled) {
        this.tiled = tiled;
        trees = tiled.getLayer("trees");
        tileImages = tiled.getImages();
        GameContainer.drawables.add(this);
    }

    /**
     * No movement implemented.
     */
    @Override
    public void move(double delta) {}

    /**
     * Draws the tree tiles.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < trees.getTiles().size(); i++) {
            g2d.drawImage(tileImages.get(trees.getTiles().get(i)
                            .getImageCoordinate()),
                    trees.getTiles().get(i).getX(),
                    trees.getTiles().get(i).getY(),
                    tiled.getTileWidth(),
                    tiled.getTileHeight(),
                    null);
        }
    }
}
