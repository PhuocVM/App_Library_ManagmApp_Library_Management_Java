import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FacultyPage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FacultyPage frame = new FacultyPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FacultyPage() {
        setTitle("Trang Giảng Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 850, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(4, 2, 10, 10));
        setContentPane(contentPane);

        JLabel lblTieuDe = new JLabel("📚 Trang Quản Lý Thư Viện - Giảng Viên");
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTieuDe.setForeground(new Color(30, 30, 30));
        contentPane.add(lblTieuDe);

        JButton btnThemSach = new JButton("Thêm sách mới");
        btnThemSach.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnThemSach.addActionListener(e -> new AddNewBook().setVisible(true));
        contentPane.add(btnThemSach);

        JButton btnSuaSach = new JButton("Chỉnh sửa sách");
        btnSuaSach.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnSuaSach.addActionListener(e -> new EditBook().setVisible(true));
        contentPane.add(btnSuaSach);

        JButton btnThongKe = new JButton("Xem thống kê");
        btnThongKe.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnThongKe.addActionListener((ActionEvent e) -> {
            try {
                new ViewStatic().setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        contentPane.add(btnThongKe);

        JButton btnXemSach = new JButton("Xem danh sách sách");
        btnXemSach.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnXemSach.addActionListener(e -> new ViewBooks().setVisible(true));
        contentPane.add(btnXemSach);

        JButton btnXemNguoiDung = new JButton("Xem người dùng");
        btnXemNguoiDung.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnXemNguoiDung.addActionListener(e -> new ViewUser().setVisible(true));
        contentPane.add(btnXemNguoiDung);

        JButton btnMuonSach = new JButton("Mượn sách");
        btnMuonSach.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnMuonSach.addActionListener(e -> new IssueBooks().setVisible(true));
        contentPane.add(btnMuonSach);

        JButton btnTraSach = new JButton("Trả sách");
        btnTraSach.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnTraSach.addActionListener(e -> new ReturnBook().setVisible(true));
        contentPane.add(btnTraSach);

        JButton btnThongBao = new JButton("Thêm thông báo");
        btnThongBao.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnThongBao.addActionListener(e -> new EditNotice().setVisible(true));
        contentPane.add(btnThongBao);

        JButton btnTaiKhoan = new JButton("Chỉnh sửa tài khoản");
        btnTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnTaiKhoan.addActionListener(e -> new PasswordCheck().setVisible(true));
        contentPane.add(btnTaiKhoan);
    }
}
