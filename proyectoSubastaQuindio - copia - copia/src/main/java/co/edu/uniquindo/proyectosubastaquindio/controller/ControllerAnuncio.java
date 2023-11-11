package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerAnuncio {
    private  ModelfactoryController modelfactoryController;
    public  ControllerAnuncio(){
        modelfactoryController= ModelfactoryController.getInstance();

    }


    public ProductoDto buscarProducto(String nombreProducto) throws IOException {
        return  modelfactoryController.buscarProducto(nombreProducto);
    }
    public List<ProductoDto> obtenerListaProductosTxt() throws IOException {

        return modelfactoryController.ObtenerlistaProductosTxt();
    }

    public ArrayList<String> obtenerNombresProducto() throws IOException {
        return  modelfactoryController.obtenerNombreProdutos();
    }
}
