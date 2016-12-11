package com.ebingine.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
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
                    (filePath));

            ge.registerFont(font);
            return font;

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper method splits image to chunks.
    public BufferedImage[] splitImage(BufferedImage image, int cols, int rows) {
        // Determines the image chunk's width and height.
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        BufferedImage[] images = new BufferedImage[cols * rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                images[j + i] = image.getSubimage(chunkWidth * j,
                        chunkHeight * i, chunkWidth, chunkHeight);
            }
        }

        return images;
    }
}
