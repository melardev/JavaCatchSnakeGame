package com.melardev.game;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    public static final int GAME_WIDTH = 1300;
    public static final int GAME_HEIGHT = 600;

    public static final Color COLOR_SNAKE_HEAD = new Color(0x000000);
    public static final Color COLOR_SNAKE_TAIL = new Color(0x79FFAA);
    public static final Color COLOR_GAME_BACKGROUND = new Color(0xFFFFFF);
    public static final Color COLOR_OBSTACLE = new Color(0xFF3021);

    public static final int OBSTACLE_WIDTH = 20;
    public static final int OBSTACLE_HEIGHT = 20;

    public static final int SNAKE_HEAD_WIDTH = 15;
    public static final int SNAKE_HEAD_HEIGHT = 10;

    public static final int SNAKE_PART_WIDTH = 10;
    public static final int SNAKE_PART_HEIGHT = 10;

    public SnakeGame() {
        SnakePanel sPanel = new SnakePanel(this);
        getContentPane().add(sPanel);
        setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));


        pack(); // Pack so we get the insets populated
        // now we have the insets, let's adjust the size
        setSize(GAME_WIDTH + getInsets().left + getInsets().right,
                GAME_HEIGHT + getInsets().top + getInsets().bottom);

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}