package com.ebingine.featureGame1;

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
    public static BufferedImage player;

    /**
     * Holds image for monster.
     */
    public static BufferedImage monster;

    /**
     * Holds image for Img.
     */
    public static BufferedImage background;

    /**
     * Holds image for bullet.
     */
    public static BufferedImage bullet;

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
                    ("src/com/ebingine/featureGame1/assets/Background.png"));
            bullet = ImageIO.read(new File
                    ("src/com/ebingine/featureGame1/assets/bullet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
