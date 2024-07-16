package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTxtFields;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DddFocusListener implements FocusListener {

    private CadastroResponsavelTxtFields main;
    public DddFocusListener(CadastroResponsavelTxtFields main){
        this.main = main;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.main.getTxtFields()[2].getText().length() <= 1){
            this.main.getTxtFields()[2].setText("21");
            LoggerManager.getClassLog(DddFocusListener.class).info(": DDD inválido!");
        }
    }
}
