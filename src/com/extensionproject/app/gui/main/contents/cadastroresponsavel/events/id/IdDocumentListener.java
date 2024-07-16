package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.id;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTxtFields;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class IdDocumentListener implements DocumentListener {

    private CadastroResponsavelTxtFields main;

    public IdDocumentListener(CadastroResponsavelTxtFields main){
        this.main = main;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        warn(e);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        warn(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        warn(e);
    }

    private void warn(DocumentEvent e){

        String id = this.main.getTxtFields()[0].getText();
        this.main.getMainpanel().getResposavel().setId_responsavel(id);

        if(this.main.getMainpanel().getResposavel().getId_responsavel().isBlank() || this.main.getMainpanel().getResposavel().getId_responsavel().equals("0")){
            this.main.getMainpanel().getResposavel().setId_responsavel("DEFAULT");
        }

    }


}
