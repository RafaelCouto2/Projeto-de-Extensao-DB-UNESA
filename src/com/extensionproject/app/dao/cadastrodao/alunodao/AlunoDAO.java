package com.extensionproject.app.dao.cadastrodao.alunodao;

import com.extensionproject.app.connect.statements.StatementsManager;
import com.extensionproject.app.dao.pagamentodao.PagamentoDAO;
import com.extensionproject.app.dao.tablerequestsdao.TableRequests;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;
import com.extensionproject.app.logger.LoggerManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AlunoDAO {
    private CadastroAluno main;
    private TableRequests tableRequests;
    public AlunoDAO(CadastroAluno main){
        this.main = main;
    }

    public void iniTableData(){
        this.tableRequests = new TableRequests();
        this.alunoTableRequest(new String[]{"select `id_alunoreferente`, `nome`, `id_responsavel`, `sexo`," +
                " DATE_FORMAT(`dt_nascimento`, '%d/%m/%Y') as `dt_nascimento` from `aluno`;",
                "select `nome`, `id_responsavel` from `responsavel`;"});
    }

    public void deletarAluno(String[] ids){
        try(Statement stmt = StatementsManager.createStatement()){
            if (stmt == null) {
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            stmt.executeUpdate("delete from `extpj`.`aluno` where id_responsavel = " + ids[1] + " and id_alunoreferente = "+ ids[0] + ";");
            LoggerManager.getClassLog(PagamentoDAO.class).info(": CADASTRO DO ALUNO DELETADO COM SUCESSO!");
        } catch (SQLException e) {
            LoggerManager.getClassLog(AlunoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL DELETAR O CADASTRO DO ALUNO.");
        }
    }

    public void insertAluno(String[] dados){
        try(Statement stmt = StatementsManager.createStatement()){
            String dt_nasc = "";
            if (stmt == null){
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            if(dados[4].equals("DEFAULT")){
                dt_nasc = "DEFAULT";
            } else dt_nasc = "STR_TO_DATE('" + dados[4] + "', '%d/%m/%Y')";

            stmt.executeUpdate("insert into `extpj`.`aluno` values (" +
                    dados[0] +
                    ", '"  + dados[1] +
                    "', '" + dados[2] +
                    "', '"  + dados[3] +
                    "', "   + dt_nasc +
                    ");");
            LoggerManager.getClassLog(PagamentoDAO.class).info(": ALUNO CADASTRADO COM SUCESSO!");
        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL CADASTRAR DO ALUNO.");
        }
    }

    public void updateAluno(String[] dados, boolean trocando){
        try(Statement stmt = StatementsManager.createStatement()){
            String dt_nasc = "";
            if (stmt == null){
                throw new IllegalStateException("Falha ao criar o Statement.");
            }
            if(dados[4].equals("DEFAULT")){
                dt_nasc = "DEFAULT";
            } else dt_nasc = "STR_TO_DATE('" + dados[4] + "', '%d/%m/%Y')";

            stmt.executeUpdate("update `extpj`.`aluno` set `nome` = '" + dados[2] +
                    "', `sexo` = '"                + dados[3] +
                    "', `dt_nascimento` = "       + dt_nasc +
                    " where `id_responsavel` = " + dados[0] + " and `id_alunoreferente` = " + dados[1] + ";");

            LoggerManager.getClassLog(PagamentoDAO.class).info(": CADASTRO DO RESPONSÁVEL EDITADO COM SUCESSO!");

        } catch (SQLException e) {
            LoggerManager.getClassLog(PagamentoDAO.class).error(e.getMessage()+ ": NÃO FOI POSSÍVEL EDITAR O CADASTRO DO RESPONSÁVEL.");
        }
    }


    private void alunoTableRequest(String[] sql){
        this.tableRequests.tableRequest(sql);
        Vector<Vector<Object>> alunorequest = this.tableRequests.getResultsSetData()[0];
        Vector<Vector<Object>> responsavelrequest = this.tableRequests.getResultsSetData()[1];

        for(int i = 0; i < this.tableRequests.getResultsSetData()[0].size(); i++) {
            for (int l = 0; l < responsavelrequest.size(); l++) {
                if(alunorequest.get(i).get(2).equals(responsavelrequest.get(l).get(1))) {
                    alunorequest.get(i).add(1, alunorequest.get(i).get(2));
                    alunorequest.get(i).set(3, /* alunorequest.get(i).get(2) + ": " + */ responsavelrequest.get(l).get(0));
                    if(alunorequest.get(i).get(5) == null){
                        alunorequest.get(i).set(5, "--/--/----");
                    }

                }
            }

            if (alunorequest.get(i).get(4).equals("m")) {
                alunorequest.get(i).set(4, "Masculino");
            } else alunorequest.get(i).set(4, "Feminino");

        }
    }

    public TableRequests getTableRequests() {
        return tableRequests;
    }
}
