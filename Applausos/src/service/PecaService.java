package service;

import usuario.InfoPeca;
import util.PersistenceUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

public class PecaService {
    private static InfoPeca[] listaPeca = new InfoPeca[10];
    private static int indice = 0;

    static {
        List<InfoPeca> temp = PersistenceUtil.loadList("peca.ser");
        for (int i = 0; i < temp.size() && i < listaPeca.length; i++) {
            listaPeca[i] = temp.get(i);
        }
        indice = Math.min(temp.size(), listaPeca.length);
    }

    public static void cadastrarPeca(Scanner entrada) {
        if (indice >= listaPeca.length) {
            System.out.println("A lista de peças está cheia!");
            return;
        }
        exibirCalendarioDatasCadastradas();
        System.out.println("==== CADASTRANDO PEÇA ====");
        entrada.nextLine();
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Dia: ");
        int dia = entrada.nextInt();
        System.out.print("Mês: ");
        int mes = entrada.nextInt();
        System.out.print("Ano: ");
        int ano = entrada.nextInt();
        System.out.print("Valor: ");
        double valor = entrada.nextDouble();
        entrada.nextLine();
        System.out.print("Local: ");
        String local = entrada.nextLine();

        listaPeca[indice++] = new InfoPeca(nome, dia, mes, ano, valor, local);
        System.out.println("Peça cadastrada com sucesso!");
    }

    public static void listarPeca() {
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

    public static void editarPeca(Scanner entrada) {
        listarPeca();
        if (indice == 0) return;

        System.out.print("Digite o número da peça a ser editada: ");
        int num = entrada.nextInt();
        if (num < 1 || num > indice) {
            System.out.println("Número inválido!");
            return;
        }
        int pos = num - 1;
        entrada.nextLine();
        System.out.print("Novo nome (atual: " + listaPeca[pos].getNome() + "): ");
        String novoNome = entrada.nextLine();
        if (!novoNome.trim().isEmpty()) listaPeca[pos].setNome(novoNome);
        System.out.print("Novo dia (atual: " + listaPeca[pos].getData().getDayOfMonth() + "): ");
        int nd = entrada.nextInt();
        System.out.print("Novo mês (atual: " + listaPeca[pos].getData().getMonthValue() + "): ");
        int nm = entrada.nextInt();
        System.out.print("Novo ano (atual: " + listaPeca[pos].getData().getYear() + "): ");
        int na = entrada.nextInt();
        listaPeca[pos].setData(LocalDate.of(na, nm, nd));
        System.out.print("Novo valor (atual: " + listaPeca[pos].getValor() + "): ");
        double nv = entrada.nextDouble();
        listaPeca[pos].setValor(nv);
        entrada.nextLine();
        System.out.print("Novo local (atual: " + listaPeca[pos].getLocal() + "): ");
        String nl = entrada.nextLine();
        if (!nl.trim().isEmpty()) listaPeca[pos].setLocal(nl);

        System.out.println("Peça editada com sucesso!");
    }

    public static void deletarPeca(Scanner entrada) {
        listarPeca();
        if (indice == 0) return;

        System.out.print("Digite o número da peça a ser deletada: ");
        int num = entrada.nextInt();
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

    public static void visualizarVendas() {
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

    public static void salvarPecas() {
        PersistenceUtil.saveList(Arrays.asList(listaPeca).subList(0, indice), "peca.ser");
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
            ocupadas.computeIfAbsent(ym, k -> new TreeSet<>()).add(d.getDayOfMonth());
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
                if ((primeiroDow + d - 1) % 7 == 0) System.out.println();
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
