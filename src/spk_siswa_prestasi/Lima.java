/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk_siswa_prestasi;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;
/**
 *
 * @author Yoga Eka Pratama
 */
public class Lima extends javax.swing.JFrame {

    /**
     * Creates new form Lima
     */
    public Lima() {
         initComponents();
        Tampilkan_Data_Max();
        Tampilkan_Data_Min();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void Tampilkan_Data_Min() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("K1");
        model.addColumn("K2");
        model.addColumn("K3");
        model.addColumn("K4");
        model.addColumn("K5");

        try {

            int no = 1;
            String sql = "SELECT \n" +
"MIN((K1/ x.ratio1) * bobotK1.bobotkriteria) AS K1, \n" +
"MIN((K2/ x.ratio2) * bobotK2.bobotkriteria) AS K2, \n" +
"MIN((K3/ x.ratio3) * bobotK3.bobotkriteria) AS K3, \n" +
"MIN((K4/ x.ratio4) * bobotK4.bobotkriteria) AS K4, \n" +
"MIN((K5/ x.ratio5) * bobotK5.bobotkriteria) AS K5 FROM tabelkriteriasiswa AS tk\n" +
"JOIN (\n" +
"    SELECT \n" +
"    SQRT(SUM(power(K1,2))) as ratio1, \n" +
"    SQRT(SUM(power(K2,2))) as ratio2, \n" +
"    SQRT(SUM(power(K3,2))) as ratio3, \n" +
"    SQRT(SUM(power(K4,2))) as ratio4, \n" +
"    SQRT(SUM(power(K5,2))) as ratio5  \n" +
"    from tabelkriteriasiswa) as x\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K1') AS bobotK1\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K2') AS bobotK2\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K3') AS bobotK3\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K4') AS bobotK4\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K5') AS bobotK5;";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

           while (res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),
                    res.getString(3),res.getString(4),res.getString(5)});
            }
            min.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }
    
    private void Tampilkan_Data_Max() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("K1");
        model.addColumn("K2");
        model.addColumn("K3");
        model.addColumn("K4");
        model.addColumn("K5");

        try {

            int no = 1;
            String sql = "SELECT \n" +
"MAX((K1/ x.ratio1) * bobotK1.bobotkriteria) AS K1, \n" +
"MAX((K2/ x.ratio2) * bobotK2.bobotkriteria) AS K2, \n" +
"MAX((K3/ x.ratio3) * bobotK3.bobotkriteria) AS K3, \n" +
"MAX((K4/ x.ratio4) * bobotK4.bobotkriteria) AS K4, \n" +
"MAX((K5/ x.ratio5) * bobotK5.bobotkriteria) AS K5 FROM tabelkriteriasiswa AS tk\n" +
"JOIN (\n" +
"    SELECT \n" +
"    SQRT(SUM(power(K1,2))) as ratio1, \n" +
"    SQRT(SUM(power(K2,2))) as ratio2, \n" +
"    SQRT(SUM(power(K3,2))) as ratio3, \n" +
"    SQRT(SUM(power(K4,2))) as ratio4, \n" +
"    SQRT(SUM(power(K5,2))) as ratio5  \n" +
"    from tabelkriteriasiswa) as x\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K1') AS bobotK1\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K2') AS bobotK2\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K3') AS bobotK3\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K4') AS bobotK4\n" +
"JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K5') AS bobotK5;";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

           while (res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),
                    res.getString(3),res.getString(4),res.getString(5)});
            }
            max.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());

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

        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        min = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        max = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PERHITUNGAN KETIGA");
        setMinimumSize(new java.awt.Dimension(550, 455));
        getContentPane().setLayout(null);

        jButton7.setBackground(new java.awt.Color(255, 0, 51));
        jButton7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton7.setText("LANGKAH 7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(280, 140, 109, 30);

        jButton6.setBackground(new java.awt.Color(255, 0, 51));
        jButton6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton6.setText("LANGKAH 6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(150, 140, 109, 30);

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton2.setText("LANGKAH 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(150, 80, 109, 30);

        jButton4.setBackground(new java.awt.Color(255, 0, 51));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton4.setText("LANGKAH 4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(410, 80, 109, 30);

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Perhitungan Ketiga");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 10, 230, 34);

        jButton5.setBackground(new java.awt.Color(255, 0, 51));
        jButton5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton5.setText("LANGKAH 5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(20, 140, 109, 30);

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton1.setText("LANGKAH 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 80, 109, 30);

        jButton3.setBackground(new java.awt.Color(255, 0, 51));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton3.setText("LANGKAH 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(280, 80, 109, 30);

        jButton8.setBackground(new java.awt.Color(255, 0, 51));
        jButton8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton8.setText("LANGKAH 8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(410, 140, 109, 30);

        jLabel2.setFont(new java.awt.Font("Rockwell Condensed", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Perhitungan Nilai Solusi Ideal Positif (Max) Dan Solusi Ideal Negatif (Min)");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 190, 460, 19);

        min.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "K1", "K2", "K3", "K4"
            }
        ));
        jScrollPane1.setViewportView(min);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 360, 450, 44);

        max.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "K1", "K2", "K3", "K4"
            }
        ));
        jScrollPane2.setViewportView(max);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(40, 260, 450, 45);

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("Nilai MAX");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 230, 75, 19);

        jLabel4.setFont(new java.awt.Font("Rockwell Condensed", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Nilai MIN");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 330, 75, 19);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_lima.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(-6, -6, 550, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Satu().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Dua().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Tiga().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Empat().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new Lima().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new Enam().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new Tujuh().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Delapan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Lima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lima().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable max;
    private javax.swing.JTable min;
    // End of variables declaration//GEN-END:variables
}
