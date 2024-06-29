package com.extensionproject.app.gui.login.loginguicontents;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.gui.login.LoginGui;

public class LoginEventsPanel {
    private final LoginGui gui;

    public LoginEventsPanel(LoginGui gui) {
        this.gui = gui;
    }

    public void clickEvents() {

        this.gui.getBtnLogin().addActionListener(e -> {
            //JOptionPane.showInputDialog("HEHEHE!");
            FactoryConnection.createConnection(gui.getTxtLogin(), gui.getTxtSenha());
            GuiLinker.getMainGui().setVisible(true);
            GuiLinker.getLoginGui().setVisible(false);
        });



    }

}
