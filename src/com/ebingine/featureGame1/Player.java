package com.ebingine.featureGame1;

import com.ebingine.Game;
import com.ebingine.GameContainer;
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
        setSpeedY(50);
        setSpeedX(50);
    }

  //  @Override
    public void move(double delta) {
        if (Input.keyPressed("W")) {
            System.out.println("UP");
            setY(getY() - (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("S")) {
            setY(getY() + (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("A")) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (Input.keyPressed("D")) {
            System.out.println("LEFT");
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }
}
