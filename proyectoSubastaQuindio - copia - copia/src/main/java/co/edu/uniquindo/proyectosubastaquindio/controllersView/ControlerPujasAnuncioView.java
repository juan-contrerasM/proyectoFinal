package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerPujasAnuncio;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.ArraylistVacioException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.PujaDto;
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
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerPujasAnuncioView implements Initializable {

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVender;

    @FXML
    private ImageView imgAnteriorss;

    @FXML
    private ImageView imgAnuncios;

    @FXML
    private ImageView imgSiguiente;

    @FXML
    private Label lblAnunciosPublicados;

    @FXML
    private Label lblTabla;

    @FXML
    private TableView<Puja> tablePujas;
    @FXML
    private TableColumn<Puja, String> columCodigo;

    @FXML
    private TableColumn<Puja, String> columOferta;

    @FXML
    private TableColumn<Puja, String> columOfertaIni;

    @FXML
    private TableColumn<Puja, String> columPujador;

    @FXML
    private TextArea textdESCRIPCION;
    private ObservableList<AnuncioDto>listaAnuncion;
    private ControllerPujasAnuncio controllerPujasAnuncio;
private  int contador;

    @FXML
    void AnteriorAnuncio(MouseEvent event) throws IOException {
        listaAnuncion.clear();
        listaAnuncion.addAll(controllerPujasAnuncio.cargarAnuncion());
        listaPujasDto.clear();
        listaPujasDto.addAll(controllerPujasAnuncio.cargarPujas());

        tablePujas.setItems(listaPujasDto);
        try {
            if (listaAnuncion.isEmpty()) {
                throw new ArraylistVacioException("El ArrayList está vacío");
            }

            contador--;
            if(contador<0){
                contador=listaAnuncion.size()-1;

            }
            textdESCRIPCION.setText("Nombre Anuncio: "+listaAnuncion.get(contador).nombre()+"\n"+"Nombre Producto: "+listaAnuncion.get(contador).nombreProdcuto()+" codigo: "+listaAnuncion.get(contador).codigo() +" Anunciante: "+listaAnuncion.get(contador).nombreAnunciante()+ "\n"+"Fecha Inicial: " +listaAnuncion.get(contador).fechaInicio()+"- Fecha Finalizacion: "+listaAnuncion.get(contador).fechaFinalizacion());

            Image image2=new Image(listaAnuncion.get(contador).url());
            imgAnuncios.setImage(image2);



        } catch (ArraylistVacioException e) {
            System.out.println("Excepción: " + e.getMessage());
            mostrarMensaje("No hay anuncios","No hay Anuncios","No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
            textdESCRIPCION.setText("");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg" );
            String url = destino.toURI().toString();
            Image image= new Image(url);
            imgAnuncios.setImage(image);
        }

    }

    @FXML
    void SiguienteAnuncio(MouseEvent event) throws IOException {
        listaAnuncion.clear();
        listaAnuncion.addAll(controllerPujasAnuncio.cargarAnuncion());
        listaPujasDto.clear();
        listaPujasDto.addAll(controllerPujasAnuncio.cargarPujas());

        tablePujas.setItems(listaPujasDto);
        try {
            if (listaAnuncion.isEmpty()) {
                throw new ArraylistVacioException("El ArrayList está vacío");
            }

            contador++;
            if(contador==listaAnuncion.size()){
                contador=0;

            }
            textdESCRIPCION.setText("Nombre Anuncio: "+listaAnuncion.get(contador).nombre()+"\n"+"Nombre Producto: "+listaAnuncion.get(contador).nombreProdcuto()+" codigo: "+listaAnuncion.get(contador).codigo() +" Anunciante: "+listaAnuncion.get(contador).nombreAnunciante()+ "\n"+"Fecha Inicial: " +listaAnuncion.get(contador).fechaInicio()+"- Fecha Finalizacion: "+listaAnuncion.get(contador).fechaFinalizacion());

            Image image2=new Image(listaAnuncion.get(contador).url());
            imgAnuncios.setImage(image2);



        } catch (ArraylistVacioException e) {
            System.out.println("Excepción: " + e.getMessage());
            mostrarMensaje("No hay anuncios","No hay Anuncios","No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
            textdESCRIPCION.setText("");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg" );
            String url = destino.toURI().toString();
            Image image= new Image(url);
            imgAnuncios.setImage(image);
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

    private ObservableList<Puja> listaPujasDto;
    @FXML
    void elminar(ActionEvent event) throws IOException {
        controllerPujasAnuncio.eliminarAnuncio(listaAnuncion.get(contador));
        listaAnuncion.clear();
        listaAnuncion.addAll(controllerPujasAnuncio.cargarAnuncion());

    }

    @FXML
    void vender(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerPujasAnuncio=new ControllerPujasAnuncio();
        listaAnuncion= FXCollections.observableArrayList();
        listaPujasDto=FXCollections.observableArrayList();
        try {
            listaPujasDto.addAll(controllerPujasAnuncio.cargarPujas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        columCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columOferta.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOferta())));
        columOfertaIni.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOfertaInicial())));
        columPujador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreComprador()));

        tablePujas.getItems().clear();


    }
    private void registrarAcciones(String mensaje, int nivel, String accion) {
        controllerPujasAnuncio.registrarAcciones(mensaje, nivel, accion);

    }
}
