package Interfaz.MovimientosVaca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import mySQL_XML.DbConnection;

public class ModifyVaca extends javax.swing.JFrame {
 
    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;

    // Declaramos variables de la INTERFAZ              
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbCrotalesVacas;
    private javax.swing.JComboBox<String> cbEstadoVaca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtCrotal;
    private javax.swing.JLabel txtEstado;
    // ****************************************    


    public ModifyVaca (java.awt.Frame parent, boolean modal) throws SQLException {

        initComponents();
        actualizarCrotalesYtipoEstado();

    }
    
    private void actualizarCrotalesYtipoEstado() throws SQLException {

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        // Creo la consulta
        String sqlEstado = "SELECT estadoParto " + 
                           "from proyecto_vacas.madre  " +                    
                           "group by estadoParto;";

        String sqlCrotal = "SELECT id_Crotal " + 
                           "from proyecto_vacas.madre;  ";

        if (con != null) {
            // Estado
            PreparedStatement ps = null;
            ps = con.prepareStatement(sqlEstado);

            ResultSet rs = ps.executeQuery();

            String te[] = new String[1];

            while (rs.next()) {

               te[0] = rs.getString(1);

                cbEstadoVaca.addItem(te[0]);

            }
        
            // Raza
            ps = null;
            ps = con.prepareStatement(sqlCrotal);

            rs = ps.executeQuery();  
            String crotal[] = new String[1];          

            while (rs.next()) {

               crotal[0] = rs.getString(1);

                cbCrotalesVacas.addItem(crotal[0]);
            }

        } else {
            JOptionPane.showMessageDialog(this, "conexion fallida");

        }
            
    }

    private void btnModificarMetodoUpdate(java.awt.event.ActionEvent evt) throws SQLException {                                          
        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        

        String crotal = cbCrotalesVacas.getSelectedItem().toString();
        String estado = cbEstadoVaca.getSelectedItem().toString();

        int idCrotal;
        try {
            String sql = "update madre "+
                         "set estadoParto = ? "+
                         "where id_Crotal = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            idCrotal = Integer.parseInt(crotal);
            ps.setString(1, estado);
            ps.setInt(2, idCrotal);
            


            ps.executeUpdate();
            
        } catch (SQLException e) {

            //System.out.println("ERROR: crotal de vaca existente");
            Logger.getLogger(AddVaca.class.getName()).log(Level.SEVERE, null, e);

        }

            dispose();

            

    }

    public ModifyVaca(Object object, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
 
                        
    private void initComponents() {

        cbCrotalesVacas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCrotal = new javax.swing.JLabel();
        cbEstadoVaca = new javax.swing.JComboBox<>();
        txtEstado = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Modificar Estado Vaca");

        txtCrotal.setText("Crotal Vaca");

        txtEstado.setText("Elige el estado");

        btnModificar.setText("Modificar Vaca");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnModificarMetodoUpdate(evt);
                } catch (SQLException e) {
                    System.out.println("ERROR: boton modificar");
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbEstadoVaca, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbCrotalesVacas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCrotalesVacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCrotal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstadoVaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnModificar)
                .addGap(25, 25, 25))
        );

        pack();
    }                     

    

                  
}
