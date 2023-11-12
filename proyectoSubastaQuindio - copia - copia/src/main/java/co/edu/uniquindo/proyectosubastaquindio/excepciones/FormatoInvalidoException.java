package co.edu.uniquindo.proyectosubastaquindio.excepciones;

public class FormatoInvalidoException extends RuntimeException {

    public FormatoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
