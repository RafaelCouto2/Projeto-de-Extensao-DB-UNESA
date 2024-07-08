package com.extensionproject.app.dao.tablerequestdao;

import com.extensionproject.app.connect.statements.StatementsManager;

import java.sql.ResultSet;

public class TableRequestDAO {
    public TableRequestDAO() {
        //StatementsManager.createStatement();
    }

    public ResultSet executeQuery(String sql){
        StatementsManager.createStatement();
        return StatementsManager.executeQuery(sql);
    }
}
