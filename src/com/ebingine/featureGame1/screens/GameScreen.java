package com.ebingine.featureGame1.screens;

import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.Render;
import com.ebingine.featureGame1.*;

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

    public GameScreen() {
    }

    @Override
    public void create(GameContainer gc) {
        Utils utils = new Utils();
        background = new Img(AssetManager.background, 0, 0, gc.getWidth(), gc
                .getHeight());
        player = new Player(gc.getWidth() / 2,
                gc.getHeight() / 2, 100, 100);
        gc.getCamera().update((int)player.getX(), (int)player.getY());
        player2 = new Player(gc.getWidth() / 2 + player.getWidth(),
                gc.getHeight() / 2 + player.getHeight(), 100, 100);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        if (player.collidesWith(player2.getEllipse())) {
            System.out.println("COLLISION");
            player.setCanMove(false);
        } else {
            player.setCanMove(true);
        }

        if (player.canMove()) {
            player.move(deltaTime);
            gc.getCamera().update((int)player.getX(), (int)player.getY());
        }

        if (player2.canMove())
            player2.moveP2(deltaTime);

    }

    @Override
    public void render(GameContainer gc, Render renderer) {
        // todo
        //  gc.getCamera().update((int) player.getX(), (int) player.getY());

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
