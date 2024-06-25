package com.extensionproject.app.connect.factoryconnection;

import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryConnection {

    private static Connection conn;
    private static Statement statement;

    private static final Logger logconnectionmanager = LoggerManager.getClassLog(ConnectionManager.class);
    private static final Logger logfactoryconnection = LoggerManager.getClassLog(FactoryConnection.class);

    public static Connection connect(String user, String pass) {

        try {
            logconnectionmanager.info("TRYING TO CONNECT INTO DATABASE...");
            conn = ConnectionManager.getConnection(user, pass);
            logconnectionmanager.info("SUCCESS!");
            return conn;
        } catch (SQLException e) {
            logconnectionmanager.info("FAILED TO CONNECT INTO DATABASE!");
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.getCause();
                }
            }
            e.getCause();
            throw new RuntimeException(e);
        }

    }

    public static Statement createStatement(Connection conn)  {
        try {
            logfactoryconnection.info("Tyring to create a new statement.");
            statement = conn.createStatement();
            logfactoryconnection.info("Statement created and ready.");
            return statement;
        } catch (SQLException e) {
            logfactoryconnection.info("Failed to create statement!");
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.getCause();
                }
            }
            throw new RuntimeException(e);
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static Statement getStatement() throws NullPointerException {
        return statement;
    }
}
