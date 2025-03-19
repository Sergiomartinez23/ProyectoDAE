package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;
public class CapsVistosSerie {
    private List<List<CapVisto>> capsVistos; //Usamos doble lista para poder acceder a los capitulos vistos de cada temporada
    private Serie serie;
    public CapsVistosSerie(Serie serie) {
        this.serie = serie;
        capsVistos = new ArrayList<List<CapVisto>>();
        for (int i = 0; i < serie.getTemporadas(); i++) {
            capsVistos.add(new LinkedList<CapVisto>());
        }
    }

     

    public int addCapitulo(CapVisto capitulo) {
        
        int temp = capitulo.getTemporada();
        int num = capitulo.getNumero();
        
        
        
        for (CapVisto cap : capsVistos.get(temp-1)) {
            if (cap.getNumero() == num) {
                return -1;
            }
        }
        
        CapVisto cap;
        for (int i = 0; i < capsVistos.get(temp-1).size(); i++) {
            cap = capsVistos.get(temp-1).get(i);
            if (cap.getNumero() > num) {
                capsVistos.get(temp-1).add(i,capitulo);
                return 0;
            }
        }
        capsVistos.get(temp-1).add(capitulo);
        return 0;
    }

    public List<List<CapVisto>> getCapitulosVistos() {
        return capsVistos;
    }
    
    public Serie getSerie() {
        return serie;
    }
    //This is just to see if a serie is finished
    public int numUltimoCapitulo() {
        int numTemp = capsVistos.size();
        
        if (capsVistos.get(numTemp-1).size() == 0) {
            return 0;
        }
        
        return capsVistos.get(numTemp-1).get(capsVistos.get(numTemp-1).size()-1).getNumero();
    }
}