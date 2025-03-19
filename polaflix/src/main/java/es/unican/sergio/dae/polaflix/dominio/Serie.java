package es.unican.sergio.dae.polaflix.dominio;

import java.util.*;

public class Serie {
    public enum Tipo {
        silver, gold, estandar
    }
    private String titulo;
    private String descripcion;
    private List<Capitulo> capitulos;
    private Tipo tipo;
    private List<Integer> episodiosporTemporada;
    private int temporadas;

    public Serie(String titulo, String descripcion, Tipo tipo, int temporadas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.capitulos = new ArrayList<>();
        this.episodiosporTemporada = new ArrayList<>(temporadas);
        this.temporadas = temporadas;
        this.episodiosporTemporada = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
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