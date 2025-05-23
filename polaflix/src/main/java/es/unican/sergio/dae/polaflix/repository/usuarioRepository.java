package es.unican.sergio.dae.polaflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.sergio.dae.polaflix.dominio.Usuario;

public interface usuarioRepository extends JpaRepository<Usuario, Integer> {}    
//Esta interfaz es la que se encarga de la persistencia de los usuarios en la base de datos.