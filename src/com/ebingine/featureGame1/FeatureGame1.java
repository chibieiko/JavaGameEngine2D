package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.Game;
import com.ebingine.Render;
import com.ebingine.featureGame1.screens.GameScreen;
import com.ebingine.utils.Camera;
import com.ebingine.utils.Input;

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
    Camera camera;
    public int height;
    public int width;

    public FeatureGame1() {
        gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.getCamera().setViewportSizeX(800);
        gc.getCamera().setViewportSizeY(600);
        gc.start();
        height = gc.getHeight();
        width = gc.getWidth();
    }
}
