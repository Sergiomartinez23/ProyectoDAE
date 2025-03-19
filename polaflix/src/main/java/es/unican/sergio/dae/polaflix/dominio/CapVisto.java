package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;
public class CapVisto {
    private int numero;
    private int temporada;
    private Date fecha;
    public CapVisto (int numero, int temporada) {
        this.numero = numero;
        this.temporada = temporada;
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