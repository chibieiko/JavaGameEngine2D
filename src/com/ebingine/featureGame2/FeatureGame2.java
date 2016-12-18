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

    public FeatureGame2() {
        gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.setWidth(gameScreen.tiled.getMapWidth()
                * gameScreen.tiled.getTileWidth());
        gc.setHeight(gameScreen.tiled.getMapHeight()
                * gameScreen.tiled.getTileHeight());
        gc.getCamera().setViewportSizeX(gc.getWidth());
        gc.getCamera().setViewportSizeY(gc.getHeight());
        // Scales down the game window if game size cannot fit to device screen.
        gc.getCamera().tieToScreenMaxBounds();
        gc.start();

        String[] keyArray = {"SPACE", "A", "D", "control S", "T", "G", "B",
                "W"};
        GameContainer.input.addInputKey(keyArray);
    }
}
