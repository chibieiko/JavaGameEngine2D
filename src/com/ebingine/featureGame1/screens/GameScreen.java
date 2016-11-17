package com.ebingine.featureGame1.screens;

import com.ebingine.GUI.Screen;
import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.Render;
import com.ebingine.featureGame1.AssetManager;
import com.ebingine.featureGame1.FeatureGame1;
import com.ebingine.featureGame1.Player;
import com.sun.deploy.association.AssociationException;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1116
 * @since 1.7
 */
public class GameScreen extends Game {

    Player player;

    public GameScreen() {

    }

    @Override
    public void create(GameContainer gc) {
        player = new Player(gc.getWidth()/2,
                gc.getHeight()/2, 50, 50);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
       // System.out.println("Game screen update");
    }

    @Override
    public void render(GameContainer gc, Render renderer) {
        gc.drawImg(AssetManager.background, 0, 0, gc.getWidth(), gc.getHeight());
        gc.drawGameObject(player);

        //System.out.println("Game screen render");
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
