package service;

import usuario.InfoEnsaio;
import util.PersistenceUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnsaioService {
    private static List<InfoEnsaio> listaEnsaios = PersistenceUtil.loadList("ensaio.ser");

    public static void cadastrarEnsaio(Scanner entrada) {
        try {
            System.out.println("==== CADASTRO DE ENSAIO ====");
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
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar ensaio: " + e.getMessage());
        }
    }

    public static void listarEnsaios() {
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

    public static List<InfoEnsaio> getListaEnsaios() {
        return listaEnsaios;
    }

    public static void salvarEnsaios() {
        PersistenceUtil.saveList(listaEnsaios, "ensaio.ser");
        System.out.println("Retornando ao menu principal...");
    }
}
