package com.extensionproject.app.gui.login;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.FactoryConnection;

public class LoginEventsPanel {
    private final LoginGui gui;

    public LoginEventsPanel(LoginGui gui) {
        this.gui = gui;
    }

    public void clickEvents() {

        this.gui.getBtnLogin().addActionListener(e -> {
            //JOptionPane.showInputDialog("HEHEHE!");
            FactoryConnection.connect(gui.getTxtLogin(), gui.getTxtSenha());
            GuiLinker.getMainGui().setVisible(true);
            GuiLinker.getLoginGui().setVisible(false);
        });



    }

}
