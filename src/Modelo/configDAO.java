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
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigosantacruzespino
 */
public class configDAO {
    Conexion cn = new Conexion();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
    public boolean Clientes(){
        String sql = "TRUNCATE TABLE Clientes ";
        try{
           con = cn.getConnection();
            System.out.println("Si se pudo truncar");
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
        }
        catch(SQLException e){
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
    public boolean Detalles(){
        String sql = "TRUNCATE TABLE Detalles ";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
        }
        catch(SQLException e){
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
    public boolean Productos(){
        String sql = "TRUNCATE TABLE Productos ";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
        }
        catch(SQLException e){
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
    
    public boolean Proveedor(){
        String sql = "TRUNCATE TABLE Proveedor ";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
        }
        catch(SQLException e){
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
    
    public boolean Ventas(){
        String sql = "TRUNCATE TABLE Ventas ";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
        }
        catch(SQLException e){
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
}
