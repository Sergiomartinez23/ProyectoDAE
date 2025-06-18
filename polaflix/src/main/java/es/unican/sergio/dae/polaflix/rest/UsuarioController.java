package es.unican.sergio.dae.polaflix.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.PathVariable;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "*")

public class UsuarioController {

    @Autowired
    protected UsuarioService us;

    @GetMapping("/usuario/{usuarioId}/series/{serieId}")
    @JsonView(Views.serieUsuarioDetail.class)
    public ResponseEntity<VistaSerieUsuario> getSerieDetalle(@PathVariable Integer usuarioId, @PathVariable Integer serieId) {
        ResponseEntity<VistaSerieUsuario> response = null;

        VistaSerieUsuario serie = us.getSerieUsuario(usuarioId, serieId);

        if (serie != null) {
            response = ResponseEntity.ok(serie);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;

    }
    

    @GetMapping("/usuarios/{usuariosId}/facturas")
    @JsonView(Views.UsuarioFactura.class)
    public ResponseEntity<List<Factura>> getFacturas(@PathVariable Integer usuarioId) {
        ResponseEntity<List<Factura>> response = null;
        
        List<Factura> facturas = us.getFacturas(usuarioId);
        if (facturas != null) {
            response = ResponseEntity.ok(facturas);
        } else {
            response = ResponseEntity.notFound().build();
        }        
        return response;
    }

    @GetMapping("/usuarios/{usuarioId}/Series")
    @JsonView(Views.serieUsuario.class)
    public ResponseEntity<SeriesVistasUsuario> getSeriesUsuario(@PathVariable Integer usuarioId) {
        ResponseEntity<SeriesVistasUsuario> response = null;
        SeriesVistasUsuario seriesVistasUsuario = us.getSeriesUsuario(usuarioId);
        if (seriesVistasUsuario != null) {
            response = ResponseEntity.ok(seriesVistasUsuario);
        } else {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }
    

    @PostMapping("/usuarios/{usuarioId}/verCapitulo/{idCapitulo}")
    public ResponseEntity<?> verCapitulo(@PathVariable Integer usuarioId, @PathVariable Integer idCapitulo) {
        ResponseEntity<?> response = null;
        
        int result = us.verCapitulo(idCapitulo, usuarioId);
        
        if (result == 0) {
            response = ResponseEntity.ok("Capitulo ya estaba en la lista de capitulos vistos");
        } else if (result == -1) {
            response = ResponseEntity.notFound().build();
        } else if (result == -2) {
            response = ResponseEntity.badRequest().body("Capitulo no encontrado");
        } 
    
        
        return response;
    }

    @PutMapping("/usuarios/{usuarioId}/SeriesPorVer")
    public ResponseEntity<?> anhadirPendiente(@PathVariable Integer usuarioId, @RequestBody Serie serie) {
        ResponseEntity<?> response = null;
        
        int result = us.anhadirPendiente(serie, usuarioId);
        if (result == 1) {
            response = ResponseEntity.ok("Serie añadida a la lista de pendientes");
        } else if (result == 0) {
            response = ResponseEntity.ok("Serie ya estaba en la lista de pendientes");
        } else if (result == -1 || result == -2) {
            response = ResponseEntity.notFound().build();
        } 
        
        return response;
    }

    @DeleteMapping("/usuarios/{usuarioId}/SeriesPorVer")
    public ResponseEntity<?> borrarSeriePendiente(@PathVariable Integer usuarioId, @RequestBody Serie serie) {
        ResponseEntity<?> response = null;
        
        int result = us.borrarSeriePendiente(serie, usuarioId);
        if (result == 0) {
            response = ResponseEntity.ok("Serie borrada de la lista de pendientes");
        } else if (result == 1) {
            response = ResponseEntity.ok("Serie no estaba en la lista de pendientes");
        } else if (result == -1 || result == -2) {
            response = ResponseEntity.notFound().build();
        }
        
        return response;
    }

    

}
