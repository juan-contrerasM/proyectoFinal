package co.edu.uniquindo.proyectosubastaquindio.mapping.dto;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;

import java.time.LocalDate;

public record AnuncioDto (
        //Se declaran las variables
        String nombre,
        String codigo,
       TipoEstado estado,
        LocalDate fechaInicio,
        LocalDate fechaFinalizacion,
        float valorInicial,
        String nombreProdcuto,
        String nombreAnunciante,
        String url

){
}
