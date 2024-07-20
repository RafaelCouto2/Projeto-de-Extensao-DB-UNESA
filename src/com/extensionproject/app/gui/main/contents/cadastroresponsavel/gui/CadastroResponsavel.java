package com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui;

import com.extensionproject.app.dao.cadastrodao.responsaveldao.ResponsavelDAO;
import com.extensionproject.app.domain.responsavel.Responsavel;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.*;

import javax.swing.*;
import java.awt.*;

public class CadastroResponsavel {
    private JPanel mainpanel;
    private GridBagConstraints[] componentsGrid;
    private GridBagConstraints cadastrogrid;
    private ResponsavelDAO responsavelDAO;
    private CadastroResponsavelTable cadastroResponsavelTable;
    private CadastroResponsavelTxtFields txtFields;
    private CadastroResponsavelButtons btnCadastro;
    private CadastroResponsavelCmbBoxes cmbBoxes;
    private CadastroResponsavelSpinner spnData;
    private CadastroResponsavelLabels lblInfo;
    private Responsavel resposavel;

    private void initComponents (){
        this.setMainPanelLayout();
        this.startComponentsGrid();
        this.resposavel = new Responsavel();
        this.responsavelDAO = new ResponsavelDAO(this);
        this.cadastroResponsavelTable = new CadastroResponsavelTable(this);
        this.cadastroResponsavelTable.startTable();
        this.cadastroResponsavelTable.iniPn();
        this.txtFields = new CadastroResponsavelTxtFields(this);
        this.txtFields.startNumberTxtFields();
        this.btnCadastro = new CadastroResponsavelButtons(this);
        this.btnCadastro.startBtns();
        this.cmbBoxes = new CadastroResponsavelCmbBoxes(this);
        this.cmbBoxes.starCmbx();
        this.spnData = new CadastroResponsavelSpinner(this);
        this.spnData.startSpnData();
        this.lblInfo = new CadastroResponsavelLabels(this);
        this.lblInfo.startLbls();

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

        this.componentsGrid = new GridBagConstraints[17];
        for(int i = 0; i < 17; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 1) || (i == 2) ||
                                           (i == 3) || (i == 6) ||
                                           (i == 8) || (i == 9) ||
                                          (i == 10) || (i == 14)||
                                          (i == 15) || (i == 16)? 2: 1;
        }
        this.componentsGrid[6].fill = GridBagConstraints.HORIZONTAL;
        int[][] insets = {
                {1,5,-16,713}, // ID FIELD
                {5,110,20,545}, // TELEFONE FIELD
                {5,60,20,660}, // TELEFONE DDD FIELD
                {1,5,16,710}, // BTN RELOAD
                {1,60,-20,415}, // CMBOX NOMES(RESPONSÁVEIS)
                {1,360,-20,300}, // CMBOX SEXO (RESPONSÁVEIS)
                {0,226,13,410}, // SPNDATA
                {1,480,-10,100}, // SWITCH
                {1,370,10,260}, // BTN DELETAR
                {1,370,10,260}, // BTN REGISTRAR
                {1,510,10,120}, //BTN EDITAR

                {2,7,29,713}, // LBL ID
                {2,60,29,415}, // LBL NOME
                {2,360,29,300}, // LBL SEXO
                {5,60,60,660}, // LBL DDD
                {5,110,60,545}, // LBL TEL
                {5,226,60,400} // LBL DATA
        };

        for(int g = 0; g < 17; g++){
            this.componentsGrid[g].insets = new Insets(insets[g][0], insets[g][1],insets[g][2],insets[g][3]);
        }

    }

    public void reloadComponentsProperties(){
        this.getResponsavelTable().reloadTable();
        this.getCmbBoxes().reloadReponsavelCombox();
        this.getCmbBoxes().getCmbBoxSexo().setSelectedItem(null);
        this.getTxtFields().reloadTxtFields();
        //this.getSpnData().todayDate();
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

    public CadastroResponsavelTable getResponsavelTable() {
        return this.cadastroResponsavelTable;
    }

    public CadastroResponsavelButtons getBtnCadastro() {
        return btnCadastro;
    }

    public CadastroResponsavelCmbBoxes getCmbBoxes() {
        return cmbBoxes;
    }

    public CadastroResponsavelSpinner getSpnData() {
        return spnData;
    }

    public CadastroResponsavelTxtFields getTxtFields() {
        return txtFields;
    }

    public Responsavel getResposavel() {
        return this.resposavel;
    }
}
