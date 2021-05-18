package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ForgotPassword extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel label2;
    private JLabel judul;

    private JTextField email;
    private String gett;
    private String emaill;

    private ImageIcon bg;
    private ImageIcon logo;

    private JButton update;
    private JButton cancel;

    public ForgotPassword(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //untuk membuat jframe full screen
        Container container = getContentPane(); //memasukkan getContentPane() ke container
        container.setLayout(null);

        //memasukkan gambar background
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //memasukkan logo
        logo = new ImageIcon(getClass().getResource("img/sitras.png"));
        label2 = new JLabel(logo);
        label2.setBounds(582,20,200,200);

        //membuat tulisan masukan email
        judul = new JLabel("Masukkan Email");
        judul.setBounds(532,220,300,20);
        judul.setFont(new Font("Droid Sans",Font.BOLD,20));

        //membuat textfield email
        email = new JTextField("Email");
        email.setBounds(532,250,300,30);

        //membuat placeholder pada textfield
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(email.getText().trim().equals("Email"))
                    email.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(email.getText().trim().equals(""))
                    email.setText("Email");
            }
        });

        //membuat tombol update
        update = new JButton("Update Password");
        update.setBounds(532,440,300,30);
        //aksi apabila tombol login di klik
        update.addActionListener(e -> {
            emaill = email.getText().trim();

            try{
                Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksi kan dengan database melalui class Koneksi
                String get = "Select * from pengguna WHERE email='"+emaill+"'"; //membuat query
                ResultSet rs = stat.executeQuery(get); //eksekusi query
                while (rs.next()){
                    gett = rs.getString("notelepon");
                }

            } catch (SQLException er) {
                //notif apabila gagal koneksi ke database
                JOptionPane.showMessageDialog(null, "Terjadi Error : " + er.getMessage());
            }

            try{
                Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksikan dengan database melalui class koneksi

                String sql = "UPDATE pengguna SET password='"+gett+"'WHERE email='"+emaill+"'"; //membuat query
                int rs = stat.executeUpdate(sql); //eksekusi query

                //memasukan data email
                if (!emaill.equals("") && !emaill.equals("Email")) {
                    //notif bila berhasil
                    JOptionPane.showMessageDialog(null,"Update password berhasil! \nPassword telah diubah menjadi no telepon \nSilahkan login!");
                    dispose();
                    new Main().setVisible(true);
                } else {
                    //notif bila gagal
                    JOptionPane.showMessageDialog(null, "Silahkan masukkan email anda");
                }

            } catch (SQLException er) {
                //notif apabila gagal koneksi ke database
                JOptionPane.showMessageDialog(null, "Terjadi Error : " + er.getMessage());
            }
        });

        //membuat tombol cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(532,480,300,30);
        //aksi apabila cancel di klik
        cancel.addActionListener(e -> {
            dispose();
            new Main().setVisible(true);
        });

        //memasukkan semua variabel ke dalam container
        container.add(label2);
        container.add(email);
        container.add(judul);
        container.add(update);
        container.add(cancel);
        container.add(label);


    }

    public static void main (String []args){
        ForgotPassword frame = new ForgotPassword(); //membuat objek frame supaya dapat ditampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
