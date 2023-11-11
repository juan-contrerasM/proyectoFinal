package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ControllerInicioView {
    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSeguiente;
    @FXML
    private TableColumn<?, ?> columnaNombreProductoPublicaciones;

    @FXML
    private TableColumn<?, ?> columnaTipoProductoPublicaciones;
    @FXML
    private TableColumn<?, ?> columnaDescripcionPublicaciones;
    @FXML
    private ImageView imgPublicaciones;

    @FXML
    private TableView<?> tablaPublicaciones;
    @FXML
    void publicacionAnterior(ActionEvent event) {

    }

    @FXML
    void publicacionSiguiente(ActionEvent event) {

    }


}
