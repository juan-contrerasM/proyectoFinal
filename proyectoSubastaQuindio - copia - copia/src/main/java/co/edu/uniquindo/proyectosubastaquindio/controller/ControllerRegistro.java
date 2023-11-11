package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegistro  {
    ModelfactoryController modelfactoryController;
    public ControllerRegistro(){
        modelfactoryController=ModelfactoryController.getInstance();

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
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }


}
