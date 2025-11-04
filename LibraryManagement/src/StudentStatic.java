import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class StudentStatic extends JFrame {

    private JPanel contentPane;
    public static Vector<Vector> values;
    public static Vector cols;
    private JTable table, table_all;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentStatic frame = new StudentStatic();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentStatic() {
        setTitle("Xem danh sách sách đang mượn");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 421, 300);
        setLocationRelativeTo(this);

        contentPane = new JPanel();
        JFrame frame = new JFrame();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JLabel lblTieuDe = new JLabel("DANH SÁCH SÁCH ĐANG MƯỢN");
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel lblTimKiem = new JLabel("Tìm kiếm theo mã sách:");
        lblTimKiem.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnTim = new JButton("Tìm");
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String value = textField.getText().trim();
                if (value.isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Vui lòng nhập mã sách để tìm kiếm.", "Thiếu thông tin",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String query = "SELECT * FROM issueBooks WHERE bookId='" + value + "' AND returnStatus='pending'";
                try {
                    Connection con = DBInfo.conn();
                    PreparedStatement ps = con.prepareStatement(query);
                    values = new Vector<>();
                    cols = new Vector<>();
                    ResultSet res = ps.executeQuery();
                    ResultSetMetaData rsmd = res.getMetaData();
                    int countCols = rsmd.getColumnCount();

                    for (int i = 2; i <= countCols; i++) {
                        cols.add(rsmd.getColumnName(i));
                    }

                    while (res.next()) {
                        Vector<String> v = new Vector<>();
                        for (int i = 2; i <= countCols; i++) {
                            v.add(res.getString(i));
                        }
                        values.add(v);
                    }

                    if (values.isEmpty()) {
                        JOptionPane.showMessageDialog(getParent(), "Không tìm thấy sách đang mượn với mã này.",
                                "Không có kết quả", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        table = new JTable(values, cols);
                        JScrollPane pane = new JScrollPane(table);
                        frame.getContentPane().add(pane);
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnTim.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnXemTatCa = new JButton("Xem tất cả sách đang mượn");
        btnXemTatCa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM issueBooks WHERE returnStatus='pending'";
                try {
                    Connection con = DBInfo.conn();
                    PreparedStatement ps = con.prepareStatement(query);
                    values = new Vector<>();
                    cols = new Vector<>();
                    ResultSet res = ps.executeQuery();
                    ResultSetMetaData rsmd = res.getMetaData();
                    int countCols = rsmd.getColumnCount();

                    for (int i = 2; i <= countCols; i++) {
                        cols.add(rsmd.getColumnName(i));
                    }

                    while (res.next()) {
                        Vector<String> v = new Vector<>();
                        for (int i = 2; i <= countCols; i++) {
                            v.add(res.getString(i));
                        }
                        values.add(v);
                    }

                    if (values.isEmpty()) {
                        JOptionPane.showMessageDialog(getParent(), "Hiện tại không có sách nào đang được mượn.",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        table_all = new JTable(values, cols);
                        JScrollPane pane = new JScrollPane(table_all);
                        frame.getContentPane().add(pane);
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnXemTatCa.setFont(new Font("Verdana", Font.PLAIN, 13));

        JButton btnHuy = new JButton("Đóng");
        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnHuy.setFont(new Font("Verdana", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.PLAIN, 13));
        textField.setColumns(10);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGap(11)
                                        .addComponent(lblTimKiem)
                                        .addGap(18)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18)
                                        .addComponent(btnTim))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGap(43)
                                        .addComponent(btnXemTatCa)
                                        .addGap(18)
                                        .addComponent(btnHuy)))
                        .addContainerGap(8, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addGap(36)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblTimKiem, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(67)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnXemTatCa, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHuy))
                        .addContainerGap(66, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
