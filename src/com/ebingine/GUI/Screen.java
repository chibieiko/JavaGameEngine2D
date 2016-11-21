package com.ebingine.GUI;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

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
    private GameContainer gc;

    public Screen(GameContainer gc) {
        this.gc = gc;

        // Resize window to fit screens that are smaller than specified width
        // and height.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Rectangle screenMax = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
        // todo reduce title height
        System.out.println("screen width: " + screenSize.getWidth());
        System.out.println("screen height: " + screenSize.getHeight());
        float ratio = (float) gc.getHeight() / (float) gc.getWidth();
        if (screenMax.getWidth() < gc.getWidth() ||
                screenMax.getHeight() < gc.getHeight()) {
            System.out.println("ratio: " + ratio);
            gc.setHeight((int) (Math.floor(screenMax.getHeight())));
            gc.setWidth((int) Math.floor(screenMax.getHeight() / ratio));
            System.out.println("juttu: " + gc.getWidth());
        }

        // TODO
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

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

        // Calls the render method of game.
        gc.getGame().render(gc, gc.getRenderer());

        super.paintComponent(g);

        for (Drawable obj : GameContainer.drawables) {
            g.drawImage(obj.getImg(), (int) obj.getX(),(int) obj.getY(), obj
                    .getWidth(),
                    obj.getHeight(), null);
        }

        update();
        GameContainer.drawables.clear();
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
