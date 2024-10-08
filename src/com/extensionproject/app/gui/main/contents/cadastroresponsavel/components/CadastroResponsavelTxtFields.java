package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone.DddFocusListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.id.IdDocumentListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone.TelefoneDocumentListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.telefone.TelefoneFocusListener;
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

    public void reloadTxtFields(){
        this.changeToActualId();
        this.txtFields[1].setText(null);
    }

    public void changeToActualId(){
        this.txtFields[0].setText(String.valueOf(this.mainpanel.getResponsavelTable().getActualId() + 1));
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
            this.txtFields[i].setEnabled(false);
            this.mainpanel.getMainpanel().add(this.txtFields[i], this.mainpanel.getComponentsGrid()[i]);
        }

        this.txtFields[2] = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()){{
            this.setValueClass(Integer.class);
            this.setMinimum(0);
            this.setMaximum(99);
            this.setCommitsOnValidEdit(true);
        }});
        this.txtFields[2].setEnabled(false);
        this.txtFields[2].setFont(new Font("Arial", Font.BOLD, 13));
        this.mainpanel.getMainpanel().add(this.txtFields[2], this.mainpanel.getComponentsGrid()[2]);

        this.txtFields[0].getDocument().addDocumentListener(new IdDocumentListener(this));
        this.txtFields[1].getDocument().addDocumentListener(new TelefoneDocumentListener(this));
        this.txtFields[1].addFocusListener(new TelefoneFocusListener(this));
        this.txtFields[2].addFocusListener(new DddFocusListener(this));
    }

    public JFormattedTextField[] getTxtFields() {
        return this.txtFields;
    }

    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }
}
