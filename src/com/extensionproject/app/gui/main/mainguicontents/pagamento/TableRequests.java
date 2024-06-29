package com.extensionproject.app.gui.main.mainguicontents.pagamento;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.*;
import java.util.Vector;

public class TableRequests {

    private static Connection conn;
    private static Statement stm;
    private static Object[][] rowsData;
    private static Vector<Vector<Object>> tempData;
    private static Vector<ResultSet> resultSets;
    private static Vector<ResultSetMetaData> resultSetsMetaData;


    private static void tableRequest(String[] sqls){

        try {
            conn = FactoryConnection.getConn();
            resultSets = new Vector<>();
            resultSetsMetaData = new Vector<>();
            tempData = new Vector<>();

            //RESULT SETS DOS SQL[], PRINCIPAL.
            for (int i = 0; i < sqls.length; i++) {
                resultSets.add(conn.createStatement().executeQuery(sqls[i]));
                resultSetsMetaData.add(resultSets.get(i).getMetaData());
            }

            //TOTAL DE LINHAS E COLUNAS DOS RESULTS SET SQL[]
            int totColumns = resultSetsMetaData.get(0).getColumnCount();
            int rows = 0;
            while (resultSets.get(0).next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 0; i < totColumns; i++) {
                    row.add(resultSets.get(0).getObject(i+1));
                }
                tempData.add(row);
                rows++;
            }
//            rowsData = new Object[rows][totColumns];
//            for (int l = 0; l < tempData.size(); l++) {
//                for (int c = 0; c < tempData.get(l).size(); c++) {
//                    rowsData[l][c] = tempData.get(0).get(c);
//                }
//            }

        } catch (SQLException e) {
            LoggerManager.getClassLog(TableRequests.class).info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void pagamentoTableRequest(String[] sql) {
        tableRequest(sql);
        rowsData = new Object[tempData.size()][5];

        try {
            int[] nomesIndex = {0,0};
            for(int resultindex = 0; resultindex < 2; resultindex++) {
                for (int indx = 0; indx < resultSetsMetaData.elementAt(resultindex + 1).getColumnCount(); indx++) {
                    if (resultSetsMetaData.get(resultindex + 1).getColumnName(indx + 1).equals("nome")) {
                        nomesIndex[resultindex] = indx;
                    }
                }
            }
            for (int l = 0; l < tempData.size(); l++) {
                Object responsavel, aluno;
                resultSets.get(1).next();
                resultSets.get(2).next();
                for (int c = 0; c < tempData.get(l).size(); c++) {
                    switch(c){
                        case 0:
                            tempData.get(l).set(c, resultSets.get(1).getObject(1));
                            break;
                        case 1:
                            responsavel = resultSets.get(1).getObject(nomesIndex[0]+1);
                            tempData.get(l).set(c, responsavel);
                            break;
                        case 2:
                            aluno = resultSets.get(2).getObject(nomesIndex[1]+1);
                            tempData.get(l).add(tempData.get(l).size(), tempData.get(l).elementAt(c+1));
                            tempData.get(l).set(c+1, tempData.get(l).elementAt(c));
                            tempData.get(l).set(c, aluno);
                            break;
                    }

                    rowsData[l][c] = tempData.get(l).get(c);
                }
            }

        } catch(SQLException e){
            LoggerManager.getClassLog(TableRequests.class).info(e.getMessage());
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e){
            LoggerManager.getClassLog(TableRequests.class).info(e.getMessage());

        }

    }

    public static void requestTableInfo(String sql) throws SQLException { //USANDO NO BOTAO

        Connection conn = FactoryConnection.getConn();
        stm = conn.createStatement();
        ResultSet resultSet = stm.executeQuery(sql);
        resultSet.next();
        System.out.println(resultSet.getString(3));

    }

    public static Object[][] getRowsData() {
        return rowsData;
    }

    public static Vector<Vector<Object>> getTempData() {
        return tempData;
    }
}
