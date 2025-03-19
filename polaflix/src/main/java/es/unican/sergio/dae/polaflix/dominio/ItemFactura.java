package es.unican.sergio.dae.polaflix.dominio;


import java.util.*;
public class ItemFactura {
    private float precio;
    private int temporada;
    private int numero;
    private Serie serie;
    private Date fecha;

    public ItemFactura(float precio, int temporada, int numero, Serie serie) {
        this.precio = precio;
        this.temporada = temporada;
        this.numero = numero;
        this.serie = serie;
        this.fecha = new Date();
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