import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;

public class EditUser extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldTenDangNhap;
    private JTextField textFieldHoTen;
    private JTextField textFieldEmail;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EditUser frame = new EditUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        textFieldTenDangNhap.setText("");
        textFieldHoTen.setText("");
        textFieldEmail.setText("");
    }

    public EditUser() {
        setResizable(false);
        setTitle("Xóa người dùng");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 480);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTieuDe = new JLabel("Xóa người dùng");
        lblTieuDe.setFont(new Font("Verdana", Font.BOLD, 18));
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblThongBao = new JLabel("⚠️ Sau khi xóa, tài khoản không thể khôi phục!");
        lblThongBao.setFont(new Font("Verdana", Font.ITALIC, 13));
        lblThongBao.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(new Font("Verdana", Font.PLAIN, 13));

        textFieldTenDangNhap = new JTextField();
        textFieldTenDangNhap.setFont(new Font("Verdana", Font.PLAIN, 13));
        textFieldTenDangNhap.setColumns(10);

        textFieldTenDangNhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                timKiemNguoiDung();
            }
        });

        JLabel lblHoTen = new JLabel("Họ và tên:");
        lblHoTen.setFont(new Font("Verdana", Font.PLAIN, 13));

        textFieldHoTen = new JTextField();
        textFieldHoTen.setFont(new Font("Verdana", Font.PLAIN, 13));
        textFieldHoTen.setColumns(10);

        JLabel lblEmail = new JLabel("Địa chỉ email:");
        lblEmail.setFont(new Font("Verdana", Font.PLAIN, 13));

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Verdana", Font.PLAIN, 13));
        textFieldEmail.setColumns(10);

        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnTimKiem.addActionListener(e -> timKiemNguoiDung());

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnXoa.addActionListener(e -> xoaNguoiDung());

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnHuy.addActionListener(e -> setVisible(false));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.CENTER)
                        .addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addComponent(lblThongBao, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblTenDangNhap)
                                        .addComponent(lblHoTen)
                                        .addComponent(lblEmail))
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textFieldTenDangNhap, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textFieldHoTen, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(40)
                                .addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        );

        gl_contentPane.setVerticalGroup(
                gl_contentPane.createSequentialGroup()
                        .addGap(10)
                        .addComponent(lblTieuDe)
                        .addGap(10)
                        .addComponent(lblThongBao)
                        .addGap(30)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblTenDangNhap)
                                .addComponent(textFieldTenDangNhap, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(15)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblHoTen)
                                .addComponent(textFieldHoTen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(15)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(40)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(30)
        );

        contentPane.setLayout(gl_contentPane);
    }

    private void timKiemNguoiDung() {
        String username = textFieldTenDangNhap.getText().trim();
        if (username.isEmpty()) return;

        try (Connection con = DBInfo.conn()) {
            String query = "SELECT * FROM registration WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                textFieldTenDangNhap.setText(rs.getString("username"));
                textFieldHoTen.setText(rs.getString("name"));
                textFieldEmail.setText(rs.getString("email"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy người dùng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void xoaNguoiDung() {
        String username = textFieldTenDangNhap.getText().trim();
        String email = textFieldEmail.getText().trim();
        String name = textFieldHoTen.getText().trim();

        if (username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và email.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa người dùng \"" + name + "\"?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection con = DBInfo.conn()) {
                String check = "SELECT * FROM registration WHERE username=? AND email=?";
                PreparedStatement ps = con.prepareStatement(check);
                ps.setString(1, username);
                ps.setString(2, email);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String type = rs.getString("usertype");
                    if (type.equalsIgnoreCase("Admin")) {
                        JOptionPane.showMessageDialog(this, "Không thể xóa tài khoản Admin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String delete = "DELETE FROM registration WHERE username=? AND email=?";
                    ps = con.prepareStatement(delete);
                    ps.setString(1, username);
                    ps.setString(2, email);
                    int result = ps.executeUpdate();

                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Đã xóa người dùng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản phù hợp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
