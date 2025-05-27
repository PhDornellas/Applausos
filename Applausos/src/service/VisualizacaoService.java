package service;

import usuario.InfoEnsaio;

import java.util.List;

public class VisualizacaoService {

    public static void visualizarEnsaios(String email) {
        List<InfoEnsaio> lista = EnsaioService.getListaEnsaios();
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
