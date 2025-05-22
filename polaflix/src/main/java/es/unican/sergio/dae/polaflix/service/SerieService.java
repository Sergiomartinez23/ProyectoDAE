package es.unican.sergio.dae.polaflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.sergio.dae.polaflix.repository.serieRepository;
import jakarta.transaction.Transactional;

import es.unican.sergio.dae.polaflix.dominio.Serie;
import java.util.List;

@Service
public class SerieService {
    
    public SerieService() {
        // Constructor
    }
    @Autowired
    protected serieRepository sr;
    @Transactional
    public List<Serie> getSerieNombre(String nombre) {
        return sr.findByTituloContainsIgnoreCase(nombre);

        
    }

    @Transactional
    public List<Serie> getSerieNombreStart(String nombre) {
    
        return sr.findByTituloStartsWithIgnoreCase(nombre);
    }

    @Transactional
    public List<Serie> getAllSeries() {
        return sr.findAll();
    }

    @Transactional
    public Serie getSerieById(Integer id) {
        return sr.findById(id).orElse(null);
    }
}
