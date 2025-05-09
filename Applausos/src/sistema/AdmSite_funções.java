package sistema;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import usuario.InfoPeca;

public class AdmSite_funções {
    private static Scanner ENTRADA = new Scanner(System.in);
    private static InfoPeca[] listaPeca = new InfoPeca[10];
    private static int indice = 0;

    public static void opcaoAdmSite() {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Peças =====");
            System.out.println("1. Cadastrar peça");
            System.out.println("2. Listar peças cadastradas");
            System.out.println("3. Editar peça");
            System.out.println("4. Deletar peça");
            System.out.println("5. Ver vendas de ingressos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ENTRADA.nextInt();
            switch (opcao) {
                case 1 -> cadastrarPeca();
                case 2 -> listarPeca();
                case 3 -> editarPeca();
                case 4 -> deletarPeca();
                case 5 -> visualizarVendas();
                case 6 -> System.out.println("Encerrando o sistema");
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 6);
    }

    private static void cadastrarPeca() {
        if (indice >= listaPeca.length) {
            System.out.println("A lista de peças está cheia!");
            return;
        }
        exibirCalendarioDatasCadastradas();
        System.out.println("==== CADASTRANDO PEÇA ====");
        ENTRADA.nextLine();
        String nome = ENTRADA.nextLine();
        System.out.print("Dia: ");
        int dia = ENTRADA.nextInt();
        System.out.print("Mês: ");
        int mes = ENTRADA.nextInt();
        System.out.print("Ano: ");
        int ano = ENTRADA.nextInt();
        System.out.print("Valor: ");
        double valor = ENTRADA.nextDouble();
        ENTRADA.nextLine();
        String local = ENTRADA.nextLine();
        listaPeca[indice++] = new InfoPeca(nome, dia, mes, ano, valor, local);
        System.out.println("Peça cadastrada com sucesso!");
    }

    private static void listarPeca() {
        System.out.println("==== LISTA DE PEÇAS ====");
        if (indice == 0) {
            System.out.println("Nenhuma peça cadastrada.");
        } else {
            for (int i = 0; i < indice; i++) {
                InfoPeca p = listaPeca[i];
                System.out.println("Peça " + (i + 1) + ": "
                    + p.getNome()
                    + ", Data: " + p.getDataFormatada()
                    + ", Valor: R$ " + p.getValor()
                    + ", Local: " + p.getLocal());
            }
        }
    }

    private static void editarPeca() {
        if (indice == 0) {
            System.out.println("Nenhuma peça cadastrada para editar.");
            return;
        }
        System.out.println("==== EDITAR PEÇA ====");
        listarPeca();
        System.out.print("Digite o número da peça a ser editada: ");
        int num = ENTRADA.nextInt();
        if (num < 1 || num > indice) {
            System.out.println("Número inválido!");
            return;
        }
        int pos = num - 1;
        ENTRADA.nextLine();
        System.out.print("Novo nome (atual: " + listaPeca[pos].getNome() + "): ");
        String novoNome = ENTRADA.nextLine();
        if (!novoNome.trim().isEmpty()) {
            listaPeca[pos].setNome(novoNome);
        }
        System.out.print("Novo dia (atual: " + listaPeca[pos].getData().getDayOfMonth() + "): ");
        int nd = ENTRADA.nextInt();
        System.out.print("Novo mês (atual: " + listaPeca[pos].getData().getMonthValue() + "): ");
        int nm = ENTRADA.nextInt();
        System.out.print("Novo ano (atual: " + listaPeca[pos].getData().getYear() + "): ");
        int na = ENTRADA.nextInt();
        listaPeca[pos].setData(LocalDate.of(na, nm, nd));
        System.out.print("Novo valor (atual: " + listaPeca[pos].getValor() + "): ");
        double nv = ENTRADA.nextDouble();
        listaPeca[pos].setValor(nv);
        ENTRADA.nextLine();
        System.out.print("Novo local (atual: " + listaPeca[pos].getLocal() + "): ");
        String nl = ENTRADA.nextLine();
        if (!nl.trim().isEmpty()) {
            listaPeca[pos].setLocal(nl);
        }
        System.out.println("Peça editada com sucesso!");
    }

    private static void deletarPeca() {
        if (indice == 0) {
            System.out.println("Nenhuma peça para deletar.");
            return;
        }
        System.out.println("==== DELETAR PEÇA ====");
        listarPeca();
        System.out.print("Digite o número da peça a ser deletada: ");
        int num = ENTRADA.nextInt();
        if (num < 1 || num > indice) {
            System.out.println("Número inválido!");
            return;
        }
        int pos = num - 1;
        for (int i = pos; i < indice - 1; i++) {
            listaPeca[i] = listaPeca[i + 1];
        }
        listaPeca[--indice] = null;
        System.out.println("Peça deletada com sucesso!");
    }

    private static void visualizarVendas() {
        System.out.println("\n==== VENDAS DE INGRESSOS ====");
        if (indice == 0) {
            System.out.println("Nenhuma peça cadastrada.");
        } else {
            for (int i = 0; i < indice; i++) {
                InfoPeca p = listaPeca[i];
                System.out.println("Peça " + (i + 1) + ": "
                    + p.getNome()
                    + " – Vendidos: " + p.getIngressosVendidos()
                    + " | Restantes: " + p.getIngressosRestantes());
            }
        }
    }

    private static void exibirCalendarioDatasCadastradas() {
        if (indice == 0) {
            System.out.println("Nenhuma data cadastrada ainda.\n");
            return;
        }
        Map<YearMonth, Set<Integer>> ocupadas = new LinkedHashMap<>();
        for (int i = 0; i < indice; i++) {
            LocalDate d = listaPeca[i].getData();
            YearMonth ym = YearMonth.from(d);
            ocupadas.computeIfAbsent(ym, k -> new TreeSet<>())
                    .add(d.getDayOfMonth());
        }
        Locale pt = Locale.forLanguageTag("pt-BR");
        for (Map.Entry<YearMonth, Set<Integer>> entry : ocupadas.entrySet()) {
            YearMonth ym = entry.getKey();
            Set<Integer> dias = entry.getValue();
            System.out.println("── " + ym.getMonth().getDisplayName(TextStyle.FULL, pt) + " " + ym.getYear() + " ──");
            for (DayOfWeek dow : DayOfWeek.values()) {
                System.out.printf("%4s", dow.getDisplayName(TextStyle.SHORT, pt));
            }
            System.out.println();
            int primeiroDow = ym.atDay(1).getDayOfWeek().getValue();
            for (int s = 1; s < primeiroDow; s++) {
                System.out.print("    ");
            }
            int totalDias = ym.lengthOfMonth();
            for (int d = 1; d <= totalDias; d++) {
                String marca = dias.contains(d) ? "*" : " ";
                System.out.printf("%3d%s", d, marca);
                if ((primeiroDow + d - 1) % 7 == 0) {
                    System.out.println();
                }
            }
            System.out.println("\n");
        }
    }

    public static InfoPeca[] getListaPeca() {
        return listaPeca;
    }

    public static int getTotalPecas() {
        return indice;
    }
}
