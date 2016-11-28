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
public class Window extends JFrame {

    private Panel panel;
    private GameContainer gc;

    public Window(GameContainer gc) {
        setResizable(false);
        this.gc = gc;
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

    public void update() {
        panel.update();
    }

    public void clear() {
        this.dispose();
        panel.clear();
    }

    /*
    // Defines which panel to show in the event of multiple screens.
    public void setScreen(Panel panel) {
        this.panel = panel;
        add(panel, BorderLayout.CENTER);

        // Sets the size to match drawing area component's preferred size.
        setContentPane(panel);
        pack();
    }
*/

    public Panel getPanel() {
        return panel;
    }
}
