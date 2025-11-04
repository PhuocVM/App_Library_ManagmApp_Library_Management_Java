import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewUser extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewUser frame = new ViewUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewUser() {
        setResizable(false);
        setTitle("Xem Người Dùng"); // Tiêu đề cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 628, 344);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Xem Người Dùng");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        JButton btnAdmin = new JButton("Quản trị viên");
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = "Admin";
                showUserTable(type);
            }
        });
        btnAdmin.setFont(new Font("Verdana", Font.PLAIN, 15));

        JButton btnStudent = new JButton("Sinh viên");
        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = "Student";
                showUserTable(type);
            }
        });
        btnStudent.setFont(new Font("Verdana", Font.PLAIN, 15));

        JLabel lblAccountType = new JLabel("Loại tài khoản");
        lblAccountType.setHorizontalAlignment(SwingConstants.CENTER);
        lblAccountType.setFont(new Font("Verdana", Font.ITALIC, 13));

        JButton btnFaculty = new JButton("Giảng viên");
        btnFaculty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = "Faculty";
                showUserTable(type);
            }
        });
        btnFaculty.setFont(new Font("Verdana", Font.PLAIN, 15));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(44)
                                .addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnFaculty, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(btnStudent, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                .addGap(36))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAccountType, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(31)
                                .addComponent(lblNewLabel)
                                .addGap(18)
                                .addComponent(lblAccountType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addGap(44)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnStudent, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnFaculty, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(93, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    // Hàm hỗ trợ hiển thị bảng người dùng theo loại
    private void showUserTable(String type) {
        JFrame frame = new JFrame("Danh sách " + type);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        try {
            DBInfo.viewLibrarians(type); // Giả sử phương thức này nhận loại tài khoản
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        table = new JTable(DBInfo.outerVector2, DBInfo.colsName2);
        JScrollPane pane = new JScrollPane(table);
        frame.getContentPane().add(pane);
    }
}