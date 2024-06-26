package Vista;
import Modelo.*;
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
import net.sf.jasperreports.engine.JRException;

public class MDDiagnosticoJInternalFrame extends javax.swing.JInternalFrame {
    //Declaraciones globales
    private DefaultTableModel modeloTablaDiag;
    private Object[] objetoDiagTabla = new Object[8];
    private Double total = 0.0;
    private int item = 0;
    private java.sql.Date fech;
    private int IDDiagnostico = 0;
    private int idser;
    
    public MDDiagnosticoJInternalFrame() {
        initComponents();
        TxtIDCliente.setText("0");
        TxtIDEmpleado.setText("0");
        TxtIDEquipo.setText("0");
        TxtIDServicio.setText("0");
        TxtIDCliente.setEnabled(false);
        TxtIDEmpleado.setEnabled(false);
        TxtIDEquipo.setEnabled(false);
        TxtIDServicio.setEnabled(false);
        TxtNombreServicio.setEnabled(false);
        TxtNombreCliente.setEnabled(false);
        TxtNombreEmpleado.setEnabled(false);
        TxtEquipo.setEnabled(false);
        jDialogCliente.setLocationRelativeTo(null);
        jDialogEmpleado.setLocationRelativeTo(null);
        jDialogEquipoComp.setLocationRelativeTo(null);
        jDialogServicio.setLocationRelativeTo(null);
        fech = java.sql.Date.valueOf(LocalDate.now());
        TxtFecha.setText(fech.toString());
        modeloTablaDiag = (DefaultTableModel) TableDiagnostico.getModel();
        DAODiagnostico daod = new DAODiagnostico();
        try {
            IDDiagnostico = daod.obtenerUltimoIDDiagnostico()+1;
        } catch (SQLException ex) {
            Logger.getLogger(MDDiagnosticoJInternalFrame.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        TxtIDDiagnostico.setText(Integer.toString(IDDiagnostico));
    }
    
    public void limpiarCamposDiagnostico(){
        TxtCosto.setText("");
        TxtDescripcion.setText("");
    }
    
    public void limpiarCamposServicios(){
        TxtIDServicio.setText("0");
        TxtNombreServicio.setText("");
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
    
    private void buscarDatosServicios (String dato) throws SQLException{
        List<Servicios> servicio = new DAOServicios().buscarDialogoServicios(dato);
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"ID", "Descripción", "Costo"};
        modelo.setColumnIdentifiers(columnas);
        for(Servicios ser : servicio){
            String[] renglon = {Integer.toString(ser.getId_ser()),
                ser.getDescripcion(), Float.toString(ser.getCosto())};
            modelo.addRow(renglon);
        }
        TableDBuscaServicio.setModel(modelo);
    }
    
    public void guardarDiagnostico() throws SQLException{
        //Datos a guardar en diagnostico: Descripcion, idequipo, idcliente, idempleado
        String Descripción;
        int Id_equipocomp, Id_cliente, Id_empleado;
        if(TxtIDCliente.getText().contentEquals("")||
                TxtIDEmpleado.getText().contentEquals("")||
                TxtIDEquipo.getText().contentEquals("")||
                TxtDescripcion.getText().contentEquals("")||
                TxtIDEquipo.getText().contentEquals("")||
                TableDiagnostico.getRowCount()==0){
            JOptionPane.showMessageDialog(rootPane,
                    "Se requieren todos los datos");
        } else{
            try{
                //captura de datos de los Txt en JInternalFrame Diagnostico
                Id_cliente = Integer.parseInt(TxtIDCliente.getText());
                Id_empleado = Integer.parseInt(TxtIDEmpleado.getText());
                Id_equipocomp = Integer.parseInt(TxtIDEquipo.getText());
                Descripción = TxtDescripcion.getText();
                //Objeto que llama a constructor con datos a guardar en diagnostico
                Diagnostico diag = new Diagnostico(Descripción,Id_equipocomp,Id_cliente,Id_empleado);
                DAODiagnostico daodiagnostico = new DAODiagnostico();
                if(daodiagnostico.insertarDiagnostico(diag)==0){
                    JOptionPane.showMessageDialog(rootPane,
                            "Registro agregado");
                    //Se obtiene el ultimo numero de factura generado
                    //Se lle asigna a una variable global idDiagnostico
                    IDDiagnostico = daodiagnostico.obtenerUltimoIDDiagnostico();
                } else{
                    JOptionPane.showMessageDialog(rootPane,
                            "Ha ocurrido un error, no se insertó el diagnostico");
                }
            } catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(rootPane,
                        "No se agregaron los registros " +e);
            }
            JOptionPane.showMessageDialog(rootPane,
                    "Registro listo para agregar en diagnostico " + IDDiagnostico);
            guardarDetalleDiagnostico();
        }
    }
    
    public void guardarDetalleDiagnostico() throws SQLException{
        //Datos de jtable diagnostico a guardar en detalle
        //IDDiagnostico, IDSer estan declarados de forma global
        float costo;
        //Se evalua que jtable no este vacia y que se haya guardado
        if(IDDiagnostico == 0 || TableDiagnostico.getRowCount() == 0){
            JOptionPane.showMessageDialog(rootPane,
                    "No se ha obtenido ID del Diagnostico o no"
                            + " tiene datos añadidos a la tabla");
        } else{
            //se recorre jtable para capturar los datos a guardar en detalle de la BD
            for(int i =0; i < TableDiagnostico.getRowCount(); i++){
            IDDiagnostico = Integer.parseInt(TableDiagnostico.
                    getValueAt(i,1).toString());
            idser = Integer.parseInt(TxtIDServicio.getText());
            costo = Integer.parseInt(TableDiagnostico.
                    getValueAt(i,6).toString());
            //Objeto de diagnostico que invoca al constructor con los parametros
            //a guardar en detalle
            Detalle_Diagnostico detdiag = new Detalle_Diagnostico(costo,
            IDDiagnostico,idser);
            //Objeto de DAODetalle que invoca al metodo InsertarDetalle
            DAODetalle_Diagnostico daodetdiag = new DAODetalle_Diagnostico();
            if(daodetdiag.insertarDetalleDiagnostico(detdiag)==0){
                JOptionPane.showMessageDialog(rootPane,
                        "Registro agregado en Detalle Diagnostico");
            } else{
                JOptionPane.showMessageDialog(rootPane,
                        "Ha ocurrido un error, no se insertó el detalle");
            }
        }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogCliente = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        TxtDBuscaCliente = new javax.swing.JTextField();
        BtnDBuscaCliente = new javax.swing.JButton();
        BtnDAgregarCliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDBuscaCliente = new javax.swing.JTable();
        jDialogEmpleado = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        TxtDEmpleado = new javax.swing.JTextField();
        BtnDBuscaEmpleado = new javax.swing.JButton();
        BtnDAgregarEmpleado = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableDBuscaEmpleado = new javax.swing.JTable();
        jDialogEquipoComp = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        TxtDbuscaEquipo = new javax.swing.JTextField();
        BtnDbuscaEquipo = new javax.swing.JButton();
        BtnDAgregarEquipo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableDbuscaEquipo = new javax.swing.JTable();
        jDialogServicio = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TxtDbuscaServicio = new javax.swing.JTextField();
        BtnDbuscaServicio = new javax.swing.JButton();
        BtnDAgregarServicio = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableDBuscaServicio = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDiagnostico = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtNombreCliente = new javax.swing.JTextField();
        TxtIDCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtIDEmpleado = new javax.swing.JTextField();
        TxtNombreEmpleado = new javax.swing.JTextField();
        TxtIDEquipo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtDescripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtEquipo = new javax.swing.JTextField();
        TxtFecha = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BtnBuscarEmpleado = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        TxtCosto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TxtIDServicio = new javax.swing.JTextField();
        BtnQuitarEmpleado = new javax.swing.JButton();
        BtnBuscarEquipo = new javax.swing.JButton();
        BtnQuitarEquipo = new javax.swing.JButton();
        BtnBuscarCliente = new javax.swing.JButton();
        BtnQuitarCliente = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        TxtNombreServicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BtnBuscarServicio = new javax.swing.JButton();
        BtnQuitarServicio = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        TxtIDDiagnostico = new javax.swing.JLabel();
        TxtQuitarD = new javax.swing.JButton();
        BtnGuardarFactura = new javax.swing.JButton();
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

        jPanel7.setBackground(new java.awt.Color(153, 200, 202));

        jLabel11.setText("Buscar Clientes");

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
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(TxtDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jDialogEmpleado.setSize(new java.awt.Dimension(730, 500));

        jPanel10.setBackground(new java.awt.Color(153, 200, 202));

        jLabel17.setText("Buscar Empleados");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(TxtDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDBuscaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEmpleadoLayout.setVerticalGroup(
            jDialogEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogEquipoComp.setSize(new java.awt.Dimension(730, 500));

        jPanel17.setBackground(new java.awt.Color(153, 200, 202));

        jLabel18.setText("Buscar Equipo");

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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Marca", "Color", "Modelo", "Cliente"
            }
        ));
        jScrollPane5.setViewportView(TableDbuscaEquipo);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(TxtDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnDAgregarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDbuscaEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogEquipoCompLayout = new javax.swing.GroupLayout(jDialogEquipoComp.getContentPane());
        jDialogEquipoComp.getContentPane().setLayout(jDialogEquipoCompLayout);
        jDialogEquipoCompLayout.setHorizontalGroup(
            jDialogEquipoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogEquipoCompLayout.setVerticalGroup(
            jDialogEquipoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogServicio.setSize(new java.awt.Dimension(730, 500));

        jPanel16.setBackground(new java.awt.Color(153, 200, 202));

        jLabel15.setText("Buscar Servicios");

        BtnDbuscaServicio.setText("Buscar");
        BtnDbuscaServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDbuscaServicioActionPerformed(evt);
            }
        });

        BtnDAgregarServicio.setText("Añadir");
        BtnDAgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDAgregarServicioActionPerformed(evt);
            }
        });

        TableDBuscaServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción", "Costo"
            }
        ));
        jScrollPane3.setViewportView(TableDBuscaServicio);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(TxtDbuscaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BtnDbuscaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnDAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(302, 302, 302))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDbuscaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDbuscaServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDAgregarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogServicioLayout = new javax.swing.GroupLayout(jDialogServicio.getContentPane());
        jDialogServicio.getContentPane().setLayout(jDialogServicioLayout);
        jDialogServicioLayout.setHorizontalGroup(
            jDialogServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogServicioLayout.setVerticalGroup(
            jDialogServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 211, 218));

        TableDiagnostico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Items", "Diagnóstico", "Cliente", "Equipo", "Descripción", "Servicio", "Costo", "Empleado"
            }
        ));
        jScrollPane1.setViewportView(TableDiagnostico);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 45));
        jLabel1.setText("Diagnostico N°");
        jLabel1.setToolTipText("");

        TxtIDCliente.setText("ID_Cliente");

        jLabel3.setText("Cliente");

        jLabel5.setText("Empleado");

        TxtIDEmpleado.setText("ID_Empleado");

        TxtIDEquipo.setText("ID_EquipoComp");

        jLabel7.setText("Equipo");

        jLabel9.setText("Descripción");

        jLabel10.setText("Nombre equipo");

        TxtFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TxtFecha.setText("Fecha");

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jLabel4.setText("Nombre Cliente");

        jLabel6.setText("Nombre Empleado");

        BtnBuscarEmpleado.setText("Buscar");
        BtnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarEmpleadoActionPerformed(evt);
            }
        });

        jLabel13.setText("Costo");

        jLabel16.setText("ID Servicio");

        BtnQuitarEmpleado.setText("Quitar");
        BtnQuitarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarEmpleadoActionPerformed(evt);
            }
        });

        BtnBuscarEquipo.setText("Buscar");
        BtnBuscarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarEquipoActionPerformed(evt);
            }
        });

        BtnQuitarEquipo.setText("Quitar");
        BtnQuitarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarEquipoActionPerformed(evt);
            }
        });

        BtnBuscarCliente.setText("Buscar");
        BtnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarClienteActionPerformed(evt);
            }
        });

        BtnQuitarCliente.setText("Quitar");
        BtnQuitarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarClienteActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setText("Nombre Servicio");

        BtnBuscarServicio.setText("Buscar");
        BtnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarServicioActionPerformed(evt);
            }
        });

        BtnQuitarServicio.setText("Quitar");
        BtnQuitarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarServicioActionPerformed(evt);
            }
        });

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

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        TxtIDDiagnostico.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 16)); // NOI18N
        TxtIDDiagnostico.setText("Diagnostico");

        TxtQuitarD.setText("Quitar");
        TxtQuitarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtQuitarDActionPerformed(evt);
            }
        });

        BtnGuardarFactura.setText("Guardar Factura");
        BtnGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBuscarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnQuitarCliente)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3)
                        .addGap(239, 239, 239)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtFecha)
                        .addGap(163, 163, 163))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(jLabel5)
                                        .addGap(216, 216, 216)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel7)
                                        .addGap(238, 238, 238)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(TxtEquipo))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(TxtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(BtnBuscarEmpleado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnQuitarEmpleado))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(BtnBuscarEquipo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(BtnQuitarEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TxtQuitarD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtDescripcion)
                                .addGap(18, 18, 18)
                                .addComponent(TxtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(167, 167, 167)))
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(90, 90, 90))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(TxtIDServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(BtnBuscarServicio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(BtnQuitarServicio))
                                            .addComponent(TxtNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(BtnGuardarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtIDDiagnostico)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtIDDiagnostico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(TxtFecha))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnQuitarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BtnBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TxtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TxtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(BtnQuitarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(BtnQuitarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TxtIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TxtEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(BtnBuscarEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(TxtIDServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(BtnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BtnQuitarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TxtQuitarD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(BtnGuardarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(11, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
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
              //  String mar = (String) this.TableDbuscaEquipo.getValueAt(fila, 2);
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

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        String cli = TxtNombreCliente.getText();
        String equi = TxtEquipo.getText();
        String Emp = TxtNombreEmpleado.getText();
        String des = TxtDescripcion.getText();
        String ser = TxtNombreServicio.getText();
        String cos = TxtCosto.getText();
        if(cos.contentEquals("") || cli.contentEquals("")|| 
                equi.contentEquals("") || Emp.contentEquals("")||
                des.contentEquals("")|| ser.contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                    "Escriba todos los datos");
        } else{
        item +=1;
        objetoDiagTabla[0] = item;
        objetoDiagTabla[1] = TxtIDDiagnostico.getText().trim();
        objetoDiagTabla[2] = TxtNombreCliente.getText().trim();
        objetoDiagTabla[3] = TxtEquipo.getText().trim();
        objetoDiagTabla[4] = TxtDescripcion.getText().trim();
        objetoDiagTabla[5] = TxtNombreServicio.getText().trim();
        objetoDiagTabla[6] = TxtCosto.getText().trim();
        objetoDiagTabla[7] = TxtNombreEmpleado.getText().trim();
        
       //total += TxtCosto;
        //LblTotal.setText(total.toString());
        
        modeloTablaDiag.addRow(objetoDiagTabla);
        JOptionPane.showMessageDialog(rootPane,
                    "Registro agregado");
        }
        
         //Guardar la venta | Obtiene el ultimo id de venta guardado
        //Para agregar a cada detalle
       // try{
         //   guardarDiagnostico(); //Llama al metodo que gaurda en venta (Factura)
        //} catch(SQLException ex){
          //  Logger.getLogger(MDDiagnosticoJInternalFrame.class.getName()).
        //            log(Level.SEVERE,null,ex);
       // }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarEmpleadoActionPerformed
        jDialogEmpleado.setVisible(true);
    }//GEN-LAST:event_BtnBuscarEmpleadoActionPerformed

    private void BtnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarClienteActionPerformed
        jDialogCliente.setVisible(true);
    }//GEN-LAST:event_BtnBuscarClienteActionPerformed

    private void BtnBuscarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarEquipoActionPerformed
        jDialogEquipoComp.setVisible(true);
    }//GEN-LAST:event_BtnBuscarEquipoActionPerformed

    private void BtnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarServicioActionPerformed
        jDialogServicio.setVisible(true);
    }//GEN-LAST:event_BtnBuscarServicioActionPerformed

    private void BtnDbuscaServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDbuscaServicioActionPerformed
        if(TxtDbuscaServicio.getText().contentEquals("")){
            JOptionPane.showMessageDialog(rootPane,
                "Ingrese texto a buscar");
        } else{
            try{
                //Se obtiene el dato de la caja de texto, para realizar busqueda
                String dato = TxtDbuscaServicio.getText();
                //Llamada al metodo para buscar clientes
                buscarDatosServicios(dato);
                TxtDbuscaServicio.setText("");
            } catch(SQLException ex){
                Logger.getLogger(MDRecepcionJInternalFrame.class.getName()).log(Level.
                    SEVERE,null,ex);
            }
        }
    }//GEN-LAST:event_BtnDbuscaServicioActionPerformed

    private void BtnDAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDAgregarServicioActionPerformed
        int fila = this.TableDBuscaServicio.getSelectedRow();
        //Se obtiene #fila seleccionado
        if(fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                "Seleccione un registro de la tabla");
        } else{ //Se toma cada campo de la tabla del registro
            //seleccionado y se asigna a una variable
            try{
                int id = Integer.parseInt((String) this.TableDBuscaServicio.
                    getValueAt(fila, 0).toString());
                String serv = (String) this.TableDBuscaServicio.getValueAt(fila, 1);
                //se ubican en las cajas de textos los datos capturados de la tabla
                //Datos ubicados en Datos del cliente del JFrame
                TxtIDServicio.setText("" + id);
                TxtNombreServicio.setText(serv);
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,
                    "¡Ocurrió un ERROR! " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnDAgregarServicioActionPerformed

    private void BtnQuitarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarClienteActionPerformed
        limpiarCamposClientes();
    }//GEN-LAST:event_BtnQuitarClienteActionPerformed

    private void BtnQuitarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEmpleadoActionPerformed
        limpiarCamposEmpleados();
    }//GEN-LAST:event_BtnQuitarEmpleadoActionPerformed

    private void BtnQuitarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarEquipoActionPerformed
        limpiarCamposEquipos();
    }//GEN-LAST:event_BtnQuitarEquipoActionPerformed

    private void BtnQuitarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarServicioActionPerformed
        limpiarCamposServicios();
    }//GEN-LAST:event_BtnQuitarServicioActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        //Guardar la venta | Obtiene el ultimo id de venta guardado
        //Para agregar a cada detalle
        try{
            guardarDiagnostico(); //Llama al metodo que gaurda en venta (Factura)
        } catch(SQLException ex){
            Logger.getLogger(MDDiagnosticoJInternalFrame.class.getName()).
                    log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void TxtQuitarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQuitarDActionPerformed
        limpiarCamposDiagnostico();
    }//GEN-LAST:event_TxtQuitarDActionPerformed

    private void BtnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarFacturaActionPerformed
        
    }//GEN-LAST:event_BtnGuardarFacturaActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = this.TableDiagnostico.getSelectedRow(); //Se obtiene #fila seleccionado
        if(fila == -1) {
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione una fila a quitar de la tabla");
        } else{
            //JOptionPane.OK_CANCEL_OPTION();
            JDialog.setDefaultLookAndFeelDecorated(true);
            int resp = JOptionPane.showConfirmDialog(null,
                    "¿Esta seguro de quitar el diagnostico de la lista?", "Aceptar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(resp == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(rootPane,
                        "No se ha retirado el diagnostico");
            }else{
                if(resp == JOptionPane.YES_OPTION){
                    DefaultTableModel temp = (DefaultTableModel)
                            TableDiagnostico.getModel();
                    temp.removeRow(fila);
                }
            }
            if(resp == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(rootPane,
                        "Ninguna acción realizada");
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBuscarCliente;
    private javax.swing.JButton BtnBuscarEmpleado;
    private javax.swing.JButton BtnBuscarEquipo;
    private javax.swing.JButton BtnBuscarServicio;
    private javax.swing.JButton BtnDAgregarCliente;
    private javax.swing.JButton BtnDAgregarEmpleado;
    private javax.swing.JButton BtnDAgregarEquipo;
    private javax.swing.JButton BtnDAgregarServicio;
    private javax.swing.JButton BtnDBuscaCliente;
    private javax.swing.JButton BtnDBuscaEmpleado;
    private javax.swing.JButton BtnDbuscaEquipo;
    private javax.swing.JButton BtnDbuscaServicio;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnGuardarFactura;
    private javax.swing.JButton BtnQuitarCliente;
    private javax.swing.JButton BtnQuitarEmpleado;
    private javax.swing.JButton BtnQuitarEquipo;
    private javax.swing.JButton BtnQuitarServicio;
    private javax.swing.JTable TableDBuscaCliente;
    private javax.swing.JTable TableDBuscaEmpleado;
    private javax.swing.JTable TableDBuscaServicio;
    private javax.swing.JTable TableDbuscaEquipo;
    private javax.swing.JTable TableDiagnostico;
    private javax.swing.JTextField TxtCosto;
    private javax.swing.JTextField TxtDBuscaCliente;
    private javax.swing.JTextField TxtDEmpleado;
    private javax.swing.JTextField TxtDbuscaEquipo;
    private javax.swing.JTextField TxtDbuscaServicio;
    private javax.swing.JTextField TxtDescripcion;
    private javax.swing.JTextField TxtEquipo;
    private javax.swing.JLabel TxtFecha;
    private javax.swing.JTextField TxtIDCliente;
    private javax.swing.JLabel TxtIDDiagnostico;
    private javax.swing.JTextField TxtIDEmpleado;
    private javax.swing.JTextField TxtIDEquipo;
    private javax.swing.JTextField TxtIDServicio;
    private javax.swing.JTextField TxtNombreCliente;
    private javax.swing.JTextField TxtNombreEmpleado;
    private javax.swing.JTextField TxtNombreServicio;
    private javax.swing.JButton TxtQuitarD;
    private javax.swing.JDialog jDialogCliente;
    private javax.swing.JDialog jDialogEmpleado;
    private javax.swing.JDialog jDialogEquipoComp;
    private javax.swing.JDialog jDialogServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
