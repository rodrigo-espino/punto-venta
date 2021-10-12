
package Modelo;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author rodrigosantacruzespino
 */
public class ClienteDAO {
   Conexion cn = new Conexion();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   public boolean RegistrarCliente(Cliente cl){
       String sql = "INSERT INTO Clientes (DNI, Nombre, Telefono, Direccion, Razon) VALUES (?,?,?,?,?)";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, cl.getDNI());
           ps.setString(2, cl.getNombre());
           ps.setString(3, cl.getTelefono());
           ps.setString(4, cl.getDireccion());
           ps.setString(5, cl.getRazon());
           ps.execute();
           return true;
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           System.out.println(e);
           return false;
       }
       finally{
           try{
           con.close();
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           }
           
       } 
   }
   public List ListarCliente(){
       List<Cliente> ListaCl = new ArrayList();
       String sql = "SELECT * FROM Clientes";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               Cliente cl = new Cliente();
               cl.setID(rs.getInt("ID"));
               cl.setDNI(rs.getInt("DNI"));
               cl.setNombre(rs.getNString("Nombre"));
               cl.setTelefono(rs.getString("Telefono"));
               cl.setDireccion(rs.getString("Direccion"));
               cl.setRazon(rs.getString("Razon"));
               ListaCl.add(cl);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
       }
       return ListaCl;
   }
   public boolean EliminarCliente(int id){
    String sql = "DELETE FROM Clientes WHERE ID = ?";
    try{
        con = cn.getConnection();
           
        rs = ps.executeQuery();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;
    }   
    
    catch(SQLException e){
        System.out.println(e);
        JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
        return false;
    }
    finally{
        try{
            con.close();
        }
        catch(SQLException ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex, "Error", HEIGHT);
        }
    }
}
   public boolean Modificar(Cliente cl){
       String sql = "UPDATE Clientes SET DNI = ?, Nombre = ?, Telefono = ?, Direccion = ?, Razon = ? WHERE ID = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, cl.getDNI());
           ps.setString(2, cl.getNombre());
           ps.setString(3, cl.getTelefono());
           ps.setString(4, cl.getDireccion());
           ps.setString(5, cl.getRazon());
           ps.setInt(6, cl.getID());
           ps.execute();
           return true;
       }
       catch(SQLException e){
           System.out.println(e);
           JOptionPane.showMessageDialog(null, e, "Error Base de Datos", HEIGHT);
           return false;
       }
       finally{
           try{
               con.close(); 
           }
           catch(SQLException e){
               JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
               System.out.println(e);
           }
       }
   }
    public Cliente BuscarCliente(int dni){
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM Clientes WHERE DNI = ?";
        
        try{
          con = cn.getConnection();
          ps = con.prepareStatement(sql);
          ps.setInt(1, dni);
          rs = ps.executeQuery();
            if (rs.next()) {
                cl.setNombre(rs.getString("Nombre"));
                cl.setTelefono(rs.getString("Telefono"));
                cl.setDireccion(rs.getString("Direccion"));
                
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return cl;
    }
}
