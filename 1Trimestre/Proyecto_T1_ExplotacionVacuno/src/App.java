import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import Objetos.Vacas;
import mySQL_XML.DbConnection;

// extends JFrame implements ActionListener
public class App extends JFrame implements ActionListener {

  // CONEXION
  public static DbConnection dbconnection = new DbConnection();
  public static Connection con = null;

  // LISTAS ANIMNALES
  static List<Vacas> arrayVacas = new ArrayList<Vacas>();
  
  
  public static void main(String[] args) throws IOException {
    
    comprobarConexion();
    
    java.awt.EventQueue.invokeLater(new Runnable() {
    
        public void run() {
              new App().setVisible(true);
          }
      }); 


    {// Scanner sc = new Scanner(System.in);
        
    // int menu = 10;

    // while (menu != 0) {
    //     System.out.println();
    //     System.out.println("====================== MENU XML : DOM =====================");
    //     System.out.println("======================== VETERINARIA ======================");
    //     System.out.println("===========================================================");
    //     System.out.println("= 1 - Mostrar todos los animales de nuestra base de datos'=");
    //     System.out.println("= 2 - Cambair altura a uno de nuestros animales           =");
    //     System.out.println("= 3 - Añadir nuevo animal                                 =");
    //     System.out.println("= 4 - Borrar animal seleccionado                          =");
    //     System.out.println("===========================================================");
    //     System.out.println("==              0 - Para cerrar el programa              ==");
    //     System.out.println("===========================================================");

    //     try{
    //         menu = sc.nextInt();

    //         switch (menu) {
    //             case 1:
    //             comprobarConexion();
    //               mostrarVacas();
    //                 break;

    //             case 2:
                   
    //                 break;

    //             case 3:
                
    //                 break;

    //             case 4:
                    
    //                 break;
    //             case 0:
    //                 return;

    //             default:
    //                 System.out.println("Numero del 1 al 4");

    //         }
    //     }catch(InputMismatchException ex){
            
    //         System.out.println("Debes insertar un número");
    //         System.out.println("Pulsa intro...");
    //         // Metodo para pausar el menu
    //         saltoLinea();
    //         sc.next();
            
    //     }
    // }
    }
      
  }
   
      

  { //   private static void mostrarVacas() {
    
  //   try {
  //     // Genero la conexión
  //     con = dbconnection.dataSource.getConnection();
      
  //     // Creo la consulta
  //     String sql = "select * from proyecto_vacas.madre;";



  //     if (con != null) {
  //       PreparedStatement ps = null;
  //       ps = con.prepareStatement(sql);
  //       ResultSet rs = ps.executeQuery();

  //       while (rs.next()) {

  //         Vacas v = new Vacas(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6) );

  //         arrayVacas.add(v);

  //       }

  //       for (Vacas vacas : arrayVacas) {
  //         System.out.println(vacas.getIdCrotal() + "  -  " + vacas.getEstado());
  //       }

  //     } else {
  //      System.out.println("ERROR: no se han mostrado las vacas");

  //     }
      
      
  //   }catch(SQLException e){

  //   }

  // }
} 
  

  




  private static void saltoLinea() throws IOException {
    // Pedir pulsar intro
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    br.readLine();
  }

  private static void comprobarConexion() {
      
      
        try {
          con = dbconnection.dataSource.getConnection();
          System.out.println("conectado");
      
        } catch (SQLException e) {
          System.out.println("No se puede conectar");
        } finally {
          try {
            con.close();
          } catch (SQLException e) {
            System.out.println("No se puede conectar");
          }
        }
      
      }

 
  private void jMenuItem2Accion(ActionEvent evt) throws SQLException {    

      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Estado Parto","Nº Partos","Entrada Explotacion","Fecha Nacimiento"});
        
       // Genero la conexión
       con = dbconnection.dataSource.getConnection();
      
       // Creo la consulta
       String sql = "select * from proyecto_vacas.madre;";
 
 
 
       if (con != null) {
         PreparedStatement ps = null;
         ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
      
     

        while (rs.next()) {        

          
          String vacas[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
          Vacas v = new Vacas(vacas[0], vacas[1], vacas[2], vacas[3], vacas[4], vacas[5]);
          
          dtm.addRow(vacas);

          arrayVacas.add(v);

        
          
        
        }
        tablaDatos.setModel(dtm);
        
      }else{
        JOptionPane.showMessageDialog(this, "conexion fallida");
      }

      
      
      
  }



  public App() {         
        intiComponents();

      }
           
  private void intiComponents() {

        
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        vacasMostrar = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

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

        vacasMostrar.setText("Vacas");

        jMenuItem2.setText("Mostrar Vacas");
        
        
        jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                  jMenuItem2Accion(evt);
                } catch (SQLException e) {
                  System.out.println("error");
                }
            }
        });

        vacasMostrar.add(jMenuItem2);

        jMenuItem1.setText("Modificar Una vaca");
        vacasMostrar.add(jMenuItem1);

        jMenuBar1.add(vacasMostrar);

        jMenu2.setText("Toros");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Terneros");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Razas");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

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
  
      
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JMenu vacasMostrar;


    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
    }




    

}

