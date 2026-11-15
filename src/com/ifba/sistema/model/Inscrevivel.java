package com.ifba.sistema.model;

public interface Inscrevivel {
    boolean cadastrarParticipante(Participante participante);
    boolean isParticipanteInscrito(Participante participante);
    int getVagasDisponiveis();
}
