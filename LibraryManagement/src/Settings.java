import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Settings extends JFrame {

    private JPanel contentPane;
    JLabel lblNewLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Settings frame = new Settings();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Settings() {
        setResizable(false);
        setTitle("Cài đặt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 414, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel_1 = new JLabel("Cài đặt tài khoản");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));

        JButton btnNewButton = new JButton("Chỉnh sửa hồ sơ");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                EditProfile editProfile = new EditProfile();
                editProfile.lblNewLabel_3_2_1.setText(id);
                editProfile.setVisible(true);
            }
        });
        btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_1 = new JButton("Đổi mật khẩu");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                ChangePassword changePassword = new ChangePassword();
                changePassword.lblNewLabel.setText(id);
                changePassword.setVisible(true);
            }
        });
        btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_2 = new JButton("Chuyển tài khoản");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                ChangeUserAccount cua = new ChangeUserAccount();
                cua.lblNewLabel.setText(id);
                cua.setVisible(true);
            }
        });
        btnNewButton_2.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_3 = new JButton("Xóa tài khoản");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                DeleteUser deleteUser = new DeleteUser();
                deleteUser.lblNewLabel.setText(id);
                deleteUser.setVisible(true);
            }
        });
        btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        lblNewLabel = new JLabel("Mã người dùng");
        lblNewLabel.setForeground(new Color(240, 240, 240));
        lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(91)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblNewLabel)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(5)
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addGap(48)
                                .addComponent(btnNewButton)
                                .addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(btnNewButton_1)
                                .addGap(27)
                                .addComponent(btnNewButton_2)
                                .addGap(27)
                                .addComponent(btnNewButton_3)
                                .addGap(40))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
