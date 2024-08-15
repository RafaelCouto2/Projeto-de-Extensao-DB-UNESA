package com.extensionproject.app.gui.main.contents.cadastroaluno.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;

public class CadastroAlunoButtons {
    private CadastroAluno mainpanel;
    private JButton[] btnCadastro;
    private JCheckBox btnSwitch;
    private boolean stateChanged = false, switched = false;
    public CadastroAlunoButtons(CadastroAluno mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startBtns(){
        this.btnCadastro = new JButton[4];
        this.btnSwitch = new JCheckBox();

        for (int i = 0; i < 4; i++) {
            this.btnCadastro[i] = new JButton();
        }

        this.btnCadastro[0].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/refresh_icon.png"))));
        this.btnCadastro[0].addActionListener(e -> btnReloadActionEvent());
        this.btnCadastro[0].setCursor(Utils.handcursor);

        this.btnCadastro[1].setFont(Utils.unibold);
        this.btnCadastro[1].setText("<html>DELETAR<br>ALUNO</html>");
        this.btnCadastro[1].addActionListener(e -> btnDeletarActionEvent());
        this.btnCadastro[1].setCursor(Utils.handcursor);

        this.btnCadastro[2].setFont(Utils.unibold);
        this.btnCadastro[2].setText("<html>REGISTRAR<br>ALUNO</html>");
        this.btnCadastro[2].addActionListener(e -> btnRegistrarActionEvent());
        this.btnCadastro[2].setCursor(Utils.handcursor);

        this.btnCadastro[3].setFont(Utils.unibold);
        this.btnCadastro[3].setText("<html>EDITAR<br>ALUNO</html>");
        this.btnCadastro[3].addActionListener(e -> btnEditarActionEvent());
        this.btnCadastro[3].setCursor(Utils.handcursor);
        this.btnCadastro[3].setVisible(false);
        this.btnCadastro[3].setEnabled(false);

        //this.btnSwitch.setFont(Utils.jetmono);
        this.btnSwitch.setCursor(Utils.handcursor);
        this.btnSwitch.setText("<html>CADASTRAR OU EDITAR<br>ALUNO?</html>");
        this.btnSwitch.addActionListener(e -> {
            btnSwitchActionEvent(btnSwitch.isSelected());
        });

        this.mainpanel.getMainpanel().add(this.btnCadastro[0], this.mainpanel.getComponentsGrid()[3]);
        this.mainpanel.getMainpanel().add(this.btnCadastro[1], this.mainpanel.getComponentsGrid()[8]);
        this.mainpanel.getMainpanel().add(this.btnCadastro[2], this.mainpanel.getComponentsGrid()[9]);
        this.mainpanel.getMainpanel().add(this.btnCadastro[3], this.mainpanel.getComponentsGrid()[10]);

        this.mainpanel.getMainpanel().add(this.btnSwitch, this.mainpanel.getComponentsGrid()[7]);

    }

    private void btnDeletarActionEvent(){
        if(this.mainpanel.getAluno().getId_responsavel() != null && this.mainpanel.getAluno().getId_aluno() != null) {
            this.mainpanel.getAlunoDAO().deletarAluno(new String[]{this.mainpanel.getAluno().getId_aluno(), this.mainpanel.getAluno().getId_responsavel()});
            this.mainpanel.reloadComponentsProperties();
            this.mainpanel.getAluno().nullifer();
        }
    }

    private void btnRegistrarActionEvent(){
        if(this.mainpanel.getAluno().hasValues()){
            this.mainpanel.getAlunoDAO().insertAluno(this.mainpanel.getAluno().getValues());
            this.mainpanel.reloadComponentsProperties();
            this.mainpanel.getAluno().nullifer();
        }
    }

    private void btnEditarActionEvent(){
        if(this.mainpanel.getAluno().hasValues()){
            boolean trocando = false;
            trocando = this.mainpanel.getAluno().isChangingResp();
            this.mainpanel.getAlunoDAO().updateAluno(this.mainpanel.getAluno().getValues(), trocando);
            this.mainpanel.reloadComponentsProperties();
            this.mainpanel.getAluno().nullifer();
        }
    }

    private void btnReloadActionEvent(){
        //this.mainpanel.reloadComponentsProperties();
        //this.mainpanel.getAluno().nullifer();
        System.out.println(Arrays.toString(this.mainpanel.getAluno().getValues()));
    }

    private void btnSwitchActionEvent(boolean bool){
        this.btnCadastro[1].setVisible(!bool);
        this.btnCadastro[2].setVisible(bool);
        this.btnCadastro[3].setVisible(bool);

        this.mainpanel.getCmbBoxes().getCmbAluno().setEditable(bool);
        this.mainpanel.getCmbBoxes().getCmbSexo().setEnabled(bool);
        this.mainpanel.getCmbBoxes().getCmbResponsavel().setEnabled(bool);

        this.mainpanel.getCmbBoxes().getCmbSexo().setSelectedItem(null);
        this.mainpanel.getCmbBoxes().getCmbResponsavel().setSelectedItem(null);
        this.mainpanel.getCmbBoxes().getCmbAluno().setSelectedItem(null);
        this.mainpanel.getTxtFields().getTxtField().setEnabled(bool);
        this.mainpanel.getSpnDate().getSpnDate().setEnabled(bool);
        this.mainpanel.getCmbBoxes().getCmbAluno().setEnabled(!bool);
        this.changeState();

        this.mainpanel.getCmbBoxes().setForeignReload(true);
        this.mainpanel.getCmbBoxes().reloadAlunoComboBox();
        this.mainpanel.getCmbBoxes().setForeignReload(false);

        if(bool){

        } else {

        }

        if(switched){
            this.switchCadastrarEditar();
            this.mainpanel.getCmbBoxes().setEditing(false);
        }
        this.mainpanel.getAluno().nullifer();

    }

    public void changeState(){
        this.stateChanged = !this.stateChanged;
        //this.btnCadastro[2].setEnabled(!this.btnCadastro[2].isEnabled());
        //this.btnCadastro[3].setEnabled(!this.btnCadastro[3].isEnabled());
    }

    public void switchCadastrarEditar(){
        this.btnCadastro[2].setEnabled(this.btnCadastro[3].isEnabled());
        this.btnCadastro[3].setEnabled(!this.btnCadastro[2].isEnabled());
        this.switched = !btnCadastro[2].isEnabled();
    }
    public boolean isSwitched(){
        return switched;
    }

    public boolean isStateChanged() {
        return this.stateChanged;
    }

    public JCheckBox getBtnSwitch() {
        return this.btnSwitch;
    }
}
