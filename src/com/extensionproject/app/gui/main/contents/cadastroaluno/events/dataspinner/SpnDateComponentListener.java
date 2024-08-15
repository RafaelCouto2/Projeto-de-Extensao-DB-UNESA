package com.extensionproject.app.gui.main.contents.cadastroaluno.events.dataspinner;

import com.extensionproject.app.gui.main.contents.cadastroaluno.components.CadastroAlunoSpinner;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpnDateComponentListener implements KeyListener {

    private CadastroAlunoSpinner main;

    public SpnDateComponentListener(CadastroAlunoSpinner main){
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        warn(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        warn(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        warn(e);
    }

    private void warn(KeyEvent e){
        Object source = e.getSource();
        if(source instanceof JFormattedTextField) {
            if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                if (this.main.getChildcomp().getText().isEmpty()) {
                    this.main.getMainpanel().getAluno().setDt_nascimento("DEFAULT");
                }
            } else{
                this.main.getMainpanel().getAluno().setDt_nascimento(this.main.getChildcomp().getText());
            }
        }
    }
}
