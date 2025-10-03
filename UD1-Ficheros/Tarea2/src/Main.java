import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        File fileXML = new File("Tarea2/src/catalogo.xml");
        Scanner sc = new Scanner(System.in);
        String modo;
        int opcion;
        List<Book> books;

        System.out.println("Tarea 2 - Leer XML con DOM y SAX");
        System.out.println("Escribe el modo:" +
                "\n dom" +
                "\n sax");
        modo = sc.nextLine();

        System.out.println("ESTÁS EN MODO " + modo.toUpperCase());

        System.out.println();
        do {
            System.out.println("Introduzca opción:" +
                    "\n1. Mostrar todos los libros." +
                    "\n2. Mostrar sólo los títulos publicados después de 2010." +
                    "\n3. Mostrar los libros con más de un autor." +
                    "\n4. Calcular el precio medio en EUR (para los que tengan currency=\"EUR\")." +
                    "\n0. Salir ");
            opcion = sc.nextInt();
            sc.nextLine();

            System.out.println();

            switch (opcion){
                case 1:
                    books = showBooks(fileXML, modo);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 2:
                    System.out.println("Libros publicados después del 2010:");
                    books = showBookAfter2010(fileXML, modo);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 3:
                    System.out.println("Libros con más de un autor:");
                    books = showBooksWithAuthors(fileXML, modo);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 4:
                    books = showBooksWithEUR(fileXML, modo);
                    double average = averageEUR(fileXML, modo);

                    System.out.println("Los libros con curency EUR son:");
                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println("El precio medio de los libros con currency EUR es " + average + " euros.");

                    System.out.println();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;
            }
        }while (opcion != 0);

    }



    public static List<Book> showBooks(File fileXML, String mode) throws Exception {
        if (mode.equalsIgnoreCase("dom")){
            System.out.println("DOM");

            return DomReader.read(fileXML);

        } else if (mode.equalsIgnoreCase("sax")) {
            System.out.println("SAX");

            return SaxReader.read(fileXML);

        }
        // Si no es ninguno de los modos disponibles se muestra mensaje y se devuelve lista vacía.
        else {
            System.out.println("Modo no disponible.");
            return new ArrayList<>();
        }
    }


    public static List<Book> showBookAfter2010(File fileXML, String mode) throws Exception {
        List<Book> booksAfter2010 = new ArrayList<>();

        if (mode.equalsIgnoreCase("dom")){
            System.out.println("DOM");

            List<Book> books = DomReader.read(fileXML);

            for (Book book : books){
                if (book.getYear() > 2010){
                    booksAfter2010.add(book);
                }
            }

            return booksAfter2010;

        } else if (mode.equalsIgnoreCase("sax")) {
            System.out.println("SAX");

            List<Book> books = SaxReader.read(fileXML);

            for (Book book : books){
                if (book.getYear() > 2010){
                    booksAfter2010.add(book);
                }
            }

            return booksAfter2010;
        }
        // Si no es ninguno de los modos disponibles se muestra mensaje y se devuelve lista vacía.
        else {
            System.out.println("Modo no disponible.");
            return new ArrayList<>();
        }

    }


    public static List<Book> showBooksWithAuthors(File fileXML, String mode) throws Exception {
        List<Book> booksAuthors = new ArrayList<>();

        if (mode.equalsIgnoreCase("dom")){
            System.out.println("DOM");

            List<Book> books = DomReader.read(fileXML);

            for (Book book : books){
                if (book.getAuthors().size() > 1){
                    booksAuthors.add(book);
                }
            }

            return booksAuthors;

        } else if (mode.equalsIgnoreCase("sax")) {
            System.out.println("SAX");

            List<Book> books = SaxReader.read(fileXML);

            for (Book book : books){
                if (book.getAuthors().size() > 1){
                    booksAuthors.add(book);
                }
            }

            return booksAuthors;
        }
        // Si no es ninguno de los modos disponibles se muestra mensaje y se devuelve lista vacía.
        else {
            System.out.println("Modo no disponible.");
            return new ArrayList<>();
        }

    }


    public static double averageEUR(File fileXML, String mode) throws Exception {
        List<Book> booksEUR = new ArrayList<>();

        if (mode.equalsIgnoreCase("dom")){
            System.out.println("DOM");

            List<Book> books = DomReader.read(fileXML);

            double totalPrice = 0;

            for (Book book : books){
                if (book.getCurrency().equals("EUR")){
                    totalPrice += book.getPrice();
                    booksEUR.add(book);
                }
            }

            double average = totalPrice / booksEUR.size();

            return average;

        } else if (mode.equalsIgnoreCase("sax")) {
            System.out.println("SAX");

            List<Book> books = SaxReader.read(fileXML);

            double totalPrice = 0;

            for (Book book : books){
                if (book.getCurrency().equals("EUR")){
                    totalPrice += book.getPrice();
                    booksEUR.add(book);
                }
            }

            double average = totalPrice / booksEUR.size();

            return average;

        }
        // Si no es ninguno de los modos disponibles se muestra mensaje y se devuelve 0.0
        else {
            System.out.println("Modo no disponible.");
            return 0.0;
        }

    }


    public static List<Book> showBooksWithEUR(File fileXML, String mode) throws Exception {
        List<Book> booksEUR = new ArrayList<>();

        if (mode.equalsIgnoreCase("dom")){
            System.out.println("DOM");

            List<Book> books = DomReader.read(fileXML);

            for (Book book : books){
                if (book.getCurrency().equals("EUR")){
                    booksEUR.add(book);
                }
            }

            return  booksEUR;

        } else if (mode.equalsIgnoreCase("sax")) {
            System.out.println("SAX");

            List<Book> books = SaxReader.read(fileXML);

            for (Book book : books){
                if (book.getCurrency().equals("EUR")){
                    booksEUR.add(book);
                }
            }

            return  booksEUR;
        }
        // Si no es ninguno de los modos disponibles se muestra mensaje y se devuelve lista vacía.
        else {
            System.out.println("Modo no disponible.");
            return new ArrayList<>();
        }

    }
}
