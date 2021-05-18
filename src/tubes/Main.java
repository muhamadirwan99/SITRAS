package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Main extends JFrame {

    //deklarasi variabel

    private JLabel label;
    private JLabel label2;
    private JLabel forgot;
    private JLabel labeluser;
    private JLabel labelpw;

    private ImageIcon bg;
    private ImageIcon logo;
    private ImageIcon iconuser;
    private ImageIcon iconpw;

    private JTextField usertf;
    private JPasswordField passttf;

    private JButton login;
    private JButton signup;



    public Main(){

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

        //memasukkan icon user
        iconuser = new ImageIcon(getClass().getResource("img/user.png"));
        labeluser = new JLabel(iconuser);
        labeluser.setBounds(500,240,27,27);

        //membuat textfield username
        usertf = new JTextField("Username");
        usertf.setBounds(532,240,300,27);

        //membuat placeholder pada textfield
        usertf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usertf.getText().trim().equals("Username"))
                    usertf.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(usertf.getText().trim().equals(""))
                    usertf.setText("Username");
            }
        });

        //memasukkan icon password
        iconpw = new ImageIcon(getClass().getResource("img/password.png"));
        labelpw = new JLabel(iconpw);
        labelpw.setBounds(500,280,27,27);

        //membuat texfield password
        passttf = new JPasswordField("Password");
        passttf.setBounds(532,280,300,27);

        //membuat placeholder pada password
        passttf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(passttf.getText().trim().equals("Password"))
                    passttf.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(passttf.getText().trim().equals(""))
                    passttf.setText("Password");
            }
        });

        //membuat label lupa password
        forgot = new JLabel("Forgot Password");
        forgot.setBounds(730,310,200,27);
        forgot.setFont(new Font("Droid Serif",Font.BOLD,12));

        //membuat jlabel lupa password supaya dapat di klik
        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ForgotPassword().setVisible(true);
            }
        });

        //membuat tombol login
        login = new JButton("Login");
        login.setBounds(532,400,300,30);
        //aksi apabila tombol login di klik
        login.addActionListener(e -> {
            //inisialisasi
            String nama, hasil;
            char[] passname;
            //untuk medapatkan username dan password
            nama = usertf.getText();
            passname = passttf.getPassword();
            hasil = new String(passname);

            try {
                Statement stat = Koneksi.getKoneksi().createStatement(); //untuk mengkoneksi kan dengan database melalui class Koneksi
                String sql = "select * from pengguna where username='" + nama + "' AND password='" + hasil + "'"; //membuat query
                ResultSet rs = stat.executeQuery(sql); //eksekusi query
                boolean masuk;
                masuk = rs.next();
                if (masuk) {
                    //memasukkan data id_pengguna,username, dan email ke class session
                    Session.id = rs.getString("id_pengguna");
                    Session.username = rs.getString("username");
                    Session.email = rs.getString("email");
                    //cek admin
                    if(nama.equals("admin")) {
                        dispose();
                        new MenuAdmin().setVisible(true);
                    }else{
                        dispose();
                        new Menu().setVisible(true);
                    }
                } else {
                    //notif apabila gagal login
                    JOptionPane.showMessageDialog(null, "Username dan password anda salah", "Pesan",
                            JOptionPane.INFORMATION_MESSAGE);
                    //menentukan kembali user dan pass menjadi kosong
                    usertf.setText("");
                    passttf.setText("");
                    usertf.requestFocus();
                }
                stat.close();
            } catch (SQLException er) {
                //notif apabila gagal koneksi ke database
                JOptionPane.showMessageDialog(null, "Terjadi Error : " + er.getMessage());
            }
        });

        //membuat tombol signup
        signup = new JButton("Signup");
        signup.setBounds(532,440,300,30);

        //aksi apabila signup di klik
        signup.addActionListener(e -> {
            dispose();
            new SignUp().setVisible(true);
        });

        //memasukkan semua variabel ke dalam container
        container.add(label2);
        container.add(labeluser);
        container.add(labelpw);
        container.add(usertf);
        container.add(passttf);
        container.add(forgot);
        container.add(login);
        container.add(signup);
        container.add(label);
    }

    public static void main (String []args){
        Main frame = new Main(); //membuat objek frame supaya dapat di tampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
