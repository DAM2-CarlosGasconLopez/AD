
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import Objetos.Razas;
import Objetos.Terneros;
import Objetos.Toros;
import Objetos.Vacas;
import administrador.dentroAdmin;
import interfazGrafica.AddVaca;
import interfazGrafica.DeleteVaca;
import interfazGrafica.ModifyVaca;
import mySQL_XML.DbConnection;

public class App extends JFrame implements ActionListener {

  // CONEXION
  public static DbConnection dbconnection = new DbConnection();
  public static Connection con = null;
  //******************************************************************* 

  // Declaracion Variables del MENU INTERFAZ
  private JMenu vacas;
  private JMenu toros;
  private JMenu terneros;
  private JMenu razas;
  private JMenuBar menuPrincipal;
  private JMenuItem mostrarVacasMenu;
  private JMenuItem mostrarTorosMenu;
  private JMenuItem mostrarTernerosMenu;
  private JMenuItem mostrarRazasMenu;
  private JMenuItem insertarVacasMenu;
  private JMenuItem modificarVacasMenu;
  private JMenuItem borrarVacasMenu;
  private JScrollPane jScrollPane1;
  private JTable tablaDatos;
  //****************************************************************** 

  // LISTAS ANIMNALES
  static List<Vacas> arrayVacas = new ArrayList<Vacas>();
  static List<Toros> arrayToros = new ArrayList<Toros>();
  static List<Terneros> arrayTerneros = new ArrayList<Terneros>();
  static List<Razas> arrayRazas = new ArrayList<Razas>();

  // CONTRASEÑA admin usada en el metodo 'comprobarContraseñaAdministrador()'
  static final String passwd = "Admin1234";
  //************************************************************************ 

  // Inicializamos los componentes de la interfaz
  public App()  throws SQLException{    

    intiComponents();
    actualizarMadres();

  }
  //************************************************************************ 
  

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
  //************************************************************************ 


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
  
  //*********************************************** */

      

  

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

  private void mostrarRaza(ActionEvent evt) throws SQLException { 
    
    // Llamamos al metodo actualizarPadres, para mostrar los padres en la tabla de datos
    actualizarRazas(); 
  }

  // ****************************************************************



  // Metodos de actualizar tablas y objetos

  private void actualizarMadres() throws SQLException{
      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Crotal","Raza","Estado Parto","Nº Partos","Entrada Explotacion","Fecha Nacimiento","Alimento"});
        
       // Genero la conexión
       con = dbconnection.dataSource.getConnection();
      
       // Creo la consulta
       String sql = "SELECT m.id_Crotal, r.tipoRaza, m.estadoParto, m.nPartos, m.entradaExplotacion, m.fecha_Nacimiento, m.cod_TipoComida " + 
                    "from proyecto_vacas.madre m " + 
                    "inner join proyecto_vacas.raza r on r.id_Raza = m.id_Raza;";
 
 
 
       if (con != null) {
         PreparedStatement ps = null;
         ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
      
     

        while (rs.next()) {        
         
          String vacas[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
          Vacas v = new Vacas(vacas[0], vacas[1], vacas[2], vacas[3], vacas[4], vacas[5],vacas[6]);
          
          dtm.addRow(vacas);
          arrayVacas.add(v);
                 
        }
        tablaDatos.setModel(dtm);
        
      }else{
        JOptionPane.showMessageDialog(this, "conexion fallida");
      }
  }

  private void actualizarPadres() throws SQLException{


    // Marcamos el modelo de la tabla que necesitamos
      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Estado Reproductor","Entrada Explotacion","Fecha Nacimiento"});
        
       // Genero la conexión
       con = dbconnection.dataSource.getConnection();
      
       // Creo la consulta
       String sql = "select * from proyecto_vacas.padre;";
 
 
 
       if (con != null) {
         PreparedStatement ps = null;
         ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
      
     

        while (rs.next()) {        

          
          String toros[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
          Toros t = new Toros(toros[0], toros[1], toros[2], toros[3], toros[4]);
          
          dtm.addRow(toros);

          arrayToros.add(t);

        
          
        
        }

        tablaDatos.setModel(dtm);
        
      }else{
        JOptionPane.showMessageDialog(this, "conexion fallida");
      }
  }

  private void actualizarTerneros() throws SQLException{


    // Marcamos el modelo de la tabla que necesitamos
      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Sexo","Peso","Fecha Nacimiento","Crotal Padre", "Crotal Madre"});
        
       // Genero la conexión
       con = dbconnection.dataSource.getConnection();
      
       // Creo la consulta
       String sql = "select * from proyecto_vacas.terneros;";
 
 
 
       if (con != null) {
         PreparedStatement ps = null;
         ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
      
     

        while (rs.next()) {        

          
          String terneros[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
          Terneros tern = new Terneros(terneros[0], terneros[1], terneros[2], terneros[3], terneros[4],terneros[5],terneros[6],terneros[7]);
          
          dtm.addRow(terneros);

          arrayTerneros.add(tern);

        
          
        
        }

        tablaDatos.setModel(dtm);
        
      }else{
        JOptionPane.showMessageDialog(this, "conexion fallida");
      }
  }
    
  private void actualizarRazas() throws SQLException{



    // Marcamos el modelo de la tabla que necesitamos
      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Id Raza","Tipo","Color De Piel"});
        
       // Genero la conexión
       con = dbconnection.dataSource.getConnection();
      
       // Creo la consulta
       String sql = "select * from proyecto_vacas.raza;";
 
 
 
       if (con != null) {
         PreparedStatement ps = null;
         ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
      
     

        while (rs.next()) {        

          
          String razas[] = {rs.getString(1),rs.getString(2),rs.getString(3)};
          Razas r = new Razas(razas[0], razas[1], razas[2]);
          
          dtm.addRow(razas);

          arrayRazas.add(r);

        
          
        
        }

        tablaDatos.setModel(dtm);
        
      }else{
        JOptionPane.showMessageDialog(this, "conexion fallida");
      }
  }

 //***************************************************** */

  // Añadir vaca

  private void insertarVacaActionListener(ActionEvent evt) {

    AddVaca newvaca = null;
    try {
      newvaca = new AddVaca(null,true);
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
    newvaca.setVisible(true);

    try {
      mostrarVacas(evt);
    } catch (SQLException e) {
      System.out.println("ERROR: mostrar vacas despues de insertar");
    }
    


  }


  // Modificar vaca

  private void modificarVacaActionListener(ActionEvent evt) {

    ModifyVaca modificarVaca = null;
    try {
      modificarVaca = new ModifyVaca(null,true);
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
    modificarVaca.setVisible(true);

    try {
      mostrarVacas(evt);
    } catch (SQLException e) {
      System.out.println("ERROR: mostrar vacas despues de modificar");
    }
    
  }

  // ******************************************
  
  
  // Delete Vaca
  private void borrarVacaActionListener(ActionEvent evt) throws SQLException{

    DeleteVaca delVaca = null;

    try {
      delVaca = new DeleteVaca(null,true);
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
    delVaca.setVisible(true);

    
    mostrarVacas(evt);
    
    
  }
  // ******************************************



  private void intiComponents() {

        
        jScrollPane1 = new JScrollPane();
        tablaDatos = new JTable();
        menuPrincipal = new JMenuBar();

        vacas = new JMenu();
        toros = new JMenu();
        terneros = new JMenu();
        razas = new JMenu();

        mostrarVacasMenu = new JMenuItem();
        mostrarTorosMenu = new JMenuItem();
        mostrarTernerosMenu = new JMenuItem();
        mostrarRazasMenu = new JMenuItem();

        modificarVacasMenu = new JMenuItem();

        insertarVacasMenu = new JMenuItem();

        borrarVacasMenu = new JMenuItem();
        razas = new JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
              insertarVacaActionListener(evt);
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

        razas.setText("Razas");
        mostrarRazasMenu.setText("Mostrar Razas");
        mostrarRazasMenu.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              try {
                mostrarRaza(evt);
              } catch (SQLException e) {
                System.out.println("ERROR: en mostrar terneros");

              }
          }
        });
        
        razas.add(mostrarRazasMenu);

        menuPrincipal.add(razas);


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
  

      


    @Override
    public void actionPerformed(ActionEvent e) {
     
      
    }

   
  
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


  
}

