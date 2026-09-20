package view;

import model.Atividade;
import model.Evento;
import model.Palestrante;
import model.Participante;
import service.GerenciadorService;

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
                    cadastrarAtividade();
                    break;
                case 5:
                    buscarParticipante();
                    break;
                case 6:
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
            System.out.println("[ Evento Selecionado: " + eventoSelecionado.getNome() + " ]");
        } else {
            System.out.println("[ Nenhum evento selecionado ]");
        }
        System.out.println("[1] Gerenciar Eventos (Cadastrar / Ver)");
        System.out.println("[2] Selecionar/Alternar Evento Ativo");
        System.out.println("[3] Cadastrar Participante (no evento selecionado)");
        System.out.println("[4] Cadastrar Atividade (no evento selecionado)");
        System.out.println("[5] Buscar Participante por CPF");
        System.out.println("[6] Listar Atividades do Evento Selecionado");
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
                System.out.println((i + 1) + ". " + eventos[i].getNome() + " [Capacidade: " + eventos[i].getCapacidadeMaxima() + "]" + indicadorAtivo);
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

        System.out.println("\n=== CADASTRO DE PARTICIPANTE ===");
        System.out.print("Informe o nome do participante: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o CPF do participante: ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o e-mail do participante: ");
        String email = scanner.nextLine();

        Participante p = new Participante(nome, cpf, email);

        if (service.realizarInscricao(eventoSelecionado, p)) {
            System.out.println("Participante cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar (Capacidade máxima atingida ou CPF já cadastrado).");
        }
    }

    private static void cadastrarAtividade() {
        if (eventoSelecionado == null) {
            System.out.println("Selecione ou cadastre um evento primeiro!");
            return;
        }

        System.out.println("\n=== CADASTRO DE ATIVIDADE ===");
        System.out.print("Informe o nome da atividade: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o local da atividade: ");
        String local = scanner.nextLine();
        System.out.print("Horário: ");
        String horario = scanner.nextLine();
        System.out.print("Duração em minutos: ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.println("\n--- DADOS DO PALESTRANTE ---");
        System.out.print("Informe o nome do palestrante: ");
        String nomePalestrante = scanner.nextLine();
        System.out.print("Informe o CPF do palestrante: ");
        String cpfPalestrante = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        Palestrante palestrante = new Palestrante(nomePalestrante, cpfPalestrante, especialidade);

        if (service.adicionarAtividade(eventoSelecionado, nome, local, horario, duracao, palestrante)) {
            System.out.println("Atividade cadastrada com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar a atividade (Limite atingido ou conflito de horário e local).");
        }
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
            System.out.println("Nome: " + p.getNome());
            System.out.println("CPF: " + p.getCpf());
            System.out.println("E-mail: " + p.getEmail());
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
        Atividade[] atividades = eventoSelecionado.getAtividades();
        boolean possuiAtividades = false;

        if (atividades != null) {
            for (Atividade atividade : atividades) {
                if (atividade != null) {
                    possuiAtividades = true;
                    System.out.println("--------------------------------");
                    System.out.println("Nome: " + atividade.getNome());
                    System.out.println("Local: " + atividade.getLocal());
                    System.out.println("Horário: " + atividade.getHorario());
                    System.out.println("Palestrante: " + atividade.getPalestrante().getNome());
                }
            }
        }

        if (!possuiAtividades) {
            System.out.println("Nenhuma atividade cadastrada.");
        }
    }
}