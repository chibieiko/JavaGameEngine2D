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
public class DrawingAreaComponent extends JPanel {

    private BufferedImage image;
    private GameContainer cont;

    public DrawingAreaComponent(GameContainer cont) {
        this.cont = cont;
        image = new BufferedImage(cont.getWidth(), cont.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);

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

        g.drawImage(image, 0, 0, cont.getWidth(), cont.getHeight(), null);

        updateDrawingArea();
    }

    /**
     * Updates the drawing area component.
     */
    public void updateDrawingArea() {
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
