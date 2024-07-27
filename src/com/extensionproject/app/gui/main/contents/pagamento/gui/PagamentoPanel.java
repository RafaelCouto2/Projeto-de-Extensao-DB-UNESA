package com.extensionproject.app.gui.main.contents.pagamento.gui;

import com.extensionproject.app.domain.pagamento.Pagamento;
import com.extensionproject.app.gui.main.contents.pagamento.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class PagamentoPanel {

    private PagamentoPanelTxtFields ptxtFields;
    private PagamentoPanelTable ppagamentoTable;
    private PagamentoPanelSpnDate pspnDate;
    private PagamentoPanelButtons pbtns;
    private PagamentoPanelCmbBoxes pcmbFields;
    private PagamentoPanelLabels plblInfo;
    private final JPanel mainpanel;
    private GridBagConstraints tableGrid;
    private GridBagConstraints[] componentsGrid;
    private Pagamento pagamento;

    private void iniComponents() {
        this.pagamento = new Pagamento();
        this.setLoyout();
        this.startDefaultGridBagConstraints();

        this.ppagamentoTable = new PagamentoPanelTable(this);
        this.ppagamentoTable.startTable();
        this.ppagamentoTable.iniPn();
        this.ptxtFields = new PagamentoPanelTxtFields(this);
        this.ptxtFields.startTxtFields();
        this.pcmbFields = new PagamentoPanelCmbBoxes(this);
        this.pcmbFields.startCmbFields();
        this.pbtns = new PagamentoPanelButtons(this);
        this.pbtns.startBtns();
        this.plblInfo = new PagamentoPanelLabels(this);
        this.plblInfo.startLbls();
        this.pspnDate = new PagamentoPanelSpnDate(this);
        this.pspnDate.startDateSpn();
        this.ppagamentoTable.tableMouseListener();

        this.mainpanel.setBackground(new Color(241, 239, 249));
        this.mainpanel.revalidate();
        this.mainpanel.setVisible(true);

    }

    public PagamentoPanel(JPanel panel) {
        this.mainpanel = panel;
        this.mainpanel.removeAll();
        this.iniComponents();
    }

    private void startDefaultGridBagConstraints() {
        this.tableGrid = new GridBagConstraints();
        this.tableGrid.fill = GridBagConstraints.BOTH;
        this.tableGrid.insets = new Insets(5,5,10,14);
        this.componentsGrid = new GridBagConstraints[17];
        for (int i = 0; i < 17; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 2 || i == 4 || i == 8 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16) ? 2 : 1;
        }
        this.componentsGrid[13].fill = GridBagConstraints.BOTH;

        int[][] insets = {
                {6, 5, -18, 713}, // TXTID GRID
                {6, 60, -18, 415}, // CMBRESPONSAVEL GRID
                {1, 60, 15, 415}, // CMBALUNO GRID
                {6, 395, -18, 325}, // TXTVALOR PAGAMENTO GRID REAIS
                {20, 486, 16, 148}, // BTNREGISTRAR GRID
                {6, 444, -18, 280}, // TXTVALOR PAGAMENTO GRID CENTAVOS
                {1, 5, 20, 710}, // ID LABEL GRID
                {1, 55, 20, 400}, // RESPONSAVEL LABEL GRID
                {8, 55, 65, 400}, // ALUNO LABEL GRID
                {6, 370, -16, 320}, // VALOR PAGAMENTO R$ LABEL GRID
                {6, 436, -16, 280}, // VALOR PAGAMENTO ',' LABEL GRID
                {6, 480, -18, 65}, // SWITCH MODE CHECKBOX GRID
                {20, 486, 16, 28}, // BTNDELETAR GRID
                {25, 390, 25, 280}, //DATA GRID
                {25, 350, 25, 320}, //DATA LABEL GRID
                {1, 5, 15, 710}, //BTNREFRESH GRID
                {20, 620, 16, 18} //BTNATUALIZAR
        };

        for (int i = 0; i < 17; i++){
            this.componentsGrid[i].insets = new Insets(insets[i][0], insets[i][1], insets[i][2], insets[i][3]);
        }

    }

    private void setLoyout() {
        this.mainpanel.setLayout(new GridBagLayout());
    }

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public GridBagConstraints[] getComponentsGrid(){
        return this.componentsGrid;
    }

    public GridBagConstraints getTableGrid(){
        return this.tableGrid;
    }

    public PagamentoPanelTxtFields getPtxtFields() {
        return this.ptxtFields;
    }

    public PagamentoPanelTable getPpagamentoTable() {
        return this.ppagamentoTable;
    }

    public PagamentoPanelSpnDate getPspnDate() {
        return this.pspnDate;
    }

    public Pagamento getPagamento() {
        return this.pagamento;
    }

    public PagamentoPanelButtons getPbtns() {
        return this.pbtns;
    }

    public PagamentoPanelCmbBoxes getPcmbFields() {
        return this.pcmbFields;
    }

    public PagamentoPanelLabels getPlblInfo() {
        return plblInfo;
    }
}

