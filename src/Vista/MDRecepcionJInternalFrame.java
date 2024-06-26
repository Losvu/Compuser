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

public class MDRecepcionJInternalFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel ModeloTablaRecepcion;
    private Object[] objetoRecepcionTabla = new Object[6];
    private Double total = 0.0;
    private int item = 0;
    private java.sql.Date fech;
    private int numrec = 0;
    private int cantidad = 0;
    private int idr = 0;
    private java.sql.Date fecha;
    
    public MDRecepcionJInternalFrame() throws SQLException {
        initComponents();
        TxtIDCliente.setText("0");
        TxtIDEquipo.setText("0");
        TxtIDEmpleado.setText("0");
        jDialogCliente.setLocationRelativeTo(null);
        jDialogEquipoComp.setLocationRelativeTo(null);
        jDialogEmpleados.setLocationRelativeTo(null);
        TxtIDRecepcion.setEnabled(false);
        TxtIDCliente.setEnabled(false);
        TxtNombreCliente.setEnabled(false);
        TxtIDEquipo.setEnabled(false);
        TxtEquipo.setEnabled(false);
        TxtIDEmpleado.setEnabled(false);
        TxtNombreEmpleado.setEnabled(false);
        //Obtiene la fecha actual
        fech = java.sql.Date.valueOf(LocalDate.now());     
        TxtFecha.setText(fech.toString());
        
        DAORecepcion daoR = new DAORecepcion();
        try {
            idr = daoR.ObtenerUltimoIDRecepcion()+1;
        } catch (SQLException ex) {
            Logger.getLogger(MDEntregaEquipJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TxtIDRecepcion.setText(Integer.toString(idr));
        
        ModeloTablaRecepcion=(DefaultTableModel) TableRecepcion.getModel();
                
    }
    
    public void limpiarCamposRecepcion(){
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
    
    public void guardarRecepcion() throws SQLException{
        //Datos a guardar en diagnostico: Descripcion, idequipo, idcliente, idempleado
        String Estado;
        int Id_equipocomp, Id_cliente, Id_empleado;
        if(TxtIDCliente.getText().contentEquals("")||
                TxtIDEmpleado.getText().contentEquals("")||
                TxtIDEquipo.getText().contentEquals("")||
                TxtEstado.getText().contentEquals("")||
                TxtFecha.getText().contentEquals("")||
                TableRecepcion.getRowCount()==0){
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
                Recepcion rec = new Recepcion(fech,
                        Estado,Id_cliente,Id_equipocomp,Id_empleado);
                DAORecepcion daorecepcion = new DAORecepcion();
                if(daorecepcion.Insertar(rec)==0){
                    JOptionPane.showMessageDialog(rootPane,
                            "Registro agregado");
                    //Se obtiene el ultimo numero de factura generado
                    //Se lle asigna a una variable global idDiagnostico
                    idr = daorecepcion.ObtenerUltimoIDRecepcion();
                } else{
                    JOptionPane.showMessageDialog(rootPane,
                            "Ha ocurrido un error, no se insertó el diagnostico");
                }
            } catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(rootPane,
                        "No se agregaron los registros " +e);
            }
            JOptionPane.showMessageDialog(rootPane,
                    "Registro listo para agregar en diagnostico " + idr);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogCliente = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TxtDBuscaCliente = new javax.swing.JTextField();
        BtnDBuscaCliente = new javax.swing.JButton();
        BtnDAgregarCliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDBuscaCliente = new javax.swing.JTable();
        jDialogEquipoComp = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtDbuscaEquipo = new javax.swing.JTextField();
        BtnDbuscaEquipo = new javax.swing.JButton();
        BtnDAgregarEquipo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableDbuscaEquipo = new javax.swing.JTable();
        jDialogEmpleados = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TxtDEmpleado = new javax.swing.JTextField();
        BtnDBuscaEmpleado = new javax.swing.JButton();
        BtnDAgregarEmpleado = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableDBuscaEmpleado = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        TxtIDRecepcion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        TxtFecha = new javax.swing.JTextField();
        TxtEstado = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRecepcion = new javax.swing.JTable();
        BtnAgregar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        TxtIDEquipo = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        BtnBuscarEquipo = new javax.swing.JButton();
        BtnQuitarEquipo = new javax.swing.JButton();
        TxtEquipo = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        TxtIDEmpleado = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        BtnBuscarEmpleado = new javax.swing.JButton();
        BtnQuitarEmpleado = new javax.swing.JButton();
        TxtNombreEmpleado = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        TxtIDCliente = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        BtnBuscarCliente = new javax.swing.JButton();
        BtnQuitarCliente = new javax.swing.JButton();
        TxtNombreCliente = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        jDialogCliente.setSize(new java.awt.Dimension(730, 500));

        jPanel13.setBackground(new java.awt.Color(153, 200, 202));

        jLabel6.setText("Buscar Clientes");

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

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Direccion", "Tipo de Cliente", "Telefono", "Cedula"
            }
        ));
        jScrollPane2.setViewportView(TableDBuscaCliente);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addComponent(TxtDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BtnDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnDAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogClienteLayout = new javax.swing.GroupLayout(jDialogCliente.getContentPane());
        jDialogCliente.getContentPane().setLayout(jDialogClienteLayout);
        jDialogClienteLayout.setHorizontalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogClienteLayout.setVerticalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogEquipoComp.setSize(new java.awt.Dimension(750, 500));

        jPanel16.setBackground(new java.awt.Color(153, 200, 202));

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

            },
            new String [] {
                "ID", "Fecha", "Estado de Entrega", "Equipo Computarizado", "Cliente", "Empleado"
            }
        ));
        jScrollPane3.setViewportView(TableDbuscaEquipo);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(TxtDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BtnDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnDAgregarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEquipoCompLayout.setVerticalGroup(
            jDialogEquipoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogEmpleados.setSize(new java.awt.Dimension(750, 500));

        jPanel17.setBackground(new java.awt.Color(153, 200, 202));

        jLabel15.setText("Buscar Empleados");

        TxtDEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDEmpleadoActionPerformed(evt);
            }
        });

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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Telefono", "Direccion", "Cedula"
            }
        ));
        jScrollPane4.setViewportView(TableDBuscaEmpleado);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(TxtDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDBuscaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDBuscaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogEmpleadosLayout = new javax.swing.GroupLayout(jDialogEmpleados.getContentPane());
        jDialogEmpleados.getContentPane().setLayout(jDialogEmpleadosLayout);
        jDialogEmpleadosLayout.setHorizontalGroup(
            jDialogEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEmpleadosLayout.setVerticalGroup(
            jDialogEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel8.setBackground(new java.awt.Color(204, 211, 218));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 45));
        jLabel20.setText("Recepcion");

        jLabel21.setText("ID Recepcion");

        jLabel22.setText("Fecha");

        jLabel23.setText("Estado Recepcion");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(TxtIDRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(104, 104, 104)
                                        .addComponent(jLabel22)
                                        .addGap(41, 41, 41)))))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addGap(63, 63, 63))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(85, 85, 85)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TableRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Estado de Recepción", "Cliente", "Equipo", "Empleado"
            }
        ));
        jScrollPane1.setViewportView(TableRecepcion);

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

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jLabel25.setText("Equipo Computarizado");

        jLabel26.setText("Nombre Equipo");

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

        TxtEquipo.setText("Nombre");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel26))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(TxtEquipo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtEquipo)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(BtnBuscarEquipo)
                        .addGap(18, 18, 18)
                        .addComponent(BtnQuitarEquipo)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));

        jLabel29.setText("Empleado");

        TxtIDEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIDEmpleadoActionPerformed(evt);
            }
        });

        jLabel30.setText("Nombre Empleado");

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

        TxtNombreEmpleado.setText("Nombre");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(TxtNombreEmpleado))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel29)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNombreEmpleado)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(BtnBuscarEmpleado)
                        .addGap(18, 18, 18)
                        .addComponent(BtnQuitarEmpleado)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        jLabel31.setText("Cliente");

        TxtIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIDClienteActionPerformed(evt);
            }
        });

        jLabel39.setText("Nombre Cliente");

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

        TxtNombreCliente.setText("Nombre");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel31))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(118, 118, 118)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnQuitarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(TxtNombreCliente)))
                .addGap(55, 55, 55))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNombreCliente)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(BtnBuscarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(BtnQuitarCliente)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(584, 584, 584))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        //.trim() es para suprimir espacios en caso que existan
        //  objetoRecepcionTabla[0] = TxtIDRecepcion.getText().trim();
        String fec = TxtFecha.getText();
        String est= TxtEstado.getText();
        String cl= TxtIDCliente.getText();
        String eq= TxtIDEquipo.getText();
        String em= TxtIDEmpleado.getText();
        if(fec.contentEquals("")|| est.contentEquals("")
                || cl.contentEquals("")
                || eq.contentEquals("") || em.contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Todos los campos son obligatorios");
        } else{
        objetoRecepcionTabla[0] = TxtIDRecepcion.getText().trim();
        objetoRecepcionTabla[1] = TxtFecha.getText().trim();
        objetoRecepcionTabla[2] = TxtEstado.getText().trim();
        objetoRecepcionTabla[3] = TxtNombreCliente.getText().trim();
        objetoRecepcionTabla[4] = TxtEquipo.getText().trim();
        objetoRecepcionTabla[5] = TxtNombreEmpleado.getText().trim();
        //Se agregan los datos del objeto al modelo
        ModeloTablaRecepcion.addRow(objetoRecepcionTabla);
        JOptionPane.showMessageDialog(rootPane,
                    "Registro agregado");
        try{
            guardarRecepcion();
        } catch(SQLException ex){
            Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).
                    log(Level.SEVERE,null,ex);
        }
        DAORecepcion daoR = new DAORecepcion();
        try {
            idr = daoR.ObtenerUltimoIDRecepcion()+1;
        } catch (SQLException ex) {
            Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TxtIDRecepcion.setText(Integer.toString(idr));
       limpiarCamposEquipos();
       limpiarCamposEmpleados();
       limpiarCamposClientes();
       limpiarCamposRecepcion();
        }  
    }//GEN-LAST:event_BtnAgregarActionPerformed

    
    
    
    public void actualizar() throws SQLException {
        int id= Integer.parseInt(this.TxtIDRecepcion.getText());
        java.sql.Date fec = java.sql.Date.valueOf(this.TxtFecha.getText());
        String est = this.TxtEstado.getText();
        int cl = Integer.parseInt(this.TxtIDCliente.getText());
        int eq = Integer.parseInt(this.TxtIDEquipo.getText());
        int em = Integer.parseInt(this.TxtIDEmpleado.getText());
        
        Recepcion recepcion = new Recepcion(id, fec, est,
                cl,eq, em);
        
        DAORecepcion dao = new DAORecepcion();
        int res = dao.Actualizar(recepcion);
        if(res == 0){
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Recepción Actualizada!");
        } else{
            JOptionPane.showMessageDialog(rootPane, 
                    "¡Ocurrió un ERROR!");
        }
    }
    
    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = this.TableRecepcion.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.TableRecepcion.
                                getValueAt(fila,0).toString());
                        DAORecepcion dao = new DAORecepcion();
                        dao.Eliminar(id);
                        DefaultTableModel temp = (DefaultTableModel)
                            TableRecepcion.getModel();
                    temp.removeRow(fila);
                    } catch (SQLException ex){
                        Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).
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

    private void TxtIDEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIDEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIDEmpleadoActionPerformed

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
                Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).log(Level.
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
                Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).log(Level.
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
                Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).log(Level.
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
        jDialogEmpleados.setVisible(true);
    }//GEN-LAST:event_BtnBuscarEmpleadoActionPerformed

    private void TxtIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIDClienteActionPerformed
        
    }//GEN-LAST:event_TxtIDClienteActionPerformed

    private void TxtDEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDEmpleadoActionPerformed

    private void BtnQuitarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarClienteActionPerformed
        limpiarCamposClientes();
    }//GEN-LAST:event_BtnQuitarClienteActionPerformed

    private void BtnQuitarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEquipoActionPerformed
        limpiarCamposEquipos();
    }//GEN-LAST:event_BtnQuitarEquipoActionPerformed

    private void BtnQuitarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEmpleadoActionPerformed
        limpiarCamposEmpleados();
    }//GEN-LAST:event_BtnQuitarEmpleadoActionPerformed


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
    private javax.swing.JTable TableRecepcion;
    private javax.swing.JTextField TxtDBuscaCliente;
    private javax.swing.JTextField TxtDEmpleado;
    private javax.swing.JTextField TxtDbuscaEquipo;
    private javax.swing.JLabel TxtEquipo;
    private javax.swing.JTextField TxtEstado;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtIDCliente;
    private javax.swing.JTextField TxtIDEmpleado;
    private javax.swing.JTextField TxtIDEquipo;
    private javax.swing.JTextField TxtIDRecepcion;
    private javax.swing.JLabel TxtNombreCliente;
    private javax.swing.JLabel TxtNombreEmpleado;
    private javax.swing.JDialog jDialogCliente;
    private javax.swing.JDialog jDialogEmpleados;
    private javax.swing.JDialog jDialogEquipoComp;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
