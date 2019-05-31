package com.melardev.game.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private int clickXPos;
    private int clickYPos;

    @Override
    public void mouseClicked(MouseEvent me) {
        clickXPos = me.getX();
        clickYPos = me.getY();
        System.out.println(me.getX() + " " + me.getY());

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        clickXPos = -1;
        clickYPos = -1;
    }

    public int getClickedXPos() {
        return clickXPos;
    }

    public void setClickXPos(int clickXPos) {
        this.clickXPos = clickXPos;
    }

    public int getClickedYPos() {
        return clickYPos;
    }

    public void setClickYPos(int clickYPos) {
        this.clickYPos = clickYPos;
    }

    public Mouse() {
        clickXPos = -1;
        clickYPos = -1;
    }
}
