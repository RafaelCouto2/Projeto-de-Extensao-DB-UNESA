package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import java.awt.*;

public class PagamentoPanelSpnDate {
    private final PagamentoPanel mainpanel;
    private JSpinner spnDate;
    public PagamentoPanelSpnDate(PagamentoPanel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public void startDateSpn(){
        SpinnerDateModel spinModel = new SpinnerDateModel();
        this.spnDate = new JSpinner(spinModel);
        this.spnDate.setEditor(new JSpinner.DateEditor(spnDate, "dd/MM/yyyy"));
        this.spnDate.setVisible(true);
        this.spnDate.setBackground(Color.gray.brighter());
        this.spnDate.setEnabled(false);
        this.mainpanel.getMainpanel().add(this.spnDate, this.mainpanel.getComponentsGrid()[13]);
    }

    public JSpinner getSpnDate() {
        return this.spnDate;
    }
}
