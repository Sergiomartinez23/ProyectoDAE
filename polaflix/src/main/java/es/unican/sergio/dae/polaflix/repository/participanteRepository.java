package es.unican.sergio.dae.polaflix.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.sergio.dae.polaflix.dominio.Participante;
public interface participanteRepository extends JpaRepository<Participante, Integer> {
    // Esta interfaz es la que se encarga de la persistencia de los participantes en la base de datos.
    // No es necesario añadir métodos adicionales, ya que JpaRepository proporciona todos los métodos necesarios para la persistencia.

}
