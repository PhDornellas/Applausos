package sistema;

import java.util.Scanner;
import usuario.InfoPeca;

public class cliente_funções {
    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoCliente() {
        int opcao;
        do {
            System.out.println("\n===== Menu do Cliente =====");
            System.out.println("1. Visualizar peças disponíveis");
            System.out.println("2. Comprar peça");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    visualizarPecas();
                    break;
                case 2:
                    comprarPeca();
                    break;
                case 3:
                    System.out.println("Encerrando o sistema do cliente.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private static void visualizarPecas() {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        System.out.println("==== Peças Disponíveis ====");
        if (total == 0) {
            System.out.println("Nenhuma peça disponível.");
        } else {
            for (int i = 0; i < total; i++) {
                InfoPeca p = lista[i];
                System.out.println("Peça " + (i + 1) + ": " 
                    + p.getNome() 
                    + ", Data: " + p.getDataFormatada() 
                    + ", Valor: R$ " + p.getValor() 
                    + ", Local: " + p.getLocal());
            }
        }
    }

    private static void comprarPeca() {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        if (total == 0) {
            System.out.println("Nenhuma peça disponível para compra.");
            return;
        }
        visualizarPecas();
        System.out.print("Digite o número da peça que deseja comprar: ");
        int num = entrada.nextInt();
        if (num < 1 || num > total) {
            System.out.println("Número inválido!");
            return;
        }
        InfoPeca peca = lista[num - 1];
        peca.incrementarIngressosVendidos();
        System.out.println("Você comprou a peça: " 
            + peca.getNome() 
            + " localizada em " + peca.getLocal() 
            + " no dia " + peca.getDataFormatada() 
            + " por R$" + peca.getValor() 
            + ". Confirmação enviada via E-mail.");
    }
}
