/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk_siswa_prestasi;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import koneksi.koneksi;

/**
 *
 * @author Yoga Eka Pratama
 */
public class data_admin extends javax.swing.JFrame {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public data_admin() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        Tampilkan_Data();
        kosong();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    for (Level level : Level.values()) {
        comboBoxModel.addElement(level.toString());
    }
    level.setModel(comboBoxModel);
}

private enum Level {
    Admin,
    Tata_Usaha,
    Guru
}

    protected void kosong() {
        idadmin.setText("");
        namaadm.setText("");
        uname.setText("");
        pass.setText("");
        level.setSelectedIndex(0);
    }

    private void Tampilkan_Data() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Admin");
        model.addColumn("Nama Pengguna");
        model.addColumn("Username");
        model.addColumn("Kata Sandi");
        model.addColumn("Level");

        try {
            String sql = "SELECT * FROM tabeladmin WHERE PNadmin LIKE ? OR username LIKE ?";
            con = koneksi.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + k.getText() + "%");
            pst.setString(2, "%" + k.getText() + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("PNadmin"),
                    rs.getString("nama"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("level")
                });
            }
            ds.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
        } else {
            super.processWindowEvent(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        cr = new javax.swing.JToggleButton();
        jde = new javax.swing.JButton();
        namaadm = new javax.swing.JTextField();
        level = new javax.swing.JComboBox<>();
        jed = new javax.swing.JButton();
        uname = new javax.swing.JTextField();
        jsub = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idadmin = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ds = new javax.swing.JTable();
        k = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Admin SMK PGRI 20");
        setMinimumSize(new java.awt.Dimension(1020, 590));
        getContentPane().setLayout(null);

        jLabel5.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("Cari Data :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(476, 60, 100, 30);

        cr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crActionPerformed(evt);
            }
        });
        getContentPane().add(cr);
        cr.setBounds(830, 60, 55, 40);

        jde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jde.setText("HAPUS");
        jde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeActionPerformed(evt);
            }
        });
        getContentPane().add(jde);
        jde.setBounds(320, 300, 134, 40);

        namaadm.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(namaadm);
        namaadm.setBounds(59, 184, 224, 29);

        level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        getContentPane().add(level);
        level.setBounds(59, 456, 224, 28);

        jed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jed.setText("UBAH");
        jed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jedActionPerformed(evt);
            }
        });
        getContentPane().add(jed);
        jed.setBounds(320, 180, 134, 41);

        uname.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(uname);
        uname.setBounds(59, 275, 224, 29);

        jsub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jsub.setText("SIMPAN");
        jsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsubActionPerformed(evt);
            }
        });
        getContentPane().add(jsub);
        jsub.setBounds(320, 240, 134, 41);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(133, 322, 70, 26);

        jLabel6.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Level");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(126, 412, 83, 26);

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Kode Admin");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(111, 63, 101, 26);

        idadmin.setBackground(new java.awt.Color(220, 221, 225));
        idadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idadminActionPerformed(evt);
            }
        });
        getContentPane().add(idadmin);
        idadmin.setBounds(59, 95, 224, 27);

        pass.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(pass);
        pass.setBounds(59, 366, 224, 28);

        jLabel3.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nama");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(118, 140, 87, 26);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Username");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(123, 231, 73, 26);

        ds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Admin", "Nama Pengguna", "Kata Sandi"
            }
        ));
        ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ds);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(472, 110, 480, 390);

        k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kActionPerformed(evt);
            }
        });
        getContentPane().add(k);
        k.setBounds(590, 60, 230, 34);

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("DATA ADMIN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(420, 10, 170, 34);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_data_admin.jpg"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(-6, -6, 1030, 590);

        getAccessibleContext().setAccessibleName("Data Admin SMK PGRI 20");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM tabeladmin where PNadmin like '%"
            + k.getText() + "%'"
            + "or username like '%" + k.getText();

            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {

        }
        Tampilkan_Data();
        kosong();
    }//GEN-LAST:event_crActionPerformed

    private void dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsMouseClicked
        int baris = ds.rowAtPoint(evt.getPoint());
        String PNadmin = ds.getValueAt(baris, 0).toString();
        idadmin.setText(PNadmin);
        idadmin.setEnabled(true);
        
        String Nama = ds.getValueAt(baris, 1).toString();
        namaadm.setText(Nama);

        String Username = ds.getValueAt(baris, 2).toString();
        uname.setText(Username);

        String Password = ds.getValueAt(baris, 3).toString();
        pass.setText(Password);
        
        String levelValue = ds.getValueAt(baris, 4).toString();
        level.setSelectedItem(levelValue);
    }//GEN-LAST:event_dsMouseClicked

    private void jedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jedActionPerformed
        try{
            String sql = "UPDATE tabeladmin set nama=?,username=?,password=?,level=? where PNadmin=?";
            java.sql.Connection con = (Connection) Konfig.configDB();
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, namaadm.getText());
            stat.setString(2, uname.getText());
            stat.setString(3, pass.getText());
            stat.setString(4, (String) level.getSelectedItem());
            stat.setString(5, idadmin.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
            kosong();
            idadmin.requestFocus();
            Tampilkan_Data();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Diubah" + e);
        }
    }//GEN-LAST:event_jedActionPerformed

    private void jdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeActionPerformed
        try {
            String sql = "DELETE FROM tabeladmin WHERE PNadmin ='" + idadmin.getText() + "'";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkan_Data();
        kosong();
    }//GEN-LAST:event_jdeActionPerformed

    private void jsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsubActionPerformed
        try {
        String sql = "INSERT INTO tabeladmin (PNadmin, nama, username, password, level) VALUES (?, ?, ?, ?, ?)";
        java.sql.Connection con = (Connection) Konfig.configDB();
        java.sql.PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, idadmin.getText());
        pstm.setString(2, namaadm.getText());
        pstm.setString(3, uname.getText());
        pstm.setString(4, pass.getText());
        pstm.setString(5, (String) level.getSelectedItem());
        pstm.execute();
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        Tampilkan_Data();
        kosong();
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_jsubActionPerformed

    private void kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kActionPerformed

    private void idadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idadminActionPerformed
        try {

            String sql = "SELECT * FROM tabeladmin where id='"+idadmin.getText()+"'";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                idadmin.setText(res.getString("id"));
                uname.setText(res.getString("username"));
                pass.setText(res.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }//GEN-LAST:event_idadminActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton cr;
    private javax.swing.JTable ds;
    private javax.swing.JTextField idadmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jde;
    private javax.swing.JButton jed;
    private javax.swing.JButton jsub;
    private javax.swing.JTextField k;
    private javax.swing.JComboBox<String> level;
    private javax.swing.JTextField namaadm;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField uname;
    // End of variables declaration//GEN-END:variables
}
