package com.ifba.sistema.model;

public class Evento implements Inscrevivel{
    private String nome;
    private int capacidadeMaxima;
    private Participante[] participantes;
    private Atividade[] atividades;
    private int indiceAtividade;
    private int indiceParticipante;

    public Evento(String nome, int capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.participantes = new Participante[capacidadeMaxima];
        this.atividades = new Atividade[10];
        this.indiceAtividade = 0;
        this.indiceParticipante = 0;
    }

    public boolean adicionarAtividade(Atividade atividade) {
        if (indiceAtividade >= this.atividades.length) {
            return false; // Array de atividades cheio
        }
        this.atividades[indiceAtividade] = atividade;
        this.indiceAtividade++;
        return true;
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (getVagasDisponiveis() == 0) {
            return false; // Atingiu a capacidade máxima
        }
        this.participantes[indiceParticipante] = participante;
        this.indiceParticipante++;
        return true;
    }

    @Override
    public boolean isParticipanteInscrito(Participante participante) {
        return buscarParticipanteRecursivo(participante.getCpf(), 0) == null;
    }

    @Override
    public int getVagasDisponiveis() {
        return this.capacidadeMaxima - this.indiceParticipante;
    }

    public Participante buscarParticipanteRecursivo(String cpf, int indice) {
        if (indice >= this.participantes.length || indice >= this.indiceParticipante) {
            return null;
        }

        if (this.participantes[indice] != null && this.participantes[indice].getCpf().equals(cpf)) {
            return this.participantes[indice];
        }

        return buscarParticipanteRecursivo(cpf, indice + 1);
    }

    public String getNome() { return nome; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }

    public Participante[] getParticipantes() { return participantes; }

    public Atividade[] getAtividades() { return atividades; }
}
