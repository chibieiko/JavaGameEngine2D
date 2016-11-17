package com.ebingine.featureGame1;

import javax.imageio.ImageIO;
import java.awt.*;
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
    public static Image player;

    /**
     * Holds image for monster.
     */
    public static Image monster;

    /**
     * Holds image for background.
     */
    public static Image background;

    /**
     * Holds image for bullet.
     */
    public static Image bullet;

    /**
     * Constructor loads the images from assets file.
     */
    public AssetManager() {
        try {
            player = ImageIO.read(new File
                    ("src/com/ebingine/featureGame1/assets/mog.png"));
            monster = ImageIO.read(new File
                    ("src/com/ebingine/featureGame1/assets/enemy.png"));
            background = ImageIO.read(new File
                    ("src/com/ebingine/featureGame1/assets/background.png"));
            System.out.println(background);
            bullet = ImageIO.read(new File
                    ("src/com/ebingine/featureGame1/assets/bullet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
