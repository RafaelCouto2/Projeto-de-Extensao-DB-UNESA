package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelCmbBoxes;
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
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
