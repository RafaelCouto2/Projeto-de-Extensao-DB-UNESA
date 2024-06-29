package com.extensionproject.app.gui.main;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.gui.main.mainguicontents.pagamento.PagamentoPanel;
import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame {
    private JPanel mainGui;
    private JPanel windowField;
    private PagamentoPanel pagamentoPanel;
    private int panelid = -1;
    private JButton tempButton;
    private JButton btnPagamento;
    private JLabel lblMenu;
    private JButton btnAluno;
    public boolean canUpdate;

    private static final int WIDTH = 800, HEIGHT = 600;

    public void initComponents(){
        this.btnEvnts();
        this.setContentPane(mainGui);
        this.mainGui.setBackground(Color.lightGray);
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
            if (panelid != 1) {
                panelid = 1;
                canUpdate = true;
                pagamentoPanel = new PagamentoPanel(this.windowField);
                updateScreen();
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.updateScreen();
        this.drawRects(g);
        this.drawTexts(g);
        this.lblMenu.setOpaque(true);
        this.lblMenu.setBackground(new Color(75, 75, 75));
        this.btnPagamento.repaint(10);
        this.btnAluno.repaint(10);
        this.lblMenu.repaint(10);

    }

    private void drawRects(Graphics g){

        g.setColor(new Color(166, 166, 166));
        g.fillRect(10, 30, this.lblMenu.getWidth() + 60, this.getHeight() - 40);
        g.setColor(new Color(75, 75, 75));
        g.fillRect(11, 30, this.getWidth() - 20, this.lblMenu.getHeight() + 10);
        g.setColor(new Color(1, 1, 1));
        g.fillRect(this.lblMenu.getX() + 125, 30, 1, this.getHeight() - 40);

        switch (panelid){
            case -1:

                break;
            case 1:
                this.pagamentoPanel.drawTableRect();
                this.pagamentoPanel.drawFieldsRect();
                break;
        }

    }

    private void drawTexts(Graphics g){
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial",Font.BOLD,12));

        switch (this.panelid){
            case -1:
                g.drawString("<-- SELECIONE ALGUMA INTERFACE NO MENU",this.getWidth() - 550, 50);
                break;
            case 1:
                g.drawString("TABELA DE PAGAMENTOS DOS RESPONSÁVEIS DE CADA ALUNO",this.getWidth() - 550, 50);
                break;
            case 2:

                break;
        }
    }

    public void updateScreen () {
        double timeUpdate = 1000000000.0 / 24;
        long pt = System.nanoTime();
        double deltaU = 0;
        while(canUpdate){
            long at = System.nanoTime();
            deltaU += (at - pt) / timeUpdate;
            if (deltaU > 1) {
                switch (panelid){
                    case 1:
                        this.repaint(50);
                        break;
                    case 2:
                        System.out.println("NOTHING HERE FOR WHILE... '-'");
                        break;
                    case 3:
                        System.out.println("NOTHING HERE FOR WHILE... :)");
                        break;
                }
                deltaU--;
                canUpdate = false;
            }
        }
    }
}
