package interfazGrafica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import mySQL_XML.DbConnection;

public class DeleteVaca extends javax.swing.JFrame{

    // CONEXION
    public static DbConnection dbconnection = new DbConnection();
    public static Connection con = null;

    // Declaracion Vriable INTERFAZ                    
    private javax.swing.JButton btnBorrar;
    private javax.swing.JComboBox<String> cbCrotalesVacas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtCrotal;
    //******************************************  
    
    public DeleteVaca(java.awt.Frame parent, boolean modal) throws SQLException {
        
        initComponents();
        actualizarCrotalesComboBox();
    }

    private void actualizarCrotalesComboBox() throws SQLException {

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        // Creo la consulta

        String sqlCrotal = "SELECT id_Crotal " + 
                           "from proyecto_vacas.madre;  ";

        if (con != null) {
            
            
            PreparedStatement ps = null;
            ps = con.prepareStatement(sqlCrotal);

            ResultSet rs = ps.executeQuery();

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

    private void btnBorrarMetodoDelete(java.awt.event.ActionEvent evt) throws SQLException { 

        // Genero la conexión
        con = dbconnection.dataSource.getConnection();

        String crotal = cbCrotalesVacas.getSelectedItem().toString();

        int idCrotal;

        try {
            String sql = "delete from madre "+
                         "where id_Crotal = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            idCrotal = Integer.parseInt(crotal);

            ps.setInt(1, idCrotal);
            


            ps.executeUpdate();
            
        } catch (SQLException e) {

            //System.out.println("ERROR: crotal de vaca existente");
            Logger.getLogger(AddVaca.class.getName()).log(Level.SEVERE, null, e);

        }

            dispose();

    }

    private void initComponents() {

        cbCrotalesVacas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCrotal = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Borrar Vaca");

        txtCrotal.setText("Crotal Vaca");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnBorrarMetodoDelete(evt);
                } catch (SQLException e) {
                    System.out.println("ERROR: boton eliminar");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCrotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(cbCrotalesVacas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(btnBorrar)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    

                   
}
