package com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.table;

import com.extensionproject.app.exception.WrongPointTableClickException;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.components.CadastroResponsavelTable;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.gui.main.contents.pagamento.events.TableMouseListenerEvents;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroResponsavelTableMouseListener implements MouseListener {

    private final CadastroResponsavelTable tableclass;
    private JTable table;

    public CadastroResponsavelTableMouseListener(CadastroResponsavelTable tablee){
        this.tableclass = tablee;
        this.table = tableclass.getResptable();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (this.table.getValueAt(this.table.getSelectedRow(), 0) != null) {
                this.tableclass.getMainpanel().getCmbBoxes().getCmbBoxResponsavel()
                        .setSelectedItem(
                                this.table.getValueAt(
                                        this.table.getSelectedRow(), 0) + ": " +
                                        this.table.getValueAt(this.table.getSelectedRow(), 1));
            } else {
                this.tableclass.getMainpanel().getCmbBoxes().getCmbBoxResponsavel().setSelectedItem(null);
                this.tableclass.getMainpanel().getTxtFields().getTxtFields()[0].setText(String.valueOf(this.tableclass.getActualId() + 1));
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            LoggerManager.getClassLog(TableMouseListenerEvents.class).error(": FALHA AO CAPTURAR OS DADOS. POR FAVOR, CLIQUE DENTRO DA TABELA.");
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
