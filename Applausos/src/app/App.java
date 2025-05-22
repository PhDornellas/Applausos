package app;

import sistema.SistemaTeatro;
import sistema.Admpeca_funções;
import sistema.AdmSite_funções;
import util.PersistenceUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        
        SistemaTeatro.executar();

    
        PersistenceUtil.saveList(
            SistemaTeatro.usuarios,
            "usuarios.ser"
        );
        PersistenceUtil.saveList(
            Admpeca_funções.getListaEnsaios(),
            "ensaio.ser"
        );
        PersistenceUtil.saveList(
            Arrays.stream(AdmSite_funções.getListaPeca())
                  .limit(AdmSite_funções.getTotalPecas())
                  .collect(Collectors.toList()),
            "peca.ser"
        );
    }
}