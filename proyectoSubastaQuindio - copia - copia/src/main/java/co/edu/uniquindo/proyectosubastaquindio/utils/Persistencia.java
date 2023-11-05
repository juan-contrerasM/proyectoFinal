package co.edu.uniquindo.proyectosubastaquindio.utils;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/
import co.edu.uniquindo.proyectosubastaquindio.model.Anunciante;
import co.edu.uniquindo.proyectosubastaquindio.model.Comprador;
import co.edu.uniquindo.proyectosubastaquindio.utils.ArchivoUtil;

import co.edu.uniquindo.proyectosubastaquindio.model.Producto;
import co.edu.uniquindo.proyectosubastaquindio.model.SubastaQuindio;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Persistencia {


//--------------------------------------RUTAS----------------------------------------
    public static final String RUTA_ARCHIVO_PRODUCTOS = "C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\archivos\\productos.txt";
    public static final String RUTA_ARCHIVO_MODELO_PRODCUTOS_XML="C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\model.xml";
    public static final  String RUTA_ARCHIVO_MODELO_BANCO_BINARIO="C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\model.dat";
    public static final String RUTA_ARCHIVO_LOG ="C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\archivos\\log.txt";
    public static final  String RUTA_ARCHIVO_Anunciastes="C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\archivos\\anunciastes";
    public static final String RUTA_ARCHIVO_COMPRADORES="C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\persistencia\\archivos\\compradores.txt";


    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param
     * @throws IOException
     */
//-------------------------------------------GUARDAR ARCHIVOS------------------------------
   //prodcutos
    public static void guardarProdcutos(ArrayList<Producto> listaClientes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Producto productos:listaClientes)
        {
            contenido+= productos.getNombreProducto()+"--"+productos.getDescripcion()+"--"+productos.getUrlFoto()
                    +"--"+productos.getTipo_Articulo()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
    }
    //anunciantes
    public static void guardarAnunciastes(ArrayList<Anunciante> listaAnunciantes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Anunciante anunciante:listaAnunciantes)
        {
            contenido+= anunciante.getNombre()+"--"+anunciante.getApellido()+"--"+anunciante.getCantAnunciosActivos()
                    +"--"+anunciante.getUsuario()+"--"+anunciante.getEdad()+"--"+anunciante.getCedula()+"--"+anunciante.getContrasenia()+"--"+anunciante.getCantTimpoLimitado()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_Anunciastes, contenido, false);
    }
    //compradores
    public static void guardarCompradores(ArrayList<Comprador> listaCompradores) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Comprador comprador:listaCompradores)
        {
            contenido+= comprador.getNombre()+"--"+comprador.getApellido()+"--"+comprador.getCantMaxPujasAnuncio()
                    +"--"+comprador.getUsuario()+"--"+comprador.getEdad()+"--"+comprador.getCedula()+"--"+comprador.getContrasenia()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenido, false);
    }









//	--------------------------------------------CARGAR ARCHIVOS----------------------------------------------------------

    /**
     *
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

    //productos
    public static ArrayList<Producto> cargarProductos() throws FileNotFoundException, IOException
    {
        ArrayList<Producto> productos =new ArrayList<Producto>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Producto producto = new Producto();
            producto.setNombreProducto(linea.split("--")[0]);
            producto.setDescripcion(linea.split("--")[1]);
            producto.setUrlFoto(linea.split("--")[2]);
            producto.setTipo_Articulo(tipoArticulo.valueOf(linea.split("--")[3]));
              productos.add(producto);
        }
        return productos;
    }

    //anunciantes
    public static ArrayList<Anunciante> cargarAnunciantes() throws FileNotFoundException, IOException
    {
        ArrayList<Anunciante> listaAnunciantes =new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_Anunciastes);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Anunciante anunciante = new Anunciante();
            anunciante.setNombre(linea.split("--")[0]);
            anunciante.setApellido(linea.split("--")[1]);
            anunciante.setCantAnunciosActivos(Integer.parseInt(linea.split("--")[2]));
            anunciante.setUsuario((linea.split("--")[3]));
            anunciante.setEdad(Integer.parseInt(linea.split("--")[4]));
            anunciante.setCedula((linea.split("--")[5]));
            anunciante.setContrasenia((linea.split("--")[6]));
            anunciante.setCantTimpoLimitado(Float.parseFloat((linea.split("--")[7])));
            listaAnunciantes.add(anunciante);
        }
        return listaAnunciantes;
    }
    //compradores
    public static ArrayList<Comprador> cargarCompradores() throws FileNotFoundException, IOException
    {
        ArrayList<Comprador> listaCompradores =new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Comprador comprador = new Comprador();
            comprador.setNombre(linea.split("--")[0]);
            comprador.setApellido(linea.split("--")[1]);
            comprador.setCantMaxPujasAnuncio(Integer.parseInt(linea.split("--")[2]));
            comprador.setUsuario((linea.split("--")[3]));
            comprador.setEdad(Integer.parseInt(linea.split("--")[4]));
            comprador.setCedula((linea.split("--")[5]));
            comprador.setContrasenia((linea.split("--")[6]));

            listaCompradores.add(comprador);
        }
        return listaCompradores;
    }



    //------------------------------REGISTRO LOG-----------------------------------------
    public static void guardaRegistroLog(String mensaje, int nivel, String accion) {

        ArchivoUtil.guardarRegistroLog(mensaje, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    //------------------------------------SERIALIZACIÓN  y XML

//BINARIO
    public static SubastaQuindio cargarRecursoBancoBinario() {

        SubastaQuindio subastaQuindio= null;

        try {
            subastaQuindio = (SubastaQuindio) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subastaQuindio;
    }

    public static void guardarRecursoBancoBinario(SubastaQuindio subastaQuindio) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO, subastaQuindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//XML
    public static void guardarRecursoBancoXML(SubastaQuindio subastaQuindio) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PRODCUTOS_XML, subastaQuindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }










}
