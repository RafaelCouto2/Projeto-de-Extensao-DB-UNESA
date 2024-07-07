package com.extensionproject.app.gui.main.contents.pagamento.components;

import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PagamentoPanelLabels {
    private JLabel[] lblInfo;
    private final PagamentoPanel mainpanel;
    public PagamentoPanelLabels(PagamentoPanel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public void startLbls() {
        this.lblInfo = new JLabel[6];
        for(int i = 0; i < 6; i++){
            int swapI = i;
            this.lblInfo[i] = new JLabel(){{
                setFont(Utils.jetmono);
                switch(swapI){
                    case 3:
                        setForeground(new Color(37, 105, 21));
                        break;
                    case 4:
                        setFont(new Font("JetBrains Mono", Font.BOLD, 24));
                        break;
                }
            }};
        }
        this.lblInfo[0].setText("   ID:");
        this.lblInfo[1].setText("RESPONSÁVEL:");
        this.lblInfo[2].setText("ALUNO:");
        this.lblInfo[3].setText("R$:");
        this.lblInfo[4].setText(",");
        this.lblInfo[5].setText("DATA:");
        Consumer<Integer> addlbls = lamb -> {
            for(int i = 0; i < this.lblInfo.length; i++) {
                this.mainpanel.getMainpanel().add(this.lblInfo[i], this.mainpanel.getComponentsGrid()[i+6]);
            }
            this.mainpanel.getMainpanel().add(this.lblInfo[5], this.mainpanel.getComponentsGrid()[14]);
        };
        addlbls.accept(1);
    }

}
