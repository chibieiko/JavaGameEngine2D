package com.ebingine.featureGame2;

import com.ebingine.resources.Audio;
import com.ebingine.resources.Texture;

/**
 * Loads resources, so that they are ready when they are needed.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class AssetManager {

    /**
     * Holds texture of the player.
     */
    public static Texture rosette;

    /**
     * Holds walking frames of player.
     */
    public static Texture rosetteWalk;

    /**
     * Holds texture of a dead player.
     */
    public static Texture rosetteDead;

    /**
     * Holds texture of the monster.
     */
    public static Texture monster;

    /**
     * Holds texture of an explosion.
     */
    public static Texture explosion;

    /**
     * Holds texture of a bullet.
     */
    public static Texture bullet;

    /**
     * Holds the audio of background music.
     */
    public static Audio backgroundMusic;

    /**
     * Loads the resources from file.
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

    /**
     * Disposes of all the resources.
     */
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
