package com.ebingine.featureGame2;

import com.ebingine.Screen;
import com.ebingine.GameContainer;
import com.ebingine.resources.Text;
import com.ebingine.tiled.TiledMap;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.7
 */
public class GameScreen extends Screen {

    private Player player;
    public TiledMap tiled;
    private Text text;

    public GameScreen(TiledMap tiled) {
        this.tiled = tiled;
    }

    public GameScreen() {
        tiled = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap2.tmx",
                        "src/com/ebingine/featureGame2/assets/");
    }

    @Override
    public void create(GameContainer gc) {
        Object object = null;
        boolean exists = true;
        try {
            object = loadSave("src/com/ebingine/featureGame2/assets/player");
        } catch (FileNotFoundException e) {
            exists = false;
            System.out.println("save not found");
        }

        text = new Text("ようこそこのゲームへ",
                gc.getWidth() / 2 - tiled.getTileWidth() * 4,
                gc.getHeight() / 3);
        text.setColor(Color.WHITE, gc);
        text.setTTFFont("src/com/ebingine/featureGame2/assets"
                + "/font_1_honokamin.ttf");

        if (object != null && exists) {
            player = new Player((int) ((Player) object).getX(),
                    (int) ((Player) object).getY(),
                    ((Player) object).getWidth(),
                    ((Player) object).getHeight());
        } else {
            player = new Player(tiled.getTileWidth(),
                    tiled.getMapHeight() * tiled.getTileHeight()
                            - tiled.getTileHeight() * 3,
                    AssetManager.rosette.getWidth(),
                    AssetManager.rosette.getHeight());
        }

        player.setTiled(tiled);
        new Trees(tiled);

        AssetManager.backgroundMusic.playIndefinitely();
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
            gc.clearRender();
            GameScreen2 gameScreen2 = new GameScreen2();
            gameScreen2.create(gc);
            gc.setScreen(gameScreen2);
        }
    }

    @Override
    public void clear(GameContainer gc) {
        AssetManager.dispose();
    }
}
