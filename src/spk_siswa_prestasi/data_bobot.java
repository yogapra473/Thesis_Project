/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk_siswa_prestasi;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;

/**
 *
 * @author Yoga Eka Pratama
 */
public class data_bobot extends javax.swing.JFrame {
    ResultSet r;
    Statement s;
    Connection c;
    private Object[][]databobot=null;
    private String[]label={"Id Kriteria","Kode Subkriteria", "Keterangan", "Bobot Subkriteria"};
    private Connection con;

    public data_bobot() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        bukakoneksi();
        bacaTable();
        combokriteria();
    }
    
    private void bukakoneksi()
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/spksiswaprestasi","root","");
            System.out.println("Koneksi Sukses");
        } 
        catch (Exception e) 
        {
            System.out.println("Koneksi Sukses");
        }
    }
    
    public void reset(){
        kodekriteria.setSelectedIndex(0);
        kodesubkriteria.requestFocus();
        kodesubkriteria.setText("");
        namakriteria.setText("");
        ketsub.setText("");        
        namasubkriteria2.setSelectedIndex(0);
    }
    public void combokriteria(){
        try 
       {
           String sql = "select idkriteria from tabelkriteria";
           s =c.createStatement();
           r = s.executeQuery(sql);
           while(r.next())
           {
               kodekriteria.addItem(r.getString("idkriteria"));
               namakriteria.setEnabled(false);
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void bacaTable(){
        try 
       {
           s=c.createStatement();
           String sql = "SELECT * FROM tabelbobot WHERE idbobot like '%"
                    + jtCari.getText() + "%'"
                    + "or keterangan like '%" + jtCari.getText()+ "%'";;
           r=s.executeQuery(sql);
           ResultSetMetaData m=r.getMetaData();
           int kolom=m.getColumnCount();
           int baris=0;
           while(r.next())
           {
               baris = r.getRow();
           }
           databobot= new Object[baris][kolom];
           int x=0;
           r.beforeFirst();
           while(r.next())
           {
               databobot[x][0]=r.getString("idkriteria");
               
//               databobot[x][1]=r.getString("namakriteria");
               databobot[x][1]=r.getString("idbobot");
               databobot[x][2]=r.getString("keterangan");
               databobot[x][3]=r.getString("bobot");
               
               //jScrollPane1.getColumnidkriteria().setVisible(false);
               x++;
           }
           ds.setModel(new DefaultTableModel(databobot, label));
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void pencarian(){
        try {
            String sql = "SELECT * FROM tabelbobot where idbobot like '%"
            + jtCari.getText() + "%'"
            + "or keterangan like '%" + jtCari.getText();

            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {

        }
        bacaTable();
        reset();
    }
    
    private void simpan()
    {
       try {
            String sql = "insert into tabelbobot values('"+kodesubkriteria.getText()+"','"+kodekriteria.getSelectedItem()+"','"+ketsub.getText()+"','"+namasubkriteria2.getSelectedItem()+"')";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan");
            bacaTable();
            reset();
            
        }
      catch (SQLException e) 
      {
          JOptionPane.showMessageDialog(null, e);
      }
    }
    
    private void editdata()
    {
    try{
        String sql = "UPDATE tabelbobot set idkriteria=?,keterangan=?,bobot=? where idbobot=?";
        java.sql.Connection con = (Connection) Konfig.configDB();
        PreparedStatement stat = con.prepareStatement(sql);
        
        stat.setString(1, (String) kodekriteria.getSelectedItem());
        stat.setString(2, ketsub.getText());
        stat.setString(3, (String) namasubkriteria2.getSelectedItem());
        stat.setString(4, kodesubkriteria.getText());
        
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
        reset();
        kodesubkriteria.requestFocus();
        bacaTable();
      }
      catch (SQLException e) 
      {
          JOptionPane.showMessageDialog(null, e);
      }
    }
    
    private void settable()
       {
        int baris = ds.getSelectedRow();
        
        String Kodekriteria = ds.getValueAt(baris, 0).toString();
        kodekriteria.setSelectedItem(Kodekriteria);
        
        String KodeSubKriteria = ds.getValueAt(baris, 1).toString();
        kodesubkriteria.setText(KodeSubKriteria);
        
        String Keterangan = ds.getValueAt(baris, 2).toString();
        ketsub.setText(Keterangan);
        
        String BobotSub = ds.getValueAt(baris, 3).toString();
        namasubkriteria2.setSelectedItem(BobotSub);
                  
       }
    
     private void hapusdata()
    {
      try 
      {
          String sql = "DELETE FROM tabelbobot WHERE idbobot='"+kodesubkriteria.getText()+"'";
          s.executeUpdate(sql);
          s.close();
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
          bacaTable();
          reset();
      } 
      catch (SQLException e)
      {
          JOptionPane.showMessageDialog(null, e);
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

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kodekriteria = new javax.swing.JComboBox<>();
        kodesubkriteria = new javax.swing.JTextField();
        jsub = new javax.swing.JButton();
        jed = new javax.swing.JButton();
        jde = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ds = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jtCari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ketsub = new javax.swing.JTextField();
        namasubkriteria2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        namakriteria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Bobot SMK PGRI 20");
        setMinimumSize(new java.awt.Dimension(597, 618));
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel3.setText("DATA SUBKRITERIA");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(210, 10, 194, 29);

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Kode Kriteria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 70, 110, 26);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Kode SubKriteria");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 150, 140, 30);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Bobot SubKriteria");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(140, 190, 150, 26);

        kodekriteria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        kodekriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodekriteriaActionPerformed(evt);
            }
        });
        getContentPane().add(kodekriteria);
        kodekriteria.setBounds(300, 70, 160, 30);
        getContentPane().add(kodesubkriteria);
        kodesubkriteria.setBounds(300, 150, 160, 30);

        jsub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jsub.setText("SIMPAN");
        jsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsubActionPerformed(evt);
            }
        });
        getContentPane().add(jsub);
        jsub.setBounds(240, 280, 110, 31);

        jed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jed.setText("UBAH");
        jed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jedActionPerformed(evt);
            }
        });
        getContentPane().add(jed);
        jed.setBounds(70, 280, 100, 31);

        jde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jde.setText("HAPUS");
        jde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeActionPerformed(evt);
            }
        });
        getContentPane().add(jde);
        jde.setBounds(420, 280, 100, 31);

        ds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Kode Subkriteria", "Keterangan", "Bobot Subkriteria"
            }
        ));
        ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ds);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 370, 500, 210);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Cari Data :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(76, 338, 80, 20);

        jtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCariActionPerformed(evt);
            }
        });
        getContentPane().add(jtCari);
        jtCari.setBounds(180, 330, 210, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(400, 330, 55, 31);

        jLabel7.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Keterangan");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(160, 230, 100, 26);
        getContentPane().add(ketsub);
        ketsub.setBounds(300, 230, 160, 30);

        namasubkriteria2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "1", "2", "3", "4", "5" }));
        namasubkriteria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namasubkriteria2ActionPerformed(evt);
            }
        });
        getContentPane().add(namasubkriteria2);
        namasubkriteria2.setBounds(300, 190, 160, 30);

        jLabel8.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("Nama Kriteria");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(150, 110, 120, 26);
        getContentPane().add(namakriteria);
        namakriteria.setBounds(300, 110, 160, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_data_bobot.jpg"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, -50, 590, 700);

        getAccessibleContext().setAccessibleName("Data Bobot SMK PGRI 20");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kodekriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodekriteriaActionPerformed
        int i = kodekriteria.getSelectedIndex();
        if (i== 0){
            namakriteria.setText("");
            return;
        }else{
            try
            {
                String sql = "select namakriteria from tabelkriteria WHERE idkriteria ='"+kodekriteria.getSelectedItem()+"'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.namakriteria.setText(r.getString("namakriteria"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_kodekriteriaActionPerformed

    private void jsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsubActionPerformed
        
        if("".equals(kodesubkriteria.getText()) || "".equals(kodekriteria.getSelectedItem())|| "".equals(ketsub.getText())
        || "".equals(namasubkriteria2.getSelectedItem()) || "".equals(namakriteria.getText())){
            JOptionPane.showMessageDialog(null, "Lengkapi data dahulu");
            kodesubkriteria.requestFocus();
        }else{
            try {
                s=c.createStatement();
                String a="SELECT * FROM tabelbobot WHERE idbobot='"+kodesubkriteria.getText()+"'";
                r=s.executeQuery(a);
                if(r.next()){
                    JOptionPane.showMessageDialog(null, "Kode Subkriteria Pelamar Sudah ada");
                    kodesubkriteria.requestFocus();
                }else{
                    simpan();
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jsubActionPerformed

    private void jedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jedActionPerformed
        if("".equals(kodesubkriteria.getText())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            kodesubkriteria.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "Apakah data ini ingin di Edit ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                editdata();
            }
        }
    }//GEN-LAST:event_jedActionPerformed

    private void jdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeActionPerformed
        if("".equals(kodesubkriteria.getText())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            kodesubkriteria.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "Apakah data ini ingin di hapus ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                hapusdata();
            }
        }
    }//GEN-LAST:event_jdeActionPerformed

    private void dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsMouseClicked
        settable();
    }//GEN-LAST:event_dsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("".equals(jtCari.getText())){
            bacaTable();
        }else{
            pencarian();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void namasubkriteria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namasubkriteria2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namasubkriteria2ActionPerformed

    private void jtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCariActionPerformed

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
            java.util.logging.Logger.getLogger(data_bobot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_bobot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_bobot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_bobot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_bobot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ds;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jde;
    private javax.swing.JButton jed;
    private javax.swing.JButton jsub;
    private javax.swing.JTextField jtCari;
    private javax.swing.JTextField ketsub;
    private javax.swing.JComboBox<String> kodekriteria;
    private javax.swing.JTextField kodesubkriteria;
    private javax.swing.JTextField namakriteria;
    private javax.swing.JComboBox<String> namasubkriteria2;
    // End of variables declaration//GEN-END:variables
}
