import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class IssueBooks extends JFrame {

    private JPanel contentPane;
    private JTextField txtBookId;
    private JTextField txtTitle;
    private JComboBox comboBoxAuthor;
    private JTextField txtUsername;
    private JDateChooser dateChooser;
    private JTextField txtIssueDate;
    private String date;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                IssueBooks frame = new IssueBooks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        txtBookId.setText(null);
        txtTitle.setText(null);
        txtUsername.setText(null);
        comboBoxAuthor.setSelectedIndex(0);
        txtIssueDate.setText(date);
        dateChooser.setCalendar(null);
        txtTitle.setEditable(true);
        comboBoxAuthor.setEnabled(true);
    }

    public IssueBooks() {
        setTitle("Mượn sách");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 520);
        setLocationRelativeTo(this);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("📖 Mượn sách thư viện");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 18));

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblBookId = new JLabel("Mã sách:");
        lblBookId.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblBookTitle = new JLabel("Tên sách:");
        lblBookTitle.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblAuthor = new JLabel("Tác giả:");
        lblAuthor.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblIssueDate = new JLabel("Ngày mượn:");
        lblIssueDate.setFont(new Font("Verdana", Font.PLAIN, 13));

        JLabel lblDueDate = new JLabel("Ngày trả dự kiến:");
        lblDueDate.setFont(new Font("Verdana", Font.PLAIN, 13));

        // Lấy ngày hiện tại
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtBookId = new JTextField();
        txtBookId.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtBookId.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String id = txtBookId.getText();
                Connection con = DBInfo.conn();
                String query = "SELECT * FROM book WHERE bookId=?";
                int flag = 0;
                try {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        flag = 1;
                        String title = res.getString(2);
                        String author = res.getString(3);
                        txtBookId.setText(id);
                        txtTitle.setText(title);
                        comboBoxAuthor.setSelectedItem(author);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (flag == 0) {
                    JOptionPane.showMessageDialog(getParent(), "Mã sách không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    txtTitle.setEditable(false);
                    comboBoxAuthor.setEnabled(false);
                }
            }
        });

        txtTitle = new JTextField();
        txtTitle.setFont(new Font("Verdana", Font.PLAIN, 13));

        comboBoxAuthor = new JComboBox(DBInfo.getValue("author"));
        comboBoxAuthor.setFont(new Font("Verdana", Font.PLAIN, 13));

        txtIssueDate = new JTextField(date);
        txtIssueDate.setEditable(false);
        txtIssueDate.setFont(new Font("Verdana", Font.PLAIN, 13));

        Date noPastDates = new Date();
        Calendar today = new GregorianCalendar();
        today.setTime(noPastDates);
        today.add(today.DATE, 15);

        dateChooser = new JDateChooser();
        dateChooser.setMinSelectableDate(noPastDates);
        dateChooser.setMaxSelectableDate(today.getTime());
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnIssue = new JButton("Xác nhận mượn");
        btnIssue.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnIssue.addActionListener((ActionEvent e) -> {
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            String username = txtUsername.getText();
            String bookId = txtBookId.getText();
            String title = txtTitle.getText();
            String author = comboBoxAuthor.getSelectedItem().toString();
            String issueDate = txtIssueDate.getText();
            String dueDate = dFormat.format(dateChooser.getDate());

            if (username.isEmpty() || bookId.isEmpty() || title.isEmpty() ||
                    author.equalsIgnoreCase("Select") || issueDate.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(getParent(), "Vui lòng điền đầy đủ thông tin!", "Thiếu thông tin", JOptionPane.ERROR_MESSAGE);
            } else {
                Connection con = DBInfo.conn();
                try {
                    String sql = "SELECT * FROM registration WHERE username=?";
                    PreparedStatement ps_username = con.prepareStatement(sql);
                    ps_username.setString(1, username);
                    ResultSet res = ps_username.executeQuery();
                    if (res.next()) {
                        String sqlId = "SELECT * FROM book WHERE bookId=?";
                        PreparedStatement ps_bookId = con.prepareStatement(sqlId);
                        ps_bookId.setString(1, bookId);
                        ResultSet res_id = ps_bookId.executeQuery();
                        if (res_id.next()) {
                            String query = "INSERT INTO issueBooks (username, bookId, title, author, issueDate, dueDate, returnStatus) VALUES (?,?,?,?,?,?,?)";
                            PreparedStatement ps = con.prepareStatement(query);
                            ps.setString(1, username);
                            ps.setString(2, bookId);
                            ps.setString(3, title);
                            ps.setString(4, author);
                            ps.setString(5, issueDate);
                            ps.setString(6, dueDate);
                            ps.setString(7, "Chưa trả");
                            int flag = ps.executeUpdate();

                            if (flag == 0) {
                                JOptionPane.showMessageDialog(getParent(), "Sách đã được mượn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(getParent(), "Mượn sách thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                                reset();
                            }
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Không tìm thấy sách với mã này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Tên đăng nhập không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        });

        JButton btnCancel = new JButton("Hủy bỏ");
        btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnCancel.addActionListener(e -> setVisible(false));

        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
                gl.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addGroup(gl.createSequentialGroup()
                                .addGap(40)
                                .addGroup(gl.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblUsername)
                                        .addComponent(lblBookId)
                                        .addComponent(lblBookTitle)
                                        .addComponent(lblAuthor)
                                        .addComponent(lblIssueDate)
                                        .addComponent(lblDueDate))
                                .addGap(20)
                                .addGroup(gl.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(txtUsername)
                                        .addComponent(txtBookId)
                                        .addComponent(txtTitle)
                                        .addComponent(comboBoxAuthor, 0, 200, Short.MAX_VALUE)
                                        .addComponent(txtIssueDate)
                                        .addComponent(dateChooser))
                                .addGap(40))
                        .addGroup(gl.createSequentialGroup()
                                .addGap(100)
                                .addComponent(btnIssue)
                                .addGap(30)
                                .addComponent(btnCancel)
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
                gl.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl.createSequentialGroup()
                                .addGap(15)
                                .addComponent(lblTitle)
                                .addGap(30)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUsername)
                                        .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBookId)
                                        .addComponent(txtBookId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBookTitle)
                                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAuthor)
                                        .addComponent(comboBoxAuthor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblIssueDate)
                                        .addComponent(txtIssueDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblDueDate)
                                        .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(25)
                                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnIssue)
                                        .addComponent(btnCancel))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl);
    }
}
