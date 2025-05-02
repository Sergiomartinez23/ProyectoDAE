package es.unican.sergio.dae.polaflix.rest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.sergio.dae.polaflix.repository.facturaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.unican.sergio.dae.polaflix.dominio.Factura; 

@RestController
public class FacturaController {

    @Autowired
    protected facturaRepository fr;

    @GetMapping("/factura/{id}")
    @JsonView(Views.FacturaDetail.class)
    public ResponseEntity<Factura> getFactura(@PathVariable Integer id) {
        Optional<Factura> factura = fr.findById(id);
        ResponseEntity<Factura> response = null;
        if (factura.isPresent()) {
            response = ResponseEntity.ok(factura.get());
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    
    
}
