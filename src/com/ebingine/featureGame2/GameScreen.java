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
 * @version 2016.1204
 * @since 1.7
 */
public class GameScreen extends Game {

    private Player player;
    private TiledMap tiled;
    private GameScreen2 gameScreen2;

    public GameScreen(TiledMap tiled) {
        this.tiled = tiled;
    }

    @Override
    public void create(GameContainer gc) {
        Object object
                = loadSave("src/com/ebingine/featureGame2/assets/player");
        if (object != null) {
            player = new Player((int) ((Player) object).getX(),
                    (int) ((Player) object).getY(),
                    ((Player) object).getWidth(),
                    ((Player) object).getHeight());
        } else {
            player = new Player(tiled.getTileWidth(),
                    tiled.getPixelHeight() - tiled.getTileHeight() * 3,
                    AssetManager.rosette.getWidth(null),
                    AssetManager.rosette.getHeight(null));
        }

        player.setTiled(tiled);
        new Trees(tiled);
        gameScreen2 = new GameScreen2(tiled);
    }

    @Override
    public void update(GameContainer gc, double deltaTime) {
        player.move(deltaTime);
        player.jump();

        if (GameContainer.input.keyTyped("G")) {
            String[] array = loadInfo
                    ("src/com/ebingine/featureGame2/assets/saves/text");
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }

        if (GameContainer.input.keyTyped("T")) {
            System.out.println("SavingText");
            String[] values = {"playerX:" + Float.toString(player.getX()),
                    "playerY:" + Float.toString(player.getY())};
            saveInfo(values, "src/com/ebingine/featureGame2/assets/text");
        }

        if (GameContainer.input.keyTyped("control S")) {
            System.out.println("Saving");
            saveGame("src/com/ebingine/featureGame2/assets/player",
                    player);
        }

        if (player.collidesWith(tiled.getObject("door").getRectangle())) {
            gameScreen2.create(gc);
            gc.setGame(gameScreen2);
        }
    }

    @Override
    public void clear(GameContainer gc) {

    }
}
