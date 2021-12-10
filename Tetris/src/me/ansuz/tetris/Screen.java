/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import me.ansuz.tetris.listener.InputKeyListener;
import me.ansuz.tetris.listener.ScreenSizeChangeListener;

/**
 *
 * @author Adrian Martin Ruiz del Valle
 */
public class Screen {

    private JFrame frame;
    private Canvas canvas;
    private Updater updater;
    private BufferStrategy bst;

    /**
     * Crea la pantalla dadas las dimensiones
     */
    protected Screen() {
        frame = new JFrame();
        canvas = new Canvas();
        updater = new Updater(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final Dimension dim = new Dimension(Tetris.config.SCREEN_WIDTH, Tetris.config.SCREEN_HEIGHT);
        frame.setTitle(Tetris.config.SCREEN_TITLE);
        frame.addComponentListener(new ScreenSizeChangeListener());
        canvas.addKeyListener(new InputKeyListener());
        canvas.requestFocus();
        frame.setSize(dim);
        frame.setMinimumSize(new Dimension(Tetris.config.SCREEN_MIN_WIDTH, Tetris.config.SCREEN_MIN_HEIGHT));
        frame.setMaximumSize(dim);
        frame.setPreferredSize(dim);
        frame.setResizable(true);
        canvas.setBackground(Tetris.config.RENDER_BACKGROUND);
        frame.setLocationRelativeTo(null);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        updater.start();
    }

    protected Updater getUpdater() {
        return updater;
    }

    protected Graphics2D getGraphics() {
        bst = canvas.getBufferStrategy();
        if (bst == null) {
            canvas.createBufferStrategy(Tetris.config.SCREEN_BUFFER);
            bst = canvas.getBufferStrategy();
        }
        bst.getDrawGraphics().clearRect(0, 0, Tetris.config.SCREEN_WIDTH, Tetris.config.SCREEN_HEIGHT);
        return (Graphics2D) bst.getDrawGraphics();
    }

    protected void updateGraphics() {
        bst.show();
        bst.dispose();
    }

}
