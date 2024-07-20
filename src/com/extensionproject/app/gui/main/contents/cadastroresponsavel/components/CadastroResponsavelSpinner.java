package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.dataspinner.SpnDateChangeListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.dataspinner.SpnDateChildcomponentKeyListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;

public class CadastroResponsavelSpinner {

    private JSpinner spnDate;
    private boolean nullValue;
    private JFormattedTextField childComponent;
    private CadastroResponsavel mainpanel;
    public CadastroResponsavelSpinner(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startSpnData() {
        this.spnDate = new JSpinner(new SpinnerDateModel());
        this.spnDate.setEditor(new JSpinner.DateEditor(spnDate, "dd/MM/yyyy"));
        this.spnDate.setBackground(Color.gray.brighter());
        this.spnDate.setFont(Utils.jetmono13);
        this.spnDate.setCursor(Utils.handcursor);
        this.spnDate.setEnabled(false);
        JFormattedTextField childcomp = (JFormattedTextField) this.spnDate.getEditor().getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) childcomp.getFormatter();
        formatter.setCommitsOnValidEdit(false);
        childcomp.setText(null);
        this.childComponent = childcomp;
        childcomp.addKeyListener(new SpnDateChildcomponentKeyListener(this));
        this.spnDate.addChangeListener(new SpnDateChangeListener(this));
        this.mainpanel.getMainpanel().add(this.spnDate, this.mainpanel.getComponentsGrid()[6]);
    }

    public void reloadDt(){
        //this.spnDate.getModel().setValue(Utils.calendar.getTime());
        this.getChildComponent().setText(null);
    }

    public void todayDate(){
        this.spnDate.getModel().setValue(Utils.calendar.getTime());
    }

    public JSpinner getSpnDate() {
        return this.spnDate;
    }

    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }

    public boolean getNullValue() {
        return this.nullValue;
    }

    public void setNullValue(boolean nullValue) {
        this.nullValue = nullValue;
    }

    public JFormattedTextField getChildComponent() {
        return this.childComponent;
    }
}
