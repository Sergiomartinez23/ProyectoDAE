package es.unican.sergio.dae.polaflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;

@SpringBootApplication
public class PolaflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolaflixApplication.class, args);
		Usuario usuario = new Usuario(Usuario.Pago.porVisualizacion, "John Doe", "ES1234567890123456789012", "password123");

        Serie serie1 = new Serie("Serie 1", "desc", Serie.Tipo.gold, 3);
        Serie serie2 = new Serie("Serie 2", "desc", Serie.Tipo.gold, 2);

        usuario.addSeriePorVer(serie1);
        usuario.addSeriePorVer(serie2);

        Capitulo capitulo1 = new Capitulo(1, "cap1", "desc", "url", 1, serie1);
        Capitulo capitulo2 = new Capitulo(2, "cap2", "desc", "url", 1, serie1);
        Capitulo capitulo3 = new Capitulo(1, "cap1", "desc", "url", 2, serie2);

        usuario.verCapitulo(capitulo1);
        usuario.verCapitulo(capitulo2);
        usuario.verCapitulo(capitulo3);
        // System.out.println("Facturas: " + usuario.facturas.size());
        System.out.println("Series por ver: " + usuario.getSeriesPorVer().size());
        System.out.println("Series empezadas: " + usuario.getSeriesEmpezadas().size());
        System.out.println("Series terminadas: " + usuario.getSeriesTerminadas().size());
        System.out.println("Factura 1: " + usuario.getFactura(3, 2025).getImporte());
	}

}
