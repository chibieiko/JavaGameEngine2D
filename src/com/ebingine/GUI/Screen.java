package com.ebingine.GUI;

import com.ebingine.GameContainer;

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
public class Screen extends JPanel {

    private BufferedImage image;
    private GameContainer cont;

    public Screen(GameContainer cont) {
        this.cont = cont;

        // Resize window to fit screens that are smaller than specified width
        // and height.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenMax = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
        // todo reduce title height
        System.out.println("screen width: " + screenSize.getWidth());
        System.out.println("screen height: " +  screenSize.getHeight());
        float ratio = (float) cont.getHeight() / (float) cont.getWidth();
        if (screenMax.getWidth() < cont.getWidth() ||
                screenMax.getHeight() < cont.getHeight()) {
            System.out.println("ratio: " + ratio);
            cont.setHeight((int) (Math.floor(screenMax.getHeight())));
            cont.setWidth((int) Math.floor(screenMax.getHeight() / ratio));
            System.out.println("juttu: " + cont.getWidth());
        }

        // TODO
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        image = new BufferedImage(cont.getWidth(), cont.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
/*
        image = config.createCompatibleImage(cont.getWidth(), cont.getHeight
                (), Transparency.TRANSLUCENT);*/
    }

    /**
     * Sets the preferred size for the drawing area component.
     *
     * @return Dimension preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension test = new Dimension(cont.getWidth(),
                cont.getHeight());
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

        super.paintComponent(g);

        setBackground(Color.blue);

        update();
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
