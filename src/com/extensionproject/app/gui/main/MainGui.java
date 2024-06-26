package com.extensionproject.app.gui.main;

import com.extensionproject.app.GuiLinker;

import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame {
    private JPanel mainGui;
    private JButton tempButton;
    private static final int WIDTH = 800, HEIGHT = 600;

    public MainGui(){
        this.setContentPane(mainGui);
        this.mainGui.setBackground(Color.lightGray);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Escolinha da Tia Celeusa");
        //this.setVisible(true);

        this.tempButton.addActionListener(e -> {
            GuiLinker.getMainGui().setVisible(false);
            GuiLinker.getLoginGui().setVisible(true);
        });

    }

}
