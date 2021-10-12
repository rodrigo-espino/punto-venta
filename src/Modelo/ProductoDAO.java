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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author rodrigosantacruzespino
 */
public class ProductoDAO {
    Conexion cn = new Conexion();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   public boolean RegistrarProducto(Producto Prod){
       String sql = "INSERT INTO Productos (Codigo, Nombre, Proveedor, Cantidad, Precio) VALUES (?,?,?,?,?)";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, Prod.getCodigo());
           ps.setString(2, Prod.getNombre());
           ps.setString(3, Prod.getProveedor());
           ps.setInt(4, Prod.getCantidad());
           ps.setFloat(5, Prod.getPrecio());
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
           catch(SQLException e){
               JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           }
           
       } 
   }
   
   public void ConsultarProveedor(JComboBox proveedor){
       String sql = "SELECT * FROM Proveedor";
       
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               proveedor.addItem(rs.getString("Nombre"));
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           System.out.println(e);
       }
   }
   
   public List ListarProducto(){
       List<Producto> ListaProd = new ArrayList();
       String sql = "SELECT * FROM Productos";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               Producto prod = new Producto();
               prod.setID(rs.getInt("ID"));
               prod.setCodigo(rs.getString("Codigo"));
               prod.setNombre(rs.getNString("Nombre"));
               prod.setProveedor(rs.getString("Proveedor"));
               prod.setCantidad(rs.getInt("Cantidad"));
               prod.setPrecio(rs.getFloat("Precio"));
               ListaProd.add(prod);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
       }
       return ListaProd;
    }
   
   public boolean EliminarProducto(int id){
    String sql = "DELETE FROM Productos WHERE ID = ?";
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

   public boolean Modificar(Producto prod){
       String sql = "UPDATE Productos SET Codigo = ?, Nombre = ?, Proveedor = ?, Cantidad = ?, Precio = ? WHERE ID = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, prod.getCodigo());
           ps.setString(2, prod.getNombre());
           ps.setString(3, prod.getProveedor());
           ps.setInt(4, prod.getCantidad());
           ps.setFloat(5, prod.getPrecio());
           ps.setInt(6, prod.getID());
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

   public Producto BuscarPro(String cod){
       Producto producto = new Producto();
       String sql = "SELECT * FROM Productos WHERE Codigo = ?";
       
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, cod);
           rs = ps.executeQuery();
           if(rs.next()){
               producto.setNombre(rs.getString("Nombre"));
               producto.setPrecio(rs.getFloat("Precio"));
               producto.setCantidad(rs.getInt("Cantidad"));
           }
       }
       catch(Exception e){
           System.out.println(e);
       }
       return producto;
   }
   
   public config BuscarDatos(){
       config conf = new config();
       String sql = "SELECT * FROM Config";
       
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           if(rs.next()){
               conf.setId(rs.getInt("ID"));
               conf.setRuc(rs.getString("Ruc"));
               conf.setNombre(rs.getString("Nombre"));
               conf.setTelefono(rs.getString("Telefono"));
               conf.setDireccion(rs.getString("Direccion"));
               conf.setRazon(rs.getString("Razon"));
           }
       }
       catch(Exception e){
           System.out.println(e);
       }
       return conf;
   }
   
   public boolean ModificarDatos(config conf){
       String sql = "UPDATE Config SET RUC = ?, Nombre = ?, Telefono = ?, Direccion = ?, Razon = ? WHERE ID = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, conf.getRuc());
           ps.setString(2, conf.getNombre());
           ps.setString(3, conf.getTelefono());
           ps.setString(4, conf.getDireccion());
           ps.setString(5, conf.getRazon());
           ps.setInt(6, conf.getId());
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
