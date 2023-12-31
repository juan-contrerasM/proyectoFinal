package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.controller.ControllerRegistro;
import co.edu.uniquindo.proyectosubastaquindio.excepciones.FormatoInvalidoException;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.AnuncianteDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.CompradorDto;
import co.edu.uniquindo.proyectosubastaquindio.mapping.dto.ProductoDto;
import co.edu.uniquindo.proyectosubastaquindio.model.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegistroView implements Initializable {

    private ControllerRegistro controllerRegistro;

    @FXML
    private Button btnGuardarUsuario;


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

            if (datosValidosRegistro()) {
                if(Float.parseFloat(txtEdad.getText())>=18) {
                    if (comboTipo.getValue().equals(TipoUsuario.ANUNCIANTE)) {
                        AnuncianteDto anuncianteDto = construirAnuncianteDto();
                        if (controllerRegistro.verificarAnuncianteCreado(anuncianteDto)) {
                            controllerRegistro.guardarAnunciante(anuncianteDto);
                            limpiarCamposRegistro();
                            registrarAcciones("guardar usuario", 1, "guardar usuario anunciante");

                        }
                    } else if (comboTipo.getValue().equals(TipoUsuario.COMPRADOR)) {
                        CompradorDto compradorDto = construirCompradorDto();
                        if (controllerRegistro.verificarCompradorCreado(compradorDto)) {
                            controllerRegistro.guardarComprador(compradorDto);
                            limpiarCamposRegistro();
                        }
                        registrarAcciones("guardar usuario", 1, "guardar usuario comprador");

                    }
                }else {
                    mostrarMensaje("Debe ser mayo de edad","Registro invalido","Registro invalido debe ser mayor de edad", Alert.AlertType.INFORMATION);
                }
            }



    }

    public boolean datosValidosRegistro() {
        String mensaje = "";
        if (txtNombre.getText() == null || txtNombre.getText().equals(""))
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
                registrarAcciones("Se lanzo Exceptio formato invalido Exception, en controller registro ", 2, "se lanzo exception");
                throw new FormatoInvalidoException("Error formato de campo numerico invalido", e);
            }
        }
        if (comboTipo.getValue() == null)
            mensaje += "Debe escojer un tipo de usuario \n";
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            registrarAcciones("Noficiacion cliente", 1, "datos invalidos");
            return false;
        }


    }

    public void limpiarCamposRegistro() {
        txtEdad.setText("");
        txtCedula.setText("");
        txtUsuario.setText("");
        txtClave.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        comboTipo.setPromptText("Tipo usuario");
    }



    //ANUNCIANTE
    private AnuncianteDto construirAnuncianteDto() {
        registrarAcciones("se creo un anuncianteDTO", 1, "se creo un anuncianteDTO");


        return new AnuncianteDto(
                txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                Integer.parseInt(txtEdad.getText()),
                txtUsuario.getText(),
                txtClave.getText(),
                0,
                11


        );

    }

    private CompradorDto construirCompradorDto() {
        registrarAcciones("se creo un compradorDTO", 1, "se creo un compradorDTO");

        return new CompradorDto(txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                Integer.parseInt(txtEdad.getText()),
                txtUsuario.getText(),
                txtClave.getText(),
                0
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
        controllerRegistro.registrarAcciones(mensaje, nivel, accion);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerRegistro = new ControllerRegistro();
        comboTipo.getItems().addAll(TipoUsuario.values());
    }
}
