/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.ansuz.tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import me.ansuz.tetris.listener.InputKeyListener;

/**
 *
 * @author marruiad
 */
public class Drawer {

    private static final char DOWN = '\u2193';
    private static final char LEFT = '<';
    private static final char RIGHT = '>';
    private static boolean reload = true;

    private static int startX = -1;
    private static int startY = -1;
    private static int controlsX = -1;
    private static int controlsY = -1;
    private static int scoreX = -1;
    private static int scoreY = -1;

    private static DrawerLogic logic;

    protected static void draw(Graphics2D g, int[][] pad) {
        // "<| . . . . . . . . . .|> "
        // 20 lineas (22 total)

        if (Tetris.config.RENDER_ANTIALISING)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setFont(Tetris.config.RENDER_FONT);
        g.setColor(Tetris.config.RENDER_COLOR);
        // Calculate positions
        if (reload) {
            logic = new DrawerLogic(g);
            startX = (Tetris.config.SCREEN_WIDTH / 2) - g.getFontMetrics().stringWidth("<| . . . . .");
            startY = Tetris.config.SCREEN_HEIGHT / 12;
            controlsX = (int) (Tetris.config.SCREEN_WIDTH * 0.75f);
            controlsY = (int) (Tetris.config.SCREEN_HEIGHT * 0.086f);
            scoreX = (int) (Tetris.config.SCREEN_WIDTH * 0.075f);
            scoreY = (int) (Tetris.config.SCREEN_HEIGHT * 0.115f);
        }

        switch (Tetris.state) {
            case INITIALIZATING:
                gamestateInitalizating(g);
                break;
            case PLAYING:
                gamestatePlaying(g, pad);
                break;
            case READY:
                gamestateReady(g, pad);
                break;
        }
    }

    private static void gamestateReady(Graphics g, int[][] pad) {
        logic.setPosition(0.5f, 0.06f);
        for (int y = 0; y < pad.length; y++) {
            String line = "<|";
            for (int x = 0; x < pad[y].length; x++) {
                if (pad[y][x] > 0)
                    line += "[]";
                else
                    line += " .";
            }
            line += "|>";
            logic.draw(line, DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT);
        }
        logic.draw("<|====================|>", DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT);
        logic.draw("   \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/   ", DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT_CLOSE);
        logic.setPosition(10, Tetris.config.SCREEN_HEIGHT - (g.getFontMetrics().getHeight() * 3));
        logic.draw("Creado por: Adrian MRV / Ansuz.", DrawerLogic.WIDTH_NEXT, DrawerLogic.HEIGHT_CURRENT, DrawerLogic.POSITION_START);

        logic.setPosition(.73f, .092f);
        logic.draw((InputKeyListener.KEY_LEFT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, LEFT + "  ");
        logic.draw((InputKeyListener.KEY_DOWN) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, DOWN + "  ");
        logic.draw((InputKeyListener.KEY_RIGHT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, RIGHT + "  ");
        logic.nextLine();
        logic.draw((InputKeyListener.KEY_DROP) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, "ESPACIO");
        logic.nextLine();
        logic.nextChar(-1);
        logic.draw((InputKeyListener.KEY_ROTATE_LEFT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, "Z/A  ");
        logic.draw((InputKeyListener.KEY_ROTATE_RIGHT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, " X/D");

        // Caracteristicas del modo "READY"
        logic.nextLine(4);
        logic.draw("Pulsa ");
        logic.draw((InputKeyListener.KEY_START) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, "ENTER");
        logic.nextLine();
        logic.draw("para empezar");

        
    }

    private static void gamestateInitalizating(Graphics g) {
        final int alignX = (Tetris.config.SCREEN_WIDTH / 2) - (g.getFontMetrics().stringWidth("Cargando...") / 2);
        final int alignX2 = (Tetris.config.SCREEN_WIDTH / 2) - g.getFontMetrics().stringWidth("[||||||||||");
        final int alignY = (Tetris.config.SCREEN_HEIGHT / 2);
        String carga = "[";
        g.drawString("Cargando...", alignX, alignY);
        for (int i = 0; i < 20; i++)
            carga += (Tetris.carga > i) ? '|' : '·';
        carga += ']';
        g.drawString(carga, alignX2, alignY + g.getFontMetrics().getHeight());
    }

    private static void gamestatePlaying(Graphics g, int[][] pad) {
        logic.setPosition(0.5f, 0.06f);
        for (int y = 0; y < pad.length; y++) {
            String line = "<|";
            for (int x = 0; x < pad[y].length; x++) {
                if (pad[y][x] > 0)
                    line += "[]";
                else
                    line += " .";
            }
            line += "|>";
            logic.draw(line, DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT);
        }
        logic.draw("<|====================|>", DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT);
        logic.draw("   \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/   ", DrawerLogic.WIDTH_CENTER, DrawerLogic.HEIGHT_NEXT_CLOSE);
        logic.setPosition(10, Tetris.config.SCREEN_HEIGHT - (g.getFontMetrics().getHeight() * 3));
        logic.draw("Creado por: Adrian MRV / Ansuz.", DrawerLogic.WIDTH_NEXT, DrawerLogic.HEIGHT_CURRENT, DrawerLogic.POSITION_START);

        logic.setPosition(.73f, .092f);
        logic.draw((InputKeyListener.KEY_LEFT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, LEFT + "  ");
        logic.draw((InputKeyListener.KEY_DOWN) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, DOWN + "  ");
        logic.draw((InputKeyListener.KEY_RIGHT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, RIGHT + "  ");
        logic.nextLine();
        logic.draw((InputKeyListener.KEY_DROP) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, "ESPACIO");
        logic.nextLine();
        logic.nextChar(-1);
        logic.draw((InputKeyListener.KEY_ROTATE_LEFT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, "Z/A  ");
        logic.draw((InputKeyListener.KEY_ROTATE_RIGHT) ? Tetris.config.RENDER_COLOR_HIGHLIGHT : Tetris.config.RENDER_COLOR, " X/D");
    
        logic.setPosition(.13f, .112f);
        logic.draw("Nivel ");
        logic.draw(Tetris.config.RENDER_COLOR_HIGHLIGHT, Tetris.stats.getLevel()+ "");
        logic.nextLine();
        logic.draw("Puntos");
        logic.nextLine(2);
        logic.draw(Tetris.config.RENDER_COLOR_HIGHLIGHT, Tetris.stats.getScore() + "");
        
    }
}
