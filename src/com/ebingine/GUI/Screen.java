package com.ebingine.GUI;

import com.ebingine.GameContainer;

import javax.swing.*;
import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
public class Screen extends JFrame {

    private DrawingAreaComponent dac;
    private GameContainer cont;

    public Screen(GameContainer cont) {
        setResizable(false);
        this.cont = cont;
        dac = new DrawingAreaComponent(cont);
        setLayout(new BorderLayout());
        add(dac, BorderLayout.CENTER);

        // Sets the size to match drawing area component's preferred size.
        setContentPane(dac);
        pack();

        System.out.println("window width: " + getSize().getWidth());
        System.out.println("window height: " + getSize().getHeight());

        // Basic configurations for JFrame.
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(cont.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update() {
        dac.updateDrawingArea();
    }

    public void clear() {
        this.dispose();
        dac.clear();
    }

    public DrawingAreaComponent getDac() {
        return dac;
    }
}
