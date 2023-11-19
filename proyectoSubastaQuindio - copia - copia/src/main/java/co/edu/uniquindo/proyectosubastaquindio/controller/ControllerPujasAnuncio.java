package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PujaDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerPujasAnuncio {
    static ModelfactoryController modelfactoryController = new ModelfactoryController();
    public ControllerPujasAnuncio(){
        modelfactoryController=ModelfactoryController.getInstance();
    }
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelfactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }

    public List<AnuncioDto> cargarAnuncion() throws IOException {
      return   modelfactoryController.cargarAnuncios();
    }

    public List<Puja> cargarPujas() throws IOException {
        return modelfactoryController.cargarPujas();
    }

    public void eliminarAnuncio(AnuncioDto anuncioDto) throws IOException {
        modelfactoryController.eliminarAnuncio(anuncioDto);
    }
}
