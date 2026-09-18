package model;

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
    public void setNome(String nome) { this.nome = nome; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public Palestrante getPalestrante() { return palestrante; }
    public void setPalestrante(Palestrante palestrante) { this.palestrante = palestrante; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
}
