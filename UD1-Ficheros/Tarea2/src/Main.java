import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        File fileXML = new File("Tarea2/src/catalogo.xml");
        Scanner sc = new Scanner(System.in);
        int opcion;
        List<Book> books;

        do {
            System.out.println("Tarea 2 - Leer XML con DOM");
            System.out.println("Intorduzca opción:" +
                    "\n1. Mostrar todos los libros." +
                    "\n2. Mostrar sólo los títulos publicados después de 2010." +
                    "\n3. Mostrar los libros con más de un autor." +
                    "\n4. Calcular el precio medio en EUR (para los que tengan currency=\"EUR\")." +
                    "\n0. Salir ");
            opcion = sc.nextInt();

            System.out.println();
            switch (opcion){
                case 1:
                    books = showBooks(fileXML);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 2:
                    System.out.println("Libros publicados después del 2010:");
                    books = showBookAfter2010(fileXML);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 3:
                    System.out.println("Libros con más de un autor:");
                    books = showBooksWithAuthors(fileXML);

                    for (Book book : books){
                        System.out.println(book);
                        System.out.println();
                    }

                    System.out.println();
                    break;

                case 4:
                    books = showBooksWithEUR(fileXML);
                    double average = averageEUR(fileXML);

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



    public static List<Book> showBooks(File fileXML) throws Exception {
        return  DomReader.read(fileXML);
    }

    public static List<Book> showBookAfter2010(File fileXML) throws Exception {
        List<Book> books = DomReader.read(fileXML);
        List<Book> booksAfter2010 = new ArrayList<>();

        for (Book book : books){
            if (book.getYear() > 2010){
                booksAfter2010.add(book);
            }
        }

        return booksAfter2010;
    }

    public static List<Book> showBooksWithAuthors(File fileXML) throws Exception {
        List<Book> books = DomReader.read(fileXML);
        List<Book> booksAuthors = new ArrayList<>();

        for (Book book : books){
            if (book.getAuthors().size() > 1){
                booksAuthors.add(book);
            }
        }

        return booksAuthors;
    }

    public static double averageEUR(File fileXML) throws Exception {
        List<Book> books = DomReader.read(fileXML);
        List<Book> booksEUR = new ArrayList<>();

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

    public static List<Book> showBooksWithEUR(File fileXML) throws Exception {
        List<Book> books = DomReader.read(fileXML);
        List<Book> booksEUR = new ArrayList<>();

        for (Book book : books){
            if (book.getCurrency().equals("EUR")){
                booksEUR.add(book);
            }
        }

        return  booksEUR;
    }
}
