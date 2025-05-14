package sistema;

import java.util.Scanner;
import usuario.InfoPeca;

public class cliente_funções {
    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoCliente(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Menu do Cliente =====");
            System.out.println("1. Visualizar peças disponíveis");
            System.out.println("2. Comprar peça");
            System.out.println("3. Avaliar peça");
            System.out.println("4. Editar Perfil");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1 -> visualizarPecas(false);
                case 2 -> comprarPeca();
                case 3 -> avaliarPeca();
                case 4 -> editarPerfilCliente(nome, email, telefone, cpf, senha);
                case 5 -> System.out.println("Encerrando o sistema do cliente.");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void visualizarPecas(Boolean meia) {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        System.out.println("==== Peças Disponíveis ====");
        if (total == 0) {
            System.out.println("Nenhuma peça disponível.");
        } else {
            if (meia == true) {
                 for (int i = 0; i < total; i++) {
                InfoPeca p = lista[i];
                System.out.println("Peça " + (i + 1) + ": " 
                    + p.getNome()
                    + " | Data: " + p.getDataFormatada()
                    + " | Valor: R$" + p.getValor() / 2
                    + " | Local: " + p.getLocal()
                    + " | Vendidos: " + p.getIngressosVendidos()
                    + " | Restantes: " + p.getIngressosRestantes());
            }
            }

            else{
                 for (int i = 0; i < total; i++) {
                    InfoPeca p = lista[i];
                    System.out.println("Peça " + (i + 1) + ": " 
                        + p.getNome()
                        + " | Data: " + p.getDataFormatada()
                        + " | Valor: R$" + p.getValor() 
                        + " | Local: " + p.getLocal()
                        + " | Vendidos: " + p.getIngressosVendidos()
                        + " | Restantes: " + p.getIngressosRestantes());
            }
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

        System.out.println("Deseja comprar meia entrada? (S/N) ");
        String opcaoMeia = entrada.nextLine().toUpperCase();

        if (opcaoMeia.equals("S")) {
            visualizarPecas(true);
        }
        else{
            visualizarPecas(false);
        }

       
        System.out.print("Digite o número da peça que deseja comprar: ");
        int num = entrada.nextInt();

        if (num < 1 || num > total) {
            System.out.println("Número inválido!");
            return;
        }
        InfoPeca peca = lista[num - 1];
        if (peca.getIngressosRestantes() > 0) {
            peca.incrementarIngressosVendidos();
            System.out.println("Ingresso comprado com sucesso!");
        } else {
            System.out.println("Não há ingressos disponíveis para esta peça.");
        }
    }

    private static void avaliarPeca() {
        InfoPeca[] lista = AdmSite_funções.getListaPeca();
        int total = AdmSite_funções.getTotalPecas();
        if (total == 0) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }
        System.out.print("Digite o nome da peça que deseja avaliar: ");
        String nome = entrada.nextLine();
        InfoPeca selecionada = null;
        for (int i = 0; i < total; i++) {
            if (lista[i].getNome().equalsIgnoreCase(nome)) {
                selecionada = lista[i];
                break;
            }
        }
        if (selecionada == null) {
            System.out.println("Peça não encontrada.");
            return;
        }
        System.out.print("Classificação (0 a 5): ");
        int estrelas = entrada.nextInt();
        entrada.nextLine();
        if (estrelas < 0 || estrelas > 5) {
            System.out.println("Classificação inválida.");
            return;
        }
        System.out.print("Comentário: ");
        String comentario = entrada.nextLine();
        selecionada.adicionarAvaliacao(estrelas, comentario);
        System.out.println("Avaliação registrada!");
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