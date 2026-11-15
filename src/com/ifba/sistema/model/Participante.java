package com.ifba.sistema.model;

public class Participante extends Pessoa{
    private String email;

    public Participante(String nome, String cpf, String email) {
        super(nome, cpf);
        this.email = email;
    }

    @Override
    public String getDescricao() {
        return "Participante: " + getNome() + " | CPF: " + getCpf() + " | E-mail: " + email;
    }

    public String getEmail() {
        return email;
    }
}
