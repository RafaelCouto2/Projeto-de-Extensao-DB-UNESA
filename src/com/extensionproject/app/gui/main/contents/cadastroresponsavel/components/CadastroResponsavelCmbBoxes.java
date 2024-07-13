package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class CadastroResponsavelCmbBoxes {

    private CadastroResponsavel mainpanel;
    private JComboBox<Object> cmbBoxResponsavel, cmbBoxSexo;
    public CadastroResponsavelCmbBoxes(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void starCmbx() {
        this.cmbBoxResponsavel = new JComboBox<>();
        this.cmbBoxSexo = new JComboBox<>();
        this.cmbBoxResponsavel.setCursor(Utils.handcursor);
        this.cmbBoxSexo.setCursor(Utils.handcursor);
        this.cmbBoxResponsavel.setFont(Utils.jetmono);
        this.cmbBoxSexo.setFont(Utils.jetmono);
        this.cmbBoxResponsavel.addItemListener(this::cmbBoxResponsavelItemEvent);
        this.cmbBoxSexo.addItemListener(this::cmbBoxSexoItemEvent);

        this.cmbBoxSexo.addItem(new String("Masculino"));
        this.cmbBoxSexo.addItem(new String("Feminino"));

        this.mainpanel.getMainpanel().add(this.cmbBoxResponsavel, this.mainpanel.getComponentsGrid()[4]);
        this.mainpanel.getMainpanel().add(this.cmbBoxSexo, this.mainpanel.getComponentsGrid()[5]);

    }

    private void cmbBoxResponsavelItemEvent(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){

            }
        }
    }

    private void cmbBoxSexoItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){

            }
        }
    }

}
