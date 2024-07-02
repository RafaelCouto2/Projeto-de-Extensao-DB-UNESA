package com.extensionproject.app.domain.responsavel;

public class Responsaveis {
    private String id_responsavel, nome, cpf, telefone, sexo, dt_nascimento;
    public Responsaveis(){

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
