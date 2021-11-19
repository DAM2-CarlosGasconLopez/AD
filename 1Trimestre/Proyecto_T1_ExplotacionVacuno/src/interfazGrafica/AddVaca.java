package interfazGrafica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import mySQL_XML.DbConnection;

public class AddVaca extends javax.swing.JFrame{

    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;

    // Declaracion de variables de la interfaz                   
    private JComboBox<String> cbEstado;
    private JComboBox<String> cbRaza;
    private JLabel jLabel1;
    private JSpinner spinnerCrotal;
    private JSpinner spinnerEntrada;
    private JSpinner spinnerNacimiento;
    private JSpinner spinnerPartos;
    private JTextField txtCrotal;
    private JTextField txtEntrada;
    private JTextField txtEstado;
    private JTextField txtNacimiento;
    private JTextField txtPartos;
    private JTextField txtRaza;
    private JButton btnAñadir;
    // **************************************************   
    
    public AddVaca(java.awt.Frame parent, boolean modal) throws SQLException {
        
        initComponents();
        actualizarTipoRazaTipoEstado();
    }

    private void actualizarTipoRazaTipoEstado() throws SQLException {

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        // Creo la consulta
        String sqlEstado = "SELECT estadoParto " + 
                     "from proyecto_vacas.madre  " +                    
                     "group by estadoParto;";

        String sqlRaza = "SELECT id_Raza,tipoRaza " + 
                     "from proyecto_vacas.raza  " +                    
                     "group by tipoRaza;";

        if (con != null) {
            // Estado
            PreparedStatement ps = null;
            ps = con.prepareStatement(sqlEstado);

            ResultSet rs = ps.executeQuery();

            String te[] = new String[1];

            String comida = "";
            while (rs.next()) {
               
                if(rs.getString(1).equals("Inactiva")){
                    comida = "1";
                }else if (rs.getString(1).equals("Cubierta")) {
                    comida = "4";
                }else if(rs.getString(1).equals("En celo")){
                    comida = "2";
                }

               te[0] = rs.getString(1);

                cbEstado.addItem(te[0] + "    ," + comida);

            }
        
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

    private void btnAñadirMetodoInsert(java.awt.event.ActionEvent evt) throws SQLException {                                          
        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int crotal = (int) spinnerCrotal.getValue();
        String raza = cbRaza.getSelectedItem().toString();
        String estado =  cbEstado.getSelectedItem().toString();
        int partos = (int) spinnerPartos.getValue();
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
            String sql = "insert into madre values(?,?,?,?,?,?,?);";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, crotal);
            ps.setInt(2, newRaza);
            ps.setString(3, estado);
            ps.setInt(4, partos);
            ps.setString(5, entrada);
            ps.setString(6, nacimiento);
            ps.setInt(7,comida);


            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: crotal de vaca existente");
            Logger.getLogger(AddVaca.class.getName()).log(Level.SEVERE, null, e);

        }

            dispose();

    }

    public AddVaca(Object object, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
    
    private void initComponents() {

        txtCrotal = new JTextField();
        txtRaza = new JTextField();
        txtEstado = new JTextField();
        txtPartos = new JTextField();
        txtNacimiento = new JTextField();
        txtEntrada = new JTextField();
        spinnerCrotal = new JSpinner();
        cbRaza = new JComboBox<>();
        cbEstado = new JComboBox<>();
        spinnerPartos = new JSpinner();
        spinnerEntrada = new JSpinner();
        spinnerNacimiento = new JSpinner();
        jLabel1 = new JLabel();
        btnAñadir = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtCrotal.setText("Crotal");

        txtRaza.setText("Raza");

        txtEstado.setText("Estado Parto");

        txtPartos.setText("Nº Partos");

        txtNacimiento.setText("Fecha Nacimiento");

        txtEntrada.setText("Entrada Explotación");

        spinnerCrotal.setModel(new javax.swing.SpinnerNumberModel(7000, 7000, 9000, 1));

        cbRaza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        spinnerEntrada.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerEntrada.setToolTipText("");
        spinnerEntrada.setEditor(new javax.swing.JSpinner.DateEditor(spinnerEntrada, "yyyy-MM-dd"));

        spinnerNacimiento.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerNacimiento.setToolTipText("");
        spinnerNacimiento.setEditor(new javax.swing.JSpinner.DateEditor(spinnerNacimiento, "yyyy-MM-dd"));

        jLabel1.setText("Añadir Vaca");

        btnAñadir.setText("Añadir");

        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAñadirMetodoInsert(evt);
                } catch (SQLException e) {
                    System.out.println("ERROR: boton insertar");
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRaza, 0, 154, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
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
                    .addComponent(txtPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAñadir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        


}