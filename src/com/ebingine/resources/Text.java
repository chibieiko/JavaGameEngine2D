package com.ebingine.resources;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

import java.awt.*;
import java.io.Serializable;

/**
 * Text object enables easy creation of text into a game.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Text implements Drawable, Serializable {

    /**
     * The font style which will be used for the text.
     */
    private Font font;

    /**
     * The size of the text.
     */
    private float size;

    /**
     * The style of the text.
     */
    private int style;

    /**
     * The message to write.
     */
    private String text;

    /**
     * Position x.
     */
    private int x;

    /**
     * Position y.
     */
    private int y;

    /**
     * Creates the text with default font TimesRoman.
     *
     * @param text the text to be drawn
     * @param x the position x
     * @param y the position y
     */
    public Text(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        size = 30;
        style = Font.PLAIN;
        font = new Font("TimesRoman", style, (int) size);
        synchronized (GameContainer.drawables) {
            GameContainer.drawables.add(this);
        }
    }

    /**
     * Sets a new true type font as the text's font.
     *
     * @param pathToTTFfile the path where the .ttf file can be found
     */
    public void setTTFFont(String pathToTTFfile) {
        font = GameContainer.utils.createFont((pathToTTFfile));
        setProperties(style, size);
    }

    /**
     * Sets a new basic font as the text's font.
     *
     * @param fontName the name of the font
     */
    public void setFont(String fontName) {
        font = new Font(fontName, style, (int) size);
    }

    /**
     * Sets a new true type font as the text's font.
     *
     * @param font the new true type font
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * Sets a new style and size for the text.
     *
     * @param fontStyle the style of the text
     * @param fontSize the size of the text
     */
    public void setProperties(int fontStyle, float fontSize) {
        style = fontStyle;
        size = fontSize;
        font = font.deriveFont(fontStyle, fontSize);
    }

    /**
     * Sets a new size for the text.
     *
     * @param fontSize the new size
     */
    public void setSize(float fontSize) {
        size = fontSize;
        font = font.deriveFont(fontSize);
    }

    /**
     * Sets a new style for the text.
     *
     * @param fontStyle the new style
     */
    public void setStyle(int fontStyle) {
        style = fontStyle;
        font = font.deriveFont(fontStyle);
    }

    /**
     * Sets a new color for the text.
     *
     * @param color the new color
     * @param gc game container object
     */
    public void setColor(Color color, GameContainer gc) {
        gc.getWindow().getPanel().setForeground(color);
    }

    /**
     * Draws the text.
     *
     * @param g2d a graphics object for drawing
     */
    public void draw(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }
}
