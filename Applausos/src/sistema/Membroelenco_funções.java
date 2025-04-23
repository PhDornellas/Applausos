package sistema;

import usuario.InfoEnsaio;
import java.util.List;
import java.util.Scanner;

public class Membroelenco_funções {
    private static Scanner entrada = new Scanner(System.in);

    public static void opcaoMembroElenco(String email) {
        int opcao;
        do {
            System.out.println("\n===== Menu Membro de Elenco =====");
            System.out.println("1. Visualizar ensaios disponíveis");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1 -> visualizarEnsaios(email);
                case 2 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 2);
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
}





    