package co.edu.uniquindo.proyectosubastaquindio.controllersView;

import co.edu.uniquindo.proyectosubastaquindio.excepciones.PersistenciaArchivosTxtException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class SubastaQuindioAplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SubastaQuindioAplication.class.getResource("homeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        /*
        ----------------------------------------------------------------------------------------------------------------
        Respaldo de los productos
        ----------------------------------------------------------------------------------------------------------------
         */
        launch();

        String archivoOrigenProducto = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/productos.txt"; // Nombre del archivo original
        String archivoRespaldoProducto = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/productoRpealdo.txt"; // Nombre del archivo de respaldo

        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenProducto));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoProducto));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo producto realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de productos desde el archivo de texto", e);
        }




         /*
        ----------------------------------------------------------------------------------------------------------------
        Respaldo de los log
        ----------------------------------------------------------------------------------------------------------------
         */

        String archivoOrigenLog = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/log.txt"; // Nombre del archivo original
        String archivoRespaldoLog = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/logRespaldo.txt"; // Nombre del archivo de respaldo
        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenLog));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoLog));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo  log realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo log.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de log desde el archivo de texto", e);
        }




         /*
        ----------------------------------------------------------------------------------------------------------------
        Respaldo de los compradores
        ----------------------------------------------------------------------------------------------------------------
         */

        String archivoOrigenCompradores = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/compradores.txt"; // Nombre del archivo original
        String archivoRespaldoCompradores = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/compradoresRespaldo.txt"; // Nombre del archivo de respaldo
        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenCompradores));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoCompradores));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo  compradores realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo compradores.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de compradores desde el archivo de texto", e);
        }




        /*
        ----------------------------------------------------------------------------------------------------------------
        Respaldo de los anuncios
        ----------------------------------------------------------------------------------------------------------------
         */

        String archivoOrigenAnuncios = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/anuncios.txt"; // Nombre del archivo original
        String archivoRespaldoAnuncios = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/anunciosRespaldo.txt"; // Nombre del archivo de respaldo
        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenAnuncios));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoAnuncios));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo anuncios realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo anuncios.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de anuncios desde el archivo de texto", e);
        }




         /*
        ----------------------------------------------------------------------------------------------------------------
        Respaldo de los anunciantes
        ----------------------------------------------------------------------------------------------------------------
         */


        String archivoOrigenAnunciantes = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/anunciantes.txt"; // Nombre del archivo original
        String archivoRespaldoAnunciantes = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/anunciantesRespaldo.txt"; // Nombre del archivo de respaldo
        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenAnunciantes));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoAnunciantes));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo  anunciantes realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo anunciantes.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de anunciantes desde el archivo de texto", e);
        }
        String archivoOrigenPujas= "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/archivos/pujas.txt"; // Nombre del archivo original
        String archivoRespaldoPujas = "src/main/resources/co/edu/uniquindo/proyectosubastaquindio/persistencia/respaldo/pujasRespaldo"; // Nombre del archivo de respaldo
        try {
            // Abrir el archivo de origen para lectura
            BufferedReader lector = new BufferedReader(new FileReader(archivoOrigenAnunciantes));

            // Abrir el archivo de respaldo para escritura
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRespaldoAnunciantes));

            // Leer el contenido del archivo de origen y escribirlo en el archivo de respaldo
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Agregar una nueva línea después de cada línea del archivo
            }

            // Cerrar los archivos
            lector.close();
            escritor.close();

            System.out.println("Respaldo del archivo  anunciantes realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();  // Imprime la traza de la excepción original
            System.err.println("Error al respaldar el archivo anunciantes.");
            throw new PersistenciaArchivosTxtException("Error al obtener la lista de anunciantes desde el archivo de texto", e);
        }

    }




    }



        



