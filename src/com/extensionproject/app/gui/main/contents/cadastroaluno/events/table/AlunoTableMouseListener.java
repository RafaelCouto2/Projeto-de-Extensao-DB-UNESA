package com.extensionproject.app.gui.main.contents.cadastroaluno.events.table;

import com.extensionproject.app.gui.main.contents.cadastroaluno.components.CadastroAlunoTable;
import com.extensionproject.app.gui.main.contents.pagamento.events.TableMouseListenerEvents;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AlunoTableMouseListener implements MouseListener {

    private CadastroAlunoTable main;
    private boolean isSelected = false;

    public AlunoTableMouseListener(CadastroAlunoTable main){
        this.main = main;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            if(this.main.getAlunosTable().getValueAt(this.main.getAlunosTable().getSelectedRow(), 0) != null) {
                this.isSelected = true;
                this.main.getMainpanel().getCmbBoxes().getCmbAluno().setSelectedItem(
                        this.main.getAlunosTable().getValueAt(this.main.getAlunosTable().getSelectedRow(), 0) + ": " +
                        this.main.getAlunosTable().getValueAt(this.main.getAlunosTable().getSelectedRow(), 2));
            } else {
                this.main.getMainpanel().getCmbBoxes().getCmbAluno().setSelectedItem(null);
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            LoggerManager.getClassLog(TableMouseListenerEvents.class).error(": FALHA AO CAPTURAR OS DADOS. POR FAVOR, CLIQUE DENTRO DA TABELA.");
        } finally {
            this.isSelected = false;
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

    public boolean isSelected() {
        return this.isSelected;
    }
}
