package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.tiled.TiledMap;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.7
 */
public class FeatureGame2 {

    GameScreen gameScreen;
    public int height;
    public int width;

    public FeatureGame2() {
        gameScreen = new GameScreen();
        TiledMap tiledMap = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap2.tmx");
        GameContainer gc = new GameContainer(gameScreen);
        gc.setWidth(tiledMap.getMapWidth() * tiledMap.getTileWidth());
        gc.setHeight(tiledMap.getMapHeight() * tiledMap.getTileHeight());
        gc.getCamera().setViewportSizeX(gc.getWidth());
        gc.getCamera().setViewportSizeY(gc.getHeight());
        // Scales down the game window if game size cannot fit to device screen.
        gc.getCamera().tieToScreenMaxBounds();
        gc.start();
        height = gc.getHeight();
        width = gc.getWidth();
    }
}
