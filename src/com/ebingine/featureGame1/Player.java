package com.ebingine.featureGame1;

import com.ebingine.gameObjects.Sprite;
import com.ebingine.utils.Input;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1116
 * @since 1.7
 */
public class Player extends Sprite {

    /**
     * Constructor sets sprite's variable values.
     *
     * @param coordinateX int coordinate x
     * @param coordinateY int coordinate y
     * @param height      int height
     * @param width       int width
     */
    public Player(int coordinateX, int coordinateY, int height, int width) {
        super(coordinateX, coordinateY, height, width);
        setImg(AssetManager.player);
        setKeys();
    }

    public void setKeys() {
       // String[] keyArray = {"SPACE", "w", "a", "s", "d"};

        System.out.println("I'm a player key");
        Input.addInputKey("SPACE", 3, 0);
        Input.addInputKey("W", 0, 3);
        Input.addInputKey("A", -3, 0);
        Input.addInputKey("S", 0, 3);
        Input.addInputKey("D", 3, 0);
    }
}
