package RepasoEjeciciosFicheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EjerciciosRepaso {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Elige opción:" +
                    "\n1.Ej1" +
                    "\n2.Ej2" +
                    "\n3.Ej3" +
                    "\n4.Ej4" +
                    "\n5.Ej5" +
                    "\n0.Salir");

            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    ejercicio1();
                    break;

                case 2:
                    ejercicio2();
                    break;

                case 3:
                    ejercicio3();
                    break;

                case 4:
                    ejercicio4();
                    break;

                case 5:
                    ejercicio5();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        }while (opcion != 0);

    }

    /**
     * Crear fichero con la siguiente estructura para cada fila:
     * ID:Nombre:Edad (1:Juan:20)
     * Leer toda la línea y mostrar solo el nombre en cada fila.
     */
    public static void ejercicio1(){
        String rutaArchivo = "Tarea1/src/RepasoEjeciciosFicheros/ejercicio1.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split(":");
                System.out.println(palabrasLinea[1]);
            }

            br.close();
            System.out.println("Archivo leído.");

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Crear un fichero con un número en cada fila
     * Guardar los números en un ArrayList<Integer> y mostrar la suma y la media.
     */
    public static void ejercicio2(){
        String rutaArchivo = "Tarea1/src/RepasoEjeciciosFicheros/ejercicio2.txt";

        try {
            Scanner sc = new Scanner(System.in);
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

            // Pedir cuántos num escribir
            System.out.println("¿Cuántos números quieres escribir?");
            int numEscribir = sc.nextInt();

            for (int i = 1; i <= numEscribir; i++) {
                bw.write(i + "\n");
            }

            bw.close();
            System.out.println("Números del 1 hasta " + numEscribir + " escritos.");

            //Leer, hacer suma y media
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            int suma = 0;
            ArrayList<Integer> numeros = new ArrayList<Integer>();
            while ((linea = br.readLine()) != null){
                numeros.add(Integer.parseInt(linea));
            }

            for (int num : numeros){
                suma += num;
            }

            double media = (double) suma / numeros.size();

            br.close();
            System.out.println("La suma es: " + suma);
            System.out.println("La media es: " + media);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Pedir por consola nombres de alumnos hasta que el usuario escriba "fin"
     * Guardarlos en salida.txt, un nombre por línea.
     */
    public static void ejercicio3(){
        String rutaArchivo = "Tarea1/src/RepasoEjeciciosFicheros/ejercicio3.txt";

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

            Scanner sc = new Scanner(System.in);
            String nombre;

            do {
                System.out.println("Introduce nombre. Escribe fin para salir");

                nombre = sc.nextLine();

                if (!nombre.equalsIgnoreCase("fin")){
                    bw.write(nombre + "\n");
                }else {
                    System.out.println("Saliendo...");
                }
            }while (!nombre.equalsIgnoreCase("fin"));

            bw.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Crear fichero empleado.txt con la siguiente estructura:
     * Juan;25;Programador
     * Ana;30;Diseñadora
     * Leer el fichero, crear objetos Empleado y guardarlos en un ArrayList<Empleado>.
     * Mostrar luego todos los empleados.
     */
    public static void ejercicio4(){
        String rutaArchivo = "Tarea1/src/RepasoEjeciciosFicheros/empleado.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            ArrayList<Empleado> empleados = new ArrayList<>();

            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split(";");
                String nombre = palabrasLinea[0];
                int edad = Integer.parseInt(palabrasLinea[1]);
                String profesion = palabrasLinea[2];

                Empleado empleado = new Empleado(nombre, edad, profesion);

                empleados.add(empleado);
            }

            System.out.println("Empleados:");
            for (Empleado empleado : empleados){
                System.out.println(empleado);
            }

            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Crear fichero productos.txt
     * 1;Teclado;25.5
     * 2;Raton;15.0
     * 3;Monitor;200.0
     * Leer todos los productos en memoria, aumentar todos los precios un 10% y
     * guardarlos en productos_actualizados.txt
     */
    public static void ejercicio5(){
        String rutaArchivo = "Tarea1/src/RepasoEjeciciosFicheros/productos.txt";
        String rutaArchivoEscribir = "Tarea1/src/RepasoEjeciciosFicheros/productos-mod.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoEscribir));

            String linea;
            ArrayList<String[]> estructura = new ArrayList<>();

            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split(";");

                // Guardar el array de la linea divida en el arrayLIst de estructura.
                estructura.add(palabrasLinea);
            }

            // Acceder al precio de cada subarray y aumentar el precio.
            for (int i = 0; i < estructura.size(); i++) {
                double precio = Double.parseDouble(estructura.get(i)[2]);

                double precioAumentado = (int) (precio * 1.1);

                // Guardarlo aumentado.
                estructura.get(i)[2] = String.valueOf(precioAumentado);
            }

            for (int i = 0; i < estructura.size(); i++) {
                String lineaModificada = "";

                lineaModificada += estructura.get(i)[0] + ";";
                lineaModificada += estructura.get(i)[1] + ";";
                lineaModificada += estructura.get(i)[2] + "\n";

                bw.write(lineaModificada);
            }

            br.close();
            bw.close();
            System.out.println("FIN");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
