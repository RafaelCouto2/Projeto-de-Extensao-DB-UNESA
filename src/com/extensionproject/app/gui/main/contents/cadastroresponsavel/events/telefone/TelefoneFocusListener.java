package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTxtFields;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TelefoneFocusListener implements FocusListener {

    private CadastroResponsavelTxtFields main;
    public TelefoneFocusListener(CadastroResponsavelTxtFields main){
        this.main = main;
    }
    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.main.getTxtFields()[1].getText().length() < 9){
            this.main.getTxtFields()[1].setText("0");
            this.main.getMainpanel().getResposavel().setTelefone("DEFAULT");
            LoggerManager.getClassLog(TelefoneFocusListener.class).info(": Número de telefone errado.");
        }
    }
}
