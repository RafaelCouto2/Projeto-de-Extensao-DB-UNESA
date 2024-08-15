package com.extensionproject.app.domain.alunos;

import com.extensionproject.app.domain.responsavel.Responsavel;
import com.extensionproject.app.logger.LoggerManager;

public class Aluno {

    private String id_aluno, id_responsavel, nome, dt_nascimento = "DEFAULT", sexo, id_responsavel_if_changing;
    private boolean changingResp = false;

    public Aluno() {
    }

    public String getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(String id_aluno) {
        this.id_aluno = id_aluno;
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

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setChangingResp(boolean changingResp) {
        this.changingResp = changingResp;
    }

    public boolean isChangingResp() {
        return this.changingResp;
    }

    public String getId_responsavel_if_changing() {
        return id_responsavel_if_changing;
    }

    public void setId_responsavel_if_changing(String id_responsavel_if_changing) {
        this.id_responsavel_if_changing = id_responsavel_if_changing;
    }

    public boolean hasValues(){
        try {
            return (!this.getNome().isBlank() &&
                    !this.getId_responsavel().isBlank() &&
                    !this.getSexo().isBlank() &&
                    !this.getDt_nascimento().isBlank());
        } catch (NullPointerException ex) {
            LoggerManager.getClassLog(Responsavel.class).error(": HÁ VALORES VAZIOS. COMPLETE TODOS OS CAMPOS.");
            return false;
        }
    }

    public String[] getValues() {
        //if (this.hasValues()) {
            return new String[]{
                    this.id_responsavel,
                    this.id_aluno,
                    this.nome,
                    this.sexo,
                    this.dt_nascimento
            };
        //} else throw new RuntimeException();
    }

    public void nullifer(){
        this.id_aluno = null;
        this.sexo = null;
        this.id_responsavel = null;
        this.id_responsavel_if_changing = null;
        this.dt_nascimento = "DEFAULT";
        this.setChangingResp(false);
    }
}
