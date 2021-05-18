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


public class ForumAdmin extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel headertxt;

    private JLabel homel;
    private JLabel profilel;
    private JLabel labelhapus;
    private JButton buttonhapus;
    private JTextField hapus;

    private JPanel header;


    private ImageIcon bg;
    private ImageIcon home;

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane sp;

    public ForumAdmin(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //membuat jframe secara full screen
        Container container = getContentPane(); //memasukkan getContentPane() ke dalam container

        container.setLayout(null);

        //memasukkan gambar home
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

        //membuat text forum
        headertxt = new JLabel("<html><font color='white'>Forum Admin</font></html>");
        headertxt.setFont(new Font("Droid Sans",Font.BOLD,23));
        header.add(headertxt);

        //memasukkan background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //membuat text
        labelhapus = new JLabel("Masukkan id posting");
        labelhapus.setFont(new Font("Droid Sans",Font.BOLD,18));
        labelhapus.setBounds(345,100,200,30);

        hapus = new JTextField();
        hapus.setBounds(533,100,300,27);

        //membuat tabel forum
        table = new JTable();
        model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Id Posting");
        model.addColumn("Waktu Posting");
        model.addColumn("Nama");
        model.addColumn("Judul");
        model.addColumn("Isi");



        try {
            Statement stat = (Statement) Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan ke dalam database melalui class Koneksi
            String sql = "Select * from forum INNER JOIN pengguna on forum.id_pengguna = pengguna.id_pengguna"; //membuat query
            ResultSet rs = stat.executeQuery(sql); //eksekusi query

            while (rs.next()){
                Object[] baris = new Object[5];
                baris[0] = rs.getString("id_forum");
                baris[1] = rs.getString("waktu");
                baris[2] = rs.getString("nama");
                baris[3] = rs.getString("judul");
                baris[4] = rs.getString("isi");

                model.addRow(baris);
            }
        } catch (SQLException er) {
            JOptionPane.showMessageDialog(null,er.getMessage());
        }

        //membuat scroll pane untuk tabel forum diskusi
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
                    String sql = "DELETE from forum where id_forum='"+Hapus+"'"; //membuat query
                    PreparedStatement p = (PreparedStatement) Koneksi.getKoneksi().prepareStatement(sql); //eksekusi query
                    int count = p.executeUpdate();
                    if(count > 0){
                        //notif apabila berhasil
                        JOptionPane.showMessageDialog(null,"Berhasil menghapus postingan");
                        dispose();
                        new ForumAdmin().setVisible(true);
                    }else{
                        //notif apabila gagal
                        JOptionPane.showMessageDialog(null,"Gagal menghapus postingan");
                    }

                } catch (SQLException er) {
                    JOptionPane.showMessageDialog(null,er.getMessage());
                }
            }
        });

        //membuat semua variabel ke dalam container
        container.add(homel);
        container.add(header);
        container.add(labelhapus);
        container.add(buttonhapus);
        container.add(hapus);
        container.add(sp);
        container.add(label);


    }

    public static void main (String []args){
        ForumAdmin frame = new ForumAdmin(); //membuat objek frame supaya dapat ditampilkan
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
