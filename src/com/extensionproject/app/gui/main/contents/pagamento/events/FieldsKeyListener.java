package com.extensionproject.app.gui.main.contents.pagamento.events;

import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;
import com.extensionproject.app.logger.LoggerManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldsKeyListener implements KeyListener {

    private final PagamentoPanel mainpanel;
    private int id, dot;
    public FieldsKeyListener(int id, PagamentoPanel mainpanel){
        this.mainpanel = mainpanel;
        this.id = id;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (id) {
            case 3:
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    try {

                        // PRECISO RESOLVER ISSO, MAS HOJE ESTOU COM SONO.. DE MADRUGADA, MALUCO!
                        // O QUE TEM AQUI É GAMBIARRA TEMPORÁRIA!


                        //this.mainpanel.getPagamento().setValor(String.valueOf((this.mainpanel.getPtxtFields().getTxtFields()[3].getText())), "reais");

                    } catch (NumberFormatException ex) {
                        LoggerManager.getClassLog(FieldsKeyListener.class).error(": ENTRE SOMENTE COM NÚMEROS!");
                        throw new NumberFormatException(": NÃO FOI POSSÍVEL INSERIR O NÚMERO!");
                    }
                }
            break;
            case 4:
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    try {
                        //this.mainpanel.getPagamento().setValor(String.valueOf(Integer.parseInt(String.valueOf(e.getKeyChar()))), "centavos");


                    } catch (NumberFormatException ex) {
                        LoggerManager.getClassLog(FieldsKeyListener.class).error(": ENTRE SOMENTE COM NÚMEROS!");
                        throw new NumberFormatException(": NÃO FOI POSSÍVEL INSERIR O NÚMERO!");
                    }
                }
            break;
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
