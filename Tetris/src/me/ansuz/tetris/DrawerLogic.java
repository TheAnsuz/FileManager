/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author marruiad
 */
public class DrawerLogic {

    public static final float WIDTH_LEFT = 0.15f;
    public static final float WIDTH_CENTER = 0.5f;
    public static final float WIDTH_RIGHT = 0.85f;
    public static final float WIDTH_NEXT = -1f;
    public static final float WIDTH_CURRENT = -2f;
    public static final float WIDTH_PREVIOUS = -3f;

    public static final float HEIGHT_TOP = 0.15f;
    public static final float HEIGHT_CENTER = 0.5f;
    public static final float HEIGHT_BOTTOM = 0.85f;
    public static final float HEIGHT_NEXT = -1f;
    public static final float HEIGHT_NEXT_CLOSE = -2f;
    public static final float HEIGHT_CURRENT = -3f;
    public static final float HEIGHT_PREVIOUS = -4f;

    public static final int POSITION_START = -1;
    public static final int POSITION_CENTER = 0;
    public static final int POSITION_END = 1;

    private final Graphics2D g;
    private int x_pos = 0;
    private int y_pos = 0;
    private int initialX = 0;

    public DrawerLogic(Graphics2D g) {
        this.g = g;
    }

    public void setPosition(int x, int y) {
        initialX = x;
        x_pos = x;
        y_pos = y;
    }

    public void setPosition(float width_percent, float height_percent) {
        x_pos = (int) (Tetris.config.SCREEN_WIDTH * width_percent);
        y_pos = (int) (Tetris.config.SCREEN_HEIGHT * height_percent);
        initialX = x_pos;
    }

    public void draw(String args, float width_percent, float height_percent) {
        draw(Tetris.config.RENDER_COLOR, args, width_percent, height_percent);
    }

    public void draw(String args, float width_percent, float height_percent, int positionX) {
        draw(Tetris.config.RENDER_COLOR, args, width_percent, height_percent, positionX);
    }

    public void draw(String args, int x, int y) {
        draw(Tetris.config.RENDER_COLOR, args, x, y);
    }

    public void draw(Color color, String args, float width_percent, float height_percent) {
        draw(color, args, width_percent, height_percent, 0);
    }

    public void nextLine() {
        y_pos += g.getFontMetrics().getHeight();
        x_pos = initialX;
    }
    
    public void nextLine(int lines) {
        final int size = g.getFontMetrics().getHeight();
        y_pos += size*lines;
        x_pos = initialX;
    }
    
    public void nextChar() {
        x_pos += g.getFontMetrics().charWidth(' ');
    }
    
    public void nextChar(int chars) {
        final int size = g.getFontMetrics().charWidth(' ');
        x_pos += size*chars;
    }
    
    public void draw(String str) {
        draw(Tetris.config.RENDER_COLOR,str,x_pos,y_pos);
        nextChar(str.length());
    }
    
    public void draw(Color color, String str) {
        draw(color,str,x_pos,y_pos);
        nextChar(str.length());
    }
    
    public void draw(Color color, String args, float width_percent, float height_percent, int positionX) {
        final int y = (height_percent == -1f) ? y_pos + g.getFontMetrics().getHeight()
                : (height_percent == -2f) ? y_pos + g.getFontMetrics().getAscent()
                        : (height_percent == -3f) ? y_pos
                                : (height_percent == -4f) ? y_pos - g.getFontMetrics().getHeight()
                                        : (int) (Tetris.config.SCREEN_HEIGHT * height_percent);
        int x;
        if (positionX < 0) {
            x = (width_percent == -1f) ? x_pos + g.getFontMetrics().charWidth(' ')
                    : (width_percent == -2f) ? x_pos
                            : (width_percent == -3f) ? x_pos - g.getFontMetrics().charWidth(' ')
                                    : (int) (Tetris.config.SCREEN_WIDTH * width_percent);
        } else if (positionX > 0) {
            x = (width_percent == -1f) ? x_pos + g.getFontMetrics().charWidth(' ')
                    : (width_percent == -2f) ? x_pos
                            : (width_percent == -3f) ? x_pos - g.getFontMetrics().charWidth(' ')
                                    : ((int) (Tetris.config.SCREEN_WIDTH * width_percent)) - (g.getFontMetrics().stringWidth(args));
        } else {
            x = (width_percent == -1f) ? x_pos + g.getFontMetrics().charWidth(' ')
                    : (width_percent == -2f) ? x_pos
                            : (width_percent == -3f) ? x_pos - g.getFontMetrics().charWidth(' ')
                                    : ((int) (Tetris.config.SCREEN_WIDTH * width_percent)) - (g.getFontMetrics().stringWidth(args) / 2);
        }

        draw(color, args, x, y);
    }

    public void draw(Color color, String args, int x, int y) {
        g.setColor(color);
        g.drawString(args, x, y);
        x_pos = x;
        y_pos = y;
    }

}
