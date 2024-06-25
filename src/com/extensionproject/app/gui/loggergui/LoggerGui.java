package com.extensionproject.app.gui.loggergui;


import javax.swing.*;

public class LoggerGui extends JFrame {

    private JPanel logpanel;
    public LoggerGui() {

        logpanel = new LoggerPanel();
        logpanel.setFocusable(true);
        logpanel.requestFocus();
        this.setTitle("LOG");
        this.add(logpanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}
