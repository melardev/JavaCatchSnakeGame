package com.melardev.game;

import static com.melardev.game.SnakeGame.COLOR_OBSTACLE;

public class BoxObstacle extends Box {

    public BoxObstacle(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, COLOR_OBSTACLE, width, height);
    }
}
