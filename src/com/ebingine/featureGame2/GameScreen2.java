package com.ebingine.featureGame2;

import com.ebingine.Screen;
import com.ebingine.GameContainer;
import com.ebingine.resources.Text;
import com.ebingine.tiled.TiledMap;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1211
 * @since 1.7
 */
public class GameScreen2 extends Screen {
    private Player player;
    private Monster monster;
    private TiledMap tiled;
    private Text text;
    private boolean screenOn;

    public GameScreen2(TiledMap tiled) {
        this.tiled = tiled;
    }

    public GameScreen2() {
        tiled = new TiledMap
                ("src/com/ebingine/featureGame2/assets/testMap3.tmx",
                        "src/com/ebingine/featureGame2/assets/");
        screenOn = false;
    }

    @Override
    public void create(GameContainer gc) {
        text = new Text("気おつけて帰ってください",
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

    @Override
    public void clear(GameContainer gc) {
        AssetManager.dispose();
    }
}
