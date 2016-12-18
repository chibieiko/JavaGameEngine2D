package com.ebingine.featureGame2;

import com.ebingine.resources.Audio;
import com.ebingine.resources.Texture;

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
    public static Texture rosette;

    /**
     * Holds walking frames for player.
     */
    public static Texture rosetteWalk;

    /**
     *
     */
    public static Texture rosetteDead;

    /**
     *
     */
    public static Texture monster;

    public static Texture explosion;

    public static Texture bullet;

    public static Audio backgroundMusic;

    /**
     * Loads the images from assets file.
     */
    public AssetManager() {
        rosette = new Texture("src/com/ebingine/featureGame2/assets/"
                + "Rosette_Stand_R.png");
        rosetteWalk = new Texture("src/com/ebingine/featureGame2/assets/"
                + "RosetteWalkAnim.png");
        rosetteDead = new Texture("src/com/ebingine/featureGame2/assets/"
                + "RosetteDead.png");
        monster = new Texture("src/com/ebingine/featureGame2/assets/whiteWalkR"
                + ".png");
        explosion = new Texture("src/com/ebingine/featureGame2/assets/"
                + "explosionFull.png");
        bullet = new Texture("src/com/ebingine/featureGame2/assets/"
                + "bullet.png");
        backgroundMusic = new Audio(
                "src/com/ebingine/featureGame2/assets/Pinecones.wav");
    }

    public static void dispose() {
        rosette.dispose();
        rosetteWalk.dispose();
        rosetteDead.dispose();
        monster.dispose();
        explosion.dispose();
        bullet.dispose();
        backgroundMusic.dispose();
    }
}
