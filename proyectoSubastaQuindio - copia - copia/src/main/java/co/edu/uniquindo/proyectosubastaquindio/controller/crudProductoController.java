package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class crudProductoController  {


    /*
    falta organizar codigo como las demas clases por seeciones
     */
    static ModelfactoryController modelfactoryController = new ModelfactoryController();
    public  crudProductoController(){
        modelfactoryController=ModelfactoryController.getInstance();
    }

    //-----------------------------------------VARABLES GLOBALES---------------------------------------------------------------

     ArrayList<Producto> listaProductos = new ArrayList<>();

    //--------------------------------------------------GUARDAR PRODUCTOS----------------------------------------
     public boolean guardarProducto(ProductoDto productoDto) throws IOException {
       return   modelfactoryController.agregarProducto(productoDto);


    }


    //-------------------------------------VERIFICAR PRODCUTO CREADO----------------------------------------
     public boolean verificarProductoCreado(ProductoDto productoDto) throws IOException {

        return modelfactoryController.verificarProducto(productoDto);

    }

    //------------------------------------ACTUALIZAR PRODUCTO-----------------------------------------------
     public List<ProductoDto> actualizarProducto(ProductoDto productoDto) throws IOException {
        return modelfactoryController.actualizarProducto(productoDto);
    }

    //----------------------------------ELIMINAR PRODUCTO-----------------------------------------------------
     public List<ProductoDto> eliminarProdcuto(ProductoDto productoDto) throws IOException {

        return modelfactoryController.eliminarProducto(productoDto);


    }

    //-----------------------------OBTENER INFORMACION DE PRODCUTOS DESDE EL TXT---------------------------------------------------
     public List<ProductoDto> obtenerListaProductosTxt() throws IOException {

        return modelfactoryController.ObtenerlistaProductosTxt();
    }


    //++++++++++++++++++++++++++++++++LOG++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }
    //+++++++++++++++++++++++++++++++++++++++anunciante++++++++++++++++++++++++++++++++++++++++++++++++++
    public boolean verificarInicioSesionAnunciante(String usuario, String clave) throws IOException {
         return modelfactoryController.verificarInicioSesionAnunciante( usuario, clave);
    }


    public void guardarAnunciante(AnuncianteDto anuncianteDto) throws IOException {
         modelfactoryController.guardarAnunciante(anuncianteDto);
    }

    public boolean verificarInicioSesionComprador(String usuario, String clave) throws IOException {
         return modelfactoryController.verificarInicioComprador(usuario, clave);
    }
    //++++++++++++++++++++++COMPRADOR++++++++++++++++++++++++++++++++++++

    public void guardarComprador(CompradorDto compradorDto) throws IOException {
         modelfactoryController.guardarcomprador(compradorDto);
    }

    public boolean verificarAnuncianteCreado(AnuncianteDto anuncianteDto) throws IOException {
         return  modelfactoryController.verificarAnuncianteCreado(anuncianteDto);
    }

    public boolean verificarCompradorCreado(CompradorDto compradorDto) throws IOException {
         return modelfactoryController.verificarCompradorCreado(compradorDto);
    }

    public ProductoDto buscarProducto(String nombreProducto) throws IOException {
       return  modelfactoryController.buscarProducto(nombreProducto);
    }
}
