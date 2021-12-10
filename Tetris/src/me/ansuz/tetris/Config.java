/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author marruiad
 */
public class Config {
    
    public String SCREEN_TITLE = "T E T R I S";
    public int SCREEN_WIDTH = 680;
    public int SCREEN_HEIGHT = 480;
    public int SCREEN_BUFFER = 2;
    public int FRAMES_PER_SECOND = 30;
    public boolean RENDER_ANTIALISING = false;
    public int RENDER_FONT_SIZE = 12;
    public Font RENDER_FONT = new Font(Font.MONOSPACED,Font.PLAIN,RENDER_FONT_SIZE);
    public Color RENDER_COLOR = new Color(180,255,180);
    public Color RENDER_COLOR_HIGHLIGHT = new Color(150,150,255);
    public Color RENDER_BACKGROUND = new Color(5,5,5);
    protected int SCREEN_MIN_WIDTH = 480;
    protected int SCREEN_MIN_HEIGHT = 360;
    protected int INPUT_FRAMES = 4;
    
    protected Config() {
        
    }
    
}
