package com.extensionproject.app.DAO.pagamentoDAO;

import com.extensionproject.app.connect.factoryconnection.ConnectionManager;
import com.extensionproject.app.connect.statements.StatementsManager;
import com.extensionproject.app.general.TableRequests;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.SQLException;
import java.sql.Statement;

public class PagamentoDAO {

    private TableRequests tablerequest;

    public void iniTableData(){
        tablerequest = new TableRequests();
        this.pagamentoTableRequest(new String[] {"select `id_pagamento`,`id_responsavel`,`id_alunoreferente`," +
                "`valor_mensal`,DATE_FORMAT(`data_pagamento`, '%d/%m/%Y') as `data_pagamento` from `extpj`.`pagamento`;",
                "select `id_responsavel`,`nome` from `extpj`.`responsavel`;", "select * from `extpj`.`aluno`;"});

//        tablerequest.pagamentoTableRequest(new String[] {"select `id_pagamento`,`id_responsavel`,`id_alunoreferente`," +
//                "`valor_mensal`,DATE_FORMAT(`data_pagamento`, '%d/%m/%Y') as `data_pagamento` from `extpj`.`pagamento`;",
//                "select `id_responsavel`,`nome` from `extpj`.`responsavel`;", "select * from `extpj`.`aluno`;"});
    }

    public void deletePagamento(String id){

        try (Statement stmt = StatementsManager.createStatement()){
            if (stmt == null) {
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            stmt.executeUpdate("delete from `extpj`.`pagamento` where `id_pagamento` = " + id + ";");
                LoggerManager.getClassLog(PagamentoDAO.class).info(": REGISTRO DE PAGAMENTO DELETADO COM SUCESSO!");
            } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL DELETAR O REGISTRO DE PAGAMENTO.");
        }
    }
        ///{call reset_autoincrement('pagamento', 'id_pagamento')}

    public void insertPagamento(String[] infos){
        try (Statement stmt = StatementsManager.createStatement()) {
            if (stmt == null) {
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            stmt.executeUpdate("insert into `extpj`.`pagamento` values (" + infos[0] +
                    ", " + infos[1] +
                    ", " + infos[2] +
                    ", " + infos[3] +
                    ", " + infos[4] + ");");
            LoggerManager.getClassLog(PagamentoDAO.class).info(": NOVO PAGAMENTO REGRISTRADO!");
        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage() + ": NÃO FOI POSSÍVEL REGISTRAR O PAGAMENTO.");
        }
    }


    public void resetAutoIncrement() {
        ConnectionManager.prepareCall("call reset_autoincrement('pagamento', 'id_pagamento')");
        LoggerManager.getClassLog(PagamentoDAO.class).info("IDs DE PAGAMENTOS RESETADOS.");
    }

    public void pagamentoTableRequest(String[] sql) {
        this.tablerequest.tableRequest(sql);
        try {
            int[] nomesIndex = {0,0};
            for(int resultindex = 0; resultindex < 2; resultindex++) {
                for(int tableindx = 0; tableindx < this.tablerequest.getTablesColumnsName()[resultindex+1].size(); tableindx++){
                    if (this.tablerequest.getTablesColumnsName()[resultindex+1].get(tableindx).toString().equals("nome")) {
                        nomesIndex[resultindex] = tableindx;
                    }
                }
            }

            int resNext = 0;
            int aluNext = 0;
            for (int l = 0; l < this.tablerequest.getResultsSetData()[0].size(); l++) {
                Object responsavel, aluno;
                while (!this.tablerequest.getResultsSetData()[1].get(resNext).get(0).equals(this.tablerequest.getResultsSetData()[0].get(l).get(1))) {
                    resNext++;
                    if(resNext >= this.tablerequest.getResultsSetData()[1].size()){
                        resNext = 0;
                    }
                }
                while (!this.tablerequest.getResultsSetData()[2].get(aluNext).get(1).equals(this.tablerequest.getResultsSetData()[0].get(l).get(2)) ||
                        !this.tablerequest.getResultsSetData()[2].get(aluNext).get(0).equals(this.tablerequest.getResultsSetData()[0].get(l).get(1))) {
                    //resultsSetData[1].get(resNext).get(0) <- Mesmo que resultsSetData[0].get(l).get(1)
                    aluNext++;
                    if(aluNext >= this.tablerequest.getResultsSetData()[2].size()){
                        aluNext = 0;
                    }
                }
                for (int c = 0; c < this.tablerequest.getResultsSetData()[0].get(l).size(); c++) {
                    switch (c) {
                        case 1:
                            //ATENÇÃO: O ACRESCENTADOR DE ID AO NOME DO RESPONSÁVEL FICA AQUI.
                            responsavel = this.tablerequest.getResultsSetData()[0].get(l).get(1) + ": " + this.tablerequest.getResultsSetData()[1].get(resNext).get(nomesIndex[0]);
                            this.tablerequest.getResultsSetData()[0].get(l).set(c, responsavel);
                            break;
                        case 2:
                            aluno = this.tablerequest.getResultsSetData()[2].get(aluNext).get(nomesIndex[1]);
                            this.tablerequest.getResultsSetData()[0].get(l).set(c, aluno);
                            break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            LoggerManager.getClassLog(TableRequests.class).error(e.getMessage());

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

    public TableRequests getTablerequest() {
        return this.tablerequest;
    }
}

