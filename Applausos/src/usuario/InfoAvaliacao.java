package usuario;

public class InfoAvaliacao {
    private int estrelas;
    private String comentario;

    public InfoAvaliacao(int estrelas, String comentario) {
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public int getEstrelas() { return estrelas; }
    public String getComentario() { return comentario; }
}
