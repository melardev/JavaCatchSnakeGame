package com.melardev.game;

import com.melardev.game.input.KeyBoard;
import com.melardev.game.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.melardev.game.SnakeGame.OBSTACLE_HEIGHT;
import static com.melardev.game.SnakeGame.OBSTACLE_WIDTH;

public class SnakePanel extends JPanel implements Runnable {

    private volatile boolean running;
    private Thread gameThread;
    private BufferedImage canvasImg;
    private SnakeGame snakeGame;
    private KeyBoard keyBoard;
    // private Screen screen;
    private Mouse mouse;
    private ArrayList<BoxObstacle> obstacles;


    private boolean winner;
    private Snake snake;

    public SnakePanel(SnakeGame game) {

        snakeGame = game;
        keyBoard = new KeyBoard();
        mouse = new Mouse();

        obstacles = new ArrayList<>();
        winner = false;
        snake = new Snake();

        addKeyListener(keyBoard);
        addMouseListener(mouse);
        requestFocus();
        setRunning(false);
        canvasImg = new BufferedImage(SnakeGame.GAME_WIDTH, SnakeGame.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // The painting will be done by us manually, no need to let Java manage the painting for us.
        setIgnoreRepaint(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        System.out.println("notify");
        if (!isRunning() && gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    private boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        setRunning(true);
        System.out.println("started");
        while (isRunning()) {

            gameUpdate();
            gameRender();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void gameUpdate() {

        if (keyBoard.isRequestingExit())
            setRunning(false);

        if (winner)
            return;

        if (mouse.getClickedXPos() != -1) {

            Box head = snake.getHead();
            if (head.contains(mouse.getClickedXPos(), mouse.getClickedYPos())) {
                winner = true;
                return;
            }

            obstacles.add(
                    new BoxObstacle(mouse.getClickedXPos(), mouse.getClickedYPos(), OBSTACLE_WIDTH,
                            OBSTACLE_HEIGHT));
        }

        snake.update(obstacles);
    }

    private boolean collidesWithBox(BoxObstacle head) {

        for (BoxObstacle o : obstacles) {
            if (o.contains(head.x, head.y))
                return true;
        }
        return false;
    }

    private void gameRender() {
        Graphics2D gImg = canvasImg.createGraphics();
        if (winner) {
            gImg.setColor(Color.BLACK);
            gImg.drawString("You WIN!!!!!!!!!", 50, 50);
            System.out.println("yo are winner");
            return;
        }

        gImg.setColor(SnakeGame.COLOR_GAME_BACKGROUND);
        gImg.fillRect(0, 0, SnakeGame.GAME_WIDTH, SnakeGame.GAME_HEIGHT);
        gImg.setColor(SnakeGame.COLOR_OBSTACLE);
        for (BoxObstacle o : obstacles) {
            o.render(gImg);
        }

        snake.render(gImg);

        Graphics gComp = getGraphics();
        gComp.drawImage(canvasImg, 0, 0, null);

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
