/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

/**
 *
 * @author marruiad
 */
public class Updater implements Runnable {
    
    private Screen screen;
    private boolean run;
    private Thread thread;
    
    protected Updater(Screen screen) {
        this.screen = screen;
        run = false;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        while (Tetris.carga < 20) {
            int n = Logic.carga(5, 500 + (Tetris.carga*50));
            Tetris.carga += n;
            Drawer.draw(screen.getGraphics(),new int[20][10]);
            screen.updateGraphics();
        }
        Tetris.state = Tetris.GameState.READY;
        while (run) {
            // Update logic
            int[][] pad = new int[20][10];
            
            if (Tetris.state == Tetris.GameState.READY)
                Logic.waitStart();
            else
                pad = Logic.calculate();
            // Update graphics
            Drawer.draw(screen.getGraphics(),pad);
            screen.updateGraphics();
            // Frames per second approach cuz lazy
            try {
                final long sleepTime = 1000 / Tetris.config.FRAMES_PER_SECOND;
//                System.out.println(sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    protected synchronized void start() {
        if (run)
            return;
        run = true;
        thread.start();
    }
    
    protected synchronized boolean stop() {
        if (!run)
            return false;
        try {
            run = false;
            thread.join();
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            run = true;
            return false;
        }
    }
    
}
