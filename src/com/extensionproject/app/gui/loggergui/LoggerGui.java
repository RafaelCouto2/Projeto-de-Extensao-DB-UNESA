package com.extensionproject.app.gui.loggergui;


import javax.swing.*;

public class LoggerGui extends JFrame {

    private final JPanel logpanel;
    public LoggerGui() {

        logpanel = new LoggerPanel();
        this.setTitle("LOG");
        this.add(logpanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.setVisible(true);

    }

}
