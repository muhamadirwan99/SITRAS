package tubes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataAlumniAdmin extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel homel;
    private JLabel profilel;

    private JPanel header;

    private JLabel labelhapus;
    private JButton buttonhapus;
    private JTextField hapus;

    private ImageIcon bg;
    private ImageIcon home;
    private ImageIcon profile;

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane sp;

    public DataAlumniAdmin(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //untuk membuat jframe full screen
        Container container = getContentPane(); //memasukkan getContentPane() ke container

        container.setLayout(null);

        //memasukkan gambar home dan membuat icon home
        home = new ImageIcon(getClass().getResource("img/home.png"));
        homel = new JLabel(home);
        homel.setBounds(20,8,50,50);
        //aksi apabila icon home di klik
        homel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MenuAdmin().setVisible(true);
            }
        });

        //membuat panel
        header = new JPanel(new FlowLayout(FlowLayout.CENTER,0,18));
        header.setBackground(Color.darkGray);
        header.setBounds(0,0,1366,70);

        //membuat text data alumni pada panel
        headertxt = new JLabel("<html><font color='white'>Data Alumni</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //memasukkan gambar background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //membuat text masukan id pengguna
        labelhapus = new JLabel("Masukkan id pengguna");
        labelhapus.setFont(new Font("Droid Sans",Font.BOLD,18));
        labelhapus.setBounds(320,100,200,30);

        //
        hapus = new JTextField();
        hapus.setBounds(533,100,300,27);

        //membuat tabel data alumni
        table = new JTable();
        model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Id Pengguna");
        model.addColumn("Username");
        model.addColumn("Email");
        model.addColumn("Nama");
        model.addColumn("No Telepon");
        model.addColumn("Email");
        model.addColumn("Tahun Lulus");
        model.addColumn("Jurusan");
        model.addColumn("Pekerjaan");

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksi kan dengan database melalui class Koneksi
            String sql = "Select * from quesioner INNER JOIN pengguna on quesioner.id_pengguna = pengguna.id_pengguna"; //membuat query
            ResultSet rs = stat.executeQuery(sql); //eksekusi query

            while (rs.next()){
                Object[] baris = new Object[9];
                baris[0] = rs.getString("id_pengguna");
                baris[1] = rs.getString("username");
                baris[2] = rs.getString("email");
                baris[3] = rs.getString("nama");
                baris[4] = rs.getString("notelepon");
                baris[5] = rs.getString("email");
                baris[6] = rs.getString("tahun_lulus");
                baris[7] = rs.getString("jurusan");
                baris[8] = rs.getString("pekerjaan");

                model.addRow(baris);
            }
        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null,er.getMessage());
        }

        //membuat scroll pane bila terdapat banyak data alumni
        sp = new JScrollPane(table);
        sp.setBounds(183, 150, 1000, 500);

        //membuat tombol hapus
        buttonhapus = new JButton("Hapus Postingan");
        buttonhapus.setBounds(845,100,160,27);

        //aksi apabila tombol hapus di klik
        buttonhapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Hapus = hapus.getText().trim();

                try {
                    Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); // untuk mengkoneksikan ke dalam database melalui class Koneksi
                    String sql = "DELETE from quesioner where id_pengguna='"+Hapus+"'"; //membuat query
                    PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql); // eksekusi query
                    p.executeUpdate();
                } catch (SQLException er) {
                    JOptionPane.showMessageDialog(null,er.getMessage());
                }

                try {
                    Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //ntuk mengkoneksikan ke dalam database melalui class Koneksi
                    String sql = "DELETE from pengguna where id_pengguna='"+Hapus+"'"; //membuat query
                    PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql); //eksekusi query
                    int count = p.executeUpdate();
                    if(count > 0){
                        //notif apabila berhasil menghapus data
                        JOptionPane.showMessageDialog(null,"Berhasil menghapus postingan");
                        dispose();
                        new ForumAdmin().setVisible(true);
                    }else{
                        //notif apabila gagal menghapus data
                        JOptionPane.showMessageDialog(null,"Gagal menghapus postingan");
                    }

                } catch (SQLException er) {
                    JOptionPane.showMessageDialog(null,er.getMessage());
                }
            }
        });

        //membuat semua variable ke dalam container
        container.add(homel);
        container.add(labelhapus);
        container.add(hapus);
        container.add(buttonhapus);
        container.add(header);
        container.add(sp);
        container.add(label);

    }



    public static void main (String []args){
        DataAlumniAdmin frame = new DataAlumniAdmin(); //membuat objek frame supayadapat di tampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}