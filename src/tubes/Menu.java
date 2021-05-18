package tubes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends JFrame {

    //deklarasi variabel
    private JLabel label;
    private JLabel label2;
    private JLabel menu1;
    private JLabel menu2;
    private JLabel menu3;
    private JLabel menu4;
    private JLabel txt1;
    private JLabel txt2;
    private JLabel txt3;
    private JLabel txt4;

    private ImageIcon bg;
    private ImageIcon logo;
    private ImageIcon gmenu1;
    private ImageIcon gmenu2;
    private ImageIcon gmenu3;
    private ImageIcon gmenu4;

    private JButton logout;

    public Menu(){
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH); //membuat jframe full screen
        Container container = getContentPane(); //memasukkan getContentPane() ke container


        container.setLayout(null);

        //membuatbackground
        bg = new ImageIcon(getClass().getResource("img/bg5.jpg"));
        label = new JLabel(bg);
        label.setSize(1365,703);

        //membuat logo
        logo = new ImageIcon(getClass().getResource("img/sitras.png"));
        label2 = new JLabel(logo);
        label2.setBounds(582,20,200,200);

        //membuat icon menu quisioner
        gmenu1 = new ImageIcon(getClass().getResource("img/menu11.png"));
        menu1 = new JLabel(gmenu1);
        menu1.setBounds(382,170,200,200);
        //aksi apabbila icon menu quisioner di klik
        menu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Quesioner().setVisible(true);
            }
        });

        //membuat text quisioner
        txt1 = new JLabel("Quesioner");
        txt1.setFont(new Font("Droid Serif",Font.BOLD,20));
        txt1.setBounds(430,320,200,20);

        //membuat icon menu forum
        gmenu2 = new ImageIcon(getClass().getResource("img/menu21.png"));
        menu2 = new JLabel(gmenu2);
        menu2.setBounds(782,170,200,200);
        //aksi apabila icon menu forum di klik
        menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Forum().setVisible(true);
            }
        });

        //membuat text forum
        txt2 = new JLabel("Forum");
        txt2.setFont(new Font("Droid Serif",Font.BOLD,20));
        txt2.setBounds(840,320,200,20);

        //membuat icon menu data alumni
        gmenu3 = new ImageIcon(getClass().getResource("img/menu31.png"));
        menu3 = new JLabel(gmenu3);
        menu3.setBounds(382,370,200,200);
        //aksi apabila icon menu data alumni di klik
        menu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new DataAlumni().setVisible(true);
            }
        });

        //membuat text data alumni
        txt3 = new JLabel("Data Alumni");
        txt3.setFont(new Font("Droid Serif",Font.BOLD,20));
        txt3.setBounds(420,525,200,20);

        //membuat icon menu profile
        gmenu4 = new ImageIcon(getClass().getResource("img/menu41.png"));
        menu4 = new JLabel(gmenu4);
        menu4.setBounds(782,370,200,200);
        //aksi apabila icon menu profile di klik
        menu4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new profile().setVisible(true);
            }
        });

        //membuat text profile
        txt4 = new JLabel("Profile");
        txt4.setFont(new Font("Droid Serif",Font.BOLD,20));
        txt4.setBounds(847,525,200,20);

        //membuat tombol logout
        logout = new JButton("LOGOUT");
        logout.setBounds(563,600,240,40);
        //aksi apabila tombol logout di klik
        logout.addActionListener(e -> {
            //inisialisasi
            Session.id = null;
            Session.username = null;
            Session.email = null;

            //notif apaila berhasil logout
            JOptionPane.showMessageDialog(null,"Anda telah logout");
            dispose();
            new Main().setVisible(true);
        });

        //memasukkan semua variabel ke container
        container.add(label2);
        container.add(menu1);
        container.add(txt1);
        container.add(menu2);
        container.add(txt2);
        container.add(menu3);
        container.add(txt3);
        container.add(menu4);
        container.add(txt4);
        container.add(logout);
        container.add(label);


    }

    public static void main (String []args){
        Menu frame = new Menu(); //membuat objek frame supaya dapat di tampilkan

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
