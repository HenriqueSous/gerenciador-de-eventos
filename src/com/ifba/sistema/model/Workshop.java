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
}
