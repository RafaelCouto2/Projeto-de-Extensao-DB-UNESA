package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import javax.swing.*;

public class ResponsavelTableColumnComboBox {
    private JComboBox<String>[] telBox;

    public ResponsavelTableColumnComboBox(ResponsavelTable mtable){

        this.telBox = new JComboBox[mtable.getResptable().getRowCount()];
        for(int i = 0; i < mtable.getResptable().getRowCount(); i++){
            this.telBox[i] = new JComboBox<>();
        }

    }

    public JComboBox<String>[] getTelBox() {
        return this.telBox;
    }
}
