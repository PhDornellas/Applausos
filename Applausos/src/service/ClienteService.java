package service;

import usuario.InfoPeca;
import util.PersistenceUtil;
import java.util.List;

public class ClienteService {
    public static void visualizarPecas(boolean meia) {
        InfoPeca[] lista = PecaService.getListaPeca();
        int total = PecaService.getTotalPecas();
        System.out.println("==== Peças Disponíveis ====");
        if (total == 0) {
            System.out.println("Nenhuma peça disponível.");
        } else {
            for (int i = 0; i < total; i++) {
                InfoPeca p = lista[i];
                double valor = meia ? p.getValor() / 2 : p.getValor();
                System.out.println("Peça " + (i + 1) + ": "
                        + p.getNome()
                        + " | Data: " + p.getDataFormatada()
                        + " | Valor: R$" + valor
                        + " | Local: " + p.getLocal()
                        + " | Vendidos: " + p.getIngressosVendidos()
                        + " | Restantes: " + p.getIngressosRestantes());
            }
        }
    }

    public static void finalizarSessao() {
        PersistenceUtil.saveList(
                List.of(PecaService.getListaPeca())
                        .subList(0, PecaService.getTotalPecas()),
                "peca.ser"
        );
        System.out.println("Encerrando o sistema do cliente.");
    }
}
