package es.unican.sergio.dae.polaflix.dominio;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.sergio.dae.polaflix.rest.Views;
import jakarta.persistence.*;

@Entity
public class Participante {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    private String nombreCompleto;


    public Participante() {
        // Constructor vacío
    }
    public Participante(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
