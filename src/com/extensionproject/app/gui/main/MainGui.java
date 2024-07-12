package com.extensionproject.app.gui.main;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;

import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame {
    private JPanel mainGui;
    private JPanel windowField;
    //private PagamentoPanel pagamentoPanel;
    private int panelid = -1;
    private JButton tempButton;
    private JButton btnPagamento;
    private JLabel lblMenu;
    private JButton btnAlunos;
    private JButton btnResp;
    public boolean canUpdate;
    private static final int WIDTH = 900, HEIGHT = 640;

    public void initComponents(){
        this.btnEvnts();
        this.setContentPane(mainGui);
        this.mainGui.setBackground(new Color(241, 239, 249));
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Escolinha da Tia Celeusa ");

        this.setVisible(true);
    }

    public MainGui(){
        this.initComponents();
    }

    private void btnEvnts() {

        this.tempButton.addActionListener(e -> {
            GuiLinker.getMainGui().setVisible(false);
            GuiLinker.getLoginGui().setVisible(true);
        });

        this.btnPagamento.addActionListener(e -> {
            if (panelid != 1 && ConnectionManager.hasConnection()) {
                panelid = 1;
                canUpdate = true;
                new PagamentoPanel(this.windowField);
//                windowField.revalidate();
//                windowField.repaint();
                this.repaint();
            }
        });

        this.btnResp.addActionListener(e -> {
            if (panelid != 3 && ConnectionManager.hasConnection()){
                panelid = 3;
                canUpdate = true;
                new CadastroResponsavel(this.windowField);
//                windowField.revalidate();
//                windowField.repaint();
                this.repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawRects(g);
        this.drawTexts(g);
        this.lblMenu.setOpaque(true);
        this.lblMenu.setBackground(new Color(255, 255, 255));
        this.lblMenu.setForeground(new Color(30, 31, 34));
        this.btnPagamento.repaint(10);
        this.btnAlunos.repaint(10);
        this.btnResp.repaint(10);
        this.lblMenu.repaint(10);

    }

    private void drawRects(Graphics g){

        g.setColor(new Color(255, 255, 255));
        //FAIXA ESQUERDA
        g.fillRect(10, 30, this.lblMenu.getWidth() + 65, this.getHeight() - 40);
        g.setColor(new Color(255, 255, 255));
        //FAIXA SUPERIOR
        g.fillRect(11, 30, this.getWidth() - 20, this.lblMenu.getHeight() + 10);
        g.setColor(new Color(229, 229, 233));
        //LINHAS
        g.fillRect(this.lblMenu.getX() + 130, 60 , 2, this.getHeight() - 40);
        g.fillRect(this.lblMenu.getX() + 130,60,this.getWidth(),2);


    }

    private void drawTexts(Graphics g){
        g.setColor(new Color(66,66,66));
        g.setFont(new Font("Arial",Font.BOLD,12));

        switch (this.panelid){
            case -1:
                g.drawString("<-- SELECIONE ALGUMA INTERFACE NO MENU",this.getWidth() - 550, 50);
                break;
            case 1:
                g.drawString("TABELA DE PAGAMENTOS DOS RESPONSÁVEIS DE CADA ALUNO",this.getWidth() - 600, 50);
                break;
            case 2:

                break;
        }
    }

    public void updateScreen () {

    }
}


