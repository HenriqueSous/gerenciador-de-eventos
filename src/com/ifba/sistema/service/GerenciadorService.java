package com.ifba.sistema.service;

import com.ifba.sistema.model.Atividade;
import com.ifba.sistema.model.Evento;
import com.ifba.sistema.model.Participante;

public class GerenciadorService {
    private Evento[] eventos;
    private int indiceEvento;

    public GerenciadorService() {
        this.eventos = new Evento[10];
        this.indiceEvento = 0;
    }

    public boolean criarEvento(Evento evento) {
        if (indiceEvento >= eventos.length) {
            return false;
        }
        eventos[indiceEvento] = evento;
        indiceEvento++;
        return true;
    }

    public boolean adicionarAtividade(Atividade atividade) {
        Evento evento = atividade.getEvento();

        if (verificarConflitosEntreAtividades(evento, atividade)) {
            return false;
        }

        if (verificarConflitoPalestrante(evento, atividade)) {
            return false;
        }

        if (!validarCpf(atividade.getPalestrante().getCpf())) {
            return false;
        }

        return evento.adicionarAtividade(atividade);
    }

    public boolean realizarInscricao(Evento evento, Participante participante) {
        if (!verificarParticipanteInscrito(participante, evento) && validarCpf(participante.getCpf())) {
            return evento.cadastrarParticipante(participante);
        }
        return false;
    }

    private boolean verificarConflitosEntreAtividades(Evento evento, Atividade atividade) {
        for (Atividade atv : evento.getAtividades()) {
            if (atv != null) {
                if (atv.getLocal().equals(atividade.getLocal()) && atv.getHorario().equals(atividade.getHorario())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarConflitoPalestrante(Evento evento, Atividade novaAtividade) {
        for (Atividade atv : evento.getAtividades()) {
            if (atv != null) {
                if (atv.getPalestrante().getCpf().equals(novaAtividade.getPalestrante().getCpf()) &&
                        atv.getHorario().equals(novaAtividade.getHorario())) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean verificarParticipanteInscrito(Participante participante, Evento evento) {
        Participante encontrado = evento.buscarParticipanteRecursivo(participante.getCpf(), 0);
        return encontrado != null;
    }

    private boolean validarCpf(String cpf) {
        return cpf.matches("\\d{11}");
    }

    public Evento[] getEventos() {
        return eventos;
    }
}
