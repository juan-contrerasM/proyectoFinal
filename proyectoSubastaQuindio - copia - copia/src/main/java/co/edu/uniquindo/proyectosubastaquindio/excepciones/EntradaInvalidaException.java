package co.edu.uniquindo.proyectosubastaquindio.excepciones;

public class EntradaInvalidaException extends RuntimeException {

    public EntradaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}