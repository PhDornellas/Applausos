package sistema;

import usuario.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaTeatro {
    private static List<User> usuarios = new ArrayList<>();

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
                case 1 -> cadastrarUsuario(entrada);
                case 2 -> fazerLogin(entrada);
                case 3 -> listarUsuarios();
                case 4 -> System.out.println("Encerrando o sistema.");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        entrada.close();
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("\n===== Cadastro de Usuário =====");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("Já existe um usuário com este CPF.");
            return;
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("\nSelecione o tipo de usuário:");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador de Peça");
        System.out.println("3. Administrador do Site");
        System.out.print("Opção: ");
        int tipoOpcao = lerInteiro(scanner);

        User novoUsuario = switch (tipoOpcao) {
            case 1 -> new Cliente(nome, email, telefone, cpf, senha);
            case 2 -> new AdministradorPeca(nome, email, telefone, cpf, senha);
            case 3 -> new AdministradorSite(nome, email, telefone, cpf, senha);
            default -> {
                System.out.println("Opção inválida. Cadastro cancelado.");
                yield null;
            }
        };

        if (novoUsuario != null) {
            usuarios.add(novoUsuario);
            System.out.println("\nCadastro realizado com sucesso!");
            System.out.println("Bem-vindo, " + nome + " (" + novoUsuario.getTipo() + ")!");
        }
    }

    private static void fazerLogin(Scanner scanner) {
        System.out.println("\n===== Login =====");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (!usuario.getSenha().equals(senha)) {
            System.out.println("Senha incorreta.");
            return;
        }

        System.out.println("\nLogin realizado com sucesso!");
        System.out.println("Bem-vindo, " + usuario.getNome() + " (" + usuario.getTipo() + ")!");

        if (usuario instanceof AdministradorPeca ) {
            SistemaAdmSite.opcaoAdmPeca();
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n===== Usuários Cadastrados =====");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (User u : usuarios) {
                System.out.println("Nome: " + u.getNome() + " | CPF: " + u.getCpf() + " | Tipo: " + u.getTipo());
            }
        }
    }

    private static User buscarUsuarioPorCpf(String cpf) {
        return usuarios.stream().filter(u -> u.getCpf().equalsIgnoreCase(cpf)).findFirst().orElse(null);
    }

    private static int lerInteiro(Scanner scanner) {
        int valor;
        while (true) {
            try {
                valor = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Por favor, insira um número válido: ");
            }
        }
        return valor;
    }
}
