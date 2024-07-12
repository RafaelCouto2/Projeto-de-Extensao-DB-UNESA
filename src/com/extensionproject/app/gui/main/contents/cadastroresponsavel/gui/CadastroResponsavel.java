package com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui;

import com.extensionproject.app.dao.cadastrodao.responsaveldao.ResponsavelDAO;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTxtFields;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.ResponsavelTable;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class CadastroResponsavel {
    private JPanel mainpanel;
    private GridBagConstraints[] componentsGrid;
    private GridBagConstraints cadastrogrid;
    private ResponsavelDAO responsavelDAO;
    private ResponsavelTable responsavelTable;
    private CadastroResponsavelTxtFields txtFields;

    private void initComponents (){
        this.setMainPanelLayout();
        this.startComponentsGrid();
        this.responsavelDAO = new ResponsavelDAO(this);
        this.responsavelTable = new ResponsavelTable(this);
        this.responsavelTable.startTable();
        this.responsavelTable.iniPn();
        this.txtFields = new CadastroResponsavelTxtFields(this);
        this.txtFields.startNumberTxtFields();

    }
    public CadastroResponsavel(JPanel mainpanel) {
        this.mainpanel = mainpanel;
        this.mainpanel.removeAll();
        this.initComponents();

        this.mainpanel.setBackground(new Color(241, 239, 249));
        this.mainpanel.revalidate();
        this.mainpanel.setVisible(true);
    }

    private void startComponentsGrid(){
        this.cadastrogrid = new GridBagConstraints();
        this.cadastrogrid.fill = GridBagConstraints.BOTH;
        this.cadastrogrid.gridx = 0;
        this.cadastrogrid.gridy = 0;
        this.cadastrogrid.gridwidth = 1;
        this.cadastrogrid.gridheight = 1;
        this.cadastrogrid.weightx = 1.0;
        this.cadastrogrid.weighty = 1.0;
        this.cadastrogrid.insets = new Insets(5,5,10,18);

        this.componentsGrid = new GridBagConstraints[5];
        for(int i = 0; i < 5; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 1)? 2: 1;
        }
        int[][] insets = {
                {5,5,10,700}, //ID FIELD
                {5,100,25,400}, //TELEFONE FIELD
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };

        for(int g = 0; g < 5; g++){
            this.componentsGrid[g].insets = new Insets(insets[g][0], insets[g][1],insets[g][2],insets[g][3]);
        }

    }

    private void setMainPanelLayout(){
        this.mainpanel.setLayout(new GridBagLayout());
    }

    public ResponsavelDAO getResponsavelDAO() {
        return this.responsavelDAO;
    }

    public GridBagConstraints getCadastrogrid() {
        return this.cadastrogrid;
    }

    public GridBagConstraints[] getComponentsGrid() {
        return this.componentsGrid;
    }

    public JPanel getMainpanel() {
        return this.mainpanel;
    }

    public ResponsavelTable getResponsavelTable() {
        return this.responsavelTable;
    }
}
