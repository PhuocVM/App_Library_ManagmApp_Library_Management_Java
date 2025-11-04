import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminPage frame = new AdminPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminPage() {
        setTitle("Trang Quản Trị Hệ Thống Thư Viện");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 850, 500);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 3, 10, 10));

        // Tiêu đề chính
        JLabel lblTitle = new JLabel("Trang Quản Trị");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        contentPane.add(lblTitle);

        // Nút thêm người quản lý
        Image imgAddUser = new ImageIcon(this.getClass().getResource("/member-add-on-300x300.png")).getImage();
        JButton btnAddManager = new JButton("Thêm Người Quản Lý");
        btnAddManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddNewUser().setVisible(true);
            }
        });
        btnAddManager.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnAddManager.setIcon(new ImageIcon(imgAddUser));
        contentPane.add(btnAddManager);

        // Nút xóa người quản lý
        JButton btnDeleteManager = new JButton("Xóa Người Quản Lý");
        btnDeleteManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditUser().setVisible(true);
            }
        });
        Image imgDelete = new ImageIcon(this.getClass().getResource("editUser.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        btnDeleteManager.setIcon(new ImageIcon(imgDelete));
        btnDeleteManager.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnDeleteManager);

        // Nút xem danh sách người dùng
        JButton btnViewUsers = new JButton("Xem Danh Sách Người Dùng");
        btnViewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewUser().setVisible(true);
            }
        });
        Image imgViewUser = new ImageIcon(this.getClass().getResource("viewUser.png")).getImage().getScaledInstance(65, 60, Image.SCALE_DEFAULT);
        btnViewUsers.setIcon(new ImageIcon(imgViewUser));
        btnViewUsers.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnViewUsers);

        // Nút xem thống kê
        JButton btnViewStats = new JButton("Xem Thống Kê");
        Image imgStats = new ImageIcon(this.getClass().getResource("Statics.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        btnViewStats.setIcon(new ImageIcon(imgStats));
        btnViewStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ViewStatic().setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnViewStats.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnViewStats);

        // Nút xem sách
        JButton btnViewBooks = new JButton("Xem Danh Sách Sách");
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBooks vb = new ViewBooks();
                vb.setVisible(true);
            }
        });
        btnViewBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
        Image imgBooks = new ImageIcon(this.getClass().getResource("viewBooks.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnViewBooks.setIcon(new ImageIcon(imgBooks));
        contentPane.add(btnViewBooks);

        // Nút thêm thông báo
        JButton btnAddNotice = new JButton("Thêm Thông Báo Mới");
        btnAddNotice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditNotice().setVisible(true);
            }
        });
        Image imgNotice = new ImageIcon(this.getClass().getResource("notice.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnAddNotice.setIcon(new ImageIcon(imgNotice));
        btnAddNotice.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnAddNotice);
    }
}
