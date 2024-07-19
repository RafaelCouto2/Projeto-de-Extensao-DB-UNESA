package com.extensionproject.app.gui.main.events;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.gui.main.MainGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGuiBtnLogActionListener implements ActionListener {
    private MainGui main;
    public MainGuiBtnLogActionListener(MainGui main){
        this.main = main;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GuiLinker.getLoginGui().getLogger().setVisible(this.main.getBtnLog().isSelected());
    }
}
