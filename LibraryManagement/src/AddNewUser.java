import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddNewUser extends JFrame {

    private JPanel contentPane;
    private JTextField txtHoTen;
    private JTextField txtSoDienThoai;
    private JTextField txtEmail;
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JPasswordField txtNhapLaiMatKhau;
    private JComboBox<String> cmbLoaiTaiKhoan;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddNewUser frame = new AddNewUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Reset toàn bộ form
    public void reset() {
        txtHoTen.setText(null);
        txtSoDienThoai.setText(null);
        txtEmail.setText(null);
        txtTenDangNhap.setText(null);
        txtMatKhau.setText(null);
        txtNhapLaiMatKhau.setText(null);
        cmbLoaiTaiKhoan.setSelectedIndex(0);
    }

    // Sinh mã người dùng ngẫu nhiên
    public String userId() {
        String id = "";
        for (int i = 0; i < 6; i++) {
            int num = (int) (Math.random() * 9) + 1;
            id += num;
        }
        return id;
    }

    // Constructor chính
    public AddNewUser() {
        setResizable(false);
        setTitle("Thêm Người Dùng");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 480, 560);
        setLocationRelativeTo(this);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTieuDe = new JLabel("THÊM NGƯỜI DÙNG MỚI");
        lblTieuDe.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblHuongDan = new JLabel("Vui lòng nhập đầy đủ thông tin bên dưới");
        lblHuongDan.setFont(new Font("Verdana", Font.ITALIC, 13));

        JLabel lblHoTen = new JLabel("Họ và tên:");
        lblHoTen.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtHoTen = new JTextField();
        txtHoTen.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
        lblSoDienThoai.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu:");
        lblNhapLaiMatKhau.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtNhapLaiMatKhau = new JPasswordField();
        txtNhapLaiMatKhau.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblLoaiTaiKhoan = new JLabel("Loại tài khoản:");
        lblLoaiTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 13));

        String[] userTypes = {"Chọn loại tài khoản", "Quản trị viên", "Thủ thư", "Người dùng"};
        cmbLoaiTaiKhoan = new JComboBox<>(userTypes);
        cmbLoaiTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtHoTen.getText();
                String mobile = txtSoDienThoai.getText();
                String email = txtEmail.getText();
                String username = txtTenDangNhap.getText();
                String password = String.copyValueOf(txtMatKhau.getPassword());
                String rePassword = String.copyValueOf(txtNhapLaiMatKhau.getPassword());
                String userType = cmbLoaiTaiKhoan.getSelectedItem().toString();

                if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || username.isEmpty()
                        || password.isEmpty() || rePassword.isEmpty()
                        || userType.equalsIgnoreCase("Chọn loại tài khoản")) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Vui lòng nhập đầy đủ thông tin!",
                            "Thiếu thông tin", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(rePassword)) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Mật khẩu nhập lại không khớp!",
                            "Lỗi mật khẩu", JOptionPane.ERROR_MESSAGE);
                } else {
                    Connection con = DBInfo.conn();
                    String query = "INSERT INTO registration(id,name,mobile,email,username,password,usertype) VALUES(?,?,?,?,?,?,?)";
                    int i = 0;
                    try {
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, userId());
                        ps.setString(2, name);
                        ps.setString(3, mobile);
                        ps.setString(4, email);
                        ps.setString(5, username);
                        ps.setString(6, password);
                        ps.setString(7, userType);
                        i = ps.executeUpdate();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    if (i == 1) {
                        JOptionPane.showMessageDialog(getParent(),
                                "Thêm người dùng thành công!",
                                "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                }
            }
        });

        JButton btnDatLai = new JButton("Đặt lại");
        btnDatLai.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnDatLai.addActionListener(e -> reset());

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnHuy.addActionListener(e -> setVisible(false));

        // Bố cục giao diện
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(130)
                                .addComponent(lblTieuDe))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(95)
                                .addComponent(lblHuongDan))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(25)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblHoTen)
                                        .addComponent(lblSoDienThoai)
                                        .addComponent(lblEmail)
                                        .addComponent(lblTenDangNhap)
                                        .addComponent(lblMatKhau)
                                        .addComponent(lblNhapLaiMatKhau)
                                        .addComponent(lblLoaiTaiKhoan))
                                .addGap(30)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(txtHoTen)
                                        .addComponent(txtSoDienThoai)
                                        .addComponent(txtEmail)
                                        .addComponent(txtTenDangNhap)
                                        .addComponent(txtMatKhau)
                                        .addComponent(txtNhapLaiMatKhau)
                                        .addComponent(cmbLoaiTaiKhoan, 0, 220, Short.MAX_VALUE)))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(60)
                                .addComponent(btnLuu)
                                .addGap(40)
                                .addComponent(btnDatLai)
                                .addGap(40)
                                .addComponent(btnHuy))
        );

        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(20)
                                .addComponent(lblTieuDe)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHuongDan)
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblHoTen)
                                        .addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblSoDienThoai)
                                        .addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblEmail)
                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTenDangNhap)
                                        .addComponent(txtTenDangNhap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblMatKhau)
                                        .addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNhapLaiMatKhau)
                                        .addComponent(txtNhapLaiMatKhau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLoaiTaiKhoan)
                                        .addComponent(cmbLoaiTaiKhoan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(25)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnLuu)
                                        .addComponent(btnDatLai)
                                        .addComponent(btnHuy)))
        );

        contentPane.setLayout(gl_contentPane);
    }
}
