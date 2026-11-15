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
        this.capacidadeMaxima = Math.max(capacidadeMaxima, 0);
        this.participantes = new Participante[this.capacidadeMaxima];
        this.atividades = new Atividade[10];
        this.indiceAtividade = 0;
        this.indiceParticipante = 0;
    }

    public boolean adicionarAtividade(Atividade atividade) {
        if (atividade == null || indiceAtividade >= this.atividades.length) {
            return false;
        }
        this.atividades[indiceAtividade] = atividade;
        this.indiceAtividade++;
        return true;
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (participante == null || getVagasDisponiveis() <= 0 || isParticipanteInscrito(participante)) {
            return false;
        }
        this.participantes[indiceParticipante] = participante;
        this.indiceParticipante++;
        return true;
    }

    @Override
    public boolean isParticipanteInscrito(Participante participante) {
        if (participante == null) {
            return false;
        }
        return buscarParticipanteRecursivo(participante.getCpf(), 0) != null;
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

    public void listarAtividadesRecursivo(int indice) {
        if (indice >= this.indiceAtividade || this.atividades[indice] == null) {
            return;
        }
        this.atividades[indice].exibirHierarquiaRecursivo(0);
        listarAtividadesRecursivo(indice + 1);
    }

    public String getNome() { return nome; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public Participante[] getParticipantes() { return participantes; }
    public Atividade[] getAtividades() { return atividades; }
    public int getIndiceAtividade() { return indiceAtividade; }
}
