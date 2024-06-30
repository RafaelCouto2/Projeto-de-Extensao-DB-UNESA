package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableMouseListenerEvents implements MouseListener {

    private JTable pgtable;
    private JTextField[] txtfields;
    public TableMouseListenerEvents(JTable pgtable, JTextField[] txtfields){
        this.pgtable = pgtable;
        this.txtfields = txtfields;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String rsValue, cnValue;
        for(int r = 0; r < 4; r++) {
            try {
                if (r == 3) {
                    rsValue = this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().substring(0,
                            this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().indexOf("."));

                    cnValue = this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().substring(
                            this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().indexOf(".") + 1);

                    this.txtfields[r].setText(rsValue);
                    this.txtfields[r + 1].setText(cnValue);
                } else this.txtfields[r].setText(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString());
            } catch (ArrayIndexOutOfBoundsException clickoutoftable) {
                LoggerManager.getClassLog(TableMouseListenerEvents.class).info(": FALHA AO CAPTURAR OS DADOS. POR FAVOR, CLIQUE INTERNAMENTE DENTRO DA TABELA.");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
