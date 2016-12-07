package com.ebingine.tiled;

import com.ebingine.utils.Drawable;

import java.awt.*;
import java.util.HashMap;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1206
 * @since 1.7
 */
public class Layer implements Drawable {

    String name;
    int width;
    int height;
    HashMap<Integer, Integer> data = new HashMap<>();

    public Layer(String name, int width, int height, HashMap<Integer,
            Integer> data) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // todo implement draw
    }
}
