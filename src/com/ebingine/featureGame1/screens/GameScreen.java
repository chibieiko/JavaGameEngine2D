package com.ebingine.featureGame1.screens;

import com.ebingine.GUI.Screen;
import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.Render;
import com.ebingine.featureGame1.*;
import com.ebingine.utils.Input;
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
    Img background;
    Player player;
    Player player2;

    public GameScreen() {}

    @Override
    public void create(GameContainer gc) {
        Utils utils = new Utils();
        background = new Img(AssetManager.background, 0, 0, gc.getWidth(), gc
                .getHeight());
        player = new Player(gc.getWidth()/2,
                gc.getHeight()/2, 100, 100);
        player2 = new Player(gc.getWidth()/2 - 50,
                gc.getHeight()/2 - 50, 100, 100);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        player.move(deltaTime);
        player2.moveP2(deltaTime);
    }

    @Override
    public void render(GameContainer gc, Render renderer) {
        // todo
        gc.getCamera().update((int) player.getX(), (int) player.getY());


        if (Input.keyReleased("SPACE")) {
            System.out.println(Input.keyReleased("SPACE"));
            System.out.println("space");
        }

        if (Input.keyTyped("control E")) {
            System.out.println("SAVE!");
        }

        if (Input.keyTyped("R")) {
            System.out.println("R!");
        }

    /*    if (Input.mouseDragged()) {
            System.out.println("MOUSE DRAGGED");
        }

        if (Input.mouseClicked()) {
            System.out.println("MOUSE CLICKED");
        }

        if (Input.mouseMoved()) {
            System.out.println("MOUSE MOVED");
        }*/
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
