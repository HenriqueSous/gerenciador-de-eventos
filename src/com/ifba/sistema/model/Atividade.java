package com.ifba.sistema.model;

public abstract class Atividade {
    private String nome;
    private String local;
    private String horario;
    private int duracaoMinutos;
    private Ministrante ministrante;
    private Evento evento;

    public Atividade(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento) {
        this.nome = nome;
        this.local = local;
        this.horario = horario;
        this.duracaoMinutos = duracaoMinutos;
        this.ministrante = ministrante;
        this.evento = evento;
    }

    public abstract void exibirAtividade();

    public String getNome() { return nome; }

    public String getLocal() { return local; }

    public String getHorario() { return horario; }

    public int getDuracaoMinutos() { return duracaoMinutos; }

    public Ministrante getMinistrante() { return ministrante; }

    public Evento getEvento() { return evento; }
}
