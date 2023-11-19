package co.edu.uniquindo.proyectosubastaquindio.controller;

import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Anuncio;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;
import javafx.collections.ObservableList;

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

    public ArrayList<Puja> cargarPujas() throws IOException {
        return modelfactoryController.cargarPujas();
    }

    public void eliminarAnuncio(AnuncioDto anuncioDto) throws IOException {
        modelfactoryController.eliminarAnuncio(anuncioDto);
    }

    public void guardarPujas(ArrayList<Puja> listaPujasDto) throws IOException {
        modelfactoryController.guardarListaPujas(listaPujasDto);

    }

    public boolean obtenerAutenicacion() {
        return modelfactoryController.obtenerAutenticacion();
    }

    public AnuncianteDto obtenerAnunciante() throws IOException {
        return modelfactoryController.obetnerAnunciante();
    }

    public ArrayList<Anuncio>cargarAnuncios() throws IOException {
        return  modelfactoryController.obtenerAnuncios();
    }

    public void guardarAnuncios(ArrayList<Anuncio> anun) throws IOException {
         modelfactoryController.guardarAnuncios2(anun);
    }
}
