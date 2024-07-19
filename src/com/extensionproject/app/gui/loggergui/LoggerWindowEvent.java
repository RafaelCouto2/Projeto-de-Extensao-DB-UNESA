package com.extensionproject.app.gui.loggergui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class LoggerWindowEvent extends WindowAdapter {

    private LoggerGui maingui;
    public LoggerWindowEvent(LoggerGui maingui){
        this.maingui = maingui;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        super.windowClosed(e);
        try {
            this.maingui.getLogpanel().getLogsaved().writeUTF(this.maingui.getLogpanel().getTxtLog().getText());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
