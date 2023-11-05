package co.edu.uniquindo.proyectosubastaquindio.model;

import java.io.Serializable;

public  abstract class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected int edad;

    public Persona(){

    }

    public Persona(String nombre,String apellido,String cedula,int edad){
        this.nombre=nombre;
        this.apellido=apellido;
        this.cedula=cedula;
        this.edad=edad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
