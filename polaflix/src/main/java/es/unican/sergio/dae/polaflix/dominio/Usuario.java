package es.unican.sergio.dae.polaflix.dominio;


import java.util.*;

public class Usuario {

    public enum Pago {
        porVisualizacion, mensual
    }
    private Pago modoDePago;
    private String nombre;
    private String iban;
    private String contraseña;
    private List<Factura> facturas;
    private List<CapsVistosSerie> series;
    private List<Serie> seriesPorVer;

    public Usuario(Pago modoDePago, String nombre, String iban, String contraseña) {
        this.modoDePago = modoDePago;
        this.nombre = nombre;
        this.iban = iban;
        this.contraseña = contraseña;
        this.facturas = new ArrayList<>();
        this.series = new ArrayList<>();
        this.seriesPorVer = new ArrayList<>();
        addFactura();
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Factura getFactura(int mes, int año) {
        for (Factura factura : facturas) {
            if (factura.getMonth() == mes && factura.getAño() == año) {
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
        Factura factura = new Factura(this, importe);
        this.facturas.add(factura);
    }


    public List<Serie> getSeriesPorVer() {
        return seriesPorVer;
    }
    public void addSeriePorVer(Serie serie) {
        seriesPorVer.add(serie);
    }
    public void removeSeriePorVer(Serie serie) {
        seriesPorVer.remove(serie);
    }
    public List<CapsVistosSerie> getSeriesTerminadas() {
        List<CapsVistosSerie> seriesTerminadas = new ArrayList<>();
        for (CapsVistosSerie caps : series) {
            Serie serie = caps.getSerie();
            
                if (caps.numUltimoCapitulo() == serie.getLastCapitulo()) {
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
                int ret = capsVistosSerie.addCapitulo(capVisto);
                if (ret == -1) {
                    return;
                }
                encontrado = true;
            }
        }
        System.out.println("prueba");
        
        for (Serie seriePorVer : seriesPorVer) {
            if (seriePorVer.equals(serie)) {
                seriesPorVer.remove(serie);
                break;
            }
        }

        if (!encontrado) {
            CapsVistosSerie capsVistosSerie = new CapsVistosSerie(serie);
            capsVistosSerie.addCapitulo(capVisto);
            series.add(capsVistosSerie);
        }

        if (modoDePago == Pago.porVisualizacion) {
            factura.addCapitulo(capitulo.getPrecio(), temporada, numero, serie);
        }
        else {
            factura.addCapitulo(0, temporada, numero, serie);
        }
    }
    public static void main(String[] args) {
        Usuario usuario = new Usuario(Usuario.Pago.porVisualizacion, "John Doe", "ES1234567890123456789012", "password123");

        Serie serie1 = new Serie("Serie 1", "desc", Serie.Tipo.gold, 3);
        Serie serie2 = new Serie("Serie 2", "desc", Serie.Tipo.gold, 2);

        usuario.addSeriePorVer(serie1);
        usuario.addSeriePorVer(serie2);

        Capitulo capitulo1 = new Capitulo(1, "cap1", "desc", "url", 1, serie1);
        Capitulo capitulo2 = new Capitulo(2, "cap2", "desc", "url", 1, serie1);
        Capitulo capitulo3 = new Capitulo(1, "cap1", "desc", "url", 2, serie2);

        usuario.verCapitulo(capitulo1);
        usuario.verCapitulo(capitulo2);
        usuario.verCapitulo(capitulo3);
        System.out.println("Facturas: " + usuario.facturas.size());
        System.out.println("Series por ver: " + usuario.getSeriesPorVer().size());
        System.out.println("Series empezadas: " + usuario.getSeriesEmpezadas().size());
        System.out.println("Series terminadas: " + usuario.getSeriesTerminadas().size());
        System.out.println("Factura 1: " + usuario.getFactura(3, 2025).getImporte());
    }
}