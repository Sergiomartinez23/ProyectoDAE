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
    public Usuario getUsuario(Integer id) {
        return ur.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public List<Factura> getFacturas(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getFacturas();
    }

    @Transactional
    public List<CapsVistosSerie> getSeriesEmpezadas(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getSeriesEmpezadas();
    }

    @Transactional
    public List<CapsVistosSerie> getSeriesTerminadas(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getSeriesTerminadas();
    }

    @Transactional
    public Set<Serie> getSeriesPorVer(Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getSeriesPorVer();
    }

    @Transactional
    public Set<CapVisto> getCapsVistosSerie(Integer usuarioId, Integer serieId) {
        Set<CapVisto> capitulosVistos = new HashSet<>();
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<CapsVistosSerie> series = usuario.getSeriesEmpezadas();
        for (CapsVistosSerie serie : series) {
            if (serie.getSerie().getId() == serieId) {
                return serie.getCapitulosVistos();
            }

        }

        series = usuario.getSeriesTerminadas();
        for (CapsVistosSerie serie : series) {
            if (serie.getSerie().getId() == serieId) {
                return serie.getCapitulosVistos();
            }

        }



        return capitulosVistos;
    }

    @Transactional
    public int anhadirPendiente (Serie serie,Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        sr.findById(serie.getId()).orElseThrow(() -> new RuntimeException("Serie no encontrada" + serie.getId()));
        int ret = usuario.addSeriePorVer(serie);
        if (ret == 1) 
        {
            System.out.println("Serie añadida a la lista de pendientes: " + serie.getId());
            ur.save(usuario);
            return ret;
        } else {
            throw new RuntimeException("Serie ya añadida a la lista de pendientes o empezada");
        }
    }

    @Transactional
    public int borrarSeriePendiente (Serie serie,Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        sr.findById(serie.getId()).orElseThrow(() -> new RuntimeException("Serie no encontrada"));
        if (usuario.removeSeriePorVer(serie)) {
            ur.save(usuario);
            return 0;
        } else {
            throw new RuntimeException("Serie no encontrada en la lista de pendientes" + serie.getId());

        } 
    }

    @Transactional
    public int verCapitulo (Capitulo Capitulo, Integer usuarioId) {
        Usuario usuario = ur.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Serie serie = sr.findById(Capitulo.getSerie().getId()).orElseThrow(() -> new RuntimeException("Serie no encontrada"));
        
        usuario.verCapitulo(Capitulo);
        ur.save(usuario);
        return 0;
    }
}
