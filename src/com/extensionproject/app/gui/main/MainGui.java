package com.extensionproject.app.gui.main;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.general.Utils;
import com.extensionproject.app.gui.main.mainguicontents.pagamento.PagamentoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class MainGui extends JFrame {
    private JPanel mainGui;
    private JPanel windowField;
    private PagamentoPanel pagamentoPanel;
    private int panelid = -1;
    private JButton tempButton;
    private JButton btnPagamento;
    private JLabel lblMenu;
    private JButton btnAluno;
    //private JButton btnAlunos;

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

    public void btnEvnts() {
        this.tempButton.addActionListener(e -> {
            GuiLinker.getMainGui().setVisible(false);
            GuiLinker.getLoginGui().setVisible(true);
        });

        this.btnPagamento.addActionListener(e -> {
            if (panelid != 1) {
                pagamentoPanel = new PagamentoPanel(this.windowField);
                panelid = 1;
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
        this.lblMenu.setBackground(new Color(75, 75, 75));
        this.btnPagamento.repaint();
        this.btnAluno.repaint();
        this.lblMenu.repaint();
    }

    public void drawRects(Graphics g){
        g.setColor(new Color(166, 166, 166));
        g.fillRect(10, 30, this.lblMenu.getWidth() + 60, this.getHeight() - 40);
        g.setColor(new Color(75, 75, 75));
        g.fillRect(11, 30, this.getWidth() - 20, this.lblMenu.getHeight() + 10);
        g.setColor(new Color(1, 1, 1));
        g.fillRect(this.lblMenu.getX() + 125, 30, 1, this.getHeight() - 40);

    }

    public void drawTexts(Graphics g){
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial",Font.BOLD,12));

        switch (this.panelid){
            case 1:
                g.drawString("LISA DE PAGAMENTOS DOS RESPONSÁVEIS DE CADA ALUNO",this.getWidth() - 550, 50);
                break;
            case 2:

                break;
        }

    }
}
