/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
//import Reportes.Excel;


import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Conexion;
import Modelo.Detalle;
import Modelo.LoginDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import Modelo.config;
import Modelo.configDAO;
import Modelo.login;
import Reportes.etsel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rodrigosantacruzespino
 */
public class Sistema extends javax.swing.JFrame {

    /**
     * Creates new form Sistema
     */
    Cliente cl = new Cliente();
    ClienteDAO client = new ClienteDAO();
    
    Proveedor pr = new Proveedor();
    ProveedorDAO prov = new ProveedorDAO();
    
    Producto prod = new Producto();
    ProductoDAO prodDAO = new ProductoDAO();
    
    Venta v = new Venta();
    VentaDAO vd = new VentaDAO();
    
    Detalle dv = new Detalle();
    
    config conf = new config();
    
    login lg = new login();
    LoginDAO login = new LoginDAO();
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    
    configDAO cfn = new configDAO();
    
    PreparedStatement ps;
    Connection con;
    Conexion cn = new Conexion();
        
        ResultSet rs;
    int item;
    double TotalPagar = 0.00;
    public Sistema() {
        initComponents();
        /*
        this.setLocationRelativeTo(null);
            txtIDC.setVisible(false);
            txtIDP.setVisible(false);
            txtIDVenta.setVisible(false);
            txtTelCV.setVisible(false);
            txtDirCV.setVisible(false);
            txtIDCon.setVisible(false);
            ListarConfig();
            prodDAO.ConsultarProveedor(cbxProPro);*/
        
    }
    public Sistema(login priv){
        initComponents();
        this.setLocationRelativeTo(null);
            txtIDC.setVisible(false);
            txtIDP.setVisible(false);
            txtIDVenta.setVisible(false);
            txtTelCV.setVisible(false);
            txtDirCV.setVisible(false);
            txtIDCon.setVisible(false);
            txtIDV.setVisible(false);
            txtIDPro.setVisible(false);
            txtIDUsu.setVisible(false);
            ListarConfig();
            prodDAO.ConsultarProveedor(cbxProPro);
        
        if (priv.getRol().equals("Asistente")) {
            btnUser.setEnabled(false);
            btnConfig.setEnabled(false);
            
            LabelVendedorV.setText(priv.getNombre());
        }
        else{
            LabelVendedorV.setText(priv.getNombre());
        }
    }
public void ListarCliente(){
    List<Cliente> ListarCl = client.ListarCliente();
    modelo = (DefaultTableModel) tableCliente.getModel();
    Object[] ob = new Object[6];
    for (int i = 0; i < ListarCl.size(); i++) {
        ob[0] = ListarCl.get(i).getID();
        ob[1] = ListarCl.get(i).getDNI();
        ob[2] = ListarCl.get(i).getNombre();
        ob[3] = ListarCl.get(i).getTelefono();
        ob[4] = ListarCl.get(i).getDireccion();
        ob[5] = ListarCl.get(i).getRazon();
        modelo.addRow(ob);
        
    }
    tableCliente.setModel(modelo);
}

public void ListarUsuarios(){
    List<login> ListarUs = login.ListarUsuarios();
    modelo = (DefaultTableModel) TableUsuarios.getModel();
    Object[] ob = new Object[5];
    for (int i = 0; i < ListarUs.size(); i++) {
        ob[0] = ListarUs.get(i).getId();
        ob[1] = ListarUs.get(i).getNombre();
        ob[2] = ListarUs.get(i).getUsuario();
        ob[3] = ListarUs.get(i).getPass();
        ob[4] = ListarUs.get(i).getRol();
        
        modelo.addRow(ob);
        
    }
    TableUsuarios.setModel(modelo);
}

public void ListarProveedor(){
    List<Proveedor> ListarPr = prov.ListarProveedor();
    modelo = (DefaultTableModel) tableCliente.getModel();
    Object[] ob = new Object[6];
    for (int i = 0; i < ListarPr.size(); i++) {
        ob[0] = ListarPr.get(i).getID();
        ob[1] = ListarPr.get(i).getRUC();
        ob[2] = ListarPr.get(i).getNombre();
        ob[3] = ListarPr.get(i).getTelefono();
        ob[4] = ListarPr.get(i).getDireccion();
        ob[5] = ListarPr.get(i).getRazon();
        modelo.addRow(ob);
        
    }
    TablaP.setModel(modelo);
}

public void ListarProducto(){
    List<Producto> ListarProd = prodDAO.ListarProducto();
    modelo = (DefaultTableModel) TablePro.getModel();
    Object[] ob = new Object[6];
    for (int i = 0; i < ListarProd.size(); i++) {
        ob[0] = ListarProd.get(i).getID();
        ob[1] = ListarProd.get(i).getCodigo();
        ob[2] = ListarProd.get(i).getNombre();
        ob[3] = ListarProd.get(i).getCantidad();
        ob[4] = ListarProd.get(i).getPrecio();
        ob[5] = ListarProd.get(i).getProveedor();
        modelo.addRow(ob);
        
    }
   
    TablaP.setModel(modelo);
}

public void ListarConfig(){
    conf = prodDAO.BuscarDatos();
    txtIDCon.setText(""+conf.getId());
    txtRucCon.setText(""+conf.getRuc());
    txtNombreCon.setText(""+conf.getNombre());
    txtDirCon.setText(""+conf.getDireccion());
    txtTelefonoCon.setText(""+conf.getTelefono());
    txtRazonCon.setText(""+conf.getRazon());
}

public void LimpiarTabla(){
    for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i--;
    }
    
    
}

public void ListarVenta(){
    List<Venta> ListarVenta = vd.ListarVenta();
    
    modelo = (DefaultTableModel) TableV.getModel();
    Object[] ob = new Object[4];
    for (int i = 0; i < ListarVenta.size(); i++) {
        ob[0] = ListarVenta.get(i).getID();
        ob[1] = ListarVenta.get(i).getCliente();
        ob[2] = ListarVenta.get(i).getVendedor();
        ob[3] = ListarVenta.get(i).getTotal();

        modelo.addRow(ob);
        
    }
   
    TableV.setModel(modelo);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        LabelVendedorV = new javax.swing.JLabel();
        btnConfig = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TMenu = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoV = new javax.swing.JTextField();
        txtDescV = new javax.swing.JTextField();
        txtCantidadV = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCVenta = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioV = new javax.swing.JTextField();
        txtDisponibleV = new javax.swing.JTextField();
        btnEliminarV = new javax.swing.JButton();
        txtRucV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnImprimirV = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNombreV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtIDVenta = new javax.swing.JTextField();
        txtTelCV = new javax.swing.JTextField();
        txtDirCV = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDniC = new javax.swing.JTextField();
        txtNombreC = new javax.swing.JTextField();
        txtTelefonoC = new javax.swing.JTextField();
        txtDireccionC = new javax.swing.JTextField();
        txtRazonC = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCliente = new javax.swing.JTable();
        btnGuardarC = new javax.swing.JButton();
        btnEditarC = new javax.swing.JButton();
        btnEliminarC = new javax.swing.JButton();
        btnNuevoC = new javax.swing.JButton();
        txtIDC = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtRucP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNombreP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelefonoP = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDireccionP = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtRazonP = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaP = new javax.swing.JTable();
        btnGuardarP = new javax.swing.JButton();
        btnEditarP = new javax.swing.JButton();
        btnEliminarP = new javax.swing.JButton();
        btnNuevoP = new javax.swing.JButton();
        txtIDP = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableV = new javax.swing.JTable();
        btnPDFV = new javax.swing.JButton();
        txtIDV = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtRucCon = new javax.swing.JTextField();
        txtNombreCon = new javax.swing.JTextField();
        txtTelefonoCon = new javax.swing.JTextField();
        txtDirCon = new javax.swing.JTextField();
        txtRazonCon = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        txtIDCon = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtNombrePro = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCantidadPro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPrecioPro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablePro = new javax.swing.JTable();
        btnGuardarPro = new javax.swing.JButton();
        btnEditarPro = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        BtnNuevoPro = new javax.swing.JButton();
        cbxProPro = new javax.swing.JComboBox<>();
        btnExcelPro = new javax.swing.JButton();
        txtIDPro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbxRolUsu = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        txtNombreUsu = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableUsuarios = new javax.swing.JTable();
        txtIDUsu = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        jButton1.setText("Nueva Venta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        jButton2.setText("Clientes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        jButton3.setText("Proveedores");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        jButton4.setText("Productos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 130, 50));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        jButton5.setText("Ventas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 130, 50));

        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnUser.setText("Usuarios");
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 130, 50));

        LabelVendedorV.setFont(new java.awt.Font("Lucida Grande", 2, 14)); // NOI18N
        LabelVendedorV.setForeground(new java.awt.Color(255, 255, 255));
        LabelVendedorV.setText("jLabel10");
        jPanel1.add(LabelVendedorV, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("Config");
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        jPanel1.add(btnConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 130, 50));

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Bienvenid@!");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 720));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 890, 170));

        TMenu.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        TMenu.setEnabled(false);
        TMenu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel3.setText("Codigo");

        jLabel4.setText("Descripcion");

        jLabel5.setText("Cantidad");

        txtCodigoV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVKeyPressed(evt);
            }
        });

        txtCantidadV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVKeyPressed(evt);
            }
        });

        TableCVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TableCVenta);
        if (TableCVenta.getColumnModel().getColumnCount() > 0) {
            TableCVenta.getColumnModel().getColumn(0).setPreferredWidth(15);
            TableCVenta.getColumnModel().getColumn(1).setPreferredWidth(20);
            TableCVenta.getColumnModel().getColumn(2).setPreferredWidth(3);
            TableCVenta.getColumnModel().getColumn(3).setPreferredWidth(5);
            TableCVenta.getColumnModel().getColumn(4).setPreferredWidth(3);
        }

        jLabel6.setText("Precio");

        jLabel7.setText("Disponible");

        txtPrecioV.setEditable(false);

        btnEliminarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVActionPerformed(evt);
            }
        });

        txtRucV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVKeyPressed(evt);
            }
        });

        jLabel2.setText("DNI/RUC");

        btnImprimirV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnImprimirV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirVActionPerformed(evt);
            }
        });

        jLabel8.setText("NOMBRE");

        txtNombreV.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel9.setText("Total a Pagar:");

        LabelTotal.setText("----");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addComponent(txtCodigoV, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtDescV, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioV, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtDisponibleV, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRucV, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtTelCV, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtNombreV, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74)
                                .addComponent(btnImprimirV)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel9)
                                .addGap(48, 48, 48)
                                .addComponent(LabelTotal))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtDirCV, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(txtIDVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(txtCodigoV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(txtDescV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminarV)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecioV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDisponibleV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(LabelTotal))
                                .addGap(47, 47, 47))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRucV)
                                    .addComponent(txtNombreV))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDirCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnImprimirV)
                        .addContainerGap())))
        );

        TMenu.addTab("", jPanel4);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel11.setText("DNI/RUC:");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel12.setText("Nombre:");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel13.setText("Telefono:");

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel14.setText("Direccion:");

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel15.setText("Razon Social:");

        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI/RUC", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ));
        tableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCliente);
        if (tableCliente.getColumnModel().getColumnCount() > 0) {
            tableCliente.getColumnModel().getColumn(0).setPreferredWidth(3);
            tableCliente.getColumnModel().getColumn(1).setPreferredWidth(4);
            tableCliente.getColumnModel().getColumn(2).setPreferredWidth(20);
            tableCliente.getColumnModel().getColumn(3).setPreferredWidth(12);
            tableCliente.getColumnModel().getColumn(4).setPreferredWidth(20);
            tableCliente.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        btnGuardarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        btnEditarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCActionPerformed(evt);
            }
        });

        btnEliminarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCActionPerformed(evt);
            }
        });

        btnNuevoC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(45, 45, 45))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel12))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombreC)
                                            .addComponent(txtDniC)
                                            .addComponent(txtTelefonoC)
                                            .addComponent(txtDireccionC)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRazonC, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGuardarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarC, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEditarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevoC, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDC, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDniC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefonoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtDireccionC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtRazonC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(txtIDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        TMenu.addTab("", jPanel5);

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel16.setText("RUC:");

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel17.setText("Nombre:");

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel18.setText("Telefono:");

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel19.setText("Direccion:");

        jLabel20.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel20.setText("Razon Social:");

        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ));
        TablaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaP);
        if (TablaP.getColumnModel().getColumnCount() > 0) {
            TablaP.getColumnModel().getColumn(0).setPreferredWidth(3);
            TablaP.getColumnModel().getColumn(1).setPreferredWidth(4);
            TablaP.getColumnModel().getColumn(2).setPreferredWidth(20);
            TablaP.getColumnModel().getColumn(3).setPreferredWidth(12);
            TablaP.getColumnModel().getColumn(4).setPreferredWidth(20);
            TablaP.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        btnGuardarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPActionPerformed(evt);
            }
        });

        btnEditarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPActionPerformed(evt);
            }
        });

        btnEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });

        btnNuevoP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtRazonP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombreP)
                                            .addComponent(txtRucP)
                                            .addComponent(txtTelefonoP)
                                            .addComponent(txtDireccionP))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoP, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtRucP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTelefonoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDireccionP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtRazonP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        TMenu.addTab("", jPanel6);

        TableV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vendedor", "Total"
            }
        ));
        TableV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableV);
        if (TableV.getColumnModel().getColumnCount() > 0) {
            TableV.getColumnModel().getColumn(0).setPreferredWidth(5);
            TableV.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableV.getColumnModel().getColumn(2).setPreferredWidth(60);
            TableV.getColumnModel().getColumn(3).setPreferredWidth(5);
        }

        btnPDFV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPDFV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnPDFV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(txtIDV, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPDFV)
                    .addComponent(txtIDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TMenu.addTab("", jPanel8);

        jLabel26.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel26.setText("RUC");

        jLabel27.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel27.setText("Nombre");

        jLabel28.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel28.setText("Telefono");

        jLabel29.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel29.setText("Direccion");

        jLabel30.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel30.setText("Razon Social");

        jLabel31.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel31.setText("DATOS DE LA EMPRESA");

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ayuda.png"))); // NOI18N
        jButton12.setText("Formatear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(txtDirCon, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(txtRazonCon)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(txtRucCon, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(240, 240, 240)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(txtTelefonoCon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(txtNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(7, 7, 7)))
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(354, 354, 354))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(txtIDCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel31)
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRucCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        TMenu.addTab("", jPanel9);

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel21.setText("Codigo:");

        jLabel22.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel22.setText("Nombre:");

        jLabel23.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel23.setText("Cantidad:");

        jLabel24.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel24.setText("Precio:");

        jLabel25.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel25.setText("Proveedor:");

        TablePro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Nombre", "Cantidad", "Precio", "Proveedor"
            }
        ));
        TablePro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablePro);
        if (TablePro.getColumnModel().getColumnCount() > 0) {
            TablePro.getColumnModel().getColumn(0).setPreferredWidth(3);
            TablePro.getColumnModel().getColumn(1).setPreferredWidth(4);
            TablePro.getColumnModel().getColumn(2).setPreferredWidth(50);
            TablePro.getColumnModel().getColumn(3).setPreferredWidth(12);
            TablePro.getColumnModel().getColumn(4).setPreferredWidth(20);
            TablePro.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        btnGuardarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProActionPerformed(evt);
            }
        });

        btnEditarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnEditarPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProActionPerformed(evt);
            }
        });

        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });

        BtnNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        BtnNuevoPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnNuevoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoProActionPerformed(evt);
            }
        });

        cbxProPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccciona Proveedor--" }));

        btnExcelPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNombrePro, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCodigoPro, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantidadPro, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecioPro, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxProPro, 0, 1, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(txtIDPro, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(70, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnNuevoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnExcelPro, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtCantidadPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbxProPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(txtIDPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnNuevoPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcelPro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        TMenu.addTab("", jPanel7);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 247, 50, 40));
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 140, 30));

        jLabel32.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel32.setText("Contraseña:");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel33.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel33.setText("Usuario:");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel34.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel34.setText("Nombre:");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        cbxRolUsu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar Rol--", "Administrador", "Asistente" }));
        jPanel2.add(cbxRolUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 190, -1));

        jLabel35.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel35.setText("Rol:");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        jPanel2.add(txtNombreUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 160, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 50, 40));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 50, 40));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 50, 40));

        TableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Usuario", "Password", "Rol"
            }
        ));
        TableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableUsuarios);

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 560, 520));
        jPanel2.add(txtIDUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 150, 30));

        TMenu.addTab("", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setText("Formatear Clientes");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 170, 40));

        jButton14.setText("Formatear Ventas");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 170, 40));

        jLabel37.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel37.setText("Formatear");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 100, 30));

        jButton15.setText("Formatear Detalles de Venta");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 230, 40));

        jButton16.setText("Formatear Productos");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 180, 40));

        jButton17.setText("Formatear Proveedores");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 190, 40));

        TMenu.addTab("", jPanel3);

        getContentPane().add(TMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 171, 890, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if(!"".equals(txtDniC.getText()) || !"".equals(txtNombreC.getText()) ){
          cl.setDNI(Integer.parseInt(txtDniC.getText()));
          cl.setNombre(txtNombreC.getText());
          cl.setTelefono(txtTelefonoC.getText());
          cl.setDireccion(txtDireccionC.getText());
          cl.setRazon(txtRazonC.getText());
          LimpiarTabla();
          client.RegistrarCliente(cl);
          ListarCliente();
          Limpiar();            
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        TMenu.setSelectedIndex(1);
        LimpiarTabla();
        ListarCliente();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEliminarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCActionPerformed
        if(!"".equals(txtIDC.getText())){
           int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               int id = Integer.parseInt(txtIDC.getText());
               client.EliminarCliente(id);
               LimpiarTabla();
               ListarCliente();
               Limpiar();
           }
           
        }
        
    }//GEN-LAST:event_btnEliminarCActionPerformed

    private void tableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteMouseClicked
        int fila = tableCliente.rowAtPoint(evt.getPoint());
        txtIDC.setText(tableCliente.getValueAt(fila, 0).toString());
        txtDniC.setText(tableCliente.getValueAt(fila, 1).toString());
        txtNombreC.setText(tableCliente.getValueAt(fila, 2).toString());
        txtTelefonoC.setText(tableCliente.getValueAt(fila, 3).toString());
        txtDireccionC.setText(tableCliente.getValueAt(fila, 4).toString());
        txtRazonC.setText(tableCliente.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tableClienteMouseClicked

    private void btnEditarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCActionPerformed
        if("".equals(txtIDC.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione Una Fila", "Error", HEIGHT);
            
        }
        else{
            if(!"".equals(txtDniC.getText()) || !"".equals(txtNombreC.getText()) ){
          cl.setDNI(Integer.parseInt(txtDniC.getText()));
          cl.setNombre(txtNombreC.getText());
          cl.setTelefono(txtTelefonoC.getText());
          cl.setDireccion(txtDireccionC.getText());
          cl.setRazon(txtRazonC.getText());
          cl.setID(Integer.parseInt(txtIDC.getText()));
          LimpiarTabla();
          client.Modificar(cl);
          ListarCliente();
          Limpiar();            
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
          
        }
    }//GEN-LAST:event_btnEditarCActionPerformed

    private void btnNuevoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCActionPerformed
        Limpiar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoCActionPerformed

    private void btnGuardarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPActionPerformed
        if(!"".equals(txtRucP.getText()) || !"".equals(txtNombreP.getText()) ){
          pr.setRUC((txtRucP.getText()));
          pr.setNombre(txtNombreP.getText());
          pr.setTelefono(txtTelefonoP.getText());
          pr.setDireccion(txtDireccionP.getText());
          pr.setRazon(txtRazonP.getText());
          prov.RegistrarProveedor(pr);
          Limpiar();            
          LimpiarTabla();
          ListarProveedor();
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
    }//GEN-LAST:event_btnGuardarPActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          Limpiar();            
          LimpiarTabla();
          ListarProveedor();        
        TMenu.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        if(!"".equals(txtIDP.getText())){
           int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               int id = Integer.parseInt(txtIDP.getText());
               prov.EliminarProveedor(id);
               Limpiar();            
          LimpiarTabla();
          ListarProveedor();
           }
           
        }
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void btnEditarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPActionPerformed
        if("".equals(txtIDP.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione Una Fila", "Error", HEIGHT);
            
        }
        else{
            if(!"".equals(txtRucP.getText()) || !"".equals(txtNombreP.getText()) ){
          pr.setRUC((txtRucP.getText()));
          pr.setNombre(txtNombreP.getText());
          pr.setTelefono(txtTelefonoP.getText());
          pr.setDireccion(txtDireccionP.getText());
          pr.setRazon(txtRazonP.getText());
          pr.setID(Integer.parseInt(txtIDP.getText()));
          prov.Modificar(pr);
          Limpiar();            
          LimpiarTabla();
          ListarProveedor();
                    
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
            
        }
          
        }
    }//GEN-LAST:event_btnEditarPActionPerformed

    private void btnNuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnNuevoPActionPerformed

    private void TablaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPMouseClicked
        int fila = TablaP.rowAtPoint(evt.getPoint());
        txtIDP.setText(TablaP.getValueAt(fila, 0).toString());
        txtRucP.setText(TablaP.getValueAt(fila, 1).toString());
        txtNombreP.setText(TablaP.getValueAt(fila, 2).toString());
        txtTelefonoP.setText(TablaP.getValueAt(fila, 3).toString());
        txtDireccionP.setText(TablaP.getValueAt(fila, 4).toString());
        txtRazonP.setText(TablaP.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_TablaPMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        prodDAO.ConsultarProveedor(cbxProPro);
        TMenu.setSelectedIndex(5);
        LimpiarTabla();
        ListarProducto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnGuardarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProActionPerformed
        if(!"".equals(txtCodigoPro.getText()) || !"".equals(txtNombrePro.getText()) ){
          prod.setCodigo((txtCodigoPro.getText()));
          prod.setNombre(txtNombrePro.getText());
          prod.setProveedor(cbxProPro.getSelectedItem().toString());
          prod.setCantidad(Integer.parseInt(txtCantidadPro.getText()));
          prod.setPrecio(Float.parseFloat(txtPrecioPro.getText()));
          prodDAO.RegistrarProducto(prod);
          Limpiar();            
          LimpiarTabla();
          ListarProducto();
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
    }//GEN-LAST:event_btnGuardarProActionPerformed

    private void btnEditarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProActionPerformed
        if("".equals(txtIDPro.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione Una Fila", "Error", HEIGHT);
            
        }
        else{
            if(!"".equals(txtCodigoPro.getText()) || !"".equals(txtNombrePro.getText()) ){
          prod.setCodigo((txtCodigoPro.getText()));
          prod.setNombre(txtNombrePro.getText());
          prod.setCantidad(Integer.parseInt(txtCantidadPro.getText()));
          prod.setPrecio(Float.parseFloat(txtPrecioPro.getText()));
          prod.setProveedor(cbxProPro.getSelectedItem().toString());
          prod.setID(Integer.parseInt(txtIDPro.getText()));
          prodDAO.Modificar(prod);
          Limpiar();            
          LimpiarTabla();
          ListarProducto();
                    
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
          
        }
    }//GEN-LAST:event_btnEditarProActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        if(!"".equals(txtIDPro.getText())){
           int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               int id = Integer.parseInt(txtIDPro.getText());
               prodDAO.EliminarProducto(id);
               Limpiar();            
          LimpiarTabla();
          ListarProducto();
           }
           
        }
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void BtnNuevoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoProActionPerformed
        Limpiar();
    }//GEN-LAST:event_BtnNuevoProActionPerformed

    private void btnExcelProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProActionPerformed
        etsel.reporte();
    }//GEN-LAST:event_btnExcelProActionPerformed

    private void TableProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProMouseClicked
        int fila = TablePro.rowAtPoint(evt.getPoint());
        txtIDPro.setText(TablePro.getValueAt(fila, 0).toString());
        txtCodigoPro.setText(TablePro.getValueAt(fila, 1).toString());
        txtNombrePro.setText(TablePro.getValueAt(fila, 2).toString());
        txtCantidadPro.setText(TablePro.getValueAt(fila, 3).toString());
        txtPrecioPro.setText(TablePro.getValueAt(fila, 4).toString());
        cbxProPro.setSelectedItem(TablePro.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_TableProMouseClicked

    private void txtCodigoVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoV.getText())) {
                String cod = txtCodigoV.getText();
                prod = prodDAO.BuscarPro(cod);
                if (prod.getNombre() != null) {
                    txtDescV.setText(""+prod.getNombre());
                    txtPrecioV.setText(""+prod.getPrecio());
                    txtDisponibleV.setText(""+prod.getCantidad());
                    txtCantidadV.requestFocus();
                }
                else{
                    txtDescV.setText("");
                    txtPrecioV.setText("");
                    txtDisponibleV.setText("");
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "Verifique El codigo");
                    
                }
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtCodigoV.requestFocus();
                }
        }
    }//GEN-LAST:event_txtCodigoVKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TMenu.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCantidadVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!"".equals(txtCantidadV.getText())){
                String cod = txtCodigoV.getText();
                String Nombre = txtDescV.getText();
                int Cant = Integer.parseInt(txtCantidadV.getText());
                Float precio = Float.parseFloat(txtPrecioV.getText());
                Float total = Cant * precio;
                int stock = Integer.parseInt(txtDisponibleV.getText());
                if (stock >= Cant) {
                    item = item++;
                    tmp = (DefaultTableModel) TableCVenta.getModel();
                    for (int i = 0; i < TableCVenta.getRowCount(); i++) {
                        if (TableCVenta.getValueAt(i, 1).equals(txtDescV.getText())) {
                          JOptionPane.showMessageDialog(null, "El producto ya esta registrado"); 
                          return;
                        }
                        
                    }
                    
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(Nombre);
                    lista.add(Cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    TableCVenta.setModel(tmp);
                    TotalPagar();
                    Limpiar();
                    txtCodigoV.requestFocus();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                    txtCantidadV.setText("");
                }
                
            }
            else{
                        JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
                        
            }
        }
    }//GEN-LAST:event_txtCantidadVKeyPressed

    private void btnEliminarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVActionPerformed
        tmp = (DefaultTableModel) TableCVenta.getModel();
        tmp.removeRow(TableCVenta.getSelectedRow());
        TotalPagar();
        txtCodigoV.requestFocus();
    }//GEN-LAST:event_btnEliminarVActionPerformed

    private void txtRucVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!"".equals(txtRucV.getText())){
                int dni = Integer.parseInt(txtRucV.getText());
                cl = client.BuscarCliente(dni);
                if (cl.getNombre() != null) {
                    txtNombreV.setText(""+cl.getNombre());
                    txtTelCV.setText(""+cl.getTelefono());
                    txtDirCV.setText(""+cl.getDireccion());
                    
                }
                else{
                    txtRucV.setText("");
                    JOptionPane.showMessageDialog(null, "Cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtRucVKeyPressed

    private void btnImprimirVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirVActionPerformed
        pdf();
        RegistrarVenta();
        RegistrarDetalle();
        actualizarS();
        
        LimpiarTVenta();
        Limpiar();
        LabelTotal.setText("----");
    }//GEN-LAST:event_btnImprimirVActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        TMenu.setSelectedIndex(3);
        LimpiarTabla();
        ListarVenta();
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
    TMenu.setSelectedIndex(6);
    LimpiarTabla();
    ListarUsuarios();
    Limpiar();
    
    }//GEN-LAST:event_btnUserActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if(!"".equals(txtRucCon.getText()) || !"".equals(txtNombreCon.getText()) ){
          conf.setRuc(txtRucCon.getText());
          conf.setNombre(txtNombreCon.getText());
          conf.setTelefono(txtTelefonoCon.getText());
          conf.setDireccion(txtDirCon.getText());
          conf.setRazon(txtRazonCon.getText());           
          conf.setId(Integer.parseInt(txtIDCon.getText()));
          prodDAO.ModificarDatos(conf);
          ListarConfig();
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void TableVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVMouseClicked
        int fila = TableV.rowAtPoint(evt.getPoint());
        txtIDV.setText(TableV.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TableVMouseClicked

    private void btnPDFVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFVActionPerformed
        
        try {
            int id = Integer.parseInt(txtIDV.getText()) ;
            File file = new File("src/pdf/venta"+id+".pdf");
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPDFVActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        TMenu.setSelectedIndex(4);
    }//GEN-LAST:event_btnConfigActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        RegistrarUsuario();
        LimpiarTabla();
        ListarUsuarios();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(!"".equals(txtIDUsu.getText())){
           int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               int id = Integer.parseInt(txtIDUsu.getText());
               login.EliminarUsuario(id);
               LimpiarTabla();
               ListarUsuarios();
               Limpiar();
           }
           
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if("".equals(txtIDUsu.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione Una Fila", "Error", HEIGHT);
            
        }
        else{
            if(!"".equals(txtUsuario.getText()) || !"".equals(txtPass.getText()) ){
          
          lg.setNombre(txtNombreUsu.getText());
          lg.setUsuario(txtUsuario.getText());
          lg.setPass(txtPass.getText());
          lg.setRol(cbxRolUsu.getSelectedItem().toString());
          lg.setId(Integer.parseInt(txtIDUsu.getText()));
          LimpiarTabla();
          login.Modificar(lg);
          ListarUsuarios();
          Limpiar();            
        }
        else{
            JOptionPane.showMessageDialog(null, "Completar datos", "Error", HEIGHT);
        }
          
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void TableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuariosMouseClicked
        int fila = TableUsuarios.rowAtPoint(evt.getPoint());
        txtIDUsu.setText(TableUsuarios.getValueAt(fila, 0).toString());
        txtNombreUsu.setText(TableUsuarios.getValueAt(fila, 1).toString());
        txtUsuario.setText(TableUsuarios.getValueAt(fila, 2).toString());
        txtPass.setText(TableUsuarios.getValueAt(fila, 3).toString());
        cbxRolUsu.setSelectedItem(TableUsuarios.getValueAt(fila, 4).toString());
        
    }//GEN-LAST:event_TableUsuariosMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               cfn.Clientes();
        
           }  
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        TMenu.setSelectedIndex(7);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               cfn.Ventas();
        
           }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               cfn.Proveedor();
        
           }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               cfn.Productos();
        
           }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?", "Eliminar", WIDTH);
           if(pregunta == 0){
               cfn.Detalles();
        
           }
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnNuevoPro;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel LabelVendedorV;
    private javax.swing.JTabbedPane TMenu;
    private javax.swing.JTable TablaP;
    private javax.swing.JTable TableCVenta;
    private javax.swing.JTable TablePro;
    private javax.swing.JTable TableUsuarios;
    private javax.swing.JTable TableV;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEditarC;
    private javax.swing.JButton btnEditarP;
    private javax.swing.JButton btnEditarPro;
    private javax.swing.JButton btnEliminarC;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnEliminarV;
    private javax.swing.JButton btnExcelPro;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnGuardarP;
    private javax.swing.JButton btnGuardarPro;
    private javax.swing.JButton btnImprimirV;
    private javax.swing.JButton btnNuevoC;
    private javax.swing.JButton btnNuevoP;
    private javax.swing.JButton btnPDFV;
    private javax.swing.JButton btnUser;
    private javax.swing.JComboBox<String> cbxProPro;
    private javax.swing.JComboBox<String> cbxRolUsu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tableCliente;
    private javax.swing.JTextField txtCantidadPro;
    private javax.swing.JTextField txtCantidadV;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtCodigoV;
    private javax.swing.JTextField txtDescV;
    private javax.swing.JTextField txtDirCV;
    private javax.swing.JTextField txtDirCon;
    private javax.swing.JTextField txtDireccionC;
    private javax.swing.JTextField txtDireccionP;
    private javax.swing.JTextField txtDisponibleV;
    private javax.swing.JTextField txtDniC;
    private javax.swing.JTextField txtIDC;
    private javax.swing.JTextField txtIDCon;
    private javax.swing.JTextField txtIDP;
    private javax.swing.JTextField txtIDPro;
    private javax.swing.JTextField txtIDUsu;
    private javax.swing.JTextField txtIDV;
    private javax.swing.JTextField txtIDVenta;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreCon;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtNombrePro;
    private javax.swing.JTextField txtNombreUsu;
    private javax.swing.JTextField txtNombreV;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JTextField txtPrecioV;
    private javax.swing.JTextField txtRazonC;
    private javax.swing.JTextField txtRazonCon;
    private javax.swing.JTextField txtRazonP;
    private javax.swing.JTextField txtRucCon;
    private javax.swing.JTextField txtRucP;
    private javax.swing.JTextField txtRucV;
    private javax.swing.JTextField txtTelCV;
    private javax.swing.JTextField txtTelefonoC;
    private javax.swing.JTextField txtTelefonoCon;
    private javax.swing.JTextField txtTelefonoP;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
private void Limpiar(){
    //Clientes
    txtIDC.setText("");
    txtDniC.setText("");
    txtNombreC.setText("");
    txtDireccionC.setText("");
    txtTelefonoC.setText("");
    txtRazonC.setText("");
    
    //Proveedores
    txtIDP.setText("");
    txtRucP.setText("");
    txtNombreP.setText("");
    txtDireccionP.setText("");
    txtTelefonoP.setText("");
    txtRazonP.setText("");
    
    //Productos
    txtIDPro.setText("");
    txtCodigoPro.setText("");
    txtNombrePro.setText("");
    txtCantidadPro.setText("");
    txtPrecioPro.setText("");
    cbxProPro.setSelectedIndex(0);
    
    //C. Venta
    txtCodigoV.setText("");
    txtDescV.setText("");
    txtCantidadV.setText("");
    txtPrecioV.setText("");
    txtDisponibleV.setText("");
    txtIDV.setText("");
    txtRucV.setText("");
    txtNombreV.setText("");
    
    //Usuarios
    txtUsuario.setText("");
    txtPass.setText("");
    txtNombreUsu.setText("");
    cbxRolUsu.setSelectedIndex(0);
    
    
}
    private void TotalPagar(){
        TotalPagar = 0.00;
        int numFila = TableCVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double calc = Double.parseDouble(String.valueOf(TableCVenta.getModel().getValueAt(i, 4)));
            TotalPagar = TotalPagar + calc;
        }
        LabelTotal.setText(String.format("%.2f", TotalPagar));
    }
    private void RegistrarVenta(){
        String cliente = txtNombreV.getText();
        String Vendedor = LabelVendedorV.getText();
        double monto = TotalPagar;
        v.setCliente(cliente);
        v.setVendedor(Vendedor);
        v.setTotal(monto);
        vd.RegistrarVenta(v);
        
    }
    private void pdf(){
        try{
            int id = vd.IdVenta()+1;
            
            System.out.println("Este es el bueno"+id);
            FileOutputStream archivo;
            File file = new File("src/pdf/venta"+id+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document ();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/logo_pdf.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: "+id+" \n"+ "Fecha: "+new SimpleDateFormat("dd-MM-yyyy").format(date)+" Hora: "+new SimpleDateFormat("kk:mm:ss").format(date)+"\n\n");
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnaencabezado = new float[]{20f,30f,70f,40f};
            Encabezado.setWidths(columnaencabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            Encabezado.addCell(img);
            String ruc = txtRucCon.getText();
            String nombre = txtNombreCon.getText();
            String tel = txtTelefonoCon.getText();
            String dir = txtDirCon.getText();
            String ra =  txtRazonCon.getText();
            
            Encabezado.addCell("");
            Encabezado.addCell("Ruc: "+ruc+"\nNombre: "+nombre+"\nTelefono: "+tel+"\nDireccion: "+dir+"\nRazon: "+ra );
            Encabezado.addCell(fecha);
            doc.add(Encabezado);
            
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos del CLiente: "+"\n\n");
            doc.add(cli);
            
            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] columnacli = new float[]{20f,50f,30f,40f};
            tablacli.setWidths(columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc: ", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono: ", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion: ", negrita));
            
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
            
            tablacli.addCell(txtRucV.getText());
            tablacli.addCell(txtNombreV.getText());
            tablacli.addCell(txtTelCV.getText());
            tablacli.addCell(txtDirCV.getText());
            
            doc.add(tablacli);
            
             
            //productos
            PdfPTable tablapro = new PdfPTable(4);
            tablapro.setWidthPercentage(100);
           tablapro.getDefaultCell().setBorder(0);
            float[] columnapro = new float[]{10f,50f,15f,20f};
            tablapro.setWidths(columnacli);
            tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad ", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total: ", negrita ));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);
            
            for (int i = 0; i < TableCVenta.getRowCount(); i++) {
                String cant = TableCVenta.getValueAt(i,2).toString();
                String produc = TableCVenta.getValueAt(i,1).toString();
                String prec = TableCVenta.getValueAt(i,3).toString();
                String total = TableCVenta.getValueAt(i,4).toString();
                
                 tablapro.addCell(cant);
                tablapro.addCell(produc);
                tablapro.addCell(prec);
                tablapro.addCell(total);
            }
            
           
            doc.add(tablapro);
            
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a Pagar: "+ LabelTotal.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            
            Paragraph mens = new Paragraph();
            mens.add(Chunk.NEWLINE);
            mens.add("Gracias por su compra");
            mens.setAlignment(Element.ALIGN_CENTER);
            doc.add(mens);
            
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        }
        catch(DocumentException | IOException e){
            System.out.println(e);
                 
        }
    }
    private void RegistrarDetalle(){
        int id = vd.IdVenta();
        for (int i = 0; i < TableCVenta.getRowCount(); i++) {
            String cod = TableCVenta.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(TableCVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(TableCVenta.getValueAt(i, 3).toString());
            
            System.out.println(id);
            dv.setC_producto(cod);
            dv.setCantidad(cant);
            dv.setPrecio(precio);
            dv.setID_V(id);
            vd.RegistrarDetalle(dv);
        }
    }
    
    private void actualizarS(){
        for (int i = 0; i < TableCVenta.getRowCount(); i++) {
            String cod = TableCVenta.getValueAt(i, 0).toString();
            int cant =Integer.parseInt( TableCVenta.getValueAt(i, 2).toString());
            prod = prodDAO.BuscarPro(cod);
            int StockActual = prod.getCantidad() - cant;
            vd.ActualizarStock(StockActual, cod);
        }
}
    private void LimpiarTVenta(){
        tmp = (DefaultTableModel) TableCVenta.getModel();
        int fila = TableCVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }
    public void RegistrarUsuario(){
    String usuario = txtUsuario.getText();
    String pass = txtPass.getText();
    String nombre = txtNombreUsu.getText();
    String rol = cbxRolUsu.getSelectedItem().toString();
    
    if(!"".equals(usuario)|| !"".equals(pass)){
        login lg = new login();
        LoginDAO login = new LoginDAO();
        lg.setNombre(nombre);
        lg.setUsuario(usuario);
        lg.setPass(pass);
        lg.setRol(rol);
        login.Registrar(lg);
        
    }
    
}
    
}
