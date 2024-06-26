package Vista;

import Vista.ServiciosJInternalFrame;
import Vista.DiagnosticoJInternalFrame;
import Vista.ClientesJInternalFrame;
import Vista.EmpleadosJInternalFrame;
import Vista.EntregaJInternalFrame;
import Vista.MDDiagnosticoJInternalFrame;
import Vista.MDEntregaEquipJInternalFrame;
import Vista.MDRecepcionJInternalFrame;
import javax.swing.JDesktopPane;
import Controlador.MenuJFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controlador.MenuJFrame;
import java.sql.SQLException;
import Modelo.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class EquipoCompJInternalFrame extends javax.swing.JInternalFrame {

   private javax.swing.JDesktopPane jDesktopPane; // Declaración de la variable

    public EquipoCompJInternalFrame() throws SQLException {
        initComponents();
        jDesktopPane = new javax.swing.JDesktopPane(); //aca se tiene que inicializar la variable
                                                       //obviamente en todas las interfaces
        getContentPane().add(jDesktopPane); //agregamos el JDesktopPane al contenido del JFrame
        TxtID.setEnabled(false);
        ObtenerDatos();
    }
    
    private void ObtenerDatos() throws SQLException{
        //se crea una lista que almacena los datos obtenidos de la BD
        List<Equipos_Computarizados> equipo= new DAOEquipos_Computarizados().ObtenerDatos();
        //Define un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Arreglo con nombre de columnas de la tabla
        String[] columnas = {"Id_equipocomp", "Tipo", "Marca", "Color",
        "Modelo", "Id_cliente"};
        //Establece los nombres definidos de las columnas
        modelo.setColumnIdentifiers(columnas);
        for(Equipos_Computarizados equi : equipo){ //Recorre cada elemento de la lista y los agrega
            //al modelo de la tabla
            String[] renglon = {Integer.toString(equi.getId_equipocomp()), equi.getTipo(),
                equi.getMarca(), equi.getColor(), equi.getModelo(),
                Integer.toString(equi.getId_cliente())};
            modelo.addRow(renglon);   
            }
        TableEquipos.setModel(modelo);//Ubica los datos del modelo en la tabla
        }
    
    public void actualizar() throws SQLException {
        int id= Integer.parseInt(this.TxtID.getText());
        String ti = this.TxtTipo.getText();
        String mar = this.TxtMarca.getText();
        String col = this.TxtColor.getText();
        String mod = this.TxtModelo.getText();
        int cli = Integer.parseInt(this.TxtCliente.getText());
        
        Equipos_Computarizados equipo = new Equipos_Computarizados(id,
                ti, mar,col, mod, cli);
        
        DAOEquipos_Computarizados dao = new DAOEquipos_Computarizados();
        int res = dao.Actualizar(equipo);
        if(res == 0){
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Equipo Actualizado!");
        } else{
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Ocurrió un ERROR!");
        }
    }
    
    public void limpiarCampos(){
        TxtID.setText("");
        TxtTipo.setText("");
        TxtMarca.setText("");
        TxtColor.setText("");
        TxtModelo.setText("");
        TxtCliente.setText("");
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEquipos = new javax.swing.JTable();
        TxtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtTipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        BtnActualizar = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        BtnListar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        TxtBuscar = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        TxtMarca = new javax.swing.JTextField();
        TxtColor = new javax.swing.JTextField();
        TxtModelo = new javax.swing.JTextField();
        TxtCliente = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 211, 218));

        TableEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Marca", "Color", "Modelo", "Cliente"
            }
        ));
        jScrollPane1.setViewportView(TableEquipos);

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 45));
        jLabel1.setText("EQUIPO");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setText("ID Equipo:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setText("Tipo:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setText("Marca:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setText("Color:");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setText("Modelo:");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel10.setText("Cliente:");

        jPanel5.setBackground(new java.awt.Color(225, 223, 223));

        BtnActualizar.setBackground(new java.awt.Color(153, 153, 153));
        BtnActualizar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnAgregar.setBackground(new java.awt.Color(153, 255, 153));
        BtnAgregar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnListar.setBackground(new java.awt.Color(255, 204, 204));
        BtnListar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnListar.setText("Listar");
        BtnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListarActionPerformed(evt);
            }
        });

        BtnEliminar.setBackground(new java.awt.Color(255, 153, 153));
        BtnEliminar.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnBuscar.setBackground(new java.awt.Color(153, 255, 153));
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
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(BtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(BtnBuscar)
                        .addGap(95, 95, 95)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(19, 19, 19))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9))
                            .addGap(81, 81, 81)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TxtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                .addComponent(TxtCliente)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(94, 94, 94)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 200, 202), 7, true));

        jPanel12.setBackground(new java.awt.Color(153, 200, 202));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compuser chiquito.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seo.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/obrero.png"))); // NOI18N
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mantenimiento.png"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente.png"))); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ordenador-portatil.png"))); // NOI18N
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/formulario-de-llenado_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel35)
                .addGap(101, 101, 101)
                .addComponent(jLabel34)
                .addGap(121, 121, 121)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(jLabel36)
                .addGap(113, 113, 113)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addGap(35, 35, 35))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addGap(9, 9, 9)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
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
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(498, Short.MAX_VALUE)))
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

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        //crear una instancia de ServiciosJInternalFrame
        MDDiagnosticoJInternalFrame serviciosFrame = new MDDiagnosticoJInternalFrame();
        //agregar la instancia al escritorio (JDesktopPane)
        jDesktopPane.add(serviciosFrame);
        //hacer visible el internal frame
        serviciosFrame.setVisible(true);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
       try {
           //crear una instancia de ServiciosJInternalFrame
           EmpleadosJInternalFrame serviciosFrame = new EmpleadosJInternalFrame();
           
           //agregar la instancia al escritorio (JDesktopPane)
           jDesktopPane.add(serviciosFrame);
           
           //hacer visible el internal frame
           serviciosFrame.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(EquipoCompJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
       try {
           //crear una instancia de ServiciosJInternalFrame
           ServiciosJInternalFrame serviciosFrame = new ServiciosJInternalFrame();
           
           //agregar la instancia al escritorio (JDesktopPane)
           jDesktopPane.add(serviciosFrame);
           
           //hacer visible el internal frame
           serviciosFrame.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(EquipoCompJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
       try {
           //crear una instancia de ServiciosJInternalFrame
           ClientesJInternalFrame serviciosFrame = new ClientesJInternalFrame();
           
           //agregar la instancia al escritorio (JDesktopPane)
           jDesktopPane.add(serviciosFrame);
           
           //hacer visible el internal frame
           serviciosFrame.setVisible(true);
       } catch (SQLException ex) {
           Logger.getLogger(EquipoCompJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
    // Crear una instancia del JInternalFrame deseado
   SubMenuJInternalFrame equipoFrame = new SubMenuJInternalFrame();
    
    // Agregar la instancia al JDesktopPane
    jDesktopPane.add(equipoFrame);
    
    // Hacer visible el JInternalFrame
    equipoFrame.setVisible(true);
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
//alternativa para moverse o redirigirnos de jinternal a jframe
    dispose(); //estp cierra el JInternalFrame actual
    
    //y se abre el JFrame deseado
    MenuJFrame menuFrame = new MenuJFrame();
    menuFrame.setVisible(true);
    }//GEN-LAST:event_jLabel32MouseClicked

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        try{
            //Captura datos de las cajas de texto
            String ti= TxtTipo.getText();
            String mar= TxtMarca.getText();
            String col= TxtColor.getText();
            String mod= TxtModelo.getText();
            String cli= TxtCliente.getText();
            
            //Agregar validaciones a cajas de texto según formato y
            //Caracteres válidos a ingresar
            //Comprueba que las cajas de texto no esten vacias
            if(ti.contentEquals("")|| mar.contentEquals("")
                    || col.contentEquals("")
                    || mod.contentEquals("") || cli.contentEquals("")){
                JOptionPane.showMessageDialog(rootPane,
                        "Todos los campos son obligatorios");
            } else{
                try{
                    int cl = Integer.parseInt(cli);
                    //Objeto para pasar valores a método Insertar de DAOAutor
                    Equipos_Computarizados equipo = new Equipos_Computarizados(
                            ti,mar,col,mod,cl);
                    DAOEquipos_Computarizados dao = new DAOEquipos_Computarizados();
                    if (dao.Insertar(equipo) == 0){
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
                Logger.getLogger(EquipoCompJInternalFrame.class.getName()).
                        log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListarActionPerformed
        int fila = this.TableEquipos.getSelectedRow(); //Se obtiene #fila seleccionado
        if (fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else { //Se toma cada campo de la tabla del registro seleccionado
            // y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableEquipos.
                        getValueAt(fila,0).toString());
                String ti = (String) this.TableEquipos.getValueAt(fila,1);
                String mar = (String) this.TableEquipos.getValueAt(fila,2);
                String col = (String) this.TableEquipos.getValueAt(fila,3);
                String mod = (String) this.TableEquipos.getValueAt(fila,4);
                String cli = (String) this.TableEquipos.getValueAt(fila, 5);
                
                //Se ubican en las cajas de textos los datos capturados de la tabla
                TxtID.setText("" + id);
                TxtTipo.setText(ti);
                TxtMarca.setText(mar);
                TxtColor.setText(col);
                TxtModelo.setText(mod);
                TxtCliente.setText(cli);
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                        "¡Ocurrió un ERROR! "+e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnListarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        try{
            actualizar();
        } catch(SQLException ex){
            Logger.getLogger(EquipoCompJInternalFrame.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        try{
            ObtenerDatos();
        } catch(SQLException ex) {
            Logger.getLogger(EquipoCompJInternalFrame.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = this.TableEquipos.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.TableEquipos.
                                getValueAt(fila,0).toString());
                        DAOEquipos_Computarizados dao = new DAOEquipos_Computarizados();
                        dao.Eliminar(id);
                        ObtenerDatos();
                    } catch (SQLException ex){
                        Logger.getLogger(EquipoCompJInternalFrame.class.getName()).
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
                Logger.getLogger(EquipoCompJInternalFrame.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        try{
            List<Equipos_Computarizados> equipos = new DAOEquipos_Computarizados().ObtenerDatos();
            DefaultTableModel modelo = new  DefaultTableModel();
            String[] columnas = {"Id_equipocomp", "Tipo", "Marca", "Color",
                "Modelo", "Id_cliente"};
            modelo.setColumnIdentifiers(columnas);
            //Convertir criterio a numero si es posible
            Integer criterioID = null;
            try{
                criterioID = Integer.parseInt(criterio);
            } catch (NumberFormatException e){
                // No hacer nada, criterio no es un numero
            }
            Integer criterioIDC = null;
            try{
                criterioIDC = Integer.parseInt(criterio);
            } catch(NumberFormatException ex){
                // No hacer nada, criterio no es un numero
            }
            
            for (Equipos_Computarizados equipo : equipos) {
                boolean coincide = false;
                
                // comparar con ID_ser si criterio es númerico
                if(criterioID != null){
                    if(equipo.getId_equipocomp() == criterioID){
                        coincide = true;
                    }
                } else if(criterioIDC != null){
                    if(equipo.getId_cliente() == criterioIDC){
                        coincide = true;
                    }
                }else{
                    //Comparar con otros campos si criterio es texto
                    if(equipo.getTipo().toLowerCase().contains(criterio.toLowerCase())||
                       equipo.getMarca().toLowerCase().contains(criterio.toLowerCase())||
                       equipo.getColor().toLowerCase().contains(criterio.toLowerCase())||
                       equipo.getModelo().toLowerCase().contains(criterio.toLowerCase())){
                        coincide = true;
                    }
                }
                if(coincide){
                String[] renglon = {
                    Integer.toString(equipo.getId_equipocomp()),
                    equipo.getTipo(),
                    equipo.getMarca(),
                    equipo.getColor(),
                    equipo.getModelo(),
                    Integer.toString(equipo.getId_cliente())
                };
                modelo.addRow(renglon);
                }
            }
            TableEquipos.setModel(modelo);
            if(modelo.getRowCount() == 0){
                JOptionPane.showMessageDialog(this,
                        "No se encontraron resultados para el criterio de busqueda.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex){
            Logger.getLogger(Equipos_Computarizados.class.getName())
                    .log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error a realizar la búsqueda.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnListar;
    private javax.swing.JTable TableEquipos;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JTextField TxtColor;
    private javax.swing.JTextField TxtID;
    private javax.swing.JTextField TxtMarca;
    private javax.swing.JTextField TxtModelo;
    private javax.swing.JTextField TxtTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
