import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String mode = "dom";
        File fileXML = new File("Tarea2/src/catalogo.xml");

        List<Book> books = new ArrayList<>();
        switch (mode){
            case "dom":
                try {
                    books = DomReader.read(fileXML);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                System.out.println("Opción no válida");
                break;
        }

        for (Book book : books){
            System.out.println(book);
            System.out.println();
        }

    }
}
