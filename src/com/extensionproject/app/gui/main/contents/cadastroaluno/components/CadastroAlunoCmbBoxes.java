package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.events.cmbox.CmbAlunoKeyListener;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Vector;

public class CadastroAlunoCmbBoxes {
    private CadastroAluno mainpanel;
    private JComboBox<Object> cmbAluno, cmbResponsavel, cmbSexo;
    private boolean first = true, alunoevt = false, isEditing = false, lock = false;
    private int aludotindx;
    public CadastroAlunoCmbBoxes(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }
    public void startCmbx(){
        this.cmbAluno = new JComboBox<>();
        this.cmbResponsavel = new JComboBox<>();
        this.cmbSexo = new JComboBox<>();

        this.cmbAluno.setCursor(Utils.handcursor);
        this.cmbResponsavel.setCursor(Utils.handcursor);
        this.cmbSexo.setCursor(Utils.handcursor);
        this.cmbAluno.setFont(Utils.jetmono13);
        this.cmbResponsavel.setFont(Utils.jetmono13);
        this.cmbSexo.setFont(Utils.jetmono13);

        this.cmbAluno.setBackground(Color.white);
        this.cmbSexo.setBackground(Color.white);
        this.cmbResponsavel.setBackground(Utils.cmbRed);

        this.cmbSexo.addItem(new String("Masculino"));
        this.cmbSexo.addItem(new String("Feminino"));
        //this.cmbAluno.setEditable(true);

        //this.cmbAluno.addItem("<Cadastrar aluno>");
        this.cmbAluno.addItemListener(this::cmbBoxAlunoItemEvent);
        this.cmbResponsavel.addItemListener(this::cmbBoxReponsavelItemEvent);
        this.cmbSexo.addItemListener(this::cmbBoxSexoItemListener);
        this.cmbAluno.getEditor().getEditorComponent().addKeyListener(new CmbAlunoKeyListener(this));

        for(Vector<Object> array : this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0]) {
            if(array.get(2) == null) break;
            this.cmbAluno.addItem(array.get(0) + ": " + array.get(2));
        }

        for(Vector<Object> array : this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[1]){
            this.cmbResponsavel.addItem(array.get(1) + ": " + array.get(0));
        }

        this.cmbAluno.setSelectedItem(null);
        this.cmbResponsavel.setSelectedItem(null);
        this.cmbSexo.setSelectedItem(null);
        this.cmbResponsavel.setEnabled(false);

        this.mainpanel.getMainpanel().add(this.cmbAluno, this.mainpanel.getComponentsGrid()[4]);
        this.mainpanel.getMainpanel().add(this.cmbResponsavel, this.mainpanel.getComponentsGrid()[2]);
        this.mainpanel.getMainpanel().add(this.cmbSexo, this.mainpanel.getComponentsGrid()[5]);

    }


    private void reloadAlunoComboBox(){
        this.cmbAluno.removeAllItems();
        if(this.mainpanel.getBtnCadastros().isStateChanged()) this.cmbAluno.addItem("<Cadastrar aluno>");
        for(Vector<Object> array : this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0]) {
            if(array.get(2) == null) break;
            this.cmbAluno.addItem(array.get(0) + ": " + array.get(2));
        }
    }

    private void cmbBoxAlunoItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>) {

                this.isEditing = Character.isDigit(this.cmbAluno.getSelectedItem().toString().charAt(0));
                if (this.isEditing && !lock) {
                    this.lock = true;
                    if (this.mainpanel.getAlunoTable().getMouseListener().isSelected()) this.cmbAluno.setEnabled(true);
                    Vector<Vector<Object>> aluRequest = this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0];
                    StringBuilder abf = new StringBuilder(this.cmbAluno.getSelectedItem().toString());
                    this.aludotindx = abf.indexOf(":");
                    for (int i = 0; i < aluRequest.size(); i++) {
                        if (Objects.equals(this.cmbAluno.getSelectedItem(), aluRequest.get(i).get(0) + ": " + aluRequest.get(i).get(2))) {
                            this.cmbResponsavel.setSelectedItem(aluRequest.get(i).get(1) + ": " + aluRequest.get(i).get(3));
                            this.cmbSexo.setSelectedItem(aluRequest.get(i).get(4));
                            this.mainpanel.getTxtFields().getTxtField().setText(aluRequest.get(i).get(0).toString());

                            try {
                                this.mainpanel.getSpnDate().getSpnDate().setValue(new SimpleDateFormat("dd/MM/yyyy").parse(aluRequest.get(i).get(5).toString()));
                            } catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        }
                    }
                } else {

                }
            }
        } else {
            lock = false;
            first = false;
        }
    }

    private void cmbBoxReponsavelItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>){
                StringBuilder rbf = new StringBuilder(this.cmbResponsavel.getSelectedItem().toString());
                int respdotindx = rbf.indexOf(":");

                if(this.mainpanel.getBtnCadastros().isStateChanged() && !this.mainpanel.getAlunoTable().getMouseListener().isSelected()){
                    this.cmbAluno.setEnabled(true);
                    Vector<Vector<Object>> aluRequest = this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0];
                    Vector<String> alunosPertencentesAoResp = new Vector<>();
                    //this.cmbAluno.removeItemAt(0);
                    this.reloadAlunoComboBox();
                    for (int aluindx = 0; aluindx < aluRequest.size(); aluindx++) {
                        if (aluRequest.get(aluindx).get(0) == null) break;
                        try {
                            if (this.cmbResponsavel.getSelectedItem().toString().substring(0, respdotindx).equals(aluRequest.get(aluindx).get(1).toString())) {
                                alunosPertencentesAoResp.add(aluRequest.get(aluindx).get(0).toString() + ": " + aluRequest.get(aluindx).get(2).toString());
                            }
                        } catch (NullPointerException ex) {
                            //this.cmbAluno.setSelectedItem("<Cadastrar novo aluno>");
                        }

                    }

                        this.cmbAluno.removeAllItems();
                        this.cmbAluno.addItem("<Cadastrar aluno>");
                        for (String items : alunosPertencentesAoResp) {
                            this.cmbAluno.addItem(items);
                        }


                }

            }
        }
    }

    private void cmbBoxSexoItemListener(ItemEvent e){

    }

    public CadastroAluno getMainpanel() {
        return this.mainpanel;
    }

    public JComboBox<Object> getCmbAluno() {
        return this.cmbAluno;
    }

    public JComboBox<Object> getCmbResponsavel() {
        return this.cmbResponsavel;
    }

    public JComboBox<Object> getCmbSexo() {
        return this.cmbSexo;
    }

    public boolean isEditing() {
        return this.isEditing;
    }

    public int getAludotindx() {
        return this.aludotindx;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
