import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum UserType {
    CLIENTE,
    ADMINISTRADOR_PECA,
    ADMINISTRADOR_SITE
}

abstract class User {
    protected String nome;
    protected String email;
    protected String telefone;
    protected String cpf;
    protected String senha;
    protected UserType tipo;

    public User(String nome, String email, String telefone, String cpf, String senha, UserType tipo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public UserType getTipo() {
        return tipo;
    }
}

class Cliente extends User {
    public Cliente(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.CLIENTE);
    }
}

class AdministradorPeca extends User {
    public AdministradorPeca(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_PECA);
    }
}

class AdministradorSite extends User {
    public AdministradorSite(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_SITE);
    }
}

public class SistemaTeatro2 {

    private static List<User> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== Applausos =====");
            System.out.println("\n===== faça sua escolha =====");
            System.out.println("1. Cadastro");
            System.out.println("2. Login");
            System.out.println("3. Listar Usuários");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    fazerLogin(scanner);
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
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

        User novoUsuario = null;
        switch (tipoOpcao) {
            case 1:
                novoUsuario = new Cliente(nome, email, telefone, cpf, senha);
                break;
            case 2:
                novoUsuario = new AdministradorPeca(nome, email, telefone, cpf, senha);
                break;
            case 3:
                novoUsuario = new AdministradorSite(nome, email, telefone, cpf, senha);
                break;
            default:
                System.out.println("Opção inválida. Cadastro cancelado.");
                return;
        }

        usuarios.add(novoUsuario);

        System.out.println("\nCadastro realizado com sucesso!");
        System.out.println("Bem-vindo, " + nome + " (" + novoUsuario.getTipo() + ")!");
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
        for (User u : usuarios) {
            if (u.getCpf().equalsIgnoreCase(cpf)) {
                return u;
            }
        }
        return null;
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
