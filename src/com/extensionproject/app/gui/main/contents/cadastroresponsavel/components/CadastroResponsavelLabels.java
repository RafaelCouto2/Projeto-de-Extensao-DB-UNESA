package com.extensionproject.app.gui.main.contents.cadastroresponsavel.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

import javax.swing.*;

public class CadastroResponsavelLabels {
    private CadastroResponsavel mainpanel;
    private JLabel[] lblsInfo;

    private void initComponent() {
        this.lblsInfo = new JLabel[6];
        for (int i = 0; i < 6; i++){
            this.lblsInfo[i] =  new JLabel();
            this.lblsInfo[i].setFont(Utils.jetmono12);
            this.lblsInfo[i].setForeground(Utils.tableForeground);
        }
    }
    public CadastroResponsavelLabels(CadastroResponsavel mainpanel) {
        this.mainpanel = mainpanel;
        this.initComponent();
    }

    public void startLbls(){

        this.lblsInfo[0].setText("ID");
        this.lblsInfo[1].setText("NOME");
        this.lblsInfo[2].setText("SEXO");
        this.lblsInfo[3].setText("DDD");
        this.lblsInfo[4].setText("TELEFONE");
        this.lblsInfo[5].setText("DATA DE NASCIMENTO");
        this.mainpanel.getMainpanel().add(this.lblsInfo[0],this.mainpanel.getComponentsGrid()[11]);
        this.mainpanel.getMainpanel().add(this.lblsInfo[1],this.mainpanel.getComponentsGrid()[12]);
        this.mainpanel.getMainpanel().add(this.lblsInfo[2],this.mainpanel.getComponentsGrid()[13]);
        this.mainpanel.getMainpanel().add(this.lblsInfo[3],this.mainpanel.getComponentsGrid()[14]);
        this.mainpanel.getMainpanel().add(this.lblsInfo[4],this.mainpanel.getComponentsGrid()[15]);
        this.mainpanel.getMainpanel().add(this.lblsInfo[5],this.mainpanel.getComponentsGrid()[16]);

    }

}
