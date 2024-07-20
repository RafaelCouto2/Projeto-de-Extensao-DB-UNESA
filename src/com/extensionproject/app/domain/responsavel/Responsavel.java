package com.extensionproject.app.domain.responsavel;

import com.extensionproject.app.logger.LoggerManager;

public class Responsavel {
    private String id_responsavel, nome, sexo, dt_nascimento, telefone;

    public Responsavel() {
        this.id_responsavel = "DEFAULT";
        this.telefone = "DEFAULT";
    }

    public boolean hasValues() {
        try {
            return (!this.getNome().isBlank() &&
                    !this.getTelefone().isBlank() &&
                    !this.getId_responsavel().isBlank() &&
                    !this.getSexo().isBlank() &&
                    !this.getDt_nascimento().isBlank());
        } catch (NullPointerException ex) {
            LoggerManager.getClassLog(Responsavel.class).error(": HÁ VALORES VAZIOS. COMPLETE TODOS OS CAMPOS.");
            return false;
        }
    }

    public void resetValues() {
        this.id_responsavel = "DEFAULT";
        this.nome = null;
        this.sexo = null;
        this.telefone = "DEFAULT";
        this.dt_nascimento = null;
    }

    public String[] getValues() {
        if (this.hasValues()) {
            return new String[]{
                        this.id_responsavel,
                        this.nome,
                        this.sexo,
                        this.dt_nascimento,
                        this.telefone
                    };
        } else throw new RuntimeException();
    }


    public String getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(String id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }
}
