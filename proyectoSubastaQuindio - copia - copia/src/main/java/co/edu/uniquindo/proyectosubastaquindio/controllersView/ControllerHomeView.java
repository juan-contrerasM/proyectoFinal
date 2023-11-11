package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerHome;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHomeView implements Initializable {
    private boolean autenticacion;
    private ControllerHome controllerHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerHome=new ControllerHome();
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());

    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++HOME+++++++++++++++++++++++++++++++++++++++++++++++++++++

    @FXML
    private Button btnCerrarSesion;

    @FXML
    void cerrarSesion(ActionEvent event) {

        if (autenticacion ){
            autenticacion=false;
            controllerHome.guardarAutenticacion(autenticacion);
            mostrarMensaje("Alerta accion correcta", "Cerro sesion correctamente", " ", Alert.AlertType.CONFIRMATION);
            registrarAcciones("cerro sesion", 1, "cerro sesion");


        }else {
            mostrarMensaje("Alerta accion incorrecta", "Debe ingresar para hacer uso de esta accion ", "Vuelva a intentarlo", Alert.AlertType.WARNING);

        }

    }


    @FXML
    private Hyperlink vinculoLink;
    @FXML
    void clickLink(ActionEvent event) {


    }@FXML
    private Button btnIniciarSesion;
    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;
    @FXML
    private PasswordField txtClaveHome;

    @FXML
    private TextField txtUsuarioHome;
    private AnuncianteDto anuncianteDto;
    private CompradorDto compradorDto;

    /**public Producto buscarProducto(String nombre){
     for (Producto producto:listaProductos) {
     if(producto.getNombreProducto().equals(nombre)){
     return producto;
     }

     }
     return null;
     }
     */
    String usuario;

    //FLATA ATRIBUTOS XML POR DEFINIR
    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        String usuario = txtUsuarioHome.getText();
        String clave = txtClaveHome.getText();
        try {
            if (comboTipoUsuario.getValue().equals(TipoUsuario.ANUNCIANTE)) {
                if (controllerHome.verificarInicioSesionAnunciante(usuario, clave)) {
                    controllerHome.guardarAnuncianteGlobal(usuario);
                    autenticacion=true;
                    controllerHome.guardarAutenticacion(autenticacion);
                    limpiarCamposHome();
                    mostrarMensaje("Alerta accion ", "Ingreso con exito", "", Alert.AlertType.CONFIRMATION);
                    registrarAcciones("inicio sesion", 1, "tipo anunciante");
                    //AnuncianteDto anunciante  =crudProductoController.guardarAnunciante();
                    //iniciarTablaAnuncio();
                } else {
                    mostrarMensaje("Alerta accion incorrecta", "credenciales incorrectas\nclave o usuario incorrecto", "Vuelva a intentarlo", Alert.AlertType.WARNING);
                }
            } else if (comboTipoUsuario.getValue().equals(TipoUsuario.COMPRADOR)) {
                if (controllerHome.verificarInicioSesionComprador(usuario, clave)) {
                    autenticacion=true;
                    controllerHome.guardarCompradorGlobal(usuario);
                    controllerHome.guardarAutenticacion(autenticacion);
                    limpiarCamposHome();
                    mostrarMensaje("Alerta accion ", "Ingreso con exito", "", Alert.AlertType.CONFIRMATION);
                    registrarAcciones("inicio sesion", 1, "tipo comprador");

                } else {

                    registrarAcciones("Error al iniciar sesion ", 1, "datos invalidos");
                    mostrarMensaje("Alerta accion incorrecta", "credenciales incorrectas\nclave o usuario incorrecto", "Vuelva a intentarlo", Alert.AlertType.WARNING);
                }

            }
        }catch (NullPointerException e){
            mostrarMensaje("Alerta accion incorrecta", "Debe de expecificar el tipo de usuario", "Vuelva a intentarlo", Alert.AlertType.WARNING);

        }

    }

    public void limpiarCamposHome() {
        txtClaveHome.setText("");
        txtUsuarioHome.setText("");
        comboTipoUsuario.setPromptText("Tipo de usuario");

    }
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
        controllerHome.registrarAcciones(mensaje, nivel, accion);

    }
    //+++++++++++++++++++++++++++ventanas a mostrar segun el usuario+++++++++++++++++++



}
