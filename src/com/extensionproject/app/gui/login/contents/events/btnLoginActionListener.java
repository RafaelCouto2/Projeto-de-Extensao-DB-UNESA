package com.extensionproject.app.gui.login.contents.events;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.gui.login.LoginGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnLoginActionListener implements ActionListener {
    private final LoginGui gui;

    public btnLoginActionListener(LoginGui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConnectionManager.createConnection(gui.getTxtLogin(), gui.getTxtSenha());
        this.gui.getInitmaingui().startMainGui();
        GuiLinker.getLoginGui().dispose();
    }
}
