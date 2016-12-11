package com.ebingine.tiled;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1206
 * @since 1.7
 */
public class Tileset {

    private int firstGid;
    private int lastGid;
    private String name;
    private int tileWidth;
    private int tileHeight;
    private String source;
    private int imageWidth;
    private int imageHeight;
    private BufferedImage sourceImage;
    private ArrayList<BufferedImage> tileImages = new ArrayList<>();

    public Tileset(int firstGid, String name, int tileWidth, int tileHeight,
                   String source, int imageWidth, int imageHeight) {
        this.firstGid = firstGid;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.source = source;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        lastGid = (int) (Math.floor(imageWidth/tileWidth) * Math.floor
                (imageHeight/tileHeight) + firstGid - 1);
        loadImages();
    }

    private void loadImages() {
        try {
            // todo osote tulee käyttäjältä
            File file = new File("src/com/ebingine/featureGame2/assets/"
                    + source);
            FileInputStream fis = new FileInputStream(file);
            sourceImage = ImageIO.read(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sourceImage != null) {
            int rows = (int) Math.floor(sourceImage.getHeight() / tileHeight);
            int cols = (int) Math.floor(sourceImage.getWidth() / tileWidth);
            // Determines the image chunk's width and height.
            int chunkWidth = sourceImage.getWidth() / cols;
            int chunkHeight = sourceImage.getHeight() / rows;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    tileImages.add(sourceImage.getSubimage(chunkWidth * j,
                            chunkHeight * i, chunkWidth, chunkHeight));
                   /* tileImages.add(new BufferedImage(chunkWidth, chunkHeight,
                            sourceImage.getType())); */
                }
            }
        }
    }

    public ArrayList<BufferedImage> getTileImages() {
        return tileImages;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public int getFirstGid() {
        return firstGid;
    }

    public int getLastGid() {
        return lastGid;
    }

    public String getName() {
        return name;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public String getSource() {
        return source;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}
