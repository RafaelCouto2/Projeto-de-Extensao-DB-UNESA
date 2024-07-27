package com.extensionproject.app.gui.main;

import com.extensionproject.app.GuiLinker;
import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.gui.main.events.MainGuiBtnLogActionListener;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.gui.main.contents.pagamento.gui.PagamentoPanel;
import com.extensionproject.app.gui.main.events.MainGuiWindowEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

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
    private JCheckBox btnLog;
    public boolean canUpdate;
    private BufferedImage backGround;
    private static final int WIDTH = 900, HEIGHT = 640;

    private void initComponents(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/icon.png")));
        this.setIconImage(icon.getImage());

        this.btnEvnts();
        this.setContentPane(mainGui);
        this.mainGui.setBackground(new Color(241, 239, 249));
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.btnLog.addActionListener(new MainGuiBtnLogActionListener(this));
        this.setTitle("Escolinha da Tia Celeusa ");
        this.addWindowListener(new MainGuiWindowEvent());
        this.backGround();
        this.lblMenu.setOpaque(true);
        this.lblMenu.setBackground(new Color(255, 255, 255));
        this.setVisible(true);
    }

    private void backGround(){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("imgs/school_background.png");
            //this.backGround = ImageIO.read(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/school_background.png").getPath())));
            this.backGround = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MainGui(){
        this.initComponents();
    }

    private void btnEvnts() {

        this.btnPagamento.addActionListener(e -> {
            if (panelid != 1 && ConnectionManager.hasConnection()) {
                panelid = 1;
                canUpdate = true;
                new PagamentoPanel(this.windowField);
                this.repaint();
            }
        });

        this.btnResp.addActionListener(e -> {
            if (panelid != 3 && ConnectionManager.hasConnection()){
                panelid = 3;
                canUpdate = true;
                new CadastroResponsavel(this.windowField);
                this.repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawRects(g);
        this.drawTexts(g);
        this.drawImages(g);
        this.refresh();
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
        g.setFont(new Font("JetBrains Mono",Font.BOLD,13));

        switch (this.panelid){
            case 1:
                g.drawString("{ TABELA DE PAGAMENTOS }",this.getWidth() - 500, 45);
                break;
            case 2:

                break;
            case 3:
                g.drawString("{ TABELA DE RESPONSÁVEIS }",this.getWidth() - 500, 45);
                break;
        }
    }

    private void drawImages(Graphics g){
        switch (this.panelid){
            case -1:
                g.drawImage(backGround, 430, 340, null);
                break;
        }

    }

    private void refresh() {
        this.btnPagamento.repaint(20);
        this.btnAlunos.repaint(20);
        this.btnResp.repaint(20);
        this.lblMenu.repaint(20);
        this.btnLog.repaint(20);
    }

    public JCheckBox getBtnLog() {
        return this.btnLog;
    }
}


