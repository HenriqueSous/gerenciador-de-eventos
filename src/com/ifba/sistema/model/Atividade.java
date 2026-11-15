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
    private Atividade[] subAtividades;
    private int indiceSubAtividade;

    public Atividade(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima) {
        this.nome = nome;
        this.local = local;
        this.horario = horario;
        this.duracaoMinutos = duracaoMinutos;
        this.ministrante = ministrante;
        this.evento = evento;
        this.capacidadeMaxima = Math.max(capacidadeMaxima, 0);
        this.indiceParticipante = 0;
        this.inscritos = new Participante[this.capacidadeMaxima];
        this.subAtividades = new Atividade[5];
        this.indiceSubAtividade = 0;
    }

    @Override
    public boolean cadastrarParticipante(Participante participante) {
        if (participante == null || getVagasDisponiveis() <= 0 || isParticipanteInscrito(participante)) {
            return false;
        }
        this.inscritos[indiceParticipante] = participante;
        this.indiceParticipante++;
        return true;
    }

    @Override
    public boolean isParticipanteInscrito(Participante participante) {
        if (participante == null) {
            return false;
        }
        for (int i = 0; i < this.indiceParticipante; i++) {
            if (this.inscritos[i] != null && this.inscritos[i].getCpf().equals(participante.getCpf())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVagasDisponiveis() {
        return this.capacidadeMaxima - this.indiceParticipante;
    }

    public boolean adicionarSubAtividade(Atividade subAtividade) {
        if (subAtividade == null || indiceSubAtividade >= this.subAtividades.length) {
            return false;
        }
        this.subAtividades[indiceSubAtividade] = subAtividade;
        this.indiceSubAtividade++;
        return true;
    }

    public void exibirHierarquiaRecursivo(int nivel) {
        String indentacao = "   ".repeat(nivel);
        System.out.println(indentacao + "--------------------------------");
        exibirAtividade(indentacao);

        for (int i = 0; i < this.indiceSubAtividade; i++) {
            if (this.subAtividades[i] != null) {
                this.subAtividades[i].exibirHierarquiaRecursivo(nivel + 1);
            }
        }
    }

    public abstract void exibirAtividade(String indentacao);

    public String getNome() { return nome; }
    public String getLocal() { return local; }
    public String getHorario() { return horario; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public Ministrante getMinistrante() { return ministrante; }
    public Evento getEvento() { return evento; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public int getIndiceParticipante() { return indiceParticipante; }
    public Participante[] getInscritos() { return inscritos; }
    public Atividade[] getSubAtividades() { return subAtividades; }
}
