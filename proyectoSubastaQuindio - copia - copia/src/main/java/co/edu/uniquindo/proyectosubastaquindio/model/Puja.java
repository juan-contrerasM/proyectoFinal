package co.edu.uniquindo.proyectosubastaquindio.model;

import java.io.Serializable;

public class Puja implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private int ofertaInicial;
    private String oferta;


    public Puja() {
    }

    public Puja(String codigo, int ofertaInicial, String oferta) {
        this.codigo = codigo;
        this.ofertaInicial = ofertaInicial;
        this.oferta = oferta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getOfertaInicial() {
        return ofertaInicial;
    }

    public void setOfertaInicial(int ofertaInicial) {
        this.ofertaInicial = ofertaInicial;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }
}
