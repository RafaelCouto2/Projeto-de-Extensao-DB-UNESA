package com.extensionproject.app.dao.cadastrodao.responsaveldao;

import com.extensionproject.app.connect.statements.StatementsManager;
import com.extensionproject.app.dao.pagamentodao.PagamentoDAO;
import com.extensionproject.app.dao.tablerequestsdao.TableRequests;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ResponsavelDAO {

    private TableRequests tableRequests;
    private final CadastroResponsavel mainpanel;
    private int totRows;

    public ResponsavelDAO(CadastroResponsavel main){
        this.mainpanel = main;
    }
    public void iniTableData(){
        this.tableRequests = new TableRequests();
        this.responsavelTableRequest(new String[]{"select `id_responsavel`, `nome`, `sexo`, DATE_FORMAT(`dt_nascimento`, " +
                "'%d/%m/%Y') as `dt_nascimento`, `telefone` from `extpj`.`responsavel`;"});
    }

    public void insertResponsavel(String[] dados){
        try(Statement stmt = StatementsManager.createStatement()){
            String dt_nasc = "";
            if (stmt == null){
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            if(dados[3].equals("DEFAULT")){
                dt_nasc = "DEFAULT";
            } else dt_nasc = "STR_TO_DATE('" + dados[3] + "', '%d/%m/%Y')";

            stmt.executeUpdate("insert into `extpj`.`responsavel` values (" +
                             dados[0] +
                    ", '"  + dados[1] +
                    "', '" + dados[2] +
                    "', "  + dt_nasc +
                    ", "   + dados[4] +
                    ");");
            // "STR_TO_DATE('" + dados[3] + "', '%d/%m/%Y')"
            LoggerManager.getClassLog(PagamentoDAO.class).info(": RESPONSÁVEL CADASTRADO COM SUCESSO!");
        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL REGISTRAR O CADASTRO DO RESPONSÁVEL.");
        }
    }

    public void updateResponsavel(String[] dados){
        try(Statement stmt = StatementsManager.createStatement()){
            String dt_nasc = "";
            String tel = "";
            if (stmt == null){
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            if(dados[3].equals("DEFAULT")){
                dt_nasc = "DEFAULT";
            } else dt_nasc = "STR_TO_DATE('" + dados[3] + "', '%d/%m/%Y')";
            if(!dados[4].equals("DEFAULT")){
                StringBuilder bf = new StringBuilder(dados[4]);
                tel = bf.insert(0, "'").toString();
                tel = bf.insert(bf.length(), "'").toString();
            } else tel = "DEFAULT";
            stmt.executeUpdate("update `extpj`.`responsavel` set `nome` = '" + dados[1] +
                    "', `sexo` = '"                + dados[2] +
                    "', `dt_nascimento` = "       + dt_nasc +
                    ", `telefone` = "            + tel +
                    " where `id_responsavel` = " + dados[0] + ";");

            LoggerManager.getClassLog(PagamentoDAO.class).info(": CADASTRO DO RESPONSÁVEL EDITADO COM SUCESSO!");

        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL EDITAR O CADASTRO DO RESPONSÁVEL.");
        }
    }

    public void deleteResponsavel(String id){
        try(Statement stmt = StatementsManager.createStatement()){
            if (stmt == null) {
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            stmt.executeUpdate("delete from `extpj`.`responsavel` where id_responsavel = " + id + ";");
            LoggerManager.getClassLog(PagamentoDAO.class).info(": CADASTRO DO RESPONSÁVEL DELETADO COM SUCESSO!");

        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL DELETAR O CADASTRO DO RESPONSÁVEL.");
        }
    }

    public void responsavelTableRequest(String[] sql){
        try {
            this.tableRequests.tableRequest(sql);
            totRows = this.tableRequests.getResultsSetData()[0].size();
            for (int l = 0; l < this.tableRequests.getResultsSetData()[0].size(); l++) {
                if (this.tableRequests.getResultsSetData()[0].get(l).get(2).equals("m")) {
                    this.tableRequests.getResultsSetData()[0].get(l).set(2, "Masculino");
                } else this.tableRequests.getResultsSetData()[0].get(l).set(2, "Feminino");
            }

            for (int i = 0; i < this.tableRequests.getResultsSetData()[0].size(); i++) {
                StringBuilder builder = new StringBuilder(String.valueOf(this.tableRequests.getResultsSetData()[0].get(i).get(4)));
                if(this.tableRequests.getResultsSetData()[0].get(i).get(3) == null){
                    this.tableRequests.getResultsSetData()[0].get(i).set(3, "--/--/----");
                }
                try {
                    for (int l = 0; l < this.tableRequests.getResultsSetData()[0].get(i).get(4).toString().length(); l++) {
                        switch (l) {
                            case 0:
                                this.tableRequests.getResultsSetData()[0].get(i).set(4, builder.insert(l, "("));
                                break;
                            case 3:
                                this.tableRequests.getResultsSetData()[0].get(i).set(4, builder.insert(l, ")"));
                                break;
                            case 9:
                                this.tableRequests.getResultsSetData()[0].get(i).set(4, builder.insert(l, "-"));
                                break;
                        }
                    }

                } catch (NullPointerException e) {
                    this.tableRequests.getResultsSetData()[0].get(i).set(4, "(00)00000-0000");
                    continue;
                }
            }
        } finally {
            try {
                if (!StatementsManager.getStmt().isClosed()){
                    StatementsManager.getStmt().close();
                }
            } catch (SQLException e) {
                LoggerManager.getClassLog(TableRequests.class).error(e.getMessage() + ": NÃO HÁ STATEMENTS ABERTOS.");
            }
        }
    }


    public TableRequests getTableRequests() {
        return this.tableRequests;
    }
}
