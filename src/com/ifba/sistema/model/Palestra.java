package com.ifba.sistema.model;

public class Palestra extends Atividade{
    private int tempoPerguntas;

    public Palestra(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int tempoPerguntas) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento);
        this.tempoPerguntas = tempoPerguntas;
    }

    @Override
    public void exibirAtividade() {
    }

    public int getTempoPerguntas() {
        return tempoPerguntas;
    }

    @Override
    public void exibirAtividade() {
    System.out.println("Tipo: Palestra");
    System.out.println("Nome: " + getNome());
    System.out.println("Local: " + getLocal());
    System.out.println("Horário: " + getHorario());
    System.out.println("Duração: " + getDuracaoMinutos() + " min");
    System.out.println("Ministrante: " + getMinistrante().getNome());
    System.out.println("Tempo de perguntas: " + tempoPerguntas + " min");
    }
}
