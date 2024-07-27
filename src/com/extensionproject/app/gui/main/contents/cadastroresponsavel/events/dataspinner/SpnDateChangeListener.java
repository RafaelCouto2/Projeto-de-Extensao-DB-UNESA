package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.dataspinner;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelSpinner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;

public class SpnDateChangeListener implements ChangeListener {

    private CadastroResponsavelSpinner main;
    public SpnDateChangeListener(CadastroResponsavelSpinner main){
        this.main = main;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if(source instanceof JSpinner){
            if(!this.main.getSpnDate().getModel().getValue().toString().isBlank()){
                //Aplica a data de nascimento do Spinner ao objeto do responsável.
                this.main.getMainpanel().getResposavel().setDt_nascimento(
                        new SimpleDateFormat("dd/MM/yyyy")
                                .format(this.main.getMainpanel().getSpnData().getSpnDate().getValue()));

                if(this.main.getNullValue()){
                    this.main.getMainpanel().getResposavel().setDt_nascimento("DEFAULT");
                    this.main.setNullValue(false);
                }
            }
        }

        if(this.main.getSpnDate().getModel().getValue().equals(Utils.calendar.getTime())){
            this.main.getMainpanel().getResposavel().setDt_nascimento("DEFAULT");
        }

    }
}
