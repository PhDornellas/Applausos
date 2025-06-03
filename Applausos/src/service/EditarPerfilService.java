package service;

import java.util.Scanner;

public class EditarPerfilService {
    public static void editarPerfil(String nome, String email, String telefone, String cpf, String senha, Scanner entrada) {
        int opcaoEditar;
        do {
            System.out.println("\n===== Editar perfil =====");
            System.out.println("1. Nome ");
            System.out.println("2. Email ");
            System.out.println("3. Telefone ");
            System.out.println("4. Senha ");
            System.out.println("5. Sair ");
            System.out.print("Escolha uma opção: ");
            opcaoEditar = entrada.nextInt();
            entrada.nextLine();

            switch (opcaoEditar) {
                case 1 -> {
                    app.Utils.clearScreen();
                    System.out.println("Nome atual: " + nome);
                    System.out.print("Informe o novo nome: ");
                    String novoNome = entrada.nextLine();
                    if (TipoUsuarioserver.editarNome(cpf, novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                }
                case 2 -> {
                    app.Utils.clearScreen();
                    System.out.println("Email atual: " + email);
                    System.out.print("Informe o novo Email: ");
                    String novoEmail = entrada.nextLine();
                    if (TipoUsuarioserver.editarEmail(cpf, novoEmail)) {
                        System.out.println("Email atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado");
                    }
                }
                case 3 -> {
                    app.Utils.clearScreen();
                    System.out.println("Telefone atual: " + telefone);
                    System.out.print("Informe o novo telefone ");
                    String novoTelefone = entrada.nextLine();
                    if (TipoUsuarioserver.editarTelefone(cpf, novoTelefone)) {
                        System.out.println("Telefone atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado");
                    }
                }
                case 4 -> {
                    app.Utils.clearScreen();
                    System.out.println("Senha atual: " + senha);
                    System.out.print("Informe a nova Senha ");
                    String novaSenha = entrada.nextLine();
                    if (TipoUsuarioserver.editarSenha(cpf, novaSenha)) {
                        System.out.println("Senha atualizada com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado");
                    }
                }
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Número inválido.");
            }
        } while (opcaoEditar != 5);
    }
}
