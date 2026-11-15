package com.ifba.sistema.model;

public class Minicurso extends Atividade{
    private boolean incluiCertificado;

    public Minicurso(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, boolean incluiCertificado) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento);
        this.incluiCertificado = incluiCertificado;
    }

    @Override
    public void exibirAtividade() {
    }

    public boolean getIncluiCertificado() {
        return incluiCertificado;
    }

    @Override
    public void exibirAtividade() {
    System.out.println("Tipo: Minicurso");
    System.out.println("Nome: " + getNome());
    System.out.println("Local: " + getLocal());
    System.out.println("Horário: " + getHorario());
    System.out.println("Duração: " + getDuracaoMinutos() + " min");
    System.out.println("Ministrante: " + getMinistrante().getNome());
    System.out.println("Gera certificado: " + (incluiCertificado ? "Sim" : "Não"));
    }
}
