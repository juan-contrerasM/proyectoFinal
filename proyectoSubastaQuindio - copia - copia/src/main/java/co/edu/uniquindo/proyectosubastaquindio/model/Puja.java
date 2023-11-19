package co.edu.uniquindo.proyectosubastaquindio.model;

import java.io.Serializable;

public class Puja implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private float ofertaInicial;
    private float oferta;
    private String nombreComprador;

    private String nombreAnunciante;


    public Puja() {
    }

    public Puja(String codigo, float ofertaInicial,float oferta, String nombreComprador,String nombreAnunciante) {
        this.codigo = codigo;
        this.ofertaInicial = ofertaInicial;
        this.oferta = oferta;
        this.nombreComprador=nombreComprador;
        this.nombreAnunciante=nombreAnunciante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getOfertaInicial() {
        return ofertaInicial;
    }

    public void setOfertaInicial(float ofertaInicial) {
        this.ofertaInicial = ofertaInicial;
    }

    public float getOferta() {
        return oferta;
    }

    public void setOferta(float oferta) {
        this.oferta = oferta;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }
}
