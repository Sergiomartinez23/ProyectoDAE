package es.unican.sergio.dae.polaflix.rest;

import es.unican.sergio.dae.polaflix.dominio.CapsVistosSerie;

import es.unican.sergio.dae.polaflix.dominio.Serie;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

public class SeriesVistasUsuario {

    @JsonView(Views.serieUsuario.class)
    private List<CapsVistosSerie> seriesEmpezadas;
    @JsonView(Views.serieUsuario.class)
    private List<CapsVistosSerie> seriesTerminadas;
    @JsonView(Views.serieUsuario.class)
    private Set<Serie> seriesPorVer;


    public SeriesVistasUsuario(List<CapsVistosSerie> seriesEmpezadas, List<CapsVistosSerie> seriesTerminadas, Set<Serie> seriesPorVer) {
        this.seriesEmpezadas = seriesEmpezadas;
        this.seriesTerminadas = seriesTerminadas;
        this.seriesPorVer = seriesPorVer;
    }
}
