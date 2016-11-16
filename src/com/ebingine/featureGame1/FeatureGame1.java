package com.ebingine.featureGame1;

import com.ebingine.GameContainer;
import com.ebingine.Game;
import com.ebingine.Render;
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
public class FeatureGame1 extends Game {

    public FeatureGame1() {

        GameContainer gc = new GameContainer(this);
        gc.start();
        gc.run();

        Player player = new Player(gc.getHeight()/2, gc.getWidth()/2, 50, 50);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
       // System.out.println("In game update");
    }

    @Override
    public void render(GameContainer gc, Render renderer) {
      //  System.out.println("in game render");
    }
}
