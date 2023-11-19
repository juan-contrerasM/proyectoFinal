package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerHome;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.EntradaInvalidaException;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.InicioSecionSInCerrarSesionException;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.SeleccionComboNullException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHomeView  implements Initializable {
    public Tab tabRegistrarse;
    public Tab tabGestionarAnuncio;
    public Tab tabAñadirProducto;

    public Tab tabHome;
    public Tab tabPujar;
    public TabPane tabs;
    public Tab tabPujasAnuncio;
    public Tab historialPujas;
    private boolean autenticacion;
    private ControllerHome controllerHome;
    private TipoUsuario tipoUsuario;
    @FXML
    private Hyperlink vinculoLink;
    @FXML
    void clickLink(ActionEvent event) {
    }
    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;
    @FXML
    private PasswordField txtClaveHome;

    @FXML
    private TextField txtUsuarioHome;

    @FXML
    private Button btnCerrarSesion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerHome = new ControllerHome();
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());


    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++HOME+++++++++++++++++++++++++++++++++++++++++++++++++++++


    @FXML
    void cerrarSesion(ActionEvent event) {

        if (autenticacion) {
            autenticacion = false;
            controllerHome.guardarAutenticacion(autenticacion);
            restablecerTabs();

            mostrarMensaje("Alerta accion correcta", "Cerro sesion correctamente", " ", Alert.AlertType.CONFIRMATION);
            registrarAcciones("cerro sesion", 1, "cerro sesion");


        } else {
            mostrarMensaje("Alerta accion incorrecta", "Debe ingresar para hacer uso de esta accion ", "", Alert.AlertType.WARNING);

        }

    }




    @FXML
    void IniciarSesion(ActionEvent event) throws IOException, InicioSecionSInCerrarSesionException {

        if(!autenticacion) {
            String usuario = txtUsuarioHome.getText();
            String clave = txtClaveHome.getText();
            tipoUsuario=comboTipoUsuario.getValue();
            try {
                if (comboTipoUsuario.getValue().equals(TipoUsuario.ANUNCIANTE)) {
                    if (controllerHome.verificarInicioSesionAnunciante(usuario, clave)) {
                        controllerHome.guardarAnuncianteGlobal(usuario);
                        autenticacion = true;
                        controllerHome.guardarAutenticacion(autenticacion);
                        ocultarTabAnuncianter();
                        limpiarCamposHome();
                        mostrarMensaje("Alerta accion ", "Ingreso con exito", "", Alert.AlertType.CONFIRMATION);
                        registrarAcciones("inicio sesion", 1, "tipo anunciante");

                    } else {
                        mostrarMensaje("Alerta accion incorrecta", "credenciales incorrectas\nclave o usuario incorrecto", "Vuelva a intentarlo", Alert.AlertType.WARNING);
                    }
                } else if (comboTipoUsuario.getValue().equals(TipoUsuario.COMPRADOR)) {
                    if (controllerHome.verificarInicioSesionComprador(usuario, clave)) {
                        autenticacion = true;
                        controllerHome.guardarCompradorGlobal(usuario);
                        controllerHome.guardarAutenticacion(autenticacion);
                        ocultarTabComprador();
                        //  bloquearVentanarComprador();
                        limpiarCamposHome();
                        mostrarMensaje("Alerta accion ", "Ingreso con exito", "", Alert.AlertType.CONFIRMATION);
                        registrarAcciones("inicio sesion", 1, "tipo comprador");

                    } else {

                        registrarAcciones("Error al iniciar sesion ", 1, "datos invalidos");
                        mostrarMensaje("Alerta accion incorrecta", "credenciales incorrectas\nclave o usuario incorrecto", "Vuelva a intentarlo", Alert.AlertType.WARNING);
                    }

                }
            } catch (NullPointerException e) {
                mostrarMensaje("Alerta accion incorrecta", "Debe de expecificar el tipo de usuario", "Vuelva a intentarlo", Alert.AlertType.WARNING);
                registrarAcciones("Error de entrada combo box", 1, "seleccion nula");
                throw new SeleccionComboNullException("La selección del combo box es nula", e);
            } catch (IllegalArgumentException | InputMismatchException e) {
                mostrarMensaje("Error de entrada", "Entrada de datos no válida", "Verifique sus datos e intente nuevamente.", Alert.AlertType.ERROR);
                registrarAcciones("Error de entrada", 1, "Entrada de datos no válida");
                throw new EntradaInvalidaException("Entrada de datos no válida", e);
            }
        }else {
            mostrarMensaje("Inicio Sesion Invalido","Inicio Sesion Invalido","Debe cerrar sesion \nantes de abrir otra sesion", Alert.AlertType.INFORMATION);
            registrarAcciones("inicio sesion dos veces en home controller view",3,"inicio sesion" );
            if(comboTipoUsuario.getValue().equals(TipoUsuario.COMPRADOR)){
                comboTipoUsuario.setValue(TipoUsuario.ANUNCIANTE);
            }else {
                comboTipoUsuario.setValue(TipoUsuario.COMPRADOR);
            }
            //throw new InicioSecionSInCerrarSesionException("debe cerrar sesion");

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

    public void ocultarTabComprador() {
        tabs.getTabs().remove(tabAñadirProducto);
        tabs.getTabs().remove(tabRegistrarse);
        tabs.getTabs().remove(tabGestionarAnuncio);
        tabs.getTabs().remove(tabPujasAnuncio);

    }

    public void ocultarTabAnuncianter() {

        tabs.getTabs().remove(tabRegistrarse);
        tabs.getTabs().remove(tabPujar);
        tabs.getTabs().remove(historialPujas);


    }

    public void restablecerTabs() {
        if(tipoUsuario.equals(TipoUsuario.COMPRADOR)) {
            tabs.getTabs().add(tabGestionarAnuncio);
            tabs.getTabs().add(tabRegistrarse);
            tabs.getTabs().add(tabAñadirProducto);
            tabs.getTabs().add(tabPujasAnuncio);
        }else {
            tabs.getTabs().add(tabPujar);
            tabs.getTabs().add(tabRegistrarse);
            tabs.getTabs().add(historialPujas);
        }


    }



}


