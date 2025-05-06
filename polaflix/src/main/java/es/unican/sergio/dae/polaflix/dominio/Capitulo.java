package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Capitulo {
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    private int numero;
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    private String titulo;
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    private String descripcion;
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    private String url;
    @JsonView({Views.SerieDetail.class, Views.CapituloBasic.class})
    private int temporada;
    // @JsonView(Views.CapituloBasic.class)
    @OneToOne private Serie   serie;

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
        if (serie.getTipo() == Serie.Tipo.estandar) {
            return 0.5f;
        }
        else if (serie.getTipo() == Serie.Tipo.silver) {
            return 1.0f;
        }
        else if (serie.getTipo() == Serie.Tipo.gold) {
            return 1.5f;
        }
        return 0.0f;
    }
}