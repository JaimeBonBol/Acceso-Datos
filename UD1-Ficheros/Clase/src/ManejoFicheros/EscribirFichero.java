package ManejoFicheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFichero {
    public static void main(String[] args) {
        String rutaArchivo = "ManejoFicheros/prueba.txt";

        File file = new File(rutaArchivo);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true));

            bw.write("\nEsto es una nueva escritura\n");

            System.out.println("Escritura realizada correctamente.");
            bw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
