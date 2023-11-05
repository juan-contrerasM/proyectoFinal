package co.edu.uniquindo.proyectosubastaquindio.controller.service;

import co.edu.uniquindo.proyectosubastaquindio.model.Producto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IcrudProdcutoController {

     public ArrayList<Producto> guardarProducto(String nombreProdcuto, String descripcion, String nombreAnunciante, LocalDate fechaPublicacion, LocalDate fechaFinalizacion, float valorInicial, tipoArticulo tipo_Articulo, String url) throws IOException;

     public boolean verificarProductoCreado(String nombre) throws IOException ;

    public ArrayList<Producto> actualizarProducto(Producto p1) throws IOException;

    public ArrayList<Producto> eliminarProdcuto(Producto p1) throws IOException;

    public ArrayList<Producto> obtenerListaProductosTxt() throws IOException;

    void registrarAcciones(String mensaje, int nivel, String accion);
}

