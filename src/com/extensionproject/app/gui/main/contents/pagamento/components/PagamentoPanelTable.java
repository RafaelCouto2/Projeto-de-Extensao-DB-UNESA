package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.dao.pagamentodao.PagamentoDAO;
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
        this.pagamentoDAO.iniTableData();
        this.pagamentoTable = new JTable(this.getPagamentoDAO().getTablerequest().getResultsSetData(0),
                new Vector<>(Arrays.asList("ID PG", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR", "DATA DO PAGAMENTO")));

        this.addEmptyRow.accept(1); //AUTO ADD ROW.
        this.setActualRow.accept(1);
        this.setActualPgId.accept(1);
        this.pagamentoTable.setDragEnabled(false);
        this.pagamentoTable.setBackground(Color.white);
        this.pagamentoTable.setFont(Utils.jetmono13);
        this.pagamentoTable.setCursor(Utils.handcursor);
        this.pagamentoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pagamentoTable.getColumn("ID PG").setMaxWidth(55);
        this.pagamentoTable.getColumn("VALOR").setMaxWidth(65);
        this.pagamentoTable.getColumn("DATA DO PAGAMENTO").setMaxWidth(147);
        this.pagamentoTable.getColumn("RESPONSÁVEL").setMaxWidth(230);
        this.pagamentoTable.getColumn("ALUNO REFERENTE").setMaxWidth(230);
        this.pagamentoTable.setForeground(Utils.tableForeground);
        this.pagamentoTable.setGridColor(Utils.tableGrid);
        this.pagamentoTable.setRowHeight(20);
        this.pagamentoTable.getTableHeader().setFont(Utils.jetmono13);
        this.pagamentoTable.getTableHeader().setForeground(Color.white);
        this.pagamentoTable.getTableHeader().setBackground(Utils.tableHeader);
        JTextField NonEditabletxtfield = new JTextField(){{this.setEditable(false);}};
        DefaultCellEditor nonEditCell = new DefaultCellEditor(NonEditabletxtfield);
        for(int c = 0; c < this.pagamentoTable.getColumnCount(); c++) {
            this.pagamentoTable.getColumnModel().getColumn(c).setCellEditor(nonEditCell);
        }
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
//        if(this.mainpanel.getPbtns().getSwitchMode().isSelected()){
//            this.pagamentoTable.setEnabled(false);
//        }
        this.tableMouseListener();
        //nextId.accept(1); //NEXT ID IF AUTO ADD ROW IS DEACTIVATED.
        this.mainpanel.getPbtns().nextId_.accept(1);
        addEmptyRow.accept(1);
        this.scrollPane.add(this.pagamentoTable);
        this.scrollPane.setViewportView(this.pagamentoTable);
    }

    public void tableMouseListener() {
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
