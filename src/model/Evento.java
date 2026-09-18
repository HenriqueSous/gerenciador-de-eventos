package model;

public class Evento {
    private String nome;
    private int capacidadeMaxima;
    private Participante[] participantes;
    private Atividade[] atividades;

    public Evento(String nome, int capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.participantes = new Participante[capacidadeMaxima];
        this.atividades = new Atividade[50];
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public Participante[] getParticipantes() { return participantes; }
    public void setParticipantes(Participante[] participantes) { this.participantes = participantes; }

    public Atividade[] getAtividades() { return atividades; }
    public void setAtividades(Atividade[] atividades) { this.atividades = atividades; }
}
