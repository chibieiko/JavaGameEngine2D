package com.ebingine.featureGame2;

import com.ebingine.gameObjects.GameObject;
import com.ebingine.tiled.Layer;
import com.ebingine.tiled.TiledMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1210
 * @since 1.7
 */
public class Trees extends GameObject {
    TiledMap tiled;
    Layer trees;
    ArrayList<BufferedImage> tileImages;

    public Trees(TiledMap tiled) {
        this.tiled = tiled;
        trees = tiled.getLayer("trees");
        tileImages = tiled.getImages();
    }

    @Override
    public void move(double delta) {

    }

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
