package es.unican.sergio.dae.polaflix.dominio;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
public class Factura {

    // private Usuario usuario;
    private int month;
    private int año;
    private float importe;
    private List<ItemFactura> items;
    
    public Factura(Usuario usuario, int importe) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        month = localDate.getMonthValue();
        año = localDate.getYear();
        this.importe = importe;        
        items = new ArrayList<>();
        // this.usuario = usuario;
    }

    public int getMonth() {
        return month;
    }
    public int getAño() {
        return año;
    }
    public float getImporte() {
        return importe;
    }
    public void addCapitulo(float precio, int temporada, int numero, Serie serie) {
        items.add(new ItemFactura(precio, temporada, numero, serie));
        this.importe += precio;
    }
    public void setImporte(float importe) {
        this.importe = importe;
    }

}