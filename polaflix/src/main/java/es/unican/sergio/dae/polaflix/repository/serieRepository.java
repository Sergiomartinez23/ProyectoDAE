package es.unican.sergio.dae.polaflix.repository;

import java.io.Serial;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;

public interface serieRepository extends JpaRepository<Serie, Integer> {}    
