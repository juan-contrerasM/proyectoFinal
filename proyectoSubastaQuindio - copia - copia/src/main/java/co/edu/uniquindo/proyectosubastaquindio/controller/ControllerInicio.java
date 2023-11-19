package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.controllersView.ControllerInicioView;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;

import java.io.IOException;
import java.util.List;

public class ControllerInicio {
    ModelfactoryController modelfactoryController;
    ControllerInicioView controllerInicioView;

    public  ControllerInicio(){
        modelfactoryController=ModelfactoryController.getInstance();
    }





    public List<AnuncioDto> cargarAnuncion() throws IOException {
     return    modelfactoryController.cargarAnuncios();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public CompradorDto obtenerComprador() throws IOException {
        return modelfactoryController.obetnerComprador();
    }

    public void guardarPujar(Puja puja) throws IOException {
        modelfactoryController.guarPujarDto(puja);
    }


}
