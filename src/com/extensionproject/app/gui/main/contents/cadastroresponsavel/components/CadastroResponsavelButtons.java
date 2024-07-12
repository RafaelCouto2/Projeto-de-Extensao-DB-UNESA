package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Consumer;

public class CadastroResponsavelButtons {
    private final CadastroResponsavel mainpanel;
    private JButton[] btnCadastro;
    private JCheckBox btnSwitch;
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


        this.btnCadastro[1].setText("<html>DELETAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[1].addActionListener(e -> btnDeletarActionEvent());

        this.btnCadastro[2].setText("<html>REGISTRAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[2].addActionListener(e -> btnRegistrarActionEvent());

        this.btnCadastro[3].setText("<html>EDITAR<br>RESPONSÁVEL</html>");
        this.btnCadastro[3].addActionListener(e -> btnEditarActionEvent());

        this.btnSwitch.setText("CADASTRAR OU EDITAR<br>RESPONSÁVEL?");
        this.btnSwitch.addActionListener(e -> {
            if(btnSwitch.isSelected()){
                btnSwitchActionEvent(true);
            } else btnSwitchActionEvent(false);
        });
    }

    private void btnDeletarActionEvent(){

    }

    private void btnRegistrarActionEvent(){

    }

    private void btnEditarActionEvent(){

    }

    private void btnSwitchActionEvent(boolean bool) {

    }

}
