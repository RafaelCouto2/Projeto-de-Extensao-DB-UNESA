package com.extensionproject.app.gui.main.contents.cadastroaluno.events.id;

import com.extensionproject.app.gui.main.contents.cadastroaluno.components.CadastroAlunoTxtFields;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class IdDocumentListener implements DocumentListener {

    private CadastroAlunoTxtFields main;

    public IdDocumentListener(CadastroAlunoTxtFields main){
        this.main = main;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        warn();
    }

    private void warn(){
        try {
            String id = this.main.getTxtField().getValue().toString();
            this.main.getMainpanel().getAluno().setId_aluno(id);
            if(this.main.getMainpanel().getAluno().getId_aluno().isBlank() || this.main.getMainpanel().getAluno().getId_aluno().equals("0")){
                this.main.getMainpanel().getAluno().setId_aluno("DEFAULT");
            }
        } catch (NullPointerException e){
            String id = "DEFAULT";
            this.main.getMainpanel().getAluno().setId_aluno(id);
        }

//        this.main.getMainpanel().getAluno().setId_aluno(id);
//        if(this.main.getMainpanel().getAluno().getId_aluno().isBlank() || this.main.getMainpanel().getAluno().getId_aluno().equals("0")){
//            this.main.getMainpanel().getAluno().setId_aluno("DEFAULT");
//        }

    }
}
