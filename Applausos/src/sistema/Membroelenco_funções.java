package sistema;

import usuario.InfoEnsaio;
import java.util.List;
import java.util.Scanner;

public class Membroelenco_funções {
    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoMembroElenco(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Menu Membro de Elenco =====");
            System.out.println("1. Visualizar ensaios disponíveis");
            System.out.println("2. Editar perfil");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1 -> visualizarEnsaios(email);
                case 2 -> editarPerfilMembroElenco(nome, email, telefone, cpf, senha);
                case 3 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }

    private static void visualizarEnsaios(String email) {
        List<InfoEnsaio> lista = Admpeca_funções.getListaEnsaios();
        System.out.println("==== Ensaios para " + email + " ====");
        boolean encontrou = false;
        for (InfoEnsaio e : lista) {
            if (e.getMembros().contains(email)) {
                encontrou = true;
                System.out.println("Data: " + e.getDataFormatada() +
                                   ", Hora: " + e.getHoraFormatada() +
                                   ", Local: " + e.getLocal() +
                                   ", Feedback: " + e.getFeedback());
            }
        }
        if (!encontrou) {
            System.out.println("Você não possui ensaios agendados ou não foi adicionado pelo administrador.");
        }
    }

    private static void editarPerfilMembroElenco(String nome, String email, String telefone, String cpf, String senha) {

        int opcaoEditar;
        do {
            
            System.out.println("\n===== Editar perfil =====");
            System.out.println("O que deseja alterar? ");
            System.out.println("1. Nome ");
            System.out.println("2. Email ");
            System.out.println("3. Telefone ");
            System.out.println("4. Senha ");
            System.out.println("5. Sair ");
            opcaoEditar = entrada.nextInt();
            entrada.nextLine();

            switch (opcaoEditar) {
                case 1:
                    System.out.println("Nome atual: " + nome);
                    System.out.print("Informe o novo nome: ");
                    String novoNome = entrada.nextLine();

                    if (SistemaTeatro.editarNome(cpf, novoNome)) {
                        System.out.println("Nome atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado.");
                    }
                    break;

                case 2:
                    System.out.println("Email atual " + email);
                    System.out.println("Informe o novo Email: ");
                    String novoEmail = entrada.nextLine();

                    if (SistemaTeatro.editarEmail(cpf, novoEmail)) {
                        System.out.println("Email atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado");
                    }
                    break;

                case 3:
                    System.out.println("Telefone atual: " + telefone);
                    System.out.println("Informe o novo telefone ");
                    String novoTelefone = entrada.nextLine();

                    if (SistemaTeatro.editarTelefone(cpf, novoTelefone)) {
                        System.out.println("Telefone atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado ");
                    }

                    break;
                case 4:
                    System.out.println("Senha atual " + senha);
                    System.out.println("Informe a nova Senha ");
                    String novaSenha = entrada.nextLine();

                    if (SistemaTeatro.editarSenha(cpf, novaSenha)) {
                        System.out.println("Senha atualizada com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Numero invalido. ");
                    break;
            }

        } while (opcaoEditar!=5);
        

    }

}





    