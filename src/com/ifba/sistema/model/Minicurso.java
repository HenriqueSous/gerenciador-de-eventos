package com.ifba.sistema.model;

public class Minicurso extends Atividade{
    private boolean incluiCertificado;

    public Minicurso(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima, boolean incluiCertificado) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento, capacidadeMaxima);
        this.incluiCertificado = incluiCertificado;
    }

    @Override
    public void exibirAtividade(String indentacao) {
        System.out.println(indentacao + "[MINICURSO] " + getNome());
        System.out.println(indentacao + "Local: " + getLocal() + " | Horário: " + getHorario() + " (" + getDuracaoMinutos() + " min)");
        System.out.println(indentacao + getMinistrante().getDescricao());
        System.out.println(indentacao + "Inclui certificado: " + (incluiCertificado ? "Sim" : "Não") + " | Vagas: " + getVagasDisponiveis() + "/" + getCapacidadeMaxima());
    }

    public boolean getIncluiCertificado() {
        return incluiCertificado;
    }
}
