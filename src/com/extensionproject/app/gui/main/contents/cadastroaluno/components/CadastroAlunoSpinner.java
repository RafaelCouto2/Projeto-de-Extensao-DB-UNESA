package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.text.DecimalFormat;

public class CadastroAlunoSpinner {
    private CadastroAluno mainpanel;
    private JSpinner spnDate;
    private JFormattedTextField childcomp;

    public CadastroAlunoSpinner(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startSpinner(){
        this.spnDate = new JSpinner(new SpinnerDateModel());
        this.spnDate.setEditor(new JSpinner.DateEditor(this.spnDate, "dd/MM/yyyy"));
        this.spnDate.setBackground(Color.gray.brighter());
        this.spnDate.setFont(Utils.jetmono13);
        this.spnDate.setCursor(Utils.handcursor);
        this.spnDate.setEnabled(false);
        JFormattedTextField childcomp = (JFormattedTextField) this.spnDate.getEditor().getComponent(0);
        DefaultFormatter df = (DefaultFormatter) childcomp.getFormatter();
        df.setCommitsOnValidEdit(false);
        childcomp.setText(null);
        this.childcomp = childcomp;

        this.mainpanel.getMainpanel().add(this.spnDate, this.mainpanel.getComponentsGrid()[6]);

    }

    public JSpinner getSpnDate() {
        return this.spnDate;
    }

    public JFormattedTextField getChildcomp() {
        return this.childcomp;
    }
}
