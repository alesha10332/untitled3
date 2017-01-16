package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener, KeyListener{
    private static Panel panel;
    private static BasePanel basePanel = new BasePanel();
    private static HeavyPanel heavyPanel = new HeavyPanel();
    private static LightPanel lightPanel = new LightPanel();

    private static Ball ball;
    private static BaseBall baseBall = new BaseBall();
    private static HeavyBall heavyBall = new HeavyBall();
    private static LightBall lightBall = new LightBall();
    private static RedBall redBall = new RedBall();

    private boolean showTitleScreen = true;
    private boolean playing = false;
    private boolean gameOver = false;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;

    private int numberOfPanel = 0;
    private int numberOfBall = 0;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;


    //Конструктор PongPanel
    public PongPanel(){
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        panel = basePanel;
        ball = baseBall;

        //Вызов шага() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e){
        step();
    }


    public void step(){

        if(playing){

            movePlayerFirst();
            movePlayerSecond();

            //Движение мяча
            int nextBallLeft = ball.getBallX() + ball.getBallDeltaX();
            int nextBallRight = ball.getBallX() + ball.getDiameter() + ball.getBallDeltaX();
            int nextBallTop = ball.getBallY() + ball.getBallDeltaY();
            int nextBallBottom = ball.getBallY() + ball.getDiameter() + ball.getBallDeltaY();

            //мяч отскакивает от верхней и нижней части экрана
            if (nextBallTop < 0 || nextBallBottom > getHeight()) {
                ball.setBallDeltaY(ball.getBallDeltaY() * (-1));
            }
            //Проверка на выход за левую сторону
            if (nextBallLeft < panel.getPlayerOneX() + panel.getPlayerOneWidth()) {
                //Проверка на пропуск ракетки
                if (nextBallTop > panel.getPlayerOneY() + panel.getPlayerOneHeight() || nextBallBottom < panel.getPlayerOneY()) {

                    playerTwoScore ++;

                    if (playerTwoScore == 3) {
                        playing = false;
                        gameOver = true;
                    }
                    ball.setBallX(250);
                    ball.setBallY(250);
                }
                else {
                    if(ball.getBallY() <= panel.getPlayerOneY() + panel.getPlayerOneHeight() - 40) {
                        if(ball.getBallDeltaY() < 0)
                            ball.setBallDeltaY(ball.getBallDeltaY());
                        else ball.setBallDeltaY(ball.getBallDeltaY() * (-1));
                    }
                    else {
                        if (ball.getBallDeltaY() < 0)
                            ball.setBallDeltaY(ball.getBallDeltaY() * (-1));
                        else ball.setBallDeltaY(ball.getBallDeltaY());
                    }

                    ball.setBallDeltaX(ball.getBallDeltaX() * (-1));
                }
            }

            //Проверка на выход за правую линию
            if (nextBallRight > panel.getPlayerTwoX()) {
                //Проверка на касание ракетки
                if (nextBallTop > panel.getPlayerTwoY() + panel.getPlayerTwoHeight() || nextBallBottom < panel.getPlayerTwoY()) {

                    playerOneScore++;

                    if (playerOneScore == 3) {
                        playing = false;
                        gameOver = true;
                    }
                    ball.setBallX(250);
                    ball.setBallY(250);
                } else {
                    if (ball.getBallY() <= panel.getPlayerTwoY() + panel.getPlayerTwoHeight() -40) {
                        if (ball.getBallDeltaY() < 0)
                            ball.setBallDeltaY(ball.getBallDeltaY());
                        else ball.setBallDeltaY(ball.getBallDeltaY() * (-1));
                    } else {
                        if (ball.getBallDeltaY() < 0)
                            ball.setBallDeltaY(ball.getBallDeltaY() * (-1));
                        else ball.setBallDeltaY(ball.getBallDeltaY());
                    }
                    ball.setBallDeltaX(ball.getBallDeltaX() * (-1));
                }
            }

            //Движение Шара
            ball.setBallX(ball.getBallX() + ball.getBallDeltaX());
            ball.setBallY(ball.getBallY() + ball.getBallDeltaY());
        }

        //Перерисовка изоброжениея
        repaint();
    }

    private void movePlayerFirst() {
        if (upPressed) {
            if (panel.getPlayerOneY() - panel.getPaddleSpeed() > 0) {
                panel.setPlayerOneY(panel.getPlayerOneY() - panel.getPaddleSpeed());
            }
        }
        if (downPressed) {
            if (panel.getPlayerOneY() + panel.getPaddleSpeed() + panel.getPlayerOneHeight() < getHeight()) {
                panel.setPlayerOneY(panel.getPlayerOneY() + panel.getPaddleSpeed());
            }
        }
    }

    private void movePlayerSecond(){
        if (wPressed) {
            if (panel.getPlayerTwoY()- panel.getPaddleSpeed() > 0) {
                panel.setPlayerTwoY(panel.getPlayerTwoY() - panel.getPaddleSpeed());
            }
        }
        if (sPressed) {
            if (panel.getPlayerTwoY() + panel.getPaddleSpeed() + panel.getPlayerTwoHeight() < getHeight()) {
                panel.setPlayerTwoY(panel.getPlayerTwoY() + panel.getPaddleSpeed());
            }
        }
    }

    //Рисование экрана игры
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        if (showTitleScreen) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 200, 120);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 'P' to play.", 175, 400);
        }
        else if (playing) {

            int playerOneRight = panel.getPlayerOneX() + panel.getPlayerOneWidth();
            int playerTwoLeft =  panel.getPlayerTwoX();
            //нарисовать пунктирную линию вниз центр
            for (int lineY = 0; lineY < getHeight(); lineY += 50) {
                g.drawLine(250, lineY, 250, lineY+25);
            }

            //Рисовка линий с обеих сторон
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
            g.drawLine(playerOneRight, 0, playerTwoLeft, 0);
            g.drawLine(playerOneRight, getHeight()-1, playerTwoLeft, getHeight()-1);

            //Нарисовние очков
            g.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
            g.drawString(String.valueOf(playerOneScore),130, 100);
            g.drawString(String.valueOf(playerTwoScore), 360, 100);

            //Нарисование мяча
            g.setColor(ball.getColor());
            g.fillOval(ball.getBallX(), ball.getBallY(), ball.getDiameter(), ball.getDiameter());

            //Нарисование ракетак
            g.setColor(panel.getColor());
            g.fillRect(panel.getPlayerOneX(), panel.getPlayerOneY(), panel.getPlayerOneWidth(), panel.getPlayerOneHeight());
            g.fillRect(panel.getPlayerTwoX(), panel.getPlayerTwoY(), panel.getPlayerTwoWidth(), panel.getPlayerTwoHeight());
        }
        else if (gameOver) {

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString(String.valueOf(playerOneScore), 100, 100);
            g.drawString(String.valueOf(playerTwoScore), 380, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            if (playerOneScore > playerTwoScore) {
                g.drawString("Player 1 Wins!", 130, 200);
            }
            else {
                g.drawString("Player 2 Wins!", 130, 200);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press r to restart.", 170, 400);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    //проверка на нажатие
    public void keyPressed(KeyEvent e) {
        if (showTitleScreen) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                showTitleScreen = false;
                playing = true;
            }
        }
        else if(playing){
            if (e.getKeyCode() == KeyEvent.VK_W) {
                upPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                downPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                wPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                sPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_NUMPAD1){
                if(numberOfPanel == 0) {
                    panel = heavyPanel;
                    panel.setPlayerOneY(basePanel.getPlayerOneY());
                    panel.setPlayerTwoY(basePanel.getPlayerTwoY());
                    numberOfPanel = 1;
                }
                else if(numberOfPanel == 1){
                    panel = lightPanel;
                    panel.setPlayerOneY(heavyPanel.getPlayerOneY());
                    panel.setPlayerTwoY(heavyPanel.getPlayerTwoY());
                    numberOfPanel = 2;
                }else if(numberOfPanel == 2){
                    panel = basePanel;
                    panel.setPlayerOneY(lightPanel.getPlayerOneY());
                    panel.setPlayerTwoY(lightPanel.getPlayerTwoY());
                    numberOfPanel = 0;
                }
            }else if(e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_NUMPAD2){
                if (numberOfBall == 0){
                    ball = heavyBall;
                    ball.setBallX(250);
                    ball.setBallY(250);
                    numberOfBall = 1;
                }else if (numberOfBall == 1){
                    ball = lightBall;
                    ball.setBallX(250);
                    ball.setBallY(250);
                    numberOfBall = 2;
                }else if (numberOfBall == 2){
                    ball = redBall;
                    ball.setBallX(250);
                    ball.setBallY(250);
                    numberOfBall = 3;
               }else if (numberOfBall == 3){
                    ball = baseBall;
                    ball.setBallX(250);
                    ball.setBallY(250);
                    numberOfBall = 0;
                }
            }
        }
        else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                gameOver = false;
                showTitleScreen = true;
                panel.setPlayerOneY(250);
                panel.setPlayerTwoY(250);
                ball.setBallX(250);
                ball.setBallY(250);
                playerOneScore = 0;
                playerTwoScore = 0;
                upPressed = false;
                downPressed = false;
                wPressed = false;
                sPressed = false;
            }
        }
    }

//реализация управление
    public void keyReleased(KeyEvent e) {
        if (playing) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                upPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                downPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                wPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                sPressed = false;
            }
        }
    }

}