
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;


public class App {
    static File animales = new File("D:\\Users\\damA\\Desktop\\DAM-2\\AD\\Trimestre 1\\P_3_XML-DOM\\archivos\\animales.xml");
    private static final String INDENT_NIVEL = "  ";  // Para indentación

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        
        int menu = 10;

        while (menu != 0) {
            System.out.println();
            System.out.println("==================== MENU XML : DOM ====================");
            System.out.println("====================== VETERINARIA =====================");
            System.out.println("========================================================");
            System.out.println(" 1 - Mostrar datos del fichero 'Animales.xml'");
            System.out.println("========================================================");
            System.out.println("               0 - Para cerrar el programa");

            try{
                menu = sc.nextInt();
                

                switch (menu) {
                    case 1:
                        mostrarAnimales();
                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    default:
                        System.out.println("Numero entre 1 y 7");
                }
            }catch(InputMismatchException ex){
                System.out.println("Debes insertar un número");
                sc.next();
            }
        }
    }

    private static void mostrarAnimales() {
        // Creamos la instancia
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        
        

        try {
            // Creamos el builder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Creamos el documento recogiendo el xml y combirtiendolo en document
            Document doc = dBuilder.parse(animales);
            // Lista para guardar el documento y poder utilizar sus datos

            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document domDoc = db.parse(animales);
            muestraNodo(domDoc, 0, System.out);
        } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void muestraNodo(Node nodo, int nivel, PrintStream ps) {
        if (nodo.getNodeType() == Node.TEXT_NODE) { // Ignora textos vacíos
            String text = nodo.getNodeValue();
            if (text.trim().length() == 0) {
                return;
            }
        }
        for (int i = 0; i < nivel; i++) {    // Indentación
            ps.print(INDENT_NIVEL);
        }
        switch (nodo.getNodeType()) {  // Escribe información de nodo según tipo
            case Node.DOCUMENT_NODE:  // Documento
                Document doc = (Document) nodo;
                ps.println("Documento DOM, versión: " + doc.getXmlVersion()
                        + ", codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:    // Elemento
                ps.print("<" + nodo.getNodeName());
                NamedNodeMap listaAtr = nodo.getAttributes();  // Lista atributos
                for (int i = 0; i < listaAtr.getLength(); i++) {
                    Node atr = listaAtr.item(i);
                    ps.print(" @" + atr.getNodeName() + "[" + atr.getNodeValue() + "]");
                }
                ps.println(">");
                break;
            case Node.TEXT_NODE:    // Texto
                ps.println(nodo.getNodeName() + "[" + nodo.getNodeValue() + "]");
                break;
            default:    // Otro tipo de nodo
                ps.println("(nodo de tipo: " + nodo.getNodeType() + ")");
        }
        NodeList nodosHijos = nodo.getChildNodes();    // Muestra nodos hijos
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            muestraNodo(nodosHijos.item(i), nivel + 1, ps);
        }
    }
}
