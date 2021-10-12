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
public class VentaDAO {
    
    Connection con;
    Conexion  cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdVenta(){
        int id =0;
        String sql = "SELECT MAX(ID) FROM Ventas";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
                System.out.println("El id es: "+id);
            }
            
        }
         catch(SQLException e){
        System.out.println(e);        
        }
        return id;
    }
    
    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO Ventas (Cliente, Vendedor, Total) VALUES (?,?,?)";
        
        try{
         con = cn.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1, v.getCliente());
         ps.setString(2, v.getVendedor());
         ps.setDouble(3, v.getTotal());
         ps.execute();
         
    }
       catch(SQLException e){
           System.out.println(e);
       }
        finally{
            try{
                con.close();
                
            }
            catch(SQLException e){
                
            }
        }
        return r;
}
    public int RegistrarDetalle(Detalle Dv){
        String sql = "INSERT INTO Detalle (C_producto, Cantidad, Precio, ID_V) VALUES (?, ?, ?, ?)";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getC_producto());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getID_V());
            ps.execute();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        finally{
            try{
                con.close();
                
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        return r;
    }
    public boolean ActualizarStock(int cant, String cod){
        String sql = "UPDATE  Productos SET Cantidad = ? WHERE Codigo = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
     public List ListarVenta(){
       List<Venta> ListaVenta = new ArrayList();
       String sql = "SELECT * FROM Ventas";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               Venta vent = new Venta();
               vent.setID(rs.getInt("ID"));
               vent.setCliente(rs.getString("Cliente"));
               vent.setVendedor(rs.getNString("Vendedor"));
               vent.setTotal(rs.getDouble("Total"));
               ListaVenta.add(vent);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
           JOptionPane.showMessageDialog(null, e, "Error", HEIGHT);
       }
       return ListaVenta;
    }
}
