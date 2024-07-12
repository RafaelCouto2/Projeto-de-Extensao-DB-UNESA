package com.extensionproject.app.gui.main.contents.cadastroaluno.gui;

import javax.swing.*;
import java.awt.*;

public class CadastroAluno extends JPanel {

    private GridBagConstraints[] componentsGrid;

    private void iniComponents(){
        this.setVisible(false);
        this.setLayout(new GridBagLayout());

    }
    public CadastroAluno(){
        this.iniComponents();
    }

    private void startComponentsGrid(){
        this.componentsGrid = new GridBagConstraints[5];
        for(int i = 0; i < 5; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = 1;
        }
    }

}
