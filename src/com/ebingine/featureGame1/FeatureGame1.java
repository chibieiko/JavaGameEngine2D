package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.featureGame1.screens.GameScreen;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
public class FeatureGame1 {

    GameScreen gameScreen;
    public int height;
    public int width;

    public FeatureGame1() {
        gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.getCamera().setViewportSizeX(3000);
        gc.getCamera().setViewportSizeY(2000);
        // Scales down the game window if game size cannot fit to device screen.
        gc.getCamera().tieToScreenMaxBounds();
        gc.start();
        height = gc.getHeight();
        width = gc.getWidth();
    }
}
