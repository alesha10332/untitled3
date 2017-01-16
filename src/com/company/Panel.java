package com.company;

import java.awt.*;

public abstract class Panel {
    private Color color;

    private int playerOneX = 25;
    private int playerOneY = 250;
    private final int playerOneWidth = 10;
    private final int playerOneHeight = 50;

    private int paddleSpeed;

    private int playerTwoX = 465;
    private int playerTwoY = 250;
    private final int playerTwoWidth = 10;
    private final int playerTwoHeight = 50;

    public Color getColor() {
        return color;
    }

    public int getPlayerOneX() {
        return playerOneX;
    }

    public int getPlayerOneY() {
        return playerOneY;
    }

    public int getPlayerOneWidth() {
        return playerOneWidth;
    }

    public int getPlayerOneHeight() {
        return playerOneHeight;
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public int getPlayerTwoX() {
        return playerTwoX;
    }

    public int getPlayerTwoY() {
        return playerTwoY;
    }

    public int getPlayerTwoWidth() {
        return playerTwoWidth;
    }

    public int getPlayerTwoHeight() {
        return playerTwoHeight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    public void setPlayerOneY(int playerOneY) {
        this.playerOneY = playerOneY;
    }

    public void setPlayerTwoY(int playerTwoY) {
        this.playerTwoY = playerTwoY;
    }
}
