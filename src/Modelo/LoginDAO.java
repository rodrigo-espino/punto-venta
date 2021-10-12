
package Modelo;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    public login log(String Usuario, String Pass){
        login l = new login();
        String sql = "SELECT * FROM Usuarios where Usuario = ? and Pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Usuario);
            ps.setString(2,Pass);
            rs = ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("ID"));
                l.setNombre(rs.getString("Nombre"));
                l.setUsuario(rs.getString("Usuario"));
                l.setPass(rs.getString("Pass"));
                l.setRol(rs.getString("Rol"));
                
            }
        }
        catch(SQLException e){
            
        }
        return l ;
    }

    public boolean Registrar(login reg){
        String sql = "INSERT INTO Usuarios (Nombre, Usuario, Pass, Rol) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getUsuario());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
     public List ListarUsuarios(){
       List<login> Lista = new ArrayList();
       String sql = "SELECT * FROM usuarios";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               login lg = new login();
               lg.setId(rs.getInt("ID"));
               lg.setNombre(rs.getString("Nombre"));
               lg.setUsuario(rs.getString("Usuario"));
               lg.setPass(rs.getString("Pass"));
               lg.setRol(rs.getString("Rol"));
               Lista.add(lg);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return Lista;
   }
     
     public boolean EliminarUsuario(int id){
    String sql = "DELETE FROM Usuarios WHERE ID = ?";
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
     
  public boolean Modificar(login lg){
       String sql = "UPDATE Usuarios SET Nombre = ?, Usuario = ?, Pass = ?, Rol = ? WHERE ID = ?";
       
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, lg.getNombre());
           ps.setString(2, lg.getUsuario());
           ps.setString(3, lg.getPass());
           ps.setString(4, lg.getRol());
           ps.setInt(5, lg.getId());
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
