package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

public class CadastroResponsavelCmbBoxes {

    private CadastroResponsavel mainpanel;
    private JComboBox<Object> cmbBoxResponsavel, cmbBoxSexo;
    private boolean first = true;
    public CadastroResponsavelCmbBoxes(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void starCmbx() {
        this.cmbBoxResponsavel = new JComboBox<>();
        this.cmbBoxSexo = new JComboBox<>();
        this.cmbBoxResponsavel.setCursor(Utils.handcursor);
        this.cmbBoxSexo.setCursor(Utils.handcursor);
        this.cmbBoxResponsavel.setFont(Utils.jetmono);
        this.cmbBoxSexo.setFont(Utils.jetmono);
        this.cmbBoxResponsavel.addItemListener(this::cmbBoxResponsavelItemEvent);
        this.cmbBoxSexo.addItemListener(this::cmbBoxSexoItemEvent);

        this.cmbBoxSexo.addItem(new String("Masculino"));
        this.cmbBoxSexo.addItem(new String("Feminino"));

        Vector<Vector<Object>> request0 = this.mainpanel.getResponsavelDAO().getTableRequests().getResultsSetData()[0];

        for (int i = 0; i < request0.size(); i++) {
            if (request0.get(i).get(0) != null) {
                this.cmbBoxResponsavel.addItem(request0.get(i).get(0) + ": " + request0.get(i).get(1));
            }
        }

        this.cmbBoxResponsavel.setBackground(Utils.cmbRed);
        this.cmbBoxSexo.setBackground(Color.white);

        this.cmbBoxResponsavel.setSelectedItem(null);
        this.cmbBoxSexo.setSelectedItem(null);
        this.cmbBoxSexo.setEnabled(false);

        this.mainpanel.getMainpanel().add(this.cmbBoxResponsavel, this.mainpanel.getComponentsGrid()[4]);
        this.mainpanel.getMainpanel().add(this.cmbBoxSexo, this.mainpanel.getComponentsGrid()[5]);

    }

    private void cmbBoxResponsavelItemEvent(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && !first){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){
                try {
                    StringBuilder bf = new StringBuilder(Objects.requireNonNull(this.cmbBoxResponsavel.getSelectedItem()).toString());
                    int id = bf.indexOf(":");
                    JTable table = this.mainpanel.getResponsavelTable().getResptable();
                    this.mainpanel.getTxtFields().getTxtFields()[0].setText(
                            Objects.requireNonNull(this.cmbBoxResponsavel.getSelectedItem()).toString().substring(0, id)
                    );

                    for(int i = 0; i < table.getRowCount(); i++){
                        if(this.cmbBoxResponsavel.getSelectedItem().toString().substring(0, id)
                                .equals(table.getValueAt(i, 0).toString())) {
                            this.cmbBoxSexo.setSelectedItem(table.getValueAt(i , 2).toString());
                            bf = new StringBuilder(table.getValueAt(i, 4).toString());
                            String newnumber = "";
                            for(int str = 0; str < bf.length(); str++){
                                if(bf.charAt(str) == '(' || bf.charAt(str) == ')' || bf.charAt(str) == '-'){
                                    newnumber = bf.deleteCharAt(str).toString();
                                }
                            }

                            this.mainpanel.getTxtFields().getTxtFields()[2].setText(newnumber.substring(0,2));
                            this.mainpanel.getTxtFields().getTxtFields()[1].setText(newnumber.substring(2));

                            bf = new StringBuilder(table.getValueAt(i, 3).toString());
                            this.mainpanel.getSpnData().getSpnDate().setValue(new SimpleDateFormat("dd/MM/yyyy").parse(bf.toString()));
                            break;
                        }
                    }

//                    if(this.cmbBoxResponsavel.getSelectedItem().toString().substring(0, id)
//                            .equals(this.mainpanel.getTxtFields().getTxtFields()[0].getText())){
//                        this.cmbBoxSexo.setSelectedItem(table.getValueAt());
//                    }
                } catch (NullPointerException ex){
                    LoggerManager.getClassLog(CadastroResponsavelCmbBoxes.class).error(": Item inválido, tente novamente.");
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    LoggerManager.getClassLog(CadastroResponsavelCmbBoxes.class).error(": Não foi possível converter a data.");
                    throw new RuntimeException(ex);
                }
            }
        }
        first = false;
    }

    private void cmbBoxSexoItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){

            }
        }
    }

    public JComboBox<Object> getCmbBoxResponsavel() {
        return cmbBoxResponsavel;
    }

    public JComboBox<Object> getCmbBoxSexo() {
        return this.cmbBoxSexo;
    }
}
