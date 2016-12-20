package com.ebingine.featureGame1;

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
     * Holds texture of Player.
     */
    public static Texture player;

    /**
     * Holds texture of background.
     */
    public static Texture background;

    /**
     * Constructor loads the images from file.
     */
    public AssetManager() {
        player = new Texture
                ("src/com/ebingine/featureGame1/assets/mog.png");
        background = new Texture
                ("src/com/ebingine/featureGame1/assets/Background.png");
    }

    /**
     * Disposes assets.
     */
    public static void dispose() {
        player.dispose();
        background.dispose();
    }
}
