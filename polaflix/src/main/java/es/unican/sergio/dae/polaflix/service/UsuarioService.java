package es.unican.sergio.dae.polaflix.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.unican.sergio.dae.polaflix.dominio.CapVisto;
import es.unican.sergio.dae.polaflix.dominio.Capitulo;
import es.unican.sergio.dae.polaflix.dominio.CapsVistosSerie;
import es.unican.sergio.dae.polaflix.dominio.Usuario;
import es.unican.sergio.dae.polaflix.repository.usuarioRepository;
import jakarta.transaction.Transactional;
import es.unican.sergio.dae.polaflix.dominio.Factura;
import es.unican.sergio.dae.polaflix.dominio.Serie;
import es.unican.sergio.dae.polaflix.repository.serieRepository;
import es.unican.sergio.dae.polaflix.rest.SeriesVistasUsuario;
import es.unican.sergio.dae.polaflix.rest.VistaSerieUsuario;
@Service
public class UsuarioService {

    public UsuarioService() {
        // Constructor
    }
    @Autowired 
    protected usuarioRepository ur;

    @Autowired
    protected serieRepository sr;
    
    @Transactional
    public VistaSerieUsuario getSerieUsuario(Integer usuarioId, Integer serieId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);
        Serie serie = sr.findById(serieId).orElse(null);
        if (usuario == null || serie == null) {
            return null;
        }
        CapsVistosSerie capsVistosSerie = null;
        capsVistosSerie = usuario.getSerieVistas(serie);
        
        return new VistaSerieUsuario(serie, capsVistosSerie);
    }

    
    @Transactional
    public SeriesVistasUsuario getSeriesUsuario(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);

        if (usuario == null) {
            return null;
        }
        
        return new SeriesVistasUsuario(usuario.getSeriesEmpezadas(), usuario.getSeriesTerminadas(), usuario.getSeriesPorVer());
    }
    @Transactional
    public List<Factura> getFacturas(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return null;
        }
        return usuario.getFacturas();
    }

    @Transactional
    public int anhadirPendiente (Serie serie,Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return -1; // Usuario no encontrado
        }

        if (sr.findById(serie.getId()).orElse(null) == null) {
            return -2; // Serie no encontrada
        }
        int ret = usuario.addSeriePorVer(serie);
        if (ret == 1) 
        {
            System.out.println("Serie añadida a la lista de pendientes: " + serie.getId());
            ur.save(usuario);
            return ret;
        } else {
           return 0;
        }
    }

    @Transactional
    public int borrarSeriePendiente (Serie serie,Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);
        

        if (usuario == null) {
            return -1; // Usuario no encontrado
        }
        if (sr.findById(serie.getId()).orElse(null) == null) {
            return -2; // Serie no encontrada
        }
        if (usuario.removeSeriePorVer(serie)) {
            ur.save(usuario);
            return 0;
        } else {
            return 1; // Serie no estaba en la lista de pendientes

        } 
    }

    @Transactional
    public int verCapitulo (Integer Capitulo, Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElse(null);
        Capitulo capitulo = sr.findCapituloById(Capitulo).orElse(null);
        if (usuario == null ) {
            return -1;
        }
        if (capitulo == null) {
            return -2; // Capitulo no encontrado
        }
        usuario.verCapitulo(capitulo);
        ur.save(usuario);
        return 0;
    }

    
}
