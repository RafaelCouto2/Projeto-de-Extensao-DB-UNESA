package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.general.Utils;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PagamentoPanel {

    private JButton btnRegistrar, btnDeletar;
    private JCheckBox switchMode;
    private JTextField[] txtFields;
    private JLabel[] lblInfo;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private final JPanel mainpanel;
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
        this.actionEvents();
    }


    public PagamentoPanel(JPanel panel) {
        this.mainpanel = panel;
        this.iniComponents();
        this.mainpanel.setVisible(true);
    }


    private void startDefaultGridBagConstraints() {
        this.tableGrid = new GridBagConstraints();
        this.tableGrid.fill = GridBagConstraints.BOTH;
        this.tableGrid.insets = new Insets(5,5,10,52);
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
        TableRequests.pagamentoTableRequest();
        this.pagamentoTable = new JTable(TableRequests.getRowsData(), TableRequests.getColumnsName());
        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(new Color(227, 227, 227));
        this.pagamentoTable.setFont(Utils.jetmono);
        this.pagamentoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pagamentoTable.getColumn("ID").setMaxWidth(50);
        this.pagamentoTable.getColumn("VALOR MENSAL").setMaxWidth(65);
        this.pagamentoTable.getColumn("DATA DO PAGAMENTO").setMaxWidth(100);
        this.pagamentoTable.getColumn("RESPONSÁVEL").setMaxWidth(180);
        this.pagamentoTable.getColumn("ALUNO REFERENTE").setMaxWidth(180);
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
                        } else txtFields[i].setBackground(Color.LIGHT_GRAY.brighter());
                        txtFields[i].setEditable(bool);
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
        this.scrollPane.setBackground(new Color(217, 126, 158));
        this.mainpanel.add(this.scrollPane, tableGrid);
    }


    public void drawTableRect() {
        this.mainpanel.getGraphics().drawRect(this.scrollPane.getX(), this.scrollPane.getY(),
                this.scrollPane.getWidth(), this.scrollPane.getHeight());

    }

    public void drawFieldsRect() {
        this.mainpanel.getGraphics().drawRect(this.lblInfo[0].getX() - 2, this.lblInfo[0].getY() - 2, 600,110);
    }


    private void actionEvents() {
        this.pagamentoTable.addMouseListener(new PagamentoTableActionEvents(this.pagamentoTable, this.txtFields));

    }

}
