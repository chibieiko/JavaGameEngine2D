package com.ebingine.featureGame1.screens;

import com.ebingine.Game;
import com.ebingine.GameContainer;
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
    GameContainer gc;

    public GameScreen() {
    }

    @Override
    public void create(GameContainer gc) {
        this.gc = gc;
        Utils utils = new Utils();
        background = new Img(AssetManager.background, 0, 0, gc.getWidth(), gc
                .getHeight());
        player = new Player(gc.getWidth() / 2,
                gc.getHeight() / 2, 100, 100);
        updateCamera((int) player.getX(), (int) player.getY
                ());
        player2 = new Player(gc.getWidth() / 2 + player.getWidth(),
                gc.getHeight() / 2 + player.getHeight(), 100, 100);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        if (player.collidesWith(player2.getEllipse())) {
            player.setCanMove(false);
        } else {
            player.setCanMove(true);
        }

        if (player.canMove()) {
            player.move(deltaTime);
            updateCamera((int) player.getX() + player.getWidth() / 2
                            - gc.getCamera().getViewportSizeX() / 2,
                    (int) player.getY() + player.getHeight() / 2
                            - gc.getCamera().getViewportSizeY() / 2);
        }

        if (player2.canMove())
            player2.moveP2(deltaTime);

    }

    @Override
    public void clear(GameContainer gc) {

    }

    public void updateCamera(int x, int y) {
        gc.getCamera().setPosition(x, y);

        // DOWN
        if (gc.getCamera().getY() > gc.getHeight()
                - gc.getCamera().getViewportSizeY()) {
            gc.getCamera().setY(gc.getHeight()
                    - gc.getCamera().getViewportSizeY());
        }

        // RIGHT
        if (gc.getCamera().getX() > gc.getWidth()
                - gc.getCamera().getViewportSizeX()) {
            gc.getCamera().setX(gc.getWidth()
                    - gc.getCamera().getViewportSizeX());
        }

        // LEFT
        if (gc.getCamera().getX() < 0) {
            gc.getCamera().setX(0);
        }

        // UP
        if (gc.getCamera().getY() < 0) {
            gc.getCamera().setY(0);
        }
    }
}
