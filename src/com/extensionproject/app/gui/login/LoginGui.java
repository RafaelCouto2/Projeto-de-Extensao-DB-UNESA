package com.extensionproject.app.gui.login;

import com.extensionproject.app.gui.login.contents.LoginEventsPanel;
import com.extensionproject.app.gui.main.MainGui;
import com.extensionproject.app.gui.loggergui.LoggerGui;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoginGui extends JFrame {
    private JPanel panel;
    private JButton btnLogin;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JLabel spacementLabel;
    private final LoginEventsPanel evtpanel;
    private LoggerGui logger;

    public static final int WIDTH = 425;
    public static final int HEIGHT = 190;

    public LoginGui() {
        this.clearLog();
        this.setContentPane(panel);
        this.evtpanel = new LoginEventsPanel(this);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
        this.evtsCall();
        this.logger = new LoggerGui();
        this.requestFocus();
    }
    public void evtsCall(){
        this.evtpanel.clickEvents();
    }

    public void clearLog(){
        try {
            Files.deleteIfExists(Path.of("./logs/app.log"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            LoggerManager.getClassLog(MainGui.class).info("STARTED MAIN WINDOW!");
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
}
