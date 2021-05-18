package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BuatDiskusi extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel homel;
    private JLabel profilel;
    private JLabel isi1;
    private JLabel isi2;

    private JPanel isi;

    private JTextField teks1;
    private JTextField teks2;

    private JPanel header;

    private ImageIcon bg;
    private ImageIcon home;
    private ImageIcon profile;

    private JButton post;

    public BuatDiskusi(){
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
                new Menu().setVisible(true);
            }
        });

        //memasukkan gambar profile
        profile = new ImageIcon(getClass().getResource("img/profile.png"));
        profilel = new JLabel(profile);
        profilel.setBounds(1296,8,50,50);

        //aksi apabila gambar profile di klik
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

        //membuat text forum pada panel
        headertxt = new JLabel("<html><font color='white'>Forum</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //memasukkan gambar background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);


        isi = new JPanel(new GridLayout(6,2,0,10));

        //membuat text judul
        isi1 = new JLabel("Judul");
        isi1.setFont(new Font("Droid Sans",Font.BOLD,22));

        //membuat text isi
        isi2 = new JLabel("Isi");
        isi2.setFont(new Font("Droid Sans",Font.BOLD,22));


        teks1 = new JTextField();
        teks2 = new JTextField();


        isi.add(isi1);
        isi.add(teks1);
        isi.add(isi2);
        isi.add(teks2);


        isi.setBounds(433,125,500,320);
        isi.setOpaque(false);

        //membuat tombol posting
        post = new JButton("Posting");
        post.setFont(new Font("Droid Sans",Font.BOLD,22));
        post.setBounds(433,400,500,50);

        //aksi apanila tombol post di klik
        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //inisialisasi dan untuk mendapatkan judul, isi, id_pengguna, tanggal
                String Judul = teks1.getText().trim();
                String Isi = teks2.getText().trim();
                String id_pengguna = Session.id;
                SimpleDateFormat tanggal = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date = new Date();
                JLabel tgl = new JLabel(tanggal.format(date));
                String Tanggal = tgl.getText().trim();
                try {
                    Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksi kan dengan database melalui class Koneksi

                    String sql = "INSERT INTO forum (id_forum,id_pengguna,judul,isi,waktu) VALUE(NULL,'" +id_pengguna+ "', '"+Judul+ "', '" +Isi+ "','"+Tanggal+"')"; //membuat query
                    int rs = stat.executeUpdate(sql); //eksekusi query
                    boolean berhasil = true;
                    if (berhasil) {
                        dispose();
                        new Forum().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, " ", "Pesan",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (SQLException er) {
                    //notif apabila gagal koneksi ke database
                    JOptionPane.showMessageDialog(null, "Terjadi Error : " + er.getMessage());
                }
            }
        });

        //membuat semua variable ke dalam container
        container.add(homel);
        container.add(profilel);
        container.add(header);
        container.add(isi);
        container.add(post);
        container.add(label);


    }

    public static void main (String []args){
        BuatDiskusi frame = new BuatDiskusi(); //membuat objek frame supaya dapat ditampilkan
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
