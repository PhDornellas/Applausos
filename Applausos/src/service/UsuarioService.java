package service;

import usuario.User;

import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    public static void deletarUsuario(Scanner entrada, List<User> usuarios) {
        listarUsuarios(usuarios);
        if (usuarios.isEmpty()) return;

        entrada.nextLine();
        System.out.print("\nDigite o CPF do usuário que deseja deletar: ");
        String cpf = entrada.nextLine();
        User user = usuarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
        } else {
            usuarios.remove(user);
            System.out.println("Usuário removido com sucesso.");
        }
    }

    private static void listarUsuarios(List<User> usuarios) {
        System.out.println("\n===== Usuários Cadastrados =====");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (User u : usuarios) {
                System.out.println("Nome: " + u.getNome()
                        + " | CPF: " + u.getCpf()
                        + " | Tipo: " + u.getTipo());
            }
        }
    }
}
