package com.ifba.sistema.model;

public class Workshop extends Atividade{
    private String requisitosFerramenta;

    public Workshop(String nome, String local, String horario, int duracaoMinutos, Ministrante ministrante, Evento evento, String requisitosFerramenta) {
        super(nome, local, horario, duracaoMinutos, ministrante, evento);
        this.requisitosFerramenta = requisitosFerramenta;
    }

    @Override
    public void exibirAtividade() {
    }

    public String getRequisitosFerramenta() {
        return requisitosFerramenta;
    }

    @Override
    public void exibirAtividade() {
    System.out.println("Tipo: Workshop");
    System.out.println("Nome: " + getNome());
    System.out.println("Local: " + getLocal());
    System.out.println("Horário: " + getHorario());
    System.out.println("Duração: " + getDuracaoMinutos() + " min");
    System.out.println("Ministrante: " + getMinistrante().getNome());
    System.out.println("Ferramenta requisitada: " + requisitosFerramenta);
    }
}
