package es.unican.sergio.dae.polaflix.dominio;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class ItemFactura {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    private float precio;
    private int temporada;
    private int numero;
    @OneToOne private Serie serie;
    private Date fecha;

    public ItemFactura() {
    }
    public ItemFactura(float precio, int temporada, int numero, Serie serie) {
        this.precio = precio;
        this.temporada = temporada;
        this.numero = numero;
        this.serie = serie;
        this.fecha = new Date();
    }
    public int getId() {
        return id;
    }
    public float getPrecio() {
        return precio;
    }
    public int getTemporada() {
        return temporada;
    }
    public int getNumero() {
        return numero;
    }
    public Serie getSerie() {
        return serie;
    }
    public Date getFecha() {
        return fecha;
    }
}