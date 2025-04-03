package sistema;

import java.util.Scanner;
import usuario.InfoPeca;
import sistema.AdmSite;

public class Membro {

    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoCliente() {
        int opcao;
        do {
            System.out.println("\n===== Menu do Membro =====");
            System.out.println("1. Visualizar peças");
            System.out.println("2. Cadastrar em uma peça"); // Ac que seria isso
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                visualizarPecas();
                    break;
                case 3:
                    System.out.println("Encerrando o sistema do cliente.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }

    private static void visualizarPecas() {
        InfoPeca[] lista = AdmSite.getListaPeca();
        int total = AdmSite.getTotalPecas();
        System.out.println("==== Peças Disponíveis ====");
        if (total == 0) {
            System.out.println("Nenhuma peça disponível.");
        } else {
            for (int i = 0; i < total; i++) {
                System.out.println("Peça " + (i + 1) + ": " + lista[i].getNome()
                        + ", Data: " + lista[i].getDataFormatada()
                        + ", Valor: R$ " + lista[i].getValor()
                        + ", Local: " + lista[i].getLocal());
            }
        }
    }

    
}





    