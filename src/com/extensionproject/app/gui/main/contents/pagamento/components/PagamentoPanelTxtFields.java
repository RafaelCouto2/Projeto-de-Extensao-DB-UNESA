package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PagamentoPanelTxtFields {

    private final PagamentoPanel mainpanel;
    private JTextField[] txtFields;
    public PagamentoPanelTxtFields(PagamentoPanel mainpanel){

        this.mainpanel = mainpanel;

    }

    public void startTxtFields() {

        this.txtFields = new JTextField[5];
        for (int i = 0; i < 5; i++){
            this.txtFields[i] = new JTextField();
            this.txtFields[i].setFont(Utils.jetmono13);
            this.txtFields[i].setEditable(false);
            this.txtFields[i].setBackground(Color.gray.brighter());
        }
//        this.txtFields[3].addKeyListener(new FieldsKeyListener(3, mainpanel));
//        this.txtFields[4].addKeyListener(new FieldsKeyListener(4, mainpanel));
        Consumer<Integer> addfields = f -> {
            for(int i = 0; i < this.txtFields.length; i++){
                this.mainpanel.getMainpanel().add(this.txtFields[i], this.mainpanel.getComponentsGrid()[((i == 4) ? 5 : i)]);
            }
            txtFields[1].setVisible(false);
            txtFields[2].setVisible(false);
        };
        addfields.accept(1);
    }

    public JTextField[] getTxtFields() {
        return this.txtFields;
    }
}
