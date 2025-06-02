package usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InfoPeca implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomePeca;
    private LocalDate data;
    private double valor;
    private String local;
    private int ingressosVendidos;
    private static final int CAPACIDADE = 100;
    private List<InfoAvaliacao> avaliacoes = new ArrayList<>();

    public InfoPeca(String nomePeca,
                    int dia, int mes, int ano,
                    double valor,
                    String local) {
        this.nomePeca = nomePeca;
        this.data = LocalDate.of(ano, mes, dia);
        this.valor = valor;
        this.local = local;
        this.ingressosVendidos = 0;
    }

    public String getNome()                     { return nomePeca; }
    public void setNome(String nome)            { this.nomePeca = nome; }
    public LocalDate getData()                  { return data; }
    public String getDataFormatada()            {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public void setData(LocalDate data)         { this.data = data; }
    public double getValor()                    { return valor; }
    public void setValor(double valor)          { this.valor = valor; }
    public String getLocal()                    { return local; }
    public void setLocal(String local)          { this.local = local; }
    public int getIngressosVendidos()           { return ingressosVendidos; }
    public int getIngressosRestantes()          {
        return CAPACIDADE - ingressosVendidos;
    }
    public void incrementarIngressosVendidos()  {
        this.ingressosVendidos++;
    }

    public void adicionarAvaliacao(int estrelas,
                                   String comentario) {
        this.avaliacoes.add(new InfoAvaliacao(estrelas, comentario));
    }
    public List<InfoAvaliacao> getAvaliacoes()  {
        return avaliacoes;
    }
}
