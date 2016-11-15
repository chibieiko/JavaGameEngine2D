/*package com.ebingine.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1114
 * @since 1.7
 */
/*public class KeyAction extends AbstractAction implements
        ActionListener {

    private Point moveDelta;

    /**
     * Constructor sets key value.
     *
     * @param key String key value
     */
/*    public KeyAction(String key, Point moveDelta) {
        super(key);

        this.moveDelta = moveDelta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleKeyEvent((String)getValue(NAME), moveDelta);
    }

    private void handleKeyEvent(String key, Point moveDelta)
    {
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
}
*/