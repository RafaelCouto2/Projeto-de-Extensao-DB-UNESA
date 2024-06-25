package com.extensionproject.app.gui.loggergui;

import com.extensionproject.app.gui.login.LoginGui;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.*;

public class LoggerPanel extends JPanel implements Runnable {

    private JTextArea txtLog;
    private JScrollPane pnLog;
    private final Dimension size;
    private RandomAccessFile logtemp, logsaved;
    private File file;
    private Thread logthread;
    private long lastModified = -1;
    public LoggerPanel(){
        this.setLayout(new GridLayout(1,0));
        LoggerManager.getClassLog(LoggerPanel.class).info("CREATED MAIN LOGGER PANEL!\n");
        this.size = new Dimension(LoginGui.WIDTH + 100, LoginGui.HEIGHT + 20);
        this.iniFile();
        this.iniTxtArea();
        this.iniPnArea();
        this.startLogThread();
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
    }

    public void iniTxtArea(){
        this.txtLog = new JTextArea();
        this.txtLog.setVisible(true);
        DefaultCaret caret = (DefaultCaret) this.txtLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.txtLog.setBackground(Color.DARK_GRAY);
        this.txtLog.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        this.txtLog.setForeground(Color.lightGray);
        this.txtLog.setLineWrap(true);

    }

    public void iniPnArea(){
        this.pnLog = new JScrollPane();
        this.pnLog.add(this.txtLog);
        this.pnLog.setVisible(true);
        this.pnLog.setViewportView(this.txtLog);
        this.add(pnLog);
    }

    public void updateLog() throws IOException {

        if(this.lastModified != this.file.lastModified()) {
            this.file = new File("./logs/app.log");
            this.logtemp = new RandomAccessFile("./logs/app.log", "rw");
            this.txtLog.setText("\n");
            try{
                while (!this.logtemp.readLine().equals("\0")) {
                    this.txtLog.append(this.logtemp.readLine().toUpperCase() + "\n");
                }
                //this.logsaved.write(this.logtemp.readByte());
            } catch (NullPointerException e){
                e.getMessage();
            }
            this.lastModified = this.file.lastModified();
        }

    }

    public void iniFile(){

        this.file = new File("./logs/app.log");
        try {
            this.logtemp = new RandomAccessFile("./logs/app.log", "rw");
            this.logsaved = new RandomAccessFile("./logs/app_all.log", "rw");
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void startLogThread(){
        this.logthread = new Thread(this);
        this.logthread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    @Override
    public void run() {

        try {
            String linha = this.logtemp.readLine();
            do {
                this.updateLog();
            } while (true);
        } catch (IOException e) {
            e.getMessage();
        }

    }
}

