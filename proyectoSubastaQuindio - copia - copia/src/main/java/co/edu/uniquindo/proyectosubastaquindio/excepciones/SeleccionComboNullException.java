package co.edu.uniquindo.proyectosubastaquindio.excepciones;


public class SeleccionComboNullException extends RuntimeException {

    public SeleccionComboNullException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public SeleccionComboNullException(String mensaje) {
        super(mensaje);
    }
}
