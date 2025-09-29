package ManejoFicheros;

import java.io.*;
import java.util.Scanner;

public class EjerciciosClase {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Que ejercicio quieres ejecutar:" +
                    "\n 1. ejercicio1" +
                    "\n 2. ejercicio2" +
                    "\n 0. Salir");

            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    ejercicio1();
                    System.out.println();
                    break;

                case 2:
                    ejercicio2();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }while (opcion != 0);

    }

    public static void ejercicio1(){
        String rutaArchivo = "ManejoFicheros/ejercicio1.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

            for (int i = 1; i <= 3; i++) {
                bw.write("Línea " + i);
                bw.write("\n");
            }

            bw.close();

            System.out.println("Escritura correcta");

            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
            }

            System.out.println("Lectura realizada");

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ejercicio2(){
        // Ficheros binarios simple
        String rutaArchivo = "ManejoFicheros/ejercicio2.bin";

        try {
            // Escritura de datos en binario.
            FileOutputStream fos = new FileOutputStream(new File(rutaArchivo));
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(25);
            dos.writeDouble(6.5);
            dos.writeUTF("Hola clase DAM");

            dos.close();
            fos.close();

            System.out.println("Datos escritos correctamente.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Lectura de datos en binario
            FileInputStream fis = new FileInputStream(new File(rutaArchivo));
            DataInputStream dis = new DataInputStream(fis);

            int entero = dis.readInt();
            double decimal = dis.readDouble();
            String texto = dis.readUTF();

            dis.close();
            fis.close();

            System.out.println("Entero leído: " + entero);
            System.out.println("Decimal leído: " + decimal);
            System.out.println("Texto leído: " + texto);

            System.out.println("Lectura correcta");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ejercicio3(){

    }

}
