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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

    private JPanel contentPane;
    JLabel lblNewLabel;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JLabel lblNewLabel_3;
    private JButton btnNewButton;
    private JButton btnCancel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangePassword frame = new ChangePassword();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChangePassword() {
        setTitle("Đặt lại mật khẩu");
        setBackground(new Color(0, 128, 255));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 513, 297);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        lblNewLabel = new JLabel("New label");
        lblNewLabel.setForeground(new Color(240, 240, 240));

        JLabel lblNewLabel_1 = new JLabel("Mật khẩu cũ");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_2 = new JLabel("ĐỔI MẬT KHẨU");
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 13));

        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        lblNewLabel_3 = new JLabel("Mật khẩu mới");
        lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        btnNewButton = new JButton("Đổi mật khẩu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                String password_old = String.copyValueOf(passwordField.getPassword());
                String password_new = String.copyValueOf(passwordField_1.getPassword());
                Connection con = DBInfo.conn();
                String checkSql = "SELECT * FROM registration WHERE id=?";
                String oldPassword = "";
                try {
                    PreparedStatement checkPs = con.prepareStatement(checkSql);
                    checkPs.setString(1, id);
                    ResultSet checkRes = checkPs.executeQuery();
                    while (checkRes.next()) {
                        oldPassword = checkRes.getString(6);
                    }
                    if (password_old.equals(oldPassword)) {
                        String query = "UPDATE registration SET password=? WHERE id='" + id + "'";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, password_new);
                        int flag = 0;
                        flag = ps.executeUpdate();
                        if (flag == 1) {
                            JOptionPane.showMessageDialog(getParent(),
                                    "Đổi mật khẩu thành công!",
                                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(getParent(),
                                "Mật khẩu cũ không đúng.",
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(72)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnNewButton)
                                                .addGap(18)
                                                .addComponent(btnCancel))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel_3)
                                                        .addComponent(lblNewLabel_1))
                                                .addGap(51)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))))
                                .addGap(592))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
                                .addContainerGap(540, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_2)
                                .addGap(34)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
