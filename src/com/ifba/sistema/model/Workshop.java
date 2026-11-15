package com.ifba.sistema.model;

public class Workshop extends Atividade{
    private String requisitosFerramenta;

    public Workshop(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, int capacidadeMaxima, String requisitosFerramenta) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento, capacidadeMaxima);
        this.requisitosFerramenta = requisitosFerramenta;
    }

    @Override
    public void exibirAtividade(String indentacao) {
        System.out.println(indentacao + "[WORKSHOP] " + getNome());
        System.out.println(indentacao + "Local: " + getLocal() + " | Horário: " + getHorario() + " (" + getDuracaoMinutos() + " min)");
        System.out.println(indentacao + getMinistrante().getDescricao());
        System.out.println(indentacao + "Ferramenta requisitada: " + requisitosFerramenta + " | Vagas: " + getVagasDisponiveis() + "/" + getCapacidadeMaxima());
    }

    public String getRequisitosFerramenta() {
        return requisitosFerramenta;
    }
}
