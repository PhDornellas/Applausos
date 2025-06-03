package sistema;

import service.*;
import java.util.Scanner;

public class cliente_funções {
    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoCliente(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Menu do Cliente =====");
            System.out.println("1. Visualizar peças disponíveis");
            System.out.println("2. Comprar peça");
            System.out.println("3. Avaliar peça");
            System.out.println("4. Editar Perfil");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1 -> {
                    app.Utils.clearScreen();
                    ClienteService.visualizarPecas(false);}
                case 2 -> {
                    app.Utils.clearScreen();
                    CompraService.comprarPeca(entrada);}
                case 3 -> {
                    app.Utils.clearScreen();
                    AvaliacaoService.avaliarPeca(entrada);}
                case 4 -> {
                    app.Utils.clearScreen();
                    EditarPerfilService.editarPerfil(nome, email, telefone, cpf, senha, entrada);}
                case 5 -> {
                    app.Utils.clearScreen();
                    System.out.println("De volta ao menu principal...");}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}
