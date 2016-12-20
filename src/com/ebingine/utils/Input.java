package com.ebingine.utils;

import com.ebingine.GameContainer;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles input.
 * <p>
 * Enables the use of mouse and keyboard events. Supports multiple keys
 * pressed at the same time.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Input implements ActionListener, MouseListener,
        MouseMotionListener {

    /**
     * Indicates that a key is a pressed key.
     */
    private final String PRESSED = "pressed ";

    /**
     * Indicates that a key is a released key.
     */
    private final String RELEASED = "released ";

    /**
     * Input map for key bindings.
     */
    private InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private ActionMap actionMap;

    /**
     * Holds all the keys that are in the input map and have been pressed.
     */
    private Map<String, Boolean> pressedKeys = new HashMap<>();

    /**
     * Holds all the keys that are in the input map and have been typed.
     */
    private ArrayList<String> typedKeys = new ArrayList<>();

    /**
     * Indicates whether a mouse has been clicked.
     */
    private boolean mouseClicked;

    /**
     * Indicates whether a mouse has been pressed.
     */
    private boolean mousePressed;

    /**
     * Indicates whether a mouse has been released.
     */
    private boolean mouseReleased;

    /**
     * Indicates whether a mouse has entered an area.
     */
    private boolean mouseEntered;

    /**
     * Indicates whether a mouse has exited an area.
     */
    private boolean mouseExited;

    /**
     * Indicates whether a mouse is being dragged.
     */
    private boolean mouseDragged;

    /**
     * Indicates whether a mouse is being moved.
     */
    private boolean mouseMoved;

    /**
     * Initiates input and action map and mouse events.
     *
     * @param gameContainer a game container
     */
    public Input(GameContainer gameContainer) {
        inputMap = gameContainer.getWindow().getPanel().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getWindow().getPanel().getActionMap();

        gameContainer.getWindow().getPanel().addMouseListener(this);
        gameContainer.getWindow().getPanel().addMouseMotionListener(this);
        mouseClicked = false;
        mousePressed = false;
        mouseReleased = false;
        mouseEntered = false;
        mouseExited = false;
        mouseDragged = false;
        mouseMoved = false;
    }

    /**
     * Returns whether a mouse has been clicked.
     *
     * @return true if clicked, false otherwise
     */
    public boolean mouseClicked() {
        boolean temp = mouseClicked;
        mouseClicked = false;
        return temp;
    }

    /**
     * Changes mouse clicked state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
    }

    /**
     * Returns whether a mouse button has been pressed.
     *
     * @return true if pressed, false otherwise
     */
    public boolean mousePressed() {
        boolean temp = mousePressed;
        mousePressed = false;
        return temp;
    }

    /**
     * Changes mouse pressed state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    /**
     * Returns whether a mouse button has been released.
     *
     * @return true if released, false otherwise
     */
    public boolean mouseReleased() {
        boolean temp = mouseReleased;
        mouseReleased = false;
        return temp;
    }

    /**
     * Changes mouse released state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
    }

    /**
     * Returns whether a mouse has entered an area.
     *
     * @return true if entered, false otherwise
     */
    public boolean mouseEntered() {
        boolean temp = mouseEntered;
        mouseEntered = false;
        return temp;
    }

    /**
     * Changes mouse entered state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        mouseEntered = true;
    }

    /**
     * Returns whether a mouse has exited an area.
     *
     * @return true if exited, false otherwise
     */
    public boolean mouseExited() {
        boolean temp = mouseExited;
        mouseExited = false;
        return temp;
    }

    /**
     * Changes mouse exited state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
        mouseExited = true;
    }

    /**
     * Returns whether a mouse is being dragged.
     *
     * @return true if dragged, false otherwise
     */
    public boolean mouseDragged() {
        boolean temp = mouseDragged;
        mouseDragged = false;
        return temp;
    }

    /**
     * Changes mouse dragged state to true.
     *
     * @param e a mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseDragged = true;
    }

    /**
     * Returns whether a mouse is being moved.
     *
     * @return true if moved, false otherwise
     */
    public boolean mouseMoved() {
        boolean temp = mouseMoved;
        mouseMoved = false;
        return temp;
    }

    /**
     * Changes mouse moved state to true.
     *
     * @param e a mouse event
     */
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

            //  Separates the key identifier from the modifiers of the KeyStroke.
            int offset = keyCode.lastIndexOf(" ");
            String key = offset == -1 ? keyCode : keyCode.substring(offset + 1);
            String modifiers = keyCode.replace(key, "");

            //  Creates Action and adds binding for the pressed key.
            Action pressedAction = new KeyAction(key, true);
            String pressedKey = modifiers + PRESSED + key;
            KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
            inputMap.put(pressedKeyStroke, pressedKey);
            actionMap.put(pressedKey, pressedAction);

            //  Creates Action and adds binding for the released key.
            Action releasedAction = new KeyAction(key, false);
            String releasedKey = modifiers + RELEASED + key;
            KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
            inputMap.put(releasedKeyStroke, releasedKey);
            actionMap.put(releasedKey, releasedAction);
        }
    }

    /**
     * Deletes an input key form the inputMap.
     *
     * @param keyCode the key to delete
     */
    public void deleteInputKey(String keyCode) {
        inputMap.put(KeyStroke.getKeyStroke(keyCode), "none");
    }

    /**
     * Handles key events.
     * <p>
     * Invoked whenever a key is pressed or released.
     * Saves pressed keys to an array along with their state (boolean
     * pressed, released). Also maintains an array of typed keys.
     *
     * @param key a key that was just released or pressed
     * @param pressed true if pressed, false if released
     */
    private void handleKeyEvent(String key, boolean pressed) {
        boolean keyFound = false;
        if (pressedKeys.size() > 0) {
            for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                if (entry.getKey().equals(key)) {
                    keyFound = true;
                    entry.setValue(pressed);
                }
            }

            if (!keyFound) {
                pressedKeys.put(key, pressed);
            }

        } else {
            pressedKeys.put(key, pressed);
        }

        // In case typed key has been released, then removes it
        // from the typedKeys array.
        boolean remove = false;
        for (String typedKey : typedKeys) {
            if (key.equals(typedKey) && !pressed) {
                remove = true;
            }
        }

        if (remove) {
            typedKeys.remove(key);
        }
    }

    /**
     * Indicates whether the key in question was just typed.
     *
     * @param key name of the key
     * @return true if key was just typed, false otherwise
     */
    public boolean keyTyped(String key) {
        boolean typed = false;
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

        return typed;
    }

    /**
     * Indicates whether the key in question is being pressed.
     *
     * @param key name of the key
     * @return true if key is being pressed, false otherwise
     */
    public boolean keyPressed(String key) {
        boolean isPressed = false;
        for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
            if (key.equals(entry.getKey()) && entry.getValue())
                isPressed = true;
        }

        return isPressed;
    }

    /**
     * Indicates whether the key in question was just released.
     *
     * @param key the name of the key
     * @return true if key was just released, false otherwise
     */
    public boolean keyReleased(String key) {
        boolean isReleased = false;
        for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
            if (key.equals(entry.getKey()) && !entry.getValue()) {
                isReleased = true;
            }
        }

        if (isReleased) {
            pressedKeys.remove(key);
        }

        return isReleased;
    }

    /**
     * Listens for action events.
     *
     * @param e an action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * Input's inner class that handles key actions.
     *
     * @author Erika Sankari
     * @version 2016.1220
     * @since 1.7
     */
    public class KeyAction extends AbstractAction implements
            ActionListener {

        /**
         * Indicates whether the key is pressed or just released.
         */
        private boolean pressed;

        /**
         * The name of the key.
         */
        private String name;

        /**
         * Constructor sets key value and key's state.
         *
         * @param key String key value
         * @param pressed true if pressed, false if released
         */
        public KeyAction(String key, boolean pressed) {
            super(key);
            this.pressed = pressed;
        }

        /**
         * Receives performed actions and passes them to key event handler.
         * <p>
         * Supports control and shift modifiers.
         *
         * @param e an action event
         */
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
