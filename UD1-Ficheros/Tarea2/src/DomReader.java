import org.w3c.dom.NodeList;

import org.w3c.dom.Element;

public class DomReader {

    // Método para recoger el texto de un elemento.
    public static String textOf(Element parent, String tag){

        // Se obtiene un NodeList desde el punto de partida que le pasamos (De tipo elemento), y con la etiqueta que
        // queremos buscar.
        NodeList nl = parent.getElementsByTagName(tag);

        // Si es mayor de 0, es decir, si tiene elemento, retorna el texto del primer elemento ( Ya que este método
        // es para que retorne el texto de un elemento). Si no hay elementos con ese tag, devuelve cadena vacía.
        if (nl.getLength() > 0 ){
            return nl.item(0).getTextContent().trim();
        } else {
          return "";
        }
    }

}
