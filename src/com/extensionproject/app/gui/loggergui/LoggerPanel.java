package com.extensionproject.app.gui.loggergui;

import com.extensionproject.app.gui.login.LoginGui;
import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.*;

public class LoggerPanel extends JPanel implements Runnable {

    private JTextArea txtLog;
    private JScrollPane pnLog;
    private RandomAccessFile logtemp, logsaved;
    private File file;
    private Thread logthread;
    private Logger log;
    private long lastModified = -1;
    private final int UPS = 30;

    public LoggerPanel(){
        Dimension size = new Dimension(LoginGui.WIDTH + 400, LoginGui.HEIGHT + 300);
        log = LoggerManager.getClassLog(LoggerPanel.class);
        this.setLayout(new GridLayout(1,0));
        this.iniFile();
        this.iniTxtArea();
        this.iniPnArea();
        this.startLogThread();
        this.setMaximumSize(size);
        this.setMinimumSize(new Dimension(200, 100));
        this.setPreferredSize(size);
    }

    public void iniTxtArea(){
        this.txtLog = new JTextArea();
        this.txtLog.setVisible(true);
        DefaultCaret caret = (DefaultCaret) this.txtLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.txtLog.setBackground(new Color(30,31,34));
        this.txtLog.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        this.txtLog.setForeground(Color.lightGray);
        this.txtLog.setLineWrap(true);
        this.txtLog.setEditable(false);
        this.txtLog.setEnabled(false);
        this.txtLog.setEditable(false);
        this.txtLog.addMouseListener(new Resize(this));

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
            this.logtemp = new RandomAccessFile("./logs/app.log", "r");
            this.txtLog.setText("");
            try{
                while (this.logtemp.read() > 1) {
                    this.txtLog.append("\n" + this.logtemp.readLine().toUpperCase() + "");
                }
            } catch (NullPointerException e){

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
            LoggerManager.getClassLog(LoggerPanel.class).error(": Arquivo de log não encontrado.");
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
        double timePerUpdate = 1000000000.0 / 2;
        double timePerFrame = 1000000000.0 / 30;
        long lastCheck = System.currentTimeMillis();
        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;
        int updates = 0;
        int frames = 0;

        try {
            do {
                long currentTime = System.nanoTime();
                deltaU += (currentTime - previousTime) / timePerUpdate;
                deltaF += (currentTime - previousTime) / timePerFrame;
                previousTime = currentTime;
                if(deltaU >= 1){
                    //UPDATES LOG
                    this.updateLog();
                    updates++;
                    deltaU--;
                }
                if(deltaF >= 1){
                    //FRAMES
                    frames++;
                    deltaF--;
                }
                if(System.currentTimeMillis() - lastCheck >= 1000){
                    lastCheck = System.currentTimeMillis();
                    //System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;
                }

            } while (true);

        } catch (IOException e) {

        }
    }

    public File getFile() {
        return this.file;
    }

    public JTextArea getTxtLog() {
        return this.txtLog;
    }

    public RandomAccessFile getLogsaved() {
        return this.logsaved;
    }

    public RandomAccessFile getLogtemp() {
        return this.logtemp;
    }
}

