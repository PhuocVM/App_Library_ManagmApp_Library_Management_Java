import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DeleteUser extends JFrame {

    private JPanel contentPane;
    JLabel lblNewLabel;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JButton btnNewButton_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteUser frame = new DeleteUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DeleteUser() {
        setTitle("Xóa tài khoản");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 590, 372);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        lblNewLabel = new JLabel("Mã người dùng");
        lblNewLabel.setForeground(new Color(240, 240, 240));

        lblNewLabel_2 = new JLabel("Xóa tài khoản của tôi");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));

        JTextArea txtrAreYouSure = new JTextArea();
        txtrAreYouSure.setWrapStyleWord(true);
        txtrAreYouSure.setBackground(new Color(240, 240, 240));
        txtrAreYouSure.setFont(new Font("Verdana", Font.ITALIC, 13));
        txtrAreYouSure.setLineWrap(true);
        txtrAreYouSure.setEditable(false);
        txtrAreYouSure.setText(
                "Bạn có chắc chắn muốn xóa tài khoản của mình khỏi hệ thống quản lý thư viện không? " +
                        "Hãy nhớ rằng sau khi xóa, bạn sẽ không thể truy cập lại tài khoản này hoặc khôi phục bất kỳ dữ liệu nào đã lưu trong hệ thống."
        );

        lblNewLabel_3 = new JLabel("Nếu bạn vẫn muốn xóa tài khoản, hãy nhấn \"Xóa tài khoản của tôi\".");
        lblNewLabel_3.setFont(new Font("Verdana", Font.ITALIC, 13));

        JButton btnNewButton = new JButton("Xóa tài khoản của tôi");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = lblNewLabel.getText();
                int x = JOptionPane.showConfirmDialog(
                        getParent(),
                        "Bạn có chắc chắn muốn xóa tài khoản này không?",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION
                );
                if (x == JOptionPane.YES_OPTION) {
                    Connection con = DBInfo.conn();
                    String query = "DELETE FROM registration WHERE id=?";
                    int flag = 0;
                    try {
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, id);
                        flag = ps.executeUpdate();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (flag == 0) {
                        JOptionPane.showMessageDialog(
                                getParent(),
                                "Không thể xóa người dùng! Vui lòng liên hệ quản trị viên hoặc thủ thư.",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                getParent(),
                                "Tài khoản đã được xóa thành công.",
                                "Thành công",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        System.exit(0);
                    }
                }
            }
        });
        btnNewButton.setBackground(new Color(70, 163, 255));
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 13));

        btnNewButton_1 = new JButton("Hủy bỏ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 13));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(txtrAreYouSure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblNewLabel))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(20)
                                                .addComponent(btnNewButton)
                                                .addGap(32)
                                                .addComponent(btnNewButton_1)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addGap(18)
                                .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addGap(29)
                                .addComponent(txtrAreYouSure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(lblNewLabel_3)
                                .addGap(27)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnNewButton_1))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
