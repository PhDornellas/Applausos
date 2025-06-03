package sistema;

import service.*;
// import service.PerfilAdmService;
import java.util.Scanner;

public class Admpeca_funções {
    private static final Scanner entrada = new Scanner(System.in);

    public static void opcaoAdmPeca(String nome,
                                    String email,
                                    String telefone,
                                    String cpf,
                                    String senha) {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Ensaios =====");
            System.out.println("1. Cadastrar ensaio");
            System.out.println("2. Listar ensaios");
            System.out.println("3. Ver agenda de ator");
            System.out.println("4. Editar perfil");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1 -> {
                    app.Utils.clearScreen();
                    EnsaioService.cadastrarEnsaio(entrada);}
                case 2 -> {
                    app.Utils.clearScreen();
                    EnsaioService.listarEnsaios();}
                case 3 -> {
                    app.Utils.clearScreen();
                    AgendaService.visualizarAgendaAtor(entrada);}
                case 4 -> {
                    app.Utils.clearScreen();
                    EditarPerfilService.editarPerfil(nome, email, telefone, cpf, senha, entrada);}
                case 5 -> {
                    app.Utils.clearScreen();
                    EnsaioService.salvarEnsaios();}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }
}
