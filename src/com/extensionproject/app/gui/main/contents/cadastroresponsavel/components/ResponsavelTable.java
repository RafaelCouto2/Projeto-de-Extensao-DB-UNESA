package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;

public class ResponsavelTable {

    private JTable resptable;
    private CadastroResponsavel mainpanel;
    private JScrollPane scrollPane;
    private ResponsavelTableColumnComboBox cmbBox;
    public ResponsavelTable(CadastroResponsavel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public void startTable(){
        this.mainpanel.getResponsavelDAO().iniTableData();
        this.resptable = new JTable(this.mainpanel.getResponsavelDAO().getTableRequests().getResultsSetData(0),
                new Vector<>(Arrays.asList("ID", "NOME", "SEXO", "DATA DE NASCIMENTO", "TELEFONE")));

        this.addEmptyRow.accept(1);
        this.resptable.setDragEnabled(false);
        this.resptable.setBackground(Color.white);
        this.resptable.setFont(Utils.jetmono);
        this.resptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.resptable.setForeground(new Color(66,66,66));
        this.resptable.setGridColor(new Color(210,208,216));
        this.resptable.getColumn("ID").setMaxWidth(55);
        this.resptable.getColumn("NOME").setMaxWidth(255);
        this.resptable.getColumn("SEXO").setMaxWidth(100);
        this.resptable.getColumn("DATA DE NASCIMENTO").setMaxWidth(142);
        this.resptable.getColumn("TELEFONE").setMaxWidth(120);
        this.resptable.setRowHeight(20);
        this.resptable.getTableHeader().setFont(Utils.jetmono);
        this.resptable.getTableHeader().setForeground(Color.white);
        this.resptable.getTableHeader().setBackground(new Color(16, 124, 65));

    }

    public void iniPn(){
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.resptable);
        this.scrollPane.setViewportView(this.resptable);
        this.scrollPane.setBackground(new Color(245, 245, 245));
        this.mainpanel.getMainpanel().add(this.scrollPane, this.mainpanel.getCadastrogrid());
    }

    Consumer<Integer> addEmptyRow = lamb -> { //AUTO ROW ADDER IF ROWS < 20.
        if(this.resptable.getModel().getRowCount() < 20){
            for(int i = this.resptable.getModel().getRowCount(); i < 20; i++) {
                ((DefaultTableModel) this.resptable.getModel()).addRow(new Object[]{null,null,null,null});
            }
        }
    };


    public JTable getResptable() {
        return this.resptable;
    }

    public ResponsavelTableColumnComboBox getCmbBox() {
        return cmbBox;
    }

    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }
}
