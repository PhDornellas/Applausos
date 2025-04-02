package usuario;

public class InfoPeca {
    String nomePeca;
    int dia;
    int mes;
    int ano;
    double valor;

    public InfoPeca(String nomePeca,int dia,int mes,int ano, double valor){

        this.nomePeca = nomePeca;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
    }

    public String getNome(){return nomePeca;}
    public void setNome(String nome){this.nomePeca = nome;}

    public int getDia(){return dia;}
    public void setDia(int dia){this.dia = dia;}

    public int getMes(){return mes;}
    public void setMes(int mes){this.mes = mes;}

    public int getAno(){return ano;}
    public void setAno(int ano){this.ano = ano;}

    public double getValor(){return valor;}
    public void setValor(double valor){this.valor = valor;}


}

