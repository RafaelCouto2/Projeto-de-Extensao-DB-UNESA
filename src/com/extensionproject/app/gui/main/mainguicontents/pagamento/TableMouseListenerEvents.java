package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.domain.pagamento.Pagamentos;
import com.extensionproject.app.exception.WrongPointTableClickException;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TableMouseListenerEvents implements MouseListener {

    private final JTable pgtable;
    private final JTextField[] txtfields;
    private final JComboBox<?>[] cmbFields;
    private final JSpinner spnDate;
    private final Pagamentos pagamento;
    public static boolean ignoreTableClick = false;
    private static boolean hasSelected;

    public TableMouseListenerEvents(JTable pgtable, JTextField[] txtfields, JComboBox<?>[] cmbFields, JSpinner spnDate, Pagamentos pgm){
        this.pgtable = pgtable;
        this.txtfields = txtfields;
        this.cmbFields = cmbFields;
        this.spnDate  = spnDate;
        this.pagamento = pgm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String rsValue, cnValue;
        if(this.pgtable.isEnabled()) {
            try {
                if (this.pgtable.getValueAt(this.pgtable.getSelectedRow(), 0) != null) {
                    ignoreTableClick = true;
                    for (int r = 0; r < 5; r++) {

                        switch (r) {
                            case 0:
                                this.pagamento.setId_pagamento(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString());
                                this.cmbFields[0].setSelectedItem(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), 1));
                                break;
                            case 1:
                                this.cmbFields[1].setSelectedItem(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), 2));
                                break;
                            case 4:
                                java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString());
                                this.spnDate.setValue(date);
                                this.pagamento.setData_pagamento(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString());
                                break;
                        }
                        if (r == 1 || r == 2 || r == 4) continue;
                        if (r == 3) {
                            rsValue = this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().substring(0,
                                    this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().indexOf("."));

                            cnValue = this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().substring(
                                    this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString().indexOf(".") + 1);

                            this.txtfields[r].setText(rsValue);
                            this.txtfields[r + 1].setText(cnValue);
                        } else {
                            this.txtfields[r].setText(this.pgtable.getValueAt(this.pgtable.getSelectedRow(), r).toString());
                        }
                        hasSelected = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException clickoutoftable) {
                LoggerManager.getClassLog(TableMouseListenerEvents.class).info(": FALHA AO CAPTURAR OS DADOS. POR FAVOR, CLIQUE INTERNAMENTE DENTRO DA TABELA.");
                hasSelected = false;
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
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

    protected static boolean hasSelected() throws WrongPointTableClickException {
        return hasSelected;
    }
}
