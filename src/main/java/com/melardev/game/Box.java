package com.melardev.game;

import java.awt.*;
import java.util.ArrayList;

public class Box extends Rectangle implements Obstacle {

    private final Color color;

    public Box(int xPos, int yPos, Color color, int width, int height) {
        this.x = xPos;
        this.y = yPos;
        this.color = color;
        setBounds(xPos, yPos, width, height);
    }

    @Override
    public boolean collides(int x, int y) {
        return contains(x, y);
    }

    @Override
    public void render(Graphics2D gImg) {
        gImg.setColor(color);
        gImg.fillRect(x, y, width, height);
    }

    public boolean collidesWithBoxes(ArrayList<? extends Box> obs) {
        for (Box o : obs) {
            if (this.contains(o))
                return true;
        }
        return false;
    }

    public boolean collidesWithBox(Box obs) {
        return this.contains(obs);
    }

}
