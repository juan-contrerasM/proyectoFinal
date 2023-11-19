package co.edu.uniquindo.proyectosubastaquindio.mapping.mappers;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.*;
import co.edu.uniquindo.proyectosubastaquindio.model.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface SubastaQuindioMapper {
SubastaQuindioMapper INSTANCE = Mappers.getMapper(SubastaQuindioMapper.class);

   //MAPPER PRODUCTOS
    @Named("productoToProductoDto")
    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);
    @IterableMapping(qualifiedByName = "productoToProductoDto")
    List<ProductoDto> getProductoDto(List<Producto> listaProducto);

    //MAPPER ANUNCIANTES
    @Named("anuncianteToAnuncianteDto")
    AnuncianteDto anuncianteToAnuncianteDto(Anunciante anunciante);
    Anunciante anuncianteDtoToAnunciante(AnuncianteDto anuncianteDto);
    @IterableMapping(qualifiedByName = "anuncianteToAnuncianteDto")
    List<AnuncianteDto> getAnuncianteDto(List<Anunciante> listaAnunciantes);

    //MAPPER COMPRADORES
    @Named("compradorToCompradorDto")
    CompradorDto compradorToCompradorDto(Comprador comprador);
    Comprador compradorDtoToComprador(CompradorDto compradorDtoDto);
    @IterableMapping(qualifiedByName = "compradorToCompradorDto")
    List<CompradorDto> getCompradorDto(List<Comprador> listaCompradores);

//mapper
@Named("anuncioToAnucioDto")
AnuncioDto anuncioToAnucioDto(Anuncio anuncio);
 Anuncio anuncioDtoToAnuncio(AnuncioDto anuncioDto);
 @IterableMapping(qualifiedByName = "anuncioToAnucioDto")
 List<AnuncioDto> getAnuncioDto(List<Anuncio> listaAnuncios);

 //pujas
 @Named("pujaToPujaDto")
 PujaDto pujaToPujaDto(Puja puja);
 Puja pujaDtoToPuja(PujaDto pujaDto);
 @IterableMapping(qualifiedByName = "pujaToPujaDto")
 List<PujaDto> getPujaDto(List<Puja> listaPuja);
}
