package sistema;

import service.PecaService;
import service.UsuarioService;
import usuario.User;

import java.util.List;
import java.util.Scanner;

public class AdmSite_funções {
    private static final Scanner ENTRADA = new Scanner(System.in);

    public static void opcaoAdmSite(List<User> usuarios) {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Peças =====");
            System.out.println("1. Cadastrar peça");
            System.out.println("2. Listar peças cadastradas");
            System.out.println("3. Editar peça");
            System.out.println("4. Deletar peça");
            System.out.println("5. Ver vendas de ingressos");
            System.out.println("6. Deletar usuários");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ENTRADA.nextInt();

            switch (opcao) {
                case 1 -> {
                    app.Utils.clearScreen();
                    PecaService.cadastrarPeca(ENTRADA);}
                case 2 -> {
                    app.Utils.clearScreen();
                    PecaService.listarPeca();}
                case 3 -> {
                    app.Utils.clearScreen();
                    PecaService.editarPeca(ENTRADA);}
                case 4 -> {
                    app.Utils.clearScreen();
                    PecaService.deletarPeca(ENTRADA);}
                case 5 -> {
                    app.Utils.clearScreen();
                    PecaService.visualizarVendas();}
                case 6 -> {
                    app.Utils.clearScreen();
                    UsuarioService.deletarUsuario(ENTRADA, usuarios);}
                case 7 -> {
                    app.Utils.clearScreen();
                    System.out.println("De volta ao menu principal...");                  
                }
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 7);
    }
}
