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
import es.unican.sergio.dae.polaflix.dominio.Pago;
import es.unican.sergio.dae.polaflix.dominio.Participante;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.dominio.Tipo;
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

		Usuario usuario = new Usuario(Pago.porVisualizacion, "John Doe", "ES1234567890123456789012", "password123");
        

        // Create and save all Participante instances first
        Participante actor1 = new Participante("Bryan Cranston");
        Participante actor2 = new Participante("Aaron Paul");
        Participante actor3 = new Participante("Anna Gunn");
        Participante actor4 = new Participante("Dean Norris");
        Participante actor5 = new Participante("Giancarlo Esposito");
        Participante actor6 = new Participante("Bob Odenkirk");
        Participante actor7 = new Participante("Millie Bobby Brown");
        Participante actor8 = new Participante("Finn Wolfhard");
        Participante actor9 = new Participante("Winona Ryder");
        Participante actor10 = new Participante("David Harbour");
        Participante actor11 = new Participante("Emilia Clarke");
        Participante actor12 = new Participante("Kit Harington");
        Participante actor13 = new Participante("Peter Dinklage");
        Participante actor14 = new Participante("Lena Headey");
        Participante actor15 = new Participante("Olivia Colman");
        Participante actor16 = new Participante("Claire Foy");
        Participante actor17 = new Participante("Matt Smith");
        Participante actor18 = new Participante("Tobias Menzies");
        Participante actor19 = new Participante("Pedro Pascal");
        Participante actor20 = new Participante("Gina Carano");
        Participante actor21 = new Participante("Carl Weathers");
        Participante actor22 = new Participante("Henry Cavill");
        Participante actor23 = new Participante("Anya Chalotra");
        Participante actor24 = new Participante("Freya Allan");
        Participante actor25 = new Participante("Joey Batey");
        Participante actor26 = new Participante("Álvaro Morte");
        Participante actor27 = new Participante("Úrsula Corberó");
        Participante actor28 = new Participante("Pedro Alonso");
        Participante actor29 = new Participante("Itziar Ituño");
        Participante actor30 = new Participante("Steve Carell");
        Participante actor31 = new Participante("John Krasinski");
        Participante actor32 = new Participante("Jenna Fischer");
        Participante actor33 = new Participante("Rainn Wilson");

        pr.save(actor1);
        pr.save(actor2);
        pr.save(actor3);
        pr.save(actor4);
        pr.save(actor5);
        pr.save(actor6);
        pr.save(actor7);
        pr.save(actor8);
        pr.save(actor9);
        pr.save(actor10);
        pr.save(actor11);
        pr.save(actor12);
        pr.save(actor13);
        pr.save(actor14);
        pr.save(actor15);
        pr.save(actor16);
        pr.save(actor17);
        pr.save(actor18);
        pr.save(actor19);
        pr.save(actor20);
        pr.save(actor21);
        pr.save(actor22);
        pr.save(actor23);
        pr.save(actor24);
        pr.save(actor25);
        pr.save(actor26);
        pr.save(actor27);
        pr.save(actor28);
        pr.save(actor29);
        pr.save(actor30);
        pr.save(actor31);
        pr.save(actor32);
        pr.save(actor33);

        // Now use the saved Participante instances in the Serie constructors
        Serie serie1 = new Serie(
            "Breaking Bad",
            "A high school chemistry teacher turned methamphetamine producer partners with a former student to secure his family's future.",
            Tipo.gold,
            3,
            Set.of(actor1, actor2), // Bryan Cranston, Aaron Paul
            Set.of(actor3, actor4)  // Anna Gunn, Dean Norris
        );

        Serie serie2 = new Serie(
            "Stranger Things",
            "A group of young friends witness supernatural forces and secret government exploits in their small town.",
            Tipo.gold,
            2,
            Set.of(actor7, actor8), // Millie Bobby Brown, Finn Wolfhard
            Set.of(actor9, actor10) // Winona Ryder, David Harbour
        );

        Serie serie3 = new Serie(
            "Game of Thrones",
            "Noble families vie for control of the Iron Throne as an ancient enemy returns after being dormant for millennia.",
            Tipo.gold,
            2,
            Set.of(actor11, actor12), // Emilia Clarke, Kit Harington
            Set.of(actor13, actor14) // Peter Dinklage, Lena Headey
        );

        Serie serie4 = new Serie(
            "The Crown",
            "Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the twentieth century.",
            Tipo.gold,
            2,
            Set.of(actor15, actor16), // Olivia Colman, Claire Foy
            Set.of(actor17, actor18) // Matt Smith, Tobias Menzies
        );

        Serie serie5 = new Serie(
            "The Mandalorian",
            "A lone bounty hunter in the outer reaches of the galaxy protects a mysterious child from those who seek to capture it.",
            Tipo.gold,
            2,
            Set.of(actor19, actor20), // Pedro Pascal, Gina Carano
            Set.of(actor21, actor5) // Carl Weathers, Giancarlo Esposito
        );

        Serie serie6 = new Serie(
            "The Witcher",
            "Geralt of Rivia, a solitary monster hunter, struggles to find his place in a world where people often prove more wicked than beasts.",
            Tipo.gold,
            2,
            Set.of(actor22, actor23), // Henry Cavill, Anya Chalotra
            Set.of(actor24, actor25) // Freya Allan, Joey Batey
        );

        Serie serie7 = new Serie(
            "Money Heist",
            "A criminal mastermind plans the biggest heist in recorded history to print billions of euros in the Royal Mint of Spain.",
            Tipo.gold,
            2,
            Set.of(actor26, actor27), // Álvaro Morte, Úrsula Corberó
            Set.of(actor28, actor29) // Pedro Alonso, Itziar Ituño
        );

        Serie serie8 = new Serie(
            "The Office",
            "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.",
            Tipo.gold,
            2,
            Set.of(actor30, actor31), // Steve Carell, John Krasinski
            Set.of(actor32, actor33) // Jenna Fischer, Rainn Wilson
        );


        sr.save(serie3);
        sr.save(serie2);
        sr.save(serie1);
        sr.save(serie4);
        sr.save(serie5);
        sr.save(serie6);
        sr.save(serie7);
        sr.save(serie8);
        usuario.addSeriePorVer(serie1);
        usuario.addSeriePorVer(serie2);
        usuario.addSeriePorVer(serie3);
        Capitulo capitulo1 = new Capitulo(1, "cap1", "desc", "url", 2, serie1);
        Capitulo capitulo2 = new Capitulo(2, "cap2", "desc", "url", 1, serie1);
        Capitulo capitulo4 = new Capitulo(3, "cap2", "desc", "url", 1, serie1);
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
		usuario.addSeriePorVer(serie3);
		ur.save(usuario);

        usuario.verCapitulo(capitulo4);
        ur.save(usuario);
        System.out.println("Factura 1: " + usuario.getFactura(5, 2025).getImporte());
        // Factura fact = usuario.getFactura(4, 2025);

        // List<CapsVistosSerie> series = usuario.getSeriesEmpezadas();


        //ur.delete(usuario);


		return;
	}



}
