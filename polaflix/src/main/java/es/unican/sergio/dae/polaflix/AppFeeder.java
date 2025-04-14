package es.unican.sergio.dae.polaflix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.CapsVistosSerie;
import es.unican.sergio.dae.polaflix.dominio.Factura;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import es.unican.sergio.dae.polaflix.repository.usuarioRepository;
import es.unican.sergio.dae.polaflix.repository.serieRepository;
import es.unican.sergio.dae.polaflix.*;
import java.util.List;
@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	protected usuarioRepository ur;
	@Autowired
	protected serieRepository sr;

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(Usuario.Pago.porVisualizacion, "John Doe", "ES1234567890123456789012", "password123");
        Serie serie1 = new Serie("Serie 1", "desc", Serie.Tipo.gold, 3);
		usuario.addSeriePorVer(serie1);


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
        System.out.println("Factura 1: " + usuario.getFactura(4, 2025).getImporte());
		System.out.println("Application feeded");
		sr.save(serie1);
		sr.save(serie2);
		ur.save(usuario);
        // Factura fact = usuario.getFactura(4, 2025);

        // List<CapsVistosSerie> series = usuario.getSeriesEmpezadas();


        ur.delete(usuario);


		return;
	}



}
