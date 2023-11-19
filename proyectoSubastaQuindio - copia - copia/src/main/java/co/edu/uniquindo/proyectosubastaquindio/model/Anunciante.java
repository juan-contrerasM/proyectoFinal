package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;

import java.io.Serializable;

public class Anunciante extends  Usuario  implements Serializable {
    private static final long serialVersionUID = 1L;
    private int cantAnunciosActivos;
    private float cantTimpoLimitado;

   public Anunciante(){

   }


    // Constructor con parámetros


    public Anunciante(String nombre, String apellido, String cedula, int edad, String usuario, String contrasenia, int cantAnunciosActivos, float cantTimpoLimitado) {
        super(nombre, apellido, cedula, edad, usuario, contrasenia, TipoUsuario.ANUNCIANTE);
        this.cantAnunciosActivos = cantAnunciosActivos;
        this.cantTimpoLimitado = cantTimpoLimitado;
    }

    // Métodos getters y setters para acceder y modificar los atributos
    public int getCantAnunciosActivos() {
        return cantAnunciosActivos;
    }

    public void setCantAnunciosActivos(int cantAnunciosActivos) {
        this.cantAnunciosActivos = cantAnunciosActivos;
    }

    public float getCantTimpoLimitado() {
        return cantTimpoLimitado;
    }

    public void setCantTimpoLimitado(float cantTimpoLimitado) {
        this.cantTimpoLimitado = cantTimpoLimitado;
    }

}

