package com.company;

import java.awt.*;

public abstract class Ball {
    private Color color;
    private int ballX = 250;
    private int ballY = 250;
    private int diameter = 20;
    private int ballDeltaX = -1;
    private int ballDeltaY = 3;

    public Color getColor() {
        return color;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getBallDeltaX() {
        return ballDeltaX;
    }

    public int getBallDeltaY() {
        return ballDeltaY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setBallDeltaY(int ballDeltaY) {
        this.ballDeltaY = ballDeltaY;
    }

    public void setBallDeltaX(int ballDeltaX) {
        this.ballDeltaX = ballDeltaX;
    }
}
