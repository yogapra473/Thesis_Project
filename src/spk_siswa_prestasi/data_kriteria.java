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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;

import javax.swing.JFrame;

/**
 *
 * @author Yoga Eka Pratama
 */
public class data_kriteria extends javax.swing.JFrame {

    ResultSet r;
    Statement s;
    Connection c;
    private Object[][]datakriteria=null;
    private String[]label={"Kode Kriteria", "Nama Kriteria", "Bobot Kriteria", "Keterangan"};
    
    
    public data_kriteria() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        bukakoneksi();
        Tampilkan_Data();
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
     
protected void kosong(){
       reset();   
    }  

public void reset(){
        idkriteria.requestFocus();
        idkriteria.setText("");
        namakriteria.setText("");
        bobotkriteria.setSelectedIndex(0);
        kettkriteria.setSelectedIndex(0);
    }
    
     private void Tampilkan_Data() {
 try 
       {
           s=c.createStatement();
           String sql = "SELECT * FROM tabelkriteria WHERE idkriteria like '%"
                    + jtCari.getText() + "%'"
                    + "or namakriteria like '%" + jtCari.getText()+ "%'";;
           r=s.executeQuery(sql);
           ResultSetMetaData m=r.getMetaData();
           int kolom=m.getColumnCount();
           int baris=0;
           while(r.next())
           {
               baris = r.getRow();
           }
           datakriteria= new Object[baris][kolom];
           int x=0;
           r.beforeFirst();
           while(r.next())
           {
               datakriteria[x][0]=r.getString("idkriteria");
               datakriteria[x][1]=r.getString("namakriteria");
               datakriteria[x][2]=r.getString("bobotkriteria");
               datakriteria[x][3]=r.getString("ketkriteria");

               x++;
           }
           tbkriteria.setModel(new DefaultTableModel(datakriteria, label));
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
     
     public void pencarian(){
        try {
            String sql = "SELECT * FROM tabelkriteria where idkriteria like '%"
            + jtCari.getText() + "%'"
            + "or namakriteria like '%" + jtCari.getText();

            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();

        } catch (HeadlessException | SQLException e) {

        }
        Tampilkan_Data();
        reset();
    }
     
      private void simpan()
    {       
       try {
             String sql = "insert into tabelkriteria values('"+idkriteria.getText()+"','"+namakriteria.getText()+"','"+bobotkriteria.getSelectedItem()+"','"+kettkriteria.getSelectedItem()+"')";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
            Tampilkan_Data();
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
        String sql = "UPDATE tabelkriteria set namakriteria=?,bobotkriteria=?,ketkriteria=? where idkriteria=?";
        java.sql.Connection con = (Connection) Konfig.configDB();
        PreparedStatement stat = con.prepareStatement(sql);
        
        stat.setString(1, namakriteria.getText());
        stat.setString(2, (String) bobotkriteria.getSelectedItem());
        stat.setString(3, (String) kettkriteria.getSelectedItem());
        stat.setString(4, idkriteria.getText());
        
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
        reset();
        idkriteria.requestFocus();
        Tampilkan_Data();
      }
      catch (SQLException e) 
      {
          JOptionPane.showMessageDialog(null, e);
      }
    }
      
      private void settable()
       {
        int baris = tbkriteria.getSelectedRow();
        String Idkriteria = tbkriteria.getValueAt(baris, 0).toString();
        idkriteria.setText(Idkriteria);      

        String Nama_kriteria = tbkriteria.getValueAt(baris, 1).toString();
        namakriteria.setText(Nama_kriteria);

        String Bobotkriteria = tbkriteria.getValueAt(baris, 2).toString();
        bobotkriteria.setSelectedItem(Bobotkriteria);
        
        String Ketkriteria = tbkriteria.getValueAt(baris, 3).toString();
        kettkriteria.setSelectedItem(Ketkriteria);
       }
      
      private void hapusdata()
    {
      try 
      {
          String sql = "DELETE FROM tabelkriteria WHERE idkriteria='"+idkriteria.getText()+"'";
          s.executeUpdate(sql);
          s.close();
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
          Tampilkan_Data();
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
        kettkriteria = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jtCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbkriteria = new javax.swing.JTable();
        jde = new javax.swing.JButton();
        jed = new javax.swing.JButton();
        jsub = new javax.swing.JButton();
        namakriteria = new javax.swing.JTextField();
        idkriteria = new javax.swing.JTextField();
        bobotkriteria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Kriteria SMK PGRI 20");
        setMinimumSize(new java.awt.Dimension(480, 570));
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel3.setText("DATA KRITERIA");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(160, 10, 157, 27);

        kettkriteria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "Benefit", "Cost" }));
        kettkriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kettkriteriaActionPerformed(evt);
            }
        });
        getContentPane().add(kettkriteria);
        kettkriteria.setBounds(170, 140, 209, 26);

        jLabel7.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Keterangan");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 180, 100, 26);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(320, 260, 55, 31);
        getContentPane().add(jtCari);
        jtCari.setBounds(126, 260, 190, 26);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Cari Data :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 260, 80, 19);

        tbkriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Bobot Kriteria", "Keterangan"
            }
        ));
        tbkriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbkriteria);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(21, 298, 430, 230);

        jde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jde.setText("HAPUS");
        jde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeActionPerformed(evt);
            }
        });
        getContentPane().add(jde);
        jde.setBounds(310, 220, 134, 31);

        jed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jed.setText("UBAH");
        jed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jedActionPerformed(evt);
            }
        });
        getContentPane().add(jed);
        jed.setBounds(30, 220, 134, 31);

        jsub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jsub.setText("SIMPAN");
        jsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsubActionPerformed(evt);
            }
        });
        getContentPane().add(jsub);
        jsub.setBounds(170, 220, 134, 31);
        getContentPane().add(namakriteria);
        namakriteria.setBounds(170, 100, 209, 26);
        getContentPane().add(idkriteria);
        idkriteria.setBounds(170, 60, 209, 26);

        bobotkriteria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --", "1", "2", "3", "4", "5" }));
        bobotkriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bobotkriteriaActionPerformed(evt);
            }
        });
        getContentPane().add(bobotkriteria);
        bobotkriteria.setBounds(170, 180, 210, 26);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Bobot Kriteria");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 140, 120, 26);

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Nama Kriteria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 100, 120, 26);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Kode Kriteria");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 60, 120, 26);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_dta_kriteria.jpg"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(-6, -6, 490, 560);

        getAccessibleContext().setAccessibleName("Data Kriteria SMK PGRI 20");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bobotkriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bobotkriteriaActionPerformed
        
    }//GEN-LAST:event_bobotkriteriaActionPerformed

    private void jsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsubActionPerformed
        if("".equals(idkriteria.getText()) || "".equals(namakriteria.getText()) || "".equals(bobotkriteria.getSelectedItem()) || "".equals(kettkriteria.getSelectedItem())){
            JOptionPane.showMessageDialog(null, "Lengkapi data dahulu");
            idkriteria.requestFocus();
        }else{
            try {
                s=c.createStatement();
                String a="SELECT * FROM tabelkriteria WHERE idkriteria='"+idkriteria.getText()+"'";
                r=s.executeQuery(a);
                if(r.next()){
                    JOptionPane.showMessageDialog(null, "Kode Kriteria Sudah ada");
                    idkriteria.requestFocus();
                }else{
                    simpan();
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jsubActionPerformed

    private void jedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jedActionPerformed
        if("".equals(idkriteria.getText())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            idkriteria.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "Apakah data ini ingin di ubah ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                editdata();
            }
        }    }//GEN-LAST:event_jedActionPerformed

    private void jdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeActionPerformed
        if("".equals(idkriteria.getText())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            idkriteria.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "apakah data ini ingin di Hapus ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                hapusdata();
            }
        }    }//GEN-LAST:event_jdeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("".equals(jtCari.getText())){
            Tampilkan_Data();
        }else{
            pencarian();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbkriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkriteriaMouseClicked
        settable();
        idkriteria.setEnabled(false);
    }//GEN-LAST:event_tbkriteriaMouseClicked

    private void kettkriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kettkriteriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kettkriteriaActionPerformed

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
            java.util.logging.Logger.getLogger(data_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_kriteria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bobotkriteria;
    private javax.swing.JTextField idkriteria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jde;
    private javax.swing.JButton jed;
    private javax.swing.JButton jsub;
    private javax.swing.JTextField jtCari;
    private javax.swing.JComboBox<String> kettkriteria;
    private javax.swing.JTextField namakriteria;
    private javax.swing.JTable tbkriteria;
    // End of variables declaration//GEN-END:variables
}
