package com.ebingine.featureGame1;

import com.ebingine.Screen;
import com.ebingine.GameContainer;

/**
 * Holds logic and the game loop for game screen.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class GameScreen extends Screen {

    /**
     * The player of the game and who the camera follows.
     */
    private Player player;

    /**
     * Player 2 of the game.
     */
    private Player player2;

    /**
     * Game container.
     */
    private GameContainer gc;

    /**
     * Creates all necessary components for the game screen.
     *
     * @param gc a game container
     */
    @Override
    public void create(GameContainer gc) {
        this.gc = gc;
        addKeys();

        AssetManager.background.setSize(gc.getWidth(), gc.getHeight());
        AssetManager.background.addToRender();

        player = new Player(gc.getWidth() / 2,
                gc.getHeight() / 2, 100, 100);
        updateCamera((int) player.getX(), (int) player.getY());

        player2 = new Player(gc.getWidth() / 2 + player.getWidth(),
                gc.getHeight() / 2 + player.getHeight(), 100, 100);
    }

    /**
     * Updates the game state.
     *
     * If player 2 catches player, player cannot move as long as it is
     * colliding with player 2.
     *
     * @param gc a game container
     * @param deltaTime a fixed time step
     */
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

        if (player2.canMove()) {
            player2.moveP2(deltaTime);
        }
    }

    /**
     * Disposes assets.
     *
     * @param gc a game container
     */
    @Override
    public void clear(GameContainer gc) {
        AssetManager.dispose();
    }

    /**
     * Updates camera's location.
     *
     * Camera moves up, down, left and right if there is enough game world to
     * show.
     *
     * @param x new position x
     * @param y new position y
     */
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

    /**
     * Adds the keys that will be reacted to.
     */
    public void addKeys() {
        String[] keyArray = {"SPACE", "W", "A", "S", "D", "RIGHT", "LEFT",
                "UP", "DOWN", "control E", "R"};
        GameContainer.input.addInputKey(keyArray);
    }
}
