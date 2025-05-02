package es.unican.sergio.dae.polaflix.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.PathVariable;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import es.unican.sergio.dae.polaflix.repository.usuarioRepository;
import es.unican.sergio.dae.polaflix.rest.Views.SerieBasic;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import es.unican.sergio.dae.polaflix.dominio.CapVisto;
import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.CapsVistosSerie;
import es.unican.sergio.dae.polaflix.dominio.Factura;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.service.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class UsuarioController {

    @Autowired
    protected UsuarioService us;

    
    @GetMapping("/usuario/{id}")
    @JsonView(Views.UsuarioBasic.class)
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {

        ResponseEntity<Usuario> response = null;
        try { 
            Usuario usuario = us.getUsuario(id);
            response = ResponseEntity.ok(usuario);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    @GetMapping("/facturas/{usuarioId}")
    @JsonView(Views.UsuarioFactura.class)
    public ResponseEntity<List<Factura>> getFacturas(@PathVariable Integer usuarioId) {
        ResponseEntity<List<Factura>> response = null;
        try { 
            List<Factura> facturas = us.getFacturas(usuarioId);
            response = ResponseEntity.ok(facturas);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    
    @GetMapping("/usuarioSeriesEmpezadas/{usuarioId}")
    @JsonView(Views.serieUsuario.class)
    public ResponseEntity<List<CapsVistosSerie>> getSeriesUsuario (@PathVariable Integer usuarioId) {
        ResponseEntity<List<CapsVistosSerie>> response = null;
        try { 
            List<CapsVistosSerie> series = us.getSeriesEmpezadas(usuarioId);
            response = ResponseEntity.ok(series);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;

    }

    @GetMapping("/usuarioSeriesTerminadas/{usuarioId}")
    @JsonView(Views.serieUsuario.class)
    public ResponseEntity<List<CapsVistosSerie>> getSeriesUsuarioTerminadas (@PathVariable Integer usuarioId) {
        ResponseEntity<List<CapsVistosSerie>> response = null;
        try { 
            List<CapsVistosSerie> series = us.getSeriesTerminadas(usuarioId);
            response = ResponseEntity.ok(series);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;

    }

    @GetMapping("/usuarioSeriesPorVer/{usuarioId}")
    @JsonView(Views.SerieBasic.class)
    public ResponseEntity<List<Serie>> getSeriesUsuarioPorVer (@PathVariable Integer usuarioId) {
        ResponseEntity<List<Serie>> response = null;
        try { 
            List<Serie> series = us.getSeriesPorVer(usuarioId);
            response = ResponseEntity.ok(series);
        }
        catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    @GetMapping("/capVisto/{usuarioId}/{serieId}")
    @JsonView(Views.serieUsuarioDetail.class)
    public ResponseEntity<List<CapVisto>> getCapVisto(@PathVariable Integer usuarioId, @PathVariable Integer serieId) {
        ResponseEntity<List<CapVisto>> response = null;
        try {
            List<CapVisto> capitulosVistos = us.getCapsVistosSerie(usuarioId, serieId);
            response = ResponseEntity.ok(capitulosVistos);
            
        } catch (Exception e) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    @PutMapping("usuarioSeriesPorVer/{usuarioId}")
    public ResponseEntity<?> seriePendienteAdd(@PathVariable Integer usuarioId, @RequestBody Serie serie) {

        ResponseEntity<?> response = null;
        try  {
            us.anhadirPendiente(serie, usuarioId);
            response = ResponseEntity.ok("Serie añadida a la lista de pendientes");
        } catch (Exception e) {
            if(e.getMessage().equals("Serie no encontrada")) {
                response = ResponseEntity.badRequest().body(e.getMessage());
            } else if (e.getMessage().equals("Usuario no encontrado")) {
                response = ResponseEntity.badRequest().body(e.getMessage());
            } else {
                response = ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        
        return response;
        
    }

    @DeleteMapping("usuarioSeriesPorVer/{usuarioId}")
    public ResponseEntity<?> seriePendienteDelete(@PathVariable Integer usuarioId, @RequestBody Serie serie) {

        ResponseEntity<?> response = null;
        try{
            us.borrarSeriePendiente(serie, usuarioId);
            response = ResponseEntity.ok("Serie eliminada de la lista de pendientes");
        }
        catch (Exception e) {
            if(e.getMessage().equals("Serie no encontrada")) {
                response = ResponseEntity.notFound().build();
            } else if (e.getMessage().equals("Usuario no encontrado")) {
                response = ResponseEntity.notFound().build();
            } else {
                response = ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        
        return response;
    }

    @PostMapping("verCapitulo/{usuarioId}")
    public ResponseEntity<?> verCapitulo(@PathVariable Integer usuarioId, @RequestBody Capitulo Capitulo) {
        ResponseEntity<?> response = null;
        try {
            us.verCapitulo(Capitulo, usuarioId);
            response = ResponseEntity.ok("Capitulo visto añadido a la lista de capitulos vistos");
        } catch (Exception e) {
            if(e.getMessage().equals("Capitulo no encontrado")) {
                response = ResponseEntity.notFound().build();
            } else if (e.getMessage().equals("Usuario no encontrado")) {
                response = ResponseEntity.notFound().build();
            } else if (e.getMessage().equals("Serie ya añadida a la lista de pendientes")) {
                response = ResponseEntity.ok("Serie ya añadida a la lista de pendientes");
            } else {
                response = ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        
        return response;
    }

    

}
