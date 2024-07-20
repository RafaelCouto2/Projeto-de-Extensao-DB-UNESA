package com.extensionproject.app.gui.loggergui;


import com.extensionproject.app.gui.loggergui.events.LoggerWindowEvent;

import javax.swing.*;
import java.util.Objects;

public class LoggerGui extends JFrame {

    private LoggerPanel logpanel;

    private void initComponents(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/log_icon.png")));
        this.setIconImage(icon.getImage());
        this.setTitle("LOG");
        this.setUndecorated(false);
        this.logpanel = new LoggerPanel();
        this.add(logpanel);
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new LoggerWindowEvent(this));
        this.setVisible(true);
    }
    public LoggerGui() {
        this.initComponents();
    }

    public LoggerPanel getLogpanel() {
        return this.logpanel;
    }

}
