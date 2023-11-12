package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerAnuncio;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.FormatoInvalidoException;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.PersistenciaArchivosTxtException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncioDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Anunciante;
import co.edu.uniquindo.proyectosubastaquindio.model.Anuncio;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoEstado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ControllerAnuncioView implements Initializable {
    private ControllerAnuncio controllerAnuncio;
    private final ObservableList<ProductoDto> productos = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> comboSeleccionProducto;
    ObservableList<String> nuevosProductos;
    private ProductoDto productoDto;
    private AnuncioDto anuncioDto;

    LocalDate fechaInicio;
    LocalDate fechaFinalizacion;
    private AnuncianteDto anuncianteDto;
    private   ObservableList<AnuncioDto>listaAnuncios=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerAnuncio = new ControllerAnuncio();

        try {
            nuevosProductos = FXCollections.observableArrayList(controllerAnuncio.obtenerNombresProducto());
        } catch (IOException e) {
            registrarAcciones("Se lanzo Exceptio Persistencia de archivos txt en controoller anuncio, metodo initializable", 2, "se lanzo exception");

            throw new PersistenciaArchivosTxtException("Error al acargar archivo informacion desde eel archivo txt", e);
        }
        comboSeleccionProducto.setItems(nuevosProductos);


    }

    @FXML
    private Button btnPublicar;

    @FXML
    private ImageView imgFotoAnuncio;
    //FLATAN ATRIBUTOS FXL POR DEFINIR


    @FXML
    void publicarAnuncio(ActionEvent event) throws IOException {
        if(controllerAnuncio.obtenerAutenticacion()) {
            if (validarCampos()) {
                if (controllerAnuncio.verificarAnuncios(anuncioDto)) {
                    controllerAnuncio.guardarAnuncio(anuncioDto);
                    mostrarMensaje("Anuncio" + anuncioDto.nombre(), "se publico", "Se publico anuncio" + anuncioDto.nombre(), Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Anuncio" + anuncioDto.nombre(), "se publico", "Se publico anuncio" + anuncioDto.nombre(), Alert.AlertType.INFORMATION);
                }
            }
        }

    }

    @FXML
    void cargarProductos(MouseEvent event) throws IOException {
        nuevosProductos.setAll(controllerAnuncio.obtenerNombresProducto());

    }

    @FXML
    private TableColumn <AnuncioDto,String> columna1AnuncioTabla3;
    @FXML
    private TableColumn <AnuncioDto,String> columna2AnuncioTabla3;
    @FXML
    private TableColumn<AnuncioDto,String> columnaCodigo;
    @FXML
    private TableView<AnuncioDto> tablaInfoFecha;

    @FXML
    void mostrarEnTabla(ActionEvent event) throws IOException {
        //sirve para daber si seleccionaron algo en el combo
        if(controllerAnuncio.obtenerAutenticacion()) {
            listaAnuncios.clear();
            String selectedItem = comboSeleccionProducto.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                productoDto = controllerAnuncio.buscarProducto(selectedItem);
                ObservableList<ProductoDto> productoSeleccionado = FXCollections.observableArrayList();
                productoSeleccionado.add(productoDto);

                // solo falta que el prooducto dto que esta arriba comenzar a mostar los atribustos en la tabla

                //String productoSeleccionado = selectedItem;

                columna1AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
                columna2AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
                columna3AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().tipo_Articulo())));

                //mostrar en  tabla anunciante

                anuncianteDto = controllerAnuncio.obtenerAnuncianteGlobal();
                ObservableList<AnuncianteDto> anuncianteDtos = FXCollections.observableArrayList();
                anuncianteDtos.add(anuncianteDto);
                columna1AnuncioTabla2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
                columna2AnuncioTabla2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
                //CANTIDAD DE ANUNCION ACTIVOS O NUMERO PUBLICACIONES?
                columna3AnuncioTabla2.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().cantAnunciosActivos())));

                tableAnuncio2.getItems().clear();
                tableAnuncio2.setItems(anuncianteDtos);

                // Supongamos que productoDto.urlFoto() devuelve la URL de la imagen como una cadena
                String urlImagen = productoDto.urlFoto();


                // Carga la imagen en el ImageView
                Image image = new Image(urlImagen);
                imgFotoAnuncio.setImage(image);
                //columna4AnuncioTabla1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urlFoto()));

                tablaAnuncio1.getItems().clear();
                tablaAnuncio1.setItems(productoSeleccionado);



                    fechaInicio = LocalDate.now();
                    fechaFinalizacion = fechaInicio.plusDays(3);

                    anuncioDto = construirAnuncioDto();
                    listaAnuncios.add(anuncioDto);
                    //mostrar fechas

                    columna1AnuncioTabla3.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaInicio())));
                    columna2AnuncioTabla3.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().fechaFinalizacion())));
                    columnaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().codigo())));
                    tablaInfoFecha.setItems(listaAnuncios);



            } else {
                System.out.println("Ningún elemento seleccionado.");
            }
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




    private void registrarAcciones(String mensaje, int nivel, String accion) {
        controllerAnuncio.registrarAcciones(mensaje, nivel, accion);

    }
    //----------------------------AnuncioDtp--------------------------------------------
public AnuncioDto construirAnuncioDto(){
        return  new AnuncioDto(
                txtNombreAnuncio.getText(),
                generarCodigo(),
                TipoEstado.ACTIVO,
                fechaInicio,
                fechaFinalizacion,
               Float.parseFloat( txtValorInicial.getText()),
                productoDto.nombreProducto(),
                anuncianteDto.nombre()
        );



    }
    private Set<String> codigosGenerados = new HashSet<>();
    private String generarCodigo() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        // Generar 6 caracteres (letras y números) para el código
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

        // Verificar si el código ya ha sido generado
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

        // Agregar el código a la lista de códigos generados
        codigosGenerados.add(codigo.toString());

        return codigo.toString();
    }
    @FXML
    private TextField txtNombreAnuncio;

    @FXML
    private TextField txtValorInicial;
    public boolean validarCampos() {
        String mensaje = "";
        if (txtNombreAnuncio.getText() == null || txtNombreAnuncio.getText().equals(""))
            mensaje += "El campo nombre debe rellenarlo  \n";

        if (txtValorInicial.getText() == null || txtValorInicial.getText().equals("")) {
            mensaje += "El campo de edad debe rellenarlo \n";
        } else {
            try {
                Integer.parseInt(txtValorInicial.getText());
            } catch (NumberFormatException e) {
                registrarAcciones("Se lanzo Exceptio formato invalido Exception, en controller anuncio ", 2, "se lanzo exception");
                throw new FormatoInvalidoException("Error formato de campo numerico invalido", e);
            }
        }
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            registrarAcciones("Noficiacion cliente", 1, "datos invalidos");
            return false;
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


}
