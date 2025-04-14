package es.unican.sergio.dae.polaflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import es.unican.sergio.dae.polaflix.repository.*;
@SpringBootApplication
public class PolaflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolaflixApplication.class, args);

		
	}

}
