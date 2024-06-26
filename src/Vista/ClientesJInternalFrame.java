package Vista;

import Controlador.MenuJFrame;
import Modelo.*;
import Vista.ServiciosJInternalFrame;
import Vista.DiagnosticoJInternalFrame;
import Vista.ClientesJInternalFrame;
import Vista.EmpleadosJInternalFrame;
import Vista.EntregaJInternalFrame;
import Vista.MDDiagnosticoJInternalFrame;
import Vista.MDEntregaEquipJInternalFrame;
import Vista.MDRecepcionJInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ClientesJInternalFrame extends javax.swing.JInternalFrame {

  private javax.swing.JDesktopPane jDesktopPane; // Declaración de la variable

    public ClientesJInternalFrame() throws SQLException {
        initComponents();
        jDesktopPane = new javax.swing.JDesktopPane(); //aca se tiene que inicializar la variable
                                                       //obviamente en todas las interfaces
        getContentPane().add(jDesktopPane); //agregamos el JDesktopPane al contenido del JFrame
        TxtID.setEnabled(false);
        ObtenerDatos();
    }
    
    
    private void ObtenerDatos() throws SQLException{
        //se crea una lista que almacena los datos obtenidos de la BD
        List<Clientes> cliente= new DAOClientes().ObtenerDatos();
        //Define un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Arreglo con nombre de columnas de la tabla
        String[] columnas = {"Id_cliente", "Nombre", "Apellido", "Direccion",
        "tipo_cli", "Telefono", "Cedula"};
        //Establece los nombres definidos de las columnas
        modelo.setColumnIdentifiers(columnas);
        for(Clientes cli : cliente){ //Recorre cada elemento de la lista y los agrega
            //al modelo de la tabla
            String[] renglon = {Integer.toString(cli.getId_cliente()), cli.getNombre(),
                cli.getApellido(), cli.getDireccion(), cli.getTipo_cli(),
                cli.getTelefono(), cli.getCedula()};
            modelo.addRow(renglon);   
            }
        TableClientes.setModel(modelo);//Ubica los datos del modelo en la tabla
        }
    
 public void actualizarClientes() throws SQLException {
        int id= Integer.parseInt(this.TxtID.getText());
        String nom = this.TxtNombre.getText();
        String ape = this.TxtApellido.getText();
        String dire = this.TxtDireccion.getText();
        String tipo = this.TxtTipo.getText();
        String tel = this.TxtTelefono.getText();
        String ced = this.TxtCedula.getText();
        
        Clientes cliente = new Clientes(id, nom, ape,
                dire, tipo, tel, ced);
        
        DAOClientes dao = new DAOClientes();
        int res = dao.Actualizar(cliente);
        if(res == 0){
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Cliente Actualizado!");
        } else{
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Ocurrió un ERROR!");
        }
    }


    
    public void limpiarCampos(){
        TxtID.setText("");
        TxtNombre.setText("");
        TxtApellido.setText("");
        TxtDireccion.setText("");
        TxtTipo.setText("");
        TxtTelefono.setText("");
        TxtCedula.setText("");
    }

     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableClientes = new javax.swing.JTable();
        TxtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        BtnActualizar = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        BtnListar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        TxtBuscar = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        TxtTipo = new javax.swing.JTextField();
        TxtNombre = new javax.swing.JFormattedTextField();
        TxtApellido = new javax.swing.JFormattedTextField();
        TxtTelefono = new javax.swing.JFormattedTextField();
        TxtCedula = new javax.swing.JFormattedTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 211, 218));

        TableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Dirección", "Tipo de Cliente", "Teléfono", "Cédula"
            }
        ));
        jScrollPane1.setViewportView(TableClientes);

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 45));
        jLabel1.setText("CLIENTES");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setText("ID Cliente:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setText("Apellido:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setText("Telefono:");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setText("Direccion:");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setText("Cedula:");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel11.setText("Tipo Cliente:");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        BtnActualizar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnAgregar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnListar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnListar.setText("Listar");
        BtnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListarActionPerformed(evt);
            }
        });

        BtnEliminar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnBuscar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(BtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        TxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtNombreKeyTyped(evt);
            }
        });

        TxtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtApellidoKeyTyped(evt);
            }
        });

        try {
            TxtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            TxtCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(949, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(114, 114, 114))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                            .addComponent(TxtID, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                            .addComponent(TxtNombre)
                                            .addComponent(TxtApellido)
                                            .addComponent(TxtTelefono)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(TxtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(TxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 200, 202), 7, true));

        jPanel16.setBackground(new java.awt.Color(153, 200, 202));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compuser chiquito.png"))); // NOI18N
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seo.png"))); // NOI18N
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/obrero.png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mantenimiento.png"))); // NOI18N
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente.png"))); // NOI18N
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ordenador-portatil.png"))); // NOI18N
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/formulario-de-llenado_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel49)
                .addGap(101, 101, 101)
                .addComponent(jLabel48)
                .addGap(121, 121, 121)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(jLabel50)
                .addGap(113, 113, 113)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(35, 35, 35))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(528, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        //crear una instancia de ServiciosJInternalFrame
        MDDiagnosticoJInternalFrame serviciosFrame = new MDDiagnosticoJInternalFrame();
        //agregar la instancia al escritorio (JDesktopPane)
        jDesktopPane.add(serviciosFrame);
        //hacer visible el internal frame
        serviciosFrame.setVisible(true);
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
      try {
          //crear una instancia de ServiciosJInternalFrame
          EmpleadosJInternalFrame serviciosFrame = new EmpleadosJInternalFrame();
          
          //agregar la instancia al escritorio (JDesktopPane)
          jDesktopPane.add(serviciosFrame);
          
          //hacer visible el internal frame
          serviciosFrame.setVisible(true);
      } catch (SQLException ex) {
          Logger.getLogger(ClientesJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
      try {
          //crear una instancia de ServiciosJInternalFrame
          ServiciosJInternalFrame serviciosFrame = new ServiciosJInternalFrame();
          
          //agregar la instancia al escritorio (JDesktopPane)
          jDesktopPane.add(serviciosFrame);
          
          //hacer visible el internal frame
          serviciosFrame.setVisible(true);
      } catch (SQLException ex) {
          Logger.getLogger(ClientesJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
      try {
          //crear una instancia de ServiciosJInternalFrame
          ClientesJInternalFrame serviciosFrame = new ClientesJInternalFrame();
          
          //agregar la instancia al escritorio (JDesktopPane)
          jDesktopPane.add(serviciosFrame);
          
          //hacer visible el internal frame
          serviciosFrame.setVisible(true);
      } catch (SQLException ex) {
          Logger.getLogger(ClientesJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
    // Crear una instancia del JInternalFrame deseado
   SubMenuJInternalFrame equipoFrame = new SubMenuJInternalFrame();
    
    // Agregar la instancia al JDesktopPane
    jDesktopPane.add(equipoFrame);
    
    // Hacer visible el JInternalFrame
    equipoFrame.setVisible(true);
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
//alternativa para moverse o redirigirnos de jinternal a jframe
    dispose(); //estp cierra el JInternalFrame actual
    
    //y se abre el JFrame deseado
    MenuJFrame menuFrame = new MenuJFrame();
    menuFrame.setVisible(true);



    }//GEN-LAST:event_jLabel46MouseClicked

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        try{
            //Captura datos de las cajas de texto
            String nomb= TxtNombre.getText();
            String apell= TxtApellido.getText();
            String dire= TxtDireccion.getText();
            String tipo= TxtTipo.getText();
            String tel= TxtTelefono.getText();
            String ced= TxtCedula.getText();
            
            //Agregar validaciones a cajas de texto según formato y
            //Caracteres válidos a ingresar
            //Comprueba que las cajas de texto no esten vacias
            if(nomb.contentEquals("")|| apell.contentEquals("")
                    || dire.contentEquals("") || tipo.contentEquals("")
                    || tel.contentEquals("") || ced.contentEquals("")){
                JOptionPane.showMessageDialog(rootPane,
                        "Todos los campos son obligatorios");
            } else{
                try{
                    //Objeto para pasar valores a método Insertar de DAOAutor
                    Clientes cliente = new Clientes(nomb,apell,dire,
                            tipo,tel,ced);
                    DAOClientes dao = new DAOClientes();
                    if (dao.Insertar(cliente) == 0){
                        JOptionPane.showMessageDialog(rootPane,
                                "Registro agregado");
                    }
                } catch (HeadlessException | SQLException e){
                    JOptionPane.showMessageDialog(rootPane,
                            "No se agregó el registro");
                }
                }
                ObtenerDatos(); //llama a este método para que se muestre el
                //nuevo registro en la tabla formulario
                limpiarCampos(); //Llama a este método para limpiar la caja de texto
            } catch (SQLException ex){
                Logger.getLogger(ClientesJInternalFrame.class.getName()).
                        log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListarActionPerformed
        int fila = this.TableClientes.getSelectedRow(); //Se obtiene #fila seleccionado
        if (fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else { //Se toma cada campo de la tabla del registro seleccionado
            // y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableClientes.
                        getValueAt(fila,0).toString());
                String nom = (String) this.TableClientes.getValueAt(fila,1);
                String ape = (String) this.TableClientes.getValueAt(fila,2);
                String dire = (String) this.TableClientes.getValueAt(fila,3);
                String tipo = (String) this.TableClientes.getValueAt(fila,4);
                String tel = (String) this.TableClientes.getValueAt(fila,5).toString();
                String ced = (String) this.TableClientes.getValueAt(fila, 6);
                
                //Se ubican en las cajas de textos los datos capturados de la tabla
                TxtID.setText("" + id);
                TxtNombre.setText(nom);
                TxtApellido.setText(ape);
                TxtDireccion.setText(dire);
                TxtTipo.setText(tipo);
                TxtTelefono.setText(tel);
                TxtCedula.setText(ced);
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                        "¡Ocurrió un ERROR! "+e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnListarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        try{
            actualizarClientes();
        } catch(SQLException ex){
            Logger.getLogger(ClientesJInternalFrame.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        try{
            ObtenerDatos();
        } catch(SQLException ex) {
            Logger.getLogger(ClientesJInternalFrame.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = this.TableClientes.getSelectedRow();
        if(fila == -1) { 
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else{
            JDialog.setDefaultLookAndFeelDecorated(true);
            int resp = JOptionPane.showConfirmDialog(null,
                    "¿Esta seguro de eliminar?", "Aceptar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(resp == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(rootPane,
                        "Registro no borrado");
            } else{
                if(resp == JOptionPane.YES_OPTION) {
                    try{
                        int id = Integer.parseInt((String) this.TableClientes.
                                getValueAt(fila,0).toString());
                        DAOClientes dao = new DAOClientes();
                        dao.Eliminar(id);
                        ObtenerDatos();
                    } catch (SQLException ex){
                        Logger.getLogger(ClientesJInternalFrame.class.getName()).
                                log(Level.SEVERE,null, ex);
                    }
                }
            }
            if(resp == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(rootPane,
                        "Ninguna acción realizada");
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        String criterio = TxtBuscar.getText().trim();
        if(criterio.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "por favor, ingrese un criterio de busqueda.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            try {
                ObtenerDatos();
            } catch (SQLException ex) {
                Logger.getLogger(ClientesJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        try{
            List<Clientes> clientes = new DAOClientes().ObtenerDatos();
            DefaultTableModel modelo = new  DefaultTableModel();
            String[] columnas = {"Id_cliente", "Nombre", "Apellido", "Direccion",
            "tipo_cli", "Telefono", "Cedula"};
            modelo.setColumnIdentifiers(columnas);
            
            //Convertir criterio a numero si es posible
            Integer criterioID = null;
            try{
                criterioID = Integer.parseInt(criterio);
            } catch (NumberFormatException e){
                // No hacer nada, criterio no es un numero
            }
            
            for (Clientes cliente : clientes) {
                boolean coincide = false;
                
                // comparar con IDcliente si criterio es númerico
                if(criterioID != null){
                    if(cliente.getId_cliente() == criterioID){
                        coincide = true;
                    }
                } else{
                    //Comparar con otros campos si criterio es texto
                    if(cliente.getNombre().toLowerCase().contains(criterio.toLowerCase())||
                       cliente.getApellido().toLowerCase().contains(criterio.toLowerCase())||
                       cliente.getDireccion().toLowerCase().contains(criterio.toLowerCase())||
                       cliente.getTipo_cli().toLowerCase().contains(criterio.toLowerCase())||
                       cliente.getTelefono().contains(criterio)||
                       cliente.getCedula().contains(criterio)) {
                        coincide = true;
                    }
                }
                if(coincide){
                String[] renglon = {
                    Integer.toString(cliente.getId_cliente()),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDireccion(),
                    cliente.getTipo_cli(),
                    cliente.getTelefono(),
                    cliente.getCedula()
                };
                modelo.addRow(renglon);
                }
            }
            TableClientes.setModel(modelo);
            if(modelo.getRowCount() == 0){
                JOptionPane.showMessageDialog(this,
                        "No se encontraron resultados para el criterio de busqueda.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex){
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error a realizar la búsqueda.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNombreKeyTyped
        char car=evt.getKeyChar();
        if((car<'a' || car>'z')&& (car<'A' || car >'Z')
            && car !='á'
            && car !='é'
            && car !='í'
            && car !='ó'
            && car !='ú'
            && car !='Á'
            && car !='É'
            && car !='Í'
            && car !='Ó'
            && car !='Ú'
            && car !='Ü'
            && car !='ü'
            && car !='Ñ'
            && car !='ñ'
            && car !=(char) KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_TxtNombreKeyTyped

    private void TxtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtApellidoKeyTyped
       char car=evt.getKeyChar();
        if((car<'a' || car>'z')&& (car<'A' || car >'Z')
            && car !='á'
            && car !='é'
            && car !='í'
            && car !='ó'
            && car !='ú'
            && car !='Á'
            && car !='É'
            && car !='Í'
            && car !='Ó'
            && car !='Ú'
            && car !='Ü'
            && car !='ü'
            && car !='Ñ'
            && car !='ñ'
            && car !=(char) KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_TxtApellidoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnListar;
    private javax.swing.JTable TableClientes;
    private javax.swing.JFormattedTextField TxtApellido;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JFormattedTextField TxtCedula;
    private javax.swing.JTextField TxtDireccion;
    private javax.swing.JTextField TxtID;
    private javax.swing.JFormattedTextField TxtNombre;
    private javax.swing.JFormattedTextField TxtTelefono;
    private javax.swing.JTextField TxtTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
