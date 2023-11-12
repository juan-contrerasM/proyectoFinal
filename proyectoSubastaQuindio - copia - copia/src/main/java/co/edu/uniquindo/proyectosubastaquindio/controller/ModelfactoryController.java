package co.edu.uniquindo.proyectosubastaquindio.controller;
import co.edu.uniquindo.proyectosubastaquindio.controller.service.IModelFactoryService;
import co.edu.uniquindo.proyectosubastaquindio.controller.service.ISubastaQuindioControllerService;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.mappers.SubastaQuindioMapper;
import co.edu.uniquindo.proyectosubastaquindio.model.*;
import co.edu.uniquindo.proyectosubastaquindio.utils.Persistencia;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelfactoryController implements IModelFactoryService {



//instancias
    SubastaQuindio subastaQuindio= new SubastaQuindio();
    SubastaQuindioMapper mapper = SubastaQuindioMapper.INSTANCE;

    //autenticaion

    public void guardarAutenticacion(boolean autenticacion) {
        subastaQuindio.setAutenticacion(autenticacion);
    }
    public  boolean obtenerAutenticacion(){
        return subastaQuindio.getAutenticacion();
    }

    public ArrayList<String> obtenerNombreProdutos() throws IOException {
        subastaQuindio.limpiarListaProductos();
        subastaQuindio.setListaProductos(Persistencia.cargarProductos());
        Persistencia.guardarProdcutos(subastaQuindio.getListaProductos());
        return subastaQuindio.obtenerNombresProductos();
    }

    public void guardarNombreP(String nombre) {
        subastaQuindio.guardarNombre(nombre);
    }

    public AnuncianteDto obetnerAnunciante() throws IOException {
        return mapper.anuncianteToAnuncianteDto(subastaQuindio.getAnuncianteGlobal());
    }
    public CompradorDto obetnerComprador() throws IOException {
        return mapper.compradorToCompradorDto(subastaQuindio.getCompradorGlobal());
    }

    public void guardarAnuncianteGlobal(String usuario) throws IOException {
        subastaQuindio.setListaAnunciantes(Persistencia.cargarAnunciantes());
        Persistencia.guardarAnunciastes(subastaQuindio.getListaAnunciantes());
        subastaQuindio.setAnuncianteGlobal(subastaQuindio.obetnerAnunciante(usuario));
    }
    public  void guardarCompradorGlobal(String usuario) throws IOException {
        subastaQuindio.setListaCompradores(Persistencia.cargarCompradores());
        Persistencia.guardarCompradores(subastaQuindio.getListaCompradores());
        subastaQuindio.setCompradorGlobal(subastaQuindio.obtenerComprador(usuario));
    }

    public String generarFechaActual() {
       return Persistencia.generarFechaActual();
    }

    public boolean verificarAnuncios(AnuncioDto anuncioDto) throws IOException {
        Anuncio anuncio= mapper.anuncioDtoToAnuncio(anuncioDto);
        subastaQuindio.setListaAnuncios(Persistencia.cargarAnuncios());
        Persistencia.guardarAnuncios(subastaQuindio.getListaAnuncios());
        return subastaQuindio.verificarAnuncioCreado(anuncio);
    }


//------------------------------singlentton clase secreta------------------------------------

    private static class SingletonHolder implements ISubastaQuindioControllerService {
        private final static ModelfactoryController eINSTANCE = new ModelfactoryController();
    }
    public static  ModelfactoryController getInstance(){
        return SingletonHolder.eINSTANCE;
    }

    // contrutor
    public ModelfactoryController() {

    }

 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++LOG++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {

        Persistencia.guardaRegistroLog(mensaje, nivel, accion);

    }
 //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ +++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++SECCION PRODUCCTO++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//------------------------------------------agregar prodcuto
    //se limpia la lista de subasta quindio, luego la que recibimos por parametros, se la seteamos
    @Override
    public boolean agregarProducto(ProductoDto productoDto) throws IOException {
           subastaQuindio.limpiarListaProductos();
           subastaQuindio.setListaProductos( Persistencia.cargarProductos());
           Producto producto = mapper.productoDtoToProducto(productoDto);
           subastaQuindio.guardarProducto(producto);
           guardarResourceXML();
           guardarResourceBinario();
            //guardar en archico de txt
            Persistencia.guardarProdcutos(subastaQuindio.getListaProductos());
            return true;
    }

    //------------------------------------------actualizar producto
    public List<ProductoDto> actualizarProducto(ProductoDto productoDto) throws IOException {
       subastaQuindio.limpiarListaProductos();
       subastaQuindio.setListaProductos(Persistencia.cargarProductos());

       Producto producto= mapper.productoDtoToProducto(productoDto);
       subastaQuindio.actualizarProducto(producto);
       Persistencia.guardarProdcutos(subastaQuindio.getListaProductos());

       return   mapper.getProductoDto(subastaQuindio.getListaProductos());
    }
    //--------------------------------------------eliminar producto-------------------------------
    public List<ProductoDto> eliminarProducto(ProductoDto productoDto) throws IOException {
        subastaQuindio.limpiarListaProductos();
        subastaQuindio.setListaProductos(Persistencia.cargarProductos());
        Producto producto=mapper.productoDtoToProducto(productoDto);
        subastaQuindio.eliminarProdcuto(producto);
        Persistencia.guardarProdcutos(subastaQuindio.getListaProductos());
        return mapper.getProductoDto(subastaQuindio.getListaProductos());
    }
    //----------------------------------------verificar producto-------------------------
    public boolean verificarProducto(ProductoDto productoDto) throws IOException {
        Producto producto = mapper.productoDtoToProducto(productoDto);
        subastaQuindio.setListaProductos(Persistencia.cargarProductos());
        return subastaQuindio.verificarProductoCreado(producto);
    }

    public ProductoDto buscarProducto(String nombreProducto) throws IOException {
        subastaQuindio.limpiarListaProductos();
        subastaQuindio.setListaProductos(Persistencia.cargarProductos());
        Persistencia.guardarProdcutos(subastaQuindio.getListaProductos());
       return mapper.productoToProductoDto(subastaQuindio.buscarProducto(nombreProducto));

    }







//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++OBTENER LISTAS INIZIALIZABLE++++++++++++++++++++++++++++++++++++++++++
    public List<ProductoDto> ObtenerlistaProductosTxt() throws IOException {
        ArrayList<Producto>productosTxt=Persistencia.cargarProductos();

        return  mapper.getProductoDto(productosTxt);

    }
    public List<AnuncianteDto>ObtenerListasAnunciantesTxt() throws IOException {
        ArrayList<Anunciante>listaAnunciantestxt=Persistencia.cargarAnunciantes();
        return  mapper.getAnuncianteDto(listaAnunciantestxt);
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//+++++++++++++++++++++++++++++++++++++++++++++++SECCION ANUNCIANTE++++++++++++++++++++++++++++++++++++++++++++++++

   //-------------------guardarAnunciante
    public void guardarAnunciante(AnuncianteDto anuncianteDto) throws IOException {
        subastaQuindio.limpiarListaAnunciantes();
        subastaQuindio.setListaAnunciantes(Persistencia.cargarAnunciantes());
        Anunciante anunciante = mapper.anuncianteDtoToAnunciante(anuncianteDto);
        subastaQuindio.guardarAnunciante(anunciante);
        Persistencia.guardarAnunciastes(subastaQuindio.getListaAnunciantes());
    }
    //-------------------------verificarInicioAnunciante
    public boolean verificarInicioSesionAnunciante(String usuario, String clave) throws IOException {
        subastaQuindio.setListaAnunciantes(Persistencia.cargarAnunciantes());
        return subastaQuindio.verificarinicioSesionAnunciante(usuario, clave);
    }
    public boolean verificarAnuncianteCreado(AnuncianteDto anuncianteDto) throws IOException {
        subastaQuindio.setListaAnunciantes(Persistencia.cargarAnunciantes());
        Anunciante anunciante= mapper.anuncianteDtoToAnunciante(anuncianteDto);
        Persistencia.guardarAnunciastes(subastaQuindio.getListaAnunciantes());
        return  subastaQuindio.verificarAnuncianteCreado(anunciante);
    }
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 //+++++++++++++++++++++++++++++++++++++++++++++SECCION COMPRADOR++++++++++++++++++++++++++++++++++++++++++++++++

    //-------------------guardarComprador
    public void guardarcomprador(CompradorDto compradorDtoDto) throws IOException {
        subastaQuindio.limpiarCompradores();
        subastaQuindio.setListaCompradores(Persistencia.cargarCompradores());
        Comprador comprador =mapper.compradorDtoToComprador(compradorDtoDto);
        subastaQuindio.guardarComprador(comprador);
        Persistencia.guardarCompradores(subastaQuindio.getListaCompradores());
    }
    //-------------------------verificarInicioComprador
    public boolean verificarInicioComprador(String usuario, String clave) throws IOException {
        subastaQuindio.setListaCompradores(Persistencia.cargarCompradores());
        return subastaQuindio.verificarinicioSesionCompradores(usuario, clave);
    }


    public boolean verificarCompradorCreado(CompradorDto compradorDto) throws IOException {
        subastaQuindio.setListaCompradores(Persistencia.cargarCompradores());
        Comprador comprador= mapper.compradorDtoToComprador(compradorDto);
        Persistencia.guardarCompradores(subastaQuindio.getListaCompradores());
        return  subastaQuindio.verificarCompradorCreado(comprador);
    }







    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++GUARDADO EN ARCHIVOS XML Y BINARIO+++++++++++++++++++++++++++++++++++++++++++
    //Xml
    private void guardarResourceXML() {
        Persistencia.guardarRecursoBancoXML(getSubastaQuindio(subastaQuindio));
    }

   //Binario
    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(subastaQuindio);
    }
    private void cargarResourceBinario() {
        subastaQuindio = Persistencia.cargarRecursoBancoBinario();
    }


    //+++++++++++++++++++++++++++++++++++GETTER Y SETTERS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SubastaQuindio getSubastaQuindio(SubastaQuindio subastaQuindio) {
        return this.subastaQuindio;
    }

    public void setSubastaQuindio(SubastaQuindio subastaQuindio) {
        this.subastaQuindio = subastaQuindio;
    }
    //+++++++++++++++++++++++++++++++++++Anuncio ++++++++++++++++++++++++++++++++++++++++
    public void guardarAnuncios(AnuncioDto anuncioDto) throws IOException {
        subastaQuindio.setListaAnuncios(Persistencia.cargarAnuncios());
        Persistencia.guardarAnuncios(subastaQuindio.getListaAnuncios());
      Anuncio anuncio= mapper.anuncioDtoToAnuncio(anuncioDto);
        subastaQuindio.guardarAnuncios(anuncio);
    }
}
