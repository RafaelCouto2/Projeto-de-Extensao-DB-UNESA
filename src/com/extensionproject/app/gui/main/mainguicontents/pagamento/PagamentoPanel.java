package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.gui.main.MainGui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.function.Consumer;

public class PagamentoPanel {

    private JButton btnTest;
    private JTable pagamentoTable;
    private JScrollPane scrollPane;
    private JPanel mainpanel;
    private GridBagConstraints grid;
    private void iniComponents(){
        this.setLoyout();
        this.startDefaultGridBagConstraints();
        this.startTable();
        this.iniPn();
    }
    public PagamentoPanel(JPanel panel){
        this.mainpanel = panel;
        this.iniComponents();
        this.drawTableRect();
        this.mainpanel.setVisible(true);
    }

    private void startDefaultGridBagConstraints(){
        this.grid = new GridBagConstraints();
        this.grid.fill = GridBagConstraints.BOTH;
        this.grid.insets = new Insets(5,5,200,100);
    }
    private void setLoyout(){
        this.mainpanel.setLayout(new GridBagLayout());
    }

    private void startTable() {

        PagamentoTableRequest.pagamentoTableRequest();
        this.pagamentoTable = new JTable(PagamentoTableRequest.getRowsData(), PagamentoTableRequest.getColumnsName());
        //this.mainpanel.add(pagamentoTable, grid);
        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(new Color(190,190,190));
        this.pagamentoTable.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        this.pagamentoTable.setEnabled(false);
        this.pagamentoTable.setVisible(true);
    }

    private void updateTable(){

    }

    private void iniPn(){
        this.grid.gridx = 0; // coluna 0
        this.grid.gridy = 0; // linha 0
        this.grid.gridwidth = 1; // 1 célula de largura
        this.grid.gridheight = 1; // 1 célula de altura
        this.grid.weightx = 1.0; // expansão horizontal
        this.grid.weighty = 1.0; // expansão vertical
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setVisible(true);
        this.scrollPane.setViewportView(this.pagamentoTable);
        this.scrollPane.setBackground(new Color(217, 126, 158));
        this.mainpanel.add(this.scrollPane, grid);
    }

    public void drawTableRect(){
        this.mainpanel.getGraphics().setColor(new Color(1,1,1));
        this.mainpanel.getGraphics().drawRect(this.scrollPane.getX(), this.scrollPane.getY(),
                this.scrollPane.getWidth(),this.scrollPane.getHeight());
    }

}
