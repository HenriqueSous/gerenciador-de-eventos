package service;

import model.Atividade;
import model.Evento;
import model.Palestrante;
import model.Participante;

public class GerenciadorService {
    private Evento[] eventos;
    private int indiceEvento;

    public GerenciadorService() {
        this.eventos = new Evento[10];
        this.indiceEvento = 0;
    }

    public boolean criarEvento(Evento evento) {
        if (indiceEvento >= eventos.length) {
            return false; // Limite de eventos atingido
        }
        eventos[indiceEvento] = evento;
        indiceEvento++;
        return true;
    }

    public boolean adicionarAtividade(Evento evento, String nome, String local, String horario, int duracaoMinutos, Palestrante palestrante) {
        Atividade atividade = new Atividade(nome, local, horario, duracaoMinutos, palestrante, evento);

        if (!verificarConflitosEntreAtividades(evento, atividade)) {
            return evento.adicionarAtividade(atividade);
        }
        return false;
    }

    public boolean realizarInscricao(Evento evento, Participante participante) {
        if (!verificarParticipanteInscrito(participante, evento)) {
            return evento.cadastrarParticipante(participante);
        }
        return false;
    }

    public boolean verificarParticipanteInscrito(Participante participante, Evento evento) {
        Participante encontrado = evento.buscarParticipanteRecursivo(participante.getCpf(), 0);
        return encontrado != null;
    }

    public boolean verificarConflitosEntreAtividades(Evento evento, Atividade atividade) {
        for (Atividade atv : evento.getAtividades()) {
            if (atv != null) {
                if (atv.getLocal().equals(atividade.getLocal()) && atv.getHorario().equals(atividade.getHorario())) {
                    return true; // Conflito de horário e local detectado
                }
            }
        }
        return false;
    }

    public Evento[] getEventos() {
        return eventos;
    }
}
