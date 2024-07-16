package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.table.CadastroResponsavelTableMouseListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;

public class CadastroResponsavelTable {

    private JTable resptable;
    private CadastroResponsavel mainpanel;
    private JScrollPane scrollPane;
    private CadastroResponsavelTableMouseListener tableMouseEvent;
    private int actualId;

    public CadastroResponsavelTable(CadastroResponsavel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public void startTable(){
        this.mainpanel.getResponsavelDAO().iniTableData();
        this.resptable = new JTable(this.mainpanel.getResponsavelDAO().getTableRequests().getResultsSetData(0),
                new Vector<>(Arrays.asList("ID", "NOME", "SEXO", "DATA DE NASCIMENTO", "TELEFONE")));

        this.addEmptyRow.accept(1);
        this.setActualId.accept(1);
        this.resptable.setDragEnabled(false);
        this.resptable.setCursor(Utils.handcursor);
        this.resptable.setBackground(Color.white);
        this.resptable.setFont(Utils.jetmono);
        this.resptable.getTableHeader().setFont(Utils.jetmono);
        this.resptable.getTableHeader().setForeground(Color.white);
        this.resptable.getTableHeader().setBackground(Utils.tableHeader);
        this.resptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.resptable.setForeground(Utils.tableForeground);
        this.resptable.setGridColor(Utils.tableGrid);
        this.resptable.getColumn("ID").setMaxWidth(55);
        this.resptable.getColumn("NOME").setMaxWidth(262);
        this.resptable.getColumn("SEXO").setMaxWidth(100);
        this.resptable.getColumn("DATA DE NASCIMENTO").setMaxWidth(155);
        this.resptable.getColumn("TELEFONE").setMaxWidth(120);
        this.resptable.setRowHeight(20);
        JTextField NonEditabletxtfield = new JTextField(){{this.setEditable(false);}};
        DefaultCellEditor nonEditCell = new DefaultCellEditor(NonEditabletxtfield);
        for(int c = 0; c < this.resptable.getColumnCount(); c++) {
            this.resptable.getColumnModel().getColumn(c).setCellEditor(nonEditCell);
        }
        this.tableMouseListener();

    }

    public void iniPn(){
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.resptable);
        this.scrollPane.setViewportView(this.resptable);
        this.scrollPane.setBackground(new Color(245, 245, 245));
        this.mainpanel.getMainpanel().add(this.scrollPane, this.mainpanel.getCadastrogrid());
    }

    public void tableMouseListener(){
        this.tableMouseEvent = new CadastroResponsavelTableMouseListener(this);
        this.resptable.addMouseListener(this.tableMouseEvent);
    }

    Consumer<Integer> addEmptyRow = lamb -> { //AUTO ROW ADDER IF ROWS < 20.
        if(this.resptable.getModel().getRowCount() < 20){
            for(int i = this.resptable.getModel().getRowCount(); i < 20; i++) {
                ((DefaultTableModel) this.resptable.getModel()).addRow(new Object[]{null,"",null,null});
            }
        }
    };

    Consumer<Integer> setActualId = lamb -> {
        if(this.resptable.getRowCount() > 0){
            for(int i = this.resptable.getRowCount(); i > 0; i--){
                if(this.resptable.getValueAt(i - 1, 0) != null){
                    this.actualId = Integer.parseInt(this.resptable.getValueAt(i -1, 0).toString());
                    break;
                } else this.actualId = 0;
            }
        }
    };

    public void reloadTable(){
        this.startTable();
        this.scrollPane.add(this.resptable);
        this.scrollPane.setViewportView(this.resptable);
        this.mainpanel.getCmbBoxes().reloadReponsavelCombox();

    }

    public JTable getResptable() {
        return this.resptable;
    }


    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }

    public int getActualId() {
        return this.actualId;
    }
}
