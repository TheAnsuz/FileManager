/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.util.Random;

/**
 *
 * @author marruiad
 */
public class Stats {

    private long score;
    private int level;
    private int completedLines;
    private final Random rnd;
    private final long seed;

    protected Stats() {
        seed = new Random().nextLong();
        score = 0;
        level = 1;
        completedLines = 0;
        rnd = new Random(seed);
    }

    public long getScore() {
        return score;
    }

    protected void completeLines(int lines) {
        completedLines += lines;
        // la puntuacion son 100 por linea mas 25^linea por cada linea completada consecutiva
        score += 100 * lines * 1.25f;
    }
    
    protected int getCompletedLines() {
        return completedLines;
    }

    public int getLevel() {
        return level;
    }

    protected void addLevel() {
        level++;
    }

    public int getRNG() {
        return rnd.nextInt(700);
    }

}
