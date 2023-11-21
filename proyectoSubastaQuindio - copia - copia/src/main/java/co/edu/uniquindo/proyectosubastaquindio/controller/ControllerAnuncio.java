package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
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

    public AnuncianteDto obtenerAnuncianteGlobal() throws IOException {
        return  modelfactoryController.obetnerAnunciante();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public String generarFechaActual() {
        return  modelfactoryController.generarFechaActual();
    }

    public void guardarAnuncio(AnuncioDto anuncioDto) throws IOException {
        modelfactoryController.guardarAnuncios(anuncioDto);
    }

    public boolean verificarAnuncios(AnuncioDto anuncioDto) throws IOException {
        return modelfactoryController.verificarAnuncios(anuncioDto);
    }
    public boolean obtenerAutenticacion() {
        return modelfactoryController.obtenerAutenticacion();
    }


    public void contabilizarAnuncios(int i) throws IOException {
        modelfactoryController.contabilizarAnuncios(i);
    }

    public void guardarAnunciante(AnuncianteDto anuncianteDto) throws IOException {
        modelfactoryController.guardarAnuncianteCantAnuncios(anuncianteDto);
    }
}
