package tubes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataAlumni extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel homel;
    private JLabel profilel;

    private JPanel header;

    private ImageIcon bg;
    private ImageIcon home;
    private ImageIcon profile;

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane sp;

    public DataAlumni(){
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

        //memasukkan gambar profile dan membuaticon profile
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

        //membuat text data alumni pada panel
        headertxt = new JLabel("<html><font color='white'>Data Alumni</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //memasukkan gambar background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //membuat tabel berisi nama, no telepon, email, tahun lulus, jurusan, pekerjaan
        table = new JTable();
        model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Nama");
        model.addColumn("No Telepon");
        model.addColumn("Email");
        model.addColumn("Tahun Lulus");
        model.addColumn("Jurusan");
        model.addColumn("Pekerjaan");

        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan dengan database melalui class koneksi
            String sql = "Select * from quesioner INNER JOIN pengguna on quesioner.id_pengguna = pengguna.id_pengguna"; //membuat query
            ResultSet rs = stat.executeQuery(sql); //eksekusi query

            while (rs.next()){
                Object[] baris = new Object[6];
                baris[0] = rs.getString("nama");
                baris[1] = rs.getString("notelepon");
                baris[2] = rs.getString("email");
                baris[3] = rs.getString("tahun_lulus");
                baris[4] = rs.getString("jurusan");
                baris[5] = rs.getString("pekerjaan");

                model.addRow(baris);
            }
        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null,er.getMessage());
        }


        sp = new JScrollPane(table);
        sp.setBounds(183, 150, 1000, 500);


        //membuat semua variable ke dalam container
        container.add(homel);
        container.add(profilel);
        container.add(header);
        container.add(sp);
        container.add(label);


    }


    public static void main (String []args){
        DataAlumni frame = new DataAlumni(); //membuat objek frame supaya dapat ditampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}