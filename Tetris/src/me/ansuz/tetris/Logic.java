/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.util.Random;
import me.ansuz.tetris.listener.InputKeyListener;

/**
 *
 * @author marruiad
 */
public class Logic {

    private static int[][] pad = new int[20][10]; // (Y,X)
    private static boolean ready = false;
    private static int lines = 0;
    private static int offsetY = 0;
    private static int offsetX = 0;
    private static int rotation = 0;
    private static char piece = ' ';
    private static int framesTranscurred = 0; // los frames que han pasado desde el ultimo movimiento de pieza
    private static int[] map = {1, 2, 5, 6}; // Mapa conteniendo las posiciones relativas de la pieza
    private static int framesPerMove = (int) (Tetris.config.FRAMES_PER_SECOND * 1.5f);
    private static int inputHit = 0;

    public static int carga(int percent, int max) {
        int n = new Random().nextInt(max);
        return (n <= percent) ? 1 : 0;
    }

    public static boolean waitStart() {
        if (InputKeyListener.KEY_START)
            ready = true;
        else if (!InputKeyListener.KEY_START && ready) {
            Tetris.state = Tetris.GameState.PLAYING;
            ready = false;
            init();
            return true;
        }
        return false;
    }

    public static int[][] calculate() {
        System.out.println(">>Piece : " + piece + " - " + offsetY + "y " + offsetX + "x " + rotation * 90 + "º");
        // Comprobar si el juego debe acabar
        if (checkGameEnded())
            end();
        // Comprobar si debe aparecer una nueva pieza
        if (InputKeyListener.KEY_DROP)
            spawnPiece();
        // Incrementar el nivel
        if (lines >= 10)
            incrementLevel();
        // Mover la pieza (sistema)
        if (framesTranscurred > framesPerMove) {
            offsetY++;
            framesTranscurred = 0;
        }
        // Mover la pieza (jugador)

        if (inputHit >= Tetris.config.INPUT_FRAMES) {
            boolean moved = playerMovement();
            if (moved)
                inputHit = 0;
        } else
            inputHit++;

        // Dibujar la pieza en la pantalla
        // convertir las coordenadas que pasa entre 0 - 15 a unas coordenadas absolutas
        int[][] falling = new int[pad.length][pad[0].length];
        for (int n : map) {
            int x = (n % 4) - 1;
            int y = (n > 11) ? 3 : (n > 7) ? 2 : (n > 3) ? 1 : 0;
            falling[offsetY + y][offsetX + x] = 1;
        }

        int[][] finalPad = new int[20][10];
        for (int y = 0; y < finalPad.length; y++)
            for (int x = 0; x < finalPad[y].length; x++)
                if (pad[y][x] > 0)
                    finalPad[y][x] = pad[y][x];
                else if (falling[y][x] > 0)
                    finalPad[y][x] = falling[y][x];

        framesTranscurred++;
        return finalPad;
    }

    public static boolean checkGameEnded() {
//        for (int x : pad[pad.length - 1]) {
//            if (x > 0)
//                return true;
//        }
        return false;
    }

    public static void incrementLevel() {
        Tetris.stats.addLevel();
        framesPerMove -= (int) (Tetris.config.FRAMES_PER_SECOND / 30f * 1.12f);
        lines = 0;
        framesTranscurred = 0;
    }

    public static void spawnPiece() {
        framesTranscurred = 0;
        offsetX = 3;
        offsetY = 0;
        rotation = 0;
        piece = Pieza.newPiece();
        map = Pieza.getPiece(piece, rotation);
        System.out.print(">> " + piece);
        for (int n : map)
            System.out.print(" - " + n);
        System.out.println();
    }

    public static void init() {
        lines = 0;
        Tetris.state = Tetris.GameState.PLAYING;
        Tetris.stats = new Stats();
        spawnPiece();
        pad = new int[20][10];
        framesPerMove = (int) (Tetris.config.FRAMES_PER_SECOND * 1.5f);
    }

    public static void end() {
        Tetris.state = Tetris.GameState.ENDED;
    }

    public static boolean playerMovement() {

        if (InputKeyListener.KEY_DOWN) {
            offsetY++;
            return true;
        }

        if (InputKeyListener.KEY_LEFT) {
            if (AdvancedLogic.canMoveLeft(map, pad, offsetX, offsetY)) {
                offsetX--;
                return true;
            }
        } else if (InputKeyListener.KEY_RIGHT) {
            if (AdvancedLogic.canMoveRight(map, pad, offsetX, offsetY)) {
                offsetX++;
                return true;
            } else
                return false;
        }
        if (InputKeyListener.KEY_ROTATE_LEFT) {
            if (AdvancedLogic.canRotateRight(map, pad, offsetX, offsetY)) {
                rotation = (rotation > 0) ? rotation - 1 : 3;
                map = Pieza.getPiece(piece, rotation);
                return true;
            } else
                return false;
        } else if (InputKeyListener.KEY_ROTATE_RIGHT) {
            if (AdvancedLogic.canRotateLeft(map, pad, offsetX, offsetY)) {
                rotation = (rotation < 3) ? rotation + 1 : 0;
                map = Pieza.getPiece(piece, rotation);
                return true;
            } else
                return false;
        }

        return false;
    }

}
