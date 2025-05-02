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

    public enum Pago {
        porVisualizacion, mensual
    }
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
    @OneToMany(cascade = CascadeType.ALL) private List<CapsVistosSerie> series;
    @OneToMany private List<Serie> seriesPorVer;
    public Usuario() {
    }
    public Usuario(Pago modoDePago, String nombre, String iban, String contrasenha) {
        this.modoDePago = modoDePago;
        this.nombre = nombre;
        this.iban = iban;
        this.contrasenha = contrasenha;
        this.facturas = new ArrayList<>();
        this.series = new ArrayList<>();
        this.seriesPorVer = new ArrayList<>();
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
            if (factura.getMonth() == mes && factura.getAnho() == anho) {
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


    public List<Serie> getSeriesPorVer() {
        return seriesPorVer;
    }
    public int addSeriePorVer(Serie serie) {
        if (seriesPorVer.contains(serie)) {
            return 0;
        }
        seriesPorVer.add(serie);
        return 1;
    }
    public boolean removeSeriePorVer(Serie serie) {

        for (Serie seriePorVer : seriesPorVer) {
            if (seriePorVer.getId() == serie.getId()) {
                seriesPorVer.remove(seriePorVer);
                return true;
            }
        }    
        return false;
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
            if (capsVistosSerie.getSerie().getId()==serie.getId()) {  
                System.out.println(serie.getId()); 
                List <CapVisto> capitulosVistos = capsVistosSerie.getCapitulosVistos();
                CapVisto cap;
                for (int i =0; i<capitulosVistos.size();i++) {
                    cap = capitulosVistos.get(i);
                    if (cap.getNumero() == numero && cap.getTemporada() == temporada) {
                        System.out.println("Capitulo ya visto: " + capitulo.getTitulo());
                        return;
                    }
                }

                int ret = capsVistosSerie.addCapitulo(capVisto);
                if (ret == -1) {
                    System.out.println("Capitulo no valido: " + capitulo.getTitulo());
                    return;
                }
                encontrado = true;
            }
        }
        
        for (Serie seriePorVer : seriesPorVer) {
            if (seriePorVer.getId() == serie.getId()) {
                seriesPorVer.remove(serie);
                break;
            }
        }

        if (!encontrado) {
            CapsVistosSerie capsVistosSerie = new CapsVistosSerie(serie);
            capsVistosSerie.addCapitulo(capVisto);
            series.add(capsVistosSerie);
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