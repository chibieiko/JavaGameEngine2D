package com.ebingine.utils;

import java.awt.*;

/**
 * Indicates which objects are drawable.
 * <p>
 * Enforces draw method.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public interface Drawable {

    /**
     * Draws all the game elements.
     *
     * @param g2d a graphics object for drawing
     */
    void draw(Graphics2D g2d);
}
