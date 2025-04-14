package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
@Entity
public class CapVisto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

    private int numero;
    private int temporada;
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