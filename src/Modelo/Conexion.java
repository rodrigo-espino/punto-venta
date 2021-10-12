
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        try{
            String myDB = "jdbc:mysql://localhost:3306/sistemaventa";
            con = DriverManager.getConnection(myDB,"root","");
            
            return con;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
