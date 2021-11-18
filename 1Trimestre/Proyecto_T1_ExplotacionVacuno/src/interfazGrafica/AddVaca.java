package interfazGrafica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import mySQL_XML.DbConnection;

public class AddVaca extends javax.swing.JFrame{

    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;
    
    public AddVaca(java.awt.Frame parent, boolean modal) throws SQLException {
        
        initComponents();
        actualizarTipoRazaTipoEstado();
    }

    private void actualizarTipoRazaTipoEstado() throws SQLException {

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        // Creo la consulta
        String sql = "SELECT estadoParto " + 
                     "from proyecto_vacas.madre  " +                    
                     "group by estadoParto;";

        if (con != null) {
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            String te[] = new String[2];

            while (rs.next()) {

               te[0] = rs.getString(1);
               te[1] = rs.getString(2);

                cbRaza.addItem(te[0]);
                cbEstado.addItem(te[1]);

            }

        } else {
            JOptionPane.showMessageDialog(this, "conexion fallida");
        }
    }

    public AddVaca(Object object, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
    
    private void initComponents() {

        txtCrotal = new javax.swing.JTextField();
        txtRaza = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtPartos = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtEntrada = new javax.swing.JTextField();
        spinnerCrotal = new javax.swing.JSpinner();
        cbRaza = new javax.swing.JComboBox<>();
        cbEstado = new javax.swing.JComboBox<>();
        spinnerPartos = new javax.swing.JSpinner();
        spinnerEntrada = new javax.swing.JSpinner();
        spinnerNacimiento = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCrotal.setText("Crotal");

        txtRaza.setText("Raza");

        txtEstado.setText("Estado Parto");

        txtPartos.setText("Nº Partos");

        txtNacimiento.setText("Fecha Nacimiento");

        txtEntrada.setText("Entrada Explotación");

        spinnerCrotal.setModel(new javax.swing.SpinnerNumberModel(7000, 7000, 9000, 1199));

        cbRaza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        spinnerEntrada.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerEntrada.setToolTipText("");
        spinnerEntrada.setEditor(new javax.swing.JSpinner.DateEditor(spinnerEntrada, "yyyy-MM-dd"));

        spinnerNacimiento.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1637257729200L), null, null, java.util.Calendar.DAY_OF_MONTH));
        spinnerNacimiento.setToolTipText("");
        spinnerNacimiento.setEditor(new javax.swing.JSpinner.DateEditor(spinnerNacimiento, "yyyy-MM-dd"));

        jLabel1.setText("Añadir Vaca");

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
                            .addComponent(cbRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
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
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>                        


    // Declaracion de variables de la interfaz                   
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbRaza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner spinnerCrotal;
    private javax.swing.JSpinner spinnerEntrada;
    private javax.swing.JSpinner spinnerNacimiento;
    private javax.swing.JSpinner spinnerPartos;
    private javax.swing.JTextField txtCrotal;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtPartos;
    private javax.swing.JTextField txtRaza;
    // **************************************************                 
}