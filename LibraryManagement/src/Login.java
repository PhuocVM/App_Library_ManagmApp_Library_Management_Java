import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setResizable(false);
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 430, 320);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton = new JButton("Đăng nhập");
        Image img1 = new ImageIcon(this.getClass().getResource("login.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
        btnNewButton.setIcon(new ImageIcon(img1));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = String.copyValueOf(passwordField.getPassword());
                Connection con = DBInfo.conn();
                int i = 0;
                String usertype = "";

                String query = "SELECT * FROM registration WHERE username=? and password=?";
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        i = 1;
                        usertype = res.getString(7);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (i == 1 && usertype.equalsIgnoreCase("Admin")) {
                    new AdminPage().setVisible(true);
                    dispose();
                } else if (i == 1 && usertype.equalsIgnoreCase("Student")) {
                    new StudentPage().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Tên đăng nhập hoặc mật khẩu không đúng", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnSignUp = new JButton("Người dùng mới? Đăng ký");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Registration().setVisible(true);
                setVisible(false);
            }
        });
        btnSignUp.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel = new JLabel("Đăng nhập hệ thống quản lý thư viện");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(64)
                                                .addComponent(lblNewLabel))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(47)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblNewLabel_1)
                                                                        .addComponent(lblNewLabel_1_1))
                                                                .addGap(31))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(btnNewButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordField)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                        .addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                                .addGap(45))
        );

        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(20)
                                .addComponent(lblNewLabel)
                                .addGap(30)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_1)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(35)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }
}
