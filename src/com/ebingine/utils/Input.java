package com.ebingine.utils;

import com.ebingine.GameContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

    private GameContainer gameContainer;
    private final String PRESSED = "pressed ";
    private final String RELEASED = "released ";
    boolean pressed1 = false;
    // Makes sure key events are only queried when at least one key is down.
    boolean keyDown = false;

    /**
     * Input map for key bindings.
     */
    private InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private ActionMap actionMap;

    public Timer timer;
    public Map<String, Boolean> pressedKeys = new HashMap<>();
    public ArrayList<String> typedKeys = new ArrayList<>();

    private boolean mouseClicked = false;
    private boolean mousePressed = false;
    private boolean mouseReleased = false;
    private boolean mouseEntered = false;
    private boolean mouseExited = false;
    private boolean mouseDragged = false;
    private boolean mouseMoved = false;

    public Input(GameContainer gameContainer, int delay) {
        this.gameContainer = gameContainer;

        inputMap = gameContainer.getWindow().getPanel().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getWindow().getPanel().getActionMap();

        gameContainer.getWindow().getPanel().addMouseListener(this);
        gameContainer.getWindow().getPanel().addMouseMotionListener(this);

        timer = new Timer(delay, this);
        timer.setInitialDelay(0);
    }

    public boolean mouseClicked() {
        boolean temp = mouseClicked;
        mouseClicked = false;
        return temp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
    }

    public boolean mousePressed() {
        boolean temp = mousePressed;
        mousePressed = false;
        return temp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public boolean mouseReleased() {
        boolean temp = mouseReleased;
        mouseReleased = false;
        return temp;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
    }

    public boolean mouseEntered() {
        boolean temp = mouseEntered;
        mouseEntered = false;
        return temp;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEntered = true;
    }

    public boolean mouseExited() {
        boolean temp = mouseExited;
        mouseExited = false;
        return temp;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseExited = true;
    }

    public boolean mouseDragged() {
        boolean temp = mouseDragged;
        mouseDragged = false;
        return temp;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseDragged = true;
    }

    public boolean mouseMoved() {
        boolean temp = mouseMoved;
        mouseMoved = false;
        return temp;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMoved = true;
    }

    /**
     * Configures which keys can be used in the game.
     */
    public void addInputKey(String[] keyCodes) {
        String keyCode;

        for (int i = 0; i < keyCodes.length; i++) {
            keyCode = keyCodes[i];
            System.out.println(keyCode);

            //  Separates the key identifier from the modifiers of the KeyStroke.
            int offset = keyCode.lastIndexOf(" ");
            String key = offset == -1 ? keyCode : keyCode.substring(offset + 1);
            String modifiers = keyCode.replace(key, "");
            System.out.println("modifiers: " + modifiers);

            //  Creates Action and adds binding for the pressed key.
            Action pressedAction = new KeyAction(key, true);
            String pressedKey = modifiers + PRESSED + key;
            System.out.println("pressedKey: " + pressedKey);
            KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
            inputMap.put(pressedKeyStroke, pressedKey);
            actionMap.put(pressedKey, pressedAction);

            //  Creates Action and adds binding for the released key.
            Action releasedAction = new KeyAction(key, false);
            String releasedKey = modifiers + RELEASED + key;
            System.out.println("releasedKey: " + releasedKey);
            KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
            inputMap.put(releasedKeyStroke, releasedKey);
            actionMap.put(releasedKey, releasedAction);
        }
    }

    // todo fix
    public void deleteInputKey(String keyCode) {
        inputMap.put(KeyStroke.getKeyStroke(keyCode), "none");
    }

    // Invoked whenever a key is pressed or released.
    private void handleKeyEvent(String key, boolean pressed) {
        //  Keeps track of which keys are pressed.
        // if (!pressed) {
        //     pressedKeys.remove(key);
        // } else {


        pressedKeys.put(key, pressed);
        // }

        //  Starts the Timer when the first key is pressed.
        if (pressedKeys.size() == 1) {
            timer.start();
        }

        boolean allReleased = true;
        for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
            if (entry.getValue()) {
                allReleased = false;
            }


            // In case typed key has been released, then removes it
            // from the typedKeys array.
            boolean remove = false;
            for (String typedKey : typedKeys) {
                if (key.equals(typedKey) && !pressed) {
                    remove = true;
                }
            }

            if (remove)
                typedKeys.remove(key);
        }

        if (allReleased) {
            keyDown = false;
            pressedKeys.clear();
            timer.stop();
        }
    }

    //  Stops the Timer when all keys have been released.
    // if (pressedKeys.size() == 0) {
    //     timer.stop();
    //  }

       /* for (Map.Entry<String,Boolean> entry : pressedKeys.entrySet()) {
            String first = entry.getKey();
            boolean value = entry.getValue();
        } */


    public boolean keyTyped(String key) {
        boolean typed = false;
        if (keyDown) {
            boolean alreadyTyped = false;
            for (String typedKey : typedKeys) {
                if (key.equals(typedKey)) {
                    alreadyTyped = true;
                }
            }

            for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                if (key.equals(entry.getKey()) && entry.getValue() &&
                        !alreadyTyped) {
                    typed = true;
                    synchronized (typedKeys) {
                        typedKeys.add(entry.getKey());
                    }
                }
            }
        }

        return typed;
    }

    public boolean keyPressed(String key) {
        boolean isPressed = false;
        if (keyDown) {
            for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                if (key.equals(entry.getKey()) && entry.getValue())
                    isPressed = true;
            }
        }

        return isPressed;
    }

    public boolean keyReleased(String key) {
        boolean isReleased = false;
        if (keyDown) {
            for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                if (key.equals(entry.getKey()) && !pressed1) {
                    isReleased = true;
                }
            }
        }

        return isReleased;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyDown = true;
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
    public class KeyAction extends AbstractAction implements
            ActionListener {

        private boolean pressed;
        private String name;

        /**
         * Constructor sets key value.
         *
         * @param key String key value
         */
        public KeyAction(String key, boolean pressed) {
            super(key);
            this.pressed = pressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            name = (String) getValue(NAME);
            if (e.getModifiers() == InputEvent.CTRL_MASK) {
                name = "control " + name;
            }

            if (e.getModifiers() == InputEvent.SHIFT_MASK) {
                name = "shift " + name;
            }

            handleKeyEvent(name, pressed);
        }
    }
}
