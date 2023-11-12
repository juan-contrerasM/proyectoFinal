package co.edu.uniquindo.proyectosubastaquindio.excepciones;

import java.io.IOException;

public class ImportarImagenException extends IOException {
    public ImportarImagenException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
