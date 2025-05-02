package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;


@Entity
public class CapVisto {
    @JsonView(Views.serieUsuarioDetail.class)
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

    @JsonView(Views.serieUsuarioDetail.class)
    private int numero;

    @JsonView(Views.serieUsuarioDetail.class)
    private int temporada;

    @JsonView(Views.serieUsuarioDetail.class)
    private Date fecha;

    public CapVisto() {
     
    }
    public CapVisto (int numero, int temporada) {
        this.numero = numero;
        this.temporada = temporada;
    }
    public int getId() {
        return id;
    }
    public int getNumero() {
        return numero;
    }
    public int getTemporada() {
        return temporada;
    }

    
    public Date getFecha() {
        return fecha;
    }

}