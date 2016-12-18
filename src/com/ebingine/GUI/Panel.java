package com.ebingine.GUI;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1112
 * @since 1.7
 */
public class Panel extends JPanel {

    private BufferedImage image;
    private GameContainer gc;
    Utils util = new Utils();

    public Panel(GameContainer gc) {
        this.gc = gc;

        /* IMPLEMENTED IN CAMERA
        // Resize game to fit screens that are smaller than the specified width
        // and height in the game container.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenMax = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

        // Height of the task bar.
        int taskbarSize = screenSize.height - screenMax.height;

        float ratio = (float) gc.getHeight() / (float) gc.getWidth();

        if (screenMax.getWidth() < gc.getWidth() ||
                screenMax.getHeight() - taskbarSize < gc.getHeight()) {
            gc.setHeight((int) (Math.floor(screenMax.getHeight()))
                    - taskbarSize);
            gc.setWidth((int) Math.floor(screenMax.getHeight() / ratio));
        }

        // TODO
        /*
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();

        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        float ratio = (float) gc.getHeight() / (float) gc.getWidth();
        if (config.getBounds().getWidth() < gc.getWidth() ||
                config.getBounds().getHeight() - taskbarSize < gc.getHeight()) {
            gc.setHeight((int) (Math.floor(config.getBounds().getHeight())) -
                    taskbarSize);
            gc.setWidth((int) Math.floor(config.getBounds().getHeight() / ratio));
        }
        */

        image = new BufferedImage(gc.getWidth(), gc.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
/*
        image = config.createCompatibleImage(gc.getWidth(), gc.getHeight
                (), Transparency.TRANSLUCENT);*/

        setBackground(Color.black);
    }

    /**
     * Sets the preferred size for the drawing area component.
     *
     * @return Dimension preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension test = new Dimension(gc.getCamera().getViewportSizeX(),
                gc.getCamera().getViewportSizeY());
        System.out.println("dac: " + test.getWidth());
        System.out.println("dac: " + test.getHeight());
        return test;
    }

    /**
     * Paints images to drawing area and updates it.
     *
     * @param g Graphics object
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
     * Updates the drawing area component.
     */
    public void update() {
        repaint();
        invalidate();
    }

    public void clear() {
        image.flush();
    }

    public BufferedImage getImage() {
        return image;
    }
}
