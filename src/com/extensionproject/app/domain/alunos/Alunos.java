package com.extensionproject.app.domain.alunos;

public class Alunos {

    private int id_aluno, id_responsavel;
    private String nome, dt_nascimento, telefone;
    private char sexo;

    public Alunos (int id_responsavel, int id_aluno, String nome, String telefone, char sexo, String data) {
        this.id_aluno = id_aluno;
        this.nome = nome;

    }

}
