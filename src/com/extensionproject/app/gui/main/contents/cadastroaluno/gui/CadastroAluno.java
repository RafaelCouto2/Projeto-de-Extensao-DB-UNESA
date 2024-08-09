package com.extensionproject.app.gui.main.contents.cadastroaluno.gui;

import com.extensionproject.app.dao.cadastrodao.alunodao.AlunoDAO;
import com.extensionproject.app.domain.alunos.Aluno;
import com.extensionproject.app.gui.main.contents.cadastroaluno.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class CadastroAluno extends JPanel {

    private Aluno aluno;
    private GridBagConstraints[] componentsGrid;
    private GridBagConstraints cadastrogrid;
    private JPanel mainpanel;
    private CadastroAlunoTable alunoTable;
    private AlunoDAO alunoDAO;
    private CadastroAlunoButtons btnCadastros;
    private CadastroAlunoCmbBoxes cmbBoxes;
    private CadastroAlunoLabels lblsInfo;
    private CadastroAlunoTxtFields txtFields;
    private CadastroAlunoSpinner spnDate;
    private void iniComponents(){
        this.setMainPanelLayout();
        this.startComponentsGrid();
        this.aluno = new Aluno();
        this.alunoDAO = new AlunoDAO(this);
        this.alunoTable = new CadastroAlunoTable(this);
        this.alunoTable.startTable();
        this.alunoTable.iniPn();
        this.btnCadastros = new CadastroAlunoButtons(this);
        this.btnCadastros.startBtns();
        this.spnDate = new CadastroAlunoSpinner(this);
        this.spnDate.startSpinner();
        this.txtFields = new CadastroAlunoTxtFields(this);
        this.txtFields.startTxtfields();
        this.cmbBoxes = new CadastroAlunoCmbBoxes(this);
        this.cmbBoxes.startCmbx();
        this.lblsInfo = new CadastroAlunoLabels(this);
        this.lblsInfo.startLbls();
    }
    public CadastroAluno(JPanel mainpanel){
        this.mainpanel = mainpanel;
        this.mainpanel.removeAll();
        this.iniComponents();
        this.mainpanel.revalidate();
        this.mainpanel.setVisible(true);
    }



    private void startComponentsGrid(){
        this.cadastrogrid = new GridBagConstraints();
        this.cadastrogrid.fill = GridBagConstraints.BOTH;
        this.cadastrogrid.gridx = 0;
        this.cadastrogrid.gridy = 0;
        this.cadastrogrid.gridwidth = 1;
        this.cadastrogrid.gridheight = 1;
        this.cadastrogrid.weightx = 1.0;
        this.cadastrogrid.weighty = 1.0;
        this.cadastrogrid.insets = new Insets(5,5,10,18);

        this.componentsGrid = new GridBagConstraints[17];
        for(int i = 0; i < 17; i++){
            this.componentsGrid[i] = new GridBagConstraints();
            this.componentsGrid[i].fill = GridBagConstraints.HORIZONTAL;
            this.componentsGrid[i].gridx = 0;
            this.componentsGrid[i].gridy = (i == 1) || (i == 2) ||
                    (i == 3) || (i == 6) ||
                    (i == 8) || (i == 9) ||
                    (i == 10) || (i == 14)||
                    (i == 15) || (i == 16)? 2: 1;

        }

        int[][] insets = {
                {1,5,-16,713}, // ID FIELD
                {5,110,20,545}, // TELEFONE FIELD
                {5,60,20,415}, // CMBOX NOMES(RESPONSÁVEIS)
                {1,5,16,710}, // BTN RELOAD
                {1,60,-20,415}, // CMBOX NOMES(ALUNO)
                {1,360,-20,300}, // CMBOX SEXO (ALUNO)
                {0,360,13,290}, // SPNDATA
                {1,480,-10,100}, // SWITCH
                {1,500,10,150}, // BTN DELETAR
                {1,500,10,150}, // BTN REGISTRAR
                {1,620,10,30}, //BTN EDITAR

                {2,7,29,713}, // LBL ID
                {2,60,29,315}, // LBL NOME DO ALU
                {2,360,29,300}, // LBL SEXO
                {5,60,60,540}, // LBL NOME DO RES
                {5,110,60,545}, // LBL TEL
                {5,360,60,260} // LBL DATA
        };

        for(int g = 0; g < 17; g++){
            this.componentsGrid[g].insets = new Insets(insets[g][0], insets[g][1],insets[g][2],insets[g][3]);
        }

    }

    private void setMainPanelLayout(){
        this.mainpanel.setLayout(new GridBagLayout());
    }

    public AlunoDAO getAlunoDAO() {
        return this.alunoDAO;
    }

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public GridBagConstraints getCadastrogrid() {
        return cadastrogrid;
    }

    public CadastroAlunoTable getAlunoTable() {
        return this.alunoTable;
    }

    public GridBagConstraints[] getComponentsGrid() {
        return this.componentsGrid;
    }

    public CadastroAlunoCmbBoxes getCmbBoxes() {
        return this.cmbBoxes;
    }

    public CadastroAlunoTxtFields getTxtFields() {
        return this.txtFields;
    }

    public CadastroAlunoSpinner getSpnDate() {
        return this.spnDate;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public CadastroAlunoButtons getBtnCadastros() {
        return this.btnCadastros;
    }
}
