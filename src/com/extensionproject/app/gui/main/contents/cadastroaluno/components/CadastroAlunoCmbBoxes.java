package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class CadastroAlunoCmbBoxes {
    private CadastroAluno mainpanel;
    private JComboBox<Object> cmbAluno, cmbResponsavel, cmbSexo;
    public CadastroAlunoCmbBoxes(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }
    public void startCmbx(){
        this.cmbAluno = new JComboBox<>();
        this.cmbResponsavel = new JComboBox<>();
        this.cmbSexo = new JComboBox<>();

        this.cmbAluno.setCursor(Utils.handcursor);
        this.cmbResponsavel.setCursor(Utils.handcursor);
        this.cmbSexo.setCursor(Utils.handcursor);
        this.cmbAluno.setFont(Utils.jetmono13);
        this.cmbResponsavel.setFont(Utils.jetmono13);
        this.cmbSexo.setFont(Utils.jetmono13);

        this.cmbAluno.setBackground(Color.white);
        this.cmbSexo.setBackground(Color.white);
        this.cmbResponsavel.setBackground(Utils.cmbRed);

        this.cmbAluno.addItemListener(this::cmbBoxAlunoItemEvent);
        this.cmbResponsavel.addItemListener(this::cmbBoxReponsavelItemEvent);
        this.cmbSexo.addItemListener(this::cmbBoxSexoItemListener);

        this.cmbSexo.addItem(new String("Masculino"));
        this.cmbSexo.addItem(new String("Feminino"));

        this.cmbAluno.setSelectedItem(null);
        this.cmbResponsavel.setSelectedItem(null);
        this.cmbSexo.setSelectedItem(null);
        this.cmbResponsavel.setEnabled(false);
        //this.cmbAluno.setEditable(true);

        this.mainpanel.getMainpanel().add(this.cmbAluno, this.mainpanel.getComponentsGrid()[4]);
        this.mainpanel.getMainpanel().add(this.cmbResponsavel, this.mainpanel.getComponentsGrid()[2]);
        this.mainpanel.getMainpanel().add(this.cmbSexo, this.mainpanel.getComponentsGrid()[5]);

    }

    private void cmbBoxAlunoItemEvent(ItemEvent e){

    }

    private void cmbBoxReponsavelItemEvent(ItemEvent e){

    }

    private void cmbBoxSexoItemListener(ItemEvent e){

    }

    public CadastroAluno getMainpanel() {
        return this.mainpanel;
    }

    public JComboBox<Object> getCmbAluno() {
        return this.cmbAluno;
    }

    public JComboBox<Object> getCmbResponsavel() {
        return this.cmbResponsavel;
    }

    public JComboBox<Object> getCmbSexo() {
        return this.cmbSexo;
    }
}
