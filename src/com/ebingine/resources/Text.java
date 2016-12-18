package com.ebingine.resources;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;

import java.awt.*;
import java.io.Serializable;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1213
 * @since 1.7
 */
public class Text implements Drawable, Serializable {

    private Font font;
    private float size;
    private int style;
    private String text;
    private int x;
    private int y;

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

    public void setTTFFont(String pathToTTFfile) {
        font = GameContainer.utils.createFont((pathToTTFfile));
        setProperties(style, size);
    }

    public void setFont(String fontName) {
        font = new Font(fontName, style, (int) size);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setProperties(int fontStyle, float fontSize) {
        style = fontStyle;
        size = fontSize;
        font = font.deriveFont(fontStyle, fontSize);
    }

    public void setSize(float fontSize) {
        size = fontSize;
        font = font.deriveFont(fontSize);
    }

    public void setStyle(int fontStyle) {
        style = fontStyle;
        font = font.deriveFont(fontStyle);
    }

    public void setColor(Color color, GameContainer gc) {
        gc.getWindow().getPanel().setForeground(color);
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }
}
