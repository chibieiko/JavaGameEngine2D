package com.ebingine.utils;

import com.ebingine.resources.Texture;

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

    public Font createFont(String filePath) {
        Font readyFont = null;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment
            .getLocalGraphicsEnvironment();
            readyFont = Font.createFont(Font.TRUETYPE_FONT, new File
                    (filePath));
            ge.registerFont(readyFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return readyFont;
    }

    // Helper method splits image to chunks.
    public Texture[] splitImage(BufferedImage image, int cols, int rows) {
        // Determines the image chunk's width and height.
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        Texture[] images = new Texture[cols * rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                images[j + i] = new Texture(image.getSubimage(chunkWidth * j,
                        chunkHeight * i, chunkWidth, chunkHeight));
            }
        }

        return images;
    }
}
