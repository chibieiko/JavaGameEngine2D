package com.ebingine.featureGame1;

import com.ebingine.GameContainer;

/**
 * Initializes the feature game.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class FeatureGame1 {

    /**
     * Adds the game screen to the game container and sets camera size.
     */
    public FeatureGame1() {
        GameContainer gc = new GameContainer(new GameScreen());
        gc.getCamera().setViewportSizeX(3000);
        gc.getCamera().setViewportSizeY(2000);
        // Scales down the game window if game size cannot fit to device screen.
        gc.getCamera().tieToScreenMaxBounds();
        gc.start();
    }
}
