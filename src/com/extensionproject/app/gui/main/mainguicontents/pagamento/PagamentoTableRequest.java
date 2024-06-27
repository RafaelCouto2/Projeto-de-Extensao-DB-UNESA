package com.extensionproject.app.gui.main.mainguicontents.pagamento;

public class PagamentoTableRequest {

    private static Object[][] rowsData = {{"1", "JOAO",
            "230,50", "2024-06-26"}, {"2", "1",
            "230,50", "2024-06-26"}};
    private static Object[] columnsName = {"ID PAGAMENTO", "RESPONSÁVEL", "VALOR MENSAL", "DATA DO PAGAMENTO"};

    public static void pagamentoTableRequest(){
        rowsData = new Object[40][4];
        for (int l = 0; l < 40; l++){
            for (int c = 0; c < 1; c++){
                rowsData[l][c] = "1";
                rowsData[l][c+1] = "JOAO";
                rowsData[l][c+2] = "230,50";
                rowsData[l][c+3] = "2024-06-26";
            }
        }
    }

    public static Object[] getColumnsName() {
        return columnsName;
    }

    public static Object[][] getRowsData() {
        return rowsData;
    }
}
