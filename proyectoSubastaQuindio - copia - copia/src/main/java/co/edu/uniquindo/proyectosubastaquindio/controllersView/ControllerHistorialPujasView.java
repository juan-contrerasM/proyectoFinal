package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerHistorialPujas;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.InvalidUrlException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerHistorialPujasView implements Initializable {

    @FXML
    private TableColumn<Puja, String> columAnunciante;

    @FXML
    private TableColumn<Puja, String> columCodigo;

    @FXML
    private TableColumn<Puja, String> columNombreProducto;

    @FXML
    private TableColumn<Puja, String> columOferta;

    @FXML
    private ImageView imgProducto;

    @FXML
    private TableView<Puja> tableHistorial;

    @FXML
    private TextField txtEstado;
    private ObservableList<Puja>listPu=FXCollections.observableArrayList();

    @FXML
    private Button btnRefrescar;
    private Puja puja;

    private ControllerHistorialPujas controllerHistorialPujas;
    private ObservableList<Puja>listaPujas= FXCollections.observableArrayList();
    @FXML
    void Refrescar(ActionEvent event) throws IOException {
        if(controllerHistorialPujas.obtenerAutenticaion()) {
            listPu.clear();
            listaPujas.clear();
            listPu.addAll(controllerHistorialPujas.obtenerPujas());
            listaPujas.addAll(filtrarPujas());
            columNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreAnuncio()));
            columAnunciante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreAnunciante()));
             columCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
            columOferta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOferta())));
            tableHistorial.setItems(listaPujas);
            registrarAcciones("SE ACUATLIZO TABLA HISTORIAL PUJAS", 1, "tabla actualizada");
        }else {
            mostrarMensaje("Autenticarse", "Usuario no autenticado", "Debe registrarse para ser autenticado\npara poder refrecar la tabla", Alert.AlertType.ERROR);
            //estoy tratando de abrir otras ventanas para poder navegar

            registrarAcciones("Error al refrescar debe autenticarse", 1, "no hubo un registro");

        }

    }
    public ArrayList<Puja> filtrarPujas() throws IOException {
        ArrayList<Puja>lista=new ArrayList<>();
        for (int i = 0; i <listPu.size() ; i++) {
            if(listPu.get(i).getNombreComprador().equals(controllerHistorialPujas.obtenerComprador().nombre())){
                lista.add(listPu.get(i));

            }
        }
        return lista;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerHistorialPujas=new ControllerHistorialPujas();
        listenerSelection();
        registrarAcciones("inicio historial pujas", 1, "Se inicializo tab hitorial pujas");


    }

    private void listenerSelection() {

            if (controllerHistorialPujas.obtenerAutenticaion()) {
                tableHistorial.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    puja = newSelection;
                    registrarAcciones("puja seleccionado", 1, "puja seleccionado");
                    txtEstado.setText(String.valueOf(puja.getTipoEstadoPuja()));

                    Image image = new Image(puja.getUrl());
                    imgProducto.setImage(image);
                });
            }
    }


    private void registrarAcciones(String mensaje, int nivel, String accion) {
        controllerHistorialPujas.registrarAcciones(mensaje, nivel, accion);

    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
        registrarAcciones("se mostro mensaje", 1, "mostrar mensaje");
    }


}
