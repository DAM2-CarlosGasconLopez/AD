import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.xdevapi.Result;

import Objetos.Vacas;
import mySQL_XML.DbConnection;


public class App extends javax.swing.JFrame{
   
      public static DbConnection dbconnection = new DbConnection();
      public static Connection con = null;
       
    
      public static void main(String[] args) throws IOException {

        comprobarConexion();
   
        java.awt.EventQueue.invokeLater(new Runnable() {
          
          public void run() {
              new App().setVisible(true);
          }
      });       
        
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {    

      List<Vacas> arrayVacas = new ArrayList<Vacas>();;

      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Estado Parto","Nº Partos","Entrada Explotacion","Fecha Nacimiento"});
        
      if(con != null){
      Statement stat = con.createStatement();
      ResultSet rs = stat.executeQuery("SELECT *" +
                                       "FROM proyecto_vacas.madre;") ; 
      
     

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

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                  jMenuItem2ActionPerformed(evt);
                } catch (SQLException e) {
                  e.printStackTrace();
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
      }


      private static void comprobarConexion() {


        try {
          con =  dbconnection.dataSource.getConnection();
          
        } catch (SQLException e) {
          System.out.println("No se puede conectar");
        }finally{
          try {
            con.close();
          } catch (SQLException e) {
            System.out.println("No se puede conectar");
          }
        }

      
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

    }
    
