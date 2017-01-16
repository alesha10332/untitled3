package com.company;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pong");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new PongPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
