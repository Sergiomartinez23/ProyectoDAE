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
import es.unican.sergio.dae.polaflix.dominio.Participante;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import es.unican.sergio.dae.polaflix.repository.usuarioRepository;
import es.unican.sergio.dae.polaflix.repository.participanteRepository;
import es.unican.sergio.dae.polaflix.repository.serieRepository;
import es.unican.sergio.dae.polaflix.*;
import java.util.List;
@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	protected usuarioRepository ur;
	@Autowired
	protected serieRepository sr;
    @Autowired
    protected participanteRepository pr;
	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario(Usuario.Pago.porVisualizacion, "John Doe", "ES1234567890123456789012", "password123");
        Participante actor1 = new Participante("Actor 1");
        Participante actor2 = new Participante("Actor 2");
        Participante actor3 = new Participante("Actor 3");
        Participante actor4 = new Participante("Actor 4");
        Participante actor5 = new Participante("Actor 5");
        Participante actor6 = new Participante("Actor 6");

        pr.save(actor1);
        pr.save(actor2);
        pr.save(actor3);
        pr.save(actor4);
        pr.save(actor5);
        pr.save(actor6);
        Serie serie1 = new Serie("Serie 1", "desc", Serie.Tipo.gold, 3, List.of(actor1), List.of(actor3));
		
        

        Serie serie2 = new Serie("Serie 2", "desc", Serie.Tipo.gold, 2, List.of(actor2), List.of(actor4));

        Serie serie3 = new Serie("Serie 3", "desc", Serie.Tipo.gold, 2, List.of(actor5), List.of(actor3, actor4));
        sr.save(serie3);
        sr.save(serie2);
        sr.save(serie1);
        usuario.addSeriePorVer(serie1);
        usuario.addSeriePorVer(serie2);
        usuario.addSeriePorVer(serie3);
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
        System.out.println("Factura 1: " + usuario.getFactura(5, 2025).getImporte());
		System.out.println("Application feeded");
		
		ur.save(usuario);
        // Factura fact = usuario.getFactura(4, 2025);

        // List<CapsVistosSerie> series = usuario.getSeriesEmpezadas();


        //ur.delete(usuario);


		return;
	}



}
