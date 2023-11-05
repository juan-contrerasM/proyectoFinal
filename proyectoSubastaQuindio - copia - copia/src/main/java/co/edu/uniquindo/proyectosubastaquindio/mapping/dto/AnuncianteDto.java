package co.edu.uniquindo.proyectosubastaquindio.mapping.dto;

public record AnuncianteDto  (


        //Se declaran las variables

        String nombre,
        String apellido,
        String cedula,
        int edad,
        String usuario,
        String contrasenia,
        int cantAnunciosActivos,
        float cantTimpoLimitado

) {
}
