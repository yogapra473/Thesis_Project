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
public class pembobotan extends javax.swing.JFrame {
    ResultSet r;
    Statement s;
    Connection c;
    private Object[][]datapembobotan=null;
    private String[]label={"ID Pembobotan", "Nama Siswa","K1", "K2", "K3", "K4", "K5"};
    private Connection con;

    public pembobotan() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        bukakoneksi();
        bacaTable();
        combosiswa();
        combopengetahuan();
        comboketerampilan();
        combousia();
        combokarakter();
        comboangket();
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
        namasiswa.setSelectedIndex(0);
        namasiswa.requestFocus();
        keterampilan.setSelectedIndex(0);
        karakter.setSelectedIndex(0);       
        usia.setSelectedIndex(0);
        pengetahuan.setSelectedIndex(0);
        angket.setSelectedIndex(0);
        kodebobot.setText("");
    }
    public void combosiswa(){
        try 
       {
           String sql = "select namasiswa from tabelsiswa";
           s =c.createStatement();
           r = s.executeQuery(sql);
           while(r.next())
           {
               namasiswa.addItem(r.getString("namasiswa"));
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }   
    
    public void combopengetahuan(){       
        try 
       {
           String sql = "SELECT bobot FROM tabelbobot where idkriteria ='K1'";
           s =c.createStatement();
           r = s.executeQuery(sql);

           while(r.next())
           {
               pengetahuan.addItem(r.getString("bobot"));
               pengetahuan1.setEnabled(false);
               
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void combousia(){       
        try 
       {
           String sql = "SELECT bobot FROM tabelbobot where idkriteria ='K3'";
           s =c.createStatement();
           r = s.executeQuery(sql);

           while(r.next())
           {
               keterampilan.addItem(r.getString("bobot"));
               keterampilan1.setEnabled(false);
               
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void comboketerampilan(){       
        try 
       {
           String sql = "SELECT bobot FROM tabelbobot where idkriteria ='K2'";
           s =c.createStatement();
           r = s.executeQuery(sql);

           while(r.next())
           {
               usia.addItem(r.getString("bobot"));
               usia1.setEnabled(false);
               
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void combokarakter(){       
        try 
       {
           String sql = "SELECT bobot FROM tabelbobot where idkriteria ='K4'";
           s =c.createStatement();
           r = s.executeQuery(sql);

           while(r.next())
           {
               karakter.addItem(r.getString("bobot"));
               karakter1.setEnabled(false);
               
           }
           
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void comboangket(){       
        try 
       {
           String sql = "SELECT bobot FROM tabelbobot where idkriteria ='K5'";
           s =c.createStatement();
           r = s.executeQuery(sql);

           while(r.next())
           {
               angket.addItem(r.getString("bobot"));
               angket1.setEnabled(false);
               
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
            String sql = "SELECT * FROM tabelkriteriasiswa WHERE namasiswa like '%" + jtCari.getText()+ "%'";
           r=s.executeQuery(sql);
           ResultSetMetaData m=r.getMetaData();
           int kolom=m.getColumnCount();
           int baris=0;
           while(r.next())
           {
               baris = r.getRow();
           }
           datapembobotan= new Object[baris][kolom];
           int x=0;
           r.beforeFirst();
           while(r.next())
           {            
               datapembobotan[x][0]=r.getString("idpembobotan");
               datapembobotan[x][1]=r.getString("namasiswa");
               datapembobotan[x][2]=r.getString("K1");
               datapembobotan[x][3]=r.getString("K2");
               datapembobotan[x][4]=r.getString("K3");
               datapembobotan[x][5]=r.getString("K4");
               datapembobotan[x][6]=r.getString("K5");
               
               x++;
           }
           ds.setModel(new DefaultTableModel(datapembobotan, label));
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void pencarian(){
        try {
            String sql = "SELECT * FROM tabelkriteriasiswa WHERE idpembobotan like '%"
                    + k.getText() + "%'"
                    + "or namasiswa like '%" + k.getText()+ "%'";;

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
            String sql = "insert into tabelkriteriasiswa values('"+kodebobot.getText()+"','"+namasiswa.getSelectedItem()+"','"+pengetahuan.getSelectedItem()+"','"+keterampilan.getSelectedItem()+"','"+usia.getSelectedItem()+"','"+karakter.getSelectedItem()+"','"+angket.getSelectedItem()+"')";
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
        String sql = "UPDATE tabelkriteriasiswa set namasiswa=?,K1=?,K2=?,K3=?,K4=?,K5=? where idpembobotan=?";
        java.sql.Connection con = (Connection) Konfig.configDB();
        PreparedStatement stat = con.prepareStatement(sql);
        
        stat.setString(1, (String) namasiswa.getSelectedItem());
        stat.setString(2, (String) pengetahuan.getSelectedItem());
        stat.setString(3, (String) keterampilan.getSelectedItem());      
        stat.setString(4, (String) usia.getSelectedItem());
        stat.setString(5, (String) karakter.getSelectedItem());   
        stat.setString(6, (String) angket.getSelectedItem()); 
        stat.setString(7, kodebobot.getText());
               
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
        reset();
        kodebobot.requestFocus();
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
        
        String KodeBobot = ds.getValueAt(baris, 0).toString();
        kodebobot.setText(KodeBobot);
        
        String Kodesiswa = ds.getValueAt(baris, 1).toString();
        namasiswa.setSelectedItem(Kodesiswa);
        
        String Pengetahuan = ds.getValueAt(baris, 2).toString();
        pengetahuan.setSelectedItem(Pengetahuan);
        
        String Keterampilan = ds.getValueAt(baris, 3).toString();
        keterampilan.setSelectedItem(Keterampilan);
        
        String Usia = ds.getValueAt(baris, 4).toString();
        usia.setSelectedItem(Usia);
        
        String Karakter = ds.getValueAt(baris, 5).toString();
        karakter.setSelectedItem(Karakter);
        
        String Angket = ds.getValueAt(baris, 6).toString();
        angket.setSelectedItem(Angket);
                  
       }
    
     private void hapusdata()
    {
      try 
      {
          String sql = "DELETE FROM tabelkriteriasiswa WHERE idpembobotan='"+kodebobot.getText()+"'";
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        angket1 = new javax.swing.JTextField();
        angket = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        karakter1 = new javax.swing.JTextField();
        usia1 = new javax.swing.JTextField();
        keterampilan1 = new javax.swing.JTextField();
        pengetahuan1 = new javax.swing.JTextField();
        karakter = new javax.swing.JComboBox<>();
        usia = new javax.swing.JComboBox<>();
        keterampilan = new javax.swing.JComboBox<>();
        pengetahuan = new javax.swing.JComboBox<>();
        namasiswa = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        k = new javax.swing.JButton();
        jtCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ds = new javax.swing.JTable();
        jde = new javax.swing.JButton();
        jed = new javax.swing.JButton();
        jsub = new javax.swing.JButton();
        kodebobot = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pembobotan Data Siswa");
        setMinimumSize(new java.awt.Dimension(680, 750));
        getContentPane().setLayout(null);

        angket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angket1ActionPerformed(evt);
            }
        });
        getContentPane().add(angket1);
        angket1.setBounds(360, 300, 270, 30);

        angket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        angket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angketActionPerformed(evt);
            }
        });
        getContentPane().add(angket);
        angket.setBounds(170, 300, 160, 30);

        jLabel10.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("Angket");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 300, 150, 30);
        getContentPane().add(karakter1);
        karakter1.setBounds(360, 250, 270, 30);
        getContentPane().add(usia1);
        usia1.setBounds(360, 200, 270, 30);
        getContentPane().add(keterampilan1);
        keterampilan1.setBounds(360, 150, 270, 30);
        getContentPane().add(pengetahuan1);
        pengetahuan1.setBounds(360, 100, 270, 30);

        karakter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        karakter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                karakterActionPerformed(evt);
            }
        });
        getContentPane().add(karakter);
        karakter.setBounds(170, 250, 160, 30);

        usia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        usia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usiaActionPerformed(evt);
            }
        });
        getContentPane().add(usia);
        usia.setBounds(170, 200, 160, 30);

        keterampilan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        keterampilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keterampilanActionPerformed(evt);
            }
        });
        getContentPane().add(keterampilan);
        keterampilan.setBounds(170, 150, 160, 30);

        pengetahuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        pengetahuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengetahuanActionPerformed(evt);
            }
        });
        getContentPane().add(pengetahuan);
        pengetahuan.setBounds(170, 100, 160, 30);

        namasiswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        namasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namasiswaActionPerformed(evt);
            }
        });
        getContentPane().add(namasiswa);
        namasiswa.setBounds(430, 40, 200, 30);

        jLabel9.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("Nilai Karakter");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 250, 150, 30);

        jLabel8.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("Usia       ");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 200, 150, 30);

        jLabel7.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Nilai Keterampilan");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 150, 150, 30);

        k.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kActionPerformed(evt);
            }
        });
        getContentPane().add(k);
        k.setBounds(440, 390, 55, 30);

        jtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCariActionPerformed(evt);
            }
        });
        getContentPane().add(jtCari);
        jtCari.setBounds(190, 390, 240, 30);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Cari Data :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(90, 390, 90, 30);

        ds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Bobot Kriteria", "Keterangan"
            }
        ));
        ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ds);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 430, 600, 280);

        jde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jde.setText("HAPUS");
        jde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeActionPerformed(evt);
            }
        });
        getContentPane().add(jde);
        jde.setBounds(410, 350, 110, 30);

        jed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jed.setText("UBAH");
        jed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jedActionPerformed(evt);
            }
        });
        getContentPane().add(jed);
        jed.setBounds(280, 350, 110, 30);

        jsub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jsub.setText("SIMPAN");
        jsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsubActionPerformed(evt);
            }
        });
        getContentPane().add(jsub);
        jsub.setBounds(150, 350, 110, 30);
        getContentPane().add(kodebobot);
        kodebobot.setBounds(210, 40, 90, 30);

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Nilai Pengetahuan");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 100, 150, 30);

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Nama Siswa");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(320, 40, 100, 30);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Kode Bobot");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(100, 40, 100, 30);

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jLabel3.setText("PEMBOBOTAN DATA");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 0, 195, 27);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bck_pembobotan2.jpg"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(-10, -6, 680, 750);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void keterampilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keterampilanActionPerformed
        int i = keterampilan.getSelectedIndex();
        if (i== 0){
            keterampilan1.setText("");
            return;
        }else{
            try
            {
                String sql = "SELECT keterangan from tabelbobot WHERE idkriteria like 'K2%' AND bobot ='" + keterampilan.getSelectedItem()+ "'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.keterampilan1.setText(r.getString("keterangan"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_keterampilanActionPerformed

    private void pengetahuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengetahuanActionPerformed
        int i = pengetahuan.getSelectedIndex();
        if (i== 0){
            pengetahuan1.setText("");
            return;
        }else{
            try
            {
                String sql = "SELECT keterangan from tabelbobot WHERE idkriteria like 'K1%' AND bobot ='" + pengetahuan.getSelectedItem()+ "'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.pengetahuan1.setText(r.getString("keterangan"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_pengetahuanActionPerformed

    private void namasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namasiswaActionPerformed

    }//GEN-LAST:event_namasiswaActionPerformed

    private void kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kActionPerformed
        if("".equals(jtCari.getText())){
            bacaTable();
        }else{
            pencarian();
        }
    }//GEN-LAST:event_kActionPerformed

    private void dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsMouseClicked
        settable();
        kodebobot.setEnabled(true);
    }//GEN-LAST:event_dsMouseClicked

    private void jdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeActionPerformed
        if("".equals(namasiswa.getSelectedItem())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            namasiswa.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "Apakah data ini ingin di hapus ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                hapusdata();
            }
        }
    }//GEN-LAST:event_jdeActionPerformed

    private void jedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jedActionPerformed
        if("".equals(kodebobot.getText())){
            JOptionPane.showMessageDialog(null, "Data Tidak ditemukan");
            kodebobot.requestFocus();
        }
        else{
            if(JOptionPane.showConfirmDialog(null, "Apakah data ini ingin di ubah ?", "peringatan",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION)
            {
                editdata();
            }
        }
    }//GEN-LAST:event_jedActionPerformed

    private void jsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsubActionPerformed
        if("".equals(kodebobot.getText()) || "".equals(pengetahuan.getSelectedItem()) || "".equals(keterampilan.getSelectedItem())
            || "".equals(usia.getSelectedItem())|| "".equals(karakter.getSelectedItem())|| "".equals(angket.getSelectedItem()) ||"".equals(namasiswa.getSelectedItem())){
            JOptionPane.showMessageDialog(null, "Lengkapi data dahulu");
            kodebobot.requestFocus();
        }else{
            simpan();
        }
    }//GEN-LAST:event_jsubActionPerformed

    private void jtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCariActionPerformed

    private void usiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usiaActionPerformed
        int i = usia.getSelectedIndex();
        if (i== 0){
            usia1.setText("");
            return;
        }else{
            try
            {
                String sql = "SELECT keterangan from tabelbobot WHERE idkriteria like 'K3%' AND bobot ='" + usia.getSelectedItem()+ "'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.usia1.setText(r.getString("keterangan"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_usiaActionPerformed

    private void karakterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_karakterActionPerformed
        int i = karakter.getSelectedIndex();
        if (i== 0){
            karakter1.setText("");
            return;
        }else{
            try
            {
                String sql = "SELECT keterangan from tabelbobot WHERE idkriteria like 'K4%' AND bobot ='" + karakter.getSelectedItem()+ "'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.karakter1.setText(r.getString("keterangan"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_karakterActionPerformed

    private void angket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angket1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_angket1ActionPerformed

    private void angketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angketActionPerformed
        // TODO add your handling code here:
        int i = angket.getSelectedIndex();
        if (i== 0){
            angket1.setText("");
            return;
        }else{
            try
            {
                String sql = "SELECT keterangan from tabelbobot WHERE idkriteria like 'K5%' AND bobot ='" + angket.getSelectedItem()+ "'";
                s =c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                this.angket1.setText(r.getString("keterangan"));

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }        
    }//GEN-LAST:event_angketActionPerformed

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
            java.util.logging.Logger.getLogger(pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pembobotan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pembobotan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> angket;
    private javax.swing.JTextField angket1;
    private javax.swing.JTable ds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jde;
    private javax.swing.JButton jed;
    private javax.swing.JButton jsub;
    private javax.swing.JTextField jtCari;
    private javax.swing.JButton k;
    private javax.swing.JComboBox<String> karakter;
    private javax.swing.JTextField karakter1;
    private javax.swing.JComboBox<String> keterampilan;
    private javax.swing.JTextField keterampilan1;
    private javax.swing.JTextField kodebobot;
    private javax.swing.JComboBox<String> namasiswa;
    private javax.swing.JComboBox<String> pengetahuan;
    private javax.swing.JTextField pengetahuan1;
    private javax.swing.JComboBox<String> usia;
    private javax.swing.JTextField usia1;
    // End of variables declaration//GEN-END:variables
}
