package com.extensionproject.app.connect.factoryconnection;

import com.extensionproject.app.gui.main.mainguicontents.pagamento.TableRequests;
import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class FactoryConnection {

    private static Connection conn;
    private static Statement statement;
    private static String usern, passw;

    private static final Logger logconnectionmanager = LoggerManager.getClassLog(ConnectionManager.class);
    private static final Logger logfactoryconnection = LoggerManager.getClassLog(FactoryConnection.class);

    public static Connection createConnection(String user, String pass) {

        try {
            logconnectionmanager.info(": TRYING TO CONNECT INTO DATABASE...");
            conn = ConnectionManager.getConnection(user, pass);
            logconnectionmanager.info(": SUCCESS!");
            usern = user;
            passw = pass;
            return conn;
        } catch (SQLException e) {
            logconnectionmanager.info(": FAILED TO CONNECT INTO DATABASE!");
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    logconnectionmanager.info(": CONNECTION CLOSED.");
                }
            } catch (SQLException ex) {
                logfactoryconnection.info(ex.getCause() + ": FAILED TO CLOSE CONNECTION.");
            }
            logfactoryconnection.info(e.getCause());
            throw new RuntimeException(e);
        }
    }

    public static Connection connect(){
        try {
            return ConnectionManager.getConnection(usern, passw);
        } catch (SQLException e) {
            logconnectionmanager.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Statement createStatement()  {
        try {
            statement = conn.createStatement();
            logfactoryconnection.info(": STATEMENT READY.");
            return statement;
        } catch (SQLException e) {
            logfactoryconnection.info(": FAILED TO CREATE STATEMENT!");
            try {
                if (!statement.isClosed()) {
                    statement.close();
                    logfactoryconnection.info(FactoryConnection.class + ": STATEMENT CLOSED.");
                }
                } catch (SQLException ex) {
                logfactoryconnection.info(ex.getCause() + ": FAILED TO CLOSE STATEMENT.");
            }
            logfactoryconnection.info(e.getCause());
            throw new RuntimeException(e){{LoggerManager.getClassLog(TableRequests.class).info(": RUNTIME EXCEPTION!");}};
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static Statement getStatement() throws NullPointerException, SQLException {
        return statement;
    }

    public static void closeStatement(){
        try {
            if(!statement.isClosed()) statement.close();
        } catch (SQLException e) {
            logfactoryconnection.info(e.getCause() + ": FAILED TO CLOSE STATEMENT!");
        }
    }
}
