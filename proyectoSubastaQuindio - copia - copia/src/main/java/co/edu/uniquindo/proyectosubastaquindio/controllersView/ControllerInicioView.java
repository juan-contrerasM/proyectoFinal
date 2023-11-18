package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerInicio;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.ArraylistVacioException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PublicacionesDto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerInicioView implements  Initializable {
        private final ObservableList<PublicacionesDto> listaPublicaciones = FXCollections.observableArrayList();

        private ObservableList<AnuncioDto>listaAnuncion;

        private int  contador=0;


private  ControllerInicio controllerInicio;
        @FXML
        private ImageView imagenAnterior;


        @FXML
        private ImageView imagenSiguiente;

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


/*
        @FXML
        void anteriorAnuncio(MouseEvent event) throws IOException {
                listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                contador--;
                if(contador<0){
                        contador=listaAnuncion.size()-1;

                }
                textArea.setText(listaAnuncion.get(contador).toString());
        }
*/
        @FXML
        void hacerPuja(ActionEvent event) {

        }
/*
        @FXML
        void siguienteAnuncio(MouseEvent event) throws IOException {
                listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                contador++;
                if(contador==listaAnuncion.size()){
                        contador=0;

                }
                textArea.setText(listaAnuncion.get(contador).toString());
        }
*/



        @Override
        public void initialize(URL location, ResourceBundle resources) {
                controllerInicio = new ControllerInicio();
                listaAnuncion=FXCollections.observableArrayList();
                                // Cargar los anuncios
                try {
                        listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }


        }



        @FXML
        private ImageView imagenLogo1;

        @FXML
        private ImageView imagenLogo2;



        @FXML
        private TextField txtValorPuja;


        public void siguienteAnuncio(MouseEvent mouseEvent) throws IOException {

                listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                try {
                        if (listaAnuncion.isEmpty()) {
                                throw new ArraylistVacioException("El ArrayList está vacío");
                        }
                        contador++;
                        if(contador==listaAnuncion.size()){
                                contador=0;

                        }
                        textArea.setText("Nombre Anuncio: "+listaAnuncion.get(contador).nombre()+"\n"+"Nombre Producto: "+listaAnuncion.get(contador).nombreProdcuto()+" codigo: "+listaAnuncion.get(contador).codigo() +" Anunciante: "+listaAnuncion.get(contador).nombreAnunciante()+ "\n"+"Fecha Inicial: " +listaAnuncion.get(contador).fechaInicio()+"- Fecha Finalizacion: "+listaAnuncion.get(contador).fechaFinalizacion());
                        txtValorPuja.setText(listaAnuncion.get(contador).valorInicial()+"");
                       Image image2=new Image(listaAnuncion.get(contador).url());
                       imgAnuncio.setImage(image2);

                        // Código para procesar el ArrayList aquí si no está vacío

                } catch (ArraylistVacioException e) {
                        System.out.println("Excepción: " + e.getMessage());
                        mostrarMensaje("No hay anuncios","No hay Anuncios","No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
                        textArea.setText("");
                        txtValorPuja.setText("0000");
                        File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg" );
                        String url = destino.toURI().toString();
                        Image image= new Image(url);
                        imgAnuncio.setImage(image);
                }

        }

        public void anteriorAnuncio(MouseEvent mouseEvent) throws IOException {
                listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                try {
                        if (listaAnuncion.isEmpty()) {
                                throw new ArraylistVacioException("El ArrayList está vacío");
                        }

                listaAnuncion.addAll(controllerInicio.cargarAnuncion());
                contador--;
                if(contador<0){
                        contador=listaAnuncion.size()-1;

                }
                textArea.setText("Nombre Anuncio: "+listaAnuncion.get(contador).nombre()+"\n"+"Nombre Producto: "+listaAnuncion.get(contador).nombreProdcuto()+" codigo: "+listaAnuncion.get(contador).codigo() +" Anunciante: "+listaAnuncion.get(contador).nombreAnunciante()+ "\n"+"Fecha Inicial: " +listaAnuncion.get(contador).fechaInicio()+"- Fecha Finalizacion: "+listaAnuncion.get(contador).fechaFinalizacion());
                txtValorPuja.setText(listaAnuncion.get(contador).valorInicial()+"");
                Image image2=new Image(listaAnuncion.get(contador).url());
                imgAnuncio.setImage(image2);
                } catch (ArraylistVacioException e) {
                        System.out.println("Excepción: " + e.getMessage());
                        mostrarMensaje("No hay anuncios","No hay Anuncios","No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
                        textArea.setText("");
                        txtValorPuja.setText("0000");
                        File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg" );
                        String url = destino.toURI().toString();
                        Image image= new Image(url);
                        imgAnuncio.setImage(image);

                }
        }
        private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
                Alert aler = new Alert(alertType);
                aler.setTitle(titulo);
                aler.setHeaderText(header);
                aler.setContentText(contenido);
                aler.showAndWait();
                registrarAcciones("se mostro mensaje", 1, "mostrar mensaje");
        }
        private void registrarAcciones(String mensaje, int nivel, String accion) {
                controllerInicio.registrarAcciones(mensaje, nivel, accion);

        }
}








