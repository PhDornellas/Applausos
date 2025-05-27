package service;

import usuario.InfoPeca;
import java.util.Scanner;

public class CompraService {
    public static void comprarPeca(Scanner entrada) {
        InfoPeca[] lista = PecaService.getListaPeca();
        int total = PecaService.getTotalPecas();
        if (total == 0) {
            System.out.println("Nenhuma peça disponível para compra.");
            return;
        }

        System.out.print("Deseja comprar meia entrada? (S/N) ");
        String opcaoMeia = entrada.nextLine().trim().toUpperCase();
        boolean meia = opcaoMeia.equals("S");
        ClienteService.visualizarPecas(meia);

        System.out.print("Digite o número da peça que deseja comprar: ");
        int num = entrada.nextInt();
        entrada.nextLine();

        if (num < 1 || num > total) {
            System.out.println("Número inválido!");
            return;
        }

        InfoPeca peca = lista[num - 1];
        if (peca.getIngressosRestantes() > 0) {
            peca.incrementarIngressosVendidos();
            System.out.println("Ingresso comprado com sucesso!");
        } else {
            System.out.println("Não há ingressos disponíveis para esta peça.");
        }
    }
}
