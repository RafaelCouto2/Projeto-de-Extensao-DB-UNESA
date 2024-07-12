package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class CadastroResponsavelTxtFields {

    private JFormattedTextField[] txtFields;
    private final CadastroResponsavel mainpanel;
    public CadastroResponsavelTxtFields(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;

    }

    public void startNumberTxtFields(){
        this.txtFields = new JFormattedTextField[2];
        NumberFormat nformat = NumberFormat.getInstance();
        NumberFormatter nformatter = new NumberFormatter(nformat);
        nformatter.setValueClass(Integer.class);
        nformatter.setMaximum(0);
        nformatter.setMaximum(Integer.MAX_VALUE);
        //nformatter.setAllowsInvalid(false);
        nformatter.setCommitsOnValidEdit(true);

        for (int i = 0; i < 2; i++) {
            this.txtFields[i] = new JFormattedTextField(nformatter);
            this.mainpanel.getMainpanel().add(this.txtFields[i], this.mainpanel.getComponentsGrid()[i]);
        }
        //this.mainpanel.getMainpanel().add(this.txtFields[0], this.mainpanel.getComponentsGrid()[0]);
    }
}
