package es.unican.sergio.dae.polaflix.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import es.unican.sergio.dae.polaflix.rest.Views;

@Entity
public class Factura{
    @JsonView({Views.UsuarioFactura.class, Views.FacturaDetail.class})
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id;
    @JsonView({Views.UsuarioFactura.class, Views.FacturaDetail.class})
    private int mes;
    @JsonView({Views.UsuarioFactura.class, Views.FacturaDetail.class})
    private int anho;
    @JsonView({Views.UsuarioFactura.class, Views.FacturaDetail.class})
    private float importe;
    @JsonView(Views.FacturaDetail.class)
    @OneToMany(cascade = CascadeType.ALL) 
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;
    
    public Factura() {
    }
    public Factura(int importe) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        mes = localDate.getMonthValue();
        anho = localDate.getYear();
        this.importe = importe;        
        items = new ArrayList<>();
        // this.usuario = usuario;
    }
    public int getId() {
        return id;
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

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.anho == ((Factura) obj).getAnho() && this.mes == ((Factura) obj).getMonth()) {
            return true;
        } else {
            return false;
        }
    }

    @Override 
    public int hashCode() {
        return this.id;
    }
}