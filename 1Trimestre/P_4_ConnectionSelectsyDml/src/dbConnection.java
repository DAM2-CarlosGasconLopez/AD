import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {

    private String basedatos = "bd_2_gascon_c";
    private String host = "servidorifc.iesch.org";
    private String port = "3306";
    
    //String port = = "8882";
    private String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    private String user = "2_gascon_c";
    private String pwd = "gy7f8";

    //Class.forName("com.mysql.jdbc.Driver");    // No necesario desde SE 6.0
    //Class.forName("com.mysql.cj.jdbc.Driver"); // para MySQL 8.0, no necesario


    public Connection getConnection(){
        Connection con = null;

        try  {
            con = (Connection) DriverManager.getConnection(urlConnection, user, pwd);
            System.out.println("La conexion se ha realizado con EXITO");        
          } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
          } catch (Exception e) {
            e.printStackTrace(System.err);
          }

          return con;

    }

    
    
    
}
