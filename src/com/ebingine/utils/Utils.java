package com.ebingine.utils;

import com.ebingine.resources.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Provides helper methods.
 * <p>
 * Helper methods can be used by the game engine but also by the game maker.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Utils {

    /**
     * Creates and returns a Font.
     *
     * @param filePath the path to a ttf file
     * @return the created font
     */
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

    /**
     * Splits an image to chunks.
     *
     * @param image an image to split into chunks
     * @param cols determines to how many columns should the image be split
     * @param rows determines to how many rows should the image be split
     * @return the image chunks
     */
    public Texture[] splitImage(BufferedImage image, int cols, int rows) {
        // Determines the image chunk's width and height.
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        Texture[] images = new Texture[cols * rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                images[cols * i + j] = new Texture(image.getSubimage(chunkWidth
                                * j,
                        chunkHeight * i, chunkWidth, chunkHeight));
            }
        }

        return images;
    }
}
