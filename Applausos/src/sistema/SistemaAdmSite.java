package sistema;

import java.util.Scanner;
import usuario.InfoPeca;

public class SistemaAdmSite {
    private static Scanner ENTRADA = new Scanner(System.in);

    private static InfoPeca[] listaPeca = new InfoPeca[10];
    private static int indice = 0; 


    public static void opcaoAdmPeca(){
        int opcaoAdd;
        do {
            
            System.out.println("\n===== Adicionando Peca =====");
            System.out.println("1. Cadastre sua peca");
            System.out.println("2. Listar peças cadastradas");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            opcaoAdd = ENTRADA.nextInt();

            switch (opcaoAdd) {
                case 1:
                    cadastrarPeca();
                    break;

                case 2:
                    listarPeca();
                    break;

                case 3:
                    System.out.println("Encerrando o sistema");
                    break;
                    
                    default:
                    System.out.println("Opção invalida");
                    break;
            }

        } while (opcaoAdd != 3);
    }

    private static void cadastrarPeca() {
        if (indice >= listaPeca.length) {
            System.out.println("A lista de peças está cheia!");
            return;
        }
    
        System.out.println("==== CADASTRANDO PEÇA ====");
        
        System.out.println("Nome: ");
        ENTRADA.nextLine();
        String nome = ENTRADA.nextLine();
        
        System.out.println("Dia: ");
        int dia = ENTRADA.nextInt();
        
        System.out.println("Mes: ");
        int mes = ENTRADA.nextInt();
        
        System.out.println("Ano: ");
        int ano = ENTRADA.nextInt();
        
        System.out.println("Valor: ");
        double valor = ENTRADA.nextDouble();
    
        
        listaPeca[indice] = new InfoPeca(nome, dia, mes, ano, valor);
        indice++;
    
        System.out.println("Peça cadastrada na posição " + indice + " com sucesso!");
    }

    private static void listarPeca(){

        System.out.println("LISTAR PECAS\n");

        if (indice == 0) {
            System.out.println("Lista vazia");
        }
        else{
            for (int i = 0; i < indice; i++) {
                System.out.println("Peça " + (i + 1) + ": " + listaPeca[i].getNome() + ", Data: " +listaPeca[i].getDia() + "/" + listaPeca[i].getMes() + "/" + listaPeca[i].getAno() +", Valor: R$" + listaPeca[i].getValor());
            }
        }
    }
    
}


