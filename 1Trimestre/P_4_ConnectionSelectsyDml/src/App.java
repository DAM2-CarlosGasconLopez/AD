
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void muestraErrorSQL(SQLException e) {
  
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  }


  public static dbConnection connection = new dbConnection();
  public static Connection con = connection.getConnection();

  public static PreparedStatement ps = null;

  public static List<Empleado> empleados = new ArrayList<>();
  



  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);

    int menu = 10;

    while (menu != 0) {
      System.out.println();
      System.out.println("================================== EJERCICIO 2 ================================");
      System.out.println("==================  Aplicación DDL y CRUD a la base de datos ==================");
      System.out.println("===============================================================================");
      System.out.println("===  1 - Cree una tabla con las categorías de los empleados.                ===");
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  2 - Añada a la tabla empleados una nueva FK que permita                ===");
      System.out.println("===      saber qué categoría tiene cada empleado.                           ===");
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  3 - La rellene con datos (al menos 5 categorías).                      ===");
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  4 - Asigne  a  cada empleado una  categoría, con  participación  del   ===");
      System.out.println("===      usuario de la aplicación  que decidirá  de entre  las categorías   ===");
      System.out.println("===      disponibles la que quiere asignarle a cada empleado.               ===");
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  5 - Ejecute  un procedimiento  almacenado en  la base de  datos  que   ===");
      System.out.println("===      tendrás que crear y que subirá el salario o un x%  (parámetro de   ===");
      System.out.println("===      entrada al  procedimiento)  a  cada empleado.                      ===");
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  6 - Crearás  un  método  en java que  permita  listar  a  todos  los   ===");
      System.out.println("===      empleados,  incluyendo  su  categoría  y salario  base.  Dale un   ===");
      System.out.println("===      formato legible. Tendrás que  mostrar un warning en caso de  que   ===");
      System.out.println("===      un  empleado  que  no  sea  de  la categoría  “encargado” sea el   ===");
      System.out.println("===      responsable de una  de las áreas de la tienda  (fuerza los datos   ===");
      System.out.println("===      para que esto pase)                                                ==="); 
      System.out.println("===  --------------------------------------------------------------------   ===");
      System.out.println("===  7 - Comprobar conexion a la base de datos                              ===");     
      System.out.println("===============================================================================");
      System.out.println("===                       0 - Para CERRAR el programa                       ===");
      System.out.println("===============================================================================");

      try {
        menu = sc.nextInt();

        switch (menu) {
        case 1:
          Ejercicio1();
          break;

        case 2:
          Ejercicio2();
          break;

        case 3:
          Ejercicio3();
          break;

        case 4:
          Ejercicio4();
          break;
        case 5:
          Ejercicio5();
          break;
        case 6:
          Ejercicio6();
          break;
        case 7:
          connection.getConnection();
          saltoLinea();
          
          break;
        case 0:
        // Cerramos la conexión para que no tengas problemas de sobrecarga
          try {
            con.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          return;

        default:
          System.out.println("Porfavor, seleccione un número del 1 al 6");

        }
      } catch (InputMismatchException ex) {

        System.out.println("Debes insertar un número");
        System.out.println("Pulsa intro...");
        // Metodo para pausar el menu
        saltoLinea();
        sc.next();

      }
     
    }

  }

  private static void Ejercicio1() {
    // Creamos las variables para hacer las consultas
    String sqlTable = "";
    String sqlInsert = "";
    // Consulta para crear una tabla
    sqlTable = "create table categoria("
          + "cod_categoria int auto_increment,"
          + "descripcion varchar(100),"
          + "salario_base int,"
          + "constraint primary key (cod_categoria));";

    // Consulta para insertar datos en la tabla
    sqlInsert = "insert into categoria (descripcion,salario_base)"
              + "values ('Vendedor'," + 1000 + "),('Reponedor'," + 900 + "),('Encargado'," + 1500 + ");";


    try {
      // Preparamos y ejecutamos la creacion de la tabla
      ps = con.prepareStatement(sqlTable);
      ps.executeUpdate();
      // Preparamos y ejecutamos la inserccion de los datos a la tabla recien creada
      ps = con.prepareStatement(sqlInsert);
      ps.executeUpdate();
      
    } catch (Exception e) {     
    }


  }

  private static void Ejercicio2() {
    // Creamos las variables para hacer las consultas
    String sqlAdd = "";
    String sqlFk = "";
    // Consulta para añadir el campo categoria a la tabla empleados
    sqlAdd = "alter table empleados"
              + " add categoria int;";

    // Consulta para añadir la foreign key entre empleados y categoria por el cod_categoria
    sqlFk = "alter table empleados"
              + " add constraint fk foreign key (categoria) references categoria(cod_categoria);";


    try {
      // Preparamos y ejecutamos el nuevo campo en empleados
      ps = con.prepareStatement(sqlAdd);
      ps.executeUpdate();
      // Preparamos y ejecutamos creación de la foreign key
      ps = con.prepareStatement(sqlFk);
      ps.executeUpdate();
      
    } catch (Exception e) {     
    }
  }

  private static void Ejercicio3() {
    // Creamos las variables para hacer las consultas
    String sqlAdd = "";
    // Consulta para añadir categorias
    sqlAdd = "insert into categoria (descripcion,salario_base)" 
           + " values ('Limpieza'," + 900 + "),('Secretaría'," + 1200 + "),('Conductor Carretilla'," + 1200 + "),('Conductor Camion'," + 1500 + "),('Encargado'," + 1300 + ");";
    

    try {
      // Preparamos y ejecutamos la inserccion de los datos a la tabla categorias
      ps = con.prepareStatement(sqlAdd);
      ps.executeUpdate();

      
    } catch (Exception e) {     
    }
  }

  private static void Ejercicio4() {
    // Creamos las variables para hacer las consultas
    String sqlAll = "";
    String sqlContCategorias = "";
    String sqlAddCategoria = "";

    // Selects para mostrar tipos de categorias
    sqlAll = "select cod_categoria,descripcion from categoria;";

    // Select para ver cuantas categorias hay
    sqlContCategorias = "select min(cod_categoria),max(cod_categoria) from categoria;";

    // Select para ver el nombre del empleado
    sqlAddCategoria = "select cod_emple, nombre, categoria from empleados;";
    

    try {
      // Ejecutamos para ver las categorias
      ps = con.prepareStatement(sqlAll);
      // Recogemos los resultados de los selects
      ResultSet rsAll = ps.executeQuery();

      // Rango categorias
      ps = con.prepareStatement(sqlContCategorias); // Rango de categorias guardadas em sql     
      ResultSet rsContCategoria = ps.executeQuery();

      //Nombres de empleados
      ps = con.prepareStatement(sqlAddCategoria); // Rango de categorias guardadas em sql     
      ResultSet rsAddCategoria = ps.executeQuery();
      

      // Sacar cuantas categorias tenemos
      int maxNum = 0;
      int minNum = 0;

      // Guardamos los resultados en una variable, para poder posteriormente mostrar los nombres los difirentes trabajos
      // Lo metemos en un metodo en el cual me mostrara todas las categorias
  
      longitudCategorias(rsContCategoria, maxNum, minNum);

      // Genero el scanner
      Scanner sc = new Scanner(System.in);


      // Genero una variable int
      int tipo_de_categoria = 0;



      while (rsAddCategoria.next()) {
        Empleado emp = new Empleado();
        emp.setCod_emple(rsAddCategoria.getInt(1));
        emp.setNombre(rsAddCategoria.getString(2));
        emp.setCategoria(rsAddCategoria.getInt(3));
        
        // Añadimos el empleado
        empleados.add(emp);
         
      }

      for (Empleado empleado : empleados) {
        
        //listamos las categorias
        lstCategorias(rsAll);

        System.out.println();
                     
        // Pedimos la categoria
        System.out.println( empleado.getNombre() + " --> Elige el tipo de categoria (entero):");

        //if( sc.nextInt() > minNum  &&  sc.nextInt() <= maxNum ){ 
          tipo_de_categoria = sc.nextInt();
        //}

          String sqlUpdate = "update empleados set categoria = " + tipo_de_categoria + " where cod_emple = " + empleado.getCod_emple();
          ps = con.prepareStatement(sqlUpdate);
          ps.executeUpdate();
      }
       
    } catch (Exception e) {   

    }
  }


  private static void longitudCategorias(ResultSet rsContCategoria, int maxNum, int minNum) throws SQLException {
    while (rsContCategoria.next()) {
      // recogemos los numeros necesarios
      minNum = rsContCategoria.getInt(1);
      maxNum = rsContCategoria.getInt(2);

    }

    System.out.println(" " + minNum + "   " + maxNum);
  }

  private static void lstCategorias(ResultSet rsAll)
      throws SQLException {
         //Creamos un array para guardar los datos
      String categoria[] = new String[2];

      String visualizarCategorias = "";
      
      // creo un contador
      int separar = 0;
    while (rsAll.next()) {
      // Recogemos el primer y segundo resultado
      categoria[0] = rsAll.getString(1);
      categoria[1] = rsAll.getString(2);

      // Separa por bloques de tres categorias
        if (separar==3) {
          // le pongo un salto de linea
          visualizarCategorias = visualizarCategorias+ "\n" + categoria[0] + " -- " + categoria[1] + "  |  ";
          separar = 0;
        }else{
          visualizarCategorias = visualizarCategorias + categoria[0] + " -- " + categoria[1] + "  |  ";
        }
        // contador a 0
      separar++;
      }
    // Visualizamos los empleos
    System.out.println(visualizarCategorias);
  }

  private static void Ejercicio5() {
  }

  private static void Ejercicio6() {
  }

  private static void saltoLinea() throws IOException {
    // Pedir pulsar intro
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    br.readLine();
  }
}


