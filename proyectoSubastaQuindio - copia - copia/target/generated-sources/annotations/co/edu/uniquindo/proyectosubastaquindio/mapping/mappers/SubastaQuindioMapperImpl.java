package co.edu.uniquindo.proyectosubastaquindio.mapping.mappers;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Anunciante;
import co.edu.uniquindo.proyectosubastaquindio.model.Anuncio;
import co.edu.uniquindo.proyectosubastaquindio.model.Comprador;
import co.edu.uniquindo.proyectosubastaquindio.model.Producto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-18T11:20:39-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
*/
public class SubastaQuindioMapperImpl implements SubastaQuindioMapper {

    @Override
    public ProductoDto productoToProductoDto(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        String nombreProducto = null;
        String descripcion = null;
        tipoArticulo tipo_Articulo = null;
        String urlFoto = null;

        nombreProducto = producto.getNombreProducto();
        descripcion = producto.getDescripcion();
        tipo_Articulo = producto.getTipo_Articulo();
        urlFoto = producto.getUrlFoto();

        ProductoDto productoDto = new ProductoDto( nombreProducto, descripcion, tipo_Articulo, urlFoto );

        return productoDto;
    }

    @Override
    public Producto productoDtoToProducto(ProductoDto productoDto) {
        if ( productoDto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setUrlFoto( productoDto.urlFoto() );
        producto.setNombreProducto( productoDto.nombreProducto() );
        producto.setDescripcion( productoDto.descripcion() );
        producto.setTipo_Articulo( productoDto.tipo_Articulo() );

        return producto;
    }

    @Override
    public List<ProductoDto> getProductoDto(List<Producto> listaProducto) {
        if ( listaProducto == null ) {
            return null;
        }

        List<ProductoDto> list = new ArrayList<ProductoDto>( listaProducto.size() );
        for ( Producto producto : listaProducto ) {
            list.add( productoToProductoDto( producto ) );
        }

        return list;
    }

    @Override
    public AnuncianteDto anuncianteToAnuncianteDto(Anunciante anunciante) {
        if ( anunciante == null ) {
            return null;
        }

        String nombre = null;
        String apellido = null;
        String cedula = null;
        int edad = 0;
        String usuario = null;
        String contrasenia = null;
        int cantAnunciosActivos = 0;
        float cantTimpoLimitado = 0.0f;

        nombre = anunciante.getNombre();
        apellido = anunciante.getApellido();
        cedula = anunciante.getCedula();
        edad = anunciante.getEdad();
        usuario = anunciante.getUsuario();
        contrasenia = anunciante.getContrasenia();
        cantAnunciosActivos = anunciante.getCantAnunciosActivos();
        cantTimpoLimitado = anunciante.getCantTimpoLimitado();

        AnuncianteDto anuncianteDto = new AnuncianteDto( nombre, apellido, cedula, edad, usuario, contrasenia, cantAnunciosActivos, cantTimpoLimitado );

        return anuncianteDto;
    }

    @Override
    public Anunciante anuncianteDtoToAnunciante(AnuncianteDto anuncianteDto) {
        if ( anuncianteDto == null ) {
            return null;
        }

        Anunciante anunciante = new Anunciante();

        anunciante.setNombre( anuncianteDto.nombre() );
        anunciante.setApellido( anuncianteDto.apellido() );
        anunciante.setCedula( anuncianteDto.cedula() );
        anunciante.setEdad( anuncianteDto.edad() );
        anunciante.setUsuario( anuncianteDto.usuario() );
        anunciante.setContrasenia( anuncianteDto.contrasenia() );
        anunciante.setCantAnunciosActivos( anuncianteDto.cantAnunciosActivos() );
        anunciante.setCantTimpoLimitado( anuncianteDto.cantTimpoLimitado() );

        return anunciante;
    }

    @Override
    public List<AnuncianteDto> getAnuncianteDto(List<Anunciante> listaAnunciantes) {
        if ( listaAnunciantes == null ) {
            return null;
        }

        List<AnuncianteDto> list = new ArrayList<AnuncianteDto>( listaAnunciantes.size() );
        for ( Anunciante anunciante : listaAnunciantes ) {
            list.add( anuncianteToAnuncianteDto( anunciante ) );
        }

        return list;
    }

    @Override
    public CompradorDto compradorToCompradorDto(Comprador comprador) {
        if ( comprador == null ) {
            return null;
        }

        String nombre = null;
        String apellido = null;
        String cedula = null;
        int edad = 0;
        String usuario = null;
        String contrasenia = null;
        int cantMaxPujasAnuncio = 0;

        nombre = comprador.getNombre();
        apellido = comprador.getApellido();
        cedula = comprador.getCedula();
        edad = comprador.getEdad();
        usuario = comprador.getUsuario();
        contrasenia = comprador.getContrasenia();
        cantMaxPujasAnuncio = comprador.getCantMaxPujasAnuncio();

        CompradorDto compradorDto = new CompradorDto( nombre, apellido, cedula, edad, usuario, contrasenia, cantMaxPujasAnuncio );

        return compradorDto;
    }

    @Override
    public Comprador compradorDtoToComprador(CompradorDto compradorDtoDto) {
        if ( compradorDtoDto == null ) {
            return null;
        }

        Comprador comprador = new Comprador();

        comprador.setNombre( compradorDtoDto.nombre() );
        comprador.setApellido( compradorDtoDto.apellido() );
        comprador.setCedula( compradorDtoDto.cedula() );
        comprador.setEdad( compradorDtoDto.edad() );
        comprador.setUsuario( compradorDtoDto.usuario() );
        comprador.setContrasenia( compradorDtoDto.contrasenia() );
        comprador.setCantMaxPujasAnuncio( compradorDtoDto.cantMaxPujasAnuncio() );

        return comprador;
    }

    @Override
    public List<CompradorDto> getCompradorDto(List<Comprador> listaCompradores) {
        if ( listaCompradores == null ) {
            return null;
        }

        List<CompradorDto> list = new ArrayList<CompradorDto>( listaCompradores.size() );
        for ( Comprador comprador : listaCompradores ) {
            list.add( compradorToCompradorDto( comprador ) );
        }

        return list;
    }

    @Override
    public AnuncioDto anuncioToAnucioDto(Anuncio anuncio) {
        if ( anuncio == null ) {
            return null;
        }

        String nombre = null;
        String codigo = null;
        TipoEstado estado = null;
        LocalDate fechaInicio = null;
        LocalDate fechaFinalizacion = null;
        float valorInicial = 0.0f;
        String nombreProdcuto = null;
        String nombreAnunciante = null;
        String url = null;

        nombre = anuncio.getNombre();
        codigo = anuncio.getCodigo();
        estado = anuncio.getEstado();
        fechaInicio = anuncio.getFechaInicio();
        fechaFinalizacion = anuncio.getFechaFinalizacion();
        valorInicial = anuncio.getValorInicial();
        nombreProdcuto = anuncio.getNombreProdcuto();
        nombreAnunciante = anuncio.getNombreAnunciante();
        url = anuncio.getUrl();

        AnuncioDto anuncioDto = new AnuncioDto( nombre, codigo, estado, fechaInicio, fechaFinalizacion, valorInicial, nombreProdcuto, nombreAnunciante, url );

        return anuncioDto;
    }

    @Override
    public Anuncio anuncioDtoToAnuncio(AnuncioDto anuncioDto) {
        if ( anuncioDto == null ) {
            return null;
        }

        Anuncio anuncio = new Anuncio();

        anuncio.setNombre( anuncioDto.nombre() );
        anuncio.setCodigo( anuncioDto.codigo() );
        anuncio.setEstado( anuncioDto.estado() );
        anuncio.setFechaInicio( anuncioDto.fechaInicio() );
        anuncio.setFechaFinalizacion( anuncioDto.fechaFinalizacion() );
        anuncio.setValorInicial( anuncioDto.valorInicial() );
        anuncio.setNombreAnunciante( anuncioDto.nombreAnunciante() );
        anuncio.setNombreProdcuto( anuncioDto.nombreProdcuto() );
        anuncio.setUrl( anuncioDto.url() );

        return anuncio;
    }

    @Override
    public List<AnuncioDto> getAnuncioDto(List<Anuncio> listaAnuncios) {
        if ( listaAnuncios == null ) {
            return null;
        }

        List<AnuncioDto> list = new ArrayList<AnuncioDto>( listaAnuncios.size() );
        for ( Anuncio anuncio : listaAnuncios ) {
            list.add( anuncioToAnucioDto( anuncio ) );
        }

        return list;
    }
}
