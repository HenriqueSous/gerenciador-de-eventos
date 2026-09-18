package test;

import model.Atividade;
import model.Evento;
import model.Palestrante;
import model.Participante;
import service.GerenciadorService;

public class GerenciadorDeEventosTest01 {
    static void main() {
        GerenciadorService gerenciadorService = new GerenciadorService();
        Evento evento = new Evento("EVENTO MASSA", 2);

        Palestrante palestrante = new Palestrante("Inara", "567", "Corridas");
        gerenciadorService.criarEvento(evento);

        gerenciadorService.adicionarAtividade(evento, "Corrida", "VCA", "15:00", 50, palestrante);
        gerenciadorService.adicionarAtividade(evento, "Marcha", "VCA", "17:00", 90, palestrante);
        gerenciadorService.adicionarAtividade(evento, "Exercicios", "VCA", "17:01", 90, palestrante);

        Participante p1 = new Participante("Henrique", "123", "henrique@gmail.com");
        Participante p2 = new Participante("Pedrinho", "654", "pedrinho@gmail.com");
        Participante p3 = new Participante("marcelo", "6545", "marcelo@gmail.com");

        gerenciadorService.realizarInscricao(evento, p1);
        gerenciadorService.realizarInscricao(evento, p2);
        gerenciadorService.realizarInscricao(evento, p3);

        printEventos(gerenciadorService);
    }

    public static void printEventos(GerenciadorService gerenciadorService) {
        for (Evento e : gerenciadorService.getEventos()) {
            if (e != null) {
                System.out.println(e.getNome());
                System.out.println(e.getCapacidadeMaxima());
                System.out.println("Atividades:");
                for (Atividade a : e.getAtividades()) {
                    if (a != null) {
                        System.out.println("\t"+a.getNome());
                        System.out.println("\t"+a.getLocal());
                        System.out.println("\t"+a.getHorario());
                        System.out.println("\t"+a.getDuracaoMinutos());
                        System.out.println("\t"+a.getPalestrante());
                        System.out.println("--------------------------");
                    } else {
                        break;
                    }
                }

                System.out.println("Participantes:");
                for (Participante p : e.getParticipantes()) {
                    if (p != null) {
                        System.out.println("\t"+p);
                    } else {
                        break;
                    }
                }

            } else {
                break;
            }
        }
    }
}
