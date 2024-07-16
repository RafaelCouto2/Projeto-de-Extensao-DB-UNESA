package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.cmbox;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelCmbBoxes;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CmbResponsavelKeyListener implements KeyListener {

    private CadastroResponsavelCmbBoxes mainbxs;
    public CmbResponsavelKeyListener(CadastroResponsavelCmbBoxes mainbxs){
        this.mainbxs = mainbxs;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(this.mainbxs.isEditing()) {
            if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                if (this.mainbxs.getCmbBoxResponsavel().getEditor().getItem().toString().length() <= this.mainbxs.getIddot() + 2) {
                    this.mainbxs.setLocked(true);
                    this.mainbxs.getCmbBoxResponsavel().getEditor().setItem(this.mainbxs.getCmbBoxResponsavel().getEditor().getItem().toString().substring(0,this.mainbxs.getIddot()+ 1) + " ");
                    this.mainbxs.setLocked(false);
                }
            }
            try {
                this.mainbxs.getMainpanel().getResposavel().setNome(this.mainbxs.getCmbBoxResponsavel().getEditor().getItem().toString().substring(this.mainbxs.getIddot() + 2));
            } catch (StringIndexOutOfBoundsException ex){
                LoggerManager.getClassLog(CmbResponsavelKeyListener.class).info(": Fim da linha, não é possível apagar o ID do responsável, em seu nome, enquanto edita.");
            }
        } else {
            this.mainbxs.getMainpanel().getResposavel().setNome(this.mainbxs.getCmbBoxResponsavel().getEditor().getItem().toString());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
