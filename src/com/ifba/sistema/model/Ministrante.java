package com.ifba.sistema.model;

public class Ministrante extends Pessoa{
    private String especialidade;

    public Ministrante(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
