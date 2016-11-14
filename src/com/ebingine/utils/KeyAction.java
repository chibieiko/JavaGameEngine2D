package com.ebingine.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

    private boolean[] keys = new boolean[Input.keyCodes.length];
    private boolean[] keysLast = new boolean[Input.keyCodes.length];

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

        System.out.println(e.toString());
        System.out.println(e.getActionCommand());

        if (key.equalsIgnoreCase("a")) {
            System.out.println("You just pressed a");
        }

        if (key.equalsIgnoreCase("w")) {
            System.out.println("You just pressed w");
        }

        if (key.equalsIgnoreCase("s")) {
            System.out.println("You just pressed s");
        }

        if (key.equalsIgnoreCase("d")) {
            System.out.println("You just pressed d");
        }

        if (key.equalsIgnoreCase("space")) {
            System.out.println("SPACE pressed");
        }

        if (key.equalsIgnoreCase("released space")) {
            System.out.println("SPACE released");
        }
    }
}
