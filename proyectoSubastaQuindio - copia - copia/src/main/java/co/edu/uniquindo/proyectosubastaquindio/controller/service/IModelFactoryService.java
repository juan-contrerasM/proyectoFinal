package co.edu.uniquindo.proyectosubastaquindio.controller.service;

//import co.edu.uniquindio.banco.bancouq.mapping.dto.EmpleadoDto;
//import co.edu.uniquindio.banco.bancouq.model.Empleado;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;

import java.io.IOException;
import java.util.List;


public interface IModelFactoryService {
    public boolean agregarProducto(ProductoDto productoDto) throws IOException;
    public List<ProductoDto> ObtenerlistaProductosTxt() throws IOException;
    public void registrarAccionesSistema(String mensaje, int nivel, String accion);


}
