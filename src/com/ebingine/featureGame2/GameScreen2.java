package com.ebingine.featureGame2;

import com.ebingine.Screen;
import com.ebingine.GameContainer;
import com.ebingine.resources.Text;
import com.ebingine.tiled.TiledMap;

import java.awt.*;

/**
 * Holds logic and the game loop for game screen 2.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class GameScreen2 extends Screen {

    /**
     * The player of the game.
     */
    private Player player;

    /**
     * The game's monster.
     */
    private Monster monster;

    /**
     * A tiled map for this screen.
     */
    private TiledMap tiled;

    /**
     * Indicates whether this screen is on(true) or not(false).
     */
    private boolean screenOn;

    /**
     * Initializes the game screen with a tiled map.
     *
     * @param tiled a tiled map
     */
    public GameScreen2(TiledMap tiled) {
        this.tiled = tiled;
        screenOn = true;
    }

    /**
     * Initializes the game screen and creates a tiled map.
     */
    public GameScreen2() {
        tiled = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap3.tmx",
                        "src/com/ebingine/featureGame2/assets/");
        screenOn = false;
    }

    /**
     * Creates all necessary components for the game screen.
     *
     * @param gc a game container
     */
    @Override
    public void create(GameContainer gc) {
        Text text = new Text("気おつけて帰ってください",
                gc.getWidth()/2 - tiled.getTileWidth() * 4,
                gc.getHeight()/3);
        text.setColor(Color.RED, gc);
        text.setTTFFont("src/com/ebingine/featureGame2/assets" +
                "/font_1_honokamin.ttf");

        player = new Player(tiled.getObject("door-closed").getX(),
                    tiled.getObject("door-closed").getY(),
                    AssetManager.rosette.getWidth(),
                    AssetManager.rosette.getHeight());
        player.setTiled(tiled);

        monster = new Monster(tiled.getObject("monster").getX(),
                tiled.getObject("monster").getY(),
                AssetManager.monster.getWidth() / 4,
                AssetManager.monster.getHeight());
        monster.setTiled(tiled);
        screenOn = true;
    }

    /**
     * Updates the game state.
     *
     * @param gc a game container
     * @param deltaTime a fixed time step
     */
    @Override
    public void update(GameContainer gc, double deltaTime) {
        if (screenOn) {
            monster.move(deltaTime);
            player.move(deltaTime);
            player.jump();

            if (GameContainer.input.mouseClicked()) {
                player.shoot();
            }

            if (player.collidesWith(monster.getRectangle())) {
                player.setAlive(false);
                monster.setAlive(true);
            } else {
                player.setAlive(true);
            }

            player.updateBullet(deltaTime, monster);

            if (player.collidesWith(tiled.getObject("door").getRectangle())) {
                gc.clearRender();
                GameScreen gs = new GameScreen();
                gs.create(gc);
                gc.setScreen(gs);
            }
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
}
