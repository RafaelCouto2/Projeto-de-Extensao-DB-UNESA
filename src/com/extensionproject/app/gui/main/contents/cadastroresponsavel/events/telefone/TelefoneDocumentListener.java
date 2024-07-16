package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTxtFields;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TelefoneDocumentListener implements DocumentListener {

    private CadastroResponsavelTxtFields main;
    public TelefoneDocumentListener(CadastroResponsavelTxtFields main){
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

    }

    private void warn(DocumentEvent e){

        this.main.getMainpanel().getResposavel().setTelefone(
                this.main.getTxtFields()[2].getText() + this.main.getTxtFields()[1].getText()
        );

        if(this.main.getTxtFields()[1].getText().length() < 9){
            this.main.getMainpanel().getResposavel().setTelefone("DEFAULT");
        }

    }
}
