package com.extensionproject.app.connect.statements;

import com.extensionproject.app.connect.factoryconnection.FactoryConnection;
import com.extensionproject.app.logger.LoggerManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.SQLException;

public class StatementsManager {
    private static final Logger logstringstatement = LoggerManager.getClassLog(StatementsManager.class);
    public static void executeStringStatement(String stm) {
        try {
            logstringstatement.info("Executing statement: " + stm);
            FactoryConnection.getStatement().executeUpdate(stm);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
           e.getMessage();
           logstringstatement.info("Failed to execute statement. Theres no statement created previously.");
        }

    }
}
