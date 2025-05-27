package service;

import usuario.InfoPeca;
import java.util.Scanner;

public class AvaliacaoService {
    public static void avaliarPeca(Scanner entrada) {
        InfoPeca[] lista = PecaService.getListaPeca();
        int total = PecaService.getTotalPecas();
        if (total == 0) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }
        System.out.print("Digite o nome da peça que deseja avaliar: ");
        String nome = entrada.nextLine();
        InfoPeca selecionada = null;
        for (int i = 0; i < total; i++) {
            if (lista[i].getNome().equalsIgnoreCase(nome)) {
                selecionada = lista[i];
                break;
            }
        }
        if (selecionada == null) {
            System.out.println("Peça não encontrada.");
            return;
        }
        System.out.print("Classificação (0 a 5): ");
        int estrelas = entrada.nextInt();
        entrada.nextLine();
        if (estrelas < 0 || estrelas > 5) {
            System.out.println("Classificação inválida.");
            return;
        }
        System.out.print("Comentário: ");
        String comentario = entrada.nextLine();
        selecionada.adicionarAvaliacao(estrelas, comentario);
        System.out.println("Avaliação registrada!");
    }
}
