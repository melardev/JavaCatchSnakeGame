package com.melardev.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.melardev.game.SnakeGame.*;


public class Snake {

    private final int UNIT = 20;

    private final Point right = new Point(UNIT, 0);
    private final Point rightUp = new Point(UNIT, -UNIT);
    private final Point up = new Point(0, -(UNIT));

    private final Point leftUp = new Point(-(UNIT), -(UNIT));
    private final Point left = new Point(-(UNIT), 0);
    private final Point leftDown = new Point(-(UNIT), UNIT);

    private final Point down = new Point(0, UNIT);
    private final Point downRight = new Point(UNIT, UNIT);

    private Point[] orientations = {right, leftDown, left, leftUp, up, rightUp, down, downRight};


    private ArrayList<Box> snakeParts;

    private Random rand;

    public Snake() {
        rand = new Random();
        snakeParts = new ArrayList<>();

        Box head = new Box(100, 100, COLOR_SNAKE_HEAD, SNAKE_HEAD_WIDTH, SNAKE_HEAD_HEIGHT);
        snakeParts.add(head);

        for (int i = 1; i <= 40; i++) {
            int x = snakeParts.get(i - 1).x + orientations[rand.nextInt(8)].x;
            int y = snakeParts.get(i - 1).y + orientations[rand.nextInt(8)].y;
            snakeParts.add(new Box(x, y, COLOR_SNAKE_TAIL, SNAKE_PART_WIDTH, SNAKE_PART_HEIGHT));
        }
    }

    public Box getHead() {
        return snakeParts.get(0);
    }

    public void update(ArrayList<? extends Box> obstacles) {
        for (int i = (snakeParts.size() - 1); i >= 0; i--) {
            if (i == 0) { // If Head
                Box head = snakeParts.get(0);
                int newX, newY;
                do {
                    newX = head.x + orientations[rand.nextInt(orientations.length)].x;
                    newY = head.y + orientations[rand.nextInt(orientations.length)].y;
                } while (head.collidesWithBoxes(obstacles));

                head.x = newX;
                head.y = newY;

                if (head.x < 0)
                    head.x = SnakeGame.GAME_WIDTH;
                else if (head.x > SnakeGame.GAME_WIDTH)
                    head.x = 0;

                if (head.y < 0)
                    head.y = SnakeGame.GAME_HEIGHT;
                else if (head.y > SnakeGame.GAME_HEIGHT)
                    head.y = 0;

            } else {
                Box currentPiece = snakeParts.get(i);
                Box previousPiece = snakeParts.get(i - 1);

                currentPiece.x = previousPiece.x;
                currentPiece.y = previousPiece.y;
            }
        }
    }

    public void render(Graphics2D gImg) {
        for (int i = 0; i < snakeParts.size(); i++) {
            Box snakePart = snakeParts.get(i);
            snakePart.render(gImg);
        }
    }
}
