import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



public class App {
    public static void main(String[] args) throws Exception {
        // creamos el menu con los ejercicios a realizar
        System.out.println("Ejericio Practica 1 'Ficheros'");
        System.out.println("======================================================");
        System.out.println("\nSeleccione un ejercicio");
        System.out.println("======================================================");

        // Opciones
        System.out.println("1 - A partir de una ruta (preguntada o almacenada) cree el siguiente árbol");
        System.out.println("2 - Una vez creados todos los archivos y directorios, renombra el directorio “Ensayos como “Divulgación”");
        System.out.println("3 - Listar el contenido del directorio “Misterio”");
        System.out.println("4 - Listar recursivamente todo el contenido de la biblioteca");
        System.out.println("5 - Elimina el fichero “come, reza, ama” y vuelve a listar toda la biblioteca");
        System.out.println("6 - Elimina el directorio “Novelas” y todo su contenido. Vuelve a listar");
        System.out.println("7 - Copiar el contenido:"); 
        
        // Declaramos el scanner
        Scanner sc = new Scanner(System.in);
        // Recogemos la opción
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                Ejericio1();
                break;
            case 2:
                Ejericio2();
                break;
            case 3:
                Ejercicio3();
                break;
            case 4:
                // Ruta
                File ruta = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos");
                // Separador
                String separator = "  -";
                Ejercicio4(ruta, separator);
                break;
            case 5:
                Ejercicio5();
                break;
            case 6:
                Ejercicio6();
                break;
            case 7:
                Ejericio7();
                break;
            
            default:
                break;
        }

    
    }

    private static void Ejericio1() {

        // Buscamos la ruta donde queremos generar el arbol     
        String rutaArbol = "D:\\Users\\damA\\Desktop";

        // Creamos la biblioteca con nuestro nombre
        File biblioteca = new File(rutaArbol, "Biblioteca De Carlos");
        if (!biblioteca.exists()) {
            biblioteca.mkdir();            
            System.out.println("El directorio 'Biblioteca de carlos' se ha creado correctamente");
        }else{
            System.out.println("Biblioteca De Carlos ya existe");
        }
       
            /* NOVELAS arbol */ {
            File novelas = new File(biblioteca, "Novelas");
            if (!novelas.exists()) {
                novelas.mkdir();               
            }else{
                System.out.println("Novelas ya existe");;
            }
                
                    // come, reza, ama
                    File come = new File(novelas, "come,reza,ama.txt");

                    try {
                      // A partir del objeto File creamos el fichero físicamente
                      if (come.createNewFile()){
                      System.out.println("El fichero 'come,reza,ama' se ha creado correctamente");
                      }else{
                      System.out.println("come,reza,ama.txt ya existe");
                      }
                    } catch (IOException excepcion) {
                    excepcion.printStackTrace();
                    }

                    // Historicas
                    File historicas = new File(novelas, "Históricas");
                    if (!historicas.exists()) {
                        historicas.mkdir();               
                        System.out.println("El directorio 'Historicas' se creado correctamente");
                    }else{
                        System.out.println("Históricas ya existe");
                    }

                        // Las puertas templarias
                        File puertastemplarias = new File(historicas,"LasPuertasTemplarias.txt");

                        try {
                            // A partir del objeto File creamos el fichero físicamente
                            if (puertastemplarias.createNewFile()){
                            System.out.println("El fichero 'Las Puertas templarias.txt' se ha creado correctamente");
                            }else{
                            System.out.println("Las Puertas templarias.txt ya existe");
                            }
                        }catch (IOException excepcionPuertas) {
                          excepcionPuertas.printStackTrace();
                        }
                    }

            /* Ensayos */  {
            File ensayos = new File(biblioteca, "Ensayos");
            if (!ensayos.exists()) {
                ensayos.mkdir();
                System.out.println("El directorio 'Ensayos' se ha creado correctamente");
            }else{
                System.out.println("Ensayos ya existe");
            }
                // El Jamon De York No Existe
                File jamon = new File(ensayos, "ElJamonDeYorkNoExiste.txt");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (jamon.createNewFile()){
                    System.out.println("El fichero 'ElJamonDeYorkNoExiste.txt' se ha creado correctamente");
                    }else{
                    System.out.println("ElJamonDeYorkNoExiste.txt ya existe");
                    }
                }catch (IOException excepcionjamon) {
                  excepcionjamon.printStackTrace();
                }
                }

            /* Misterio */ {

            
            File misterio = new File(biblioteca, "Misterio");
            if (!misterio.exists()) {
                misterio.mkdir();
                System.out.println("El directorio 'Misterio' se ha creado correctamente");
            }
                // Reina Roja
                File reina = new File(misterio, "ReinaRoja.txt");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (reina.createNewFile()){
                    System.out.println("El fichero 'ReinaRoja.txt' se ha creado correctamente");
                    }else{
                    System.out.println("ReinaRoja.txt ya existe");
                    }
                }catch (IOException excepcionreina) {
                  excepcionreina.printStackTrace();
                }
                
                // Cicatriz
                File cicatriz = new File(misterio, "Cicatriz.txt");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (cicatriz.createNewFile()){
                    System.out.println("El fichero 'Cicatriz.txt' se ha creado correctamente");
                    }else{
                    System.out.println("Cicatriz.txt ya existe");
                    }
                }catch (IOException excepcioncicatriz) {
                  excepcioncicatriz.printStackTrace();
                }
                }
            
            /* Harry Potter */ {
                File potter = new File(biblioteca, "HarryPotter.txt");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (potter.createNewFile()){
                    System.out.println("El fichero 'HarryPotter.txt' se ha creado correctamente");
                    }else{
                    System.out.println("HarryPotter.txt ya existe");
                    }
                }catch (IOException excepcionpotter) {
                  excepcionpotter.printStackTrace();
                }
            }
            
            /* El tiempo entre costuras */ {
                File tiempo = new File(biblioteca, "ElTiempoEntreCosturas.txt");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (tiempo.createNewFile()){
                    System.out.println("El fichero 'ElTiempoEntreCosturas.txt' se ha creado correctamente");
                    }else{
                    System.out.println("ElTiempoEntreCosturas.txt ya existe");
                    }
                }catch (IOException excepciontiempo) {
                  excepciontiempo.printStackTrace();
                }
            }          



        }
                
                
    private static void Ejericio2() {
        // Busco ruta de Ensayos
        File rutaEnsayo = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos\\Ensayos");   
        // Nueva Ruta, retrocedemos con \.. y añadimos el nuevo nombre
        File nuevoNombre = new File(rutaEnsayo + "\\..\\Divulgación");

        // si se renombra, mostramos una opcion, sino otra
        if (rutaEnsayo.renameTo(nuevoNombre)) {

            System.out.println("Archivo Renombrado Correctamente (Divulgacion)");
        }else{
            System.out.println("Error, no se ha renombrado el directorio");
        }
    }

    private static void Ejercicio3() {
        // Buscamos la ruta
        File misterio = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos\\Misterio"); //directorio a listar                                             
        // Guardamos el contenido en una array list
        String[] lst = misterio.list();
        // ordenamos la lista
        Arrays.sort(lst); 
        // Recorremos la lista para mostrarla   
        for (int i = 0; i < lst.length; i++) {
            System.out.println(lst[i]);
        }
    }

    

    private static void Ejercicio4(File ruta, String separator) {
        // el metodo recibe la ruta de biblioteca y un separador
        // Los archivos los ponemos en un array listando loas archvos
        File[] contenido = ruta.listFiles();

        // si el array contiene archivos
        if(contenido != null){
            // Recorremos el array
            for (int i = 0; i < contenido.length; i++) {
                // Mostramos el archvio
                System.out.println(separator + contenido[i].getName());
                // si el contenido recorrido, es directorio, entonces llamamos otra vez al metodo
                // Pasandole solo el directorio primeramente recorrido, es decir la posicion de [i]
                if (contenido[i].isDirectory()) {
                    // sumamos el separador para una mejor apariencia
                    String nuevo_separador = separator + "--";
                    Ejercicio4(contenido[i],nuevo_separador);
                }
            }
        }
    }

    private static void Ejercicio5() {
        
        // Ruta en la que se encuentra el archivo
        File rutaComeRezaAma = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos\\Novelas\\come,reza,ama.txt");

            // Si no se borra el archivo lanzamos el error
            if (!rutaComeRezaAma.delete()) {

                System.out.println("Error no se ha podido eliminar el  archivo");

           }else{
               // si se borra lanzamos el aprobado

                System.out.println("Se ha eliminado el archivo exitosamente");

           }
        System.out.println("==========================================================");
        
        // Ruta de la biblioteca
        File rutaBiblio = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos");
        // Creamos el separador
        String separator = "  -";
        
        // Reutilizamos el metodo del Ejercicio4, en el que le pasamos la ruta y el separador para mostrar la biblioteca
        Ejercicio4(rutaBiblio, separator);


    }

    private static void Ejercicio6() {
        // Ruta en la que se encuentra el directorio
        // Ponemos lo que encuentra en un array listando los ficheros
        File path = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos\\Novelas");

        // Llamamos al metodo de borrado
        sistemaDeBorrado(path);
        System.out.println("==========================================================");
        
        // Ruta de la biblioteca
        File rutaBiblio = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos");
        // Creamos el separador
        String separator = "  -";
        
        // Reutilizamos el metodo del Ejercicio4, en el que le pasamos la ruta y el separador para mostrar la biblioteca
        Ejercicio4(rutaBiblio, separator);
    }

    private static void sistemaDeBorrado(File path){

        // Ponemos lo que encuentra en un array listando los ficheros
        File[] rutaNovelas = path.listFiles();
        // Si contiene algo la ruta, entonces...
        if(rutaNovelas!=null) { 
            // recorremos el array
            for(File ruta: rutaNovelas) {
                // Si lo que hay dentro es un directorio, volvemos a llamar a el metodo
                if(ruta.isDirectory()) {
                    sistemaDeBorrado(ruta);
                } else {
                // sino borramos el archivo que se encuentra
                System.out.println("- "+ruta.getName() + " ha sido elimiando correctamente");
                ruta.delete();
                }
            }
        }
        System.out.println("- " +path.getName() + " ha sido elimiando correctamente");
        path.delete();

    }
    
    private static void Ejericio7() {
        File rutaMisterio = new File("D:\\Users\\damA\\Desktop\\Biblioteca De Carlos\\Misterio");

        File miarchivo = new File(rutaMisterio, "CarlosGascónLópez.txt");
        File tuarchivo = new File(rutaMisterio, "Maria.txt");
        // Archivo Carlos
        if (!miarchivo.exists()) {
            
            try {
                // A partir del objeto File creamos el fichero físicamente
                if (miarchivo.createNewFile()){
                    System.out.println("El fichero 'CarlosGascónLópez.txt' se ha creado correctamente");
                }
            } catch (IOException excepcion) {
                excepcion.printStackTrace();
            }
        }else{
            System.out.println("'CarlosGascónLópez.txt' ya existe");
        }
        
        // Archivo Maria
        if (!tuarchivo.exists()) {
            
            try {
                // A partir del objeto File creamos el fichero físicamente
                if (tuarchivo.createNewFile()){
                    System.out.println("El fichero 'Maria.txt' se ha creado correctamente");
                }
            } catch (IOException excepcion) {
                excepcion.printStackTrace();
            }
        }else{
            System.out.println("'Maria.txt' ya existe");
        }
            
        
    }
    





}
