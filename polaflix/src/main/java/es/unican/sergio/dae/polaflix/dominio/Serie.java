package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Serie {
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class, Views.CapituloBasic.class})
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

  
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class, Views.FacturaDetail.class})
    private String titulo;
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    private String descripcion;
    @JsonView(Views.serieUsuarioDetail.class)
    @OneToMany(cascade = CascadeType.ALL) 
    @JoinColumn(name = "serie_id")
    private List<Capitulo> capitulos;
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    private Tipo tipo;
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    private List<Integer> episodiosporTemporada;
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    private int temporadas;
    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    @ManyToMany private Set<Participante> actores;

    @JsonView({Views.SerieBasic.class, Views.serieUsuarioDetail.class})
    @ManyToMany private Set<Participante> director;
    
    public Serie() {
        this.episodiosporTemporada = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }
    public Serie(String titulo, String descripcion, Tipo tipo, int temporadas, Set<Participante> actores, Set<Participante> director) {
        this();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.capitulos = new ArrayList<>();
        // Crear un capítulo falso para pruebas
        Capitulo capituloFalso = new Capitulo(1, "Capítulo Piloto", "Descripción del capítulo piloto", "http://url-del-capitulo.com", 1, this);
        addCapitulo(capituloFalso);
        this.temporadas = temporadas;
        // this.episodiosporTemporada = new ArrayList<>(temporadas);
        this.episodiosporTemporada = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
        this.actores = actores;
        this.director = director;
    }
    public int getId() {
        return id;
    }
    // Esto esta para hardcoder el capitulo falso
    public void addCapitulo(Capitulo capitulo) {
        capitulo.setSerie(this);  // Make sure the Capitulo knows its Serie
        this.capitulos.add(capitulo);
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
        // Accede a un capitulo determinado por ello se ha de recorrer posicionalmente
        for (int i = 0; i < temporada-1; i++) {
            pos = pos + episodiosporTemporada.get(i);
        }
        pos = pos + episodio - 1;
        return capitulos.get(pos);
    }

    public int getTemporadas() {
        return temporadas;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.id == ((Serie)obj).getId()) {
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