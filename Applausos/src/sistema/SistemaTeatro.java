package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import usuario.InfoAdministradorPeca;
import usuario.InfoAdministradorSite;
import usuario.InfoCliente;
import usuario.InfoMembroElenco;
import usuario.User;

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
        System.out.println("4. Membro de Elenco");
        System.out.print("Opção: ");
        int tipoOpcao = lerInteiro(scanner);

        User novoUsuario = switch (tipoOpcao) {
            case 1 -> new InfoCliente(nome, email, telefone, cpf, senha);
            case 2 -> new InfoAdministradorPeca(nome, email, telefone, cpf, senha);
            case 3 -> new InfoAdministradorSite(nome, email, telefone, cpf, senha);
            case 4 -> new InfoMembroElenco(nome, email, telefone, cpf, senha);
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

        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        while (true) {
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (usuario.getSenha().equals(senha)) {
                System.out.println("\nLogin realizado com sucesso!");
                System.out.println("Bem-vindo, " + usuario.getNome() + " (" + usuario.getTipo() + ")!");

                if (usuario instanceof InfoAdministradorSite) {
                    AdmSite_funções.opcaoAdmSite();
                } else if (usuario instanceof InfoCliente) {
                    cliente_funções.opcaoCliente();
                } else if (usuario instanceof InfoAdministradorPeca) {
                    Admpeca_funções.opcaoAdmPeca(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(),
                            usuario.getCpf(), usuario.getSenha());
                } else if (usuario instanceof InfoMembroElenco) {
                    Membroelenco_funções.opcaoMembroElenco(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(),
                            usuario.getCpf(), usuario.getSenha());
                }
                return;
            }

            System.out.println("Senha incorreta.");
            System.out.println("1. Tentar novamente");
            System.out.println("2. Recuperar senha");
            System.out.print("Escolha uma opção: ");
            int escolha = lerInteiro(scanner);

            if (escolha == 1) {
                continue;
            } else if (escolha == 2) {
                System.out.print("Digite o e-mail para recuperação: ");
                String emailRec = scanner.nextLine();
                System.out.println("Link de recuperação enviado para: " + emailRec);
                return;
            } else {
                System.out.println("Opção inválida. Retornando ao menu principal.");
                return;
            }
        }
    }

    private static void listarUsuarios() {
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

    private static User buscarUsuarioPorCpf(String cpf) {
        return usuarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);
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

    public static boolean editarNome(String cpf, String novoNome) {
        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            usuario.setNome(novoNome);
            return true;
        }
        return false;
    }

    public static boolean editarEmail(String cpf, String novoEmail) {
        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            usuario.setEmail(novoEmail);
            return true;
        }
        return false;
    }

    public static boolean editarTelefone(String cpf, String novoTelefone) {
        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            usuario.setTelefone(novoTelefone);
            return true;
        }
        return false;
    }

    public static boolean editarSenha(String cpf, String novaSenha) {
        User usuario = buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            return true;
        }
        return false;
    }

}
