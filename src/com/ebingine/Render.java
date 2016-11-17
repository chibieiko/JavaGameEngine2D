package com.ebingine;

import java.awt.image.DataBufferByte;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1030
 * @since 1.7
 */
public class Render {

    private int width;
    private int height;
    private byte[] pixels;

    public Render(GameContainer cont) {
        width = cont.getWidth();
        height = cont.getHeight();
        pixels = ((DataBufferByte)cont.getWindow().getScreen().getImage()
                .getRaster().getDataBuffer()).getData();
    }



    /* Clears screen.
    public void clearScreen() {



        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int index = (i + j * width) * 4;
                pixels[index] = (byte) 255; // visible or not
                pixels[index + 1] = (byte) 0; // blue
                pixels[index + 2] = (byte) 255; // green
                pixels[index + 3] = (byte) 0; // red
            }
        }

    }
    */

}
