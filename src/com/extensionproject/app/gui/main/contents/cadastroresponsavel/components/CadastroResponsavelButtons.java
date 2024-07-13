package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;
import java.awt.*;
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

        this.btnSwitch.setFont(Utils.jetmono);
        this.btnSwitch.setCursor(Utils.handcursor);
        this.btnSwitch.setText("<html>CADASTRAR OU EDITAR<br>RESPONSÁVEL?</html>");
        this.btnSwitch.addActionListener(e -> {
            if(btnSwitch.isSelected()) {
                btnSwitchActionEvent(true);
            } else btnSwitchActionEvent(false);
        });

        this.mainpanel.getMainpanel().add(this.btnCadastro[0], this.mainpanel.getComponentsGrid()[3]);
        this.mainpanel.getMainpanel().add(this.btnCadastro[1], this.mainpanel.getComponentsGrid()[8]);

        this.mainpanel.getMainpanel().add(this.btnSwitch, this.mainpanel.getComponentsGrid()[7]);
    }

    private void btnDeletarActionEvent(){

    }

    private void btnRegistrarActionEvent(){

    }

    private void btnEditarActionEvent(){

    }

    private void btnSwitchActionEvent(boolean bool) {
        System.out.println(bool);


    }

}
