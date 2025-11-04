import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class ReturnBook extends JFrame {

    private JPanel contentPane;
    private JTextField textField_MaSach;
    private JTextField textField_TenSach;
    private JComboBox comboBox_TacGia;
    private JTextField textField_TenNguoiDung;
    private JTextField textField_NgayMuon;
    private JDateChooser dateChooser_NgayTra;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReturnBook frame = new ReturnBook();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Hàm reset dữ liệu
    public void reset() {
        textField_MaSach.setText(null);
        textField_TenSach.setText(null);
        comboBox_TacGia.setSelectedIndex(0);
        textField_TenNguoiDung.setText(null);
        textField_NgayMuon.setText(null);
        dateChooser_NgayTra.setCalendar(null);
        textField_TenSach.setEditable(true);
        comboBox_TacGia.setEnabled(true);
    }

    public ReturnBook() {
        setTitle("Trả Sách");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTieuDe = new JLabel("TRẢ SÁCH");
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Verdana", Font.BOLD, 18));

        JLabel lblLoiCamOn = new JLabel("Cảm ơn bạn đã trả sách đúng hạn!");
        lblLoiCamOn.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoiCamOn.setFont(new Font("Verdana", Font.ITALIC, 13));

        JLabel lblMaSach = new JLabel("Mã sách:");
        lblMaSach.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblTacGia = new JLabel("Tác giả:");
        lblTacGia.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblTenNguoiDung = new JLabel("Tên người dùng:");
        lblTenNguoiDung.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNgayMuon = new JLabel("Ngày mượn:");
        lblNgayMuon.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNgayTra = new JLabel("Ngày trả:");
        lblNgayTra.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField_MaSach = new JTextField();
        textField_MaSach.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_MaSach.setColumns(10);
        textField_MaSach.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String id = textField_MaSach.getText();
                Connection con = DBInfo.conn();
                String query = "SELECT * FROM issueBooks WHERE bookId=?";
                int flag = 0;
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        flag = 1;
                        String title = res.getString(3);
                        String author = res.getString(4);
                        String issueDate = res.getString(5);

                        textField_TenSach.setText(title);
                        comboBox_TacGia.setSelectedItem(author);
                        textField_NgayMuon.setText(issueDate);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (flag == 0) {
                    JOptionPane.showMessageDialog(getParent(), "Mã sách không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    textField_TenSach.setEditable(false);
                    comboBox_TacGia.setEnabled(false);
                }
            }
        });

        textField_TenSach = new JTextField();
        textField_TenSach.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_TenSach.setColumns(10);

        comboBox_TacGia = new JComboBox(DBInfo.getValue("author"));
        comboBox_TacGia.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField_TenNguoiDung = new JTextField();
        textField_TenNguoiDung.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_TenNguoiDung.setColumns(10);

        textField_NgayMuon = new JTextField();
        textField_NgayMuon.setEditable(false);
        textField_NgayMuon.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_NgayMuon.setColumns(10);

        dateChooser_NgayTra = new JDateChooser();
        dateChooser_NgayTra.setDateFormatString("dd-MM-yyyy");
        dateChooser_NgayTra.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnTraSach = new JButton("Xác nhận trả");
        btnTraSach.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnTraSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
                String username = textField_TenNguoiDung.getText();
                String bookId = textField_MaSach.getText();
                String title = textField_TenSach.getText();
                String author = comboBox_TacGia.getSelectedItem().toString();
                String issueDate = textField_NgayMuon.getText();
                String dueDate = (dateChooser_NgayTra.getDate() == null) ? "" : dFormat.format(dateChooser_NgayTra.getDate());

                if (username.isEmpty() || bookId.isEmpty() || title.isEmpty() || author.equalsIgnoreCase("Select")
                        || issueDate.isEmpty() || dueDate.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng điền đầy đủ thông tin!", "Thiếu dữ liệu",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Connection con = DBInfo.conn();
                    try {
                        String sql = "SELECT * FROM issueBooks WHERE username=?";
                        PreparedStatement ps_username = con.prepareStatement(sql);
                        ps_username.setString(1, username);
                        ResultSet res = ps_username.executeQuery();

                        if (res.next()) {
                            String sqlId = "SELECT * FROM issueBooks WHERE bookId=?";
                            PreparedStatement ps_bookId = con.prepareStatement(sqlId);
                            ps_bookId.setString(1, bookId);
                            ResultSet res_id = ps_bookId.executeQuery();
                            if (res_id.next()) {
                                String query = "UPDATE issueBooks SET dueDate=?, returnStatus='Đã trả' WHERE bookId=?";
                                PreparedStatement ps = con.prepareStatement(query);
                                ps.setString(1, dueDate);
                                ps.setString(2, bookId);
                                int flag = ps.executeUpdate();

                                if (flag > 0) {
                                    JOptionPane.showMessageDialog(getParent(), "Trả sách thành công!",
                                            "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                    reset();
                                } else {
                                    JOptionPane.showMessageDialog(getParent(), "Không thể cập nhật dữ liệu!",
                                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Không tìm thấy sách với mã " + bookId + ".", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Tên người dùng không hợp lệ!", "Lỗi",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

        JButton btnHuy = new JButton("Hủy bỏ");
        btnHuy.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                        .addComponent(lblLoiCamOn, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(50)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblNgayTra)
                                                        .addComponent(lblNgayMuon)
                                                        .addComponent(lblTacGia)
                                                        .addComponent(lblTenSach)
                                                        .addComponent(lblMaSach)
                                                        .addComponent(lblTenNguoiDung))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textField_MaSach)
                                                        .addComponent(textField_TenSach)
                                                        .addComponent(comboBox_TacGia, 0, 220, Short.MAX_VALUE)
                                                        .addComponent(textField_TenNguoiDung)
                                                        .addComponent(textField_NgayMuon)
                                                        .addComponent(dateChooser_NgayTra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(10))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(110)
                                .addComponent(btnTraSach)
                                .addGap(30)
                                .addComponent(btnHuy)
                                .addContainerGap(110, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(10)
                                .addComponent(lblTieuDe)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblLoiCamOn)
                                .addGap(30)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTenNguoiDung)
                                        .addComponent(textField_TenNguoiDung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblMaSach)
                                        .addComponent(textField_MaSach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTenSach)
                                        .addComponent(textField_TenSach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTacGia)
                                        .addComponent(comboBox_TacGia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNgayMuon)
                                        .addComponent(textField_NgayMuon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNgayTra)
                                        .addComponent(dateChooser_NgayTra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(25)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnTraSach)
                                        .addComponent(btnHuy))
                                .addGap(30))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
