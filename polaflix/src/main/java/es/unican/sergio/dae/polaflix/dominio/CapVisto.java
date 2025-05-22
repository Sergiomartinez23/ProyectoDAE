package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;


@Entity
public class CapVisto implements Comparable<CapVisto> {
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

    @Override
    public int compareTo(CapVisto o) {
        if (this.temporada == o.temporada) {
            return this.numero - o.numero;
        } else {
            return this.temporada - o.temporada;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.numero == ((CapVisto) obj).getNumero() && this.temporada == ((CapVisto) obj).getTemporada()) {
            return true;
        } else {
            return false;
        }
    }
    @Override 
    public int hashCode() {
        return this.id;
    }
}