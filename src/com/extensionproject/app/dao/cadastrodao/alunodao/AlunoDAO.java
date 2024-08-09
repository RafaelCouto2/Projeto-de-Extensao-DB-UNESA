package com.extensionproject.app.dao.cadastrodao.alunodao;

import com.extensionproject.app.dao.tablerequestsdao.TableRequests;
import com.extensionproject.app.gui.main.contents.cadastroaluno.gui.CadastroAluno;

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

    private void alunoTableRequest(String[] sql){
        this.tableRequests.tableRequest(sql);
        Vector<Vector<Object>> alunorequest = this.tableRequests.getResultsSetData()[0];
        Vector<Vector<Object>> responsavelrequest = this.tableRequests.getResultsSetData()[1];

        for(int i = 0; i < this.tableRequests.getResultsSetData()[0].size(); i++) {
            for (int l = 0; l < responsavelrequest.size(); l++) {
                if(alunorequest.get(i).get(2).equals(responsavelrequest.get(l).get(1))){
                    alunorequest.get(i).add(1, alunorequest.get(i).get(2));
                    alunorequest.get(i).set(3, /* alunorequest.get(i).get(2) + ": " + */ responsavelrequest.get(l).get(0));
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
