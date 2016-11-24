package com.ebingine.featureGame1;

import com.ebingine.Game;
import com.ebingine.GameContainer;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.utils.Input;

import java.awt.*;

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
    public Player(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setImg(AssetManager.player);
        setSpeedY(100);
        setSpeedX(100);
    }

  //  @Override
    public void move(double delta) {
        if (Input.keyPressed("W")) {
            setY(getY() - (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("S")) {
            setY(getY() + (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("A")) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (Input.keyPressed("D")) {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }

    public void moveP2(double delta) {
        if (Input.keyPressed("UP")) {
            setY(getY() - (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("DOWN")) {
            setY(getY() + (getSpeedY() * (float) delta));
        }

        if (Input.keyPressed("LEFT")) {
            setX(getX() - (getSpeedX() * (float) delta));
        }

        if (Input.keyPressed("RIGHT")) {
            setX(getX() + (getSpeedX() * (float) delta));
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getImg(), (int) getX(), (int) getY(), getWidth(),
                getHeight(), null);
    }
}
