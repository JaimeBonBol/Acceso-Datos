import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomReader {

    /**
     * Método que lee un archivo XML y devuelve una lista de objetos Book.
     * @param xmlFile
     * @return
     * @throws Exception
     */
    public static List<Book> read(File xmlFile) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        // Parsear (leer) el archivo XML y construir el árbol DOM en memoria.
        Document doc = db.parse(xmlFile);

        // Buscar todos los nodos <book> en el XML.
        NodeList nodesBook = doc.getElementsByTagName("book");

        List<Book> books = new ArrayList<>();

        // Recorrer todos los elementos(nodos) <book> enocnytrados en el XML.
        for (int i = 0; i < nodesBook.getLength(); i++) {

            // Obtener el elemento <book> en la posición i. (Para cada book en el XML)
            Element bookElement = (Element) nodesBook.item(i);  // .item(i) devuelve el nodo en la posición i

            Book book = new Book();

            // Obtener atributos y contenido del XML.

            book.setId(bookElement.getAttribute("id"));

            book.setTitle(textOf(bookElement, "title"));

            book.setYear(parseIntSafe(textOf(bookElement, "year")));

            book.setIsbn(bookElement.getAttribute("isbn"));

            // AUTORES
            // Se buscan todas las etiquetas <author> dentro del <book>
            NodeList nodesAuthor = bookElement.getElementsByTagName("author");

            // Si hay almenos un autor, se inicializa la lista de autores del libro.
            if (nodesAuthor.getLength() > 0 ){
                book.initializeListAuthors();
            }

            // Recorrer cada <author> para obtener su contenido y añadirlo a la lista de autores del libro.
            for (int j = 0; j < nodesAuthor.getLength(); j++) {

                // Obtener el elemento <author> en la posición j. (PAra cada author en el book)
                Element authorElement = (Element) nodesAuthor.item(j);

                book.addAuthor(authorElement.getTextContent().trim());

                // Para obtener el atributo role hay que cogerlo desde el nodo (elemento) author que es donde está ese atributo
                book.setRole(authorElement.getAttribute("role"));
            }

            //CATEGORIAS
            // Se buscan todas las etiquetas <category> dentro del <book>
            NodeList nodesCategory = bookElement.getElementsByTagName("category");

            // Si hay almenos una categoria, se inicializa la lista de categorias del libro.
            if (nodesCategory.getLength() > 0 ){
                book.initializeListCategories();
            }

            // Recorrer cada <category> para obtener su contenido y añadirlo a la lista de categorias del libro.
            for (int j = 0; j < nodesCategory.getLength(); j++) {

                // Obtener le elemento <category> en la posción j. (Para cada category en el bokk).
                Element categoryElement = (Element) nodesCategory.item(j);

                book.addCategory(categoryElement.getTextContent().trim());

            }

            book.setPrice(parseDoubleSafe(textOf(bookElement, "price")));

            // Para obtener el atributo currency hay que cogerlo desde el nodo (elemento) price que es donde está ese atributo
            NodeList nodesPrice = bookElement.getElementsByTagName("price");
            Element priceElement = (Element) nodesPrice.item(0);

            book.setCurrency(priceElement.getAttribute("currency"));

            // Una vez recogidos los atributos y contenidos del libro, se añad eese libro a la lista de libros que devuelve
            // este método.
            books.add(book);

        }

        // Devolver la lista de libros con la información del XMl.
        return books;
    }

    /**
     * Método que devuelve el texto de un elemento dado un padre (punto de partida) y el nombre de la etiqueta.
     * Busca dentro de parent la etiqueta tag y devuelve el texto del primer elemento encontrado; si no existe devuelve "".
     * @param parent
     * @param tag
     * @return
     */
    public static String textOf(Element parent, String tag){

        // Busca todos los elementos con el nombre de la etiqueta "tag"
        // dentro del elemento padre (incluye hijos y descendientes).
        NodeList nl = parent.getElementsByTagName(tag);

        if (nl.getLength() > 0 ){
            return nl.item(0).getTextContent().trim();
        } else {
            return "";
        }
    }


    /**
     * Método para hacer un parseo de String a int seguro, ya que asegura devolver 0 si no hay cadena de texto.
     * @param text
     * @return
     */
    public static int parseIntSafe(String text){
        if (text.isEmpty()){
            return 0;
        }else {
            return Integer.parseInt(text);
        }

        // return s.isEmpty() ? 0 : Integer.parseInt(s);
    }

    /**
     * Método para hacer un parseo de String a double seguro, ya que asegura devolver 0.0 si no hay cadena de texto.
     * @param text
     * @return
     */
    public static double parseDoubleSafe(String text){
        if (text.isEmpty()){
            return 0.0;
        }else {
            return Double.parseDouble(text);
        }

        // return s.isEmpty() ? 0.0 : Double.parseDouble(s);
    }

}
