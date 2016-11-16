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

    private Screen screen;
    private GameContainer cont;

    public Window(GameContainer cont) {
        setResizable(false);
        this.cont = cont;
        screen = new Screen(cont);
        setLayout(new BorderLayout());
        add(screen, BorderLayout.CENTER);

        // Sets the size to match screen's preferred size.
        setContentPane(screen);
        pack();

        System.out.println("window width: " + getSize().getWidth());
        System.out.println("window height: " + getSize().getHeight());

        // Basic configurations for JFrame window.
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(cont.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update() {
        screen.update();
    }

    public void clear() {
        this.dispose();
        screen.clear();
    }

    // Defines which screen to show in the event of multiple screens.
    public void setScreen(Screen screen) {
        this.screen = screen;
        add(screen, BorderLayout.CENTER);

        // Sets the size to match drawing area component's preferred size.
        setContentPane(screen);
        pack();
    }

    public Screen getScreen() {
        return screen;
    }
}
