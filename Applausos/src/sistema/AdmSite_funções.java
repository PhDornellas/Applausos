package sistema;

import java.time.LocalDate;
import java.util.Scanner;
import usuario.InfoPeca;

public class AdmSite_funções {
    private static Scanner ENTRADA = new Scanner(System.in);
    private static InfoPeca[] listaPeca = new InfoPeca[10];
    private static int indice = 0;

    public static void opcaoAdmSite(String nome, String email, String telefone, String cpf, String senha) {
        int opcao;
        do {
            System.out.println("\n===== Gerenciamento de Peças =====");
            System.out.println("1. Cadastrar peça");
            System.out.println("2. Listar peças cadastradas");
            System.out.println("3. Editar peça");
            System.out.println("4. Deletar peça");
            System.out.println("5. Editar Perfil");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = ENTRADA.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarPeca();
                    break;
                case 2:
                    listarPeca();
                    break;
                case 3:
                    editarPeca();
                    break;
                case 4:
                    deletarPeca();
                    break;
                case 5:
                    editarPerfilAdmSite(nome, email, telefone, cpf, senha);
                    break;
                case 6:
                    System.out.println("Encerrando o sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 6);
    }

    private static void cadastrarPeca() {
        if (indice >= listaPeca.length) {
            System.out.println("A lista de peças está cheia!");
            return;
        }

        System.out.println("==== CADASTRANDO PEÇA ====");

        System.out.print("Nome: ");
        ENTRADA.nextLine();
        String nome = ENTRADA.nextLine();

        System.out.print("Dia: ");
        int dia = ENTRADA.nextInt();

        System.out.print("Mês: ");
        int mes = ENTRADA.nextInt();

        System.out.print("Ano: ");
        int ano = ENTRADA.nextInt();

        System.out.print("Valor: ");
        double valor = ENTRADA.nextDouble();

        ENTRADA.nextLine();
        System.out.print("Local: ");
        String local = ENTRADA.nextLine();

        listaPeca[indice] = new InfoPeca(nome, dia, mes, ano, valor, local);
        indice++;

        System.out.println("Peça cadastrada com sucesso!");
    }

    private static void listarPeca() {
        System.out.println("==== LISTA DE PEÇAS ====");
        if (indice == 0) {
            System.out.println("Nenhuma peça cadastrada.");
        } else {
            for (int i = 0; i < indice; i++) {
                System.out.println("Peça " + (i + 1) + ": " + listaPeca[i].getNome()
                        + ", Data: " + listaPeca[i].getDataFormatada()
                        + ", Valor: R$ " + listaPeca[i].getValor()
                        + ", Local: " + listaPeca[i].getLocal());
            }
        }
    }

    private static void editarPeca() {
        if (indice == 0) {
            System.out.println("Nenhuma peça cadastrada para editar.");
            return;
        }

        System.out.println("==== EDITAR PEÇA ====");
        listarPeca();
        System.out.print("Digite o número da peça a ser editada: ");
        int num = ENTRADA.nextInt();

        if (num < 1 || num > indice) {
            System.out.println("Número inválido!");
            return;
        }

        int posicao = num - 1;
        ENTRADA.nextLine();

        System.out.print("Novo nome (atual: " + listaPeca[posicao].getNome() + "): ");
        String novoNome = ENTRADA.nextLine();
        if (!novoNome.trim().isEmpty()) {
            listaPeca[posicao].setNome(novoNome);
        }

        System.out.print("Novo dia (atual: " + listaPeca[posicao].getData().getDayOfMonth() + "): ");
        int novoDia = ENTRADA.nextInt();
        System.out.print("Novo mês (atual: " + listaPeca[posicao].getData().getMonthValue() + "): ");
        int novoMes = ENTRADA.nextInt();
        System.out.print("Novo ano (atual: " + listaPeca[posicao].getData().getYear() + "): ");
        int novoAno = ENTRADA.nextInt();
        listaPeca[posicao].setData(LocalDate.of(novoAno, novoMes, novoDia));

        System.out.print("Novo valor (atual: " + listaPeca[posicao].getValor() + "): ");
        double novoValor = ENTRADA.nextDouble();
        listaPeca[posicao].setValor(novoValor);

        ENTRADA.nextLine();
        System.out.print("Novo local (atual: " + listaPeca[posicao].getLocal() + "): ");
        String novoLocal = ENTRADA.nextLine();
        if (!novoLocal.trim().isEmpty()) {
            listaPeca[posicao].setLocal(novoLocal);
        }

        System.out.println("Peça editada com sucesso!");
    }

    private static void deletarPeca() {
        if (indice == 0) {
            System.out.println("Nenhuma peça para deletar.");
            return;
        }

        System.out.println("==== DELETAR PEÇA ====");
        listarPeca();
        System.out.print("Digite o número da peça a ser deletada: ");
        int num = ENTRADA.nextInt();

        if (num < 1 || num > indice) {
            System.out.println("Número inválido!");
            return;
        }

        int posicao = num - 1;
        for (int i = posicao; i < indice - 1; i++) {
            listaPeca[i] = listaPeca[i + 1];
        }
        listaPeca[indice - 1] = null;
        indice--;

        System.out.println("Peça deletada com sucesso!");
    }

    public static InfoPeca[] getListaPeca() {
        return listaPeca;
    }

    public static int getTotalPecas() {
        return indice;
    }

    private static void editarPerfilAdmSite(String nome, String email, String telefone, String cpf, String senha) {

        int opcaoEditar;
        do {

            System.out.println("\n===== Editar perfil =====");
            System.out.println("O que deseja alterar? ");
            System.out.println("1. Nome ");
            System.out.println("2. Email ");
            System.out.println("3. Telefone ");
            System.out.println("4. Senha ");
            System.out.println("5. Sair ");
            opcaoEditar = ENTRADA.nextInt();
            ENTRADA.nextLine();

            switch (opcaoEditar) {
                case 1:
                    System.out.println("Nome atual: " + nome);
                    System.out.print("Informe o novo nome: ");
                    String novoNome = ENTRADA.nextLine();

                    if (SistemaTeatro.editarNome(cpf, novoNome)) {
                        System.out.println("Nome atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado.");
                    }
                    break;

                case 2:
                    System.out.println("Email atual " + email);
                    System.out.println("Informe o novo Email: ");
                    String novoEmail = ENTRADA.nextLine();

                    if (SistemaTeatro.editarEmail(cpf, novoEmail)) {
                        System.out.println("Email atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado");
                    }
                    break;

                case 3:
                    System.out.println("Telefone atual: " + telefone);
                    System.out.println("Informe o novo telefone ");
                    String novoTelefone = ENTRADA.nextLine();

                    if (SistemaTeatro.editarTelefone(cpf, novoTelefone)) {
                        System.out.println("Telefone atualizado com sucesso! ");
                    } else {
                        System.out.println("Usuario nao encontrado ");
                    }

                    break;
                case 4:
                    System.out.println("Senha atual " + senha);
                    System.out.println("Informe a nova Senha ");
                    String novaSenha = ENTRADA.nextLine();

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

        } while (opcaoEditar != 5);

    }

}
