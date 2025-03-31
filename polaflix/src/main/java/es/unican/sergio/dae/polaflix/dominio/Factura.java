package es.unican.sergio.dae.polaflix.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


import jakarta.persistence.*;
@Entity
public class Factura{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)  private int id;
    // private Usuario usuario;
    private int mes;
    private int anho;
    private float importe;
    @OneToMany(cascade = CascadeType.REMOVE) private List<ItemFactura> items;
    
    public Factura(int importe) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        mes = localDate.getMonthValue();
        anho = localDate.getYear();
        this.importe = importe;        
        items = new ArrayList<>();
        // this.usuario = usuario;
    }

    public int getMonth() {
        return mes;
    }
    public int getAnho() {
        return anho;
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