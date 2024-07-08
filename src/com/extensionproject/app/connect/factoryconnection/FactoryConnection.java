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
    private static final Logger logfactoryconnection = LoggerManager.getClassLog(FactoryConnection.class);

    public static void closeStatement(){
        try {
            if(!statement.isClosed()) statement.close();
        } catch (SQLException e) {
            logfactoryconnection.error(e.getCause() + ": FAILED TO CLOSE STATEMENT!");
        }
    }

}
