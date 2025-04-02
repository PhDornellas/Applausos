package usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InfoPeca {
    private String nomePeca;
    private LocalDate data;
    private double valor;
    private String local; 

    public InfoPeca(String nomePeca, int dia, int mes, int ano, double valor, String local) {
        this.nomePeca = nomePeca;
        this.data = LocalDate.of(ano, mes, dia);
        this.valor = valor;
        this.local = local;
    }

    public String getNome() {
        return nomePeca;
    }

    public void setNome(String nome) {
        this.nomePeca = nome;
    }

    public LocalDate getData() {
        return data;
    }
    
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
}
