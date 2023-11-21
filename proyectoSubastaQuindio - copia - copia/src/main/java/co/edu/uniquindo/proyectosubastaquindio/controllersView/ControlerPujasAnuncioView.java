package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerPujasAnuncio;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.ArraylistVacioException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Anuncio;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstadoPuja;
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
import java.time.LocalDate;
import java.util.ArrayList;
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
    private ObservableList<AnuncioDto> listaAnuncion;
    private ControllerPujasAnuncio controllerPujasAnuncio;
    private ObservableList<Puja> auxiliar = FXCollections.observableArrayList();
    private Puja puja;
    private ArrayList<Puja> listPujasAUxi;
    private int contador;
    private ObservableList<Puja> listaPujasDto;
    private ObservableList<AnuncioDto>listAnunciosAux=FXCollections.observableArrayList();
    private ArrayList<Anuncio>Anun=new ArrayList<>();
   private LocalDate fechaInicio ;
   private LocalDate fechaFinalizacion ;


    @FXML
    void AnteriorAnuncio(MouseEvent event) throws IOException {
        if(controllerPujasAnuncio.obtenerAutenicacion()) {
            listaAnuncion.clear();
            listAnunciosAux.clear();
            listAnunciosAux.addAll(controllerPujasAnuncio.cargarAnuncion());
            listaAnuncion.addAll(filtrarAnuncios());
            try {
                if (listaAnuncion.isEmpty()) {
                    throw new ArraylistVacioException("El ArrayList está vacío");
                }

                contador--;
                if (contador < 0) {
                    contador = listaAnuncion.size() - 1;

                }

                listaPujasDto.clear();
                auxiliar.clear();
                textdESCRIPCION.setText("Nombre Anuncio: " + listaAnuncion.get(contador).nombre() + "\n" + "Nombre Producto: " + listaAnuncion.get(contador).nombreProdcuto() + " codigo: " + listaAnuncion.get(contador).codigo() + " Anunciante: " + listaAnuncion.get(contador).nombreAnunciante() + "\n" + "Fecha Inicial: " + listaAnuncion.get(contador).fechaInicio() + "- Fecha Finalizacion: " + listaAnuncion.get(contador).fechaFinalizacion());

                Image image2 = new Image(listaAnuncion.get(contador).url());
                imgAnuncios.setImage(image2);
                auxiliar.addAll(controllerPujasAnuncio.cargarPujas());
                listaPujasDto.addAll(relacionarPujas());
                tablePujas.setItems(listaPujasDto);


            } catch (ArraylistVacioException e) {
                System.out.println("Excepción: " + e.getMessage());
                mostrarMensaje("No hay anuncios", "No hay Anuncios", "debe publicar \nanuncios", Alert.AlertType.INFORMATION);
                textdESCRIPCION.setText("");
                File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
                String url = destino.toURI().toString();
                Image image = new Image(url);
                imgAnuncios.setImage(image);
            }
        }else {
            textdESCRIPCION.setText("");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
            String url = destino.toURI().toString();
            Image image = new Image(url);
            imgAnuncios.setImage(image);
            mostrarMensaje("Inice sesion","Iniciar sesion","debe iniciarSesion para mostrar\nRelacionado con su usario", Alert.AlertType.INFORMATION);
        }

    }

    public ArrayList<Puja> relacionarPujas() {
        ArrayList<Puja> lista = new ArrayList<>();
        for (Puja puja : auxiliar) {
            if (listaAnuncion.get(contador).nombre().equals(puja.getNombreAnuncio())) {
                lista.add(puja);
            }
        }

        return lista;
    }

    @FXML
    void SiguienteAnuncio(MouseEvent event) throws IOException {
        if(controllerPujasAnuncio.obtenerAutenicacion()) {
            listaAnuncion.clear();
            listAnunciosAux.clear();
            listAnunciosAux.addAll(controllerPujasAnuncio.cargarAnuncion());
            listaAnuncion.addAll(filtrarAnuncios());

            try {
                if (listaAnuncion.isEmpty()) {
                    throw new ArraylistVacioException("El ArrayList está vacío");
                }

                contador++;
                if (contador == listaAnuncion.size()) {
                    contador = 0;

                }

                listaPujasDto.clear();
                auxiliar.clear();
                textdESCRIPCION.setText("Nombre Anuncio: " + listaAnuncion.get(contador).nombre() + "\n" + "Nombre Producto: " + listaAnuncion.get(contador).nombreProdcuto() + " codigo: " + listaAnuncion.get(contador).codigo() + " Anunciante: " + listaAnuncion.get(contador).nombreAnunciante() + "\n" + "Fecha Inicial: " + listaAnuncion.get(contador).fechaInicio() + "- Fecha Finalizacion: " + listaAnuncion.get(contador).fechaFinalizacion());

                Image image2 = new Image(listaAnuncion.get(contador).url());
                imgAnuncios.setImage(image2);
                auxiliar.addAll(controllerPujasAnuncio.cargarPujas());
                listPujasAUxi = controllerPujasAnuncio.cargarPujas();
                listaPujasDto.addAll(relacionarPujas());

                tablePujas.setItems(listaPujasDto);


            } catch (ArraylistVacioException e) {
                System.out.println("Excepción: " + e.getMessage());
                mostrarMensaje("No hay anuncios", "No hay Anuncios", "debe publicar \nanuncios", Alert.AlertType.INFORMATION);
                textdESCRIPCION.setText("");
                File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
                String url = destino.toURI().toString();
                Image image = new Image(url);
                imgAnuncios.setImage(image);
            }

        }else {
            textdESCRIPCION.setText("");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
            String url = destino.toURI().toString();
            Image image = new Image(url);
            imgAnuncios.setImage(image);
            mostrarMensaje("Inice sesion","Iniciar sesion","debe iniciarSesion para mostrar\nRelacionado con su usario", Alert.AlertType.INFORMATION);

        }

    }
    public ArrayList<AnuncioDto> filtrarAnuncios() throws IOException {
        ArrayList<AnuncioDto> lista = new ArrayList<>();
        for (int i = 0; i < listAnunciosAux.size(); i++) {
            if(listAnunciosAux.get(i).nombreAnunciante().equals(controllerPujasAnuncio.obtenerAnunciante().nombre())){
                lista.add(listAnunciosAux.get(i));
            }
        }

        return lista;

    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
        registrarAcciones("se mostro mensaje", 1, "mostrar mensaje");
    }


    @FXML
    void elminar(ActionEvent event) throws IOException {
        if (controllerPujasAnuncio.obtenerAutenicacion()) {
            controllerPujasAnuncio.eliminarAnuncio(listaAnuncion.get(contador));
            listaAnuncion.clear();
            listaAnuncion.addAll(controllerPujasAnuncio.cargarAnuncion());
            try {
                if (listaAnuncion.isEmpty()) {
                    registrarAcciones("execpcion lazanda por arralis vacio en controller pujasAnuncioView", 1, "array vacio");

                    throw new ArraylistVacioException("El ArrayList está vacío");
                }

                contador--;
                if (contador < 0) {
                    contador = listaAnuncion.size() - 1;

                }
                textdESCRIPCION.setText("Nombre Anuncio: " + listaAnuncion.get(contador).nombre() + "\n" + "Nombre Producto: " + listaAnuncion.get(contador).nombreProdcuto() + " codigo: " + listaAnuncion.get(contador).codigo() + " Anunciante: " + listaAnuncion.get(contador).nombreAnunciante() + "\n" + "Fecha Inicial: " + listaAnuncion.get(contador).fechaInicio() + "- Fecha Finalizacion: " + listaAnuncion.get(contador).fechaFinalizacion());

                Image image2 = new Image(listaAnuncion.get(contador).url());
                imgAnuncios.setImage(image2);
                auxiliar.addAll(controllerPujasAnuncio.cargarPujas());
                listPujasAUxi = controllerPujasAnuncio.cargarPujas();
                listaPujasDto.addAll(relacionarPujas());
                tablePujas.setItems(listaPujasDto);
                registrarAcciones("anuncio eliminado", 1, "anuncio Eliminado");
                mostrarMensaje("Anuncio eliminado", "anuncio eliminado", "El anuncio fue eliminado", Alert.AlertType.INFORMATION);


            } catch (ArraylistVacioException e) {
                System.out.println("Excepción: " + e.getMessage());
                textdESCRIPCION.setText("");
                File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
                String url = destino.toURI().toString();
                Image image = new Image(url);
                imgAnuncios.setImage(image);
                listaPujasDto.clear();
                tablePujas.setItems(listaPujasDto);
            }
        }else{
            mostrarMensaje("debe autenticarse","debe autenticarse","debe autenticarse", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void vender(ActionEvent event) throws IOException {
        if (controllerPujasAnuncio.obtenerAutenicacion()) {
            if (puja != null) {
                puja.setTipoEstadoPuja(TipoEstadoPuja.GANADA);
                for (int i = 0; i < listPujasAUxi.size(); i++) {
                    if (puja.getCodigo().equals(listPujasAUxi.get(i).getCodigo())) {
                        listPujasAUxi.remove(i);
                        listPujasAUxi.add(puja);
                        mostrarMensaje("producto vendido", "producto vendido", "El producto fue vendido, \nse le noticara al pujador", Alert.AlertType.INFORMATION);
                        registrarAcciones("puja vendida", 1, "puja vendida");
                        break;
                    }
                }
                controllerPujasAnuncio.guardarPujas(listPujasAUxi);
            } else {
                mostrarMensaje("no hay puja seleccionada", "no hay puja seleccionada", "no hay puja seleccionada, seleccione una puja", Alert.AlertType.INFORMATION);
                registrarAcciones("no hay puja selcionada", 1, "selecionar puja");
            }
        }
        else {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerPujasAnuncio=new ControllerPujasAnuncio();
        fechaInicio = LocalDate.now();
//anuncios
        try {
            Anun=controllerPujasAnuncio.cargarAnuncios();
            for (Anuncio anuncioDto:Anun) {
                if(anuncioDto.getFechaFinalizacion().equals(fechaInicio)){
                    anuncioDto.setEstado(TipoEstado.INACTIVO);
                }
            }
            controllerPujasAnuncio.guardarAnuncios(Anun);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listenerSelection();
        listPujasAUxi = new ArrayList<>();


        controllerPujasAnuncio = new ControllerPujasAnuncio();
        listaAnuncion = FXCollections.observableArrayList();
        listaPujasDto = FXCollections.observableArrayList();
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
        registrarAcciones("controller PujasAnunciosView inicalizasdo", 1, "inicializacion");

    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        controllerPujasAnuncio.registrarAcciones(mensaje, nivel, accion);

    }

    private void listenerSelection() {
        tablePujas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            puja = newSelection;
            registrarAcciones("puja seleccionada", 1, "puja seleccionado");

        });
    }


}
