package es.unican.sergio.dae.polaflix.repository;

import java.io.Serial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import java.util.List;
import java.util.Optional;
public interface serieRepository extends JpaRepository<Serie, Integer> {

    List<Serie> findByTituloContainsIgnoreCase(String nombre);
    List<Serie> findByTituloStartsWithIgnoreCase(String nombre);

    @Query("SELECT c FROM Serie s JOIN s.capitulos c WHERE c.id = :capituloId")
    Optional<Capitulo> findCapituloById(@Param("capituloId") Integer capituloId);
}    
