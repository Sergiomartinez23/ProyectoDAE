package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;
public class CapsVistosSerie {
    private List<List<CapVisto>> capsVistos; //Usamos doble lista para poder acceder a los capitulos vistos de cada temporada
    private Serie serie;
    public CapsVistosSerie(Serie serie) {
        this.serie = serie;
        capsVistos = new ArrayList<List<CapVisto>>();
        for (int i = 0; i < serie.getTemporadas(); i++) {//Se asume que nunca va a llegar un cap con temporada mas alta que las de la serie ya que se pasan desde el front
            capsVistos.add(new LinkedList<CapVisto>());
        }
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
        
        
        for (CapVisto cap : capsVistos.get(temp-1)) {
            if (cap.getNumero() == num) {
                return -1;
            }
            if (cap.getNumero() > num) {
                capsVistos.get(temp-1).add(capsVistos.get(temp-1).indexOf(cap),capitulo);
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