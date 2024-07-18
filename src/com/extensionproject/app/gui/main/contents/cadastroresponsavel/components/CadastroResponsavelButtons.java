package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;

public class CadastroResponsavelButtons {
    private final CadastroResponsavel mainpanel;
    private JButton[] btnCadastro;
    private JCheckBox btnSwitch;
    private boolean stateChanged = false;
    public CadastroResponsavelButtons(CadastroResponsavel mainpanel){
        this.mainpanel = mainpanel;
    }

    public void startBtns(){
        this.btnCadastro = new JButton[4];
        this.btnSwitch = new JCheckBox();

        for (int i = 0; i < 4; i++) {
            this.btnCadastro[i] = new JButton();
        }

        this.btnCadastro[0].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/refresh_icon.png"))));
        this.btnCadastro[0].addActionListener(e -> this.mainpanel.getResponsavelTable().reloadTable());
        this.btnCadastro[0].setCursor(Utils.handcursor);

        this.btnCadastro[1].setFont(Utils.unibold);
        this.btnCadastro[1].setText("<html>DELETAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[1].addActionListener(e -> btnDeletarActionEvent());
        this.btnCadastro[1].setCursor(Utils.handcursor);

        this.btnCadastro[2].setFont(Utils.unibold);
        this.btnCadastro[2].setText("<html>REGISTRAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[2].addActionListener(e -> btnRegistrarActionEvent());
        this.btnCadastro[2].setCursor(Utils.handcursor);

        this.btnCadastro[3].setFont(Utils.unibold);
        this.btnCadastro[3].setText("<html>EDITAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[3].addActionListener(e -> btnEditarActionEvent());
        this.btnCadastro[3].setCursor(Utils.handcursor);
        this.btnCadastro[3].setVisible(false);
        this.btnCadastro[3].setEnabled(false);

        //this.btnSwitch.setFont(Utils.jetmono);
        this.btnSwitch.setCursor(Utils.handcursor);
        this.btnSwitch.setText("<html>CADASTRAR OU EDITAR<br>RESPONSÁVEL?</html>");
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
        if(this.mainpanel.getResposavel().getId_responsavel() != null && this.mainpanel.getResposavel().getNome() != null) {
            this.mainpanel.getResponsavelDAO().deleteResponsavel(this.mainpanel.getResposavel().getId_responsavel());
            this.mainpanel.reloadComponentsProperties();
        }
    }

    private void btnRegistrarActionEvent(){
        System.out.println(this.mainpanel.getResposavel().hasValues());
        if(this.mainpanel.getResposavel().hasValues()){
            System.out.println(Arrays.toString(this.mainpanel.getResposavel().getValues()));
            this.mainpanel.getResponsavelDAO().insertResponsavel(this.mainpanel.getResposavel().getValues());
            this.mainpanel.getResposavel().resetValues();
            this.mainpanel.reloadComponentsProperties();
        }


    }

    private void btnEditarActionEvent(){

    }

    private void btnSwitchActionEvent(boolean bool) {

        this.btnCadastro[1].setVisible(!bool);
        this.btnCadastro[2].setVisible(bool);
        this.btnCadastro[3].setVisible(bool);
        this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().setEditable(bool);
        this.mainpanel.getCmbBoxes().getCmbBoxSexo().setEnabled(bool);
        this.mainpanel.getCmbBoxes().getCmbBoxSexo().setSelectedItem(null);
        this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().setSelectedItem(null);
        this.mainpanel.getSpnData().getSpnDate().setValue(new SimpleDateFormat(){{this.setCalendar(Utils.calendar);}}.getCalendar().getTime());
        this.mainpanel.getSpnData().getSpnDate().setEnabled(bool);

        if(this.stateChanged){
            this.changeState();
        }

        for (int i = 0; i < 3; i++) {
            this.mainpanel.getTxtFields().getTxtFields()[i].setEnabled(bool);
        }
        this.mainpanel.getTxtFields().getTxtFields()[1].setText("");
        this.mainpanel.getTxtFields().getTxtFields()[2].setText("21");

        if(bool){
            this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().setBackground(Utils.cmbGreen);
            this.mainpanel.getTxtFields().getTxtFields()[0].setText(String.valueOf(this.mainpanel.getResponsavelTable().getActualId() + 1));
            //this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().addItem("<Cadastrar novo responsável>");
            this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().insertItemAt("<Cadastrar novo responsável>", 0);
        } else {
            this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().setBackground(Utils.cmbRed);
            this.mainpanel.getCmbBoxes().getCmbBoxResponsavel().removeItem("<Cadastrar novo responsável>");
        }

    }

    public void changeState(){

        this.btnCadastro[2].setEnabled(!this.btnCadastro[2].isEnabled());
        this.btnCadastro[3].setEnabled(!this.btnCadastro[3].isEnabled());
        this.stateChanged = !this.stateChanged;

    }

    public JCheckBox getBtnSwitch() {
        return this.btnSwitch;
    }

    public JButton[] getBtnCadastro() {
        return this.btnCadastro;
    }

    public boolean isStateChanged() {
        return this.stateChanged;
    }
}
