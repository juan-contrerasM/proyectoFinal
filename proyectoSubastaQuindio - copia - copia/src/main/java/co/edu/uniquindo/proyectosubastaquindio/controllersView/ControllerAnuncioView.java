package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerAnuncio;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAnuncioView implements Initializable {
    private ControllerAnuncio controllerAnuncio;
    private final ObservableList<ProductoDto> productos = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> comboSeleccionProducto;
    ObservableList<String> nuevosProductos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerAnuncio=new ControllerAnuncio();

        try {
            nuevosProductos = FXCollections.observableArrayList(controllerAnuncio.obtenerNombresProducto());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        comboSeleccionProducto.setItems(nuevosProductos);



    }
    @FXML
    private Button btnPublicar;

    @FXML
    private ImageView imgFotoAnuncio;
    //FLATAN ATRIBUTOS FXL POR DEFINIR


    @FXML
    void publicarAnuncio(ActionEvent event) {

    }
    @FXML
    void cargarProductos(MouseEvent event) throws IOException {
        nuevosProductos.setAll(controllerAnuncio.obtenerNombresProducto());

    }

    @FXML
    void mostrarEnTabla(ActionEvent event) throws IOException {
        //sirve para daber si seleccionaron algo en el combo
        String selectedItem = comboSeleccionProducto.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ProductoDto productoDto = controllerAnuncio.buscarProducto(selectedItem);
            ObservableList<ProductoDto> productoSeleccionado = FXCollections.observableArrayList();
            productoSeleccionado.add(productoDto);
            // solo falta que el prooducto dto que esta arriba comenzar a mostar los atribustos en la tabla

            //String productoSeleccionado = selectedItem;

            columna1AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
            columna2AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
            columna3AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().tipo_Articulo())));
            // Supongamos que productoDto.urlFoto() devuelve la URL de la imagen como una cadena
            String urlImagen = productoDto.urlFoto();

// Carga la imagen en el ImageView
            Image image = new Image(urlImagen);
            imgFotoAnuncio.setImage(image);
            //columna4AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urlFoto()));

            tablaAnuncio1.getItems().clear();
            tablaAnuncio1.setItems(productoSeleccionado);


        } else {
            System.out.println("Ningún elemento seleccionado.");
        }

    }//SEgunda parte


    @FXML
    private Button btnCargarProducto;

    @FXML
    private TableColumn<ProductoDto, String> columna1AnuncioTabla1;
    @FXML
    private TableColumn<ProductoDto, String> columna2AnuncioTabla1;
    @FXML
    private TableColumn<ProductoDto, String> columna3AnuncioTabla1;
    @FXML
    private TableView<ProductoDto> tablaAnuncio1;


    @FXML
    private TableColumn<AnuncianteDto, String> columna1AnuncioTabla2;
    @FXML
    private TableColumn<AnuncianteDto, String> columna2AnuncioTabla2;
    @FXML
    private TableColumn<AnuncianteDto, String> columna3AnuncioTabla2;
    @FXML
    private TableView<AnuncianteDto> tableAnuncio2;



    @FXML
    private TableColumn<?, ?> columna1AnuncioTabla3;
    @FXML
    private TableColumn<?, ?> columna2AnuncioTabla3;
    @FXML
    private TableView<?> tablaInfoFecha;




}
