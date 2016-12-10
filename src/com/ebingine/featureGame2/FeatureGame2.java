package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.tiled.TiledMap;
import com.ebingine.utils.Input;

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
        TiledMap tiledMap = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap2.tmx");
        gameScreen = new GameScreen(tiledMap);
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

        String[] keyArray = {"SPACE", "A", "D", "control S", "T", "G", "B"};
        GameContainer.input.addInputKey(keyArray);
    }
}
