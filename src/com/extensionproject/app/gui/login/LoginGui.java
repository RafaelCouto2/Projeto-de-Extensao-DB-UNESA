package com.extensionproject.app.gui.login;

import com.extensionproject.app.gui.login.contents.events.InitMainGui;
import com.extensionproject.app.gui.login.contents.events.btnLoginActionListener;
import com.extensionproject.app.gui.login.contents.events.btnLogActionListener;
import com.extensionproject.app.gui.main.MainGui;
import com.extensionproject.app.gui.loggergui.LoggerGui;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LoginGui extends JFrame {
    private JPanel panel;
    private JButton btnLogin;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JLabel spacementLabel;
    private JCheckBox btnLog;
    private LoggerGui logger;
    private InitMainGui initmaingui;

    public static final int WIDTH = 425;
    public static final int HEIGHT = 190;

    private void initComponents(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/icon.png")));
        this.setIconImage(icon.getImage());

        this.clearLog();
        this.setContentPane(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
        this.evtsCall();
        this.logger = new LoggerGui();
        this.initmaingui = new InitMainGui(this);
        this.requestFocus();
    }

    public LoginGui() {
        this.initComponents();
    }
    public void evtsCall(){
        //this.evtpanel.clickEvents();
        this.btnLogin.addActionListener(new btnLoginActionListener(this));
        this.btnLog.addActionListener(new btnLogActionListener(this));
    }

    public void clearLog(){
        try {
            Files.deleteIfExists(Path.of("./logs/app.log"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            LoggerManager.getClassLog(MainGui.class).info("LOG4J2!");
        }
    }

    public JButton getBtnLogin(){
        return this.btnLogin;
    }

    public String getTxtLogin(){
        return this.txtLogin.getText().toLowerCase();
    }

    public String getTxtSenha() {
        return this.txtSenha.getText().toLowerCase();
    }

    public LoggerGui getLogger() {
        return this.logger;
    }

    public InitMainGui getInitmaingui() {
        return this.initmaingui;
    }
    public JCheckBox getBtnLog() {
        return this.btnLog;
    }
}
