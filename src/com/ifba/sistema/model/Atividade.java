package com.ifba.sistema.model;

public abstract class Atividade implements Inscrevivel{
    private String nome;
    private String local;
    private String horario;
    private int duracaoMinutos;
    private Ministrante ministrante;
    private Evento evento;
    private int capacidadeMaxima;
    private int indiceParticipante;
    private Participante[] inscritos;

    public Atividade(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima) {
        this.nome = nome;
        this.local = local;
        this.horario = horario;
        this.duracaoMinutos = duracaoMinutos;
        this.ministrante = ministrante;
        this.evento = evento;
        this.capacidadeMaxima = capacidadeMaxima;
        this.indiceParticipante = 0;
        this.inscritos = new Participante[capacidadeMaxima];
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (getVagasDisponiveis() == 0 || isParticipanteInscrito(participante)) {
            return false; // Atingiu a capacidade máxima
        }
        this.inscritos[indiceParticipante] = participante;
        this.indiceParticipante++;
        return true;
    }

    @Override
    public boolean isParticipanteInscrito(Participante participante) {
        for (Participante inscrito : this.inscritos) {
            if (inscrito.getCpf().equals(participante.getCpf())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getVagasDisponiveis() {
        return this.capacidadeMaxima - this.indiceParticipante;
    }

    public abstract void exibirAtividade();

    public String getNome() { return nome; }

    public String getLocal() { return local; }

    public String getHorario() { return horario; }

    public int getDuracaoMinutos() { return duracaoMinutos; }

    public Ministrante getMinistrante() { return ministrante; }

    public Evento getEvento() { return evento; }

    public int getCapacidadeMaxima() {return capacidadeMaxima;}

    public int getIndiceParticipante() {return indiceParticipante;}

    public Participante[] getInscritos() {return inscritos;}
}
