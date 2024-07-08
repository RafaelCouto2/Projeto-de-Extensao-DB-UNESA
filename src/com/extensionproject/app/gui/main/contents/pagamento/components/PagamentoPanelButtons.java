package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.dao.pagamentodao.PagamentoDAO;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

public class PagamentoPanelButtons {

    private PagamentoPanel mainpanel;
    private JButton btnRegistrar, btnDeletar, btnAtualizar, btnRefresh;
    private JCheckBox switchMode;
    private PagamentoDAO pagamentoDAO;

    public PagamentoPanelButtons(PagamentoPanel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startBtns() {
        pagamentoDAO = new PagamentoDAO();
        this.btnRegistrar = new JButton() {{
            setFont(Utils.unibold);
            setText("<html>Registrar<br>pagamento</html>".toUpperCase());
            setEnabled(false);
            setVisible(false);
            setCursor(Utils.handcursor);
            addActionListener(e -> btnRegistrarActionEvent());
        }};
        this.btnDeletar = new JButton() {{
            setFont(Utils.unibold);
            setText("<html>Deletar registro<br>de pagamento</html>".toUpperCase());
            addActionListener(e -> btnDeletarActionEvent());
            setCursor(Utils.handcursor);
        }};

        this.btnRefresh = new JButton() {{
            setFont(Utils.unibold);
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/refresh_icon.png")));
            setIcon(icon);
            addActionListener(e -> mainpanel.getPpagamentoTable().updateTable());
        }};

        this.btnAtualizar = new JButton() {{
            setFont(Utils.unibold);
            setText("<html>Atualizar Registro<br></html>".toUpperCase());
            setHorizontalAlignment(SwingConstants.CENTER);
            setCursor(Utils.handcursor);
            addActionListener(e -> btnAtualizarActionEvent());
            setVisible(false);
        }};

        this.switchMode = new JCheckBox() {{
            setText("<html>REGISTRAR OU ATUALIZAR<br>PAGAMENTOS?</html>");
            addActionListener(e -> {
                Consumer<Boolean> b = (bool) -> {
                    btnRegistrar.setEnabled(bool);
                    btnRegistrar.setVisible(bool);
                    btnAtualizar.setVisible(bool);
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
                    //mainpanel.getPpagamentoTable().getPagamentoTable().setEnabled(!bool);
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
            this.mainpanel.getMainpanel().add(this.btnAtualizar, this.mainpanel.getComponentsGrid()[16]);
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

    private void btnRegistrarActionEvent() {
        boolean txtFilled = false;
        for (JTextField txtField : mainpanel.getPtxtFields().getTxtFields()) {
            txtFilled = !txtField.getText().isBlank();
        }
        if (this.mainpanel.getPtxtFields().getTxtFields()[0].getText().isBlank()){
            this.mainpanel.getPagamento().setId_pagamento("DEFAULT");
        } else this.mainpanel.getPagamento().setId_pagamento(this.mainpanel.getPtxtFields().getTxtFields()[0].getText());
        this.mainpanel.getPagamento().setData_pagamento(new SimpleDateFormat("dd/MM/yyyy").format(this.mainpanel.getPspnDate().getSpnDate().getValue()));

        if(txtFilled && this.mainpanel.getPagamento().hasValues()) {

            pagamentoDAO.insertPagamento(new String[]{this.mainpanel.getPagamento().getId_pagamento(),
                    this.mainpanel.getPagamento().getId_responsavel(),
                    this.mainpanel.getPagamento().getId_alunoreferente(),
                    this.mainpanel.getPtxtFields().getTxtFields()[3].getText() + "." + this.mainpanel.getPtxtFields().getTxtFields()[4].getText(),
                    "STR_TO_DATE('" + this.mainpanel.getPagamento().getData_pagamento() + "', '%d/%m/%Y')"});

            this.mainpanel.getPpagamentoTable().updateTable();
        }
    }

    private void btnDeletarActionEvent() {

        if (this.mainpanel.getPpagamentoTable().getTableMouseListenerEvents().hasSelected() && this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getValueAt(0,0) != null) {
            pagamentoDAO.deletePagamento(this.mainpanel.getPtxtFields().getTxtFields()[0].getText());
            this.mainpanel.getPpagamentoTable().updateTable();
            if(this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getValueAt(0,0) == null){
                pagamentoDAO.resetAutoIncrement();
                this.mainpanel.getPpagamentoTable().setActualPgId.accept(1);
            }
        }
    }

    private void btnAtualizarActionEvent() {

        if(this.mainpanel.getPpagamentoTable().getTableMouseListenerEvents().hasSelected()){
            //ESSE SET É TEMPORÁRIO, GAMBIRARRINHA.

            this.mainpanel.getPagamento().setValor(this.mainpanel.getPtxtFields().getTxtFields()[3].getText() + "." +
                    this.mainpanel.getPtxtFields().getTxtFields()[4].getText());

            Date value = (Date) this.mainpanel.getPspnDate().getSpnDate().getValue();
            this.mainpanel.getPagamento().setData_pagamento(new SimpleDateFormat("dd/MM/yyyy").format(value));

            //FIM DA GAMBIARRA

            String[] dados = {this.mainpanel.getPagamento().getId_pagamento(),
                    this.mainpanel.getPagamento().getId_responsavel(),
                    this.mainpanel.getPagamento().getId_alunoreferente(),
                    this.mainpanel.getPagamento().getValor(),
                    this.mainpanel.getPagamento().getData_pagamento()};
            this.pagamentoDAO.updatePagamento(dados);
            this.mainpanel.getPpagamentoTable().updateTable();
        }
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
