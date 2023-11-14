package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerInicio;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PublicacionesDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInicioView implements Initializable {
        private final ObservableList<PublicacionesDto> listaPublicaciones = FXCollections.observableArrayList();

        ControllerInicio controllerInicio;




        @FXML
        private Button btnAnteriorAnuncio;

        @FXML
        private Button btnHacerPuja;

        @FXML
        private Button btnSiguienteAnuncio;

        @FXML
        private ImageView imagenLogo;

        @FXML
        private ImageView imgAnuncio;

        @FXML
        private AnchorPane paneAnuncio;

        @FXML
        private AnchorPane paneBotones;

        @FXML
        private TextArea textArea;

        @FXML
        void anteriorAnuncio(ActionEvent event) {

        }

        @FXML
        void hacerPuja(ActionEvent event) {

        }

        @FXML
        void siguienteAnuncio(ActionEvent event) {

        }

        public void lista(){
                listaPublicaciones.addAll(controllerInicio.listaPublicaciones());

        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {

                //publicarAnuncios();
               // System.out.println(listaPublicacios.get(0).anuncianteDto().toString());
                //System.out.println(listaPublicacios.get(0).anuncioDto().toString());
                //System.out.println(listaPublicacios.get(0).productoDto().toString());
                //
                }
}

