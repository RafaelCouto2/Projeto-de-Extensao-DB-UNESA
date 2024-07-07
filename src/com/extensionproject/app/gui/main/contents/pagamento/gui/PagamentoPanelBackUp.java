package com.extensionproject.app.gui.main.contents.pagamento.gui;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.domain.pagamento.Pagamentos;
import com.extensionproject.app.general.TableRequests;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.TableMouseListenerEvents;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Consumer;

public class PagamentoPanelBackUp {

    private JButton btnRegistrar, btnDeletar, btnAtualizar, btnRefresh;
    private JCheckBox switchMode;
    private JTextField[] txtFields;
    private JComboBox<Object>[] cmbFields;
    private JLabel[] lblInfo;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private final JPanel mainpanel;
    private GridBagConstraints tableGrid;
    private GridBagConstraints[] componentsGrid;
    private Consumer<Integer> resetcmbField;
    private Pagamentos pagamento;
    private JSpinner spnDate;
    private int actualRow;
    private int actualPgId;

    private void iniComponents() {
        this.pagamento = new Pagamentos();
        this.setLoyout();
        this.startDefaultGridBagConstraints();
        this.startTable();
        this.iniPn();
        this.startTxtFields();
        this.startCmbFields();
        this.startBtns();
        this.startLbls();
        this.startDateSpn();
        this.tableMouseListener();
        this.mainpanel.setBackground(new Color(241, 239, 249));
        this.mainpanel.setVisible(true);
    }

    public PagamentoPanelBackUp(JPanel panel) {
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

    private void startTable() {

        TableRequests.pagamentoTableRequest(new String[] {"select `id_pagamento`,`id_responsavel`,`id_alunoreferente`,`valor_mensal`,DATE_FORMAT(`data_pagamento`, '%d/%m/%Y') as `data_pagamento` from `extpj`.`pagamento`;",
                "select `id_responsavel`,`nome` from `extpj`.`responsavel`;", "select * from `extpj`.`aluno`;"});
        this.pagamentoTable = new JTable(TableRequests.getResultsSetData(0),
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
            txtFields[1].setVisible(false);
            txtFields[2].setVisible(false);
        };
        addfields.accept(1);
    }

    private void startBtns() {
        this.btnRegistrar = new JButton() {{
            setFont(new Font("Unispace", Font.BOLD, 11));
            setText("<html>Registrar<br>pagamento</html>".toUpperCase());
            setEnabled(false);
            setVisible(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addActionListener(e -> btnRegistrarActionEvent(e));
        }};
        this.btnDeletar = new JButton(){{
            setFont(new Font("Unispace", Font.BOLD, 11));
            setText("<html>Deletar registro<br>de pagamento</html>".toUpperCase());
            addActionListener(e-> btnDeletarActionEvent(e));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }};

        this.btnRefresh = new JButton(){{
            setFont(new Font("Unispace", Font.BOLD, 11));
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(".//main//resources//imgs//refresh_icon.png")));
            setIcon(icon);
            addActionListener(e -> updateTable());
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
                        txtFields[i].setBackground( (!bool)? Color.gray.brighter(): Color.LIGHT_GRAY.brighter());
                        txtFields[i].setText((bool && i >= 3)?"00":"");
                        spnDate.setBackground( (!bool)? Color.gray.brighter(): Color.LIGHT_GRAY.brighter());
                        if (bool) {
                            if (i == 0) {
                                //nextId.accept(1); //NEXT ID IF AUTO ADD ROW IS DEACTIVATED.
                                nextId_.accept(1);
                                cmbFields[i].setSelectedItem(null);
                                cmbFields[i + 1].setSelectedItem(null);
                            }
                            if (i < 2) {
                                cmbFields[i].setBackground(new Color(187, 248, 182));
                            }
                        } else {
                            if(i < 2) cmbFields[i].setBackground(new Color(248, 182, 182));
                            txtFields[0].setText("");
                        }
                        txtFields[i].setEditable(bool);
                    }
                    spnDate.setEnabled(bool);
                    pagamentoTable.setEnabled(!bool);
                };
                if (switchMode.isSelected()) {
                    b.accept(true);
                } else {
                    b.accept(false);
                }
            });
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }};
        Consumer<Integer> addbtns = lamb -> {
            this.mainpanel.add(this.btnRegistrar, this.componentsGrid[4]);
            this.mainpanel.add(this.switchMode, this.componentsGrid[11]);
            this.mainpanel.add(this.btnDeletar, this.componentsGrid[12]);
            this.mainpanel.add(this.btnRefresh, this.componentsGrid[15]);
        };
        addbtns.accept(1);
    }

    private void startLbls() {
        this.lblInfo = new JLabel[6];
        for(int i = 0; i < 6; i++){
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
        this.lblInfo[5].setText("DATA:");
        Consumer<Integer> addlbls = lamb -> {
            for(int i = 0; i < this.lblInfo.length; i++) {
                this.mainpanel.add(this.lblInfo[i], this.componentsGrid[i+6]);
            }
            this.mainpanel.add(this.lblInfo[5], this.componentsGrid[14]);
        };
        addlbls.accept(1);
    }

    private void startCmbFields(){
        this.cmbFields = new JComboBox[2];
        for (int l = 0; l < 2; l++) {
            int cmbIndx = l;
            this.cmbFields[l] = new JComboBox<>(){{
                for (int i = 0; i < TableRequests.getResultsSetData(cmbIndx+1).size(); i++){
                    //O ACRESCENTADOR DE ID AO NOME DO RESPONSÁVEL E ALUNO FICA AQUI.
                    addItem(TableRequests.getResultsSetData(cmbIndx+1).get(i).get((cmbIndx == 0)? 0:1) + ": " +
                            TableRequests.getResultsSetData(cmbIndx+1).get(i).get((cmbIndx == 0)? 1:2));

                    setSelectedItem(null);
                    setEditable(false);
                    //setEnabled(false); //THOSE OPTIONS CHANGES THE BACKGROUND.
                    //setBackground(Color.LIGHT_GRAY.brighter());
                    setBackground(new Color(248, 182, 182));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }};
            this.resetcmbField = (reset) -> {
                switch (reset){
                    case 0:

                        break;
                    case 1:
                        this.cmbFields[1].removeAllItems();
                        for(int i = 0; i < TableRequests.getResultsSetData(2).size(); i++){
                            this.cmbFields[1].addItem(TableRequests.getResultsSetData(2).get(i).get(2));
                        }
                        this.cmbFields[1].setSelectedItem(null);
                        break;
                }
            };
        this.mainpanel.add(this.cmbFields[cmbIndx], this.componentsGrid[cmbIndx+1]);
        }
        this.cmbFields[0].addItemListener(this::cmbResponsavelItemListener);
        this.cmbFields[1].addItemListener(this::cmbAlunoItemListener);
    }

    Consumer<Integer> nextId = lamb -> {
        if(this.pagamentoTable.getModel().getRowCount() > 0) {
            txtFields[0].setText(String.valueOf(Integer.parseInt(pagamentoTable.getValueAt(
                    pagamentoTable.getRowCount() - 1, 0).toString()) + 1));
        }
    };

    Consumer<Integer> nextId_ = lamb -> {
        if(this.pagamentoTable.getModel().getRowCount() > 0) {
            for(int i = pagamentoTable.getModel().getRowCount(); i > 0; i--){
                if(pagamentoTable.getValueAt(i - 1, 0) != null){
                    txtFields[0].setText(String.valueOf(Integer.parseInt(pagamentoTable.getValueAt(i - 1, 0).toString()) + 1));
                    this.actualPgId = Integer.parseInt(pagamentoTable.getValueAt(i - 1, 0).toString());
                    break;
                } else this.pagamento.setId_pagamento("DEFAULT");
            }
        }
    };

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

    private void startDateSpn(){
        SpinnerDateModel spinModel = new SpinnerDateModel();
        this.spnDate = new JSpinner(spinModel);
        this.spnDate.setEditor(new JSpinner.DateEditor(spnDate, "dd/MM/yyyy"));
        this.spnDate.setVisible(true);
        this.spnDate.setBackground(Color.gray.brighter());
        this.spnDate.setEnabled(false);
        this.mainpanel.add(this.spnDate, this.componentsGrid[13]);
    }

    private void updateTable() {
        this.startTable();
        if(this.switchMode.isSelected()){
            this.pagamentoTable.setEnabled(false);
        }
        this.tableMouseListener();
        //nextId.accept(1); //NEXT ID IF AUTO ADD ROW IS DEACTIVATED.
        nextId_.accept(1);
        addEmptyRow.accept(1);
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setViewportView(this.pagamentoTable);
    }

    private void tableMouseListener() {
        this.pagamentoTable.addMouseListener(new TableMouseListenerEvents(this.pagamentoTable, this.txtFields, this.cmbFields, this.spnDate, this.pagamento));
    }

    private boolean ignoreFireEvent;

    private void cmbResponsavelItemListener(ItemEvent e){
        this.ignoreFireEvent = true;
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == (this.cmbFields[0])) {
                int[] responsavelIndx = new int[TableRequests.getResultsSetData(1).size()];
                for (int pfv = 0; pfv < TableRequests.getResultsSetData(1).size(); pfv++){
                    responsavelIndx[pfv] = (int) TableRequests.getResultsSetData(1).get(pfv).get(0);
                }
                this.resetcmbField.accept(1);
                for (int i = 0; i < TableRequests.getResultsSetData(1).size(); i++) {
                    if(i == this.cmbFields[0].getSelectedIndex()) {
                        for (int c = 0; c < TableRequests.getResultsSetData(2).size(); c++) {
                            if (Integer.parseInt(TableRequests.getResultsSetData(2).get(c).get(0).toString()) != responsavelIndx[i]) {
                                this.cmbFields[1].removeItem(TableRequests.getResultsSetData(2).get(c).get(2));
                            }
                        }
                        this.pagamento.setId_responsavel(TableRequests.getResultsSetData(1).get(i).get(0).toString());
                        break;
                    }
                }
                if(!this.switchMode.isSelected()){
                    if(!TableMouseListenerEvents.ignoreTableClick) {

                        this.updateTable();
                        int actualRow = this.pagamentoTable.getRowCount();
                        for (int l = actualRow - 1; l >= 0; l--) {
                            String tableItem = this.pagamentoTable.getModel().getValueAt(l, 1).toString();
                            String selectedItem = this.cmbFields[0].getModel().getSelectedItem().toString();
                            if (!selectedItem.equals(tableItem)) {
                                ((DefaultTableModel) this.pagamentoTable.getModel()).removeRow(l);
                            }
                        }
                    }
                }
            }
            TableMouseListenerEvents.ignoreTableClick = false;
        }
        this.cmbFields[1].dispatchEvent(e);
        this.ignoreFireEvent = false;
    }

    private void cmbAlunoItemListener(ItemEvent i){
        if(!this.ignoreFireEvent) {
            if (i.getStateChange() == ItemEvent.SELECTED) {
                if (i.getSource() == this.cmbFields[1]) {
                    for(int l = 0; l < TableRequests.getResultsSetData(1).size(); l++) {
                        for (int c = 0; c < TableRequests.getResultsSetData(2).size(); c++) {
                            if (TableRequests.getResultsSetData(2).get(c).get(2).equals(this.cmbFields[1].getSelectedItem()) &&
                                    TableRequests.getResultsSetData(2).get(c).get(0) == TableRequests.getResultsSetData(1).get(l).get(0)) {

                                this.pagamento.setId_alunoreferente(TableRequests.getResultsSetData(2).get(c).get(1).toString());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void btnRegistrarActionEvent(ActionEvent evt) {
        boolean txtFilled = false;
        for (JTextField txtField : txtFields) {
            txtFilled = !txtField.getText().isBlank();
        }
        if (this.txtFields[0].getText().isBlank()){
            this.pagamento.setId_pagamento("DEFAULT");
        } else this.pagamento.setId_pagamento(this.txtFields[0].getText());

        this.pagamento.setData_pagamento(new SimpleDateFormat("dd/MM/yyyy").format(this.spnDate.getValue()));
        if(txtFilled && this.pagamento.hasValues()) {
            try (Statement statement = FactoryConnection.createStatement()) {

                statement.executeUpdate("insert into `extpj`.`pagamento` values (" + this.pagamento.getId_pagamento() + ", "
                        + this.pagamento.getId_responsavel() +  ", " + this.pagamento.getId_alunoreferente() +
                        ", " + this.txtFields[3].getText() + "." + this.txtFields[4].getText()  +
                        ", " + "STR_TO_DATE('" + this.pagamento.getData_pagamento() + "', '%d/%m/%Y')" + ");");
                LoggerManager.getClassLog(PagamentoPanelBackUp.class).info(": NOVO PAGAMENTO REGRISTRADO!");
                this.updateTable();
            } catch (SQLException e) {
                LoggerManager.getClassLog(PagamentoPanelBackUp.class).error(": NÃO FOI POSSÍVEL REGISTRAR UM NOVO PAGAMENTO! " + e.getMessage());
            }
        }
    }

    private void btnDeletarActionEvent(ActionEvent evt){

        if (TableMouseListenerEvents.hasSelected() && this.pagamentoTable.getModel().getValueAt(0,0) != null) {
            try (Statement statement = FactoryConnection.createStatement()) {
                statement.executeUpdate("delete from `extpj`.`pagamento` where `id_pagamento` = " + this.txtFields[0].getText() + ";");
                this.updateTable();
                if(this.actualPgId > Integer.parseInt(this.txtFields[0].getText()) || this.pagamentoTable.getModel().getValueAt(0,0) == null){
                    String call = "{call reset_autoincrement('pagamento', 'id_pagamento')}";
                    try(CallableStatement stmt = FactoryConnection.getConn().prepareCall(call)){
                        stmt.execute();
                        LoggerManager.getClassLog(PagamentoPanelBackUp.class).info("IDs DE PAGAMENTOS RESETADOS.");
                    }
                    this.setActualPgId.accept(1);
                }
                LoggerManager.getClassLog(PagamentoPanelBackUp.class).info(": REGISTRO DE PAGAMENTO DELETADO COM SUCESSO!");
            } catch (SQLException e) {
                LoggerManager.getClassLog(PagamentoPanelBackUp.class).error(e.getMessage());
            }
        } else LoggerManager.getClassLog(PagamentoPanelBackUp.class).error(": NÃO FOI POSSÍVEL DELETAR O REGISTRO DE PAGAMENTO.");
    }


    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnDeletar() {
        return btnDeletar;
    }

    public JCheckBox getSwitchMode() {
        return switchMode;
    }

    public JTextField[] getTxtFields() {
        return txtFields;
    }

    public JComboBox<Object>[] getCmbFields() {
        return cmbFields;
    }

    public JLabel[] getLblInfo() {
        return lblInfo;
    }

    public JTable getPagamentoTable() {
        return pagamentoTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
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

    public JSpinner getSpnDate() {
        return spnDate;
    }
}
