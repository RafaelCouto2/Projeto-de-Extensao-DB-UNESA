package com.extensionproject.app.connect.factoryconnection;

import com.extensionproject.app.logger.LoggerManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection appconn;
    private static Connection getConnection(String user, String password) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/extpj";
        return DriverManager.getConnection(url, user, password);
    }

    public static void createConnection(String user, String pass) {

        try {
            appconn = getConnection(user, pass);
            LoggerManager.getClassLog(ConnectionManager.class).info("CONECTADO AO BANCO DE DADOS!");
        } catch (SQLException e) {
            LoggerManager.getClassLog(ConnectionManager.class).error("NÃO FOI POSSÍVEL SE CONECTAR.");
            LoggerManager.getClassLog(ConnectionManager.class).error(e);
            throw new RuntimeException(e);
        }

    }

    public static boolean hasConnection() {
        try {
            if (getAppconn() == null || getAppconn().isClosed()) {
                LoggerManager.getClassLog(FactoryConnection.class).info(": NÃO HÁ CONEXÃO COM O BANCO DE DADOS.");
                return false;
            } else return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void prepareCall(String call){
        try(CallableStatement callable = appconn.prepareCall(call)) {
            callable.execute();
        } catch (SQLException e) {
            LoggerManager.getClassLog(ConnectionManager.class).error(": NÃO FOI POSSÍVEL EXECUTAR UM CHAMADO A PROCEDURE DA DB.");
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            appconn.close();
        } catch (SQLException e) {
            LoggerManager.getClassLog(ConnectionManager.class).error(": NÃO FOI POSSÍVEL FECHAR A CONEXÃO.");
            throw new RuntimeException(e);
        }
    }


    public static Connection getAppconn() {
        return appconn;
    }


}
