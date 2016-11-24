package com.ebingine.featureGame1;

import com.ebingine.utils.Input;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1120
 * @since 1.7
 */
public class Utils {

    public Utils() {
        String[] keyArray = {"SPACE", "W", "A", "S", "D", "RIGHT", "LEFT",
                "UP", "DOWN", "control E", "R"};
        Input.addInputKey(keyArray);
    }

}
