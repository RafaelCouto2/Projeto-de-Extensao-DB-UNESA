package com.extensionproject.app.gui.main;

import javax.swing.*;

public class MainGui extends JFrame {
    private JPanel mainGui;
    private static final int WIDTH = 800, HEIGHT= 600;

    public MainGui(){
        this.setContentPane(mainGui);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.pack();
        //this.setVisible(true);
    }

}
