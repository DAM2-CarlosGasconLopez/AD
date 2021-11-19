
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Administrador.dentroAdmin;
import Interfaz.MovimientosToro.AddToro;
import Interfaz.MovimientosVaca.AddVaca;
import Interfaz.MovimientosVaca.DeleteVaca;
import Interfaz.MovimientosVaca.ModifyVaca;
import Objetos.Muertes;
import Objetos.Terneros;
import Objetos.Toros;
import Objetos.Vacas;
import RefrescarTabla.tabla;
import mySQL_XML.DbConnection;

public class App extends JFrame implements ActionListener {

  // CONEXION
  public static DbConnection dbconnection = new DbConnection();
  public static Connection con = null;
  //************************************************************************************************

  // Declaracion Variables del MENU INTERFAZ
  private JMenu vacas;
  private JMenu toros;
  private JMenu terneros;
  private JMenu muertes;
  private JMenuBar menuPrincipal;
  private JMenuItem mostrarVacasMenu;
  private JMenuItem mostrarTorosMenu;
  private JMenuItem mostrarTernerosMenu;
  private JMenuItem mostrarMuertesMenu;
  private JMenuItem insertarVacasMenu;
  private JMenuItem insertarToroMenu;
  private JMenuItem modificarVacasMenu;
  private JMenuItem borrarVacasMenu;
  private JScrollPane jScrollPane1;
  private JTable tablaDatos;
  //************************************************************************************************

  // LISTAS ANIMNALES
  static List<Vacas> arrayVacas = new ArrayList<Vacas>();
  static List<Toros> arrayToros = new ArrayList<Toros>();
  static List<Terneros> arrayTerneros = new ArrayList<Terneros>();
  static List<Muertes> arrayRazas = new ArrayList<Muertes>();

  // CONTRASEÑA admin usada en el metodo 'comprobarContraseñaAdministrador()'
  static final String passwd = "Admin1234";
  //************************************************************************************************



  
  // Inicializamos los componentes de la interfaz
  public App()  throws SQLException{    

    intiComponents();

    try {
     
      actualizarMadres();
     
    } catch (SQLException e) {

      System.out.println("Inserta una tabla mediante el usuario administrador");
      //new App().setVisible(false);
    }

  }
  //************************************************************************************************
  

  // INICIACION DE LA APP EN EL MAIN
  public static void main(String[] args) throws IOException, SQLException {
    
    // Aqui comprobamos la conexíon 

    comprobarConexion();
    Scanner sc = new Scanner(System.in);
        
    int menu = 10;

    while (menu != 0) {
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("=========== GESTION GANADERA por Carlos Gascón López ==========");
        System.out.println("======================= VENTANA USUARIOS ======================");
        System.out.println("===============================================================");
        System.out.println("=== 1 - Usuario Ganadero                                    ===");
        System.out.println("=== 2 - Usuario Administrador                               ===");
        System.out.println("===============================================================");
        System.out.println("===             0 - Para cerrar el programa                 ===");
        System.out.println("===============================================================");

        try{
            menu = sc.nextInt();

            switch (menu) {
                case 1:

                  //Comprobamos conexión
                  comprobarConexion();
                  // Llamamos al metodo run en el que mostramos los 
                  // componentes que tenemos de la interfaz grafica
                  inicializarInterfaz(); 
 
                    break;

                case 2: 
                  // Comprobamos la contraseña del Administrador                  
                  comprobarContraseñaAdministrador(sc);
                  
                break;

                case 0:
                    return;

                default:
                    System.out.println("Numero del 1 al 2");

            }
        }catch(InputMismatchException ex){
            
            System.out.println("Debes insertar un número");
            System.out.println("Pulsa intro...");
            // Metodo para pausar el menu
          
            sc.next();
            
        }
    }
    
      
  }
  //***********************************************************************************************


  // Metodos llamados en el switch MENU

  private static void inicializarInterfaz() {

    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        try {
          new App().setVisible(true);

        } catch (SQLException e) {
          System.out.println("Error en el metodo Runnable");
        }
      }
    });
  }

  private static void comprobarContraseñaAdministrador(Scanner sc) throws SQLException {
    String contraseña;
    System.out.println("Introduce contraseña de Administrador");
    sc.nextLine();
    while (true) {
      
      contraseña = sc.nextLine();
      if (contraseña.equals(passwd)) {
        gestionAdministrador(sc);
        break;    

      }else{
        System.out.println("Contraseña incorrecta, vuelve a introducirla");
      }

    }
  }

  private static void gestionAdministrador(Scanner sc) throws SQLException{
    int menu = 10;

    while (menu != 0) {
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("=========== GESTION GANADERA por Carlos Gascón López ==========");
        System.out.println("===================== GESTION AMINISTRADOR ====================");
        System.out.println("===============================================================");
        System.out.println("=== 1 - Crear una tabla                                     ===");
        System.out.println("=== 2 - Modificar una tabla                                 ===");
        System.out.println("===============================================================");
        System.out.println("===             0 - Para cerrar el programa                 ===");
        System.out.println("===============================================================");

        try{
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    dentroAdmin.crearTabla();
                    break;

                case 2: 
                  
                break;

                case 0:
                    return;

                default:
                    System.out.println("Numero del 1 al 2");

            }
        }catch(InputMismatchException ex){
            
            System.out.println("Debes insertar un número");
            System.out.println("Pulsa intro...");
            // Metodo para pausar el menu
          
            sc.next();
            
        }
    }

  }
  
  //***********************************************************************************************

      

  

  // EVENTOS  de los menu item para mostrar las tablas
 
  private void mostrarVacas(ActionEvent evt) throws SQLException { 
    
    // Llamamos al metodo actualizarMadres, para mostrar las madres en la tabla de datos
    actualizarMadres();

  }
  
  private void mostrarToros(ActionEvent evt) throws SQLException { 

    // Llamamos al metodo actualizarPadres, para mostrar los padres en la tabla de datos
    actualizarPadres(); 
  }
  
  private void mostrarTerneros(ActionEvent evt) throws SQLException { 
    
    // Llamamos al metodo actualizarPadres, para mostrar los padres en la tabla de datos
    actualizarTerneros(); 
  }
  
  private void mostrarMuertes(ActionEvent evt) throws SQLException { 
    
    // Llamamos al metodo actualizarPadres, para mostrar los padres en la tabla de datos
    actualizarMuertes(); 
  }
  
  // *********************************************************************************************
  
  
  
  // Metodos de actualizar tablas y objetos
  
  public void actualizarMadres() throws SQLException{
    DefaultTableModel dtm = new DefaultTableModel();
    var tableFormat = tabla.actualizarMadres(dtm);

    if (tableFormat!= null) {
      
    tablaDatos.setModel(dtm);
        
    }else{
      JOptionPane.showMessageDialog(this, "No se han actualizado las");
    }
  }
  
  private void actualizarPadres() throws SQLException{
    
    
    DefaultTableModel dtm = new DefaultTableModel();
    var tableFormat = tabla.actualizarPadres(dtm);
    
    if (tableFormat!= null) {
      
    tablaDatos.setModel(dtm);
        
    }else{

      JOptionPane.showMessageDialog(this, "No se han actualizado los toros");
    }
  }

  private void actualizarTerneros() throws SQLException{

    DefaultTableModel dtm = new DefaultTableModel();
    var tableFormat = tabla.actualizarTerneros(dtm);
    
    if (tableFormat!= null) {
      
    tablaDatos.setModel(dtm);
        
    }else{

      JOptionPane.showMessageDialog(this, "No se han actualizado los terneros");
    }
  }

  private void actualizarMuertes() throws SQLException{

    DefaultTableModel dtm = new DefaultTableModel();
    var tableFormat = tabla.actualizarMuertes(dtm);
    
    if (tableFormat!= null) {
      
    tablaDatos.setModel(dtm);
        
    }else{

      JOptionPane.showMessageDialog(this, "No se han actualizado las muertes");
    }
  }

 //***********************************************************************************************


  // Insertar Vaca


  private void insertarVacaActionListener(ActionEvent evt) throws SQLException {

    AddVaca newvaca = null;
    newvaca = new AddVaca(null,true);
    newvaca.setVisible(true);

    try {
      actualizarMadres();
    } catch (SQLException e) {
      System.out.println("ERROR: mostrar vacas despues de insertar vaca");
    }
    


  }
  // Insertar Toro
  private void insertarToroActionListener(ActionEvent evt) throws SQLException {

    AddToro newToro = null;
    newToro = new AddToro(null,true);

    newToro.setVisible(true);

    try {
      actualizarPadres();
    } catch (SQLException e) {
      System.out.println("ERROR: mostrar vacas despues de insertar toro");
    }
    


  }
 //************************************************************************************************

  // Modificar Vaca

  private void modificarVacaActionListener(ActionEvent evt) {

    ModifyVaca modificarVaca = null;
    try {
      modificarVaca = new ModifyVaca(null,true);
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
    modificarVaca.setVisible(true);

    try {
      actualizarMadres();
    } catch (SQLException e) {
      System.out.println("ERROR: mostrar vacas despues de modificar");
    }
    
  }

  // **********************************************************************************************
  
  
  // Delete Vaca
  private void borrarVacaActionListener(ActionEvent evt) throws SQLException{

    DeleteVaca delVaca = null;

    try {
      delVaca = new DeleteVaca(null,true);
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
    delVaca.setVisible(true);

    
    actualizarMadres();
    
    
  }
  // **********************************************************************************************


  // Trabajamos con los componentes de la INTERFAZ
  private void intiComponents() {

        
        jScrollPane1 = new JScrollPane();
        tablaDatos = new JTable();
        menuPrincipal = new JMenuBar();

        vacas = new JMenu();
        toros = new JMenu();
        terneros = new JMenu();
        muertes = new JMenu();

        mostrarVacasMenu = new JMenuItem();
        mostrarTorosMenu = new JMenuItem();
        mostrarTernerosMenu = new JMenuItem();
        mostrarMuertesMenu = new JMenuItem();

        modificarVacasMenu = new JMenuItem();

        insertarVacasMenu = new JMenuItem();
        insertarToroMenu = new JMenuItem();

        borrarVacasMenu = new JMenuItem();
        muertes = new JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaDatos.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaDatos);


        // Genero el menu item VACAS
        vacas.setText("Vacas");

        mostrarVacasMenu.setText("Mostrar Vacas");
            
        mostrarVacasMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                  mostrarVacas(evt);
                } catch (SQLException e) {
                  System.out.println("ERROR: en mostrar vacas");
                }
            }
        });

        vacas.add(mostrarVacasMenu);

        insertarVacasMenu.setText("Insertar Vaca");
        insertarVacasMenu.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              try {
                insertarVacaActionListener(evt);
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
          }
        });
      
        vacas.add(insertarVacasMenu);

        modificarVacasMenu.setText("Modificar Vaca");
        modificarVacasMenu.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              modificarVacaActionListener(evt);
          }
        });

        vacas.add(modificarVacasMenu);

        borrarVacasMenu.setText("Borrar Vaca");
        borrarVacasMenu.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt){
              try {
                borrarVacaActionListener(evt);
              } catch (SQLException e) {
           
                e.printStackTrace();
              }
          }
        });

        vacas.add(borrarVacasMenu);

        menuPrincipal.add(vacas);



        // Genero el menu item TOROS

        toros.setText("Toros");

        mostrarTorosMenu.setText("Mostrar Toros");

        mostrarTorosMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                  mostrarToros(evt);
                } catch (SQLException e) {
                  System.out.println("ERROR: en mostrar toros");

                }
            }
        });

        toros.add(mostrarTorosMenu);

        insertarToroMenu.setText("Insertar Toro");
        insertarToroMenu.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              try {
                insertarToroActionListener(evt);
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
          }
        });
      
        toros.add(insertarToroMenu);


        menuPrincipal.add(toros);




        // Genero el menu item TERNEROS

        terneros.setText("Terneros");
        mostrarTernerosMenu.setText("Mostrar Terneros");
        mostrarTernerosMenu.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              try {
                mostrarTerneros(evt);
              } catch (SQLException e) {
                System.out.println("ERROR: en mostrar terneros");

              }
          }
        });
        
        terneros.add(mostrarTernerosMenu);

        menuPrincipal.add(terneros);
        

        // Genero el menu item RAZAS

        muertes.setText("Muertes");
        mostrarMuertesMenu.setText("Mostrar Muertes");
        mostrarMuertesMenu.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              try {
                mostrarMuertes(evt);
              } catch (SQLException e) {
                System.out.println("ERROR: en mostrar muertes");

              }
          }
        });
        
        muertes.add(mostrarMuertesMenu);

        menuPrincipal.add(muertes);


        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
      }
  //***********************************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

   
  // Metodo COMPROBAR LA CONEXION CON LA BD
    private static void comprobarConexion() {
        
        
          try {
            con = dbconnection.dataSource.getConnection();
            System.out.println("Conexion Realizada con EXITO");
        
          } catch (SQLException e) {
            System.out.println("No se puede conectar a la BD");
          } finally {
            try {
              con.close();
            } catch (SQLException e) {
              System.out.println("No se puede conectar a la BD");
            }
          }
        
        }
//************************************************************* */

  
}

