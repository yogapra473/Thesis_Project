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
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;

import javax.swing.JFrame;

/**
 *
 * @author Yoga Eka Pratama
 */
public class data_siswa extends javax.swing.JFrame {

    public data_siswa() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        Tampilkan_Data();
        kosong();
        initializeJurusanComboBox();
    }
private void initializeJurusanComboBox() {
    namajurusan.removeAllItems();
    namajurusan.addItem("Teknik Otomotif");
    namajurusan.addItem("Teknik Ketenagalistrikan");
}
    protected void kosong(){
       nis.setText("");
       namasiswa.setText("");
       namajurusan.setSelectedItem("Teknik Otomotif");  
       alamatsiswa.setText("");
       telpsiswa.setText("");
    }
    
    private void Tampilkan_Data() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("Jurusan");
        model.addColumn("Alamat");
        model.addColumn("No.Telp");

        try {

            int no = 1;
            String sql = "SELECT * FROM tabelsiswa WHERE nis like '%"
                    + k.getText() + "%'"
                    + "or namasiswa like '%" + k.getText()+ "%'";;
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

           while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5)});
            }
            ds.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }

    }
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dispose();
        } else {
            super.processWindowEvent(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        k = new javax.swing.JTextField();
        cr = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ds = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nis = new javax.swing.JTextField();
        namasiswa = new javax.swing.JTextField();
        alamatsiswa = new javax.swing.JTextField();
        telpsiswa = new javax.swing.JTextField();
        jsub = new javax.swing.JButton();
        jde = new javax.swing.JButton();
        jed = new javax.swing.JButton();
        namajurusan = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Siswa SMK PGRI 20");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(43, 203, 186));
        setMinimumSize(new java.awt.Dimension(792, 567));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Nomor Induk Siswa");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(510, 90, 160, 26);

        jLabel3.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("Nama Siswa");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(510, 170, 93, 26);

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 25)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA SISWA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(310, 10, 140, 40);

        jLabel7.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Cari Data :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(26, 50, 90, 26);

        k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kActionPerformed(evt);
            }
        });
        getContentPane().add(k);
        k.setBounds(130, 50, 200, 30);

        cr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crActionPerformed(evt);
            }
        });
        getContentPane().add(cr);
        cr.setBounds(340, 50, 55, 31);

        ds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIS", "Nama", "Jurusan", "Alamat", "No.Telp"
            }
        ));
        ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ds);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 90, 452, 350);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Jurusan");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(510, 260, 60, 26);

        jLabel5.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("Alamat");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(510, 340, 60, 26);

        jLabel6.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("No.Telp");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(510, 420, 70, 26);

        nis.setBackground(new java.awt.Color(220, 221, 225));
        nis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nisActionPerformed(evt);
            }
        });
        getContentPane().add(nis);
        nis.setBounds(510, 130, 240, 30);

        namasiswa.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(namasiswa);
        namasiswa.setBounds(510, 210, 240, 30);

        alamatsiswa.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(alamatsiswa);
        alamatsiswa.setBounds(510, 380, 240, 30);

        telpsiswa.setBackground(new java.awt.Color(220, 221, 225));
        getContentPane().add(telpsiswa);
        telpsiswa.setBounds(510, 460, 240, 30);

        jsub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jsub.setText("SIMPAN");
        jsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsubActionPerformed(evt);
            }
        });
        getContentPane().add(jsub);
        jsub.setBounds(360, 470, 110, 31);

        jde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jde.setText("HAPUS");
        jde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeActionPerformed(evt);
            }
        });
        getContentPane().add(jde);
        jde.setBounds(40, 470, 110, 31);

        jed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jed.setText("UBAH");
        jed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jedActionPerformed(evt);
            }
        });
        getContentPane().add(jed);
        jed.setBounds(200, 470, 110, 30);

        namajurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        namajurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namajurusanActionPerformed(evt);
            }
        });
        getContentPane().add(namajurusan);
        namajurusan.setBounds(510, 300, 240, 30);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_data_siswa.jpg"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(-6, -6, 800, 580);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsubActionPerformed
        try {
            String sql = "insert into tabelsiswa values('"+nis.getText()+"','"+namasiswa.getText()+"','"+namajurusan.getSelectedItem()+"','"+alamatsiswa.getText()+"','"+telpsiswa.getText()+"')";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            Tampilkan_Data();
            kosong();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jsubActionPerformed

    private void jdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeActionPerformed
       try {
            String sql = "DELETE FROM tabelsiswa WHERE nis ='" + nis.getText() + "'";
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

    private void jedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jedActionPerformed
try{
             String sql = "UPDATE tabelsiswa set namasiswa=?,jurusan=?,alamatsiswa=?,telpsiswa=? where nis=?";
        java.sql.Connection con = (Connection) Konfig.configDB();
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, namasiswa.getText());
        stat.setString(2, (String) namajurusan.getSelectedItem());
        stat.setString(3, alamatsiswa.getText());
        stat.setString(4, telpsiswa.getText());
        stat.setString(5, nis.getText());

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        kosong();
        nis.requestFocus();
        Tampilkan_Data();
        nis.setEnabled(true); // Add this line to re-enable the nis field

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Diubah" + e);
    }
    }//GEN-LAST:event_jedActionPerformed

    private void kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kActionPerformed

    private void crActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crActionPerformed

        try {
            String sql = "SELECT * FROM tabelsiswa where nis like '%"
            + k.getText() + "%'"
            + "or namasiswa like '%" + k.getText();

            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {

        }
        Tampilkan_Data();
        kosong();
    }//GEN-LAST:event_crActionPerformed

    private void nisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nisActionPerformed
       try {
        String sql = "SELECT * FROM tabelsiswa WHERE nis = '" + nis.getText() + "'";
        Connection con = Konfig.configDB();
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            nis.setText(res.getString("nis"));
            namasiswa.setText(res.getString("namasiswa"));
            namajurusan.setSelectedItem(res.getString("jurusan"));
            alamatsiswa.setText(res.getString("alamatsiswa"));
            telpsiswa.setText(res.getString("telpsiswa"));
        } else {
            JOptionPane.showMessageDialog(null, "No data found for NIS: " + nis.getText());
            kosong(); 
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    }//GEN-LAST:event_nisActionPerformed

    private void dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsMouseClicked
        int baris = ds.rowAtPoint(evt.getPoint());
        String Nis = ds.getValueAt(baris, 0).toString();
        nis.setText(Nis);
        nis.setEnabled(false);

        String Namasiswa = ds.getValueAt(baris, 1).toString();
        namasiswa.setText(Namasiswa);
    
        String Jurusan = ds.getValueAt(baris, 2).toString();
        namajurusan.setSelectedItem(Jurusan);
    
        String Alamatsiswa = ds.getValueAt(baris, 3).toString();
        alamatsiswa.setText(Alamatsiswa);
    
        String Telpsiswa = ds.getValueAt(baris, 4).toString();
        telpsiswa.setText(Telpsiswa);
    }//GEN-LAST:event_dsMouseClicked

    private void namajurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namajurusanActionPerformed
        // TODO add your handling code here:
        int selectedIndex = namajurusan.getSelectedIndex();
    
    if (selectedIndex != -1) {
        String selectedJurusan = (String) namajurusan.getSelectedItem();
        initializeJurusanComboBox();
        namajurusan.setSelectedItem(selectedJurusan);
    }
    }//GEN-LAST:event_namajurusanActionPerformed

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
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatsiswa;
    private javax.swing.JToggleButton cr;
    private javax.swing.JTable ds;
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
    private javax.swing.JComboBox<String> namajurusan;
    private javax.swing.JTextField namasiswa;
    private javax.swing.JTextField nis;
    private javax.swing.JTextField telpsiswa;
    // End of variables declaration//GEN-END:variables
}
