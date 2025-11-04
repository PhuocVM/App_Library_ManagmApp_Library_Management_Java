import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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

public class EditBook extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblNewLabel_2;
    private JTextField textField_6;
    private JLabel lblNewLabel_1_2;
    private JTextField textField_7;
    private JTextField textField_8;
    private JLabel lblNewLabel_1_1_4_1;
    private JLabel lblNewLabel_1_1_4_1_1;
    private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditBook frame = new EditBook();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Reset form
     */
    public void reset() {
        textField.setText(null);
        textField.setEditable(true);
        lblNewLabel_2.setIcon(null); // xóa logo / QR hiển thị
        textField_1.setText(null);
        comboBox.setSelectedItem(null);
        comboBox_1.setSelectedItem(null);
        comboBox_2.setSelectedItem(null);
        comboBox_3.setSelectedItem(null);
        textField_6.setText(null);
        textField_7.setText(null);
        textField_8.setText(null);
    }

    /**
     * Create the frame.
     */
    public EditBook() {
        setResizable(false);
        setTitle("Chỉnh sửa sách");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 823, 499);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Chỉnh sửa sách");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblNewLabel_1 = new JLabel("Mã sách");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String id = textField.getText().trim();
                if (id.isEmpty()) {
                    return;
                }
                textField.setEditable(false);
                Connection con = DBInfo.conn();
                String query = "SELECT * FROM book WHERE bookid=?";
                int flag = 0;
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        flag = 1;
                        textField_1.setText(res.getString(2));
                        comboBox.setSelectedItem(res.getString(3));
                        comboBox_1.setSelectedItem(res.getString(4));
                        comboBox_2.setSelectedItem(res.getString(5));
                        comboBox_3.setSelectedItem(res.getString(6));
                        textField_6.setText(res.getString(7));
                        textField_7.setText(res.getString(8));
                        textField_8.setText(res.getString(9));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (flag == 0) {
                    JOptionPane.showMessageDialog(getParent(), "Không tìm thấy sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    // giữ textbox cho sửa / tìm lại
                    textField.setEditable(true);
                    lblNewLabel_2.setIcon(null);
                } else {
                    // cố gắng hiển thị QR nếu có (không bắt buộc)
                    try {
                        java.nio.file.Path p = Paths
                                .get("C:\\Users\\Shantam\\Desktop\\java\\libraryManagement\\qrCodes\\mainQR\\" + id + ".png");
                        if (Files.exists(p)) {
                            ImageIcon imgIcon = new ImageIcon(p.toString());
                            Image img = imgIcon.getImage();
                            Image resize = img.getScaledInstance(200, 68, Image.SCALE_DEFAULT);
                            lblNewLabel_2.setIcon(new ImageIcon(resize));
                        } else {
                            lblNewLabel_2.setIcon(null);
                        }
                    } catch (Exception ex) {
                        lblNewLabel_2.setIcon(null);
                    }
                }
            }
        });
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_1.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Tên sách");
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_1_1_1 = new JLabel("Tác giả");
        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_1_1_2 = new JLabel("Chủ đề");
        lblNewLabel_1_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_1_1_3 = new JLabel("Nhà xuất bản");
        lblNewLabel_1_1_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblNewLabel_1_1_4 = new JLabel("Thể loại");
        lblNewLabel_1_1_4.setFont(new Font("Verdana", Font.PLAIN, 13));

        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        // logo đã xóa — sẽ không set icon ở đây

        textField_6 = new JTextField();
        textField_6.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_6.setColumns(10);

        lblNewLabel_1_2 = new JLabel("Mã ISBN");
        lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField_7 = new JTextField();
        textField_7.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_7.setColumns(10);

        textField_8 = new JTextField();
        textField_8.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField_8.setColumns(10);

        lblNewLabel_1_1_4_1 = new JLabel("Lần xuất bản");
        lblNewLabel_1_1_4_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        lblNewLabel_1_1_4_1_1 = new JLabel("Số kệ");
        lblNewLabel_1_1_4_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_1 = new JButton("Tìm");
        ImageIcon searchIcon = null;
        try {
            Image img = new ImageIcon(this.getClass().getResource("search.png")).getImage().getScaledInstance(13, 17,
                    Image.SCALE_DEFAULT);
            searchIcon = new ImageIcon(img);
            btnNewButton_1.setIcon(searchIcon);
        } catch (Exception ex) {
            // nếu icon ko tìm thấy thì bỏ qua
        }
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng nhập mã sách để tìm.", "Thiếu dữ liệu",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                textField.setEditable(false);
                Connection con = DBInfo.conn();
                String query = "SELECT * FROM book WHERE bookid=?";
                int flag = 0;
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        flag = 1;
                        textField_1.setText(res.getString(2));
                        comboBox.setSelectedItem(res.getString(3));
                        comboBox_1.setSelectedItem(res.getString(4));
                        comboBox_2.setSelectedItem(res.getString(5));
                        comboBox_3.setSelectedItem(res.getString(6));
                        textField_6.setText(res.getString(7));
                        textField_7.setText(res.getString(8));
                        textField_8.setText(res.getString(9));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (flag == 0) {
                    JOptionPane.showMessageDialog(getParent(), "Không tìm thấy sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    textField.setEditable(true);
                    lblNewLabel_2.setIcon(null);
                    return;
                }
                // hiển thị QR nếu có
                try {
                    java.nio.file.Path p = Paths
                            .get("C:\\Users\\Shantam\\Desktop\\java\\libraryManagement\\qrCodes\\mainQR\\" + id + ".png");
                    if (Files.exists(p)) {
                        ImageIcon imgIcon2 = new ImageIcon(p.toString());
                        Image img = imgIcon2.getImage();
                        Image resize = img.getScaledInstance(200, 68, Image.SCALE_DEFAULT);
                        lblNewLabel_2.setIcon(new ImageIcon(resize));
                    } else {
                        lblNewLabel_2.setIcon(null);
                    }
                } catch (Exception ex) {
                    lblNewLabel_2.setIcon(null);
                }
            }
        });
        btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_1_1 = new JButton("Cập nhật");
        ImageIcon updateIcon = null;
        try {
            Image img = new ImageIcon(this.getClass().getResource("update.png")).getImage().getScaledInstance(13, 17,
                    Image.SCALE_DEFAULT);
            updateIcon = new ImageIcon(img);
            btnNewButton_1_1.setIcon(updateIcon);
        } catch (Exception ex) {
        }
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText().trim();
                String title = textField_1.getText().trim();
                String author = (comboBox.getSelectedItem() == null) ? "" : comboBox.getSelectedItem().toString();
                String subject = (comboBox_1.getSelectedItem() == null) ? "" : comboBox_1.getSelectedItem().toString();
                String publisher = (comboBox_2.getSelectedItem() == null) ? "" : comboBox_2.getSelectedItem().toString();
                String category = (comboBox_3.getSelectedItem() == null) ? "" : comboBox_3.getSelectedItem().toString();
                String isbn = textField_6.getText().trim();
                String edi = textField_7.getText().trim();
                String shelfNo = textField_8.getText().trim();

                if (id.isEmpty() || title.isEmpty() || isbn.isEmpty() || edi.isEmpty() || shelfNo.isEmpty()
                        || author.equals("Select") || subject.equals("Select") || publisher.equals("Select")
                        || category.equals("Select")) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng điền đầy đủ và hợp lệ thông tin.", "Thiếu thông tin",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Connection con = DBInfo.conn();
                int i = 0;
                String query = "UPDATE book SET title=?,author=?,subject=?,publisher=?,category=?,isbn=?,edition=?,shelfNo=? WHERE bookid=?";
                try {
                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setString(1, title);
                    ps.setString(2, author);
                    ps.setString(3, subject);
                    ps.setString(4, publisher);
                    ps.setString(5, category);
                    ps.setString(6, isbn);
                    ps.setString(7, edi);
                    ps.setString(8, shelfNo);
                    ps.setString(9, id);
                    i = ps.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (i == 1) {
                    JOptionPane.showMessageDialog(getParent(), "Cập nhật thành công!", "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                    reset();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Không thể cập nhật sách!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_1_1_1 = new JButton("Xóa");
        ImageIcon deleteIcon = null;
        try {
            Image img = new ImageIcon(this.getClass().getResource("deleteIcon.png")).getImage().getScaledInstance(13, 17,
                    Image.SCALE_DEFAULT);
            deleteIcon = new ImageIcon(img);
            btnNewButton_1_1_1.setIcon(deleteIcon);
        } catch (Exception ex) {
        }
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText().trim();
                String name = textField_1.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng nhập mã sách để xóa.", "Thiếu dữ liệu",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int num = JOptionPane.showConfirmDialog(getParent(),
                        "Bạn có chắc chắn muốn xóa sách \"" + name + "\"?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (num == JOptionPane.YES_OPTION) {
                    Connection con = DBInfo.conn();
                    String query = "DELETE FROM book WHERE bookid=?";
                    int flag = 0;
                    try {
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, id);
                        flag = ps.executeUpdate();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (flag == 0) {
                        JOptionPane.showMessageDialog(getParent(), "Không tìm thấy mã sách!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Đã xóa sách thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Files.deleteIfExists(Paths.get(
                                    "C:\\Users\\Shantam\\Desktop\\java\\libraryManagement\\qrCodes\\mainQR\\" + id + ".png"));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        reset();
                    }
                }
            }
        });
        btnNewButton_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnNewButton_1_1_2 = new JButton("Hủy bỏ");
        ImageIcon cancelIcon = null;
        try {
            Image img = new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage()
                    .getScaledInstance(13, 17, Image.SCALE_DEFAULT);
            cancelIcon = new ImageIcon(img);
            btnNewButton_1_1_2.setIcon(cancelIcon);
        } catch (Exception ex) {
        }
        btnNewButton_1_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnNewButton_1_1_2.setFont(new Font("Verdana", Font.PLAIN, 13));

        comboBox = new JComboBox(DBInfo.getValue("author"));
        comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));

        comboBox_1 = new JComboBox(DBInfo.getValue("subject"));
        comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 13));

        comboBox_2 = new JComboBox(DBInfo.getValue("publisher"));
        comboBox_2.setFont(new Font("Verdana", Font.PLAIN, 13));

        comboBox_3 = new JComboBox(DBInfo.getValue("category"));
        comboBox_3.setFont(new Font("Verdana", Font.PLAIN, 13));

        // giữ nguyên GroupLayout (layout như trước)
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap(361, Short.MAX_VALUE)
                        .addComponent(lblNewLabel).addGap(354))
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(50)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 49,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 49,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 51,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 60,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 60,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGap(42)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 182,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGap(60))
                                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(91)
                                        .addComponent(btnNewButton_1)
                                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(btnNewButton_1_1).addGap(37)))
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE,
                                                                        85, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblNewLabel_1_1_4_1, GroupLayout.PREFERRED_SIZE,
                                                                        60, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblNewLabel_1_1_4_1_1, GroupLayout.PREFERRED_SIZE,
                                                                        60, GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE))
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 94,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(21)))
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 182,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 182,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 182,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addComponent(btnNewButton_1_1_2).addGap(37))))
                                .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_2,
                                        GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE).addGap(54)))
                        .addGap(73)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(24).addComponent(lblNewLabel).addGap(43)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 17,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 23,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 17,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 17,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_1_4_1, GroupLayout.PREFERRED_SIZE, 17,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_1_4_1_1, GroupLayout.PREFERRED_SIZE, 20,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblNewLabel_1_1_3, GroupLayout.PREFERRED_SIZE, 17,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lblNewLabel_1_1_4, GroupLayout.PREFERRED_SIZE, 17,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                        .addGap(48)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 25,
                                                        Short.MAX_VALUE)
                                                .addComponent(btnNewButton_1_1_2, GroupLayout.PREFERRED_SIZE, 25,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 25,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGap(61))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()))));

        contentPane.setLayout(gl_contentPane);
    }
}
