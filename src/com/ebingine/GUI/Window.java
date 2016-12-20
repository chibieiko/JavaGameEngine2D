package com.ebingine.GUI;

import com.ebingine.GameContainer;

import javax.swing.*;
import java.awt.*;

/**
 * The window in which the game will be shown.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Window extends JFrame {

    /**
     * The panel where everything is drawn on.
     */
    private Panel panel;

    /**
     * Creates the window and the panel.
     *
     * @param gc a game container
     */
    public Window(GameContainer gc) {
        setResizable(false);
        panel = new Panel(gc);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Sets the size to match panel's preferred size.
        setContentPane(panel);
        pack();

        // Basic configurations for JFrame window.
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(gc.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Calls the panels to update itself.
     */
    public void update() {
        panel.update();
    }

    /**
     * Disposes the window.
     */
    public void clear() {
        this.dispose();
    }

    /**
     * Returns the panel.
     *
     * @return the panel
     */
    public Panel getPanel() {
        return panel;
    }
}
