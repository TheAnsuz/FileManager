/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris.listener;

import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import me.ansuz.tetris.Tetris;

/**
 *
 * @author marruiad
 */
public class ScreenSizeChangeListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        Tetris.config.SCREEN_WIDTH = e.getComponent().getWidth();
        Tetris.config.SCREEN_HEIGHT = e.getComponent().getHeight();
        Tetris.config.RENDER_FONT_SIZE = (int) (Tetris.config.SCREEN_HEIGHT / 32*0.8);
        Tetris.config.RENDER_FONT = new Font(Font.MONOSPACED,Font.PLAIN,Tetris.config.RENDER_FONT_SIZE);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }

  
    
}
