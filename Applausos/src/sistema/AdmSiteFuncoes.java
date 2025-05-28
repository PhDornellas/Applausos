package sistema;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.Locale;
import usuario.InfoPeca;
import usuario.User;

public class AdmSiteFuncoes {
    public static Scanner ENTRADA = new Scanner(System.in).useLocale(Locale.US);
    private static InfoPeca[] lista = new InfoPeca[100];
    private static int indice = 0;

    public static void cadastrarPeca() {

        String nome = ENTRADA.nextLine();
        int dia = ENTRADA.nextInt();
        int mes = ENTRADA.nextInt();
        int ano = ENTRADA.nextInt();
        LocalDate data = LocalDate.of(ano, mes, dia);
        double valor = ENTRADA.nextDouble();
        ENTRADA.nextLine();
        String local = ENTRADA.nextLine();

        lista[indice] = new InfoPeca(nome, data, valor, local);
        indice++;
    }

    public static InfoPeca[] getListaPeca() {
        return lista;
    }

    public static int getTotalPecas() {
        return indice;
    }

    public static void opcaoAdmSite(List<User> usuarios) {
        System.out.println("Lista de usuários cadastrados:");
        for (User u : usuarios) {
            System.out.println("- " + u.getNome());
        }
    }
}
