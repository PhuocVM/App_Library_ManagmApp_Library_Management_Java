import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditProfile extends JFrame {

    private JPanel contentPane;
    JTextField textField;
    JTextField textField_1;
    JTextField textField_2;
    String name1, phone1, username1;
    JLabel lblNewLabel_3_2_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditProfile frame = new EditProfile();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EditProfile() {
        setTitle("Chỉnh sửa hồ sơ");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 470, 443);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel_1 = new JLabel("Chỉnh sửa hồ sơ");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblNewLabel_3 = new JLabel("Họ và tên");
        lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_1.setColumns(10);

        JLabel lblNewLabel_3_1 = new JLabel("Số điện thoại");
        lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_2.setColumns(10);

        JLabel lblNewLabel_3_1_1_1 = new JLabel("Tên đăng nhập");
        lblNewLabel_3_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String phone = textField_1.getText();
                String username = textField_2.getText();
                String id = lblNewLabel_3_2_1.getText();

                if (name.isEmpty() || phone.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Không được để trống ô nào. Vui lòng nhập đầy đủ thông tin.",
                            "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                } else {
                    int j = JOptionPane.showConfirmDialog(getParent(),
                            "Bạn có chắc muốn cập nhật thông tin người dùng?",
                            "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (j == 0) {
                        Connection con = DBInfo.conn();
                        String query = "UPDATE registration SET name='" + name + "', mobile='" + phone + "', username='" + username + "' WHERE id='" + id + "'";
                        int i = 0;
                        try {
                            PreparedStatement ps = con.prepareStatement(query);
                            i = ps.executeUpdate();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(getParent(),
                                    "Cập nhật người dùng thành công.",
                                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(getParent(),
                                    "Cập nhật không thành công.",
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 14));

        JButton btnReseat = new JButton("Làm lại");
        btnReseat.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnReseat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
        });

        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("Vui lòng điền đầy đủ thông tin vào các ô bên dưới.");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));

        lblNewLabel_3_2_1 = new JLabel("Mã người dùng");
        lblNewLabel_3_2_1.setForeground(new Color(240, 240, 240));
        lblNewLabel_3_2_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel_3_2_1)
                                .addContainerGap(351, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(35)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addGap(56)
                                .addComponent(btnNewButton)
                                .addGap(43)
                                .addComponent(btnReseat, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addGap(40)
                                .addComponent(btnCancel)
                                .addContainerGap(67, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel_3_2_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                .addGap(21)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3_1))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnReseat, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addGap(56))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
