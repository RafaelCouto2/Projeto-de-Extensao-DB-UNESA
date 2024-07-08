package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.DAO.pagamentoDAO.PagamentoDAO;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.events.TableMouseListenerEvents;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;


public class PagamentoPanelTable {

    private int actualRow;
    private int actualPgId;
    private final PagamentoPanel mainpanel;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private TableMouseListenerEvents tableMouseListenerEvents;
    private final PagamentoDAO pagamentoDAO;

    public PagamentoPanelTable(PagamentoPanel mainpanel) {
        this.mainpanel = mainpanel;
        this.pagamentoDAO = new PagamentoDAO();
    }

    public void startTable() {

//        TableRequests.pagamentoTableRequest(new String[] {"select `id_pagamento`,`id_responsavel`,`id_alunoreferente`,`valor_mensal`,DATE_FORMAT(`data_pagamento`, '%d/%m/%Y') as `data_pagamento` from `extpj`.`pagamento`;",
//                "select `id_responsavel`,`nome` from `extpj`.`responsavel`;", "select * from `extpj`.`aluno`;"});

        this.pagamentoDAO.iniTableData();
        this.pagamentoTable = new JTable(this.getPagamentoDAO().getTablerequest().getResultsSetData(0),
                new Vector<>(Arrays.asList("ID PG", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR", "DATA DO PAGAMENTO")));
        //this.pagamentoTable = new JTable(TableRequests.getRowsData(), new Object[]{"ID RESPONSÁVEL", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR", "DATA DO PAGAMENTO"});

        this.addEmptyRow.accept(1); //AUTO ADD ROW.
        this.setActualRow.accept(1);
        this.setActualPgId.accept(1);
        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(Color.white);
        this.pagamentoTable.setFont(Utils.jetmono);
        this.pagamentoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pagamentoTable.getColumn("ID PG").setMaxWidth(55);
        this.pagamentoTable.getColumn("VALOR").setMaxWidth(60);
        this.pagamentoTable.getColumn("DATA DO PAGAMENTO").setMaxWidth(140);
        this.pagamentoTable.getColumn("RESPONSÁVEL").setMaxWidth(180);
        this.pagamentoTable.getColumn("ALUNO REFERENTE").setMaxWidth(180);
        this.pagamentoTable.setForeground(new Color(66,66,66));
        this.pagamentoTable.setGridColor(new Color(210,208,216));
        this.pagamentoTable.setRowHeight(20);
        this.pagamentoTable.getTableHeader().setFont(Utils.jetmono);
        this.pagamentoTable.getTableHeader().setForeground(Color.white);
        this.pagamentoTable.getTableHeader().setBackground(new Color(16, 124, 65));
        this.pagamentoTable.setVisible(true);

    }

    public void iniPn() {

        this.mainpanel.getTableGrid().gridx = 0; // coluna 0
        this.mainpanel.getTableGrid().gridy = 0; // linha 0
        this.mainpanel.getTableGrid().gridwidth = 1; // 1 célula de largura
        this.mainpanel.getTableGrid().gridheight = 1; // 1 célula de altura
        this.mainpanel.getTableGrid().weightx = 1.0; // expansão horizontal
        this.mainpanel.getTableGrid().weighty = 1.0; // expansão vertical
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setVisible(true);
        this.scrollPane.setViewportView(this.pagamentoTable);
        this.scrollPane.setBackground(new Color(245, 245, 245));
        this.mainpanel.getMainpanel().add(this.scrollPane, this.mainpanel.getTableGrid());
    }

    public void updateTable() {
        this.startTable();
        if(this.mainpanel.getPbtns().getSwitchMode().isSelected()){
            this.pagamentoTable.setEnabled(false);
        }
        this.tableMouseListener();
        //nextId.accept(1); //NEXT ID IF AUTO ADD ROW IS DEACTIVATED.
        this.mainpanel.getPbtns().nextId_.accept(1);
        addEmptyRow.accept(1);
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setViewportView(this.pagamentoTable);
    }

    public void tableMouseListener() {
        //this.pagamentoTable.addMouseListener(new TableMouseListenerEvents(this.pagamentoTable, this.mainpanel.getPtxtFields().getTxtFields(), this.mainpanel.getPcmbFields().getCmbFields(), this.mainpanel.getPspnDate().getSpnDate(), this.mainpanel.getPagamento()));
        this.tableMouseListenerEvents = new TableMouseListenerEvents(this.pagamentoTable, this.mainpanel.getPtxtFields().getTxtFields(), this.mainpanel.getPcmbFields().getCmbFields(), this.mainpanel.getPspnDate().getSpnDate(), this.mainpanel.getPagamento());
        this.pagamentoTable.addMouseListener(tableMouseListenerEvents);
    }

    Consumer<Integer> addEmptyRow = lamb -> { //AUTO ROW ADDER IF ROWS < 20.
        if(this.pagamentoTable.getModel().getRowCount() < 20){
            for(int i = this.pagamentoTable.getModel().getRowCount(); i < 20; i++) {
                ((DefaultTableModel) this.pagamentoTable.getModel()).addRow(new Object[]{null,"","",null,""});
            }
        }
    };

    Consumer<Integer> setActualRow = lamb -> this.actualRow = this.pagamentoTable.getModel().getRowCount();
    Consumer<Integer> setActualPgId = lamb -> {
        if(this.pagamentoTable.getModel().getRowCount() > 0) {
            for(int i = pagamentoTable.getModel().getRowCount(); i > 0; i--){
                if(pagamentoTable.getValueAt(i - 1, 0) != null){
                    this.actualPgId = Integer.parseInt(pagamentoTable.getValueAt(i - 1, 0).toString());
                    break;
                }
            }
        }
    };

    public JTable getPagamentoTable() {
        return this.pagamentoTable;
    }

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public int getActualPgId() {
        return this.actualPgId;
    }

    public int getActualRow() {
        return this.actualRow;
    }


    public void setActualPgId(int actualPgId) {
        this.actualPgId = actualPgId;
    }

    public void setActualRow(int actualRow) {
        this.actualRow = actualRow;
    }

    public TableMouseListenerEvents getTableMouseListenerEvents() {
        return this.tableMouseListenerEvents;
    }

    public PagamentoDAO getPagamentoDAO() {
        return this.pagamentoDAO;
    }
}
