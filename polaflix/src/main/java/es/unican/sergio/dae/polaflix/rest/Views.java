package es.unican.sergio.dae.polaflix.rest;

public class Views {
    public static interface UsuarioBasic{}
    public static interface UsuarioFactura{}
    

    public static interface SerieBasic{}
    public static interface SerieDetail {}
    public static interface serieUsuario extends SerieBasic{}
    public static interface serieUsuarioDetail extends serieUsuario{}
    

    public static interface FacturaDetail{}

    public static interface CapituloBasic{}
}
