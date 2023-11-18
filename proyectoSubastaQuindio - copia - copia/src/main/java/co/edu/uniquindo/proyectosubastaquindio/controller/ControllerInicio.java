package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.controllersView.ControllerInicioView;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PublicacionesDto;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerInicio {
    ModelfactoryController modelfactoryController;
    ControllerInicioView controllerInicioView;

    public  ControllerInicio(){
        modelfactoryController=ModelfactoryController.getInstance();
    }
    public ObservableList<PublicacionesDto> listaPublicaciones(){

        return modelfactoryController.obtenerListaPublicaciones();


    }




    public List<AnuncioDto> cargarAnuncion() throws IOException {
     return    modelfactoryController.cargarAnuncios();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }
}
