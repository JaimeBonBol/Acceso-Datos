import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        String rutaArchivo = "Clase/src/prueba.txt";

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            while ((linea = buffer.readLine()) != null){
                System.out.print(linea);
                System.out.println();
            }

            buffer.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
    }
}
