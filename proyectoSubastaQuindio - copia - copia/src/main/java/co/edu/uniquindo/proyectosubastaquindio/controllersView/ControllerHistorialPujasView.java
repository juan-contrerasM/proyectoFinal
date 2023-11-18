package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerHistorialPujasView {

    @FXML
    private TableColumn<?, ?> columAnunciante;

    @FXML
    private TableColumn<?, ?> columCodigo;

    @FXML
    private TableColumn<?, ?> columNombreProducto;

    @FXML
    private TableColumn<?, ?> columOferta;

    @FXML
    private TableColumn<?, ?> columTipoArticulo;

    @FXML
    private ImageView imgProducto;

    @FXML
    private TableView<?> tableHistorial;

    @FXML
    private TextField txtEstado;

}
