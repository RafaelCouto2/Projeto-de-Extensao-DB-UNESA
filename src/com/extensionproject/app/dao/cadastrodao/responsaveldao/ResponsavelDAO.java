package com.extensionproject.app.dao.cadastrodao.responsaveldao;

import com.extensionproject.app.connect.statements.StatementsManager;
import com.extensionproject.app.dao.pagamentodao.PagamentoDAO;
import com.extensionproject.app.dao.tablerequestsdao.TableRequests;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.SQLException;
import java.sql.Statement;

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

    public void insertResponsavel(){

    }

    public void deleteResponsavel(String id){
        try(Statement stmt = StatementsManager.createStatement()){
            if (stmt == null) {
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            int row = stmt.executeUpdate("delete from `extpj`.`responsavel` where id_responsavel = " + id + ";");
            LoggerManager.getClassLog(PagamentoDAO.class).info(": CADASTRO DO RESPONSÁVEL DELETADO COM SUCESSO!");
            LoggerManager.getClassLog(StatementsManager.class).info("ROWS AFFECTED: " + row);

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
