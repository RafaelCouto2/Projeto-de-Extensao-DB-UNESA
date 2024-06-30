package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;

public class PagamentoPanel {

    private JButton btnRegistrar, btnDeletar;
    private JCheckBox switchMode;
    private JTextField[] txtFields;
    private JLabel[] lblInfo;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private  JPanel mainpanel;
    private GridBagConstraints tableGrid;
    private GridBagConstraints[] componentsGrid;

    private void iniComponents() {
        this.setLoyout();
        this.startDefaultGridBagConstraints();
        this.startTable();
        this.iniPn();
        this.startTxtFields();
        this.startBtns();
        this.startLbls();
        this.tableMouseListener();
        this.mainpanel.setBackground(new Color(241, 239, 249));

        this.mainpanel.setVisible(true);
    }

    public PagamentoPanel(JPanel panel) {
        this.mainpanel = panel;
        this.iniComponents();
    }

    private void startDefaultGridBagConstraints() {
        this.tableGrid = new GridBagConstraints();
        this.tableGrid.fill = GridBagConstraints.BOTH;
        this.tableGrid.insets = new Insets(5,5,10,2);
        this.componentsGrid = new GridBagConstraints[13];
        for (int i = 0; i < 13; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 2 || i == 4 || i == 8 || i == 12) ? 2 : 1;
        }
        this.componentsGrid[0].insets = new Insets(6,7,-18,610); // ID GRID
        this.componentsGrid[1].insets = new Insets(6,60,-18,300); // RESPONSAVEL GRID
        this.componentsGrid[2].insets = new Insets(1,60,15,300); // ALUNO GRID
        this.componentsGrid[3].insets = new Insets(6, 395, -18, 220); // VALOR PAGAMENTO GRID REAIS
        this.componentsGrid[4].insets = new Insets(20,375, 15, 180); // BTNREGISTRAR GRID
        this.componentsGrid[5].insets = new Insets(6, 444, -18, 180); // VALOR PAGAMENTO GRID CENTAVOS
        this.componentsGrid[6].insets = new Insets(1,5,20,610); // ID LABEL GRID
        this.componentsGrid[7].insets = new Insets(1,60,20,300); // RESPONSAVEL LABEL GRID
        this.componentsGrid[8].insets = new Insets(5,60,60,300); // ALUNO LABEL GRID
        this.componentsGrid[9].insets = new Insets(6, 370, -16, 220); // VALOR PAGAMENTO R$ LABEL GRID
        this.componentsGrid[10].insets = new Insets(6, 436, -16, 180); // VALOR PAGAMENTO ',' LABEL GRID
        this.componentsGrid[11].insets = new Insets(6, 480, -18, 55); // SWITCH MODE CHECKBOX GRID
        this.componentsGrid[12].insets = new Insets(20,375, 15, 130); // BTNDELETAR GRID
    }

    private void setLoyout() {
        this.mainpanel.setLayout(new GridBagLayout());
    }

    private void startTable() {

        TableRequests.pagamentoTableRequest(new String[] {"select `id_responsavel`,`id_alunoreferente`,`valor_mensal`,DATE_FORMAT(`data_pagamento`, '%d-%m-%Y') as `data_pagamento` from `extpj`.`pagamento`;",
                "select `id_responsavel`,`nome` from `extpj`.`responsavel`;", "select * from `extpj`.`aluno`;"});
        this.pagamentoTable = new JTable(TableRequests.getResultsSetData(),
                new Vector<>(Arrays.asList("ID RESPONSÁVEL", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR", "DATA DO PAGAMENTO")));
        //this.pagamentoTable = new JTable(TableRequests.getRowsData(), new Object[]{"ID RESPONSÁVEL", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR", "DATA DO PAGAMENTO"});

        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(Color.white);
        this.pagamentoTable.setFont(Utils.jetmono);
        this.pagamentoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pagamentoTable.getColumn("ID RESPONSÁVEL").setMaxWidth(55);
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


    private void startTxtFields() {

        this.txtFields = new JTextField[5];
        for (int i = 0; i < 5; i++){
            this.txtFields[i] = new JTextField();
            this.txtFields[i].setFont(Utils.jetmono);
            this.txtFields[i].setEditable(false);
            this.txtFields[i].setBackground(Color.gray.brighter());
        }
        Consumer<Integer> addfields = f -> {
            for(int i = 0; i < this.txtFields.length; i++){
                this.mainpanel.add(this.txtFields[i], this.componentsGrid[ ((i == 4) ? 5 : i)]);
            }
        };
        addfields.accept(1);
    }

    private void startBtns() {
        this.btnRegistrar = new JButton() {{
            setFont(new Font("Unispace", Font.BOLD, 11));
            setText("<html>Registrar <br>pagamento</html>".toUpperCase());
            setEnabled(false);
            setVisible(false);
            addActionListener(e ->
                btnRegistrarActionEvent(e)
            );

        }};
        this.btnDeletar = new JButton(){{
            setFont(new Font("Unispace", Font.BOLD, 11));
            setText("<html>Deletar registro <br>de pagamento</html>\"".toUpperCase());
        }};
        this.switchMode = new JCheckBox(){{
            setText("<html>REGISTRAR<br>PAGAMENTOS?</html>");
            addActionListener(e -> {
                Consumer<Boolean> b = (bool) -> {
                    btnRegistrar.setEnabled(bool);
                    btnRegistrar.setVisible(bool);
                    btnDeletar.setEnabled(!bool);
                    btnDeletar.setVisible(!bool);
                    for (int i = 0; i < 5; i++) {
                        if(!bool) {
                            txtFields[i].setBackground(Color.gray.brighter());
                        } else {
                            txtFields[i].setBackground(Color.LIGHT_GRAY.brighter());
                        }
                        txtFields[i].setEditable(bool);
                        pagamentoTable.setEnabled(!bool);
                    }
                };
                if (switchMode.isSelected()) {
                    b.accept(true);
                } else {
                    b.accept(false);
                }
            });
        }};
        Consumer<Integer> addbtns = lamb -> {
            this.mainpanel.add(this.btnRegistrar, this.componentsGrid[4]);
            this.mainpanel.add(this.switchMode, this.componentsGrid[11]);
            this.mainpanel.add(this.btnDeletar, this.componentsGrid[12]);
        };
        addbtns.accept(1);
    }

    private void startLbls() {
        this.lblInfo = new JLabel[5];
        for(int i = 0; i < 5; i++){
            int swapI = i;
            this.lblInfo[i] = new JLabel(){{
                setFont(Utils.jetmono);
                switch(swapI){
                    case 3:
                        setForeground(new Color(37, 105, 21));
                        break;
                    case 4:
                        setFont(new Font("JetBrains Mono", Font.BOLD, 24));
                        break;
                }
            }};
        }
        this.lblInfo[0].setText("   ID:");
        this.lblInfo[1].setText("RESPONSÁVEL:");
        this.lblInfo[2].setText("ALUNO:");
        this.lblInfo[3].setText("R$:");
        this.lblInfo[4].setText(",");
        Consumer<Integer> addlbls = lamb -> {
            for(int i = 0; i < this.lblInfo.length; i++) {
                this.mainpanel.add(this.lblInfo[i], this.componentsGrid[i+6]);
            }
        };
        addlbls.accept(1);
    }

    private void updateTable() {
        this.startTable();
        this.tableMouseListener();
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setViewportView(this.pagamentoTable);
    }

    private void iniPn() {
        this.tableGrid.gridx = 0; // coluna 0
        this.tableGrid.gridy = 0; // linha 0
        this.tableGrid.gridwidth = 1; // 1 célula de largura
        this.tableGrid.gridheight = 1; // 1 célula de altura
        this.tableGrid.weightx = 1.0; // expansão horizontal
        this.tableGrid.weighty = 1.0; // expansão vertical
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setVisible(true);
        this.scrollPane.setViewportView(this.pagamentoTable);
        this.scrollPane.setBackground(new Color(245, 245, 245));
        this.mainpanel.add(this.scrollPane, tableGrid);
    }

    public void drawTableRect() {
        this.mainpanel.getGraphics().drawRect(this.scrollPane.getX(), this.scrollPane.getY(),
                this.scrollPane.getWidth(), this.scrollPane.getHeight());

    }

    public void drawFieldsRect() {
        this.mainpanel.getGraphics().drawRect(this.lblInfo[0].getX() - 2, this.lblInfo[0].getY() - 2, 600,110);
    }

    private void tableMouseListener() {
        this.pagamentoTable.addMouseListener(new TableMouseListenerEvents(this.pagamentoTable, this.txtFields));

    }

    private void btnRegistrarActionEvent(ActionEvent evt) {
        try {

            FactoryConnection.createStatement().executeUpdate("insert into `extpj`.`pagamento` values (DEFAULT, 2, 1, 105.45, '2024-06-29');");
            FactoryConnection.closeStatement();
            LoggerManager.getClassLog(PagamentoPanel.class).info(evt.getWhen() + ": NOVO PAGAMENTO REGRISTRADO!");
            this.updateTable();
        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoPanel.class).info(evt.getWhen() + ": NÃO FOI POSSÍVEL REGISTRAR UM NOVO PAGAMENTO.");
            LoggerManager.getClassLog(PagamentoPanel.class).info(e.getCause());
        }
    }
}
