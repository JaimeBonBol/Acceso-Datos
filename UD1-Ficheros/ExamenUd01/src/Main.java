import javax.xml.transform.sax.SAXTransformerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {

    static String rutaArchivo = "ExamenUd01/src/Alumnos.txt";

    private  static int idAlumno = ultimoID(rutaArchivo) + 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String rutaArchivo = "ExamenUd01/src/Alumnos.txt";
        String rutaArchivoNotas = "ExamenUd01/src/Notas.txt";

        do {
            System.out.println("Introduce una opción:" +
                    "\n1. Añadir alumnos" +
                    "\n2. Devbolver el ID de un alumno (por nombre y apellido)" +
                    "\n3. Insertar notas" +
                    "\n4. Calcular la media de las notas de un alumno." +
                    "\n0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    int opcion2;
                    do {
                        insertarAlumno(rutaArchivo);

                        idAlumno ++;

                        System.out.println("Pulsa 1 si quieres insertar otro alumno, o 0 para salir.");
                        opcion2 = sc.nextInt();
                        sc.nextLine();

                    }while (opcion2 != 0);

                    break;

                case 2:
                    System.out.println("Nombre del alumno a buscar");
                    String nombreBuscar  = sc.nextLine();

                    System.out.println("Apellido del alumno a buscar");
                    String apellidoBuscar  = sc.nextLine();

                    buscarAlumno(nombreBuscar, apellidoBuscar, rutaArchivo);
                    break;

                case 3:
                    System.out.println("Nombre alumno:");
                    String nombre = sc.nextLine();

                    System.out.println("Apellidos alumno:");
                    String apellidos = sc.nextLine();

                    String id = buscarAlumno(nombre, apellidos, rutaArchivo);

                    addNotas(id, rutaArchivoNotas);

                    break;

                case 4:
                    System.out.println("Nombre alumno:");
                    String nombreA = sc.nextLine();

                    System.out.println("Apellidos alumno:");
                    String apellidosA = sc.nextLine();

                    String idA = buscarAlumno(nombreA, apellidosA, rutaArchivo);

                    mediaNotas(idA, rutaArchivoNotas);

                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        }while (opcion != 0);
    }


    public static void insertarAlumno(String rutaArchivo){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo,true));
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce nombre alumno:");
            String nombre = sc.nextLine();

            System.out.println("Introduce apellidos alumno:");
            String apellidos = sc.nextLine();

            System.out.println("Introduce fecha nacimiento alumno:");
            String fechaNac = sc.nextLine();

            System.out.println("Introduce clase alumno:");
            String clase = sc.nextLine();

            StringBuilder sb = new StringBuilder();
            sb.append(idAlumno + "|" + nombre + "|" + apellidos + "|" + fechaNac + "|" + clase + ";");

            //idAlumno ++;
            bw.write(sb.toString());
            bw.newLine();

            bw.close();
            System.out.println("Alumno escrito");

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static String buscarAlumno(String nombreBuscar, String apellidoBuscar, String rutaArchivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            Scanner sc = new Scanner(System.in);

            String idAlumno = "";

            Boolean encontrado = false;
            String linea;
            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split("\\|");

                if (palabrasLinea[1].equals(nombreBuscar) && palabrasLinea[2].equals(apellidoBuscar)){
                    System.out.println("Alumno eno¡contrado, ID: " + palabrasLinea[0]);
                    idAlumno = palabrasLinea[0];
                    encontrado = true;
                }
            }

            if (!encontrado){
                throw new RuntimeException("El alumno con nombre " + nombreBuscar + " y apellido " + apellidoBuscar + " no se encuentra en el archivo.");
            }

            br.close();

            return idAlumno;

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public static void addNotas(String id, String rutaArchivo){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true));

            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce notas separadas con ;");
            String notas = sc.nextLine();

            bw.write(id + "|" + notas);
            bw.newLine();

            bw.close();
            System.out.println("Notas escritas");

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mediaNotas(String id, String rutaArchivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            ArrayList<Double> notas = new ArrayList<>();
            double sumaNotas = 0;

            while ((linea = br.readLine()) != null){
                String[] palabrasLinea = linea.split("\\|");
                if (palabrasLinea[0].equals(id)){
                    for (int i = 1; i < palabrasLinea.length; i++) {
                        String[] notasL = palabrasLinea[i].split(";");
                        for (int j = 0; j < notasL.length; j++) {
                            notas.add(Double.valueOf(notasL[j]));
                        }
                    }
                }

            }

            for (Double nota : notas){
                sumaNotas += nota;
            }

            double notamedia = sumaNotas / notas.size();

            br.close();
            System.out.println("Nota media de alumno con Id: " + id + " es " +notamedia);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int ultimoID(String rutaArchivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            String linea = "";
            ArrayList<Integer> ids = new ArrayList<>();

            while ((linea = br.readLine()) != null){

                String[] palabrasLinea = linea.split("\\|");

                ids.add(Integer.valueOf(palabrasLinea[0]));


            }
            br.close();

            return ids.getLast();
        } catch (Exception e) {

            return 0;
        }
    }
}
