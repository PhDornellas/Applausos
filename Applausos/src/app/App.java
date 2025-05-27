package app;

import sistema.SistemaTeatro;
import util.PersistenceUtil;
import service.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        
        SistemaTeatro.executar();

    
        PersistenceUtil.saveList(
            TipoUsuarioserver.getUsuarios(),
            "usuarios.ser"
        );
        PersistenceUtil.saveList(
            EnsaioService.getListaEnsaios(),
            "ensaio.ser"
        );
        PersistenceUtil.saveList(
            Arrays.stream(PecaService.getListaPeca())
                  .limit(PecaService.getTotalPecas())
                  .collect(Collectors.toList()),
            "peca.ser"
        );
    }
}