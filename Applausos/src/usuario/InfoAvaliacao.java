package usuario;

import java.io.Serializable;

public class InfoAvaliacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private int estrelas;
    private String comentario;

    public InfoAvaliacao(int estrelas, String comentario) {
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public int getEstrelas()    { return estrelas; }
    public String getComentario() { return comentario; }
}