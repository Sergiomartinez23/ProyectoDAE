package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;

import es.unican.sergio.dae.polaflix.rest.Views;
@Entity
public class CapsVistosSerie {
    @OneToMany(cascade = CascadeType.ALL) 
    @JoinColumn(name = "capsVistosSerie_id")
    private SortedSet<CapVisto> capsVistos; 
    @JsonView(Views.serieUsuario.class)
    @OneToOne
    private Serie serie;
    @JsonView(Views.serieUsuario.class)
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;

    public CapsVistosSerie() {
        
    }
    public CapsVistosSerie(Serie serie) {
        this.serie = serie;
        this.capsVistos = new TreeSet<CapVisto>();
    }
    

    public int getId() {
        return id;
    }

    public int addCapitulo(CapVisto capitulo) {
        
        int temp = capitulo.getTemporada();
        int num = capitulo.getNumero();
        if (temp > serie.getTemporadas()) {
            return -1;
        }
        if (num > serie.getCapitulosTemporada(temp)) {
            return -1;
        }
        
        if (capsVistos.contains(capitulo)) {
            return -1;
        }
        
        
        capsVistos.add(capitulo);
        return 0;
    }

    public Set<CapVisto> getCapitulosVistos() {
        return capsVistos;
    }
    
    public Serie getSerie() {
        return serie;
    }
    //This is just to see if a serie is finished
    public int numUltimoCapitulo() {
        
        if (capsVistos.size() == 0) {
            return 0;
        }
        
        return capsVistos.last().getNumero();
    }
    public int numUltimaTemporada() {
        if (capsVistos.size() == 0) {
            return 0;
        }
        
        return capsVistos.last().getTemporada();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.serie.getId() == ((CapsVistosSerie) o).serie.getId()) {
            return true;
        }
        return false;

    }

    @Override 
    public int hashCode() {
        return this.id;
    }
}