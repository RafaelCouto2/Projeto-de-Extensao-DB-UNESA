package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.dataspinner.SpnDateChangeListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroResponsavelSpinner {

    private JSpinner spnDate;
    private CadastroResponsavel mainpanel;
    public CadastroResponsavelSpinner(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startSpnData() {
        this.spnDate = new JSpinner(new SpinnerDateModel());
        this.spnDate.setEditor(new JSpinner.DateEditor(spnDate, "dd/MM/yyyy"));
        this.spnDate.setBackground(Color.gray.brighter());
        this.spnDate.setFont(Utils.jetmono);
        this.spnDate.setCursor(Utils.handcursor);
        this.spnDate.setEnabled(false);
        JFormattedTextField childcomp = (JFormattedTextField) this.spnDate.getEditor().getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) childcomp.getFormatter();
        formatter.setCommitsOnValidEdit(false);
        this.spnDate.addChangeListener(new SpnDateChangeListener(this));
        this.mainpanel.getMainpanel().add(this.spnDate, this.mainpanel.getComponentsGrid()[6]);
    }

    public void reloadDt(){
        this.spnDate.getModel().setValue(Utils.calendar.getTime());
    }

    public JSpinner getSpnDate() {
        return this.spnDate;
    }

    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }
}
