package com.ebingine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
abstract public class Screen extends JFrame {

    private JPanel panel;
    private BufferedImage image;
    private Graphics g;
    private BufferStrategy bufferStrategy;

    public Screen(Container container) {

    }

    public void update() {

    }

    public void clear() {

    }
}
