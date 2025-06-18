package es.unican.sergio.dae.polaflix.rest;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.sergio.dae.polaflix.dominio.CapsVistosSerie;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;

public class VistaSerieUsuario {
    @JsonView(Views.serieUsuarioDetail.class)
    private Serie serie;
    @JsonView(Views.serieUsuarioDetail.class)
    private CapsVistosSerie capsVistosSerie;


    public VistaSerieUsuario() {
        // Constructor vacío
    }
    public VistaSerieUsuario(Serie serie, CapsVistosSerie usuario) {
        this.serie = serie;
        this.capsVistosSerie = usuario;
    }
}
