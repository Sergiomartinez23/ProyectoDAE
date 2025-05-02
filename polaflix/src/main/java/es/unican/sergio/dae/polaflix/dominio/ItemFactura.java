package es.unican.sergio.dae.polaflix.dominio;

import jakarta.persistence.*;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonView;
import es.unican.sergio.dae.polaflix.rest.*;
@Entity
public class ItemFactura {
    @JsonView(Views.FacturaDetail.class)
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    @JsonView(Views.FacturaDetail.class)
    private float precio;
    @JsonView(Views.FacturaDetail.class)
    private int temporada;
    @JsonView(Views.FacturaDetail.class)
    private int numero;
    @JsonView(Views.FacturaDetail.class)
    @ManyToOne private Serie serie;
    @JsonView(Views.FacturaDetail.class)
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