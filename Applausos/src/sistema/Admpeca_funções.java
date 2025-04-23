package sistema;

import usuario.InfoEnsaio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admpeca_funções {
    private static Scanner entrada = new Scanner(System.in);
    private static List<InfoEnsaio> listaEnsaios = new ArrayList<>();

    public static void opcaoAdmPeca() {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Ensaios =====");
            System.out.println("1. Cadastrar ensaio");
            System.out.println("2. Listar ensaios");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1 -> cadastrarEnsaio();
                case 2 -> listarEnsaios();
                case 3 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
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
            List<String> membros = new ArrayList<>();
            for (int i = 0; i < qtd; i++) {
                System.out.print("E-mail do membro " + (i + 1) + ": ");
                membros.add(entrada.nextLine().trim());
            }

            listaEnsaios.add(new InfoEnsaio(data, hora, local, feedback, membros));
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
                System.out.println("Ensaio " + (i + 1) + ": Data " + e.getDataFormatada() +
                                   ", Hora " + e.getHoraFormatada() +
                                   ", Local: " + e.getLocal() +
                                   ", Feedback: " + e.getFeedback() +
                                   ", Membros: " + e.getMembros());
            }
        }
    }

    public static List<InfoEnsaio> getListaEnsaios() {
        return listaEnsaios;
    }
}
