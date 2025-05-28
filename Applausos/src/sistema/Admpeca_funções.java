package sistema;

import usuario.InfoEnsaio;
import util.PersistenceUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Admpeca_funções {
    private static Scanner entrada = new Scanner(System.in);

   
    private static List<InfoEnsaio> listaEnsaios =
        PersistenceUtil.loadList("ensaio.ser");

    public static void opcaoAdmPeca(String nome,
                                    String email,
                                    String telefone,
                                    String cpf,
                                    String senha) {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Ensaios =====");
            System.out.println("1. Cadastrar ensaio");
            System.out.println("2. Listar ensaios");
            System.out.println("3. Ver agenda de ator");
            System.out.println("4. Editar perfil");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1 -> cadastrarEnsaio();
                case 2 -> listarEnsaios();
                case 3 -> visualizarAgendaAtor();
                case 4 -> editarPerfilAdmPeca(
                               nome, email, telefone, cpf, senha);
                case 5 -> {
                    PersistenceUtil.saveList(
                        listaEnsaios, "ensaio.ser");
                    System.out.println("Retornando ao menu principal...");
                }
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void cadastrarEnsaio() {
        System.out.println("==== CADASTRO DE ENSAIO ====");
        try {
            System.out.print("Dia (dd): ");
            int dia = Integer.parseInt(entrada.nextLine());
            System.out.print("Mês (MM): ");
            int mes = Integer.parseInt(entrada.nextLine());
            System.out.print("Ano (yyyy): ");
            int ano = Integer.parseInt(entrada.nextLine());
            LocalDate data = LocalDate.of(ano, mes, dia);

            System.out.print("Hora (HH:mm): ");
            LocalTime hora = LocalTime.parse(entrada.nextLine());

            System.out.print("Local: ");
            String local = entrada.nextLine();

            System.out.print("Feedback: ");
            String feedback = entrada.nextLine();

            System.out.print("Quantos membros receberão este ensaio? ");
            int qtd = Integer.parseInt(entrada.nextLine());
            List<String> membros = new java.util.ArrayList<>();
            for (int i = 0; i < qtd; i++) {
                System.out.print("E-mail do membro " + (i + 1) + ": ");
                membros.add(entrada.nextLine().trim());
            }

            listaEnsaios.add(new InfoEnsaio(
                data, hora, local, feedback, membros));
            System.out.println("Ensaio cadastrado com sucesso!");
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Formato de data/hora inválido. Tente novamente.");
        }
    }

    private static void listarEnsaios() {
        System.out.println("==== LISTA DE ENSAIOS ====");
        if (listaEnsaios.isEmpty()) {
            System.out.println("Nenhum ensaio cadastrado.");
        } else {
            for (int i = 0; i < listaEnsaios.size(); i++) {
                InfoEnsaio e = listaEnsaios.get(i);
                System.out.println("Ensaio " + (i + 1) +
                    ": Data " + e.getDataFormatada() +
                    ", Hora " + e.getHoraFormatada() +
                    ", Local: " + e.getLocal() +
                    ", Feedback: " + e.getFeedback() +
                    ", Membros: " + e.getMembros());
            }
        }
    }

    private static void visualizarAgendaAtor() {
        System.out.print("Digite o e-mail do ator: ");
        String email = entrada.nextLine().trim();
        boolean encontrou = false;
        System.out.println("Ensaios agendados para " + email + ":");
        for (InfoEnsaio e : listaEnsaios) {
            if (e.getMembros().contains(email)) {
                encontrou = true;
                System.out.println("Data: " + e.getDataFormatada() +
                    ", Hora: " + e.getHoraFormatada() +
                    ", Local: " + e.getLocal());
            }
        }
        if (!encontrou) {
            System.out.println(
                "Nenhum ensaio encontrado para este e-mail.");
        }
    }

    private static void editarPerfilAdmPeca(String nome,
                                            String email,
                                            String telefone,
                                            String cpf,
                                            String senha) {
        int opcaoEditar;
        do {
            System.out.println("\n===== Editar perfil =====");
            System.out.println("1. Nome");
            System.out.println("2. Email");
            System.out.println("3. Telefone");
            System.out.println("4. Senha");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoEditar = Integer.parseInt(entrada.nextLine());
            switch (opcaoEditar) {
                case 1 -> {
                    System.out.println("Nome atual: " + nome);
                    System.out.print("Novo nome: ");
                    String novoNome = entrada.nextLine();
                    if (SistemaTeatro.editarNome(cpf, novoNome))
                        System.out.println("Nome atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 2 -> {
                    System.out.println("Email atual: " + email);
                    System.out.print("Novo email: ");
                    String novoEmail = entrada.nextLine();
                    if (SistemaTeatro.editarEmail(cpf, novoEmail))
                        System.out.println("Email atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 3 -> {
                    System.out.println("Telefone atual: " + telefone);
                    System.out.print("Novo telefone: ");
                    String novoTelefone = entrada.nextLine();
                    if (SistemaTeatro.editarTelefone(cpf, novoTelefone))
                        System.out.println("Telefone atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 4 -> {
                    System.out.println("Senha atual: " + senha);
                    System.out.print("Nova senha: ");
                    String novaSenha = entrada.nextLine();
                    if (SistemaTeatro.editarSenha(cpf, novaSenha))
                        System.out.println("Senha atualizada com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcaoEditar != 5);
    }

    public static List<InfoEnsaio> getListaEnsaios() {
        return listaEnsaios;
    }
}
