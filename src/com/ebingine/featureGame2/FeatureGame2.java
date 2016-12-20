package com.ebingine.featureGame2;

import com.ebingine.GameContainer;

/**
 * Initializes the feature game 2.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class FeatureGame2 {

    /**
     * Creates the game screen and adds it to the game container.
     *
     * Sets the available keys for the game.
     */
    public FeatureGame2() {
        GameScreen gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.setWidth(gameScreen.tiled.getPixelWidth());
        gc.setHeight(gameScreen.tiled.getPixelHeight());
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
