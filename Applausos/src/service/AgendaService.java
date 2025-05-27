package service;

import usuario.InfoEnsaio;

import java.util.List;
import java.util.Scanner;

public class AgendaService {
    public static void visualizarAgendaAtor(Scanner entrada) {
        List<InfoEnsaio> listaEnsaios = EnsaioService.getListaEnsaios();

        System.out.print("Digite o e-mail do ator: ");
        String email = entrada.nextLine().trim();
        boolean encontrou = false;
        System.out.println("Ensaios agendados para " + email + ":");
        for (InfoEnsaio e : listaEnsaios) {
            if (e.getMembros().contains(email)) {
                encontrou = true;
                System.out.println("Data: " + e.getDataFormatada() +
                    ", Hora: " + e.getHoraFormatada() +
                    ", Local: " + e.getLocal());
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum ensaio encontrado para este e-mail.");
        }
    }
}
