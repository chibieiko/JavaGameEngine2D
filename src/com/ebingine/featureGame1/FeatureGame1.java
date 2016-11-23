package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.Game;
import com.ebingine.Render;
import com.ebingine.featureGame1.screens.GameScreen;
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
    public static int height;
    public static int width;

    public FeatureGame1() {
        gameScreen = new GameScreen();
        GameContainer gc = new GameContainer(gameScreen);
        gc.start();
        height = gc.getHeight();
        width = gc.getWidth();

    }


}
