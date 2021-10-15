import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class App {
    static List<Pilotos> lst = new ArrayList<>();
    static List<Pilotos> recogePilotos = new ArrayList<>();
    
    
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        int menu = 10;
        while (menu!=0) {
            System.out.println();
            System.out.println("==================== MENU FORMULA 1 ====================");
            System.out.println("========================================================");
            System.out.println(" 1 - Introducir Pilotos (Ejecución Por Primera Vez)");
            System.out.println(" 2 - Recoger datos del fichero");
            System.out.println(" 3 - Añadir carrera con sus puntos");
            System.out.println("========================================================");
            System.out.println("               0 - Para cerrar el programa");

            try{
            menu = sc.nextInt();
            
            switch (menu) {
                case 1:
                introducirPilotos();
                break;

                case 2:
                recogerPilotos();
                    break;

                case 3:
                añadirPuntuacion();
                    break;
                
                default:
                break;
            }
            } catch(InputMismatchException ex){
                System.out.println("Debes insertar un número");
                sc.next();
            }
            
        }

    
    }
    
    private static void añadirPuntuacion() {
        // Hacemos un list con los diferentes puntos
        List<Integer> puntuacion = new ArrayList<Integer>();
        puntuacion.add(25);
        puntuacion.add(20);
        puntuacion.add(18);
        puntuacion.add(15);
        puntuacion.add(10);
        
        // Hcacemos la copia del array anterior
        int copia[]= new int[5];
        // Creo la clase random
        Random rnd = new Random();

         for (int i = 0; i < copia.length; i++) {
            
            // Genero un número random
            int numeroRandom = puntuacion.get(rnd.nextInt(puntuacion.size()));                    
            copia[i] = numeroRandom;
            int pos = puntuacion.indexOf(copia[i]);
            puntuacion.remove(pos);
           
        }
        
        int contador = 0;
        for (Pilotos item : recogePilotos) {

            int puntos = item.getPuntos();
            item.setPuntos(puntos + copia[contador]);
            contador++;

            System.out.println(item.getNombre() + " ---> Escuderia: " + item.getEscuderia() + " , Fecha de nacimiento: " + item.getFechaNacimiento() + " , Dorsal: " + item.getDorsal() + " Puntuacion: " + item.getPuntos() );

            
        }
        try {
            File rutaArchivo = new File("D:\\Users\\damA\\Desktop\\Formula1.txt");

            FileOutputStream fls = new FileOutputStream(rutaArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(fls);
            salida.writeObject(recogePilotos);
        } catch (Exception e) {
            
            e.printStackTrace();

        }



        

        
        
    }

    private static void recogerPilotos() {
        
        try {
            File rutaArchivo = new File("D:\\Users\\damA\\Desktop\\Formula1.txt");

            FileInputStream fls = new FileInputStream(rutaArchivo);
            ObjectInputStream entradaDatos = new ObjectInputStream(fls);
            recogePilotos = (List<Pilotos>)entradaDatos.readObject();

        } catch (Exception e) {           
            System.out.println(e.getMessage());
        }

        for (Pilotos pilotos : recogePilotos) {
            System.out.println(pilotos.getNombre() + " ---> Escuderia: " + pilotos.getEscuderia() + " , Fecha de nacimiento: " + pilotos.getFechaNacimiento() + " , Dorsal: " + pilotos.getDorsal() + " Puntuacion: " + pilotos.getPuntos());
        }
    }

    private static void introducirPilotos() {
        Pilotos pilotos1 = new Pilotos("D. Ricciardo","McLaren", LocalDate.of(1989,07,01),3,25);
        Pilotos pilotos2 = new Pilotos("L. Norris","McLaren", LocalDate.of(1999,11,13),4,20);
        Pilotos pilotos3 = new Pilotos("V. Bottas","Mercedes", LocalDate.of(1987,11,22),77,18);
        Pilotos pilotos4 = new Pilotos("C. Leclerc","Ferrari", LocalDate.of(1983,05,14),16,15);
        Pilotos pilotos5 = new Pilotos("S. Perez","Red Bull", LocalDate.of(1989,10,07),11,10);

        lst = Arrays.asList(pilotos1,pilotos2,pilotos3,pilotos4,pilotos5);

        try {
            File rutaArchivo = new File("D:\\Users\\damA\\Desktop\\Formula1.txt");

            FileOutputStream fls = new FileOutputStream(rutaArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(fls);
            salida.writeObject(lst);
        } catch (Exception e) {
            
            e.printStackTrace();

        }

        for (Pilotos item : lst) {
            System.out.println(item);
            
        }
        System.out.println();
        System.out.println("===== Los Pilotos se han guardado exitosamente en el escritorio, en el archivo Formula1.txt ====");

    }
}
