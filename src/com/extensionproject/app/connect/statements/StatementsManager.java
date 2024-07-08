package com.extensionproject.app.connect.statements;

import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementsManager {
    private static final Logger logstringstatement = LoggerManager.getClassLog(StatementsManager.class);
    private static Statement stmt;

    public static Statement createStatement(){
        try {
            if(ConnectionManager.hasConnection()){
                stmt = ConnectionManager.getAppconn().createStatement();
                return stmt;
            } else return null;
        } catch (SQLException e) {
            LoggerManager.getClassLog(StatementsManager.class).error(": NÃO FOI POSSÍVEL CRIAR O STATEMENT.");
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String stm) {
        try {
            stmt.executeUpdate(stm);
        } catch (SQLException e) {
            logstringstatement.error(": NÃO FOI POSSÍVEL EXECUTAR A UPDATE.");
            throw new RuntimeException(e);
        } catch (NullPointerException e){
           e.getMessage();
           logstringstatement.info("NÃO HÁ STATEMENTS CRIADOS.");
        }
    }

    public static ResultSet executeQuery(String sqlstm){
        try {
            return stmt.executeQuery(sqlstm);
        } catch (SQLException e) {
            LoggerManager.getClassLog(StatementsManager.class).error("NÃO FOI POSSÍVEL EXECUTAR A QUERY.");
            throw new RuntimeException(e);
        }
    }

    public static void closeStatement(){
        try {
            stmt.close();
        } catch (SQLException e) {
            LoggerManager.getClassLog(StatementsManager.class).error("NÃO FOI POSSÍVEL FECHAR O STATEMENT.");
            throw new RuntimeException(e);
        }
    }

    public static Statement getStmt() {
        return stmt;
    }

}
