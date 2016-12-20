package com.ebingine.GUI;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.utils.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Panel extends JPanel {

    /**
     * Game container provides access to game engine components.
     */
    private GameContainer gc;

    /**
     * Creates the panel with black background.
     *
     * @param gc a game container
     */
    public Panel(GameContainer gc) {
        this.gc = gc;
        setBackground(Color.black);
    }

    /**
     * Returns the preferred size of the panel.
     *
     * @return the preferred size
     */
    @Override
    public Dimension getPreferredSize() {
         return new Dimension(gc.getCamera().getViewportSizeX(),
                gc.getCamera().getViewportSizeY());
    }

    /**
     * Paints images to panel and updates it.
     *
     * @param g a graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        // Clears the screen.
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Updates panel view to match camera view.
        g2d.translate(-gc.getCamera().getX(), -gc.getCamera().getY());
        // Smoothes the borders of drawables.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        synchronized (GameContainer.drawables) {
            for (Drawable d : GameContainer.drawables) {
                if (d instanceof GameObject) {
                    GameObject o = (GameObject) d;
                    int x = (int) o.getX();
                    int y = (int) o.getY();
                    int width = o.getWidth();
                    int height = o.getHeight();
                    Rectangle2D.Float cam = gc.getCamera().getRectangle();

                    // Draws only those game objects that are on camera. They
                    // are on camera if any of their corners are inside the
                    // camera rectangle.
                    if (cam.contains(x, y) || cam.contains(x + width, y) ||
                            cam.contains(x, y + height) ||
                            cam.contains(x + width, y + height)) {
                        d.draw(g2d);
                    }
                } else {
                    d.draw(g2d);
                }
            }
        }
    }

    /**
     * Updates the panel.
     */
    public void update() {
        repaint();
        invalidate();
    }
}
