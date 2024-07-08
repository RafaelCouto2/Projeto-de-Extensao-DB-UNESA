package com.extensionproject.app;

import com.extensionproject.app.gui.login.LoginGui;
import com.extensionproject.app.gui.main.MainGui;

public class GuiLinker {
    private static LoginGui logingui;
    private static MainGui maingui;
    public static void guiLinker() {
        logingui = new LoginGui();
        maingui = new MainGui();
    }

    public static MainGui getMainGui() {
        return maingui;
    }

    public static LoginGui getLoginGui() {
        return logingui;
    }

}
