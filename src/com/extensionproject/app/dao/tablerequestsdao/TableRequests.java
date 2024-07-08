package com.extensionproject.app.dao.tablerequestsdao;

import com.extensionproject.app.connect.statements.StatementsManager;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Vector;

public class TableRequests {

    private Vector<Vector<Object>>[] resultsSetData;
    private Vector<ResultSetMetaData> resultSetsMetaData;
    private Vector<Object>[] tablesColumnsName;
    private Vector<Vector<Object>>[] resultsSetsDataBackup;
    private Vector<ResultSet> resultSets;
    private final TableRequestsDAO tableRequestsDAO;

    public TableRequests(){
        this.tableRequestsDAO = new TableRequestsDAO();
    }

    // Obtém os ResultSet para as consultas SQL
    private void fetchResultsSets(String[] sqls){
        this.resultSets = new Vector<>();
        for (String sql : sqls) {
            this.resultSets.add(this.tableRequestsDAO.executeQuery(sql));
        }
    }

    // Obtém os meta-dados dos ResultSet
    private void fetchResultsSetMetaData(int totsqls){
        this.resultSetsMetaData = new Vector<>();
        for (int i = 0; i < totsqls; i++) {
            try {
                this.resultSetsMetaData.add(this.resultSets.get(i).getMetaData());
            } catch (SQLException e) {
                LoggerManager.getClassLog(TableRequests.class).error(": NÃO FOI POSSIVEL CAPTURAR OS RESULT SET.");
            }
        }
    }

    // Preenche os dados dos ResultSet em uma estrutura de dados
    private void fetchResultsSetData(int size){
        this.resultsSetData = new Vector[size];
        try {
            for (int i = 0; i < size; i++) {
                int totColumns = this.resultSetsMetaData.get(i).getColumnCount();
                this.resultsSetData[i] = new Vector<>();
                while (this.resultSets.get(i).next()) {
                    Vector<Object> row = new Vector<>();
                    for (int r = 0; r < totColumns; r++) {
                        row.add(this.resultSets.get(i).getObject(r + 1));
                    }
                    this.resultsSetData[i].add(row);
                }
            }
        } catch (SQLException e){
            LoggerManager.getClassLog(TableRequests.class).error(": ERRO AO PASSAR OS DADOS DOS RESULTS SETS PARA O VECTOR.");
        }
    }

    // Obtém os nomes das colunas dos ResultSet
    private void fetchTableColumnsName(int totsqls){
        this.tablesColumnsName = new Vector[totsqls];
        try {
            for (int i = 0; i < totsqls; i++) {
                int totColumns = this.resultSetsMetaData.get(i).getColumnCount();
                this.tablesColumnsName[i] = new Vector<>();
                for (int l = 0; l < totColumns; l++) {
                    this.tablesColumnsName[i].add(this.resultSetsMetaData.get(i).getColumnName(l + 1));
                }
            }
        } catch (SQLException e) {
            LoggerManager.getClassLog(TableRequests.class).error(": ERRO AO PASSAR OS NOMES DAS COLUNAS NO METADADOS PARA O VECTOR.");
        }

    }

    // Chama todos os métodos para a criação os resultados do db.
    public void tableRequest(String[] sqls){
        try {
            this.resultsSetsDataBackup = new Vector[sqls.length];
            this.fetchResultsSets(sqls);
            this.fetchResultsSetMetaData(sqls.length);
            this.fetchResultsSetData(sqls.length);
            this.fetchTableColumnsName(sqls.length);
            this.resultsSetsDataBackup = resultsSetData.clone();
        } finally {
            StatementsManager.closeStatement();
        }
    }

    public Vector<Vector<Object>> getResultsSetData(int indx) {
        return resultsSetData[indx];
    }

    public Vector<Vector<Object>>[] getResultsSetData() {
        return resultsSetData;
    }

    public Vector<Object>[] getTablesColumnsName() {
        return tablesColumnsName;
    }
}
