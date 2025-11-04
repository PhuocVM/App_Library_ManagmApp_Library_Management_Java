import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.HeadlessException;
import java.sql.*;

public class Registration extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registration frame = new Registration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reset() {
        textField.setText(null);
        textField_1.setText(null);
        textField_2.setText(null);
        textField_3.setText(null);
        passwordField.setText(null);
        passwordField_1.setText(null);
    }

    public String studentId() {
        String studentNum = "";
        for (int i = 0; i < 6; i++) {
            int num = (int) (Math.random() * 9) + 1;
            studentNum = studentNum + num;
        }
        return studentNum;
    }

    public Registration() {
        setResizable(false);
        setTitle("Đăng ký tài khoản");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 540);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Đăng ký người dùng");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblSubtitle = new JLabel("Vui lòng điền đầy đủ thông tin bên dưới");
        lblSubtitle.setFont(new Font("Verdana", Font.ITALIC, 13));

        JLabel lblName = new JLabel("Họ và tên");
        lblName.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        JLabel lblPhone = new JLabel("Số điện thoại");
        lblPhone.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_1.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_2.setColumns(10);

        JLabel lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_3.setColumns(10);

        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblRePassword = new JLabel("Nhập lại mật khẩu");
        lblRePassword.setFont(new Font("Verdana", Font.PLAIN, 13));
        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnSave = new JButton("Đăng ký");
        btnSave.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String mobile = textField_1.getText();
                String email = textField_2.getText();
                String username = textField_3.getText();
                String password = String.copyValueOf(passwordField.getPassword());
                String re_password = String.copyValueOf(passwordField_1.getPassword());

                if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()
                        || re_password.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng điền đầy đủ thông tin.", "Thiếu thông tin",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(re_password)) {
                    JOptionPane.showMessageDialog(getParent(), "Mật khẩu nhập lại không khớp.", "Sai mật khẩu",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Connection con = DBInfo.conn();
                    try (Statement stmt = con.createStatement()) {
                        String sql = "SELECT * FROM registration WHERE username='" + username + "'";
                        ResultSet res = stmt.executeQuery(sql);
                        if (res.next()) {
                            JOptionPane.showMessageDialog(getParent(), "Tên đăng nhập đã tồn tại.", "Lỗi",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            String query = "INSERT INTO registration(id,name,mobile,email,username,password,usertype) values(?,?,?,?,?,?,?)";
                            PreparedStatement ps = con.prepareStatement(query);
                            ps.setString(1, studentId());
                            ps.setString(2, name);
                            ps.setString(3, mobile);
                            ps.setString(4, email);
                            ps.setString(5, username);
                            ps.setString(6, password);
                            ps.setString(7, "Student");
                            int i = ps.executeUpdate();
                            if (i == 1) {
                                JOptionPane.showMessageDialog(getParent(), "Đăng ký thành công!", "Thành công",
                                        JOptionPane.INFORMATION_MESSAGE);
                                reset();
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JButton btnReset = new JButton("Làm lại");
        btnReset.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JButton btnLogin = new JButton("Đã có tài khoản? Đăng nhập");
        btnLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login log = new Login();
                log.setVisible(true);
                setVisible(false);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(30)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblTitle)
                                .addComponent(lblSubtitle)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblName)
                                                .addComponent(lblPhone)
                                                .addComponent(lblEmail)
                                                .addComponent(lblUsername)
                                                .addComponent(lblPassword)
                                                .addComponent(lblRePassword))
                                        .addGap(40)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(textField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(textField_1)
                                                .addComponent(textField_2)
                                                .addComponent(textField_3)
                                                .addComponent(passwordField)
                                                .addComponent(passwordField_1)))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addComponent(btnSave)
                                        .addGap(18)
                                        .addComponent(btnReset)
                                        .addGap(18)
                                        .addComponent(btnLogin)))
                        .addContainerGap(30, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitle)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(lblSubtitle)
                        .addGap(25)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblPhone)
                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblUsername)
                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblPassword)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblRePassword)
                                .addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnSave)
                                .addComponent(btnReset)
                                .addComponent(btnLogin))
                        .addContainerGap(30, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
