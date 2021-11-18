package mySQL_XML;
import java.io.FileInputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbConnection{

    // Creamos las variables String y la variable DataSource

    public DataSource dataSource;

    private String database;
    private String urlConnection;
    private String user;
    private String pwd;


    public DbConnection() {
        extractToXML();
        metodoPoolConnection();

    }

    public void extractToXML(){

        try {
            
            Properties properties = new Properties();
            FileInputStream fileInputS = new FileInputStream(".\\src\\mySQL_XML\\connectionXML.xml");
            properties.loadFromXML(fileInputS);
            fileInputS.close();
            
            database = properties.getProperty("basedatos");
            //urlConnection=properties.getProperty("urlConnectionCasa");
            urlConnection=properties.getProperty("urlConnectionClase");
            user = properties.getProperty("user");
            pwd = properties.getProperty("pwd"); 

        } catch (Exception e) {

            System.out.println("ERROR: Error en el metodo 'extractToXML' en la clase 'dbConnection.java' ");
        }
        
    }

    public void metodoPoolConnection(){

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pwd);
        basicDataSource.setUrl(urlConnection);
        basicDataSource.setMaxTotal(200);
        basicDataSource.setMinIdle(50);
        basicDataSource.setMaxIdle(100);

        dataSource = basicDataSource;

    }

    public DataSource getDataSource() {
        return dataSource;
    }

  

    

    

    

}