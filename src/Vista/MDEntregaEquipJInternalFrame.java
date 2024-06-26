package Vista;
import Modelo.*;
import Modelo.Clientes;
import Modelo.Empleados;
import Modelo.Equipos_Computarizados;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import java.util.HashMap;

public class MDEntregaEquipJInternalFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel ModeloTablaEntrega;
    private Object[] objetoEntregaTabla = new Object[6];
    private Double total = 0.0;
    private int item = 0;
    private java.sql.Date fech;
    private int nument = 0;
    private int cantidad = 0;
    private int ide = 0;
    private java.sql.Date fecha;
    
    public MDEntregaEquipJInternalFrame() {
        initComponents();
        TxtIDCliente.setText("0");
        TxtIDEquipo.setText("0");
        TxtIDEmpleado.setText("0");
        jDialogCliente.setLocationRelativeTo(null);
        jDialogEquipoComp.setLocationRelativeTo(null);
        jDialogEmpleado.setLocationRelativeTo(null);
        TxtIDEntrega.setEnabled(false);
        TxtIDCliente.setEnabled(false);
        TxtNombreCliente.setEnabled(false);
        TxtIDEquipo.setEnabled(false);
        TxtEquipo.setEnabled(false);
        TxtIDEmpleado.setEnabled(false);
        TxtNombreEmpleado.setEnabled(false);
        //Obtiene la fecha actual
        fech = java.sql.Date.valueOf(LocalDate.now());
        TxtFecha.setText(fech.toString());
        
        
        DAOEntrega daoE = new DAOEntrega();
        try {
            ide = daoE.ObtenerUltimoIDEntrega()+1;
        } catch (SQLException ex) {
            Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TxtIDEntrega.setText(Integer.toString(ide));
        
        
        ModeloTablaEntrega=(DefaultTableModel) TableEntrega.getModel();
        
    }
    
    public void limpiarCamposEntrega(){
        TxtFecha.setText(fech.toString());
        TxtEstado.setText("");
    }
    
    public void limpiarCamposClientes(){
        TxtIDCliente.setText("0");
        TxtNombreCliente.setText("");
    }
    
    public void limpiarCamposEmpleados(){
        TxtIDEmpleado.setText("0");
        TxtNombreEmpleado.setText("");
    }
    
    public void limpiarCamposEquipos(){
        TxtIDEquipo.setText("0");
        TxtEquipo.setText("");
    }
    
    private void buscarDatosCliente (String dato) throws SQLException{
        //Se crea una lista que almacena los datos obtenidos de la BD
        List<Clientes> cliente = new DAOClientes().buscarDialogoClientes(dato);
        //Define un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Arreglo con nombre de columnas de la tabla
        String[] columnas = {"Id_cliente", "Nombre", "Apellido", "Direccion",
            "tipo_cli", "Telefono", "Cedula"};
        //Establece los nombres definidos de las columnas
        modelo.setColumnIdentifiers(columnas);
        for(Clientes cli : cliente){ //Recorre cada elemento de la lista
            // y los agrega al modelo de la tabla
            String[] renglon = {Integer.toString(cli.getId_cliente()), cli.getNombre(),
                cli.getApellido(), cli.getDireccion(), cli.getTipo_cli(),
                cli.getTelefono(), cli.getCedula()};
            modelo.addRow(renglon);
            
        } //Carga datos en la tabla
        TableDBuscaCliente.setModel(modelo); //Ubica los datos del modelo de la tabla
    }
    
    
    private void buscarDatosEquipo (String dato) throws SQLException{
        //Se crea una lista que almacena los datos obtenidos de la BD
        List<Equipos_Computarizados> equipo = new DAOEquipos_Computarizados().
                buscarDialogoEquipo(dato);
        //Define un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Arreglo con nombre de columnas de la tabla
        String[] columnas = {"Id_equipocomp", "Tipo", "Marca", "Color",
            "Modelo", "Id_cliente"};
        //Establece los nombres definidos de las columnas
        modelo.setColumnIdentifiers(columnas);
        for(Equipos_Computarizados equi : equipo){ //Recorre cada elemento de la lista
            // y los agrega al modelo de la tabla
            String[] renglon = {Integer.toString(equi.getId_equipocomp()), equi.getTipo(),
                equi.getMarca(), equi.getColor(), equi.getModelo(),
                Integer.toString(equi.getId_cliente())};
            modelo.addRow(renglon);
            
        } //Carga datos en la tabla
        TableDbuscaEquipo.setModel(modelo); //Ubica los datos del modelo de la tabla
    }
    
    private void buscarDatosEmpleado (String dato) throws SQLException{
        //Se crea una lista que almacena los datos obtenidos de la BD
        List<Empleados> empleado = new DAOEmpleados().buscarDialogoEmpleado(dato);
        //Define un modelo para la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Arreglo con nombre de columnas de la tabla
        String[] columnas = {"Id_empleado", "Nombre", "Apellido", "Telefono",
            "Direccion", "Cedula"};
        //Establece los nombres definidos de las columnas
        modelo.setColumnIdentifiers(columnas);
        for(Empleados emp : empleado){ //Recorre cada elemento de la lista
            // y los agrega al modelo de la tabla
            String[] renglon = {Integer.toString(emp.getId_empleado()), emp.getNombre(),
                emp.getApellido(), emp.getTelefono(), emp.getDireccion(),emp.getCedula()};
            modelo.addRow(renglon);
            
        } //Carga datos en la tabla
        TableDBuscaEmpleado.setModel(modelo); //Ubica los datos del modelo de la tabla
    }
    
    
    public void guardarEntrega() throws SQLException{
        //Datos a guardar en diagnostico: Descripcion, idequipo, idcliente, idempleado
        String Estado;
        int Id_equipocomp, Id_cliente, Id_empleado;
        if(TxtIDCliente.getText().contentEquals("")||
                TxtIDEmpleado.getText().contentEquals("")||
                TxtIDEquipo.getText().contentEquals("")||
                TxtEstado.getText().contentEquals("")||
                TxtFecha.getText().contentEquals("")||
                TableEntrega.getRowCount()==0){
            JOptionPane.showMessageDialog(rootPane,
                    "Se requieren todos los datos");
        } else{
            try{
                //captura de datos de los Txt en JInternalFrame Diagnostico
                Id_cliente = Integer.parseInt(TxtIDCliente.getText());
                Id_empleado = Integer.parseInt(TxtIDEmpleado.getText());
                Id_equipocomp = Integer.parseInt(TxtIDEquipo.getText());
                Estado = TxtEstado.getText();
                //Objeto que llama a constructor con datos a guardar en diagnostico
                Entrega ent = new Entrega(fech,
                        Estado,Id_cliente,Id_equipocomp,Id_empleado);
                DAOEntrega daoentrega = new DAOEntrega();
                if(daoentrega.Insertar(ent)==0){
                    JOptionPane.showMessageDialog(rootPane,
                            "Registro agregado");
                    //Se obtiene el ultimo numero de factura generado
                    //Se lle asigna a una variable global idDiagnostico
                    ide = daoentrega.ObtenerUltimoIDEntrega();
                } else{
                    JOptionPane.showMessageDialog(rootPane,
                            "Ha ocurrido un error, no se insertó el diagnostico");
                }
            } catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(rootPane,
                        "No se agregaron los registros " +e);
            }
            JOptionPane.showMessageDialog(rootPane,
                    "Registro listo para agregar en diagnostico " + ide);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogEquipoComp = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtDbuscaEquipo = new javax.swing.JTextField();
        BtnDbuscaEquipo = new javax.swing.JButton();
        BtnDAgregarEquipo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableDbuscaEquipo = new javax.swing.JTable();
        jDialogCliente = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TxtDBuscaCliente = new javax.swing.JTextField();
        BtnDBuscaCliente = new javax.swing.JButton();
        BtnDAgregarCliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDBuscaCliente = new javax.swing.JTable();
        jDialogEmpleado = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TxtDEmpleado = new javax.swing.JTextField();
        BtnDBuscaEmpleado = new javax.swing.JButton();
        BtnDAgregarEmpleado = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableDBuscaEmpleado = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtIDEntrega = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtFecha = new javax.swing.JTextField();
        TxtEstado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEntrega = new javax.swing.JTable();
        BtnAgregar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TxtIDEquipo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtEquipo = new javax.swing.JTextField();
        BtnBuscarEquipo = new javax.swing.JButton();
        BtnQuitarEquipo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        TxtIDCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtNombreCliente = new javax.swing.JTextField();
        BtnBuscarCliente = new javax.swing.JButton();
        BtnQuitarCliente = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TxtIDEmpleado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TxtNombreEmpleado = new javax.swing.JTextField();
        BtnBuscarEmpleado = new javax.swing.JButton();
        BtnQuitarEmpleado = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        jDialogEquipoComp.setSize(new java.awt.Dimension(741, 574));

        jPanel8.setBackground(new java.awt.Color(153, 200, 202));

        jLabel2.setText("Buscar Equipo Computarizado");

        BtnDbuscaEquipo.setText("Buscar");
        BtnDbuscaEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDbuscaEquipoActionPerformed(evt);
            }
        });

        BtnDAgregarEquipo.setText("Añadir");
        BtnDAgregarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDAgregarEquipoActionPerformed(evt);
            }
        });

        TableDbuscaEquipo.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Estado de Entrega", "Equipo Computarizado", "Cliente", "Empleado"
            }
        ));
        jScrollPane3.setViewportView(TableDbuscaEquipo);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(TxtDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BtnDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnDAgregarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogEquipoCompLayout = new javax.swing.GroupLayout(jDialogEquipoComp.getContentPane());
        jDialogEquipoComp.getContentPane().setLayout(jDialogEquipoCompLayout);
        jDialogEquipoCompLayout.setHorizontalGroup(
            jDialogEquipoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEquipoCompLayout.setVerticalGroup(
            jDialogEquipoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogCliente.setSize(new java.awt.Dimension(731, 523));

        jPanel7.setBackground(new java.awt.Color(153, 200, 202));

        jLabel4.setText("Buscar Clientes");

        BtnDBuscaCliente.setText("Buscar");
        BtnDBuscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDBuscaClienteActionPerformed(evt);
            }
        });

        BtnDAgregarCliente.setText("Añadir");
        BtnDAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDAgregarClienteActionPerformed(evt);
            }
        });

        TableDBuscaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Direccion", "Tipo de Cliente", "Telefono", "Cedula"
            }
        ));
        jScrollPane2.setViewportView(TableDBuscaCliente);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(TxtDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogClienteLayout = new javax.swing.GroupLayout(jDialogCliente.getContentPane());
        jDialogCliente.getContentPane().setLayout(jDialogClienteLayout);
        jDialogClienteLayout.setHorizontalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogClienteLayout.setVerticalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogEmpleado.setSize(new java.awt.Dimension(750, 500));

        jPanel9.setBackground(new java.awt.Color(153, 200, 202));

        jLabel6.setText("Buscar Empleados");

        BtnDBuscaEmpleado.setText("Buscar");
        BtnDBuscaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDBuscaEmpleadoActionPerformed(evt);
            }
        });

        BtnDAgregarEmpleado.setText("Añadir");
        BtnDAgregarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDAgregarEmpleadoActionPerformed(evt);
            }
        });

        TableDBuscaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Telefono", "Direccion", "Cedula"
            }
        ));
        jScrollPane4.setViewportView(TableDBuscaEmpleado);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(TxtDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDBuscaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDBuscaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogEmpleadoLayout = new javax.swing.GroupLayout(jDialogEmpleado.getContentPane());
        jDialogEmpleado.getContentPane().setLayout(jDialogEmpleadoLayout);
        jDialogEmpleadoLayout.setHorizontalGroup(
            jDialogEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEmpleadoLayout.setVerticalGroup(
            jDialogEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 211, 218));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 45));
        jLabel1.setText("Entrega Equipo");

        TxtIDEntrega.setToolTipText("");

        jLabel3.setText("Entrega Equipo");

        jLabel5.setText("Fecha");

        jLabel7.setText("Estado Entrega");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtIDEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtFecha))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(104, 104, 104)
                                        .addComponent(jLabel5)
                                        .addGap(41, 41, 41)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel7)))
                        .addGap(19, 19, 19))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(85, 85, 85)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TableEntrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Estado de Entrega", "EquipoComp", "Cliente", "Empleado"
            }
        ));
        jScrollPane1.setViewportView(TableEntrega);

        BtnAgregar.setText("Agregar");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setText("Equipo Computarizado");

        jLabel10.setText("Nombre Equipo");

        BtnBuscarEquipo.setBackground(new java.awt.Color(102, 102, 102));
        BtnBuscarEquipo.setText("Buscar");
        BtnBuscarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarEquipoActionPerformed(evt);
            }
        });

        BtnQuitarEquipo.setBackground(new java.awt.Color(102, 102, 102));
        BtnQuitarEquipo.setText("Quitar");
        BtnQuitarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarEquipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(TxtEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(BtnBuscarEquipo)
                        .addGap(18, 18, 18)
                        .addComponent(BtnQuitarEquipo)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setText("Cliente");

        jLabel12.setText("Nombre Cliente");

        BtnBuscarCliente.setBackground(new java.awt.Color(102, 102, 102));
        BtnBuscarCliente.setText("Buscar");
        BtnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarClienteActionPerformed(evt);
            }
        });

        BtnQuitarCliente.setBackground(new java.awt.Color(102, 102, 102));
        BtnQuitarCliente.setText("Quitar");
        BtnQuitarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel11)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnBuscarCliente)
                .addGap(18, 18, 18)
                .addComponent(BtnQuitarCliente)
                .addGap(24, 24, 24))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setText("Empleado");

        TxtIDEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIDEmpleadoActionPerformed(evt);
            }
        });

        jLabel14.setText("Nombre Empleado");

        BtnBuscarEmpleado.setBackground(new java.awt.Color(102, 102, 102));
        BtnBuscarEmpleado.setText("Buscar");
        BtnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarEmpleadoActionPerformed(evt);
            }
        });

        BtnQuitarEmpleado.setBackground(new java.awt.Color(102, 102, 102));
        BtnQuitarEmpleado.setText("Quitar");
        BtnQuitarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel13)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel14))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(TxtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnBuscarEmpleado)
                .addGap(18, 18, 18)
                .addComponent(BtnQuitarEmpleado)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(590, 590, 590))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked

    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked

    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked

    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked

    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked

    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked

    }//GEN-LAST:event_jLabel37MouseClicked

    private void TxtIDEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIDEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIDEmpleadoActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = this.TableEntrega.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.TableEntrega.
                                getValueAt(fila,0).toString());
                        DAOEntrega dao = new DAOEntrega();
                        dao.Eliminar(id);
                        DefaultTableModel temp = (DefaultTableModel)
                            TableEntrega.getModel();
                    temp.removeRow(fila);
                    } catch (SQLException ex){
                        Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).
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


    
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        //.trim() es para suprimir espacios en caso que existan
        //  objetoRecepcionTabla[0] = TxtIDRecepcion.getText().trim();
        String fec = TxtFecha.getText();
        String est= TxtEstado.getText();
        String cl= TxtNombreCliente.getText();
        String eq= TxtEquipo.getText();
        String em= TxtNombreEmpleado.getText();
        if(fec.contentEquals("")|| est.contentEquals("")
                || cl.contentEquals("")
                || eq.contentEquals("") || em.contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Todos los campos son obligatorios");
        } else{
            objetoEntregaTabla[0] = TxtIDEntrega.getText().trim();
            objetoEntregaTabla[1] = TxtFecha.getText().trim();
            objetoEntregaTabla[2] = TxtEstado.getText().trim();
            objetoEntregaTabla[3] = TxtEquipo.getText().trim();
            objetoEntregaTabla[4] = TxtNombreCliente.getText().trim();
            objetoEntregaTabla[5] = TxtNombreEmpleado.getText().trim();
            //Se agregan los datos del objeto al modelo
            ModeloTablaEntrega.addRow(objetoEntregaTabla);
            JOptionPane.showMessageDialog(rootPane,
                    "Registro agregado");
       try{
            guardarEntrega(); 
        } catch(SQLException ex){
            Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).
                    log(Level.SEVERE,null,ex);
        }
        DAOEntrega daoE = new DAOEntrega();
        try {
            ide = daoE.ObtenerUltimoIDEntrega()+1;
        } catch (SQLException ex) {
            Logger.getLogger(MDEntregaEquipJInternalFrame.class.
                    getName()).log(Level.SEVERE, null, ex);
        }
        TxtIDEntrega.setText(Integer.toString(ide));
         limpiarCamposEquipos();
        limpiarCamposEmpleados();
        limpiarCamposClientes();
        limpiarCamposEntrega();
        } 
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnDbuscaEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDbuscaEquipoActionPerformed
        if(TxtDbuscaEquipo.getText().contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Ingrese texto a buscar");
        } else{
            try{
                //Se obtiene el dato de la caja de texto, para realizar busqueda
                String dato = TxtDbuscaEquipo.getText();
                //Llamada al metodo para buscar clientes
                buscarDatosEquipo(dato);
                TxtDbuscaEquipo.setText("");
            } catch(SQLException ex){
                Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).log(Level.
                        SEVERE,null,ex);
            }
        }
    }//GEN-LAST:event_BtnDbuscaEquipoActionPerformed

    private void BtnDAgregarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDAgregarEquipoActionPerformed
        int fila = this.TableDbuscaEquipo.getSelectedRow();
        //Se obtiene #fila seleccionado
        if(fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else{ //Se toma cada campo de la tabla del registro
            //seleccionado y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableDbuscaEquipo.
                        getValueAt(fila, 0).toString());
                String tip = (String) this.TableDbuscaEquipo.getValueAt(fila, 1);
           //     String mar = (String) this.TableDbuscaEquipo.getValueAt(fila, 2);
                //se ubican en las cajas de textos los datos capturados de la tabla
                //Datos ubicados en Datos del cliente del JFrame Venta
                TxtIDEquipo.setText("" + id);
                TxtEquipo.setText(tip);                
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                        "¡Ocurrió un ERROR! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnDAgregarEquipoActionPerformed

    private void BtnDBuscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDBuscaClienteActionPerformed
        if(TxtDBuscaCliente.getText().contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Ingrese texto a buscar");
        } else{
            try{
                //Se obtiene el dato de la caja de texto, para realizar busqueda
                String dato = TxtDBuscaCliente.getText();
                //Llamada al metodo para buscar clientes
                buscarDatosCliente(dato);
                TxtDBuscaCliente.setText("");
            } catch(SQLException ex){
                Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).log(Level.
                        SEVERE,null,ex);
            }
        }
    }//GEN-LAST:event_BtnDBuscaClienteActionPerformed

    private void BtnDAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDAgregarClienteActionPerformed

        int fila = this.TableDBuscaCliente.getSelectedRow();
        //Se obtiene #fila seleccionado
        if(fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else{ //Se toma cada campo de la tabla del registro
            //seleccionado y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableDBuscaCliente.
                        getValueAt(fila, 0).toString());
                String nom = (String) this.TableDBuscaCliente.getValueAt(fila, 1);
                //String ape = (String) this.TableDBuscaCliente.getValueAt(fila, 2);
                //se ubican en las cajas de textos los datos capturados de la tabla
                //Datos ubicados en Datos del cliente
                TxtIDCliente.setText("" + id);
                TxtNombreCliente.setText(nom);                
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                        "¡Ocurrió un ERROR! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnDAgregarClienteActionPerformed

    private void BtnDBuscaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDBuscaEmpleadoActionPerformed
        if(TxtDEmpleado.getText().contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Ingrese texto a buscar");
        } else{
            try{
                //Se obtiene el dato de la caja de texto, para realizar busqueda
                String dato = TxtDEmpleado.getText();
                //Llamada al metodo para buscar clientes
                buscarDatosEmpleado(dato);
                TxtDEmpleado.setText("");
            } catch(SQLException ex){
                Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).log(Level.
                        SEVERE,null,ex);
            }
        }
    }//GEN-LAST:event_BtnDBuscaEmpleadoActionPerformed

    private void BtnDAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDAgregarEmpleadoActionPerformed
        int fila = this.TableDBuscaEmpleado.getSelectedRow();
        //Se obtiene #fila seleccionado
        if(fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else{ //Se toma cada campo de la tabla del registro
            //seleccionado y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableDBuscaEmpleado.
                        getValueAt(fila, 0).toString());
                String nom = (String) this.TableDBuscaEmpleado.getValueAt(fila, 1);
          //      String ape = (String) this.TableDBuscaEmpleado.getValueAt(fila, 2);
                //se ubican en las cajas de textos los datos capturados de la tabla
                //Datos ubicados en Datos del cliente del JFrame Venta
                TxtIDEmpleado.setText("" + id);
                TxtNombreEmpleado.setText(nom);                
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                        "¡Ocurrió un ERROR! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnDAgregarEmpleadoActionPerformed

    private void BtnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarClienteActionPerformed
        jDialogCliente.setVisible(true);
    }//GEN-LAST:event_BtnBuscarClienteActionPerformed

    private void BtnBuscarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarEquipoActionPerformed
        jDialogEquipoComp.setVisible(true);
    }//GEN-LAST:event_BtnBuscarEquipoActionPerformed

    private void BtnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarEmpleadoActionPerformed
        jDialogEmpleado.setVisible(true);
    }//GEN-LAST:event_BtnBuscarEmpleadoActionPerformed

    private void BtnQuitarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEmpleadoActionPerformed
        limpiarCamposEmpleados();
    }//GEN-LAST:event_BtnQuitarEmpleadoActionPerformed

    private void BtnQuitarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEquipoActionPerformed
        limpiarCamposEquipos();
    }//GEN-LAST:event_BtnQuitarEquipoActionPerformed

    private void BtnQuitarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarClienteActionPerformed
        limpiarCamposClientes();
    }//GEN-LAST:event_BtnQuitarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBuscarCliente;
    private javax.swing.JButton BtnBuscarEmpleado;
    private javax.swing.JButton BtnBuscarEquipo;
    private javax.swing.JButton BtnDAgregarCliente;
    private javax.swing.JButton BtnDAgregarEmpleado;
    private javax.swing.JButton BtnDAgregarEquipo;
    private javax.swing.JButton BtnDBuscaCliente;
    private javax.swing.JButton BtnDBuscaEmpleado;
    private javax.swing.JButton BtnDbuscaEquipo;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnQuitarCliente;
    private javax.swing.JButton BtnQuitarEmpleado;
    private javax.swing.JButton BtnQuitarEquipo;
    private javax.swing.JTable TableDBuscaCliente;
    private javax.swing.JTable TableDBuscaEmpleado;
    private javax.swing.JTable TableDbuscaEquipo;
    private javax.swing.JTable TableEntrega;
    private javax.swing.JTextField TxtDBuscaCliente;
    private javax.swing.JTextField TxtDEmpleado;
    private javax.swing.JTextField TxtDbuscaEquipo;
    private javax.swing.JTextField TxtEquipo;
    private javax.swing.JTextField TxtEstado;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtIDCliente;
    private javax.swing.JTextField TxtIDEmpleado;
    private javax.swing.JTextField TxtIDEntrega;
    private javax.swing.JTextField TxtIDEquipo;
    private javax.swing.JTextField TxtNombreCliente;
    private javax.swing.JTextField TxtNombreEmpleado;
    private javax.swing.JDialog jDialogCliente;
    private javax.swing.JDialog jDialogEmpleado;
    private javax.swing.JDialog jDialogEquipoComp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    // End of variables declaration//GEN-END:variables
}
