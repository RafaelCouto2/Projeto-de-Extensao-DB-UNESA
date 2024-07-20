package com.extensionproject.app.gui.login.contents.events;

import com.extensionproject.app.gui.login.LoginGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnLogActionListener implements ActionListener {

    private LoginGui mainpanel;
    public btnLogActionListener(LoginGui mainpanel){
        this.mainpanel = mainpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainpanel.getLogger().setVisible(this.mainpanel.getBtnLog().isSelected());
    }
}
