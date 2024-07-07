package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.gui.main.contents.pagamento.TableMouseListenerEvents;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.function.Consumer;

public class PagamentoPanelButtons {

    private PagamentoPanel mainpanel;
    private JButton btnRegistrar, btnDeletar, btnAtualizar, btnRefresh;
    private JCheckBox switchMode;

    public PagamentoPanelButtons(PagamentoPanel mainpanel){

        this.mainpanel = mainpanel;

    }

    public void startBtns() {
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
            addActionListener(e -> mainpanel.getPpagamentoTable().updateTable());
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
                        mainpanel.getPtxtFields().getTxtFields()[i].setBackground( (!bool)? Color.gray.brighter(): Color.LIGHT_GRAY.brighter());
                        mainpanel.getPtxtFields().getTxtFields()[i].setText((bool && i >= 3)?"00":"");
                        mainpanel.getPspnDate().getSpnDate().setBackground( (!bool)? Color.gray.brighter(): Color.LIGHT_GRAY.brighter());
                        if (bool) {
                            if (i == 0) {
                                //nextId.accept(1); //NEXT ID IF AUTO ADD ROW IS DEACTIVATED.
                                nextId_.accept(1);
                                mainpanel.getPcmbFields().getCmbFields()[i].setSelectedItem(null);
                                mainpanel.getPcmbFields().getCmbFields()[i + 1].setSelectedItem(null);
                            }
                            if (i < 2) {
                                mainpanel.getPcmbFields().getCmbFields()[i].setBackground(new Color(187, 248, 182));
                            }
                        } else {
                            if(i < 2) mainpanel.getPcmbFields().getCmbFields()[i].setBackground(new Color(248, 182, 182));
                            mainpanel.getPtxtFields().getTxtFields()[0].setText("");
                        }
                        mainpanel.getPtxtFields().getTxtFields()[i].setEditable(bool);
                    }
                    mainpanel.getPspnDate().getSpnDate().setEnabled(bool);
                    mainpanel.getPpagamentoTable().getPagamentoTable().setEnabled(!bool);
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
            this.mainpanel.getMainpanel().add(this.btnRegistrar, this.mainpanel.getComponentsGrid()[4]);
            this.mainpanel.getMainpanel().add(this.switchMode, this.mainpanel.getComponentsGrid()[11]);
            this.mainpanel.getMainpanel().add(this.btnDeletar, this.mainpanel.getComponentsGrid()[12]);
            this.mainpanel.getMainpanel().add(this.btnRefresh, this.mainpanel.getComponentsGrid()[15]);
        };
        addbtns.accept(1);
    }

    Consumer<Integer> nextId_ = lamb -> {
        if(this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getRowCount() > 0) {
            for(int i = this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getRowCount(); i > 0; i--){
                if(this.mainpanel.getPpagamentoTable().getPagamentoTable().getValueAt(i - 1, 0) != null){
                    this.mainpanel.getPtxtFields().getTxtFields()[0].setText(String.valueOf(Integer.parseInt(this.mainpanel.getPpagamentoTable().getPagamentoTable().getValueAt(i - 1, 0).toString()) + 1));
                    this.mainpanel.getPpagamentoTable().setActualPgId(Integer.parseInt(this.mainpanel.getPpagamentoTable().getPagamentoTable().getValueAt(i - 1, 0).toString()));
                    break;
                } else this.mainpanel.getPagamento().setId_pagamento("DEFAULT");
            }
        }
    };

    private void btnRegistrarActionEvent(ActionEvent evt) {
        boolean txtFilled = false;
        for (JTextField txtField : mainpanel.getPtxtFields().getTxtFields()) {
            txtFilled = !txtField.getText().isBlank();
        }
        if (this.mainpanel.getPtxtFields().getTxtFields()[0].getText().isBlank()){
            this.mainpanel.getPagamento().setId_pagamento("DEFAULT");
        } else this.mainpanel.getPagamento().setId_pagamento(this.mainpanel.getPtxtFields().getTxtFields()[0].getText());

        this.mainpanel.getPagamento().setData_pagamento(new SimpleDateFormat("dd/MM/yyyy").format(this.mainpanel.getPspnDate().getSpnDate().getValue()));
        if(txtFilled && this.mainpanel.getPagamento().hasValues()) {
            try (Statement statement = FactoryConnection.createStatement()) {

                statement.executeUpdate("insert into `extpj`.`pagamento` values (" + this.mainpanel.getPagamento().getId_pagamento() + ", "
                        + this.mainpanel.getPagamento().getId_responsavel() +  ", " + this.mainpanel.getPagamento().getId_alunoreferente() +
                        ", " + this.mainpanel.getPtxtFields().getTxtFields()[3].getText() + "." + this.mainpanel.getPtxtFields().getTxtFields()[4].getText()  +
                        ", " + "STR_TO_DATE('" + this.mainpanel.getPagamento().getData_pagamento() + "', '%d/%m/%Y')" + ");");
                LoggerManager.getClassLog(PagamentoPanel.class).info(": NOVO PAGAMENTO REGRISTRADO!");
                this.mainpanel.getPpagamentoTable().updateTable();
            } catch (SQLException e) {
                LoggerManager.getClassLog(PagamentoPanel.class).error(": NÃO FOI POSSÍVEL REGISTRAR UM NOVO PAGAMENTO! " + e.getMessage());
            }
        }
    }

    private void btnDeletarActionEvent(ActionEvent evt){

        if (TableMouseListenerEvents.hasSelected() && this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getValueAt(0,0) != null) {
            try (Statement statement = FactoryConnection.createStatement()) {
                statement.executeUpdate("delete from `extpj`.`pagamento` where `id_pagamento` = " + this.mainpanel.getPtxtFields().getTxtFields()[0].getText() + ";");
                this.mainpanel.getPpagamentoTable().updateTable();
                if(this.mainpanel.getPpagamentoTable().getActualPgId() > Integer.parseInt(this.mainpanel.getPtxtFields().getTxtFields()[0].getText()) || this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getValueAt(0,0) == null){
                    String call = "{call reset_autoincrement('pagamento', 'id_pagamento')}";
                    try(CallableStatement stmt = FactoryConnection.getConn().prepareCall(call)){
                        stmt.execute();
                        LoggerManager.getClassLog(PagamentoPanel.class).info("IDs DE PAGAMENTOS RESETADOS.");
                    }
                    this.mainpanel.getPpagamentoTable().setActualPgId.accept(1);
                }
                LoggerManager.getClassLog(PagamentoPanel.class).info(": REGISTRO DE PAGAMENTO DELETADO COM SUCESSO!");
            } catch (SQLException e) {
                LoggerManager.getClassLog(PagamentoPanel.class).error(e.getMessage());
            }
        } else LoggerManager.getClassLog(PagamentoPanel.class).error(": NÃO FOI POSSÍVEL DELETAR O REGISTRO DE PAGAMENTO.");
    }

    public JCheckBox getSwitchMode() {
        return this.switchMode;
    }

    public JButton getBtnAtualizar() {
        return this.btnAtualizar;
    }

    public JButton getBtnDeletar() {
        return this.btnDeletar;
    }

    public JButton getBtnRefresh() {
        return this.btnRefresh;
    }

    public JButton getBtnRegistrar() {
        return this.btnRegistrar;
    }

    public Consumer<Integer> getNextId_() {
        return nextId_;
    }

}
