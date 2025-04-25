package sistema;

import java.util.Scanner;
import usuario.InfoPeca;
//import sistema.SistemaTeatro.*;

public class cliente_funções {

    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoCliente(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Menu do Cliente =====");
            System.out.println("1. Visualizar peças disponíveis");
            System.out.println("2. Comprar peça");
            System.out.println("3. Editar perfil");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    visualizarPecas();
                    break;
                case 2:
                    comprarPeca();
                    break;
                case 3:
                    editarPerfilCliente(nome, email, telefone, cpf, senha);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema do cliente.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }

    private static void visualizarPecas() {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        System.out.println("==== Peças Disponíveis ====");
        if (total == 0) {
            System.out.println("Nenhuma peça disponível.");
        } else {
            for (int i = 0; i < total; i++) {
                System.out.println("Peça " + (i + 1) + ": " + lista[i].getNome()
                        + ", Data: " + lista[i].getDataFormatada()
                        + ", Valor: R$ " + lista[i].getValor()
                        + ", Local: " + lista[i].getLocal());
            }
        }
    }

    private static void comprarPeca() {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        if (total == 0) {
            System.out.println("Nenhuma peça disponível para compra.");
            return;
        }
        visualizarPecas();
        System.out.print("Digite o número da peça que deseja comprar: ");
        int num = entrada.nextInt();
        if (num < 1 || num > total) {
            System.out.println("Número inválido!");
            return;
        }
        InfoPeca peca = lista[num - 1];
        System.out.println("Você comprou a peça: " + peca.getNome()
                + " localizada em " + peca.getLocal()
                + " no dia " + peca.getDataFormatada() + " por R$" + peca.getValor()
                + " Confirmação enviada via E-mail.");
    }

    private static void editarPerfilCliente(String nome, String email, String telefone, String cpf, String senha) {

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
