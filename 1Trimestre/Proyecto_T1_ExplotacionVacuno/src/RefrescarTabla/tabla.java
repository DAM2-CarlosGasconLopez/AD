package RefrescarTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Objetos.Muertes;
import Objetos.Terneros;
import Objetos.Toros;
import Objetos.Vacas;
import mySQL_XML.DbConnection;

public class tabla {

    // CONEXION
  public static DbConnection dbconnection = new DbConnection();
  public static Connection con = null;
  //******************************************************************* 

  static List<Vacas> arrayVacas = new ArrayList<Vacas>();
  static List<Toros> arrayToros = new ArrayList<Toros>();
  static List<Terneros> arrayTerneros = new ArrayList<Terneros>();
  static List<Muertes> arrayMuerte = new ArrayList<Muertes>();
    
  public static DefaultTableModel actualizarMadres(DefaultTableModel dtm) throws SQLException{
    
    dtm.setColumnIdentifiers(new String[]{"Crotal","Raza","Estado Parto","Nº Partos","Entrada Explotacion","Fecha Nacimiento","Alimento"});
      
     // Genero la conexión
     con = dbconnection.dataSource.getConnection();
    
     // Creo la consulta
     String sql = "SELECT m.id_Crotal, r.tipoRaza, m.estadoParto, m.nPartos, m.entradaExplotacion, m.fecha_Nacimiento, m.cod_TipoComida " + 
                  "from proyecto_vacas.madre m " + 
                  "inner join proyecto_vacas.raza r on r.id_Raza = m.id_Raza "+
                  "where m.id_Crotal !=0;";



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
      
    }

    return dtm;
}

public static DefaultTableModel actualizarPadres(DefaultTableModel dtm) throws SQLException{


  // Marcamos el modelo de la tabla que necesitamos
   
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

     }
     return dtm;
      
    
}

public static DefaultTableModel actualizarTerneros(DefaultTableModel dtm) throws SQLException{


  // Marcamos el modelo de la tabla que necesitamos
    dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Sexo","Peso","Estado","Fecha Nacimiento","Crotal Padre", "Crotal Madre"});
      
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

    }
    return dtm;
}
  
public static DefaultTableModel actualizarMuertes(DefaultTableModel dtm) throws SQLException{



  // Marcamos el modelo de la tabla que necesitamos
   
    dtm.setColumnIdentifiers(new String[]{"Id Crotal","Id Raza","Fecha Nacimiento"});
      
     // Genero la conexión
     con = dbconnection.dataSource.getConnection();
    
     // Creo la consulta
     String sql = "select * from proyecto_vacas.animalesmuertos;";



     if (con != null) {
       PreparedStatement ps = null;
       ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
    
   

      while (rs.next()) {        

        
        String razas[] = {rs.getString(1),rs.getString(2),rs.getString(3)};
        Muertes r = new Muertes(razas[0], razas[1], razas[2]);
        
        dtm.addRow(razas);

        arrayMuerte.add(r);

      
        
      
      }

     }
     return dtm;
}

    
}
