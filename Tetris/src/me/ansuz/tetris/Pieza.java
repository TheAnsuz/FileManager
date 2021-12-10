/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

/**
 *
 * @author marruiad
 */
public class Pieza {

    /**
     * [ 0][ 1][ 2][ 3] 
     * [ 4][ 5][ 6][ 7] 
     * [ 8][ 9][10][11] 
     * [12][13][14][15]
     * Rotacion: 0(up) 1(right) 2(down) 3(left)
     */
    private static final int[][] T = {{2, 5, 6, 7},  {1, 5, 6, 9},   {5, 6, 7, 10},  {2, 5, 6, 10} };
    private static final int[][] L = {{1, 5, 9, 10}, {5, 6, 7, 9},   {1, 2, 6, 10},  {7, 9, 10, 11}};
    private static final int[][] J = {{2, 6, 9, 10}, {5, 9, 10, 11}, {1, 2, 5, 9},   {5, 6, 7, 11} };
    private static final int[][] S = {{2, 5, 6, 9},  {5, 6, 10, 11}, {2, 5, 6, 9},   {5, 6, 10, 11}};
    private static final int[][] Z = {{1, 5, 6, 10}, {5, 6, 10, 11}, {1, 5, 6, 10},  {5, 6, 10, 11}};
    private static final int[][] O = {{1, 2, 5, 6},  {1, 2, 5, 6},   {1, 2, 5, 6},   {1, 2, 5, 6}  };
    private static final int[][] I = {{1, 5, 9, 13}, {4, 5, 6, 7},   {2, 6, 10, 14}, {8, 9, 10, 11}};

    /*      T       L       J     I      Z       S     O      
              []    []      []    []       []    []     [][]
            [][][]  []      []    []     [][]    [][]   [][]
                    [][]  [][]    []     []        []
                                  []
     */
    public static char newPiece() {
        final int next = Tetris.stats.getRNG();
        return (next < 100) ? 'T'
                : (next < 200) ? 'L'
                : (next < 300) ? 'J'
                : (next < 400) ? 'S'
                : (next < 500) ? 'Z'
                : (next < 600) ? 'O' : 'I';
    }
    
    public static int[] getPiece(char piece, int rotation) {
        rotation = (rotation < 0) ? 0 : (rotation > 3) ? 3 : rotation;
        switch (piece) {
            case 'T':
                return T[rotation];
            case 'L':
                return L[rotation];
            case 'J':
                return J[rotation];
            case 'I':
                return I[rotation];
            case 'Z':
                return Z[rotation];
            case 'S':
                return S[rotation];
            case 'O':
                return O[rotation];
            default:
                return new int[]{0};
        }
    }

}
