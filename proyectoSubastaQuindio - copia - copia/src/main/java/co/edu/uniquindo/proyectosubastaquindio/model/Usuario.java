package co.edu.uniquindo.proyectosubastaquindio.model;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String usuario;
    private String contrasenia;

    public Usuario(){

    }

    public Usuario(String nombre, String apellido, String cedula, int edad, String usuario, String contrasenia) {
        super(nombre, apellido, cedula, edad);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
