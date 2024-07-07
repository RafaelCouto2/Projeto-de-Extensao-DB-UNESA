package com.extensionproject.app.gui.main.contents.pagamento.gui;

import com.extensionproject.app.domain.pagamento.Pagamentos;
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
    private PagamentoTableLabels plblInfo;


    private final JPanel mainpanel;
    private GridBagConstraints tableGrid;
    private GridBagConstraints[] componentsGrid;
    private Pagamentos pagamento;


    private void iniComponents() {
        this.pagamento = new Pagamentos();
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
        this.plblInfo = new PagamentoTableLabels(this);
        this.plblInfo.startLbls();
        this.pspnDate = new PagamentoPanelSpnDate(this);
        this.pspnDate.startDateSpn();
        this.ppagamentoTable.tableMouseListener();

        this.mainpanel.setBackground(new Color(241, 239, 249));
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
        this.tableGrid.insets = new Insets(5,5,10,2);
        this.componentsGrid = new GridBagConstraints[16];
        for (int i = 0; i < 16; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 2 || i == 4 || i == 8 || i == 12 || i == 13 || i == 14 || i == 15) ? 2 : 1;
        }
        this.componentsGrid[13].fill = GridBagConstraints.BOTH;
        this.componentsGrid[0].insets = new Insets(6, 3, -18, 613); // ID GRID
        this.componentsGrid[1].insets = new Insets(6, 55, -18, 315); // RESPONSAVEL GRID
        this.componentsGrid[2].insets = new Insets(1, 55, 15, 315); // ALUNO GRID
        this.componentsGrid[3].insets = new Insets(6, 395, -18, 220); // VALOR PAGAMENTO GRID REAIS
        this.componentsGrid[4].insets = new Insets(20, 486, 16, 18); // BTNREGISTRAR GRID
        this.componentsGrid[5].insets = new Insets(6, 444, -18, 180); // VALOR PAGAMENTO GRID CENTAVOS
        this.componentsGrid[6].insets = new Insets(1, 5, 20, 610); // ID LABEL GRID
        this.componentsGrid[7].insets = new Insets(1, 55, 20, 300); // RESPONSAVEL LABEL GRID
        this.componentsGrid[8].insets = new Insets(8, 55, 65, 300); // ALUNO LABEL GRID
        this.componentsGrid[9].insets = new Insets(6, 370, -16, 220); // VALOR PAGAMENTO R$ LABEL GRID
        this.componentsGrid[10].insets = new Insets(6, 436, -16, 180); // VALOR PAGAMENTO ',' LABEL GRID
        this.componentsGrid[11].insets = new Insets(6, 480, -18, 55); // SWITCH MODE CHECKBOX GRID
        this.componentsGrid[12].insets = new Insets(20, 486, 16, 18); // BTNDELETAR GRID
        this.componentsGrid[13].insets = new Insets(25, 390, 25, 180); //DAY GRID
        this.componentsGrid[14].insets = new Insets(25, 350, 25, 220); //DATA LABEL GRID
        this.componentsGrid[15].insets = new Insets(1, 1, 15, 610); //BTN REFRESH GRID
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

    public Pagamentos getPagamento() {
        return this.pagamento;
    }

    public PagamentoPanelButtons getPbtns() {
        return this.pbtns;
    }

    public PagamentoPanelCmbBoxes getPcmbFields() {
        return this.pcmbFields;
    }

    public PagamentoTableLabels getPlblInfo() {
        return plblInfo;
    }
}

