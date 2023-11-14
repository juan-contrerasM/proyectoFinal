package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;

import java.io.Serializable;
import java.time.LocalDate;

public class Anuncio implements Serializable {
    private static final long serialVersionUID = 1L;
//agregar fehca publicacion, fecha finalizacion y valor inicial
    private String nombre;

     private String codigo;

    private   TipoEstado estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private float valorInicial;
    private String nombreProdcuto;
    private String nombreAnunciante;

    public Anuncio() {
    }

    public Anuncio(String nombre, String codigo, TipoEstado estado, LocalDate fechaInicio, LocalDate fechaFinalizacion, float valorInicial, String nombreProdcuto, String  nombreAnunciante) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
        this.fechaInicio=fechaInicio;
        this.fechaFinalizacion= fechaFinalizacion;
        this.valorInicial= valorInicial;
        this.nombreProdcuto=nombreProdcuto;
        this.nombreAnunciante =nombreAnunciante
        ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public float getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(float valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getProducto() {
        return nombreProdcuto;
    }

    public void setProducto(String producto) {
        this.nombreProdcuto = producto;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }
}
