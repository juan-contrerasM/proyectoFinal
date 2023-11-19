package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;
import co.edu.uniquindo.proyectosubastaquindio.model.Producto;

import java.io.Serializable;
import java.time.LocalDate;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreProducto;
    private String descripcion;


    private tipoArticulo tipo_Articulo;

    private String urlFoto;


    public  Producto(){

    }

    public Producto(String nombreProducto, String descripcion,tipoArticulo tipo_Articulo, String urlFoto) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.tipo_Articulo = tipo_Articulo;
        this.urlFoto=urlFoto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombre) {
        this.nombreProducto = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public tipoArticulo getTipo_Articulo() {
        return tipo_Articulo;
    }

    public void setTipo_Articulo(tipoArticulo tipoArticulo) {
        this.tipo_Articulo = tipoArticulo;
    }

}

