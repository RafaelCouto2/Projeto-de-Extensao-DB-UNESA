package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.events.cmbox.CmbAlunoKeyListener;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Vector;

public class CadastroAlunoCmbBoxes {
    private CadastroAluno mainpanel;
    private JComboBox<Object> cmbAluno, cmbResponsavel, cmbSexo;
    private boolean first = true, alunoevt = true, isEditing = false, lock = false, firenext = false, foreignReload = false, hasForeign = false;
    private int aludotindx, selectedIndex, actualrespid;
    private String selectedAluno;
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


    public void reloadAlunoComboBox(){
        this.cmbAluno.removeAllItems();
        if(this.mainpanel.getBtnCadastros().isStateChanged()) this.cmbAluno.addItem("<Cadastrar aluno>");
        for(Vector<Object> array : this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0]) {
            if(array.get(2) == null) break;
            this.cmbAluno.addItem(array.get(0) + ": " + array.get(2));
        }
        this.cmbAluno.setSelectedItem(null);
    }

    private void cmbBoxAlunoItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first && !this.foreignReload){
            Object source = e.getSource();
            if(source instanceof JComboBox<?>) {
                this.isEditing = Character.isDigit(this.cmbAluno.getSelectedItem().toString().charAt(0));
                if(this.mainpanel.getAlunoTable().getMouseListener().isSelected()) this.firenext = true;
                if(this.cmbAluno.getSelectedIndex() != 0 && this.isEditing){
                    StringBuilder bf = new StringBuilder(this.cmbAluno.getSelectedItem().toString());
                    int dot = bf.indexOf(":");
                    this.aludotindx = dot;
                    this.mainpanel.getAluno().setNome(this.cmbAluno.getSelectedItem().toString().substring(dot + 2));
                }
                //if (!this.cmbAluno.getEditor().getItem().toString().startsWith("<") && !this.isEditing) this.mainpanel.getAluno().setNome(this.cmbAluno.getEditor().getItem().toString());
                if (alunoevt && !this.mainpanel.getAlunoTable().getMouseListener().isSelected()) {
                    this.selectedAluno = this.cmbAluno.getSelectedItem().toString();
                    this.alunoevt = false;
                }

                //String resp = null;
                if (this.isEditing && !lock) {
                    this.lock = true;
                    if (this.mainpanel.getAlunoTable().getMouseListener().isSelected()) this.cmbAluno.setEnabled(true);
                    if (!this.mainpanel.getBtnCadastros().isSwitched()) this.mainpanel.getBtnCadastros().switchCadastrarEditar();
                    Vector<Vector<Object>> aluRequest = this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0];
                    StringBuilder abf = new StringBuilder(this.cmbAluno.getSelectedItem().toString());
                    this.aludotindx = abf.indexOf(":");
                    for (int i = 0; i < aluRequest.size(); i++) {
                        if (Objects.equals(this.cmbAluno.getSelectedItem(), aluRequest.get(i).get(0) + ": " + aluRequest.get(i).get(2))) {
                            this.cmbResponsavel.setSelectedItem(aluRequest.get(i).get(1) + ": " + aluRequest.get(i).get(3));
                            //resp = aluRequest.get(i).get(1).toString();
                            //this.mainpanel.getAluno().setId_responsavel(aluRequest.get(i).get(1).toString());

                            this.cmbSexo.setSelectedItem(aluRequest.get(i).get(4));
                            this.mainpanel.getTxtFields().getTxtField().setText(aluRequest.get(i).get(0).toString());
                            //this.mainpanel.getAluno().setId_aluno(aluRequest.get(i).get(0).toString());
                            //System.out.println("ALUNOCMB: " + this.mainpanel.getAluno().getId_aluno());
                            try {
                                if(!aluRequest.get(i).get(5).toString().equals("--/--/----")) {
                                    this.mainpanel.getSpnDate().getSpnDate().setValue(new SimpleDateFormat("dd/MM/yyyy").parse(aluRequest.get(i).get(5).toString()));
                                    this.mainpanel.getAluno().setDt_nascimento(aluRequest.get(i).get(5).toString());
                                } else {
                                    this.mainpanel.getSpnDate().getSpnDate().getModel().setValue(Utils.calendar.getTime());
                                    this.mainpanel.getAluno().setDt_nascimento("DEFAULT");
                                }

                            } catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            } catch (RuntimeException ex){

                            }
                            break;
                        }
                    }
                    this.mainpanel.getAluno().setId_aluno(this.mainpanel.getTxtFields().getTxtField().getValue().toString());

                } else {
                    if(this.mainpanel.getBtnCadastros().isSwitched()) this.mainpanel.getBtnCadastros().switchCadastrarEditar();
                    if(this.cmbAluno.getSelectedIndex() == 0){
                        this.setMaiorId();
                        //this.mainpanel.getAluno().setId_responsavel(String.valueOf(this.actualrespid));
                        ///System.out.println(this);

                    }
                    if (!this.cmbAluno.getEditor().getItem().toString().startsWith("<") &&
                            !this.isEditing){
                        this.mainpanel.getAluno().setNome(this.cmbAluno.getEditor().getItem().toString());
                        this.mainpanel.getAluno().setId_aluno(this.mainpanel.getTxtFields().getTxtField().getValue().toString());
                        //this.mainpanel.getAluno().setId_responsavel(resp);
                    }

                }
                this.actualrespid = Integer.parseInt(this.cmbResponsavel.getSelectedItem().toString().substring(0, this.cmbResponsavel.getSelectedItem().toString().indexOf(":")));
            }
            try {
                StringBuilder rbf1 = new StringBuilder(this.cmbResponsavel.getSelectedItem().toString());
                int respdotindx__ = rbf1.indexOf(":");
                this.mainpanel.getAluno().setId_responsavel(this.cmbResponsavel.getEditor().getItem().toString().substring(0, respdotindx__));
            } catch (StringIndexOutOfBoundsException ex){

            }

        } else {
            lock = false;
            first = false;
        }
        this.hasForeign = false;
    }

    private void cmbBoxReponsavelItemEvent(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED && !first && !this.foreignReload){
            Object source = e.getSource();
            if(this.foreignReload) hasForeign = true;
            if(source instanceof JComboBox<?> && !hasForeign) {
                StringBuilder rbf1 = new StringBuilder(this.cmbResponsavel.getSelectedItem().toString());
                int respdotindx__ = rbf1.indexOf(":");
                int respdotindx_ = Integer.parseInt(this.cmbResponsavel.getSelectedItem().toString().substring(0, this.cmbResponsavel.getSelectedItem().toString().indexOf(":")));
                //this.mainpanel.getAluno().setId_responsavel(this.cmbResponsavel.getSelectedItem().toString().substring(0, respdotindx__));

                //System.out.println(this.mainpanel.getAluno().getId_responsavel());

                if (!this.isEditing) {
                    StringBuilder rbf = new StringBuilder(this.cmbResponsavel.getSelectedItem().toString());
                    int respdotindx = rbf.indexOf(":");
                    respdotindx_ = respdotindx;

                    if (this.mainpanel.getBtnCadastros().isStateChanged() && !this.mainpanel.getAlunoTable().getMouseListener().isSelected()) {
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
//                    int maior = 0;
//                    Vector<Integer> ids = new Vector<>();
//                    System.out.println(this.cmbAluno.getItemCount());
//                    for(int i = 1; i < this.cmbAluno.getItemCount(); i++){
//                        System.out.println(this.cmbAluno.getItemAt(i));
//                        StringBuilder bf = new StringBuilder(this.cmbAluno.getItemAt(i).toString());
//                        int dotindx = bf.indexOf(":");
//                        bf = new StringBuilder(bf.substring(0, dotindx));
//                        ids.add(Integer.parseInt(bf.toString()));
//                    }
//                    try {
//                        maior = Collections.max(ids);
//                    } catch (NoSuchElementException ex){
//                        maior = 0;
//                    }
//                    maior++;
//                    this.mainpanel.getTxtFields().getTxtField().setText(String.valueOf(maior));
//                    this.mainpanel.getAluno().setId_aluno(maior);
//                    System.out.println("RESPCMB: " + this.mainpanel.getAluno().getId_aluno());
                        this.setMaiorId();
                    }
                    this.setAlunoNextSelectedItem();

                } else {
                    if (this.mainpanel.getBtnCadastros().getBtnSwitch().isSelected()) {
                        Vector<Integer> items = new Vector<>();
                        int actResp = 0;
                        int maior = 1;
                        for (Vector<Object> array : this.mainpanel.getAlunoDAO().getTableRequests().getResultsSetData()[0]) {
                            if (array.get(1) == null) break;
                            if (Integer.parseInt(array.get(1).toString()) == respdotindx_) {
                                items.add(Integer.parseInt(array.get(0).toString()));
                                actResp = Integer.parseInt(array.get(1).toString());
                            }
                        }
                        try {
                            maior = Collections.max(items) + 1;
                        } catch (NoSuchElementException ex) {
                            maior = 1;
                        } finally {
                            this.mainpanel.getTxtFields().getTxtField().setText(String.valueOf(maior));

                            this.mainpanel.getAluno().setId_responsavel_if_changing(this.cmbResponsavel.getSelectedItem().toString().substring(0, respdotindx__));

                            if (actResp == actualrespid) {
                                this.mainpanel.getTxtFields().getTxtField().setText(this.cmbAluno.getSelectedItem().toString().substring(0, this.cmbAluno.getSelectedItem().toString().indexOf(":")));
                            }
                            try {
                                if (this.mainpanel.getAluno().getId_responsavel().equals(this.mainpanel.getAluno().getId_responsavel_if_changing())) {
                                    this.mainpanel.getAluno().setChangingResp(false);
                                } else if (!this.mainpanel.getAlunoTable().getMouseListener().isSelected())
                                    this.mainpanel.getAluno().setChangingResp(true);
                            } catch (NullPointerException ex) {

                            }
                            if (!this.mainpanel.getBtnCadastros().getBtnSwitch().isSelected()) {
                                this.mainpanel.getAluno().setChangingResp(false);
                            }
                        }
                    } else {
                        this.mainpanel.getAluno().setId_responsavel(this.cmbResponsavel.getEditor().getItem().toString().substring(0, respdotindx__));
                    }
                }
                try {
                    this.mainpanel.getAluno().setId_responsavel(this.cmbResponsavel.getEditor().getItem().toString().substring(0, respdotindx__));
                } catch (StringIndexOutOfBoundsException ex){

                }
            }
        }
    }

    private void setMaiorId(){
        int maior = 0;
        Vector<Integer> ids = new Vector<>();
        for(int i = 1; i < this.cmbAluno.getItemCount(); i++){
            StringBuilder bf = new StringBuilder(this.cmbAluno.getItemAt(i).toString());
            int dotindx = bf.indexOf(":");
            bf = new StringBuilder(bf.substring(0, dotindx));
            ids.add(Integer.parseInt(bf.toString()));
        }
        try {
            maior = Collections.max(ids);
        } catch (NoSuchElementException ex){
            maior = 0;
        }
        maior++;
        this.mainpanel.getTxtFields().getTxtField().setText(String.valueOf(maior));
    }


    private void setAlunoNextSelectedItem() {
        if (this.firenext) {
            if (this.cmbAluno.getSelectedItem().equals("<Cadastrar aluno>") && this.cmbAluno.getItemCount() >= 1) {
                this.cmbAluno.setSelectedItem(this.selectedAluno);
                this.alunoevt = true;
                this.firenext = false;
            }
        }
    }

    private void cmbBoxSexoItemListener(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED){
            Object obj = e.getSource();
            if(obj instanceof JComboBox<?>){
                if(this.cmbSexo.getSelectedItem().toString().equals("Masculino")){
                    this.mainpanel.getAluno().setSexo("m");
                } else this.mainpanel.getAluno().setSexo("f");
            }
        }
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

    public boolean isForeignReload() {
        return this.foreignReload;
    }

    public void setForeignReload(boolean foreignReload) {
        this.foreignReload = foreignReload;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }
}
