package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

import jakarta.persistence.*;
@Entity
public class CapsVistosSerie {
    @OneToMany private List<CapVisto> capsVistos; //Usamos doble lista para poder acceder a los capitulos vistos de cada temporada
    @OneToOne private Serie serie;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

    public CapsVistosSerie(Serie serie) {
        this.serie = serie;
        capsVistos = new ArrayList<CapVisto>();
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
        
        
        for (CapVisto cap : capsVistos) {
            if (cap.getNumero() == num || cap.getTemporada() == temp) {
                return -1;
            }
            if (cap.getNumero() > num && cap.getTemporada() == temp) {
                //Si el capitulo ya existe, lo añadimos a la lista de capitulos vistos de esa temporada
                capsVistos.add(capsVistos.indexOf(cap), capitulo);
                return 0;
            }
        }
        
        
        capsVistos.add(capitulo);
        return 0;
    }

    public List<CapVisto> getCapitulosVistos() {
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
        
        return capsVistos.get(capsVistos.size()-1).getNumero();
    }
}