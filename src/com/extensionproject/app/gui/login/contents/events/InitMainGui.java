package com.extensionproject.app.gui.login.contents.events;

import com.extensionproject.app.gui.login.LoginGui;
import com.extensionproject.app.gui.main.MainGui;
import com.extensionproject.app.logger.LoggerManager;

public class InitMainGui {
    private MainGui maingui;
    private LoginGui logingui;
    public InitMainGui(LoginGui maingui){
        this.logingui = maingui;
    }

    public void startMainGui(){
        if(this.maingui == null) {
            this.maingui = new MainGui();
            this.maingui.getBtnLog().setSelected(this.logingui.getBtnLog().isSelected());
            LoggerManager.getClassLog(InitMainGui.class).info(": JANELA PRINCIPAL INICIADA!");
            this.logingui.dispose();
        } else LoggerManager.getClassLog(InitMainGui.class).error(": Janela principal já foi iniciada.");
    }

    public MainGui getMaingui() {
        return this.maingui;
    }
}
