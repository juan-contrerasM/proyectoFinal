package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerInicio;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.ArraylistVacioException;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.CamposInvalidosException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.*;
import co.edu.uniquindo.proyectosubastaquindio.model.Puja;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstadoPuja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerInicioView implements Initializable {
    private ObservableList<AnuncioDto> listaAnuncion;
    private int contador = 0;
    private ControllerInicio controllerInicio;
    @FXML
    private ImageView imagenAnterior;
    @FXML
    private ImageView imagenSiguiente;
    @FXML
    private Button btnHacerPuja;
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
    private AnuncioDto anuncioDto;
    private CompradorDto compradorDto;
    @FXML
    private TextField txtValorPuja;
    private Set<String> codigosGenerados = new HashSet<>();
    private int pujas = 0;

    @FXML
    void hacerPuja(ActionEvent event) {
        if (controllerInicio.obtenerAutenticacion()) {
            try {
                if (txtValorPuja.getText() == null || txtValorPuja.getText().isEmpty()) {
                    throw new CamposInvalidosException("El valor de la puja es nulo o vacío");
                }
                if (Float.parseFloat(txtValorPuja.getText()) > anuncioDto.valorInicial()) {
                    mostrarMensaje("Puja invalida", "puja invalida", "puja invalida debe ser mayor\n del valorinicla ques es: " + anuncioDto.valorInicial(), Alert.AlertType.INFORMATION);
                } else {
                    float valorPuja = Float.parseFloat(txtValorPuja.getText());
                    anuncioDto = listaAnuncion.get(contador);
                    compradorDto = controllerInicio.obtenerComprador();
                    Puja puja = new Puja(generarCodigo(), anuncioDto.valorInicial(), valorPuja, compradorDto.nombre(), anuncioDto.nombreAnunciante(), anuncioDto.nombre(), anuncioDto.url());
                    puja.setTipoEstadoPuja(TipoEstadoPuja.PERDIDA);
                    controllerInicio.guardarPujar(puja);

                    mostrarMensaje("Puja con exito", "Puja realizada", "Usted ha pujado por el producto:\n" + anuncioDto.nombreProdcuto(), Alert.AlertType.INFORMATION);
                }
            } catch (CamposInvalidosException e) {
                registrarAcciones("exception lanzada,campos invalidos en controllerInicioView, metodo hacer puja", 2, "hacerPuja");
                mostrarMensaje("campo vacio", "los campos deben llenarse", "campos vacios", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            mostrarMensaje("Autenticarse", "Usuario no autenticado", "Debe registrarse para ser autenticado\npara poder actualizar el producto", Alert.AlertType.ERROR);
            registrarAcciones("Error al selecionar debe autenticarse", 1, "no hubo un registro");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerInicio = new ControllerInicio();
        listaAnuncion = FXCollections.observableArrayList();
        try {
            listaAnuncion.addAll(controllerInicio.cargarAnuncion());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void siguienteAnuncio(MouseEvent mouseEvent) throws IOException {
        listaAnuncion.addAll(controllerInicio.cargarAnuncion());
        try {
            if (listaAnuncion.isEmpty()) {
                throw new ArraylistVacioException("El ArrayList está vacío");
            }
            contador++;
            if (contador == listaAnuncion.size()) {
                contador = 0;
            }
            if (listaAnuncion.get(contador).estado().equals(TipoEstado.ACTIVO)) {
                textArea.setText("Nombre Anuncio: " + listaAnuncion.get(contador).nombre() + "\n" + "Nombre Producto: " + listaAnuncion.get(contador).nombreProdcuto() + " codigo: " + listaAnuncion.get(contador).codigo() + " Anunciante: " + listaAnuncion.get(contador).nombreAnunciante() + "\n" + "Fecha Inicial: " + listaAnuncion.get(contador).fechaInicio() + "- Fecha Finalizacion: " + listaAnuncion.get(contador).fechaFinalizacion());
                txtValorPuja.setText(listaAnuncion.get(contador).valorInicial() + "");
                Image image2 = new Image(listaAnuncion.get(contador).url());
                imgAnuncio.setImage(image2);
            }
        } catch (ArraylistVacioException e) {
            System.out.println("Excepción: " + e.getMessage());
            mostrarMensaje("No hay anuncios", "No hay Anuncios", "No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
            textArea.setText("");
            txtValorPuja.setText("0000");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
            String url = destino.toURI().toString();
            Image image = new Image(url);
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
            if (contador < 0) {
                contador = listaAnuncion.size() - 1;
            }
            if (listaAnuncion.get(contador).estado().equals(TipoEstado.ACTIVO)) {
                textArea.setText("Nombre Anuncio: " + listaAnuncion.get(contador).nombre() + "\n" + "Nombre Producto: " + listaAnuncion.get(contador).nombreProdcuto() + " codigo: " + listaAnuncion.get(contador).codigo() + " Anunciante: " + listaAnuncion.get(contador).nombreAnunciante() + "\n" + "Fecha Inicial: " + listaAnuncion.get(contador).fechaInicio() + "- Fecha Finalizacion: " + listaAnuncion.get(contador).fechaFinalizacion());
                txtValorPuja.setText(listaAnuncion.get(contador).valorInicial() + "");
                Image image2 = new Image(listaAnuncion.get(contador).url());
                imgAnuncio.setImage(image2);
            }
        } catch (ArraylistVacioException e) {
            System.out.println("Excepción: " + e.getMessage());
            mostrarMensaje("No hay anuncios", "No hay Anuncios", "No hay anuncios, debe esperear\nha que publiquen", Alert.AlertType.INFORMATION);
            textArea.setText("");
            txtValorPuja.setText("0000");
            File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/logoFinal - copia.jpg");
            String url = destino.toURI().toString();
            Image image = new Image(url);
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


    private String generarCodigo() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        while (codigo.length() < 6) {
            char caracter = (char) (random.nextInt(26) + 'A');
            if (random.nextBoolean()) {
                // Agregar una letra mayúscula
                codigo.append(caracter);
            } else {
                // Agregar un número
                codigo.append(random.nextInt(10));
            }
        }
        while (codigosGenerados.contains(codigo.toString())) {
            codigo = new StringBuilder();
            // Volver a generar el código si ya existe
            while (codigo.length() < 6) {
                char caracter = (char) (random.nextInt(26) + 'A');
                if (random.nextBoolean()) {
                    codigo.append(caracter);
                } else {
                    codigo.append(random.nextInt(10));
                }
            }
        }
        codigosGenerados.add(codigo.toString());
        return codigo.toString();
    }
}