

package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.excepciones.ImportarImagenException;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.PersistenciaArchivosTxtException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.SubastaQuindio;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.tipoArticulo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerProducto;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerProductoView implements Initializable {
    SubastaQuindio subastaQuindio = new SubastaQuindio();
//++++++++++++++++++++++++++++++Seccion producto+++++++++++++++++++++++++++++++++++++++++++++++++
    //-----------------------------atributo globales
    //listas
    private final ObservableList<ProductoDto> productos = FXCollections.observableArrayList();

    private final ObservableList<AnuncianteDto> anunciante = FXCollections.observableArrayList();
    ControllerProducto ControllerProducto;
    //---------------------------------atributos fxml
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnImportar;
    @FXML
    private ComboBox<tipoArticulo> comboTipoProducto;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Pane paneBotones;
    @FXML
    private Pane paneCampos;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Pane paneTable;
    @FXML
    private TableView<ProductoDto> tableDatos;
    @FXML
    private TableColumn<ProductoDto, String> columnaUno;
    @FXML
    private TableColumn<ProductoDto, String> columnaDos;
    @FXML
    private TableColumn<ProductoDto, String> columnaTres;
    @FXML
    private TableColumn<ProductoDto, String> columnaCuatro;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtUrlFoto;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++INITIALIZABLE+++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inicio de combox
        comboTipoProducto.getItems().addAll(tipoArticulo.values());


        //intancia del controler
        ControllerProducto = new ControllerProducto();
        try {
            productos.addAll(ControllerProducto.obtenerListaProductosTxt());
        } catch (IOException e) {
            registrarAcciones("Se lanzo Exceptio Persistencia de archivos txt en controoller anuncio, metodo initializable", 2, "se lanzo exception");

            throw new PersistenciaArchivosTxtException("error al obtener la lista e productos desde el archivo de texto", e);
        }
        //llamar metodo para poder traer la informaciond e producto seleccionado
        listenerSelection();
        //para que no puedan moodificar la url de forma manual
        txtUrlFoto.setDisable(true);


        columnaUno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnaDos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        columnaTres.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().tipo_Articulo())));
        columnaCuatro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urlFoto()));

        tableDatos.getItems().clear();
        tableDatos.setItems(productos);
        registrarAcciones("inicio aplicion", 1, "Se inicializo aplicacion");


    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++seccion producto+++++++++++++++++++++++++++++++++++++++++


    //-----------------------------------actualizar
    @FXML
    void Actualizar(ActionEvent event) throws IOException {
        if (ControllerProducto.obtenerAutenticacion()) {
            ProductoDto productoDto = construirProductoDto();
            if (datosValidos()) {
                if (!(ControllerProducto.verificarProductoCreado(productoDto))) {
                    productos.clear();
                    productos.addAll(ControllerProducto.actualizarProducto(productoDto));
                } else {
                    mostrarMensaje("Producto no encontrado", "no se puede actualizar", "el producto no se pudo actualizar \n verifique que si esta selecionando un prodcuto\n existente", Alert.AlertType.WARNING);
                    registrarAcciones("Error al actualizar", 1, "no se encontro el producto");

                }
            }
            limpiarCamposProducto();
            txtNombreProducto.setDisable(false);
            registrarAcciones("Producto actualizado", 1, "Producto actualizado");
        } else {

            mostrarMensaje("Autenticarse", "Usuario no autnticado", "Debe registrarse para ser atutenticado\npara poder actualizar el producto", Alert.AlertType.ERROR);
            //estoy tratando de abrir otras ventanas para poder navegar

            registrarAcciones("Error al actualizar debe autenticarse", 1, "no hubo un registro");

        }


    }

    //------------------------------------------------------agregar
    @FXML
    void agregar(ActionEvent event) throws IOException {
        if (ControllerProducto.obtenerAutenticacion()) {
            if (datosValidos()) {
                ProductoDto productoDto = construirProductoDto();
                if (verificarProducto(productoDto)) {
                    ControllerProducto.guardarProducto(productoDto);
                    ControllerProducto.guardarNombreP(productoDto.nombreProducto());
                    productos.add(productoDto);
                    limpiarCamposProducto();
                    txtNombreProducto.setDisable(false);
                    registrarAcciones("Producto agregado", 1, "Producto fue agregado");

                } else {

                    mostrarMensaje("producto existente ", "Producto", "El producto ya fue creado, cambie el nombre o elimine el producto", Alert.AlertType.INFORMATION);
                    registrarAcciones("Error al agregar", 1, "producto ya creado");

                }
            }
        } else {
            mostrarMensaje("Autenticarse", "Usuario no autnticado", "Debe registrarse para ser atutenticado\npara poder guardar el producto", Alert.AlertType.ERROR);
            registrarAcciones("Error al agregar debe autenticarse", 1, "no hubo un registro");

        }


    }

    //------------------------------verifcar SI el producto fue creado
    public boolean verificarProducto(ProductoDto productoDto) throws IOException {
        registrarAcciones("Se verifico producto", 1, "Producto verificado");
        return ControllerProducto.verificarProductoCreado(productoDto);

    }

    //-----------------------------------------------------------------ELIMINAR
    @FXML
    void eliminar(ActionEvent event) throws IOException {
        if (ControllerProducto.obtenerAutenticacion()) {
            ProductoDto productoDto = construirProductoDto();
            productos.clear();
            productos.addAll(ControllerProducto.eliminarProdcuto(productoDto));
            limpiarCamposProducto();
            txtNombreProducto.setDisable(false);
            registrarAcciones("Producto eliminado", 1, "Producto fue elimiando");
        } else {
            mostrarMensaje("Autenticarse", "Usuario no autnticado", "Debe registrarse para ser atutenticado\npara poder eliminar el producto", Alert.AlertType.ERROR);
            registrarAcciones("Error al eliminar debe autenticarse", 1, "no hubo un registro");

        }

    }

    //----------------------------------------------IMPORTAR--

    @FXML
    void importar(ActionEvent event) throws ImportarImagenException {

        if (ControllerProducto.obtenerAutenticacion()) {

            FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imágenes");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());

            if (selectedFiles != null) {
                for (File file : selectedFiles) {
                    // Copia o mueve el archivo a la ubicación deseada en tu proyecto
                    try {
                        File destino = new File("src/main/resources/co/edu/uniquindo/proyectosubastaquindio/imagenes/" + file.getName());
                        Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Ruta del archivo: " + destino.getAbsolutePath());

                        // Convierte la ruta del archivo a una URL válida
                        String url = destino.toURI().toString();

                        // Carga la imagen en el ImageView
                        Image image = new Image(url);
                        imgFoto.setImage(image);
                        txtUrlFoto.setText(url);
                        registrarAcciones("Imagen importada", 1, "Importación exitosa");

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al importar imagen: " + e);
                        registrarAcciones("Error al importar imagen ", 1, "Error al importar imagen");
                        throw new ImportarImagenException("Error al importar Imagen", e);
                        // Aquí puedes manejar errores si la copia falla
                    }
                }
            }
        } else {
        mostrarMensaje("Autenticarse", "Usuario no autnticado", "Debe registrarse para ser atutenticado\npara poder eliminar el producto", Alert.AlertType.ERROR);
            registrarAcciones("Error al cargar producto en anuncio debe autenticarse", 1, "no hubo un registro");

    }
    }


    //___------------------------------para sleccionar un productoe n la tabla----------------------
    private void listenerSelection() {
        tableDatos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ProductoDto productoSeleccionado = newSelection;
            mostrarInformacionProdcuto(productoSeleccionado);
            txtNombreProducto.setDisable(true);
            registrarAcciones("Producto seleccionado", 1, "Producto seleccionado");

        });
    }

    //----------------mostrar informacion campos de texto_-------------------------------------------
    private void mostrarInformacionProdcuto(ProductoDto productoSelecionado) {
        if (productoSelecionado != null) {
            txtNombreProducto.setText(productoSelecionado.nombreProducto());
            txtDescripcion.setText(productoSelecionado.descripcion());
            txtUrlFoto.setText(productoSelecionado.urlFoto());
            comboTipoProducto.setValue(productoSelecionado.tipo_Articulo());
            Image image = new Image(productoSelecionado.urlFoto());
            imgFoto.setImage(image);
            registrarAcciones("se muestra Informacion del  Producto seleccionado", 1, "Se muestra la informacion del producto seleccionado");


        }
    }


    //------------------------------codigo reutilizable---------------------------------------
    //limpiar campos de texto
    private void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtUrlFoto.setText("");
        txtDescripcion.setText("");
        imgFoto.setImage(null);
        comboTipoProducto.setPromptText("combo tipo prodcuto");
        registrarAcciones("limpiar campos", 1, "campos limpiados");


    }

    //validar que no esten en campos nulls o vacios
    private boolean datosValidos() {
        String mensaje = "";
        if (txtNombreProducto.getText() == null || txtNombreProducto.getText().equals(""))
            mensaje += "El campo del nombre debe rellnarlo  \n";
        if (txtDescripcion.getText() == null || txtDescripcion.getText().equals(""))
            mensaje += "El campo de la descripcion debe rellenarlo \n";
        if (txtUrlFoto.getText() == null || txtUrlFoto.getText().equals(""))
            mensaje += "El campo de url debe rellenarlo \n";
        if (comboTipoProducto.getValue() == null)
            mensaje += "Debe escojer un tipo de producto \n";
        if (mensaje.equals("")) {
            return true;

        } else {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            registrarAcciones("datos no validos", 1, "datos invalidos");
            return false;
        }
    }


    //*****************************************************GESTION ANUNCIO**************************************************

//++++++++++++++++++++++++++++++++++Inicio+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++DTOS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //EMPLEADO
    private ProductoDto construirProductoDto() {
        registrarAcciones("se creo ProductoDTO", 1, "se creo ProductoDTO");
        return new ProductoDto(
                txtNombreProducto.getText(),
                txtDescripcion.getText(),
                comboTipoProducto.getValue(),
                txtUrlFoto.getText()

        );

    }


//+++++++++++++++++++++++++++++++++++++++++MOSTRAR MENSAJE++++++++++++++++++++++++++++++++++++++++++

    //enviamos un mensaje
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
        registrarAcciones("se mostro mensaje", 1, "mostrar mensaje");
    }

    //+++++++++++++++++++++++++++++++log++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        ControllerProducto.registrarAcciones(mensaje, nivel, accion);

    }


}







