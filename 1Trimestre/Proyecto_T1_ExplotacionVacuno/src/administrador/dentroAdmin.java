package administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mySQL_XML.DbConnection;

public class dentroAdmin {

    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;

    public static void crearTabla() throws SQLException{

        con = dbconnection.dataSource.getConnection();

        String sqldropPienso = "drop table if exists pienso;";
        String sqldropAnimales = "drop table if exists animalesMuertos;";
        String sqlAlter1 = "alter table madre DROP foreign key madre_ibfk_2;";
        String sqlAlter2 = "alter table madre DROP column cod_TipoComida;";
        
        String sql1 = "create table pienso(id_Pienso int auto_increment not null, tipoPienso varchar(20), constraint primary key (id_Pienso)); \n";

        String sql2 = "alter table madre add cod_TipoComida int; ";
        String sql3 = "alter table madre add constraint foreign key (cod_TipoComida) references pienso (id_Pienso); " ;
            
        String sql4 = "insert into pienso(tipoPienso) values ('Paja y Sorgo'),('Pienso'),('Hierba del Campo'),('Harina Molida'); ";
            
        String sql5 = "update madre set cod_TipoComida = 1 where estadoParto = 'Inactiva'; " ;

        String sql6 = "update madre set cod_TipoComida = 4 where estadoParto = 'Cubierta'; " ;

        String sql7 = "update madre set cod_TipoComida = 2 where estadoParto = 'En celo';";

        String sql8 = "create table animalesMuertos(id_Crotal int not null, id_Raza int not null, fechaNacimiento date not null, constraint primary key (id_Crotal));";

        
            if (con != null) {
                // Estado
                PreparedStatement ps = null;

                System.out.println("Espere un momento, Gracias !!!");

                ps = con.prepareStatement(sqlAlter1);
                ps.executeUpdate();
                ps = con.prepareStatement(sqlAlter2);
                ps.executeUpdate();
                ps = con.prepareStatement(sqldropPienso);
                ps.executeUpdate();
                ps = con.prepareStatement(sqldropAnimales);
                ps.executeUpdate();

                ps = con.prepareStatement(sql1);
                ps.executeUpdate();

                ps = con.prepareStatement(sql2);
                ps.executeUpdate();

                ps = con.prepareStatement(sql3);               
                ps.executeUpdate();

                ps = con.prepareStatement(sql4);                
                ps.executeUpdate();

                ps = con.prepareStatement(sql5);               
                ps.executeUpdate();

                ps = con.prepareStatement(sql6);               
                ps.executeUpdate();

                ps = con.prepareStatement(sql7);               
                ps.executeUpdate();

                ps = con.prepareStatement(sql8);               
                ps.executeUpdate();


                System.out.println("Tabla creada por el usuario Administrador");
            }    
    
                
    
                

        
    }
    
}
