package model;

public class Evento {
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

    public boolean cadastrarParticipante(Participante participante) {
        if (indiceParticipante >= this.capacidadeMaxima) {
            return false; // Atingiu a capacidade máxima
        }
        this.participantes[indiceParticipante] = participante;
        this.indiceParticipante++;
        return true;
    }

    // Metodo recursivo
    public Participante buscarParticipanteRecursivo(String cpf, int indice) {
        if (indice >= this.participantes.length || this.participantes[indice] == null) {
            return null; // Caso base: fim do array ou posição vazia
        }
        if (this.participantes[indice].getCpf().equals(cpf)) {
            return this.participantes[indice]; // Caso base: encontrou
        }
        return buscarParticipanteRecursivo(cpf, indice + 1); // Chamada recursiva
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public Participante[] getParticipantes() { return participantes; }
    public Atividade[] getAtividades() { return atividades; }
}
