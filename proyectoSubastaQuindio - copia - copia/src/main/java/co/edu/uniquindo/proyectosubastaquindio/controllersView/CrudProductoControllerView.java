

package co.edu.uniquindo.proyectosubastaquindio.controllersView;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.Producto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import co.edu.uniquindo.proyectosubastaquindio.controller.crudProductoController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class CrudProductoControllerView implements Initializable {

//++++++++++++++++++++++++++++++Seccion producto+++++++++++++++++++++++++++++++++++++++++++++++++
    //-----------------------------atributo globales

    //listas
    private final ObservableList<ProductoDto> productos = FXCollections.observableArrayList();


    crudProductoController crudProductoController ;
    private boolean autenticacion= false;


    //---------------------------------atributos fxml

    @FXML
    private DatePicker dateFechaFinal;

    @FXML
    private DatePicker dateFechaInical;

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
    private Label lblDescripcion;



    @FXML
    private Label lblNombreProducto;

    @FXML
    private Label lblTipoProducto;

    @FXML
    private Label lblUrlFoto;



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
    private TextField txtNombreAnunciante;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtUrlFoto;

    @FXML
    private TextField txtValor;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++INITIALIZABLE+++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inicio de combox
        comboTipoProducto.getItems().addAll(tipoArticulo.values());
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());
        comboTipo.getItems().addAll(TipoUsuario.values());
        //intancia del controler
        crudProductoController=new crudProductoController();
        //llamar metodo para poder traer la informaciond e producto seleccionado
        listenerSelection();
        //para que no puedan moodificar la url de forma manual
        txtUrlFoto.setDisable(true);

        try {
            productos.addAll( crudProductoController.obtenerListaProductosTxt());
            for (ProductoDto productoDto:productos) {
                nombresProductos.add(productoDto.nombreProducto());
            }
            comboSeleccion.setItems(nombresProductos);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        columnaUno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnaDos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        columnaTres.setCellValueFactory(cellData -> new SimpleStringProperty (String.valueOf(cellData.getValue().tipo_Articulo())));
        columnaCuatro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().urlFoto()));

        tableDatos.getItems().clear();
        tableDatos.setItems(productos);
        registrarAcciones("inicio aplicion",1, "Se inicializo aplicacion");




    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++seccion producto+++++++++++++++++++++++++++++++++++++++++


    //-----------------------------------actualizar
    @FXML
    void Actualizar(ActionEvent event) throws IOException {
        if(autenticacion) {
            ProductoDto productoDto = construirEmpleadoDto();
            if (datosValidos()) {
                if (!(crudProductoController.verificarProductoCreado(productoDto))) {
                    productos.clear();
                    productos.addAll(crudProductoController.actualizarProducto(productoDto));
                } else {
                    mostrarMensaje("Producto no encontrado", "no se puede actualizar", "el producto no se pudo actualizar \n verifique que si esta selecionando un prodcuto\n existente", Alert.AlertType.WARNING);
                }
            }
            limpiarCamposProducto();
            txtNombreProducto.setDisable(false);
            registrarAcciones("Producto actualizado", 1, "Producto actualizado");
        }else {
            mostrarMensaje("Autenticarse","Usuario no autnticado","Debe registrarse para ser atutenticado\npara poder actualizar el producto", Alert.AlertType.ERROR);
        }


    }

    //------------------------------------------------------agregar
    @FXML
    void agregar(ActionEvent event) throws IOException {
        if (autenticacion){
            if (datosValidos()) {
                ProductoDto productoDto = construirEmpleadoDto();
                if (verificarProducto(productoDto)) {
                    crudProductoController.guardarProducto(productoDto);
                    productos.add(productoDto);
                    nombresProductos.add(productoDto.nombreProducto());
                    limpiarCamposProducto();
                    txtNombreProducto.setDisable(false);
                    registrarAcciones("Producto agregado", 1, "Producto fue agregado");

                } else {
                    mostrarMensaje("producto existente ", "Producto", "El producto ya fue creado, cambie el nombre o elimine el producto", Alert.AlertType.INFORMATION);
                }
            }
    }else {
            mostrarMensaje("Autenticarse","Usuario no autnticado","Debe registrarse para ser atutenticado\npara poder guardar el producto", Alert.AlertType.ERROR);
        }


    }

    //------------------------------verifcar SI el producto fue creado
    public boolean verificarProducto(ProductoDto productoDto) throws IOException {
        registrarAcciones("SE VERIFICO PRODCUTO",1, "Producto verificado");
        return crudProductoController.verificarProductoCreado(productoDto);

    }

    //-----------------------------------------------------------------ELIMINAR
    @FXML
    void eliminar(ActionEvent event) throws IOException {
        if(autenticacion) {
            ProductoDto productoDto = construirEmpleadoDto();
            productos.clear();
            productos.addAll(crudProductoController.eliminarProdcuto(productoDto));
            limpiarCamposProducto();
            txtNombreProducto.setDisable(false);
            nombresProductos.remove(productoDto.nombreProducto());
            registrarAcciones("Producto eliminado", 1, "Producto fue elimiando");
        }else {
            mostrarMensaje("Autenticarse","Usuario no autnticado","Debe registrarse para ser atutenticado\npara poder eliminar el producto", Alert.AlertType.ERROR);
        }

    }

    //----------------------------------------------IMPORTAR--

    @FXML
    void importar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imágenes");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                // Copia o mueve el archivo a la ubicación deseada en tu proyecto
                try {
                    File destino = new File("C:\\Users\\juanj\\OneDrive\\Escritorio\\td\\proyectoSubastaQuindio - copia - copia\\src\\main\\resources\\co\\edu\\uniquindo\\proyectosubastaquindio\\imagenes\\" + file.getName());
                    Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Carga la imagen en el ImageView
                    Image image = new Image(destino.toURI().toString());
                    imgFoto.setImage(image);
                    txtUrlFoto.setText(String.valueOf(destino));
                } catch (IOException e) {
                    e.printStackTrace();
                    // Aquí puedes manejar errores si la copia falla
                }
            }
        }
        registrarAcciones("se importo imagen",1, "se impor la imagen");




    }



    //___------------------------------para sleccionar un productoe n la tabla----------------------
    private void listenerSelection() {
        tableDatos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ProductoDto productoSeleccionado = newSelection;
            mostrarInformacionProdcuto(productoSeleccionado);
            txtNombreProducto.setDisable(true);
            registrarAcciones("Producto seleccionado",1, "Producto seleccionado");

        });
    }
//----------------mostrar informacion campos de texto_-------------------------------------------
    private void mostrarInformacionProdcuto(ProductoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txtNombreProducto.setText(empleadoSeleccionado.nombreProducto());
            txtDescripcion.setText(empleadoSeleccionado.descripcion());
            txtUrlFoto.setText(empleadoSeleccionado.urlFoto());
            comboTipoProducto.setValue(empleadoSeleccionado.tipo_Articulo());
            Image image = new Image(empleadoSeleccionado.urlFoto());
            imgFoto.setImage(image);
            registrarAcciones("Mostrar Informacion Producto",1, "Se mostro producto seleccionado");


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
        registrarAcciones("limpiar campos",1,"campos limpiados");


    }

    //validar que no esten en campos nulls o vacios
    private boolean datosValidos() {
        String mensaje = "";
        if (txtNombreProducto.getText() == null || txtNombreProducto.getText() .equals(""))
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
            registrarAcciones("Noficiacion cliente",1, "datos invalidos");
            return false;
        }
    }


    //*****************************************************GESTION ANUNCIO**************************************************
    @FXML
    private Button btnPublicar;
    @FXML
    private ComboBox<String> comboSeleccion;
    @FXML
    private ImageView imgFotoAnuncio;
//FLATAN ATRIBUTOS FXL POR DEFINIR
    ObservableList<String>  nombresProductos = FXCollections.observableArrayList();

    @FXML
    void publicarAnuncio(ActionEvent event) {

    }
    @FXML
    void mostrarEnTabla(ActionEvent event) throws IOException {
        //sirve para daber si seleccionaron algo en el combo
        String selectedItem = comboSeleccion.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ProductoDto productoDto=crudProductoController.buscarProducto(selectedItem);
            // solo falta que el prooducto dto que esta arriba comenzar a mostar los atribustos en la tabla
            System.out.println(productoDto.nombreProducto());
        } else {
            System.out.println("Ningún elemento seleccionado.");
        }

    }




    //++++++++++++++++++++++++++++++++++++++++++++++++++++HOME+++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;

    @FXML
    private PasswordField txtClaveHome;

    @FXML
    private TextField txtUsuarioHome;
    //FLATA ATRIBUTOS XML POR DEFINIR
    @FXML
        void IniciarSesion(ActionEvent event) throws IOException {
                String usuario=txtUsuarioHome.getText();
                String clave=txtClaveHome.getText();

            if(comboTipoUsuario.getValue().equals(TipoUsuario.ANUNCIANTE)) {
                if (crudProductoController.verificarInicioSesionAnunciante(usuario,clave)) {
                    autenticacion = true;
                    limpiarCamposHome();
                    mostrarMensaje("inicio sesion", "se inicio sesion", "Se inicio sesion Correctamente \n ya puede hacer uso \n  de las acciones de la applicacion", Alert.AlertType.CONFIRMATION);
                } else {
                    mostrarMensaje("inicio sesion", "credenciales incorrectas", "no se inicio sesion \n clave o usuario incorrecto", Alert.AlertType.WARNING);
                }
            } else if (comboTipoUsuario.getValue().equals(TipoUsuario.COMPRADOR)) {
                if(crudProductoController.verificarInicioSesionComprador(usuario,clave)){
                    autenticacion=true;
                    limpiarCamposHome();
                    mostrarMensaje("inicio sesion", "se inicio sesion", "Se inicio sesion Correctamente \n ya puede hacer uso \n  de las acciones de la applicacion", Alert.AlertType.CONFIRMATION);
                }else {
                    mostrarMensaje("inicio sesion", "credenciales incorrectas", "no se inicio sesion \n clave o usuario incorrecto", Alert.AlertType.WARNING);
                }

            }

        }
        public void limpiarCamposHome(){
            txtClaveHome.setText("");
            txtUsuarioHome.setText("");
            comboTipo. setPromptText("Tipo de usuario");
        }

        //+++++++++++++++++++++++++++++++++++++++++++++++++REGISTRO++++++++++++++++++++++++++++++++++++++++++++++++++
        @FXML
        private Button btnGuardarUsuario;


    // ESTE NO ES NECESARIO REGISTARR
    @FXML
    private Button btnRegistrar;

    @FXML
    private ComboBox<TipoUsuario> comboTipo;

    @FXML
    private Label lblInformacion;

    @FXML
    private Label lblRegistro;

    @FXML
    private Tab tipoRegistro;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtClave;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;



    @FXML
        void guardarUsuario(ActionEvent event) throws IOException {
            if(datosValidosRegistro()) {
                if (comboTipo.getValue().equals(TipoUsuario.ANUNCIANTE)) {
                    AnuncianteDto anuncianteDto = construirAnuncianteDto();
                    if(crudProductoController.verificarAnuncianteCreado(anuncianteDto)) {
                        crudProductoController.guardarAnunciante(anuncianteDto);
                        limpiarCamposRegistro();
                    }
                } else if (comboTipo.getValue().equals(TipoUsuario.COMPRADOR)) {
                    CompradorDto compradorDto = construirCompradorDto();
                    if (crudProductoController.verificarCompradorCreado(compradorDto)) {
                        crudProductoController.guardarComprador(compradorDto);
                        limpiarCamposRegistro();
                    }
                }
            }


        }
        public boolean datosValidosRegistro(){
            String mensaje = "";
            if (txtNombre.getText() == null || txtNombre.getText() .equals(""))
                mensaje += "El campo nombre debe rellenarlo  \n";
            if (txtApellido.getText() == null || txtApellido.getText().equals(""))
                mensaje += "El campo de la apellido debe rellenarlo \n";
            if (txtClave.getText() == null || txtClave.getText().equals(""))
                mensaje += "El campo de clave debe rellenarlo \n";
            if (txtUsuario.getText() == null || txtUsuario.getText().equals(""))
                mensaje += "El campo de usuario debe rellenarlo \n";
            if (txtCedula.getText() == null || txtCedula.getText().equals(""))
                mensaje += "El campo de cedula debe rellenarlo \n";
            if (txtEdad.getText() == null || txtEdad.getText().equals("")) {
                mensaje += "El campo de edad debe rellenarlo \n";
            } else {
                try {
                    Integer.parseInt(txtEdad.getText());
                } catch (NumberFormatException e) {
                    mensaje += "El campo de edad debe ser un número entero \n";
                }
            }
            if (comboTipo.getValue() == null)
                mensaje += "Debe escojer un tipo de usuario \n";
            if (mensaje.equals("")) {
                return true;
            } else {
                mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
                registrarAcciones("Noficiacion cliente",1, "datos invalidos");
                return false;
            }


        }
        public  void limpiarCamposRegistro(){
            txtEdad.setText("");
            txtCedula.setText("");
            txtUsuario.setText("");
            txtClave.setText("");
            txtApellido.setText("");
            txtNombre.setText("");
            comboTipo. setPromptText("Tipo usuario");
        }




        @FXML
        void registrarse(ActionEvent event) {
            //NO CREO QUE SEA NECESARIO PORQUE PARA ESO SON LOS TABS; CRERIA NECESARIO UN BOTON PARA CERRAR SESION
            //Y CUANDO SE CIEERE SESION LA VARIABLE GLOBAL AUTENTICACION SE VUELVA FALSE  O ALGO ASI

        }


        //++++++++++++++++++++++++++++++++++DTOS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //EMPLEADO
        private ProductoDto construirEmpleadoDto() {
            registrarAcciones("Contruir prodcutoDto",1, "Se construyo producto Dto");
            return new ProductoDto(
                    txtNombreProducto.getText(),
                    txtDescripcion.getText(),
                    comboTipoProducto.getValue(),
                    txtUrlFoto.getText()

            );

        }
        //ANUNCIANTE
    private AnuncianteDto construirAnuncianteDto(){
        return  new AnuncianteDto(
                txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                Integer.parseInt(txtEdad.getText()),
                txtUsuario.getText(),
                txtClave.getText(),
                10,
                11


        );
    }
    //COMPRADOR
    private CompradorDto construirCompradorDto(){
        return new CompradorDto(     txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                Integer.parseInt(txtEdad.getText()),
                txtUsuario.getText(),
                txtClave.getText(),
                10
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
        registrarAcciones("se mostro mensaje",1, "mostrar mensaje");
    }

    //+++++++++++++++++++++++++++++++log++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        crudProductoController.registrarAcciones(mensaje, nivel, accion);

    }



}







