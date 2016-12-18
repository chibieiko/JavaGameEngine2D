package com.ebingine.featureGame1;

import com.ebingine.GameContainer;

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

    public FeatureGame1() {
        gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.getCamera().setViewportSizeX(800);
        gc.getCamera().setViewportSizeY(600);
        // Scales down the game window if game size cannot fit to device screen.
        gc.getCamera().tieToScreenMaxBounds();
        gc.start();
    }
}
