package com.ifba.sistema.service;

import com.ifba.sistema.model.Atividade;
import com.ifba.sistema.model.Evento;
import com.ifba.sistema.model.Inscrevivel;
import com.ifba.sistema.model.Participante;

public class GerenciadorService {
    private Evento[] eventos;
    private int indiceEvento;

    public GerenciadorService() {
        this.eventos = new Evento[10];
        this.indiceEvento = 0;
    }

    public boolean criarEvento(Evento evento) {
        if (evento == null || indiceEvento >= eventos.length) {
            return false;
        }
        eventos[indiceEvento] = evento;
        indiceEvento++;
        return true;
    }

    public boolean realizarInscricao(Inscrevivel alvo, Participante participante) {
        if (alvo == null || participante == null) {
            return false;
        }
        if (!validarCpf(participante.getCpf()) || !validarEmail(participante.getEmail())) {
            return false;
        }
        return alvo.cadastrarParticipante(participante);
    }

    public boolean inscreverParticipanteEmAtividade(Atividade atividade, String cpfParticipante) {
        if (atividade == null || cpfParticipante == null) {
            return false;
        }
        Evento evento = atividade.getEvento();
        Participante participante = evento.buscarParticipanteRecursivo(cpfParticipante, 0);
        if (participante == null) {
            return false;
        }
        return realizarInscricao(atividade, participante);
    }

    public boolean adicionarAtividade(Atividade atividade) {
        if (atividade == null) {
            return false;
        }
        Evento evento = atividade.getEvento();

        if (verificarConflitosEntreAtividades(evento, atividade)) {
            return false;
        }
        if (verificarConflitoPalestrante(evento, atividade)) {
            return false;
        }
        if (!validarCpf(atividade.getMinistrante().getCpf())) {
            return false;
        }
        return evento.adicionarAtividade(atividade);
    }

    public boolean adicionarSubAtividade(Atividade atividadePai, Atividade subAtividade) {
        if (atividadePai == null || subAtividade == null) {
            return false;
        }
        if (!validarCpf(subAtividade.getMinistrante().getCpf())) {
            return false;
        }
        return atividadePai.adicionarSubAtividade(subAtividade);
    }

    private boolean verificarConflitosEntreAtividades(Evento evento, Atividade atividade) {
        for (Atividade atv : evento.getAtividades()) {
            if (atv != null) {
                if (atv.getLocal().equalsIgnoreCase(atividade.getLocal()) && atv.getHorario().equalsIgnoreCase(atividade.getHorario())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarConflitoPalestrante(Evento evento, Atividade novaAtividade) {
        for (Atividade atv : evento.getAtividades()) {
            if (atv != null) {
                if (atv.getMinistrante().getCpf().equals(novaAtividade.getMinistrante().getCpf()) &&
                        atv.getHorario().equalsIgnoreCase(novaAtividade.getHorario())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean validarEmail(String email) {
        return email != null && email.contains("@");
    }

    public Evento[] getEventos() {
        return eventos;
    }
}
