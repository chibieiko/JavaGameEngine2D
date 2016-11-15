package com.ebingine.utils;

import com.ebingine.GameContainer;

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
    private InputMap inputMap;

    /**
     * Action map for key bindings.
     */
    private ActionMap actionMap;

    public static Timer timer;
    public static Map<String, Point> pressedKeys = new HashMap<String, Point>();

    public static String[] keyCodes;

    public Input(GameContainer gameContainer, int delay) {
        this.gameContainer = gameContainer;

        inputMap = gameContainer.getScreen().getDac().
                getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameContainer.getScreen().getDac().getActionMap();

        gameContainer.getScreen().getDac().addMouseListener(this);
        gameContainer.getScreen().getDac().addMouseMotionListener(this);

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
    public void addInputKey(String keyCode, int deltaX, int deltaY) {


        //  Separate the key identifier from the modifiers of the KeyStroke

        int offset = keyCode.lastIndexOf(" ");
        String key = offset == -1 ? keyCode :  keyCode.substring( offset + 1 );
        String modifiers = keyCode.replace(key, "");

        //  Create Action and add binding for the pressed key

        Action pressedAction = new KeyAction(key, new Point(deltaX, deltaY));
        String pressedKey = modifiers + PRESSED + key;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
        inputMap.put(pressedKeyStroke, pressedKey);
        actionMap.put(pressedKey, pressedAction);

        //  Create Action and add binding for the released key

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

    private void handleKeyEvent(String key, Point moveDelta) {
        //  Keep track of which keys are pressed

        if (moveDelta == null)
            Input.pressedKeys.remove( key );
        else
            Input.pressedKeys.put(key, moveDelta);

        //  Start the Timer when the first key is pressed

        if (Input.pressedKeys.size() == 1)
        {
            Input.timer.start();
        }

        //  Stop the Timer when all keys have been released

        if (Input.pressedKeys.size() == 0)
        {
            Input.timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
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

        private Point moveDelta;

        /**
         * Constructor sets key value.
         *
         * @param key String key value
         */
        public KeyAction(String key, Point moveDelta) {
            super(key);

            this.moveDelta = moveDelta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleKeyEvent((String)getValue(NAME), moveDelta);
        }
    }
}
