package co.edu.uniquindo.proyectosubastaquindio.model;

import java.io.Serializable;

public class Comprador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private int cantMaxPujasAnuncio;


    public Comprador() {
    }

    public Comprador(String nombre, String apellido, String cedula, int edad, String usuario, String contrasenia, int cantMaxPujasAnuncio) {
        super(nombre, apellido, cedula, edad, usuario, contrasenia);
        this.cantMaxPujasAnuncio = cantMaxPujasAnuncio;
    }

    public Comprador(int cantMaxPujasAnuncio) {
        this.cantMaxPujasAnuncio = cantMaxPujasAnuncio;
    }

    public int getCantMaxPujasAnuncio() {
        return cantMaxPujasAnuncio;
    }

    public void setCantMaxPujasAnuncio(int cantMaxPujasAnuncio) {
        this.cantMaxPujasAnuncio = cantMaxPujasAnuncio;
    }
}
