package com.ebingine.utils;

import com.ebingine.GameContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1112
 * @since 1.7
 */
public class Input implements MouseListener, MouseMotionListener{

    GameContainer gameContainer;

    public Input(GameContainer gameContainer) {
        this.gameContainer = gameContainer;

        inputMap = gameContainer.getScreen().getDac().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getScreen().getDac().getActionMap();

        gameContainer.getScreen().getDac().addMouseListener(this);
        gameContainer.getScreen().getDac().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Input map for key bindings.
     */
    private InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private ActionMap actionMap;

    public static String[] keyCodes;

    /**
     * Configures which keys can be used in the game.
     */
    public void addInputKeys(String[] keyCodes) {
        this.keyCodes = keyCodes;

        for (int i = 0; i < keyCodes.length; i++) {
            if (keyCodes[i].length() == 1) {
                System.out.println("char type");
                inputMap.put(KeyStroke.getKeyStroke(keyCodes[i].charAt(0)),
                        keyCodes[i]);
            } else {
                System.out.println("string type: " + keyCodes[i]);
                inputMap.put(KeyStroke.getKeyStroke(keyCodes[i]), keyCodes[i]);
            }

            actionMap.put(keyCodes[i], new KeyAction(keyCodes[i]));
        }
    }

    public void deleteInputKey(String keyCode) {
        if (keyCode.length() > 1) {
            inputMap.put(KeyStroke.getKeyStroke(keyCode), "none");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(keyCode.charAt(0)), "none");
        }
    }
}
