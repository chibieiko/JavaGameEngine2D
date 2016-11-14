package com.ebingine.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1114
 * @since 1.7
 */
public class KeyAction extends AbstractAction {

    /**
     * Contains the name for the key.
     */
    private String key;

    /**
     * Constructor sets key value.
     *
     * @param key String key value
     */
    public KeyAction(String key) {
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (key.equalsIgnoreCase("g")) {
            System.out.println("You just pressed G");
        }

        if (key.equalsIgnoreCase("w")) {
            System.out.println("You just pressed w");
        }

        if (key.equalsIgnoreCase("h")) {
            System.out.println("You just pressed h");
        }

        if (key.equalsIgnoreCase("pressed space")) {
            System.out.println("SPACE pressed");
        }

        if (key.equalsIgnoreCase("released space")) {
            System.out.println("SPACE released");
        }
    }
}
