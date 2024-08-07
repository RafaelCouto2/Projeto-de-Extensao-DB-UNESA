package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.dao.cadastrodao.alunodao.AlunoDAO;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;

public class CadastroAlunoTable {

    private CadastroAluno mainpanel;
    private JTable alunosTable;
    private JScrollPane scrollPane;
    private int actualId;
    public CadastroAlunoTable(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startTable(){
        this.mainpanel.getAlunoDAO().iniTableData();
        this.alunosTable = new JTable(this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0],
                new Vector<>(Arrays.asList("ID", "NOME", "RESPONSÁVEL", "SEXO", "DATA DE NASCIMENTO", "TAPA BURACO")));
        this.addEmptyRow.accept(1);
        this.setActualId.accept(1);
        this.alunosTable.setDragEnabled(false);
        this.alunosTable.setCursor(Utils.handcursor);
        this.alunosTable.setBackground(Color.white);
        this.alunosTable.setFont(Utils.jetmono13);
        this.alunosTable.getTableHeader().setFont(Utils.jetmono13);
        this.alunosTable.getTableHeader().setForeground(Color.white);
        this.alunosTable.getTableHeader().setBackground(Utils.tableHeader);
        this.alunosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.alunosTable.setForeground(Utils.tableForeground);
        this.alunosTable.setGridColor(Utils.tableGrid);
        this.alunosTable.getColumn("ID").setMaxWidth(55);
        this.alunosTable.getColumn("NOME").setMaxWidth(262);
        this.alunosTable.getColumn("RESPONSÁVEL").setMaxWidth(262);
        this.alunosTable.getColumn("SEXO").setMaxWidth(100);
        this.alunosTable.getColumn("DATA DE NASCIMENTO").setMaxWidth(155);
        this.alunosTable.getColumn("TAPA BURACO").setMaxWidth(10);

        this.alunosTable.setRowHeight(20);
        JTextField NonEditabletxtfield = new JTextField(){{this.setEditable(false);}};
        DefaultCellEditor nonEditCell = new DefaultCellEditor(NonEditabletxtfield);
        for(int c = 0; c < this.alunosTable.getColumnCount(); c++) {
            this.alunosTable.getColumnModel().getColumn(c).setCellEditor(nonEditCell);
        }
    }

    public void iniPn(){
        this.scrollPane = new JScrollPane();
        this.scrollPane.add(this.alunosTable);
        this.scrollPane.setViewportView(this.alunosTable);
        this.scrollPane.setBackground(new Color(245, 245, 245));
        this.mainpanel.getMainpanel().add(this.scrollPane, this.mainpanel.getCadastrogrid());
    }

    Consumer<Integer> addEmptyRow = lamb -> { //AUTO ROW ADDER IF ROWS < 20.
        if(this.alunosTable.getModel().getRowCount() < 20){
            for(int i = this.alunosTable.getModel().getRowCount(); i < 20; i++) {
                ((DefaultTableModel) this.alunosTable.getModel()).addRow(new Object[]{null, "", null, null, null, null});
            }
        }
    };

    Consumer<Integer> setActualId = lamb -> {
        if(this.alunosTable.getRowCount() > 0){
            for(int i = this.alunosTable.getRowCount(); i > 0; i--){
                if(this.alunosTable.getValueAt(i - 1, 0) != null){
                    this.actualId = Integer.parseInt(this.alunosTable.getValueAt(i -1, 0).toString());
                    break;
                } else this.actualId = 0;
            }
        }
    };

    public void reloadTable(){
        this.startTable();
        this.scrollPane.add(this.alunosTable);
        this.scrollPane.setViewportView(this.alunosTable);

    }

    public int getActualId() {
        return this.actualId;
    }

}
