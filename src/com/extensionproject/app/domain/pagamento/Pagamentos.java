package com.extensionproject.app.domain.pagamento;

import com.extensionproject.app.logger.LoggerManager;

public class Pagamentos {
    private String id_pagamento, id_responsavel, id_alunoreferente, valor, data_pagamento;
    private boolean hasValues;
    public Pagamentos(){
        this.hasValues = false;
    }

    public String getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(String id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(String id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public String getId_alunoreferente() {
        return id_alunoreferente;
    }

    public void setId_alunoreferente(String id_alunoreferente) {
        this.id_alunoreferente = id_alunoreferente;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public boolean hasValues() {
        try {
            if (!this.getId_pagamento().isBlank() && !this.getId_responsavel().isBlank() && !this.getId_alunoreferente().isBlank()) return true;
            else {
                return false;
            }
        } catch (NullPointerException e) {
            LoggerManager.getClassLog(Pagamentos.class).error(": VALORES VAZIOS. COMPLETE TODOS OS CAMPOS.");
            return false;
        }
    }
}
