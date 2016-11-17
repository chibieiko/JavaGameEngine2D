package com.ebingine.featureGame1.screens;

import com.ebingine.GUI.Screen;
import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.Render;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1117
 * @since 1.7
 */
public class OtherScreen extends Game {
    @Override
    public void create(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        System.out.println("in other screen update");
    }

    @Override
    public void render(GameContainer gc, Render renderer) {
        System.out.println("in other screen render");
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
