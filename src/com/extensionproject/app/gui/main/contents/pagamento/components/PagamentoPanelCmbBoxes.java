package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;


public class PagamentoPanelCmbBoxes {

    private JComboBox<Object>[] cmbFields;
    private final PagamentoPanel mainpanel;
    private Consumer<Integer> resetcmbField;
    public PagamentoPanelCmbBoxes(PagamentoPanel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public void startCmbFields(){
        this.cmbFields = new JComboBox[2];
        for (int l = 0; l < 2; l++) {
            int cmbIndx = l;
            this.cmbFields[l] = new JComboBox<>(){{
                for (int i = 0; i < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(cmbIndx+1).size(); i++){
                    //O ACRESCENTADOR DE ID AO NOME DO RESPONSÁVEL E ALUNO FICA AQUI.
                    addItem(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(cmbIndx+1).get(i).get((cmbIndx == 0)? 0:1) + ": " +
                            mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(cmbIndx+1).get(i).get((cmbIndx == 0)? 1:2));

                    setSelectedItem(null);
                    setEditable(false);
                    //setEnabled(false); //THOSE OPTIONS CHANGES THE BACKGROUND.
                    //setBackground(Color.LIGHT_GRAY.brighter());
                    setBackground(Utils.cmbRed);
                    setFont(Utils.jetmono13);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }};
            this.resetcmbField = (reset) -> {
                switch (reset){
                    case 0:

                        break;
                    case 1:
                        this.cmbFields[1].removeAllItems();
                        for(int i = 0; i < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).size(); i++){
                            this.cmbFields[1].addItem(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(i).get(2));
                        }
                        this.cmbFields[1].setSelectedItem(null);
                        break;
                }
            };
            this.mainpanel.getMainpanel().add(this.cmbFields[cmbIndx], this.mainpanel.getComponentsGrid()[cmbIndx+1]);
        }
        this.cmbFields[1].setEnabled(false);
        this.cmbFields[0].addItemListener(this::cmbResponsavelItemListener);
        this.cmbFields[1].addItemListener(this::cmbAlunoItemListener);
    }


    private boolean ignoreFireEvent;

    private void cmbResponsavelItemListener(ItemEvent e){
        this.ignoreFireEvent = true;
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == (this.cmbFields[0])) {
                int[] responsavelIndx = new int[mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).size()];
                for (int pfv = 0; pfv < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).size(); pfv++){
                    responsavelIndx[pfv] = (int) mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).get(pfv).get(0);
                }
                this.resetcmbField.accept(1);
                for (int i = 0; i < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).size(); i++) {
                    if(i == this.cmbFields[0].getSelectedIndex()) {
                        for (int c = 0; c < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).size(); c++) {
                            if (Integer.parseInt(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(c).get(0).toString()) != responsavelIndx[i]) {
                                this.cmbFields[1].removeItem(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(c).get(2));
                            }
                        }
                        this.mainpanel.getPagamento().setId_responsavel(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).get(i).get(0).toString());
                        break;
                    }
                }
                if(!this.mainpanel.getPbtns().getSwitchMode().isSelected()){
                    if(!this.mainpanel.getPpagamentoTable().getTableMouseListenerEvents().isIgnoreTableClick()) {

                        this.mainpanel.getPpagamentoTable().updateTable();
                        int actualRow = this.mainpanel.getPpagamentoTable().getPagamentoTable().getRowCount();
                        for (int l = actualRow - 1; l >= 0; l--) {
                            String tableItem = this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel().getValueAt(l, 1).toString();
                            String selectedItem = this.cmbFields[0].getModel().getSelectedItem().toString();
                            if (!selectedItem.equals(tableItem)) {
                                ((DefaultTableModel) this.mainpanel.getPpagamentoTable().getPagamentoTable().getModel()).removeRow(l);
                            }
                        }
                    }
                }
                this.cmbFields[1].setEnabled(true);
            }
            this.mainpanel.getPpagamentoTable().getTableMouseListenerEvents().setIgnoreTableClick(false);
            //TableMouseListenerEvents.ignoreTableClick = false;
        }
        this.cmbFields[1].dispatchEvent(e);
        this.ignoreFireEvent = false;
    }

    private void cmbAlunoItemListener(ItemEvent i){
        if(!this.ignoreFireEvent) {
            if (i.getStateChange() == ItemEvent.SELECTED) {
                if (i.getSource() == this.cmbFields[1]) {
                    for(int l = 0; l < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).size(); l++) {
                        for (int c = 0; c < mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).size(); c++) {
                            if (mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(c).get(2).equals(this.cmbFields[1].getSelectedItem()) &&
                                    mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(c).get(0) == mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(1).get(l).get(0)) {

                                this.mainpanel.getPagamento().setId_alunoreferente(mainpanel.getPpagamentoTable().getPagamentoDAO().getTablerequest().getResultsSetData(2).get(c).get(1).toString());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public Consumer<Integer> getResetcmbField() {
        return resetcmbField;
    }

    public JComboBox<Object>[] getCmbFields() {
        return this.cmbFields;
    }
}
