/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import static me.ansuz.tetris.Tetris.screen;

/**
 *
 * @author marruiad
 */
public class Tetris {

    protected enum GameState {
        /**
         * Estado cuando esta cargando el contenido del juego
         */
        INITIALIZATING,
        /**
         * Estado cuando esta preparado para empezar partida
         */
        READY,
        /**
         * Estado cuando la partida esta en progreso
         */
        PLAYING,
        /**
         * Estado de pantalla de Game Over
         */
        ENDED
    }

    public static Screen screen;
    public static Config config;
    public static Stats stats;
    public static GameState state = GameState.INITIALIZATING;
    protected static int carga = 0;

    public static void main(String[] args) {
        state = GameState.INITIALIZATING;
        config = new Config();
        stats = new Stats();
        screen = new Screen();
        Runtime.getRuntime().addShutdownHook(new ShutdownHookForTetrisGame());
    }
}

class ShutdownHookForTetrisGame extends Thread {

    @Override
    public void run() {
        System.out.println("Ended");
        screen.getUpdater().stop();
    }

}
