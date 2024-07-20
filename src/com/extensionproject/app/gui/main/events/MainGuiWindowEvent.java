package com.extensionproject.app.gui.main.events;

import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.gui.main.MainGui;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MainGuiWindowEvent extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        try {
            if(!ConnectionManager.getAppconn().isClosed()) {
                ConnectionManager.getAppconn().close();
                LoggerManager.getClassLog(MainGui.class).info(": CONEXÃO COM O BANCO DE DADOS TERMINADA. FECHANDO O ALICATIVO.");
            }
        } catch (SQLException ex) {
            LoggerManager.getClassLog(MainGui.class).error(": FALHA AO FECHAR A CONEXÃO COM O BANCO DE DADOS.");
            throw new RuntimeException(ex);
        }
    }
}
