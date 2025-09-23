import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicios {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Que ejercicio quieres ejecutar:" +
                    "\n 1. ejercicio1 (Leer solo nombre de cada fila.)" +
                    "\n 2. ejercicio2 (Escribir x numeros fila por fila y mostrar la suma y la media.)" +
                    "\n 3. ejercicio3 (Pedir por consola nombres hasta escribir fin y guardarlos en archivo.)" +
                    "\n 4. ejercicio4 (Leer el fichero, crear objetos Empleado y mostrar los empleados.)" +
                    "\n 5. ejercicio5 (Leer productos en memoria, aumentar el precio un 10% y guardarlos en productos-actualizados.)" +
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

                case 3:
                    ejercicio3();
                    System.out.println();
                    break;

                case 4:
                    ejercicio4();
                    System.out.println();
                    break;

                case 5:
                    ejercicio5();
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


    /**
     * Crear fichero con la siguiente estructura para cada fila:
     * ID:Nombre:Edad (1:Juan:20)
     * Leer toda la línea y mostrar solo el nombre en cada fila.
     */
    public static void ejercicio1(){
        String rutaArchivo = "Tarea1/src/ejercicio1.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            // Separa las palabras de cad línea por ":" en un array , y muestra la segunda posición que es donde está
            // el nombre.
            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split(":");
                System.out.println(palabrasLinea[1]);
            }

            System.out.println();
            System.out.println("Arhcivo leído correctamente.");
            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Crear un fichero con un número en cada fila
     * Guardar los números en un ArrayList<Integer> y mostrar la suma y la media.
     */
    public static void ejercicio2(){
        String rutaArchivo = "Tarea1/src/ejercicio2.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
            Scanner sc = new Scanner(System.in);

            // Pregunta cuántos números quiere que se escriban para ir escribiendolos uno por uno en cada línea.
            System.out.println("¿Hasta que número quieres que se escriba?");
            int num = sc.nextInt();
            for (int i = 1; i <= num; i++) {
                bw.write(i + "\n");
            }

            bw.close();
            System.out.println("Se han escrito desde el número 1 hasta el número " + num + " en el arhcivo ejercicio2.txt");


            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            // ArrayList donde se irán almacenando los números del documento.
            ArrayList<Integer> numerosDoc = new ArrayList<>();

            while ((linea = br.readLine()) != null){
                numerosDoc.add(Integer.parseInt(linea));
            }

            // Una vez el arrayList tenga los números del doc, se suman y se calcula la media.
            int suma = 0;
            for (int numero : numerosDoc){
                suma += numero;
            }

            double media = (double) suma / numerosDoc.size();

            System.out.println("Suma de los números: " + suma);
            System.out.println("Media del documento: " + media);

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Pedir por consola nombres de alumnos hasta que el usuario escriba "fin"
     * Guardarlos en salida.txt, un nombre por línea.
     */
    public static void ejercicio3(){
        String rutaArchivo = "Tarea1/src/salida.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
            Scanner sc = new Scanner(System.in);

            String nombre;

            // Repetirá hasta que la palabra escrita sea fin.
            do {
                System.out.println("Escribe un nombre de alumno. Para finalizar escriba fin: ");
                nombre = sc.nextLine();

                // Si no es fin, lo esribe, para evitar que la última palabra del archivo sea "fin".
                if (!nombre.equalsIgnoreCase("fin")){
                    bw.write(nombre + "\n");
                }

            }while (!nombre.equalsIgnoreCase("fin"));

            bw.close();
            System.out.println("Archivo escrito correctamente.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
        String rutaArchivo = "Tarea1/src/empleado.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            String nombre;
            int edad;
            String profesion;

            ArrayList<Empleado> empleados = new ArrayList<>();

            // Se divide la línea por los separadores ; se asigna cada elemento de la linea a el atributo del empleado,
            // se crea una instancia con esos valores de los atributos, que serán los del doc, y por último se añade a la lista.
            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split(";");

                nombre = palabrasLinea[0];
                edad = Integer.parseInt(palabrasLinea[1]);
                profesion = palabrasLinea[2];

                Empleado empleado = new Empleado(nombre, edad, profesion);

                empleados.add(empleado);
            }

            // Mostrar los empleados.
            int posEmpleado = 1;

            for (Empleado empleado : empleados){
                System.out.println("Empleado " + posEmpleado + "-> " + empleado);
                posEmpleado ++;
            }
            System.out.println();

            br.close();
            System.out.println("Todos los empleados han sido mostrados correctamente.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
        String rutaArchivo = "Tarea1/src/productos.txt";
        String rutaArchivoNuevo = "Tarea1/src/productos-actualizados.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoNuevo));

            String linea;
            double precio;
            ArrayList<String[]> estructura = new ArrayList<>();
            while ((linea = br.readLine()) != null){
                String[] palabraLista = linea.split(";");
                estructura.add(palabraLista);
            }

            // Recorro mi estructura, accedo a la posición 2 (precio) desntro del subArray para incrementarlo en un 10%.
            for (String[] datosLinea : estructura){
                // Recojo el precio del producto, que se encuentra en la posición 2 del array de la línea separada.
                // Y se aumenta el 10%
                precio = Double.parseDouble(datosLinea[2]);
                double precioAumentado = precio + (precio * 0.1);

                datosLinea[2] = String.valueOf(precioAumentado);
            }

            // Creo una línea para darle formato, recorro la estructura, y voy añadiendo a la lista los elementos del subArray
            // de cada posición de mi estructura, es decir, el elemeto 0, 1, 2 del array que se encuentra en la posición 1
            // de la estructura, etc.
            for (int i = 0; i < estructura.size(); i++) {
                String lineaMod ="";

                lineaMod += estructura.get(i)[0] + ";";
                lineaMod += estructura.get(i)[1] + ";";
                lineaMod += estructura.get(i)[2] + "\n";


                bw.write(lineaMod);
            }

            br.close();
            bw.close();
            System.out.println("El precio se ha aumentado un 10%.");


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
