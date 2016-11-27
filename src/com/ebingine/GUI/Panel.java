package com.ebingine.GUI;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Utils;

import javax.swing.*;
import java.awt.*;
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

    public void fitToMaxSize() {
        // Resize game to fit screens that are smaller than specified width
        // and height.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenMax = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets
                (getGraphicsConfiguration());
        // Height of the task bar.
        int taskbarSize = scnMax.bottom;
        // todo reduce title height
        System.out.println("screen width: " + screenSize.getWidth());
        System.out.println("screen height: " + screenSize.getHeight());
        float ratio = (float) gc.getHeight() / (float) gc.getWidth();
        if (screenMax.getWidth() < gc.getWidth() ||
                screenMax.getHeight() - taskbarSize < gc.getHeight()) {
            System.out.println("ratio: " + ratio);
            gc.setHeight((int) (Math.floor(screenMax.getHeight()))
                    - taskbarSize);
            gc.setWidth((int) Math.floor(screenMax.getHeight() / ratio));
            System.out.println("juttu: " + gc.getWidth());
        }
    }

    /**
     * Sets the preferred size for the drawing area component.
     *
     * @return Dimension preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension test = new Dimension(gc.getWidth(),
                gc.getHeight());
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
        //    g2d.translate(-gc.getCamera().getCamX(), -gc.getCamera().getCamY());
        //  g2d.setFont(util.getFont
        //        ("src/com/ebingine/featureGame1/assets/font_1_honokamin
        // .ttf"));

        // Smoothes the borders of drawables.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints
                .VALUE_ANTIALIAS_ON);

        for (Drawable d : GameContainer.drawables) {
            d.draw(g2d);
         //   g2d.drawString("こんにちは", gc.getWidth()/2, gc.getHeight()/2);
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
