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
    }

    @Override
    public void move() {

    }

    public void setKeys() {
       // String[] keyArray = {"SPACE", "w", "a", "s", "d"};

        Input.addInputKey("SPACE");
        Input.addInputKey("W");
        Input.addInputKey("A");
        Input.addInputKey("S");
        Input.addInputKey("D");
    }
}
