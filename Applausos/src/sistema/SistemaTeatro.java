package sistema;

import service.*;
import usuario.User;

import java.util.List;
import java.util.Scanner;

public class SistemaTeatro {
    public static void executar() {
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n===== Applausos =====");
            System.out.println("1. Cadastro");
            System.out.println("2. Login");
            System.out.println("3. Listar Usuários");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro(entrada);
            switch (opcao) {
                case 1 -> TipoUsuarioserver.cadastrarUsuario(entrada);
                case 2 -> TipoUsuarioserver.fazerLogin(entrada);
                case 3 -> listarUsuarios();
                case 4 -> System.out.println("Encerrando o sistema.");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
        entrada.close();
    }

    private static void listarUsuarios() {
        List<User> usuarios = TipoUsuarioserver.getUsuarios();
        System.out.println("\n===== Usuários Cadastrados =====");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (User u : usuarios) {
                System.out.println("Nome: " + u.getNome() +
                                   " | CPF: " + u.getCpf() +
                                   " | Tipo: " + u.getTipo());
            }
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, insira um número válido: ");
            }
        }
    }
}
