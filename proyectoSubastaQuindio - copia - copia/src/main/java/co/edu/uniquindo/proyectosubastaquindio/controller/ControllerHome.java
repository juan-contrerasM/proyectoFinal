package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;

import java.io.IOException;

public class ControllerHome {
    private  ModelfactoryController modelfactoryController;

    public ControllerHome(){
        modelfactoryController=ModelfactoryController.getInstance();

    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }
    public boolean verificarInicioSesionAnunciante(String usuario, String clave) throws IOException {
        return modelfactoryController.verificarInicioSesionAnunciante( usuario, clave);
    }
    public boolean verificarInicioSesionComprador(String usuario, String clave) throws IOException {
        return modelfactoryController.verificarInicioComprador(usuario, clave);
    }

    public void guardarAutenticacion(boolean autenticacion) {
        modelfactoryController.guardarAutenticacion(autenticacion);
    }


    public AnuncianteDto obtenerAnunciante(String usuario) throws IOException {
        return modelfactoryController.obetnerAnunciante(usuario);
    }
    public CompradorDto obtenerComprador(String usuario) throws IOException {
        return modelfactoryController.obetnerComprador(usuario);
    }

    public void guardarAnuncianteGlobal(String usuario) throws IOException {
        modelfactoryController.guardarAnuncianteGlobal(usuario);
    }

    public void guardarCompradorGlobal(String usuario) throws IOException {
        modelfactoryController.guardarCompradorGlobal(usuario);
    }
}
