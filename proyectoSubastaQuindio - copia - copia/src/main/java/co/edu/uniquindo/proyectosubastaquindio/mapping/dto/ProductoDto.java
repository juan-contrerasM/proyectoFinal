package co.edu.uniquindo.proyectosubastaquindio.mapping.dto;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;

import java.time.LocalDate;

public record ProductoDto (

        String nombreProducto,
        String descripcion,
        tipoArticulo tipo_Articulo,
        String urlFoto


){

}
