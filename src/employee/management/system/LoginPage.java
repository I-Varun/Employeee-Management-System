package employee.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginPage extends JFrame implements ActionListener {

    /**
     *
     */
    JTextField tfusername;
    JPasswordField tfpassword;

    //private static final long serialVersionUID = 1L;
    public LoginPage()
    {
        super("Log In");
        setSize(780, 350);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(260,20,1000,50);
        heading.setFont(new Font("serif",Font.ITALIC,40));
        heading.setForeground(Color.WHITE);
        add(heading);

        JLabel username = new JLabel("User ID :");
        username.setBounds(780,280,100,30);
        username.setFont(new Font("serif",Font.BOLD,20));
        username.setForeground(Color.WHITE);
        add(username);

        tfusername = new JTextField();
        tfusername.setBounds(880,280,150,30);
        add(tfusername);

        JLabel password = new JLabel("Password :");
        password.setBounds(770,370,100,30);
        password.setFont(new Font("serif",Font.BOLD,20));
        password.setForeground(Color.WHITE);
        add(password);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(880,370,150,30);
        add(tfpassword);

        JButton login = new JButton("Login");
        login.setBounds(925,440,70,20);
        login.setBackground(Color.white);
        login.setForeground(Color.black);
        login.addActionListener(this);
        add(login);


        JLabel image = new JLabel();
        image.setBounds(50,100,1050,500);
        image.setOpaque(false);
        image.setIcon(new ImageIcon("second.jpg"));
        getContentPane().add(image);



        setSize(1170,650);
        setLocation(200,50);
        setVisible(true);

    }


    public static void main(String args[])
    {
        new LoginPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = tfusername.getText();
        String pass = tfpassword.getText();
        if (user.equals("admin") && pass.equals("admin123")) {
            Dashboard dash = new Dashboard();
            dash.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }
    }
}
