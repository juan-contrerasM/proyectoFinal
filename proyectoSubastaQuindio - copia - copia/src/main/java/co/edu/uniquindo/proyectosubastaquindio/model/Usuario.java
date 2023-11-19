package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String usuario;
    private String contrasenia;
    private TipoUsuario tipoUsuario;

    public Usuario(){

    }

    public Usuario(String nombre, String apellido, String cedula, int edad, String usuario, String contrasenia, TipoUsuario tipoUsuario) {
        super(nombre, apellido, cedula, edad);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoUsuario=tipoUsuario;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
