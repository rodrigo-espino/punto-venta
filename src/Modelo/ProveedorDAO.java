/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ProveedorDAO {
   Conexion cn = new Conexion();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   public boolean RegistrarProveedor(Proveedor pr){
       String sql = "INSERT INTO Proveedor (RUC, Nombre, Telefono, Direccion, Razon) VALUES (?,?,?,?,?)";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pr.getRUC());
           ps.setString(2, pr.getNombre());
           ps.setString(3, pr.getTelefono());
           ps.setString(4, pr.getDireccion());
           ps.setString(5, pr.getRazon());
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
   public List ListarProveedor(){
       List<Proveedor> ListaPr = new ArrayList();
       String sql = "SELECT * FROM Proveedor";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               Proveedor pr = new Proveedor();
               pr.setID(rs.getInt("ID"));
               pr.setRUC(rs.getString("RUC"));
               pr.setNombre(rs.getNString("Nombre"));
               pr.setTelefono(rs.getString("Telefono"));
               pr.setDireccion(rs.getString("Direccion"));
               pr.setRazon(rs.getString("Razon"));
               ListaPr.add(pr);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
       }
       return ListaPr;
   }
   public boolean EliminarProveedor(int id){
    String sql = "DELETE FROM Proveedor WHERE ID = ?";
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
   public boolean Modificar(Proveedor pr){
       String sql = "UPDATE Proveedor SET RUC = ?, Nombre = ?, Telefono = ?, Direccion = ?, Razon = ? WHERE ID = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pr.getRUC());
           ps.setString(2, pr.getNombre());
           ps.setString(3, pr.getTelefono());
           ps.setString(4, pr.getDireccion());
           ps.setString(5, pr.getRazon());
           ps.setInt(6, pr.getID());
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
}
