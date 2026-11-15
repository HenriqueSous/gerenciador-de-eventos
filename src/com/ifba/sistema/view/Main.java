package com.ifba.sistema.view;

import com.ifba.sistema.model.*;
import com.ifba.sistema.service.GerenciadorService;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorService service = new GerenciadorService();
    private static Evento eventoSelecionado = null;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE EVENTOS ===");

        int opcao = -1;
        while (opcao != 0) {
            menu();
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    menuGerenciarEventos();
                    break;
                case 2:
                    selecionarEvento();
                    break;
                case 3:
                    cadastrarParticipante();
                    break;
                case 4:
                    cadastrarAtividade(false);
                    break;
                case 5:
                    cadastrarAtividade(true);
                    break;
                case 6:
                    inscreverParticipanteEmAtividade();
                    break;
                case 7:
                    buscarParticipante();
                    break;
                case 8:
                    listarAtividades();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void menu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        if (eventoSelecionado != null) {
            System.out.println("[ Evento Selecionado: " + eventoSelecionado.getNome() + " | Vagas: " + eventoSelecionado.getVagasDisponiveis() + " ]");
        } else {
            System.out.println("[ Nenhum evento selecionado ]");
        }
        System.out.println("[1] Gerenciar Eventos (Cadastrar / Ver)");
        System.out.println("[2] Selecionar/Alternar Evento Ativo");
        System.out.println("[3] Inscrever Participante no Evento");
        System.out.println("[4] Cadastrar Atividade no Evento");
        System.out.println("[5] Cadastrar Subatividade (Hierarquia)");
        System.out.println("[6] Inscrever Participante em uma Atividade");
        System.out.println("[7] Buscar Participante por CPF");
        System.out.println("[8] Listar Atividades do Evento (Recursivo)");
        System.out.println("[0] Sair");
    }

    private static void menuGerenciarEventos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE EVENTOS ===");
            System.out.println("[1] Cadastrar Novo Evento");
            System.out.println("[2] Listar Eventos Cadastrados");
            System.out.println("[0] Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarEvento() {
        System.out.println("\n=== CADASTRO DE EVENTO ===");
        System.out.print("Informe o nome do evento: ");
        String nomeEvento = scanner.nextLine();

        System.out.print("Informe a capacidade máxima de participantes: ");
        int capacidade = Integer.parseInt(scanner.nextLine());

        Evento novoEvento = new Evento(nomeEvento, capacidade);

        if (service.criarEvento(novoEvento)) {
            if (eventoSelecionado == null) {
                eventoSelecionado = novoEvento;
            }
            System.out.println("Evento '" + nomeEvento + "' cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o evento. Limite do sistema atingido.");
        }
    }

    private static void listarEventos() {
        Evento[] eventos = service.getEventos();
        boolean possuiEventos = false;

        System.out.println("\n=== LISTA DE EVENTOS ===");
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null) {
                possuiEventos = true;
                String indicadorAtivo = (eventos[i] == eventoSelecionado) ? " (SELECIONADO)" : "";
                System.out.println((i + 1) + ". " + eventos[i].getNome()
                        + " [Vagas: " + eventos[i].getVagasDisponiveis() + "/" + eventos[i].getCapacidadeMaxima() + "]" + indicadorAtivo);
            }
        }

        if (!possuiEventos) {
            System.out.println("Nenhum evento cadastrado.");
        }
    }

    private static void selecionarEvento() {
        Evento[] eventos = service.getEventos();
        boolean possuiEventos = false;

        System.out.println("\n=== EVENTOS DISPONÍVEIS ===");
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null) {
                possuiEventos = true;
                System.out.println("[" + (i + 1) + "] " + eventos[i].getNome());
            }
        }

        if (!possuiEventos) {
            System.out.println("Nenhum evento cadastrado no sistema.");
            return;
        }

        System.out.print("Selecione o número do evento desejado: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice >= 0 && indice < eventos.length && eventos[indice] != null) {
            eventoSelecionado = eventos[indice];
            System.out.println("Evento '" + eventoSelecionado.getNome() + "' selecionado!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarParticipante() {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        System.out.println("\n=== INSCRIÇÃO DE PARTICIPANTE NO EVENTO ===");
        System.out.print("Informe o nome do participante: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o CPF do participante (11 dígitos): ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o e-mail do participante: ");
        String email = scanner.nextLine();

        Participante participante = new Participante(nome, cpf, email);

        if (service.realizarInscricao(eventoSelecionado, participante)) {
            System.out.println("Participante inscrito no evento com sucesso!");
        } else {
            System.out.println("Não foi possível inscrever (Sem vagas, CPF já cadastrado, CPF inválido ou e-mail sem '@').");
        }
    }

    private static void cadastrarAtividade(boolean isSubAtividade) {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        Atividade atividadePai = null;
        if (isSubAtividade) {
            atividadePai = selecionarAtividadeDoEvento();
            if (atividadePai == null) {
                return;
            }
        }

        System.out.println(isSubAtividade ? "\n=== CADASTRO DE SUBATIVIDADE ===" : "\n=== CADASTRO DE ATIVIDADE ===");
        int tipoAtividade;
        while (true) {
            System.out.println("Informe o tipo de atividade: ");
            System.out.println("[0] Palestra");
            System.out.println("[1] Workshop");
            System.out.println("[2] Minicurso");
            System.out.print("Escolha uma opção: ");

            try {
                tipoAtividade = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                tipoAtividade = -1;
            }
            if (tipoAtividade >= 0 && tipoAtividade <= 2) {
                break;
            }
            System.out.println("Tipo de atividade inválida!");
        }

        System.out.print("Informe o nome da atividade: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o local da atividade: ");
        String local = scanner.nextLine();
        System.out.print("Horário: ");
        String horario = scanner.nextLine();
        System.out.print("Duração em minutos: ");
        int duracao = Integer.parseInt(scanner.nextLine());
        System.out.print("Capacidade máxima de vagas na atividade: ");
        int capacidadeAtividade = Integer.parseInt(scanner.nextLine());

        Ministrante ministrante = solicitarDadosMinistrante();

        Atividade atividade = null;
        switch (tipoAtividade) {
            case 0:
                System.out.print("Informe o tempo de perguntas em minutos: ");
                int tempoPerguntas = Integer.parseInt(scanner.nextLine());
                atividade = new Palestra(nome, local, horario, duracao, ministrante, eventoSelecionado, capacidadeAtividade, tempoPerguntas);
                break;
            case 1:
                System.out.print("Informe a ferramenta requisitada: ");
                String requisitoFerramenta = scanner.nextLine();
                atividade = new Workshop(nome, local, horario, duracao, ministrante, eventoSelecionado, capacidadeAtividade, requisitoFerramenta);
                break;
            case 2:
                System.out.print("O minicurso gera certificado? (S/N): ");
                boolean incluiCertificado = scanner.nextLine().trim().equalsIgnoreCase("S");
                atividade = new Minicurso(nome, local, horario, duracao, ministrante, eventoSelecionado, capacidadeAtividade, incluiCertificado);
                break;
        }

        boolean sucesso = isSubAtividade
                ? service.adicionarSubAtividade(atividadePai, atividade)
                : service.adicionarAtividade(atividade);

        if (sucesso) {
            System.out.println("Atividade cadastrada com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar a atividade (Limite atingido, conflito de horário/local ou CPF do ministrante inválido).");
        }
    }

    private static void inscreverParticipanteEmAtividade() {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        Atividade atividade = selecionarAtividadeDoEvento();
        if (atividade == null) {
            return;
        }

        System.out.print("Informe o CPF do participante já inscrito no evento: ");
        String cpf = scanner.nextLine();

        if (service.inscreverParticipanteEmAtividade(atividade, cpf)) {
            System.out.println("Participante inscrito na atividade '" + atividade.getNome() + "' com sucesso!");
        } else {
            System.out.println("Falha na inscrição (Participante não inscrito no evento, já inscrito na atividade ou sem vagas).");
        }
    }

    private static Atividade selecionarAtividadeDoEvento() {
        Atividade[] atividades = eventoSelecionado.getAtividades();
        boolean possui = false;

        System.out.println("\n=== ATIVIDADES DO EVENTO ===");
        for (int i = 0; i < eventoSelecionado.getIndiceAtividade(); i++) {
            if (atividades[i] != null) {
                possui = true;
                System.out.println("[" + (i + 1) + "] " + atividades[i].getNome() + " (Vagas: " + atividades[i].getVagasDisponiveis() + ")");
            }
        }

        if (!possui) {
            System.out.println("Nenhuma atividade cadastrada neste evento.");
            return null;
        }

        System.out.print("Selecione o número da atividade: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx >= 0 && idx < eventoSelecionado.getIndiceAtividade() && atividades[idx] != null) {
            return atividades[idx];
        }
        System.out.println("Atividade inválida.");
        return null;
    }

    private static Ministrante solicitarDadosMinistrante() {
        System.out.println("\n--- DADOS DO MINISTRANTE ---");
        System.out.print("Informe o nome do Ministrante: ");
        String nomePalestrante = scanner.nextLine();
        System.out.print("Informe o CPF do Ministrante (11 dígitos): ");
        String cpfPalestrante = scanner.nextLine();
        System.out.print("Informe a Especialidade do Ministrante: ");
        String especialidade = scanner.nextLine();

        return new Ministrante(nomePalestrante, cpfPalestrante, especialidade);
    }

    private static void buscarParticipante() {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        System.out.println("\n=== BUSCA DE PARTICIPANTE ===");
        System.out.print("Informe o CPF do participante: ");
        String cpf = scanner.nextLine();

        Participante p = eventoSelecionado.buscarParticipanteRecursivo(cpf, 0);

        if (p != null) {
            System.out.println("Participante encontrado:");
            System.out.println(p.getDescricao());
        } else {
            System.out.println("Participante não encontrado.");
        }
    }

    private static void listarAtividades() {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        System.out.println("\n=== ATIVIDADES DO EVENTO (" + eventoSelecionado.getNome() + ") ===");
        if (eventoSelecionado.getIndiceAtividade() == 0) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }

        eventoSelecionado.listarAtividadesRecursivo(0);
    }
}