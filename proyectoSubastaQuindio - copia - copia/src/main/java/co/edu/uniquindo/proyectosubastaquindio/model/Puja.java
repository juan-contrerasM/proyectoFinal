package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstadoPuja;

import java.io.Serializable;

public class Puja implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private float ofertaInicial;
    private float oferta;
    private String nombreComprador;

    private String nombreAnunciante;
    private String nombreAnuncio;

    private String url;
    private TipoEstadoPuja tipoEstadoPuja;


    public Puja() {
    }

    public Puja(String codigo, float ofertaInicial,float oferta, String nombreComprador,String nombreAnunciante,String nombreAnuncio,String url) {
        this.codigo = codigo;
        this.ofertaInicial = ofertaInicial;
        this.oferta = oferta;
        this.nombreComprador=nombreComprador;
        this.nombreAnunciante=nombreAnunciante;
        this.nombreAnuncio=nombreAnuncio;
        this.url=url;
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

    public String getNombreAnuncio() {
        return nombreAnuncio;
    }

    public void setNombreAnuncio(String nombreAnuncio) {
        this.nombreAnuncio = nombreAnuncio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoEstadoPuja getTipoEstadoPuja() {
        return tipoEstadoPuja;
    }

    public void setTipoEstadoPuja(TipoEstadoPuja tipoEstadoPuja) {
        this.tipoEstadoPuja = tipoEstadoPuja;
    }

}

