package com.extensionproject.app.connect.factoryconnection;

import com.extensionproject.app.gui.main.MainGui;
import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryConnection {

    private static Connection conn;
    private static Statement statement;
    private static String usern, passw;

    private static final Logger logconnectionmanager = LoggerManager.getClassLog(ConnectionManager.class);
    private static final Logger logfactoryconnection = LoggerManager.getClassLog(FactoryConnection.class);

//    public static Statement createStatement()  {
//        try {
//            statement = conn.createStatement();
//            logfactoryconnection.info(": STATEMENT READY!");
//            return statement;
//        } catch (SQLException e) {
//            logfactoryconnection.error(": FAILED TO CREATE STATEMENT!");
//            try {
//                if (!statement.isClosed()) {
//                    statement.close();
//                    logfactoryconnection.info(FactoryConnection.class + ": STATEMENT CLOSED.");
//                }
//                } catch (SQLException ex) {
//                logfactoryconnection.error(ex.getCause() + ": FAILED TO CLOSE STATEMENT.");
//            }
//            logfactoryconnection.error(e.getCause());
//            throw new RuntimeException(e){{LoggerManager.getClassLog(FactoryConnection.class).error(": RUNTIME EXCEPTION!");}};
//        }
//    }

//    //public static Connection getConn() {
//        return conn;
//    }

    public static Statement getStatement() throws NullPointerException, SQLException {
        return statement;
    }

    public static void closeStatement(){
        try {
            if(!statement.isClosed()) statement.close();
        } catch (SQLException e) {
            logfactoryconnection.error(e.getCause() + ": FAILED TO CLOSE STATEMENT!");
        }
    }

}
