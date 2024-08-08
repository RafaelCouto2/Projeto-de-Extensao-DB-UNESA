package com.extensionproject.app.gui.main.contents.cadastroaluno.events.id;

import com.extensionproject.app.gui.main.contents.cadastroaluno.components.CadastroAlunoTxtFields;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class IdTxtFieldsListener implements DocumentListener {

    private CadastroAlunoTxtFields main;

    public IdTxtFieldsListener(CadastroAlunoTxtFields main){
        this.main = main;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
