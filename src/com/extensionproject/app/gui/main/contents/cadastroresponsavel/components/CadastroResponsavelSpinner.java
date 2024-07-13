package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import java.awt.*;

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
        this.mainpanel.getMainpanel().add(this.spnDate, this.mainpanel.getComponentsGrid()[6]);
    }
}
