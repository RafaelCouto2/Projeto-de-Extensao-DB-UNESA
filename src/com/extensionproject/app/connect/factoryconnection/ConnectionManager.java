package com.extensionproject.app.connect.factoryconnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection(String user, String password) throws SQLException {

        //user = "jdbcuser"
        //pass = "jdbcuser__8000"
        String url = "jdbc:mysql://localhost:3306/extpj";

        return DriverManager.getConnection(url, user, password);

    }
}
