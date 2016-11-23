package com.ebingine.utils;

import com.ebingine.GameContainer;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.gameObjects.Sprite;

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
    private static final String PRESSED = "pressed ";
    private static final String RELEASED = "released ";
    static String key1 = "";
    static boolean pressed1 = false;
    static boolean keyDown = false;

    /**
     * Input map for key bindings.
     */
    private static InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private static ActionMap actionMap;

    public static Timer timer;
    public static Map<String, Boolean> pressedKeys = new HashMap<>();
    public static ArrayList<String> typedKeys = new ArrayList<>();

    public String[] keyCodes;

    public Input(GameContainer gameContainer, int delay) {
        this.gameContainer = gameContainer;

        inputMap = gameContainer.getWindow().getScreen().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getWindow().getScreen().getActionMap();

        gameContainer.getWindow().getScreen().addMouseListener(this);
        gameContainer.getWindow().getScreen().addMouseMotionListener(this);

        timer = new Timer(delay, this);
        timer.setInitialDelay(0);
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
    public static void addInputKey(String[] keyCodes) {
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

    // todo fix
    public void deleteInputKey(String keyCode) {
        if (keyCode.length() > 1) {
            inputMap.put(KeyStroke.getKeyStroke(keyCode), "none");
        } else {
            inputMap.put(KeyStroke.getKeyStroke(keyCode.charAt(0)), "none");
        }
    }

    // Invoked whenever a key is pressed or released.
    private static void handleKeyEvent(String key, boolean pressed) {
        key1 = key;
        pressed1 = pressed;

        //  Keeps track of which keys are pressed.
        // if (!pressed) {
        //     pressedKeys.remove(key);
        // } else {

        synchronized (pressedKeys) {
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

                synchronized (typedKeys) {
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

    }

    public static boolean keyTyped(String key) {
        boolean typed = false;

        if (keyDown) {
            boolean alreadyTyped = false;

            synchronized (typedKeys) {
                for (String typedKey : typedKeys) {
                    //  System.out.println("typed Keys:");
                    //  System.out.print(typedKey + " \n");
                    if (key.equals(typedKey)) {
                        alreadyTyped = true;
                    }
                }
            }

            synchronized (pressedKeys) {
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
        }

        return typed;
    }

    public static boolean keyPressed(String key) {
        boolean isPressed = false;

        if (keyDown) {
            synchronized (pressedKeys) {
                for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                    if (key.equals(entry.getKey()) && entry.getValue())
                        isPressed = true;
                }
            }
        }

        return isPressed;
    }

    public static boolean keyReleased(String key) {
        boolean isReleased = false;
   //     System.out.println("RELEASE");

        if (keyDown) {
            synchronized (pressedKeys) {
                for (Map.Entry<String, Boolean> entry : pressedKeys.entrySet()) {
                    if (key.equals(entry.getKey()) && !pressed1) {
                        isReleased = true;
                    }
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
    public static class KeyAction extends AbstractAction implements
            ActionListener {

        private boolean pressed;
        private String key;

        /**
         * Constructor sets key value.
         *
         * @param key String key value
         */
        public KeyAction(String key, boolean pressed) {
            super(key);
            this.key = key;
            this.pressed = pressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleKeyEvent((String) getValue(NAME), pressed);
        }
    }
}
