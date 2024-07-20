package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.dataspinner;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelSpinner;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SpnDateChildcomponentKeyListener extends KeyAdapter {

    private CadastroResponsavelSpinner main;

    public SpnDateChildcomponentKeyListener(CadastroResponsavelSpinner main){
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        warn(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        warn(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        warn(e);
    }

    private void warn(KeyEvent e){
        Object source = e.getSource();
        if(source instanceof JFormattedTextField) {
            if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                if (this.main.getChildComponent().getText().isEmpty()) {
                    this.main.getMainpanel().getResposavel().setDt_nascimento("DEFAULT");
                }
            } else{
                this.main.getMainpanel().getResposavel().setDt_nascimento(this.main.getChildComponent().getText());
            }
        }
    }
}
