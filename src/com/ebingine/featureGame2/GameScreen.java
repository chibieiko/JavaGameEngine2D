package com.ebingine.featureGame2;

import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.featureGame1.*;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.tiled.Layer;
import com.ebingine.tiled.TiledMap;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.7
 */
public class GameScreen extends Game {

    Player player;
    TiledMap tiled;

    public GameScreen(TiledMap tiled) {
        this.tiled = tiled;
    }

    @Override
    public void create(GameContainer gc) {
        player = new Player(tiled.getTileWidth(),
                tiled.getPixelHeight() - tiled.getTileHeight() * 3,
                AssetManager.rosette.getWidth(null),
                AssetManager.rosette.getHeight(null));
        player.setTiled(tiled);
        new Trees(tiled);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        player.move(deltaTime);
        player.jump();
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
