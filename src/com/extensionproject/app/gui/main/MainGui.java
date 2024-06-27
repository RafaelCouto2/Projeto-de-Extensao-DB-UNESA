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
        this.setTitle("Escolinha da Tia Celeusa " + Utils.getTime().get(Calendar.DAY_OF_MONTH) + "/" + (Utils.getTime().get(Calendar.MONTH)+1) + "/" + Utils.getTime().get(Calendar.YEAR));


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
            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(166, 166, 166));
        g.fillRect(10, 30, this.lblMenu.getWidth() + 60, this.getHeight() - 40);
        g.setColor(new Color(75, 75, 75));
        g.fillRect(11, 30, this.getWidth() - 20, this.lblMenu.getHeight() + 10);
        g.setColor(new Color(1, 1, 1));
        g.fillRect(this.lblMenu.getX() + 125, 30, 1, 30);


        this.lblMenu.setOpaque(true);
        this.lblMenu.setBackground(new Color(75, 75, 75));
        this.btnPagamento.repaint();
        this.btnAluno.repaint();
        this.lblMenu.repaint();
    }

}
