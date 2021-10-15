
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;


public class App {
    private static File animales = new File("animales.xml");
    private static final String INDENT_NIVEL= "  ";  // Para indentación
    static Scanner sc = new Scanner(System.in);
    // Creamos el documento recogiendo el xml y combirtiendolo en document
    private static Document doc = null;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        
        int menu = 10;

        while (menu != 0) {
            System.out.println();
            System.out.println("====================== MENU XML : DOM =====================");
            System.out.println("======================== VETERINARIA ======================");
            System.out.println("===========================================================");
            System.out.println("= 1 - Mostrar todos los animales de nuestra base de datos'=");
            System.out.println("= 2 - Cambair altura a uno de nuestros animales           =");
            System.out.println("= 3 - Añadir nuevo animal                                 =");
            System.out.println("= 4 - Borrar animal seleccionado                          =");
            System.out.println("===========================================================");
            System.out.println("==              0 - Para cerrar el programa              ==");
            System.out.println("===========================================================");

            try{
                menu = sc.nextInt();
                

                switch (menu) {
                    case 1:
                        mostrarAnimales();
                        break;

                    case 2:
                        pedirNombreAltura();
                        break;

                    case 3:
                        nuevoAnimalAñadir();
                        break;

                    case 4:
                        eliminarAnimal();
                        break;
                    case 0:
                        return;

                    default:
                        System.out.println("Numero del 1 al 4");

                }
            }catch(InputMismatchException ex){
                
                System.out.println("Debes insertar un número");
                System.out.println("Pulsa intro...");
                // Metodo para pausar el menu
                saltoLinea();
                sc.next();
                
            }
        }
    }

    private static void saltoLinea() throws IOException {
        // Pedir pulsar intro
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }

    private static void eliminarAnimal() {
                 
        try {
            Element todo = doc.getDocumentElement();

            // Creamos una lista de nodos de el documento doc                 
            NodeList nodes = doc.getElementsByTagName("animal");

            // Metodo para ver los animales que tenemos
            mostrarListaAnimales();

            // Pedimos el nombre del animal, para cambiarle la altura
            System.out.println("Dime el nombre del animal que deseas eliminar");
            String nombre = sc.nextLine();

            // Recorremos la lista
            for (int i = 0; i < nodes.getLength(); i++){
                // Guardamos en un elemento de todos los apartados de cada animal
                Element element = (Element) nodes.item(i);
                // Creamos un nodo del element, el cual pasamos los nombres
                Node name = element.getElementsByTagName("nombre").item(0);

                // Si el nombre esta vacio, continua a otra iteracciob
                if (name == null) {
                    continue;
                }  

                // Comvertimos el nodo name en un elemento para modificarlo
                Element nombreAnimal = (Element) name;
                // Metemos en un string el nombre del animal
                String nombreElement = nombreAnimal.getTextContent();

                    // Si es igual el nombre pasado por pantalla, al mismo que hemos recorrido, entonces entramos
                    if (nombre.equals(nombreElement)) {
                        // Guardamos el nodo en el que se encuentra el nombre que queremos borrar
                        Node posicion = nodes.item(i);
                        // Lo borramos
                        todo.removeChild((posicion));

                        System.out.println("Animal borrado de la base de datos");
                    }

            }

            RecargarArchivoXML();
        } 
        catch (Exception e) {
            e.getMessage();
            System.out.println("ATENCION: El perro no existe \n COMPRUEBE LAS MAYUSCULAS Y MINUSCULAS");
        }
                  

    }

    private static void mostrarListaAnimales() {
        // Creamos una lista de nodos de el documento doc                 
        NodeList nodes = doc.getElementsByTagName("animal");

        for (int i = 0; i < nodes.getLength(); i++){
            // Guardamos en un elemento de todos los apartados de cada animal
            Element element = (Element) nodes.item(i);
            // Creamos un nodo del element, el cual pasamos los nombres
            Node name = element.getElementsByTagName("nombre").item(0);
            // Mostramos el contenido del elemento name
            System.out.print(" --" + name.getTextContent());
            
        }
        System.out.println();
    }

    private static void nuevoAnimalAñadir() {


        System.out.println("AÑADIR NUEVO ANIMAL");
                   
        try {
            // definimos el elemento raíz del documento
            Element elementoRaiz = (Element) doc.getElementsByTagName("animales").item(0);
            // doc.appendChild(eRaiz);


             //creamos el elemento animal con su id
            Element elementoAnimal = doc.createElement("animal");
            elementoRaiz.appendChild(elementoAnimal);

            // Añado el id
            Random r = new Random();
            Attr atrib = doc.createAttribute("Id");
            String id = String.valueOf(r.nextInt(1000));
            atrib.setValue(id);
            elementoAnimal.setAttributeNode(atrib);

       

            // Asignamos el nombre
            System.out.println("Escribe el nombre de tu perro");
            String nombre = sc.nextLine();

            Element eNombre = doc.createElement("nombre");
            eNombre.appendChild(doc.createTextNode(nombre));
            elementoAnimal.appendChild(eNombre);

            
            // Asiganamos la raza
            System.out.println("Escribe la raza de tu perro");
            String raza = sc.nextLine();

            Element eRaza = doc.createElement("razas");
            eRaza.appendChild(doc.createTextNode(raza));
            elementoAnimal.appendChild(eRaza);
            
            
            // Introducimos la altura
            System.out.println("Escribe la altura de tu perro");
            String alt = sc.nextLine();

            Element eAlt = doc.createElement("altura");
            eAlt.appendChild(doc.createTextNode(alt + "cm"));
            elementoAnimal.appendChild(eAlt);

            
            // Inrtroducimos el peso

            System.out.println("Escribe el peso de tu perro en KG");
            String peso = sc.nextLine();

            Element ePeso = doc.createElement("peso");
            ePeso.appendChild(doc.createTextNode(peso + "Kg"));
            elementoAnimal.appendChild(ePeso);

            
            // Introducimos la fecha
            System.out.println("Escribe la fecha de nacimiento de tu perro");
            String nacimiento = sc.nextLine();

            Element eFecha = doc.createElement("fechaNacimiento");
            eFecha.appendChild(doc.createTextNode(nacimiento));
            elementoAnimal.appendChild(eFecha);

            System.out.println("Animal añadido correctamente");

            RecargarArchivoXML();
           
        } 
        catch (Exception e) {
            e.getMessage();
        }
    }

    private static void pedirNombreAltura() {

        mostrarListaAnimales();

        // Pedimos el nombre del animal, para cambiarle la altura
        System.out.println("Dime el nombre del animal al cual quieres cambiar la altura");
        String nombre = sc.nextLine();
                   
        try {
            // Creamos una lista de nodos de el documento doc                 
            NodeList nodes = doc.getElementsByTagName("animal");

            // Recorremos la lista
            for (int i = 0; i < nodes.getLength(); i++){
                // Guardamos en un elemento de todos los apartados de cada animal
                Element element = (Element) nodes.item(i);
                // Creamos un nodo del element, el cual pasamos los nombres
                Node name = element.getElementsByTagName("nombre").item(0);
                // Si el nombre esta vacio, continua a otra iteracciob
                if (name == null) {
                    continue;
                }            

                // Comvertimos el nodo name en un elemento para modificarlo
                Element nombreAnimal = (Element) name;
                // Metemos en un string el nombre del animal
                String nombreElement = nombreAnimal.getTextContent();

                    // Si es igual el nombre pasado por pantalla, al mismo que hemos recorrido, entonces entramos
                    if (nombre.equals(nombreElement)) {
                        // Buscamos y creamos un nodo de altura
                        Node altura = element.getElementsByTagName("altura").item(0);
                        // Si altura no eiste, la creamos

                        if (altura == null) {
                            Element alt = doc.createElement("altura");
                            element.appendChild(alt);
                            // Refrescamos
                            altura = element.getElementsByTagName("altura").item(0);
                        }
                        // combertimos el nodo altura en un elemento para su modificacion
                        Element alturnaElement = (Element) altura;
                        // Pedimos introducir una nueva altura
                        System.out.println("Introduce nueva altura para el animal");
                        String nuevaAltura = sc.nextLine();

                        // Cambiamos la altura
                        alturnaElement.setTextContent(nuevaAltura); 
                        
                        System.out.println("Altura modificada correctamente");

                        // Metodo para guardar el DOM al archivo
                        RecargarArchivoXML();
                        
                    }
            }


        }catch (Exception e) {
                e.getMessage();
            }
        
        
    
        
    }

    private static void RecargarArchivoXML() throws TransformerException {

        // Crear objeto transformer 
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // Para poder editar el archivo
        Transformer transformer = transformerFactory.newTransformer();
        // Formatea el arbol de datos en un documento
        DOMSource domSource = new DOMSource(doc);
        StreamResult strResult = new StreamResult(animales);
        // Lo transeforma
        transformer.transform(domSource, strResult);  
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
            doc = dBuilder.parse(animales);
            // Lista para guardar el documento y poder utilizar sus datos

            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document domDoc = db.parse(animales);

            // Llamamos a muestraNodo y  le pasamos el domDOC
            muestraNodo(domDoc, 0, System.out);

        } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {  // Excepciones
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
