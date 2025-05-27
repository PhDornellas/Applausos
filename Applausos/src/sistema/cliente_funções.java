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
                case 1 -> ClienteService.visualizarPecas(false);
                case 2 -> CompraService.comprarPeca(entrada);
                case 3 -> AvaliacaoService.avaliarPeca(entrada);
                case 4 -> PerfilService.editarPerfilCliente(nome, email, telefone, cpf, senha, entrada);
                case 5 -> ClienteService.finalizarSessao();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}
