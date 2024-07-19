package com.extensionproject.app.gui.loggergui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class LoggerGui extends JFrame {

    private final LoggerPanel logpanel;
    public LoggerGui() {

        logpanel = new LoggerPanel();
        this.setTitle("LOG");
        this.setUndecorated(false);
        this.add(logpanel);
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.addWindowListener(new LoggerWindowEvent(this));

    }

    public LoggerPanel getLogpanel() {
        return this.logpanel;
    }

}
