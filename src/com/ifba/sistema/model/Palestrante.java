package com.ifba.sistema.model;

public class Palestrante {
    private String nome;
    private String cpf;
    private String especialidade;

    public Palestrante(String nome, String cpf, String especialidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
