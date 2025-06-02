package sistema;

import service.PerfilMembroService;
import service.VisualizacaoService;

import java.util.Scanner;

public class Membroelenco_funções {
    private static final Scanner entrada = new Scanner(System.in);

    public static void opcaoMembroElenco(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Menu Membro de Elenco =====");
            System.out.println("1. Visualizar ensaios disponíveis");
            System.out.println("2. Editar perfil");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1 -> {
                    app.Utils.clearScreen();
                    VisualizacaoService.visualizarEnsaios(email);}
                case 2 -> {
                    app.Utils.clearScreen();
                    PerfilMembroService.editarPerfilMembro(entrada, nome, email, telefone, cpf, senha);}
                case 3 -> {
                    app.Utils.clearScreen();
                    System.out.println("De volta ao menu principal...");}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }
}
