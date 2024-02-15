    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk_siswa_prestasi;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import koneksi.Konfig;

import javax.swing.JFrame;
/**
 *
 * @author Yoga Eka Pratama
 */
public class Delapan extends javax.swing.JFrame {

    public Delapan() {
        initComponents();
        Tampilkan_Rangking();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void Tampilkan_Rangking() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Peringkat");
        model.addColumn("Nama Siswa"); 
        model.addColumn("Jurusan");
        model.addColumn("Preferensi");
        
        try {

            int no = 1;
            String sql = "SELECT \n" +
"    ROW_NUMBER() OVER (ORDER BY Preferensi DESC) AS Peringkat,\n" +
"    tk.namasiswa,\n" +
"    ts.jurusan,\n" +                    
"    (SQRT(\n" +
"        power(((tk.K1/ x.ratio1) * bobotK1.bobotkriteria) - V.K1min, 2) + \n" +
"        power(((tk.K2/ x.ratio2) * bobotK2.bobotkriteria) - V.K2min, 2) + \n" +
"        power(((tk.K3/ x.ratio3) * bobotK3.bobotkriteria) - V.K3min, 2) + \n" +
"        power(((tk.K4/ x.ratio4) * bobotK4.bobotkriteria) - V.K4min, 2) + \n" +
"        power(((tk.K5/ x.ratio5) * bobotK5.bobotkriteria) - V.K5min, 2))  \n" +                    
"     / \n" +
"     (SQRT(\n" +
"        power(((tk.K1/ x.ratio1) * bobotK1.bobotkriteria) - V.K1max, 2) + \n" +
"        power(((tk.K2/ x.ratio2) * bobotK2.bobotkriteria) - V.K2max, 2) + \n" +
"        power(((tk.K3/ x.ratio3) * bobotK3.bobotkriteria) - V.K3max, 2) + \n" +
"        power(((tk.K4/ x.ratio4) * bobotK4.bobotkriteria) - V.K4max, 2) + \n" +
"        power(((tk.K5/ x.ratio5) * bobotK5.bobotkriteria) - V.K5max, 2))  \n" +                    
"     +\n" +
"     SQRT(\n" +
"        power(((tk.K1/ x.ratio1) * bobotK1.bobotkriteria) - V.K1min, 2) + \n" +
"        power(((tk.K2/ x.ratio2) * bobotK2.bobotkriteria) - V.K2min, 2) + \n" +
"        power(((tk.K3/ x.ratio3) * bobotK3.bobotkriteria) - V.K3min, 2) + \n" +
"        power(((tk.K4/ x.ratio4) * bobotK4.bobotkriteria) - V.K4min, 2) + \n" +
"        power(((tk.K5/ x.ratio5) * bobotK5.bobotkriteria) - V.K5min, 2))  \n" +                    
"     ))\n" +
"      AS Preferensi\n" +
"    FROM tabelkriteriasiswa AS tk\n" +
" JOIN tabelsiswa AS ts ON tk.namasiswa = ts.namasiswa\n" +                    
"    JOIN (\n" +
"        SELECT \n" +
"            SQRT(SUM(power(K1,2))) as ratio1,\n" +
"            SQRT(SUM(power(K2,2))) as ratio2,\n" +
"            SQRT(SUM(power(K3,2))) as ratio3,\n" +
"            SQRT(SUM(power(K4,2))) as ratio4,\n" +
"            SQRT(SUM(power(K5,2))) as ratio5 \n" +                    
"        FROM tabelkriteriasiswa\n" +
"    ) AS x\n" +
"    JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K1') AS bobotK1\n" +
"    JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K2') AS bobotK2\n" +
"    JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K3') AS bobotK3\n" +
"    JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K4') AS bobotK4\n" +
"    JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K5') AS bobotK5\n" +                    
"    CROSS JOIN (\n" +
"        SELECT \n" +
"            MAX((tk.K1/ x.ratio1) * bobotK1.bobotkriteria) AS K1max,\n" +
"            MAX((tk.K2/ x.ratio2) * bobotK2.bobotkriteria) AS K2max,\n" +
"            MAX((tk.K3/ x.ratio3) * bobotK3.bobotkriteria) AS K3max,\n" +
"            MAX((tk.K4/ x.ratio4) * bobotK4.bobotkriteria) AS K4max,\n" +
"            MAX((tk.K5/ x.ratio5) * bobotK5.bobotkriteria) AS K5max,\n" +                    
"            MIN((tk.K1/ x.ratio1) * bobotK1.bobotkriteria) AS K1min,\n" +
"            MIN((tk.K2/ x.ratio2) * bobotK2.bobotkriteria) AS K2min,\n" +
"            MIN((tk.K3/ x.ratio3) * bobotK3.bobotkriteria) AS K3min,\n" +
"            MIN((tk.K4/ x.ratio4) * bobotK4.bobotkriteria) AS K4min,\n" +
"            MIN((tk.K5/ x.ratio5) * bobotK5.bobotkriteria) AS K5min\n" +                    
"        FROM tabelkriteriasiswa AS tk\n" +
"        JOIN (\n" +
"            SELECT \n" +
"                SQRT(SUM(power(K1,2))) as ratio1,\n" +
"                SQRT(SUM(power(K2,2))) as ratio2,\n" +
"                SQRT(SUM(power(K3,2))) as ratio3,\n" +
"                SQRT(SUM(power(K4,2))) as ratio4,\n" +
"                SQRT(SUM(power(K5,2))) as ratio5 \n" +                    
"            FROM tabelkriteriasiswa\n" +
"        ) AS x\n" +
"        JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K1') AS bobotK1\n" +
"        JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K2') AS bobotK2\n" +
"        JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K3') AS bobotK3\n" +
"        JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K4') AS bobotK4\n" +
"        JOIN (SELECT bobotkriteria FROM tabelkriteria WHERE idkriteria = 'K5') AS bobotK5\n" +                    
"    ) AS V\n" +
"    ORDER BY Preferensi DESC;";
            java.sql.Connection con = (Connection) Konfig.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

           while (res.next()) {
           model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
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

        jScrollPane2 = new javax.swing.JScrollPane();
        max = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HASIL PERHITUNGAN");
        setMinimumSize(new java.awt.Dimension(565, 620));
        setResizable(false);
        getContentPane().setLayout(null);

        max.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(max);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(30, 230, 500, 348);

        jLabel2.setFont(new java.awt.Font("Rockwell Condensed", 1, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Perangkingan Nilai Alternatif");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 190, 270, 25);

        jButton8.setBackground(new java.awt.Color(255, 0, 51));
        jButton8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton8.setText("LANGKAH 8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(420, 150, 109, 30);

        jButton7.setBackground(new java.awt.Color(255, 0, 51));
        jButton7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton7.setText("LANGKAH 7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(290, 150, 109, 30);

        jButton6.setBackground(new java.awt.Color(255, 0, 51));
        jButton6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton6.setText("LANGKAH 6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(160, 150, 109, 30);

        jButton5.setBackground(new java.awt.Color(255, 0, 51));
        jButton5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton5.setText("LANGKAH 5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(30, 150, 109, 29);

        jButton4.setBackground(new java.awt.Color(255, 0, 51));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton4.setText("LANGKAH 4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(420, 90, 109, 30);

        jButton3.setBackground(new java.awt.Color(255, 0, 51));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton3.setText("LANGKAH 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(290, 90, 109, 30);

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton2.setText("LANGKAH 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(160, 90, 109, 30);

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton1.setText("LANGKAH 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 90, 109, 30);

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 28)); // NOI18N
        jLabel1.setText("Hasil Perhitungan");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 10, 210, 34);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_delapan.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-6, -6, 570, 610);

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
            java.util.logging.Logger.getLogger(Delapan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Delapan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Delapan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Delapan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Delapan().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable max;
    // End of variables declaration//GEN-END:variables
}
