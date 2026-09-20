package com.ifba.sistema.model;

public class Atividade {
    private String nome;
    private String local;
    private String horario;
    private int duracaoMinutos;
    private Palestrante palestrante;
    private Evento evento;

    public Atividade(String nome, String local, String horario, int duracaoMinutos, Palestrante palestrante, Evento evento) {
        this.nome = nome;
        this.local = local;
        this.horario = horario;
        this.duracaoMinutos = duracaoMinutos;
        this.palestrante = palestrante;
        this.evento = evento;
    }

    public String getNome() { return nome; }

    public String getLocal() { return local; }

    public String getHorario() { return horario; }

    public int getDuracaoMinutos() { return duracaoMinutos; }

    public Palestrante getPalestrante() { return palestrante; }

    public Evento getEvento() { return evento; }
}
