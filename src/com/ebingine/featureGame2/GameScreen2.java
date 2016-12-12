package com.ebingine.featureGame2;

import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.tiled.TiledMap;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1211
 * @since 1.7
 */
public class GameScreen2 extends Game {
    private Player player;
    private TiledMap tiled;

    public GameScreen2(TiledMap tiled) {
        this.tiled = tiled;
    }

    public GameScreen2() {
        tiled = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap3.tmx",
                        "src/com/ebingine/featureGame2/assets/");
    }

    @Override
    public void create(GameContainer gc) {
        player = new Player(tiled.getObject("door-closed").getX(),
                    tiled.getObject("door-closed").getY(),
                    AssetManager.rosette.getWidth(null),
                    AssetManager.rosette.getHeight(null));
        player.setTiled(tiled);
        new Trees(tiled);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        player.move(deltaTime);
        player.jump();

        if (GameContainer.input.mouseClicked()) {
            System.out.println("SHOOOOT!");
        }

        if (player.collidesWith(tiled.getObject("door").getRectangle())) {
            gc.clearRender();
            GameScreen gs = new GameScreen();
            gs.create(gc);
            gc.setGame(gs);
        }
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
