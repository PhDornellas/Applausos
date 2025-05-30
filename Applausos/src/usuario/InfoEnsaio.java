package usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InfoEnsaio implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate data;
    private LocalTime hora;
    private String local;
    private String feedback;
    private List<String> membros;

    public InfoEnsaio(LocalDate data,
                      LocalTime hora,
                      String local,
                      String feedback,
                      List<String> membros) {
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.feedback = feedback;
        this.membros = membros;
    }

    public LocalDate getData()                 { return data; }
    public String getDataFormatada()           {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public LocalTime getHora()                 { return hora; }
    public String getHoraFormatada()           {
        return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public String getLocal()                   { return local; }
    public String getFeedback()                { return feedback; }
    public List<String> getMembros()           { return membros; }
}
