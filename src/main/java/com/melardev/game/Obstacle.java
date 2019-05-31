package com.melardev.game;

import java.awt.*;

public interface Obstacle {
	boolean collides(int x, int y);

	void render(Graphics2D gImg);
}
