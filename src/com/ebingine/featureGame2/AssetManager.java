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
     * Holds image for bullet.
     */
    public static BufferedImage rosetteWalk;

    /**
     * Loads the images from assets file.
     */
    public AssetManager() {
        try {
            rosette = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/Rosette_Stand_R"
                            + ".png"));
            rosetteWalk = ImageIO.read(new File
                    ("src/com/ebingine/featureGame2/assets/RosetteWalkAnim"
                            + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
