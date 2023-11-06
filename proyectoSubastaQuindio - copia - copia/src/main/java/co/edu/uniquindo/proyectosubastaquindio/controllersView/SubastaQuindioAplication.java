package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.utils.Persistencia;
import javafx.application.Application;
import javafx.css.Stylesheet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.*;

public class SubastaQuindioAplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SubastaQuindioAplication.class.getResource("productoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 866, 591);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        //RESPALDO
        launch();

        String archivoOrigen = "proyectoSubastaQuindio - copia - copia/src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/productos.txt"; // Nombre del archivo original
        String archivoRespaldo = "proyectoSubastaQuindio - copia - copia/src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/productoRpealdo.txt"; // Nombre del archivo de respaldo

        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigen));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldo));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al respaldar el archivo.");
        }
    }

}
        



