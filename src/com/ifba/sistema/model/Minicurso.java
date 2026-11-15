package com.ifba.sistema.model;

public class Minicurso extends Atividade{
    private boolean incluiCertificado;

    public Minicurso(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima, boolean incluiCertificado) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento, capacidadeMaxima);
        this.incluiCertificado = incluiCertificado;
    }

    @Override
    public void exibirAtividade() {
    }

    public boolean getIncluiCertificado() {
        return incluiCertificado;
    }
}
