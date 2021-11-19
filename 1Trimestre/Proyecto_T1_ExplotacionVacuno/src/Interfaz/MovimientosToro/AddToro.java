package Interfaz.MovimientosToro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import mySQL_XML.DbConnection;

public class AddToro extends javax.swing.JFrame{

    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;

    // Variables Interfaz                     
    private javax.swing.JButton btnAñadir;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbRaza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner spinnerCrotal;
    private javax.swing.JSpinner spinnerEntrada;
    private javax.swing.JSpinner spinnerNacimiento;
    private javax.swing.JTextField txtCrotal;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtRaza;
    // ************************ 

    public AddToro(java.awt.Frame parent, boolean modal) throws SQLException {
        initComponents();
        actualizarTipoRazaTipoEstado();
    }

    public AddToro(Object object, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void actualizarTipoRazaTipoEstado() throws SQLException {

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        // Creo la consulta
        String sqlEstado = "SELECT estadoReproductor " + 
                            "from proyecto_vacas.padre  " +                    
                            "group by estadoReproductor;";

        String sqlRaza = "SELECT id_Raza,tipoRaza " + 
                            "from proyecto_vacas.raza  " +                    
                             "group by tipoRaza;";

        if (con != null) {
            // Estado
            PreparedStatement ps = null;
            ps = con.prepareStatement(sqlEstado);

            ResultSet rs = ps.executeQuery();

            String te[] = new String[1];

               te[0] = rs.getString(1);

                cbEstado.addItem(te[0]);

            
        
            // Raza
            ps = null;
            ps = con.prepareStatement(sqlRaza);

            rs = ps.executeQuery();  
            String raza[] = new String[2];          

            while (rs.next()) {

               raza[0] = rs.getString(1);
               raza[1] = rs.getString(2);

                cbRaza.addItem(raza[0] + ", " + raza[1]);
            }

        } else {
            JOptionPane.showMessageDialog(this, "conexion fallida");

        }
            
    }
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {                                          
        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int crotal = (int) spinnerCrotal.getValue();
        String raza = cbRaza.getSelectedItem().toString();
        String estado =  cbEstado.getSelectedItem().toString();
        String entrada = simpleDateFormat.format(spinnerEntrada.getValue()).toString();
        String nacimiento = simpleDateFormat.format(spinnerNacimiento.getValue()).toString();

        int newRaza = 0;
        String[] arraySeparar = raza.split(",");
        newRaza = Integer.parseInt(arraySeparar[0]);

        int comida = 0;
        String[] arraySepararEstado = estado.split(",");
        estado = arraySepararEstado[0];
        comida = Integer.parseInt(arraySepararEstado[1]);

        

        try {
            String sql = "insert into padre values(?,?,?,?,?,?);";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, crotal);
            ps.setInt(2, newRaza);
            ps.setString(3, estado);
            ps.setString(4, entrada);
            ps.setString(5, nacimiento);
            ps.setInt(6,comida);


            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: crotal de vaca existente");
            Logger.getLogger(AddToro.class.getName()).log(Level.SEVERE, null, e);

        }

            dispose();
    } 

    
    
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        spinnerCrotal = new javax.swing.JSpinner();
        cbRaza = new javax.swing.JComboBox<>();
        cbEstado = new javax.swing.JComboBox<>();
        spinnerEntrada = new javax.swing.JSpinner();
        spinnerNacimiento = new javax.swing.JSpinner();
        btnAñadir = new javax.swing.JButton();
        txtCrotal = new javax.swing.JTextField();
        txtRaza = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtEntrada = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Añadir Toro");

        spinnerCrotal.setModel(new javax.swing.SpinnerNumberModel(7000, 7000, 9000, 1));

        spinnerEntrada.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerEntrada.setToolTipText("");
        spinnerEntrada.setEditor(new javax.swing.JSpinner.DateEditor(spinnerEntrada, "yyyy-MM-dd"));

        spinnerNacimiento.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerNacimiento.setToolTipText("");
        spinnerNacimiento.setEditor(new javax.swing.JSpinner.DateEditor(spinnerNacimiento, "yyyy-MM-dd"));

        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAñadirActionPerformed(evt);
                } catch (SQLException e) {
                   
                    System.out.println("ERROR: en btnAñadir un toro a la lista");
                }
            }
        });

        txtCrotal.setText("Crotal");

        txtRaza.setText("Raza");

        txtEstado.setText("Estado Reproductor");

        txtNacimiento.setText("Fecha Nacimiento");

        txtEntrada.setText("Entrada Explotación");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spinnerCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbRaza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 75, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnAñadir)
                .addGap(32, 32, 32))
        );

        pack();
    }                     

                                            

    
    

                    
}
    

