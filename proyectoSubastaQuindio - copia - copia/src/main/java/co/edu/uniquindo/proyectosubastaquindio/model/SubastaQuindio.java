package co.edu.uniquindo.proyectosubastaquindio.model;

import co.edu.uniquindo.proyectosubastaquindio.controllersView.ControllerInicioView;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SubastaQuindio implements Serializable {

    //lista publicacionesDto


    public  SubastaQuindio(){

 }
    private static final long serialVersionUID = 1L;
    private boolean autenticacion=false;
    private  Anunciante anuncianteGlobal;
    private Comprador compradorGlobal;
    private ArrayList<Anuncio>listaAnuncios;


 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 //+++++++++++++++++++++++++++++++++++++++++++++++++++SECCION PRODUCTO+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //--------------------------listas productos--------


    ArrayList<Producto> listaProductos=new ArrayList<Producto>();

    //---------------limpiarListaProducto-----
    public void limpiarListaProductos(){
        listaProductos.clear();
    }


    //--------------------------------------------------guardar productos
    public ArrayList<Producto> guardarProducto(Producto producto) throws IOException {
        listaProductos.add(producto);
        //se le vuelve a llamar el model factory para guardar la lista de productos en el txt
        return listaProductos;

    }

    //-------------------------------------verificar producto creado
    public boolean verificarProductoCreado(Producto producto) throws IOException {

      boolean confirmacion=true;

        // se busca a informacion con la lista obtenida
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getNombreProducto().equals(producto.getNombreProducto())) {
                confirmacion=false;
                break;
            }
        }
        return confirmacion;
    }

    //------------------------------------actualizar producto--------------
    public ArrayList<Producto> actualizarProducto(Producto producto) throws IOException {

        // se actualiza la informacion con la lista obtenida
        for (int i = 0; i <= listaProductos.size(); i++) {
            if (listaProductos.get(i).getNombreProducto().equals(producto.getNombreProducto())) {
                listaProductos.get(i).setDescripcion(producto.getDescripcion());
                listaProductos.get(i).setTipo_Articulo(producto.getTipo_Articulo());
                listaProductos.get(i).setUrlFoto(producto.getUrlFoto());
                break;
            }
        }


        return listaProductos;
    }

    //----------------------------------eliminar producto---------------
    public ArrayList<Producto> eliminarProdcuto(Producto p1) throws IOException {


        // se elimna el prodcuto con la lista obtenida
        for (int i = 0; i <= listaProductos.size(); i++) {
            if (listaProductos.get(i).getNombreProducto().equals(p1.getNombreProducto())) {
                listaProductos.remove(i);
                break;
            }
        }

        return listaProductos;


    }
    //-----------------------------busar producto-----
    public Producto buscarProducto(String nombre){
        for (Producto producto:listaProductos) {
            if(producto.getNombreProducto().equals(nombre)){
                return producto;
            }

        }
        return null;
    }
    //------------------------------------getter y setter--------------


    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }



//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SECCION ANUNCIANTES++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //lista anunciante
    public ArrayList<Anunciante>listaAnunciantes=new ArrayList<>();

    //verifica inicio de seesion tipo Anunciante
    public boolean verificarinicioSesionAnunciante(String usuario, String clave){
      boolean vericacion=false;
        for (Anunciante anuncianteAutenticado:listaAnunciantes) {
            if(anuncianteAutenticado.getUsuario().equals(usuario)&& anuncianteAutenticado.getContrasenia().equals(clave)){
                vericacion= true;
                break;
            }
        }
        return vericacion;
    }

    //guarda  a un tipo de usuario anunciante

    public ArrayList<Anunciante> guardarAnunciante(Anunciante anunciante) throws IOException {
        listaAnunciantes.add(anunciante);
        return listaAnunciantes;

    }
    //verifica anunciante qu eno haya sido creado
    public boolean verificarAnuncianteCreado(Anunciante anunciante) throws IOException {

        boolean confirmacion=true;

        // se busca a informacion con la lista obtenida
        for (int i = 0; i < listaAnunciantes.size(); i++) {
            if (listaAnunciantes.get(i).getUsuario().equals(anunciante.getUsuario())) {
                confirmacion=false;
                break;
            }
        }
        return confirmacion;
    }
    //-----------------------------limpiar ------------------

    public  void limpiarListaAnunciantes(){
        this.listaAnunciantes.clear();
    }

    //get y set de la lista
    public ArrayList<Anunciante> getListaAnunciantes() {
        return listaAnunciantes;
    }

    public void setListaAnunciantes(ArrayList<Anunciante> listaAnunciantes) {
        this.listaAnunciantes = listaAnunciantes;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SECCION COMPRADOR++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//lista anunciante
    ArrayList<Comprador>listaCompradores=new ArrayList<>();

    //verifica inicio de seesion tipo Anunciante
    public boolean verificarinicioSesionCompradores(String usuario, String clave){
        boolean vericacion=false;
        for (Comprador compradorAutenticado:listaCompradores) {
            if(compradorAutenticado.getUsuario().equals(usuario)&& compradorAutenticado.getContrasenia().equals(clave)){
                vericacion= true;
                break;
            }
        }
        return vericacion;
    }

    //guarda  a un tipo de usuario anunciante

    public ArrayList<Comprador> guardarComprador(Comprador comprador) throws IOException {
        listaCompradores.add(comprador);
        return listaCompradores;

    }
    //verifica que el comprador ya haya sido registrado
    public boolean verificarCompradorCreado(Comprador comprador) throws IOException {

        boolean confirmacion=true;

        // se busca a informacion con la lista obtenida
        for (int i = 0; i < listaCompradores.size(); i++) {
            if (listaCompradores.get(i).getUsuario().equals(comprador.getUsuario())) {
                confirmacion=false;
                break;
            }
        }
        return confirmacion;
    }

    ArrayList<String>nombresProducto=new ArrayList<>();

    //nombres productos
    public ArrayList<String> obtenerNombresProductos(){
        nombresProducto.clear();
        for (Producto producto:listaProductos) {
            nombresProducto.add(producto.getNombreProducto());
        }
        return nombresProducto;
    }
    public void guardarNombre(String nombre){
        nombresProducto.add(nombre);
    }

    public ArrayList<String> getNombresProducto() {
        return nombresProducto;
    }

    public void setNombresProducto(ArrayList<String> nombresProducto) {
        this.nombresProducto = nombresProducto;
    }
//-----------------------------limpiar ------------------

    public  void limpiarCompradores(){
        this.listaCompradores.clear();
    }
    //get y set de la lista
    public ArrayList<Comprador> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(ArrayList<Comprador>listaCompradores) {
        this.listaCompradores =listaCompradores;
    }

    public boolean getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }

    public Anunciante obetnerAnunciante(String usuario) {
        for (Anunciante anunciante:listaAnunciantes) {
            if(anunciante.getUsuario().equals(usuario)){
                return anunciante;
            }
        }
        return null;
    }
    public Comprador obtenerComprador(String usuario){
        for (Comprador comprador:listaCompradores) {
            if(comprador.getUsuario().equals(usuario)){
                return  comprador;
            }
        }
        return  null;
    }
//+++++++++++++++++++++++++++++++++++++++++seccio anuncio++++++++++++++++++++++++++++++++++++++++++++++



    public void guardarAnuncios(Anuncio anuncio){
        listaAnuncios.add(anuncio);
    }

    public ArrayList<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public Anunciante getAnuncianteGlobal() {
        return anuncianteGlobal;
    }

    public void setAnuncianteGlobal(Anunciante anuncianteGlobal) {
        this.anuncianteGlobal = anuncianteGlobal;
    }

    public Comprador getCompradorGlobal() {
        return compradorGlobal;
    }

    public void setCompradorGlobal(Comprador compradorGlobal) {
        this.compradorGlobal = compradorGlobal;
    }

    public boolean verificarAnuncioCreado(Anuncio anuncio) {
        boolean confirmacion=true;
        for (Anuncio anuncio1:listaAnuncios) {
            if(anuncio1.getNombre().equals(anuncio.getNombre())){
                confirmacion=false;
                break;
            }
        }
        return  confirmacion;
    }
    //tab anuncio
    private ControllerInicioView controllerInicioView;

//--------------------------inicio---------------------------------
    private ArrayList<Puja>listaPujas;
    public void guardarPujas(Puja puja) {
        listaPujas.add(puja);
    }

    public ArrayList<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(ArrayList<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    public void eliminarAnuncio(AnuncioDto anuncioDto) {
        for (int i = 0; i <listaAnuncios.size() ; i++) {
            if (listaAnuncios.get(i).getNombre().equals(anuncioDto.nombre())){
                listaAnuncios.remove(i);
            }

        }
    }
}
