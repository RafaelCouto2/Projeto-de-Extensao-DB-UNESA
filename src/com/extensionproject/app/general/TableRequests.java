package com.extensionproject.app.general;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.logger.LoggerManager;

//import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Objects;
import java.util.Vector;

public class TableRequests {

    private static Vector<Vector<Object>>[] resultsSetData;
    private static Vector<ResultSetMetaData> resultSetsMetaData;
    private static Vector<Vector<Object>>[] resultsSetsDataBackup;

    private static void tableRequest(String[] sqls){
        try {
            Vector<ResultSet> resultSets = new Vector<>();
            resultSetsMetaData = new Vector<>();
            resultsSetData = new Vector[sqls.length];
            resultsSetsDataBackup = new Vector[sqls.length];
            //RESULT SETS DOS SQL[], PRINCIPAL.
            for (int i = 0; i < sqls.length; i++) {
                resultSets.add(Objects.requireNonNull(FactoryConnection.createStatement().executeQuery(sqls[i])));
                resultSetsMetaData.add(resultSets.get(i).getMetaData());
                LoggerManager.getClassLog(TableRequests.class).info(": CREATING RESULT SETS FOR: " + resultSetsMetaData.get(i).getTableName(1));
            }

            for (int i = 0; i < sqls.length; i++) {
                int totColumns = resultSetsMetaData.get(i).getColumnCount();
                resultsSetData[i] = new Vector<>();
                while (resultSets.get(i).next()){
                    Vector<Object> row = new Vector<>();
                    for (int r = 0; r < totColumns; r++){
                        row.add(resultSets.get(i).getObject(r+1));
                    }
                    resultsSetData[i].add(row);
                }
            }
            resultsSetsDataBackup = resultsSetData.clone();
        } catch (SQLException e) {
            LoggerManager.getClassLog(TableRequests.class).error(e.getMessage());
            throw new RuntimeException(e.getMessage()){{LoggerManager.getClassLog(TableRequests.class).error(": RUNTIME EXCEPTION!");}};
        } catch (NullPointerException e){
            LoggerManager.getClassLog(TableRequests.class).error(e.getMessage() + ": STATEMENT IS NULL.");
        }
    }

    public static void pagamentoTableRequest(String[] sql) {
        tableRequest(sql);
        try {
            int[] nomesIndex = {0,0};
            for(int resultindex = 0; resultindex < 2; resultindex++) {
                for (int indx = 0; indx < resultSetsMetaData.elementAt(resultindex + 1).getColumnCount(); indx++) {
                    if (resultSetsMetaData.get(resultindex + 1).getColumnName(indx + 1).equals("nome")) {
                        nomesIndex[resultindex] = indx;
                    }
                }
            }
            int resNext = 0;
            int aluNext = 0;
            for (int l = 0; l < resultsSetData[0].size(); l++) {
                Object responsavel, aluno;
                while (resultsSetData[1].get(resNext).get(0) != resultsSetData[0].get(l).get(1)) {
                    resNext++;
                    if(resNext >= resultsSetData[1].size()){
                        resNext = 0;
                    }
                }
                while (resultsSetData[2].get(aluNext).get(1) != resultsSetData[0].get(l).get(2) || resultsSetData[2].get(aluNext).get(0) != resultsSetData[0].get(l).get(1)) {
                    //resultsSetData[1].get(resNext).get(0) <- Mesmo que resultsSetData[0].get(l).get(1)
                    aluNext++;
                    if(aluNext >= resultsSetData[2].size()){
                        aluNext = 0;
                    }
                }
                for (int c = 0; c < resultsSetData[0].get(l).size(); c++) {
                    switch (c) {
                        case 1:
                            responsavel = resultsSetData[1].get(resNext).get(nomesIndex[0]);
                            resultsSetData[0].get(l).set(c, responsavel);
                            break;
                        case 2:
                            aluno = resultsSetData[2].get(aluNext).get(nomesIndex[1]);
                            resultsSetData[0].get(l).set(c, aluno);
                            break;
                    }
                }
            }
        } catch(SQLException e){
            LoggerManager.getClassLog(TableRequests.class).error(e.getMessage());
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e){
            LoggerManager.getClassLog(TableRequests.class).error(e.getMessage());

        } finally {
            try {
                if (!FactoryConnection.getStatement().isClosed()){
                    FactoryConnection.getStatement().close();
                }
            } catch (SQLException e) {
                LoggerManager.getClassLog(TableRequests.class).error(e.getMessage() + " -> THERES NO CONNECTION BETWEEN THIS APP AND DB!");
            }
        }
    }

    public static void requestTableInfo(String sql) throws SQLException { //USANDO NO BOTAO

        try {
            FactoryConnection.createStatement().executeUpdate(sql);
        } catch (NullPointerException e){
            LoggerManager.getClassLog(TableRequests.class).error(": STATEMENT IS NULL.");
        }
        FactoryConnection.closeStatement();
    }

    public static Vector<Vector<Object>> getResultsSetData(int indx) {
        return resultsSetData[indx];
    }

}
