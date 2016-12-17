package com.ebingine.featureGame2;

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
 * @version 2016.1210
 * @since 1.7
 */
public class AssetManager {

    /**
     * Holds image for Player.
     */
    public static BufferedImage rosette;

    /**
     * Holds walking frames for player.
     */
    public static BufferedImage rosetteWalk;

    /**
     *
     */
    public static BufferedImage rosetteDead;

    /**
     *
     */
    public static BufferedImage monster;

    public static BufferedImage explosion;

    public static BufferedImage bullet;

    /**
     * Loads the images from assets file.
     */
    public AssetManager() {
        try {
            rosette = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/"
                            + "Rosette_Stand_R.png"));
            rosetteWalk = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/"
                            + "RosetteWalkAnim.png"));
            rosetteDead = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/"
                            + "RosetteDead.png"));
            monster = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/whiteWalkR"
                            + ".png"));
            explosion = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/"
                            + "explosionFull.png"));
            explosion = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/"
                            + "bullet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
