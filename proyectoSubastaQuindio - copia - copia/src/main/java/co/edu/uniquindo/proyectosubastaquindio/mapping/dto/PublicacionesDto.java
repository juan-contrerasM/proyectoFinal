package co.edu.uniquindo.proyectosubastaquindio.mapping.dto;

import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;

import java.time.LocalDate;

public record PublicacionesDto (
        AnuncianteDto anuncianteDto,
        AnuncioDto anuncioDto,
        ProductoDto productoDto
){
}
