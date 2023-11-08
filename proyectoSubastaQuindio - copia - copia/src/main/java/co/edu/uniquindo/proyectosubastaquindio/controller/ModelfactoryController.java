package co.edu.uniquindo.proyectosubastaquindio.controller;
import co.edu.uniquindo.proyectosubastaquindio.config.RabbitFactory;
import co.edu.uniquindo.proyectosubastaquindio.controller.service.IModelFactoryService;
import co.edu.uniquindo.proyectosubastaquindio.controller.service.ISubastaQuindioControllerService;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.mappers.SubastaQuindioMapper;
import co.edu.uniquindo.proyectosubastaquindio.model.Anunciante;
import co.edu.uniquindo.proyectosubastaquindio.model.Comprador;
import co.edu.uniquindo.proyectosubastaquindio.model.Producto;
import co.edu.uniquindo.proyectosubastaquindio.model.SubastaQuindio;
import co.edu.uniquindo.proyectosubastaquindio.utils.Persistencia;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModelfactoryController implements IModelFactoryService, Runnable {



//instancias
    SubastaQuindio subastaQuindio= new SubastaQuindio();
    SubastaQuindioMapper mapper = SubastaQuindioMapper.INSTANCE;
//rabiitMq
    RabbitFactory rabbitFactory;
    ConnectionFactory connectionFactory;

    //consumidor
    Thread hiloServicioConsumer1;




//------------------------------singlentton clase secreta------------------------------------

    private static class SingletonHolder implements ISubastaQuindioControllerService {
        private final static ModelfactoryController eINSTANCE = new ModelfactoryController();
    }
    public static  ModelfactoryController getInstance(){
        return SingletonHolder.eINSTANCE;
    }

    // contrutor
    public ModelfactoryController() {initRabbitConnection();

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


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++RABBITMQ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static final String QUEUE_NUEVA_PUBLICACION = "nueva_publicacion";
    private void initRabbitConnection() {
        rabbitFactory = new RabbitFactory();
        connectionFactory = rabbitFactory.getConnectionFactory();
        System.out.println("conexion establecidad");

    }


    public void producirMensaje(String queue, String message, String accion) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish(accion, queue, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //consumidor
    public void consumirMensajesServicio1(){
        hiloServicioConsumer1 = new Thread(this);
        hiloServicioConsumer1.start();
    }


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        if(currentThread == hiloServicioConsumer1){
            consumirMensajes();
        }
    }

    private void consumirMensajes() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NUEVA_PUBLICACION, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                //actualizarEstado(message);
            };
            while (true) {
                channel.basicConsume(QUEUE_NUEVA_PUBLICACION, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
