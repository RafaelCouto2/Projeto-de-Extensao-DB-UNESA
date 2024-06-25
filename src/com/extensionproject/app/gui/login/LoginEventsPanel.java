package com.extensionproject.app.gui.login;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.FactoryConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEventsPanel {
    private LoginGui gui;

    public LoginEventsPanel(LoginGui gui) {
        this.gui = gui;
    }

    public void clickEvents() {

        this.gui.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showInputDialog("HEHEHE!");
                FactoryConnection.connect(gui.getTxtLogin(), gui.getTxtSenha());
                GuiLinker.getMainGui().setVisible(true);
                gui.setVisible(false);
            }
        });



    }

}
