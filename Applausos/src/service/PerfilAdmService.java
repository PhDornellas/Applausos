package service;

import java.util.Scanner;

public class PerfilAdmService {

    public static void editarPerfilAdmPeca(Scanner entrada,
                                           String nome,
                                           String email,
                                           String telefone,
                                           String cpf,
                                           String senha) {
        int opcaoEditar;
        do {
            System.out.println("\n===== Editar perfil =====");
            System.out.println("1. Nome");
            System.out.println("2. Email");
            System.out.println("3. Telefone");
            System.out.println("4. Senha");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoEditar = Integer.parseInt(entrada.nextLine());

            switch (opcaoEditar) {
                case 1 -> {
                    System.out.println("Nome atual: " + nome);
                    System.out.print("Novo nome: ");
                    String novoNome = entrada.nextLine();
                    if (TipoUsuarioserver.editarNome(cpf, novoNome))
                        System.out.println("Nome atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 2 -> {
                    System.out.println("Email atual: " + email);
                    System.out.print("Novo email: ");
                    String novoEmail = entrada.nextLine();
                    if (TipoUsuarioserver.editarEmail(cpf, novoEmail))
                        System.out.println("Email atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 3 -> {
                    System.out.println("Telefone atual: " + telefone);
                    System.out.print("Novo telefone: ");
                    String novoTelefone = entrada.nextLine();
                    if (TipoUsuarioserver.editarTelefone(cpf, novoTelefone))
                        System.out.println("Telefone atualizado com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 4 -> {
                    System.out.println("Senha atual: " + senha);
                    System.out.print("Nova senha: ");
                    String novaSenha = entrada.nextLine();
                    if (TipoUsuarioserver.editarSenha(cpf, novaSenha))
                        System.out.println("Senha atualizada com sucesso!");
                    else
                        System.out.println("Usuário não encontrado.");
                }
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcaoEditar != 5);
    }
}
