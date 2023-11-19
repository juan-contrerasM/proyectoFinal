package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Comprador;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ControllerHistorialPujas {
    private  ModelfactoryController modelfactoryController;

    public ControllerHistorialPujas(){
        modelfactoryController=ModelfactoryController.getInstance();
    }

    public CompradorDto obtenerComprador() throws IOException {
        return modelfactoryController.obetnerComprador();
    }

    public List<Puja> obtenerPujas() throws IOException {
        return modelfactoryController.cargarPujas();
    }

    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public boolean obtenerAutenticaion() {
        return  modelfactoryController.obtenerAutenticacion();
    }
}
