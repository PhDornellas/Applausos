package service;

import sistema.*;
import usuario.*;
import util.PersistenceUtil;

import java.util.List;
import java.util.Scanner;

public class TipoUsuarioserver {
    private static final List<User> usuarios = PersistenceUtil.loadList("usuarios.ser");

    public static List<User> getUsuarios() {
        return usuarios;
    }

    public static void cadastrarUsuario(Scanner scanner) {
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

    public static void fazerLogin(Scanner scanner) {
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
                redirecionarUsuario(usuario);
                return;
            }
            System.out.println("Senha incorreta.");
            System.out.println("1. Tentar novamente");
            System.out.println("2. Recuperar senha");
            System.out.print("Escolha uma opção: ");
            int escolha = lerInteiro(scanner);
            if (escolha == 1) continue;
            else if (escolha == 2) {
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

    private static void redirecionarUsuario(User usuario) {
        if (usuario instanceof InfoAdministradorSite) {
            AdmSite_funções.opcaoAdmSite(usuarios);
        } else if (usuario instanceof InfoCliente) {
            cliente_funções.opcaoCliente(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getCpf(), usuario.getSenha());
        } else if (usuario instanceof InfoAdministradorPeca) {
            Admpeca_funções.opcaoAdmPeca(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getCpf(), usuario.getSenha());
        } else if (usuario instanceof InfoMembroElenco) {
            Membroelenco_funções.opcaoMembroElenco(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getCpf(), usuario.getSenha());
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
}    

