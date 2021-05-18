package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProfileUpdate extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel isi1;
    private JLabel isi2;
    private JLabel isi3;
    private JLabel isi4;
    private JLabel isi5;
    private JLabel isi6;
    private JLabel isi7;

    private JLabel homel;
    private JLabel editl;
    private JLabel sendl;
    private JLabel fotol;

    private JTextField teks1;
    private JTextField teks2;
    private JTextField teks3;
    private JTextField teks4;
    private JTextField teks5;
    private JTextField teks6;
    private JTextField teks7;

    private JPanel header;
    private JPanel isi;

    private ImageIcon bg;
    private ImageIcon home;
    private ImageIcon edit;
    private ImageIcon send;
    private ImageIcon foto;

    private String id_pengguna;

    public ProfileUpdate(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //membuat jframe full screen
        Container container = getContentPane(); //memasukkan getContentPane () ke container

        container.setLayout(null);

        //membuat icon home
        home = new ImageIcon(getClass().getResource("img/home.png"));
        homel = new JLabel(home);
        homel.setBounds(20,8,50,50);
        //aksi apabila icon home di klik
        homel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Menu().setVisible(true);
            }
        });


        //membuat panel
        header = new JPanel(new FlowLayout(FlowLayout.CENTER,0,18));
        header.setBackground(Color.darkGray);
        header.setBounds(0,0,1366,70);

        //membuat text edit profile di panel
        headertxt = new JLabel("<html><font color='white'>Edit Profile</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //membuat background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //memasukkan gambar foto profile
        foto = new ImageIcon(getClass().getResource("img/ava.png"));



        //membuat panel jenis gridlayout
        isi = new JPanel(new GridLayout(7,2,0,10));

        isi1 = new JLabel("Nama");
        isi1.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi2 = new JLabel("Jurusan");
        isi2.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi3 = new JLabel("Lulus tahun");
        isi3.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi4 = new JLabel("Pekerjaan");
        isi4.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi7 = new JLabel("Gaji");
        isi7.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi5 = new JLabel("Alamat pekerjaan");
        isi5.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi6 = new JLabel("Lama bekerja");
        isi6.setFont(new Font("Droid Sans",Font.PLAIN,16));

        //membuat kotak kosong
        teks1 = new JTextField();

        teks2 = new JTextField();

        teks3 = new JTextField();

        teks4 = new JTextField();

        teks5 = new JTextField();

        teks6 = new JTextField();

        teks7 = new JTextField();

        //memasukkan isi dan text ke dalam panel isi
        isi.add(isi1);
        isi.add(teks1);
        isi.add(isi2);
        isi.add(teks2);
        isi.add(isi3);
        isi.add(teks3);
        isi.add(isi4);
        isi.add(teks4);
        isi.add(isi7);
        isi.add(teks7);
        isi.add(isi5);
        isi.add(teks5);
        isi.add(isi6);
        isi.add(teks6);

        isi.setBounds(433,250,500,220);
        isi.setOpaque(false);

        id_pengguna = Session.id;

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi
            String sql = "Select * from quesioner WHERE id_pengguna ='" + id_pengguna + "'"; //membuat query
            ResultSet rs = stat.executeQuery(sql); //eksekusi query

            while (rs.next()) {
                teks2.setText(rs.getString("jurusan"));
                teks3.setText(rs.getString("tahun_lulus"));
                teks4.setText(rs.getString("pekerjaan"));
                teks5.setText(rs.getString("alamat_pekerjaan"));
                teks6.setText(rs.getString("lama_bekerja"));
                teks7.setText(rs.getString("Gaji"));
            }

        } catch (SQLException er) { //apabila gagal terkoneksi ke database
            JOptionPane.showMessageDialog(null,er.getMessage());
        }
        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi
            String sql2 = "Select * from pengguna WHERE id_pengguna ='" + id_pengguna + "'"; //membuat query

            ResultSet rs2 = stat.executeQuery(sql2); //eksekusi query

            while (rs2.next()){
                teks1.setText(rs2.getString("nama"));
                //       foto = new ImageIcon(getClass().getResource(rs2.getString("foto")));
            }

        } catch (SQLException er) { //apabila gagal terkoneksi ke database
            JOptionPane.showMessageDialog(null,er.getMessage());
        }

        //membuat icon edit
        edit = new ImageIcon(getClass().getResource("img/done.png"));
        editl = new JLabel(edit);
        editl.setBounds(1296,8,50,50);
        //aksi apabila icon edit di klik
        editl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //inisialisasi dan untuk mendapatkan nama, jurusan, tahun, pekerjaan, alamat lama, gaji
                String getnama = teks1.getText().trim();
                String getjurusan = teks2.getText().trim();
                String gettahun = teks3.getText().trim();
                String getpekerjaan = teks4.getText().trim();
                String getalamat = teks5.getText().trim();
                String getlama = teks6.getText().trim();
                String getgaji = teks7.getText().trim();

                try{
                    Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi
                    String sql = "UPDATE pengguna SET nama='"+getnama+"'WHERE id_pengguna='"+id_pengguna+"'"; //membuat query
                    stat.executeUpdate(sql); //eksekusi query
                } catch (SQLException er) { //apabila gagal terkoneksi ke database
                    er.printStackTrace();
                }

                try{
                    Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi
                    String sql = "UPDATE quesioner SET tahun_lulus='"+gettahun+"', jurusan='"+getjurusan+"', pekerjaan='"+getpekerjaan+
                            "', Gaji='"+getgaji+"', alamat_pekerjaan='"+getalamat+"', lama_bekerja='"+getlama+"'WHERE id_pengguna='"+id_pengguna+"'"; //membuat query
                    stat.executeUpdate(sql); //eksekusi query
                    boolean berhasil = true;
                    if(berhasil){
                        //notif berhasil
                        JOptionPane.showMessageDialog(null,"Berhasil update data");
                        dispose();
                        new profile().setVisible(true);
                    }else {
                        //notif gagal
                        JOptionPane.showMessageDialog(null,"Gagal update data");
                    }
                } catch (SQLException er) { //apabila gagal terkoneksi ke database
                    er.printStackTrace();
                }



            }
        });

        fotol = new JLabel(foto);
        fotol.setBounds(633,100,100,100);

        //memasukkan semua variabel ke container
        container.add(homel);
        container.add(editl);
        container.add(header);
        container.add(isi);
        container.add(fotol);
        container.add(label);


    }

    public static void main (String []args){
        ProfileUpdate frame = new ProfileUpdate(); //membuat objek frame supaya dapat di tampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
