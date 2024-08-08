package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.gui.main.contents.cadastroaluno.events.id.IdTxtFieldsListener;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public class CadastroAlunoTxtFields {
    private CadastroAluno mainpanel;
    private JFormattedTextField txtField;

    public CadastroAlunoTxtFields(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startTxtfields(){

        DecimalFormat df = new DecimalFormat();
        NumberFormatter nformat = new NumberFormatter(df);
        nformat.setValueClass(Integer.class);
        nformat.setMinimum(0);
        nformat.setMaximum(Integer.MAX_VALUE);
        nformat.setCommitsOnValidEdit(true);
        this.txtField = new JFormattedTextField(nformat);
        this.txtField.setFont(new Font("Arial", Font.BOLD, 13));
        this.txtField.setEnabled(false);
        this.txtField.getDocument().addDocumentListener(new IdTxtFieldsListener(this));
        this.mainpanel.getMainpanel().add(this.txtField, this.mainpanel.getComponentsGrid()[0]);

    }

    public CadastroAluno getMainpanel() {
        return this.mainpanel;
    }

    public JFormattedTextField getTxtField() {
        return this.txtField;
    }
}
