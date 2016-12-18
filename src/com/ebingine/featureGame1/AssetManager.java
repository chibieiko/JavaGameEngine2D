package com.ebingine.featureGame1;

import com.ebingine.utils.Texture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1115
 * @since 1.7
 */
public class AssetManager {

    /**
     * Holds image for Player.
     */
    public static Texture player;

    /**
     * Holds image for monster.
     */
    public static Texture monster;

    /**
     * Holds image for background.
     */
    public static Texture background;

    /**
     * Holds image for bullet.
     */
    public static Texture bullet;

    /**
     * Constructor loads the images from assets file.
     */
    public AssetManager() {
        player = new Texture
                ("src/com/ebingine/featureGame1/assets/mog.png");
        monster = new Texture
                ("src/com/ebingine/featureGame1/assets/enemy.png");
        background = new Texture
                ("src/com/ebingine/featureGame1/assets/Background.png");
        bullet = new Texture
                ("src/com/ebingine/featureGame1/assets/bullet.png");
    }

    public static void dispose() {
        player.dispose();
        monster.dispose();
        background.dispose();
        bullet.dispose();
    }
}
