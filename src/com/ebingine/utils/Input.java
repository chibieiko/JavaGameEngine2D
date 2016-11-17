package com.ebingine.utils;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1112
 * @since 1.7
 */
public class Input implements ActionListener, MouseListener,
        MouseMotionListener {

    GameContainer gameContainer;
    private final static String PRESSED = "pressed ";
    private final static String RELEASED = "released ";

    /**
     * Input map for key bindings.
     */
    private static InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private static ActionMap actionMap;

    public static Timer timer;
    public static Map<String, Point> pressedKeys = new HashMap<>();

    public static String[] keyCodes;

    public Input(GameContainer gameContainer, int delay) {
        this.gameContainer = gameContainer;

        inputMap = gameContainer.getWindow().getScreen().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getWindow().getScreen().getActionMap();

        gameContainer.getWindow().getScreen().addMouseListener(this);
        gameContainer.getWindow().getScreen().addMouseMotionListener(this);

        timer = new Timer(delay, this);
        timer.setInitialDelay( 0 );
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
     * Configures which keys can be used in the game.
     */
    public static void addInputKey(String keyCode, int deltaX, int deltaY) {

        //  Separates the key identifier from the modifiers of the KeyStroke.
        int offset = keyCode.lastIndexOf(" ");
        String key = offset == -1 ? keyCode :  keyCode.substring( offset + 1 );
        String modifiers = keyCode.replace(key, "");

        //  Creates Action and add binding for the pressed key.
        Action pressedAction = new KeyAction(key, new Point(deltaX, deltaY));
        String pressedKey = modifiers + PRESSED + key;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
        inputMap.put(pressedKeyStroke, pressedKey);
        actionMap.put(pressedKey, pressedAction);

        //  Creates Action and adds binding for the released key.
        Action releasedAction = new KeyAction(key, null);
        String releasedKey = modifiers + RELEASED + key;
        KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
        inputMap.put(releasedKeyStroke, releasedKey);
        actionMap.put(releasedKey, releasedAction);

        /*
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

            actionMap.put(keyCodes[i], new KeyAction(keyCodes[i], i, i +1));
        }*/
    }

    public void deleteInputKey(String keyCode) {
        if (keyCode.length() > 1) {
            inputMap.put(KeyStroke.getKeyStroke(keyCode), "none");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(keyCode.charAt(0)), "none");
        }
    }

    //  Invoked whenever a key is pressed or released.
    private static void handleKeyEvent(String key, Point moveDelta) {
        //  Keeps track of which keys are pressed.
        if (moveDelta == null) {
            Input.pressedKeys.remove(key);
        } else {
            Input.pressedKeys.put(key, moveDelta);
        }

        //  Starts the Timer when the first key is pressed.
        if (Input.pressedKeys.size() == 1) {
            Input.timer.start();
        }

        //  Stops the Timer when all keys have been released.
        if (Input.pressedKeys.size() == 0) {
            Input.timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Call some method
    }

    /**
     * TODO Short Description
     * <p>
     * TODO description and @since
     *
     * @author Erika Sankari
     * @version 2016.1114
     * @since 1.7
     */
    public static class KeyAction extends AbstractAction implements
            ActionListener {

        private Point moveDelta;
        private String key;

        /**
         * Constructor sets key value.
         *
         * @param key String key value
         */
        public KeyAction(String key, Point moveDelta) {
            super(key);
            this.key = key;
            this.moveDelta = moveDelta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(NAME));
            handleKeyEvent((String)getValue(NAME), moveDelta);
        }
    }
}
