package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.general.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PagamentoPanel {

    private JButton btnRegistrar, btnDeletar;
    private JTextField[] txtFields;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private JPanel mainpanel;
    private GridBagConstraints tableGrid;
    private GridBagConstraints[] componentsGrid;
    private void iniComponents(){
        this.setLoyout();
        this.startDefaultGridBagConstraints();
        this.startTable();
        this.iniPn();
        this.startTxtFields();
        this.startBtns();
        this.actionEvents();
    }
    public PagamentoPanel(JPanel panel){
        this.mainpanel = panel;
        this.iniComponents();

        //this.drawTableRect();
        this.mainpanel.setVisible(true);
    }

    private void startDefaultGridBagConstraints() {
        this.tableGrid = new GridBagConstraints();
        this.tableGrid.fill = GridBagConstraints.BOTH;
        this.tableGrid.insets = new Insets(5,5,10,100);
        this.componentsGrid = new GridBagConstraints[6];
        for (int i = 0; i < 6; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = (i == 4) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 2 || i == 4) ? 2 : 1;
        }
        this.componentsGrid[0].insets = new Insets(2,5,2,610); // ID GRID
        this.componentsGrid[1].insets = new Insets(2,60,2,300); // RESPONSAVEL GRID
        this.componentsGrid[2].insets = new Insets(5,60,50,300); // ALUNO GRID
        this.componentsGrid[3].insets = new Insets(2, 395, 2, 220); // VALOR PAGAMENTO GRID REAIS
        this.componentsGrid[4].insets = new Insets(5,395, 40, 150); // BTNREGISTRAR GRID
        this.componentsGrid[5].insets = new Insets(2, 444, 2, 180); // VALOR PAGAMENTO GRID CENTAVOS

    }
    private void setLoyout(){
        this.mainpanel.setLayout(new GridBagLayout());
    }

    private void startTable() {
        TableRequests.pagamentoTableRequest();
        this.pagamentoTable = new JTable(TableRequests.getRowsData(), TableRequests.getColumnsName());
        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(new Color(227, 227, 227));
        this.pagamentoTable.setFont(Utils.jetmono);
        this.pagamentoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pagamentoTable.setVisible(true);
    }

    private void startTxtFields() {

        this.txtFields = new JTextField[5];
        for (int i = 0; i < 5; i++){
            this.txtFields[i] = new JTextField();
            this.txtFields[i].setFont(Utils.jetmono);
        }
        Consumer<Integer> addfields = f -> {
            this.txtFields[0].setEditable(false);
            this.txtFields[0].setBackground(new Color(192, 192, 192));
            for(int i = 0; i < this.txtFields.length; i++){
                //this.mainpanel.add(this.txtFields[i], this.componentsGrid[i]);
                this.mainpanel.add(this.txtFields[i], this.componentsGrid[ ((i == 4) ? 5 : i)]);
            }
        };
        addfields.accept(1);
    }

    private void startBtns() {
        this.btnRegistrar = new JButton() {{
            setFont(new Font("Unispace", Font.BOLD, 12));
            setText("<html>Registrar <br>pagamento</html>".toUpperCase());

        }};
        this.btnDeletar = new JButton(){{
            setFont(Utils.jetmono);
            setText("<html>Deletar registro <br>de pagamento</html>\"");
        }};

        Consumer<Integer> addbtns = lamb -> {
            this.mainpanel.add(btnRegistrar, this.componentsGrid[4]);
        };
        addbtns.accept(1);
    }

    private void updateTable() {

    }

    private void iniPn(){
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

    public void drawTableRect(){
        this.mainpanel.setBackground(new Color(136, 136, 136));
        this.mainpanel.getGraphics().setColor(new Color(1,1,1));
        this.mainpanel.getGraphics().drawRect(this.scrollPane.getX(), this.scrollPane.getY(),
                this.scrollPane.getWidth(),this.scrollPane.getHeight());
    }


    private void actionEvents(){

        this.pagamentoTable.addMouseListener(new PagamentoTableActionEvents(this.pagamentoTable, this.txtFields));

    }

}
