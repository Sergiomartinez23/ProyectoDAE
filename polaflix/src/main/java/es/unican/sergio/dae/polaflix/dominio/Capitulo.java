package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Capitulo {
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    private int numero;
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    private String titulo;
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    private String descripcion;
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    private String url;
    @JsonView({Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    private int temporada;
    // @JsonView(Views.CapituloBasic.class)
    @ManyToOne private Serie   serie;

    public Capitulo() {
    }
    public Capitulo(int numero, String titulo, String descripcion, String url, int temporada, Serie serie) {
        this.numero = numero;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.serie = serie;
        this.temporada = temporada;
    }
    public int getId() {
        return id;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    public Serie getSerie() {
        return serie;
    }
    public int getNumero() {
        return numero;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUrl() {
        return url;
    }
    public int getTemporada() {
        return temporada;
    }

    public float getPrecio() {
        if (serie.getTipo() == Tipo.estandar) {
            return 0.5f;
        }
        else if (serie.getTipo() == Tipo.silver) {
            return 1.0f;
        }
        else if (serie.getTipo() == Tipo.gold) {
            return 1.5f;
        }
        return 0.0f;
    }
}