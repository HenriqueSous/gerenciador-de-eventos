package com.ifba.sistema.model;

public class Palestra extends Atividade{
    private int tempoPerguntas;

    public Palestra(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima, int tempoPerguntas) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento, capacidadeMaxima);
        this.tempoPerguntas = tempoPerguntas;
    }

    @Override
    public void exibirAtividade(String indentacao) {
        System.out.println(indentacao + "[PALESTRA] " + getNome());
        System.out.println(indentacao + "Local: " + getLocal() + " | Horário: " + getHorario() + " (" + getDuracaoMinutos() + " min)");
        System.out.println(indentacao + getMinistrante().getDescricao());
        System.out.println(indentacao + "Tempo para perguntas: " + tempoPerguntas + " min | Vagas: " + getVagasDisponiveis() + "/" + getCapacidadeMaxima());
    }

    public int getTempoPerguntas() {
        return tempoPerguntas;
    }
}
