import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PasswordCheck extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PasswordCheck frame = new PasswordCheck();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PasswordCheck() {
        setTitle("Xác thực tài khoản");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 420, 320);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Xác thực thông tin đăng nhập");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblUser = new JLabel("Tên đăng nhập");
        lblUser.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setFont(new Font("Verdana", Font.PLAIN, 13));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnSave = new JButton("Xác nhận");
        btnSave.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = String.copyValueOf(passwordField.getPassword());

                Connection con = DBInfo.conn();
                try (Statement stmt = con.createStatement()) {
                    String sql = "SELECT * FROM registration WHERE username='" + username + "' AND password='" + password + "'";
                    ResultSet res = stmt.executeQuery(sql);

                    if (res.next()) {
                        id = res.getString(1);
                        Settings setting = new Settings();
                        setting.lblNewLabel.setText(id);
                        setting.setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(getParent(),
                                "Tên đăng nhập hoặc mật khẩu không đúng, vui lòng kiểm tra lại.",
                                "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (HeadlessException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(40)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(90)
                                                .addComponent(btnSave)
                                                .addGap(30)
                                                .addComponent(btnCancel)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(20)
                                .addComponent(lblTitle)
                                .addGap(30)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblUser)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                .addGap(25)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPass)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                .addGap(35)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }
}
