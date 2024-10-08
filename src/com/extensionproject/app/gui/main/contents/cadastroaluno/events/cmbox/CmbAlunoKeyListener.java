package com.extensionproject.app.gui.main.contents.cadastroaluno.events.cmbox;

import com.extensionproject.app.gui.main.contents.cadastroaluno.components.CadastroAlunoCmbBoxes;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.cmbox.CmbResponsavelKeyListener;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CmbAlunoKeyListener implements KeyListener {

    private CadastroAlunoCmbBoxes main;

    public CmbAlunoKeyListener(CadastroAlunoCmbBoxes main){
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.main.isEditing()){
            try {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (this.main.getCmbAluno().getEditor().getItem().toString().length() <= this.main.getAludotindx() + 2) {
                        throw new StringIndexOutOfBoundsException();
                    }
                }
                this.main.getMainpanel().getAluno().setNome(this.main.getCmbAluno().getEditor().getItem().toString().substring(this.main.getAludotindx() + 2));
            } catch (StringIndexOutOfBoundsException ex){
                LoggerManager.getClassLog(CmbResponsavelKeyListener.class).info(": Não é possível apagar o ID do responsável, em seu nome, enquanto o edita.");
                this.main.setLock(true);
                this.main.getCmbAluno().getEditor().setItem(this.main.getMainpanel().getTxtFields().getTxtField().getText() + ": ");
                this.main.setLock(false);
            }
        } else {
            if(!this.main.getCmbAluno().getEditor().getItem().toString().startsWith("<") && !this.main.isEditing()) {
                this.main.getMainpanel().getAluno().setNome(this.main.getCmbAluno().getEditor().getItem().toString());
            } else {
                this.main.getMainpanel().getAluno().nullifer();
                //this.main.getMainpanel().getAluno().setNome(null);
            }
        }
        System.out.println(this.main.getMainpanel().getAluno().getNome());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
