/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author marruiad
 */
public class InputKeyListener implements KeyListener {

    public static boolean KEY_DOWN = false;
    public static boolean KEY_LEFT = false;
    public static boolean KEY_RIGHT = false;
    public static boolean KEY_DROP = false;
    public static boolean KEY_ROTATE_LEFT = false;
    public static boolean KEY_ROTATE_RIGHT = false;
    public static boolean KEY_START = false;

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("KeyEvent[type=" + e.getKeyChar() + "]");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                KEY_DOWN = true;
                break;
            case KeyEvent.VK_LEFT:
                KEY_LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
                KEY_RIGHT = true;
                break;
            case KeyEvent.VK_SPACE:
                KEY_DROP = true;
                break;
            case KeyEvent.VK_Z:
            case KeyEvent.VK_A:
                KEY_ROTATE_LEFT = true;
                break;
            case KeyEvent.VK_X:
            case KeyEvent.VK_D:
                KEY_ROTATE_RIGHT = true;
                break;
            case KeyEvent.VK_ENTER:
                KEY_START = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                KEY_DOWN = false;
                break;
            case KeyEvent.VK_LEFT:
                KEY_LEFT = false;
                break;
            case KeyEvent.VK_RIGHT:
                KEY_RIGHT = false;
                break;
            case KeyEvent.VK_SPACE:
                KEY_DROP = false;
                break;
            case KeyEvent.VK_Z:
            case KeyEvent.VK_A:
                KEY_ROTATE_LEFT = false;
                break;
            case KeyEvent.VK_X:
            case KeyEvent.VK_D:
                KEY_ROTATE_RIGHT = false;
                break;
            case KeyEvent.VK_ENTER:
                KEY_START = false;
                break;
        }
    }

}
