import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ChangeUserAccount extends JFrame {

    private JPanel contentPane;
    JLabel lblNewLabel;
    private JPasswordField passwordField_1;
    private JLabel lblNewLabel_3;
    private JButton btnNewButton;
    private JButton btnCancel;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangeUserAccount frame = new ChangeUserAccount();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChangeUserAccount() {
        setTitle("Thay đổi tài khoản");
        setBackground(new Color(0, 128, 255));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 513, 297);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        lblNewLabel = new JLabel("Mã người dùng");
        lblNewLabel.setForeground(new Color(240, 240, 240));

        JLabel lblNewLabel_1 = new JLabel("Email mới");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_2 = new JLabel("Đặt lại tài khoản");
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        lblNewLabel_3 = new JLabel("Mật khẩu mới");
        lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        btnNewButton = new JButton("Cập nhật tài khoản");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                String newEmail = textField.getText();
                String password_new = String.copyValueOf(passwordField_1.getPassword());
                Connection con = DBInfo.conn();
                String query = "UPDATE registration SET email=?, password=? WHERE id='" + id + "'";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, newEmail);
                    ps.setString(2, password_new);
                    int flag = ps.executeUpdate();
                    if (flag == 1) {
                        JOptionPane.showMessageDialog(getParent(), "Cập nhật tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Không thể cập nhật tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 13));

        btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnCancel.setFont(new Font("Verdana", Font.BOLD, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(72)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(btnNewButton)
                                                                .addGap(18)
                                                                .addComponent(btnCancel))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(lblNewLabel_3)
                                                                        .addComponent(lblNewLabel_1))
                                                                .addGap(51)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(textField)
                                                                        .addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))))
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)))
                                .addContainerGap(540, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_2)
                                .addGap(37)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_3)
                                        .addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(34)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}
