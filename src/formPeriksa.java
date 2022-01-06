
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bona
 */
public class formPeriksa extends javax.swing.JFrame {
private Connection con;
private Statement st;
private ResultSet RsPasien;
private ResultSet RsDoktor;
private ResultSet RsPeriksa;
private ResultSet RsDetail;


private ResultSet RsObat;
private String sql="";

private String no, kodepas, namapas, 
        kk, alamat, nohp, gejala, kodedok, kodeobat, namadok, keahlian, penyakit;
private int hargaobat, jumlahobat,  jumlahitem, jumlahtotal, bayar, kembali;
    /**
     * Creates new form formPeriksa
     */
    public formPeriksa() {
        initComponents();
        koneksitabel();
        DaftarDoktor();
        DaftarPasien();
        DaftarObat();
        tampildata("SELECT * FROM tabel_periksa");
        tampildetailpemeriksaan("select * from tabel_detailpemeriksaan");

    }
    
    private void form_awal(){
        form_disable();
        form_clear(); 
        Btn_Simpan.setText("Simpan");
        Btn_Tambah.setEnabled(true);
        Btn_Simpan.setEnabled(false);
        
    }
  private void form_disable(){
        Txt_namapasien.setEnabled(false);
        Txt_alamatpasien.setEnabled(false);
        Txt_nohp.setEnabled(false);
       Txt_gejala.setEnabled(false);
        Txt_keahlian.setEnabled(false);
       Txt_penyakit.setEnabled(false);
       Txt_namadoktor.setEnabled(false);
       Txt_namaobat.setEnabled(false);
       Txt_jenisobat.setEnabled(false);
       Txt_hargaobat.setEnabled(false);
       Txt_totalharga.setEnabled(false);
        Cmb_kodedoktor.setEnabled(false);
       Cmb_kodepasien.setEnabled(false);
       Cmb_kodeobat.setEnabled(false);
       Rb1.setEnabled(false);
       Rb2.setEnabled(false);
       Rb3.setEnabled(false);
               
  }
  private void form_enable(){
      Txt_no.setEnabled(true);
      Txt_totalharga.setEnabled(true);
      Txt_jumlahobat.setEnabled(true);
        Txt_namapasien.setEnabled(true);
        Txt_alamatpasien.setEnabled(true);
        Txt_nohp.setEnabled(true);
       Txt_gejala.setEnabled(true);
        Txt_keahlian.setEnabled(true);
       Txt_penyakit.setEnabled(true);
       Txt_namadoktor.setEnabled(true);
       Txt_namaobat.setEnabled(true);
       Txt_jenisobat.setEnabled(true);
       Txt_hargaobat.setEnabled(true);
        Cmb_kodedoktor.setEnabled(true);
       Cmb_kodepasien.setEnabled(true);
       Cmb_kodeobat.setEnabled(true);
       Rb1.setEnabled(true);
       Rb2.setEnabled(true);
       Rb3.setEnabled(true);
  }
      private void form_clear(){
             Txt_no.setText("");
        Txt_namapasien.setText("");
        Txt_alamatpasien.setText("");
        Txt_nohp.setText("");
       Txt_gejala.setText("");
        Txt_keahlian.setText("");
       Txt_penyakit.setText("");
       Txt_namadoktor.setText("");
       Txt_totalharga.setText("");
       Txt_namaobat.setText("");
       Txt_hargaobat.setText("");
       Txt_jenisobat.setText("");
       
       Txt_jumlahbayar.setText("");
       Txt_jumlahitem.setText("");
       Txt_jumlahobat.setText("");
       Txt_jumlahtotal.setText("");
       Txt_kembali.setText("");
         Cmb_kodedoktor.setSelectedItem("Pilih");
         Cmb_kodepasien.setSelectedItem("Pilih");
         Cmb_kodeobat.setSelectedItem("Pilih");
      }
        private void aksi_tambah(){
        form_enable();
        Btn_Tambah.setEnabled(true);
        
        Btn_Kurang.setEnabled(true);
        Btn_Simpan.setEnabled(true);
        Btn_Batal.setEnabled(true);
        
        Txt_no.requestFocus(true);
        }
    private void koneksitabel (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost:3306/db_rumahsakit", "root", "");
            System.out.println("KONEKSI BERHASIL");
            JOptionPane.showMessageDialog(null, "SELAMAT DATANG");
            
        } catch (Exception e) {
            System.out.println("KONEKSI GAGAL \n"+e);
        }
    }
    private void tampildata(String sql){
        DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("Nomor Periksa");
        datalist.addColumn("Tanggal Periksa");
        datalist.addColumn("Kode Doktor");
        datalist.addColumn("Kode Pasien");
        datalist.addColumn("Gejala");
        datalist.addColumn("Penyakit");
        datalist.addColumn("Jumlah Item");
        datalist.addColumn("Jumlah Total");
        datalist.addColumn("Bayar");
        datalist.addColumn("Kembali");
        try {
            int i = 1;
            st=con.createStatement();
            RsPeriksa=st.executeQuery("SELECT * FROM tabel_periksa");
            while (RsPeriksa.next()){
                datalist.addRow(new Object[]{
                    (""+i++),RsPeriksa.getString(1), RsPeriksa.getString(2), 
                    RsPeriksa.getString(3), RsPeriksa.getString(4), RsPeriksa.getString(5), 
                    RsPeriksa.getString(6), RsPeriksa.getString(8), 
                    RsPeriksa.getString(9), RsPeriksa.getString(10), RsPeriksa.getString(11)
                });
                Gridperiksa.setModel(datalist);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "GAGAL TAMPIL \n"+e.getMessage());
        }
        
    }
        private void DaftarObat(){
        Cmb_kodeobat.removeAllItems();
        Cmb_kodeobat.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_obat";
            Statement st=con.createStatement();
            RsObat=st.executeQuery(sql);
            while(RsObat.next()){
                String Alliasob=RsObat.getString("Kode_obat");
                Cmb_kodeobat.addItem(Alliasob);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode Obat \n"
                +e.getMessage());
        }}
    private void DaftarPasien(){
        Cmb_kodepasien.removeAllItems();
        Cmb_kodepasien.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_pasien";
            Statement st=con.createStatement();
            RsPasien=st.executeQuery(sql);
            while(RsPasien.next()){
                String Alliasps=RsPasien.getString("kode_pasien");
                Cmb_kodepasien.addItem(Alliasps);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode pasien \n"
                +e.getMessage());
        }
    }private void DaftarDoktor(){
        Cmb_kodedoktor.removeAllItems();
        Cmb_kodedoktor.addItem("Pilih");
        try {
            String sql ="Select * FROM tabel_doktor";
            Statement st=con.createStatement();
            RsDoktor=st.executeQuery(sql);
            while(RsDoktor.next()){
                String AlliasD=RsDoktor.getString("kode_doktor");
                Cmb_kodedoktor.addItem(AlliasD);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode Doktor \n"
                +e.getMessage());
        }
    }
    private void prosestambah(){
    try {
        DefaultTableModel tableModel = (DefaultTableModel)Gridobat.getModel();
        String[]data = new String[6];
        data[0] = String.valueOf(Cmb_kodeobat.getSelectedItem());
        data[1] = Txt_namaobat.getText();
        data[2] = Txt_hargaobat.getText();
        data[3] = Txt_jumlahobat.getText();
        data[4] = Txt_totalharga.getText();
        tableModel.addRow(data);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Memasukkan Data \n" +e.getMessage());
    }
}
        private void total(){
        int jumlahBaris = Gridobat.getRowCount();
        int jumlahtotal =0, jumlahitem=0;
        int jumlahjual, totalharga;
        
        TableModel tblmodel;
        tblmodel = Gridobat.getModel();
        for (int i=0; i<jumlahBaris; i++){
            jumlahjual = Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
            jumlahitem=jumlahitem+jumlahjual;
            totalharga = Integer.parseInt(tblmodel.getValueAt(i, 4).toString());
            jumlahtotal=jumlahtotal+totalharga;}
           
        Txt_jumlahtotal.setText(String.valueOf(jumlahtotal));
        Txt_jumlahitem.setText(String.valueOf(jumlahitem));
        
    }
        private void simpandetail(){
            int jumlah_baris = Gridobat.getRowCount();
            if(jumlah_baris == 0){
                JOptionPane.showMessageDialog(rootPane, "Tabel Masih Kosong!");
            }else{
                try {
                    int i=0;
                    while(i < jumlah_baris){
                        st.executeUpdate("insert into tabel_detailpemeriksaan"
                        + "(No_Periksa,Kode_obat,nama_obat,harga_obatt,jumlah_obat) "
                        + "values('"+Txt_no.getText() +"', "
                        + "'"+Gridobat.getValueAt(i, 0)+"',"
                        + "'"+Gridobat.getValueAt(i, 1)+"',"
                        + "'"+Gridobat.getValueAt(i, 2)+"',"
                        + "'"+Gridobat.getValueAt(i, 3)+"')");
                    try {
                        sql="SELECT * FROM tabel_obat WHERE "
                                + "Kode_obat='" + Gridobat.getValueAt(i, 0) +"'";
                        st=con.createStatement();
                        RsObat=st.executeQuery(sql);
                        while(RsObat.next()){
                            try {
                                /*sql="UPDATE tabel_barang "
                                    + "SET Stok ='"+ RsBrg.getString(6) - GridBarang.getValueAt(i, 4) +"' "
                                    + "WHERE Kode_Barang='" + GridBarang.getValueAt(i, 0) +"'";
                                */
                            st=con.createStatement();
                            st.execute(sql);
                            
                            } catch (Exception err) {
                                JOptionPane.showConfirmDialog(null, "Tidak Ada Barang Update!\n"+err.getMessage());
                                
                            }
                        }
                        } catch (Exception se) {
                                JOptionPane.showConfirmDialog(null, "Data Tiidak Ditemukan!!\n"+se.getMessage());
                                Txt_namapasien.requestFocus();
                                }
                    i++;
                        
                    } //JOptionPane.showMessageDialog(rootPane, "Berhasil Disimpan!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan ! ERROR : \n"+e);
                }
            }
        }
 private void tampildetailpemeriksaan(String sql){
  DefaultTableModel datalist = new DefaultTableModel();
        datalist.addColumn("No");
        datalist.addColumn("No Periksa");
        datalist.addColumn("Kode Obat");
        datalist.addColumn("Nama Obat");
        datalist.addColumn("Harga Obat");
        datalist.addColumn("Jumlah Obat");
        try {
            int i = 1;
            st=con.createStatement();
            RsDetail=st.executeQuery("SELECT * FROM tabel_detailpemeriksaan where No_Periksa='"+ no +"'");
            while (RsDetail.next()){
                datalist.addRow(new Object[]{
                    (""+i++),RsDetail.getString(1), RsDetail.getString(2), 
                    RsDetail.getString(3), RsDetail.getString(4), RsDetail.getString(5), 
                   
                });
                Gridobat.setModel(datalist);
        
        Btn_Kurang.setEnabled(false);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "GAGAL TAMPIL \n"+e.getMessage());
        }
          
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Txt_no = new javax.swing.JTextField();
        Txt_namapasien = new javax.swing.JTextField();
        Txt_alamatpasien = new javax.swing.JTextField();
        Tanggal_periksa = new com.toedter.calendar.JDateChooser();
        Cmb_kodepasien = new javax.swing.JComboBox<>();
        Btn_Simpan = new javax.swing.JButton();
        Btn_Batal = new javax.swing.JButton();
        Btn_Keluar = new javax.swing.JButton();
        Btn_Tambah = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Txt_nohp = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Txt_namadoktor = new javax.swing.JTextField();
        Cmb_kodedoktor = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Rb3 = new javax.swing.JRadioButton();
        Rb2 = new javax.swing.JRadioButton();
        Rb1 = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        Cmb_periksa = new javax.swing.JButton();
        Txt_keahlian = new javax.swing.JTextField();
        Txt_penyakit = new javax.swing.JTextField();
        Txt_gejala = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Gridobat = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Cmb_kodeobat = new javax.swing.JComboBox<>();
        Txt_namaobat = new javax.swing.JTextField();
        Txt_jenisobat = new javax.swing.JTextField();
        Btn_Kurang = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Txt_jumlahitem = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        Txt_jumlahobat = new javax.swing.JTextField();
        Txt_jumlahtotal = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Txt_jumlahbayar = new javax.swing.JTextField();
        Txt_kembali = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        Txt_hargaobat = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Gridperiksa = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        Txt_totalharga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 970, 10));

        jLabel7.setText("No Periksa");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel8.setText("Tanggal Periksa");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jLabel9.setText("Nama Pasien");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel10.setText("Kode Doktor");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jLabel11.setText("No Handphone");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        Txt_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Txt_noKeyPressed(evt);
            }
        });
        jPanel1.add(Txt_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 160, 120, -1));
        jPanel1.add(Txt_namapasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 120, -1));

        Txt_alamatpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_alamatpasienActionPerformed(evt);
            }
        });
        jPanel1.add(Txt_alamatpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 120, -1));
        jPanel1.add(Tanggal_periksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 120, -1));

        Cmb_kodepasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Laki-laki", "Perempuan" }));
        Cmb_kodepasien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cmb_kodepasienItemStateChanged(evt);
            }
        });
        jPanel1.add(Cmb_kodepasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 120, -1));

        Btn_Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/export-16x16.png"))); // NOI18N
        Btn_Simpan.setText("SIMPAN");
        Btn_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SimpanActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, -1, -1));

        Btn_Batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/refresh-16x16.png"))); // NOI18N
        Btn_Batal.setText("BATAL");
        Btn_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BatalActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 90, -1));

        Btn_Keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/forward-16x16.png"))); // NOI18N
        Btn_Keluar.setText("KELUAR");
        Btn_Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_KeluarActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, 90, -1));

        Btn_Tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/add-16x16.png"))); // NOI18N
        Btn_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TambahActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 30, -1));

        jLabel12.setText("Alamat Pasien");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));
        jPanel1.add(Txt_nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 120, -1));

        jLabel13.setText("Kode Pasien");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));
        jPanel1.add(Txt_namadoktor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 110, -1));

        Cmb_kodedoktor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        Cmb_kodedoktor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cmb_kodedoktorItemStateChanged(evt);
            }
        });
        jPanel1.add(Cmb_kodedoktor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 110, -1));

        jLabel14.setText("Nama Doktor");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        jLabel15.setText("Keahlian");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        jLabel16.setText("Indikasi");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jLabel17.setText("Gejala");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        Rb3.setBackground(new java.awt.Color(255, 255, 153));
        Rb3.setText("Hidung Tersumbat");
        Rb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb3ActionPerformed(evt);
            }
        });
        jPanel1.add(Rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));

        Rb2.setBackground(new java.awt.Color(255, 255, 153));
        Rb2.setText("Badan Pegal");
        Rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb2ActionPerformed(evt);
            }
        });
        jPanel1.add(Rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 120, -1));

        Rb1.setBackground(new java.awt.Color(255, 255, 153));
        Rb1.setText("Kepala Berdenyut");
        Rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb1ActionPerformed(evt);
            }
        });
        jPanel1.add(Rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        jLabel18.setText("Penyakit");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, -1));

        Cmb_periksa.setText("PERIKSA");
        Cmb_periksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cmb_periksaActionPerformed(evt);
            }
        });
        jPanel1.add(Cmb_periksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, -1, -1));
        jPanel1.add(Txt_keahlian, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 110, -1));
        jPanel1.add(Txt_penyakit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 106, -1));
        jPanel1.add(Txt_gejala, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 110, -1));

        Gridobat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Harga Obat", "Jumlah Obat", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Gridobat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 460, 140));

        jLabel19.setText("Kode Obat");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, -1));

        jLabel20.setText("Nama Obat");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

        jLabel21.setText("Jenis Obat");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, -1, -1));

        Cmb_kodeobat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        Cmb_kodeobat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cmb_kodeobatItemStateChanged(evt);
            }
        });
        jPanel1.add(Cmb_kodeobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 120, -1));
        jPanel1.add(Txt_namaobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 120, -1));
        jPanel1.add(Txt_jenisobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 120, -1));

        Btn_Kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/delete-16x16.png"))); // NOI18N
        Btn_Kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_KurangActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Kurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, 30, -1));

        jLabel22.setText("Jumlah Item Obat");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, -1, -1));
        jPanel1.add(Txt_jumlahitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 80, -1));

        jLabel23.setText("Jumlah Obat yg dikenakan");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 130, 20));

        Txt_jumlahobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_jumlahobatActionPerformed(evt);
            }
        });
        jPanel1.add(Txt_jumlahobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, 80, -1));

        Txt_jumlahtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(Txt_jumlahtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 80, -1));

        jLabel24.setText("Jumlah Total Biaya");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, -1, -1));

        jLabel25.setText("Jumlah Bayar");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, -1, -1));

        jLabel26.setText("Kembali");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, -1, -1));

        Txt_jumlahbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_jumlahbayarActionPerformed(evt);
            }
        });
        jPanel1.add(Txt_jumlahbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 500, 80, -1));
        jPanel1.add(Txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 530, 80, -1));

        jLabel27.setText("Harga Obat");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, -1, -1));
        jPanel1.add(Txt_hargaobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 120, -1));

        Gridperiksa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(Gridperiksa);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 950, 110));

        jLabel28.setText("Total Harga Obat");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, -1, -1));

        Txt_totalharga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(Txt_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, 80, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/home.png"))); // NOI18N
        jButton1.setText("Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 120, 60));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        jLabel3.setText("RS CIBITUNG MEDIKA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 510, 40));

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel4.setText("Siap Melayani Anda 24 Jam");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 176, 12));

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel5.setText("Jalan Boshi Raya no 46");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel6.setText("No Telp 0897 20022123");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/rs.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/periksa.png"))); // NOI18N
        jLabel1.setText("FORM PERIKSA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Txt_noKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_noKeyPressed
        // TODO add your handling code here:
        no=Txt_no.getText();
        int tekanenter=evt.getKeyCode();
        if (tekanenter==10){
            try {
                sql="Select * from tabel_periksa "
                + "where No_Periksa='"+ no +"'";
                st=con.createStatement();
                RsPeriksa=st.executeQuery(sql);
                while (RsPeriksa.next()) {
                    Tanggal_periksa.setDate(RsPeriksa.getDate("tgl_periksa"));
                    Cmb_kodedoktor.setSelectedItem(RsPeriksa.getString("kode_doktor"));
                    Cmb_kodepasien.setSelectedItem(RsPeriksa.getString("kode_pasien"));
                    Txt_gejala.setText(RsPeriksa.getString("gejala"));
                    Txt_penyakit.setText(RsPeriksa.getString("penyakit"));
                    Cmb_kodeobat.setSelectedItem(RsPeriksa.getString("Kode_obat"));
                    Txt_jumlahitem.setText(RsPeriksa.getString("jumlah_item"));
                    Txt_jumlahtotal.setText(RsPeriksa.getString("jumlah_total"));
                    Txt_jumlahbayar.setText(RsPeriksa.getString("jumlah_bayar"));
                    Txt_kembali.setText(RsPeriksa.getString("kembali"));
                    tampildetailpemeriksaan("select * from tabel_detailpemeriksaan");
                    JOptionPane.showMessageDialog(null,
                        "Data Ditemukan");
                    Btn_Tambah.setEnabled(false);
                    Btn_Simpan.setEnabled(false);
                    Rb1.setEnabled(false);
                    Rb2.setEnabled(false);
                    Rb3.setEnabled(false);
                    Txt_jumlahobat.setEnabled(false);
                    Txt_totalharga.setEnabled(false);
                    form_disable();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan \n"+e.getMessage());
                Txt_no.requestFocus();
            }
        }
    }//GEN-LAST:event_Txt_noKeyPressed

    private void Btn_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SimpanActionPerformed
        // TODO add your handling code here:
        String Tanggal, gejala, penyakit;
        int totalItem, totalobat, bayar, kembali, totalbayar;
        
        no=String.valueOf(Txt_no.getText());
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        Tanggal=format.format(Tanggal_periksa.getDate());
        kodepas=Cmb_kodepasien.getItemAt(Cmb_kodepasien.getSelectedIndex()).toString();
        kodedok=Cmb_kodedoktor.getItemAt(Cmb_kodedoktor.getSelectedIndex()).toString();
        kodeobat=Cmb_kodeobat.getItemAt(Cmb_kodeobat.getSelectedIndex()).toString();
        gejala=String.valueOf(Txt_gejala.getText());
        penyakit=String.valueOf(Txt_penyakit.getText());
        
        totalItem=Integer.parseInt(Txt_jumlahitem.getText());
        totalobat=Integer.parseInt(Txt_jumlahobat.getText());
        bayar=Integer.parseInt(Txt_jumlahbayar.getText());
        kembali=Integer.parseInt(Txt_kembali.getText());
        totalbayar=Integer.parseInt(Txt_jumlahtotal.getText());
        simpandetail();
        try {
            sql="INSERT INTO tabel_periksa(No_Periksa, "
            + "tgl_periksa, "
            + "kode_doktor, "
            + "kode_pasien, "
            + "gejala, "
            + "penyakit, "
            + "Kode_obat, "
            + "jumlah_item, "
            + "jumlah_total, "
            + "jumlah_bayar, "
            + "kembali)VALUES"
            + "('"+ no +"',"
            + "'"+ Tanggal +"',"
            + "'"+ kodedok +"',"
            + "'"+ kodepas +"',"
            + "'"+ gejala +"',"
            + "'"+ penyakit +"',"
            + "'"+ kodeobat +"',"
            + "'"+ totalItem +"',"
            + "'"+ totalbayar +"',"
            + "'"+ bayar +"',"
            + "'"+ kembali +"')";
            st=con.createStatement();
            st.execute(sql);
            tampildata("Select * from tabel_periksa");
            form_awal();
            JOptionPane.showMessageDialog(null,
                "Data Tersimpan");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan \n"+e.getMessage());
        }
    }//GEN-LAST:event_Btn_SimpanActionPerformed

    private void Btn_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BatalActionPerformed
        // TODO add your handling code here:
        form_clear();
        form_enable();
        Btn_Tambah.setEnabled(true);
        Btn_Simpan.setEnabled(true);
        Btn_Kurang.setEnabled(true);
        Txt_no.requestFocus();
        Txt_no.setEnabled(true);
        Rb1.setEnabled(true);
        Rb3.setEnabled(true);
        Rb2.setEnabled(true);

    }//GEN-LAST:event_Btn_BatalActionPerformed

    private void Btn_KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_KeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_Btn_KeluarActionPerformed

    private void Btn_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TambahActionPerformed
        // TODO add your handling code here:
        prosestambah();
        total();
    }//GEN-LAST:event_Btn_TambahActionPerformed

    private void Cmb_kodepasienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cmb_kodepasienItemStateChanged
        // TODO add your handling code here:
         try {
            sql="Select * FROM tabel_pasien WHERE "
                    + "kode_pasien='" + Cmb_kodepasien.getSelectedItem() +"'";
            st=con.createStatement();
            RsPasien=st.executeQuery(sql);
                while(RsPasien.next()){
                    Txt_namapasien.setText(RsPasien.getString("nama_pasien"));
                     Txt_alamatpasien.setText(RsPasien.getString("alamat_pasien"));
                     Txt_nohp.setText(RsPasien.getString("handphone"));
                                   
                }
                
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail Pelanggan \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_Cmb_kodepasienItemStateChanged

    private void Txt_alamatpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_alamatpasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_alamatpasienActionPerformed

    private void Cmb_kodedoktorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cmb_kodedoktorItemStateChanged
        // TODO add your handling code here:
        try {
            sql="Select * FROM tabel_doktor WHERE "
                    + "kode_doktor='" + Cmb_kodedoktor.getSelectedItem() +"'";
            st=con.createStatement();
            RsDoktor=st.executeQuery(sql);
                while(RsDoktor.next()){
                    Txt_namadoktor.setText(RsDoktor.getString("nama_dokter"));
                     Txt_keahlian.setText(RsDoktor.getString("keahlian"));
                                   
                }
                
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail doktor \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_Cmb_kodedoktorItemStateChanged

    private void Rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb1ActionPerformed
        // TODO add your handling code here:
        if ((Rb1.isSelected())) {
            gejala="Gejala1";
           Rb2.setSelected(false);
           Rb3.setSelected(false);
           Txt_penyakit.setText("");
            }
                   Txt_gejala.setText(String.valueOf(gejala));
    }//GEN-LAST:event_Rb1ActionPerformed

    private void Cmb_periksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cmb_periksaActionPerformed
        // TODO add your handling code here:
        if ((Rb1.isSelected())) {
            penyakit="tipus";}
            if ((Rb2.isSelected())) {
            penyakit="demam";}
            if ((Rb3.isSelected())) {
            penyakit="pilek";
            
            
            }
                   Txt_penyakit.setText(String.valueOf(penyakit));
    }//GEN-LAST:event_Cmb_periksaActionPerformed

    private void Rb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb2ActionPerformed
        // TODO add your handling code here:
        if ((Rb2.isSelected())) {
            gejala="Gejala2";
           Rb1.setSelected(false);
           Rb3.setSelected(false);
           Txt_penyakit.setText("");
            }
                   Txt_gejala.setText(String.valueOf(gejala));
    }//GEN-LAST:event_Rb2ActionPerformed

    private void Rb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb3ActionPerformed
        // TODO add your handling code here:
        if ((Rb3.isSelected())) {
            gejala="Gejala3";
           Rb1.setSelected(false);
           Rb2.setSelected(false);
           Txt_penyakit.setText("");
            }
                   Txt_gejala.setText(String.valueOf(gejala));
    }//GEN-LAST:event_Rb3ActionPerformed

    private void Cmb_kodeobatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cmb_kodeobatItemStateChanged
        // TODO add your handling code here:
         try {
            sql="Select * FROM tabel_obat WHERE "
                    + "Kode_obat='" + Cmb_kodeobat.getSelectedItem() +"'";
            st=con.createStatement();
            RsObat=st.executeQuery(sql);
                while(RsObat.next()){
                    Txt_namaobat.setText(RsObat.getString("nama_obat"));
                     Txt_jenisobat.setText(RsObat.getString("jenis_obat"));
                     Txt_hargaobat.setText(RsObat.getString("harga_obatt"));
                                   
                }
                
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail Pelanggan \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_Cmb_kodeobatItemStateChanged

    private void Btn_KurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_KurangActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Gridobat.getModel();
        int row = Gridobat.getSelectedRow();
            if(row>=0){
                int oke=JOptionPane.showConfirmDialog(null, 
                        "Yakin Mau Hapus?","Konfirmasi",
                        JOptionPane.YES_NO_OPTION);
                if(oke==0){
                    model.removeRow(row);
                }
            }
    }//GEN-LAST:event_Btn_KurangActionPerformed

    private void Txt_jumlahobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_jumlahobatActionPerformed
        // TODO add your handling code here:
        hargaobat=Integer.parseInt(Txt_hargaobat.getText());
        jumlahobat=Integer.parseInt(Txt_jumlahobat.getText());
        jumlahtotal=hargaobat*jumlahobat;
        Txt_totalharga.setText(String.valueOf(jumlahtotal));
    }//GEN-LAST:event_Txt_jumlahobatActionPerformed

    private void Txt_jumlahbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_jumlahbayarActionPerformed
        // TODO add your handling code here:
        jumlahtotal=Integer.parseInt(Txt_jumlahtotal.getText());
        bayar=Integer.parseInt(Txt_jumlahbayar.getText());
        kembali=bayar-jumlahtotal;
        Txt_kembali.setText(String.valueOf(kembali));
    }//GEN-LAST:event_Txt_jumlahbayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new menu().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(formPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPeriksa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Batal;
    private javax.swing.JButton Btn_Keluar;
    private javax.swing.JButton Btn_Kurang;
    private javax.swing.JButton Btn_Simpan;
    private javax.swing.JButton Btn_Tambah;
    private javax.swing.JComboBox<String> Cmb_kodedoktor;
    private javax.swing.JComboBox<String> Cmb_kodeobat;
    private javax.swing.JComboBox<String> Cmb_kodepasien;
    private javax.swing.JButton Cmb_periksa;
    private javax.swing.JTable Gridobat;
    private javax.swing.JTable Gridperiksa;
    private javax.swing.JRadioButton Rb1;
    private javax.swing.JRadioButton Rb2;
    private javax.swing.JRadioButton Rb3;
    private com.toedter.calendar.JDateChooser Tanggal_periksa;
    private javax.swing.JTextField Txt_alamatpasien;
    private javax.swing.JTextField Txt_gejala;
    private javax.swing.JTextField Txt_hargaobat;
    private javax.swing.JTextField Txt_jenisobat;
    private javax.swing.JTextField Txt_jumlahbayar;
    private javax.swing.JTextField Txt_jumlahitem;
    private javax.swing.JTextField Txt_jumlahobat;
    private javax.swing.JTextField Txt_jumlahtotal;
    private javax.swing.JTextField Txt_keahlian;
    private javax.swing.JTextField Txt_kembali;
    private javax.swing.JTextField Txt_namadoktor;
    private javax.swing.JTextField Txt_namaobat;
    private javax.swing.JTextField Txt_namapasien;
    private javax.swing.JTextField Txt_no;
    private javax.swing.JTextField Txt_nohp;
    private javax.swing.JTextField Txt_penyakit;
    private javax.swing.JTextField Txt_totalharga;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
