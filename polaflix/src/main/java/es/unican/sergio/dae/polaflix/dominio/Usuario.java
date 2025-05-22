package es.unican.sergio.dae.polaflix.dominio;


import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.*;;
@Entity
public class Usuario {
    @JsonView(Views.UsuarioBasic.class)
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;

   
    @JsonView(Views.UsuarioBasic.class)
    private Pago modoDePago;
    @JsonView(Views.UsuarioBasic.class)
    private String nombre;
    @JsonView(Views.UsuarioBasic.class)
    private String iban;
    @JsonView(Views.UsuarioBasic.class)
    private String contrasenha;
    

    @JsonView(Views.UsuarioFactura.class)
    @OneToMany(cascade = CascadeType.PERSIST) private List<Factura> facturas;
    @OneToMany(cascade = CascadeType.ALL) private Set<CapsVistosSerie> series;
    @OneToMany private Set<Serie> seriesPorVer;
    public Usuario() {
    }
    public Usuario(Pago modoDePago, String nombre, String iban, String contrasenha) {
        this.modoDePago = modoDePago;
        this.nombre = nombre;
        this.iban = iban;
        this.contrasenha = contrasenha;
        this.facturas = new ArrayList<>();
        this.series = new HashSet<>();
        this.seriesPorVer = new HashSet<>();
        addFactura();
    }
    public int getId() {
        return id;
    }
    public Pago getModoDePago() {
        return modoDePago;
    }

    public void setModoDePago(Pago modoDePago) {
        this.modoDePago = modoDePago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Factura getFactura(int mes, int anho) {
        for (Factura factura : facturas) {
            if (factura.equals(factura)) {
                return factura;
            }
        }
        return null;
    }

    public Factura ultimaFactura() {
        return facturas.get(facturas.size() - 1);
    }
    
    public void addFactura() {
        int importe = 0;
        if (modoDePago == Pago.mensual) {
            importe = 20;
        }
        Factura factura = new Factura(importe);
        this.facturas.add(factura);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }


    public Set<Serie> getSeriesPorVer() {
        return seriesPorVer;
    }
    public int addSeriePorVer(Serie serie) {
        if (seriesPorVer.contains(serie)) {
            System.out.println("Serie ya vista: " + serie.getTitulo());
            return 0;
        }
        for (CapsVistosSerie caps : series) {
            if (caps.getSerie().getId() == serie.getId()) {
                System.out.println("Serie ya vista: " + serie.getTitulo());
                return 0;
            }
            
        }
        seriesPorVer.add(serie);
        return 1;
    }
    public boolean removeSeriePorVer(Serie serie) {
        return seriesPorVer.remove(serie);
           
    }
    public List<CapsVistosSerie> getSeriesTerminadas() {
        List<CapsVistosSerie> seriesTerminadas = new ArrayList<>();
        for (CapsVistosSerie caps : series) {
            Serie serie = caps.getSerie();
            
                if (caps.numUltimoCapitulo() == serie.getLastCapitulo() && caps.numUltimaTemporada() == serie.getTemporadas()) {
                    seriesTerminadas.add(caps);
                }
            
        }
        return seriesTerminadas;
    }


    public List<CapsVistosSerie> getSeriesEmpezadas() {
        List<CapsVistosSerie> seriesEmpezadas = new ArrayList<>();
        for (CapsVistosSerie caps : series) {
            //The not null below is a fix in case there is no last chapter in the last season
            Serie serie = caps.getSerie();
            
                
            if (caps.numUltimoCapitulo() != serie.getLastCapitulo()) {
                seriesEmpezadas.add(caps);
            } else if (caps.numUltimaTemporada() != serie.getTemporadas()) {
                seriesEmpezadas.add(caps);
            }
            
        }
        return seriesEmpezadas;
    }

    
    public void verCapitulo(Capitulo capitulo) {
        int numero = capitulo.getNumero();  
        int temporada = capitulo.getTemporada();    
        Serie serie = capitulo.getSerie();
        boolean encontrado = false; 
        Factura factura = ultimaFactura();
        CapVisto capVisto = new CapVisto(numero, temporada);
        
        
        
        
        for (CapsVistosSerie capsVistosSerie : series) {
            if (capsVistosSerie.getSerie().equals(serie)) {  
                System.out.println("ID:" + serie.getId()); 
                
                if (capsVistosSerie.getCapitulosVistos().contains(capVisto)) {
                    System.out.println("Capitulo ya visto: " + capitulo.getTitulo());
                    return;
                }

                int ret = capsVistosSerie.addCapitulo(capVisto);
                if (ret == -1) {
                    System.out.println("Capitulo no valido: " + capitulo.getTitulo());
                    return;
                }
                encontrado = true;
            }
        }
        if (!encontrado) {
            CapsVistosSerie capsVistosSerie = new CapsVistosSerie(serie);
            capsVistosSerie.addCapitulo(capVisto);
            series.add(capsVistosSerie);
        }
        
        if (seriesPorVer.contains(serie)) {
            seriesPorVer.remove(serie);
        }

        System.out.println("Capitulo visto: " + capitulo.getTitulo());


        if (modoDePago == Pago.porVisualizacion) {
            System.out.println("Precio: " + capitulo.getPrecio());
            factura.addCapitulo(capitulo.getPrecio(), temporada, numero, serie);
        }
        else {
            factura.addCapitulo(0, temporada, numero, serie);
        }
    }
  
}