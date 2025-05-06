package es.unican.sergio.dae.polaflix.rest;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus.Series;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.repository.serieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.unican.sergio.dae.polaflix.service.*;
import java.util.List;

@RestController
public class SerieController {

    @Autowired
	protected serieRepository sr;
    @Autowired
    protected SerieService ss;
    @GetMapping("/serie/{id}")
    @JsonView(Views.SerieDetail.class)
    public ResponseEntity<Serie> getSerie(@PathVariable Integer id) {
        Optional<Serie> series = sr.findById(id);
        ResponseEntity<Serie> response = null;
        if (series.isPresent()) {
            response = ResponseEntity.ok(series.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    
    @GetMapping("/series")
    @JsonView(Views.SerieBasic.class)
    public List<Serie> getAllSeries() {
        return sr.findAll();
    }
    
    @GetMapping("/series/{nombre}")
    @JsonView(Views.SerieBasic.class)
    public ResponseEntity<List<Serie>> getSeriesByNombre(@PathVariable String nombre) {
        List<Serie> series = ss.getSerieNombre(nombre);
        ResponseEntity<List<Serie>> response = null;
        if (series.isEmpty()) {
            response = ResponseEntity.notFound().build();
        } else {
            response = ResponseEntity.ok(series);
        }
        return response;
    }
    
}
