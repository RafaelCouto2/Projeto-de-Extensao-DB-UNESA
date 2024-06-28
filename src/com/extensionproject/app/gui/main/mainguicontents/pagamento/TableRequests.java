package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import java.util.ArrayList;
import java.util.Vector;

public class TableRequests {

    private static Object[][] rowsData = {{"1", "JOAO",
            "230,50", "2024-06-26"}, {"2", "1",
            "230,50", "2024-06-26"}};
    private static Object[] columnsName = {"ID", "RESPONSÁVEL", "ALUNO REFERENTE", "VALOR MENSAL", "DATA DO PAGAMENTO"};



    public static void pagamentoTableRequest(){


        rowsData = new Object[40][5];

        for (int l = 0; l < 40; l++){
            for (int c = 0; c < 1; c++){
                rowsData[l][c] = "1";
                rowsData[l][c+1] = "JOAO";
                rowsData[l][c+2] = "ALUNO-X";
                rowsData[l][c+3] = "230,50";
                rowsData[l][c+4] = "2024-06-26";
            }
        }

    }

    public void startRowsData(){
        //rowsData = new Object[40][5];
    }

    public static Object[] getColumnsName() {

        return columnsName;
    }

    public static Object[][] getRowsData() {
        return rowsData;
    }
}
