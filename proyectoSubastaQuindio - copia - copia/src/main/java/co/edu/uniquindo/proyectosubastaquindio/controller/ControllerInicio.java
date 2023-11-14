package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.controllersView.ControllerInicioView;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PublicacionesDto;
import javafx.collections.ObservableList;

public class ControllerInicio {
    ModelfactoryController modelfactoryController;
    ControllerInicioView controllerInicioView;

    public ObservableList<PublicacionesDto> listaPublicaciones(){

        return modelfactoryController.obtenerListaPublicaciones();


    }

}
