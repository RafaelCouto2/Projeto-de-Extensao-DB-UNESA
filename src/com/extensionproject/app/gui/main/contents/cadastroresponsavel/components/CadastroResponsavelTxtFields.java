package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CadastroResponsavelTxtFields {

    private JFormattedTextField[] txtFields;
    private final CadastroResponsavel mainpanel;
    public CadastroResponsavelTxtFields(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;

    }

    public void startNumberTxtFields(){
        this.txtFields = new JFormattedTextField[3];
        DecimalFormat df = new DecimalFormat();
        df.setGroupingUsed(false);
        NumberFormatter nformatter = new NumberFormatter(df);
        nformatter.setValueClass(Integer.class);
        nformatter.setMinimum(0);
        nformatter.setMaximum(Integer.MAX_VALUE);
        //nformatter.setAllowsInvalid(false);
        nformatter.setCommitsOnValidEdit(true);

        for (int i = 0; i < 2; i++) {
            this.txtFields[i] = new JFormattedTextField(nformatter);
            this.txtFields[i].setFont(new Font("Arial", Font.BOLD, 13));
            this.mainpanel.getMainpanel().add(this.txtFields[i], this.mainpanel.getComponentsGrid()[i]);
        }

        this.txtFields[2] = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()){{
            this.setValueClass(Integer.class);
            this.setMinimum(0);
            this.setMaximum(99);
            this.setCommitsOnValidEdit(true);
        }});

        this.txtFields[2].setFont(new Font("Arial", Font.BOLD, 13));
        this.mainpanel.getMainpanel().add(this.txtFields[2], this.mainpanel.getComponentsGrid()[2]);
        //this.mainpanel.getMainpanel().add(this.txtFields[0], this.mainpanel.getComponentsGrid()[0]);
    }
}
