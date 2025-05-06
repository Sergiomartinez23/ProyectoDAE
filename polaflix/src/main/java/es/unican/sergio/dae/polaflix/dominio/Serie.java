package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Serie {
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class, Views.CapituloBasic.class})
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

    public enum Tipo {
        silver, gold, estandar
    }
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class, Views.FacturaDetail.class})
    private String titulo;
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    private String descripcion;
    @JsonView(Views.SerieDetail.class)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) private List<Capitulo> capitulos;
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    private Tipo tipo;
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    private List<Integer> episodiosporTemporada;
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    private int temporadas;
    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    @ManyToMany(cascade = CascadeType.PERSIST) private List<Participante> actores;

    @JsonView({Views.SerieBasic.class, Views.SerieDetail.class})
    @ManyToMany(cascade = CascadeType.PERSIST) private List<Participante> director;
    
    public Serie() {
        this.episodiosporTemporada = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }
    public Serie(String titulo, String descripcion, Tipo tipo, int temporadas, List<Participante> actores, List<Participante> director) {
        this();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.capitulos = new ArrayList<>();
        this.temporadas = temporadas;
        // this.episodiosporTemporada = new ArrayList<>(temporadas);
        this.episodiosporTemporada = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
        this.actores = actores;
        this.director = director;
    }
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }
    public void addTemporada(int episodios) {
        episodiosporTemporada.add(episodios);
        temporadas++;
    }
    public int getLastCapitulo() {
        return episodiosporTemporada.get(temporadas-1);
    }
    public int getCapitulosTemporada(int temporada) {
        return episodiosporTemporada.get(temporada-1);
    }
    public Capitulo getCapitulo(int temporada, int episodio) {
        int pos = 0;
        for (int i = 0; i < temporada-1; i++) {
            pos = pos + episodiosporTemporada.get(i);
        }
        pos = pos + episodio - 1;
        return capitulos.get(pos);
    }

    public int getTemporadas() {
        return temporadas;
    }

}