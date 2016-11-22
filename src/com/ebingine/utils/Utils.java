package com.ebingine.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1122
 * @since 1.7
 */
public class Utils {
    public Utils() {

    }

    public Font getFont(String filePath) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment
            .getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File
                    ("/featureGame1/assets/font_1_honokamin.ttf"));

            ge.registerFont(font);
            return font;

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return null;
    }
}
