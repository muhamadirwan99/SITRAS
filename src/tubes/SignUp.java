package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;


public class SignUp extends JFrame {

    //deklarasi variabel

    private JLabel label;
    private JLabel label2;

    private JTextField nama;
    private JTextField username;
    private JPasswordField password;
    private JTextField email;
    private JTextField notlp;

    private ImageIcon bg;
    private ImageIcon logo;
    private ImageIcon iconnama;
    private ImageIcon iconuser;
    private ImageIcon iconpw;
    private ImageIcon iconemail;
    private ImageIcon icontelepon;

    private JLabel labelnama;
    private JLabel labeluser;
    private JLabel labelpw;
    private JLabel labelemail;
    private JLabel labeltelepon;


    private JButton botsignup;
    private JButton cancel;


    public SignUp(){
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

        //memasukkan icon nama
        iconnama = new ImageIcon(getClass().getResource("img/nama.png"));
        labelnama = new JLabel(iconnama);
        labelnama.setBounds(500,200,27,27);

        //membuat textfield nama
        nama = new JTextField("Nama");
        nama.setBounds(532,200,300,27);
        nama.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(nama.getText().trim().equals("Nama"))
                    nama.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nama.getText().trim().equals(""))
                    nama.setText("Nama");
            }
        });

        //memasukkan icon user
        iconuser = new ImageIcon(getClass().getResource("img/user.png"));
        labeluser = new JLabel(iconuser);
        labeluser.setBounds(500,240,27,27);

        //membuat texfield username
        username = new JTextField("Username");
        username.setBounds(532,240,300,27);

        //membuat placeholder pada password
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(username.getText().trim().equals("Username"))
                    username.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(username.getText().trim().equals(""))
                    username.setText("Username");
            }
        });

        //membuat icon passoword
        iconpw = new ImageIcon(getClass().getResource("img/password.png"));
        labelpw = new JLabel(iconpw);
        labelpw.setBounds(500,280,27,27);

        //membuat textfield password
        password = new JPasswordField("Password");
        password.setBounds(532,280,300,27);

        //membuat placeholder pada password
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(password.getText().trim().equals("Password"))
                    password.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(password.getText().trim().equals(""))
                    password.setText("Password");
            }
        });

        //membuat icon email
        iconemail = new ImageIcon(getClass().getResource("img/email.png"));
        labelemail = new JLabel(iconemail);
        labelemail.setBounds(500,320,27,27);

        //membuat textfield email
        email = new JTextField("Email");
        email.setBounds(532,320,300,27);

        //membuat placeholder pada email
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

        //membuat icon telepon
        icontelepon = new ImageIcon(getClass().getResource("img/telepon.png"));
        labeltelepon = new JLabel(icontelepon);
        labeltelepon.setBounds(500,360,27,27);

        //membuat textfield telepon
        notlp = new JTextField("No Telepon");
        notlp.setBounds(532,360,300,27);

        //membuat placeholder telepon
        notlp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (notlp.getText().equals("No Telepon")) {
                    notlp.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (notlp.getText().isEmpty()) {
                    notlp.setText("No Telepon");
                }
            }
        });

        //mebuat tombol signup
        botsignup = new JButton("Signup");
        botsignup.setBounds(532,440,300,30);
        //aksi apabila tombol signup di klik
        botsignup.addActionListener(e -> {
            //inisialisasi dan untuk mendapatkan nama, user, email, password, dan no telepon
            String Nama = nama.getText().trim();
            String User = username.getText().trim();
            String Email = email.getText().trim();
            String Password = password.getText().trim();
            String Notlp = notlp.getText().trim();



            try {
                Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksi kan dengan database melalui class Koneksi

                String sql = "INSERT INTO pengguna (id_pengguna,nama,username,password,email,notelepon) VALUE(NULL,'" +Nama+ "','"+User+"','"+Password+ "', '" +Email+ "','"+Notlp+"')"; //membuat query
                int rs = stat.executeUpdate(sql); //eksekusi query

                //memasukan data nama, user, email, passsword, notelepon
                if (!Nama.equals("") && !User.equals("") && !Email.equals("") && !Password.equals("") && !Notlp.equals("")
                        && !Nama.equals("Nama") && !User.equals("User") && !Email.equals("Email") && !Password.equals("Password") && !Notlp.equals("No Telepon")) {
                    JOptionPane.showMessageDialog(null,"Username dan password berhasil dibuat silahkan login");
                    dispose();
                    new Main().setVisible(true);
                } else {
                    //notif apabila gagal signup
                    JOptionPane.showMessageDialog(null, "Silahkan isi form yang kosong");
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

        //membuat semua variable ke dalam container
        container.add(label2);
        container.add(nama);
        container.add(labelnama);
        container.add(labeluser);
        container.add(labelpw);
        container.add(labelemail);
        container.add(labeltelepon);
        container.add(username);
        container.add(password);
        container.add(email);
        container.add(notlp);
        container.add(botsignup);
        container.add(cancel);
        container.add(label);


    }

    public static void main (String []args){
        SignUp frame = new SignUp(); //membuat objek frame supaya dapat ditampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
