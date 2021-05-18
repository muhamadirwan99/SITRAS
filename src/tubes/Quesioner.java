package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Quesioner extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel isi1;
    private JLabel isi2;
    private JLabel isi3;
    private JLabel isi4;
    private JLabel isi5;
    private JLabel isi6;

    private JLabel homel;
    private JLabel profilel;
    private JLabel sendl;

    private JTextField teks1;
    private JTextField teks2;
    private JTextField teks3;
    private JTextField teks4;
    private JTextField teks5;
    private JTextField teks6;

    private JPanel header;
    private JPanel isi;

    private ImageIcon bg;
    private ImageIcon home;
    private ImageIcon profile;
    private ImageIcon send;

    public Quesioner(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //mebuat jframe full screen
        Container container = getContentPane(); //memasukkan getContentPane() ke container

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

        //membuat icon profile
        profile = new ImageIcon(getClass().getResource("img/profile.png"));
        profilel = new JLabel(profile);
        profilel.setBounds(1296,8,50,50);
        //aksi apabila icon profile di klik
        profilel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new profile().setVisible(true);
            }
        });

        //membuat panel
        header = new JPanel(new FlowLayout(FlowLayout.CENTER,0,18));
        header.setBackground(Color.darkGray);
        header.setBounds(0,0,1366,70);

        //membuat text quisioner di panel
        headertxt = new JLabel("<html><font color='white'>Quesioner</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //membuat background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //membuat panel jenis gridlayout
        isi = new JPanel(new GridLayout(6,2,0,10));

        //membuat text pada panel gridlayout
        isi1 = new JLabel("Tahun lulus sekolah");
        isi1.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi2 = new JLabel("Jurusan yang dipilih");
        isi2.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi3 = new JLabel("Pekerjaan");
        isi3.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi4 = new JLabel("Gaji");
        isi4.setFont(new Font("Droid Sans",Font.PLAIN,16));

        isi5 = new JLabel("Alamat perusahaan");
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

        //memasukkan isi dan teks kedalam panel gridlayout
        isi.add(isi1);
        isi.add(teks1);
        isi.add(isi2);
        isi.add(teks2);
        isi.add(isi3);
        isi.add(teks3);
        isi.add(isi4);
        isi.add(teks4);
        isi.add(isi5);
        isi.add(teks5);
        isi.add(isi6);
        isi.add(teks6);

        isi.setBounds(433,125,500,220);
        isi.setOpaque(false);

        //membuat icon send
        send = new ImageIcon(getClass().getResource("img/send.png"));
        sendl = new JLabel(send);
        sendl.setBounds(870,402,60,60);
        //aksi apabila icon send di klik
        sendl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //inisialisasi dan untuk mendapatkan tahun, jurusan, pekerjaan, gaji, alamat, kerja, id_pengguna
                String tahun = teks1.getText().trim();
                String jurusan = teks2.getText().trim();
                String pekerjaan = teks3.getText().trim();
                String gaji = teks4.getText().trim();
                String alamat = teks5.getText().trim();
                String kerja = teks6.getText().trim();
                String id_pengguna = Session.id;

                try {
                    Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi

                    String sql = "INSERT INTO quesioner (id_quesioner,id_pengguna,tahun_lulus,jurusan,pekerjaan,Gaji,alamat_pekerjaan,lama_bekerja) VALUE(NULL,'"+id_pengguna+"','" +tahun+ "','"+jurusan+"', '" +pekerjaan+ "', '"+gaji+"', '"+alamat+"', '"+kerja+"')"; //membuat query
                    int rs = stat.executeUpdate(sql); //eksekusi query
                    boolean berhasil = true;
                    if (!tahun.equals("") && !jurusan.equals("") && !pekerjaan.equals("") && !gaji.equals("") && !alamat.equals("") && !kerja.equals("")) {
                        //notif apabila berhasil
                        JOptionPane.showMessageDialog(null,"Terima kasih, data yang anda masukkan sangat berharga");
                        dispose();
                        new Menu().setVisible(true);
                    } else {
                        //notif apabila gagal
                        JOptionPane.showMessageDialog(null, "Mohon isi form yang kosong");
                    }
                } catch (SQLException er) { //apabila tidak terkoneksi ke database
                    JOptionPane.showMessageDialog(null, "Terjadi Error : " + er.getMessage());
                }
            }
        });

        //memasukkan semua variabel ke container
        container.add(homel);
        container.add(profilel);
        container.add(header);
        container.add(isi);
        container.add(sendl);
        container.add(label);


    }

    public static void main (String []args){
        Quesioner frame = new Quesioner(); //membuat objek frame supaya dapat di tampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
