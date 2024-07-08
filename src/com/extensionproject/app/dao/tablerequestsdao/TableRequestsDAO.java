package com.extensionproject.app.dao.tablerequestsdao;

import com.extensionproject.app.connect.statements.StatementsManager;

import java.sql.ResultSet;

public class TableRequestsDAO {
    public TableRequestsDAO() {
        //StatementsManager.createStatement();
    }

    public ResultSet executeQuery(String sql){
        StatementsManager.createStatement();
        return StatementsManager.executeQuery(sql);
    }
}
