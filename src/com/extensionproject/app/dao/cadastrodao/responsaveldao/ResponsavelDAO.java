package com.extensionproject.app.dao.cadastrodao.responsaveldao;

import com.extensionproject.app.dao.tablerequestsdao.TableRequests;
import com.extensionproject.app.gui.main.contents.cadastroresponsavel.gui.CadastroResponsavel;

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

    public void responsavelTableRequest(String[] sql){
        this.tableRequests.tableRequest(sql);
        totRows = this.tableRequests.getResultsSetData()[0].size();
        for(int l = 0; l < this.tableRequests.getResultsSetData()[0].size(); l++){
            if (this.tableRequests.getResultsSetData()[0].get(l).get(2).equals("m")){
                this.tableRequests.getResultsSetData()[0].get(l).set(2, "Masculino");
            } else this.tableRequests.getResultsSetData()[0].get(l).set(2, "Feminino");
        }

        for (int i = 0; i < this.tableRequests.getResultsSetData()[0].size(); i++) {
            StringBuilder builder = new StringBuilder(String.valueOf(this.tableRequests.getResultsSetData()[0].get(i).get(4)));
            try {
                for (int l = 0; l < this.tableRequests.getResultsSetData()[0].get(i).get(4).toString().length(); l++) {
                    switch (l){
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
            } catch (NullPointerException e){
                this.tableRequests.getResultsSetData()[0].get(i).set(4, "(00)00000-0000");
                continue;
            }
        }
    }


    public TableRequests getTableRequests() {
        return this.tableRequests;
    }
}
