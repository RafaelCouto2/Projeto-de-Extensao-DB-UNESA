package com.extensionproject.app;

import com.extensionproject.app.gui.login.LoginGui;

public class GuiLinker {
    private static LoginGui logingui;
    public static void guiLinker() {
        logingui = new LoginGui();
    }

    public static LoginGui getLoginGui() {
        return logingui;
    }

}
