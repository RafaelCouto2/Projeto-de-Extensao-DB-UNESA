package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.events.cmbox.CmbResponsavelKeyListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.logger.LoggerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Vector;

public class CadastroResponsavelCmbBoxes {

    private CadastroResponsavel mainpanel;
    private JComboBox<Object> cmbBoxResponsavel, cmbBoxSexo;
    private boolean lock = false, first = true, editing = false, reload = false;
    private int iddot;
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

        this.cmbBoxResponsavel.setBackground(Utils.cmbRed);
        this.cmbBoxSexo.setBackground(Color.white);

        this.cmbBoxResponsavel.addItemListener(this::cmbBoxResponsavelItemEvent);
        this.cmbBoxSexo.addItemListener(this::cmbBoxSexoItemEvent);
        this.cmbBoxResponsavel.getEditor().getEditorComponent().addKeyListener(new CmbResponsavelKeyListener(this));

        this.cmbBoxSexo.addItem(new String("Masculino"));
        this.cmbBoxSexo.addItem(new String("Feminino"));

        Vector<Vector<Object>> request0 = this.mainpanel.getResponsavelDAO().getTableRequests().getResultsSetData()[0];
        for (int i = 0; i < request0.size(); i++) {
            if (request0.get(i).get(0) != null) {
                this.cmbBoxResponsavel.addItem(request0.get(i).get(0) + ": " + request0.get(i).get(1));
            }
        }

        this.cmbBoxResponsavel.setSelectedItem(null);
        this.cmbBoxSexo.setSelectedItem(null);
        this.cmbBoxSexo.setEnabled(false);

        this.mainpanel.getMainpanel().add(this.cmbBoxResponsavel, this.mainpanel.getComponentsGrid()[4]);
        this.mainpanel.getMainpanel().add(this.cmbBoxSexo, this.mainpanel.getComponentsGrid()[5]);

    }

    public void reloadReponsavelCombox() {
        this.reload = true;
        this.cmbBoxResponsavel.removeAllItems();
        Vector<Vector<Object>> request0 = this.mainpanel.getResponsavelDAO().getTableRequests().getResultsSetData()[0];
        for (int i = 0; i < request0.size(); i++) {
            if (request0.get(i).get(0) != null) {
                this.cmbBoxResponsavel.addItem(request0.get(i).get(0) + ": " + request0.get(i).get(1));
            }
        }
        if(!this.mainpanel.getBtnCadastro().isStateChanged()) {
            for (int i = 0; i < this.cmbBoxResponsavel.getItemCount(); i++) {
                if(!this.cmbBoxResponsavel.getItemAt(i).toString().startsWith("<")){
                    //this.cmbBoxResponsavel.addItem("<Cadastrar novo responsável>");
                    this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().insertItemAt("<Cadastrar novo responsável>", 0);
                    this.cmbBoxResponsavel.setSelectedItem("<NOME>");
                    break;
                }
            }
        } else {
            this.cmbBoxResponsavel.setSelectedItem(null);
        }

        this.reload = false;
    }

    private void cmbBoxResponsavelItemEvent(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && !first && !reload){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){
                try {
                    if(Character.isDigit(Objects.requireNonNull(this.cmbBoxResponsavel.getSelectedItem()).toString().charAt(0)) && !lock) {
                        this.lock = true;
                        this.editing = true;
                        this.mainpanel.getTxtFields().getTxtFields()[0].setEnabled(false);
                        if(!this.mainpanel.getBtnCadastro().isStateChanged()) this.mainpanel.getBtnCadastro().changeState();
                        StringBuilder bf = new StringBuilder(Objects.requireNonNull(this.cmbBoxResponsavel.getSelectedItem()).toString());
                        int id = bf.indexOf(":");
                        String clearedName = bf.delete(0, id+2).toString();
                        this.mainpanel.getResposavel().setNome(clearedName);
                        iddot = id;
                        JTable table = this.mainpanel.getResponsavelTable().getResptable();
                        this.mainpanel.getTxtFields().getTxtFields()[0].setText(
                                Objects.requireNonNull(this.cmbBoxResponsavel.getSelectedItem()).toString().substring(0, id)
                        );

                        for (int i = 0; i < table.getRowCount(); i++) {

                            if (this.cmbBoxResponsavel.getSelectedItem().toString().substring(0, id)
                                    .equals(table.getValueAt(i, 0).toString())) {

                                this.cmbBoxSexo.setSelectedItem(table.getValueAt(i, 2).toString());
                                bf = new StringBuilder(table.getValueAt(i, 4).toString());
                                String newnumber = "";

                                for (int str = 0; str < bf.length(); str++) {
                                    if (bf.charAt(str) == '(' || bf.charAt(str) == ')' || bf.charAt(str) == '-') {
                                        newnumber = bf.deleteCharAt(str).toString();
                                    }
                                }

                                if(!((newnumber.startsWith("0")) || (newnumber.isBlank()) || (newnumber.contains("00000000000")))){
                                    this.mainpanel.getTxtFields().getTxtFields()[2].setText(newnumber.substring(0, 2));
                                    this.mainpanel.getTxtFields().getTxtFields()[1].setText(newnumber.substring(2));

                                } else {
                                    this.mainpanel.getTxtFields().getTxtFields()[1].setText(null);
                                    this.mainpanel.getTxtFields().getTxtFields()[2].setText("21");
                                }

                                //Aplica o id atual ao objeto do responsável.
                                //this.mainpanel.getResposavel().setId_responsavel(this.mainpanel.getTxtFields().getTxtFields()[0].getText());

                                this.mainpanel.getSpnData().getSpnDate().setValue(new SimpleDateFormat("dd/MM/yyyy")
                                        .parse(table.getValueAt(i,3).toString()));

                                break;
                            }

                        }

                    } else {
                        this.mainpanel.getTxtFields().getTxtFields()[0].setEnabled(true);
                        this.editing = false;
                        this.mainpanel.getResposavel().setNome(this.cmbBoxResponsavel.getEditor().getItem().toString());
                    }

                    if(this.cmbBoxResponsavel.getSelectedItem().equals("<Cadastrar novo responsável>") || this.cmbBoxResponsavel.getEditor().getItem().equals("")){
                        if(this.mainpanel.getBtnCadastro().isStateChanged()) this.mainpanel.getBtnCadastro().changeState();
                        this.mainpanel.getTxtFields().changeToActualId();
                        this.cmbBoxResponsavel.setSelectedItem("<NOME>");
                    }

                } catch (NullPointerException ex){
                    LoggerManager.getClassLog(CadastroResponsavelCmbBoxes.class).error(": Item inválido, tente novamente.");
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    LoggerManager.getClassLog(CadastroResponsavelCmbBoxes.class).error(": Não foi possível converter a data.");
                    throw new RuntimeException(ex);
                } catch (StringIndexOutOfBoundsException ex){
                    this.mainpanel.getTxtFields().getTxtFields()[0].setEnabled(true);
                }
            }
        }
        this.lock = false;
        this.first = false;
    }

    private void cmbBoxSexoItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){
                //Quando se sentir inútil, pense nesse método.
                //Aplica o sexo do respectivo responsável ao objeto do responsável.
                this.mainpanel.getResposavel().setSexo(Objects.requireNonNull(this.cmbBoxSexo.getSelectedItem()).toString().substring(0,1).toLowerCase());
            }
        }
    }

    public JComboBox<Object> getCmbBoxResponsavel() {
        return this.cmbBoxResponsavel;
    }

    public JComboBox<Object> getCmbBoxSexo() {
        return this.cmbBoxSexo;
    }

    public boolean isEditing() {
        return this.editing;
    }

    public int getIddot() {
        return this.iddot;
    }

    public boolean isLocked() {
        return this.lock;
    }

    public void setLocked(boolean lock) {
        this.lock = lock;
    }

    public CadastroResponsavel getMainpanel() {
        return this.mainpanel;
    }
}
