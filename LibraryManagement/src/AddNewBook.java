import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class AddNewBook extends JFrame {

    private JPanel contentPane;
    private JTextField textField_Id;
    private JTextField textField_Title;
    private JTextField textField_ISBN;
    private JTextField textField_Edition;
    private JTextField textField_Shelf;
    private JComboBox comboBoxAuthor, comboBoxSubject, comboBoxPublisher, comboBoxCategory;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddNewBook frame = new AddNewBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Sinh mã sách ngẫu nhiên (8 chữ số)
    public String generateBookId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int num = (int) (Math.random() * 9) + 1;
            id.append(num);
        }
        return id.toString();
    }

    // Thêm tên mới (Tác giả / Nhà XB / Môn học / Thể loại)
    public void addNames(String tableName, String value) {
        Connection con = DBInfo.conn();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE name='" + value + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(getParent(), value + " đã tồn tại.", "Dữ liệu trùng", JOptionPane.ERROR_MESSAGE);
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO " + tableName + "(name) VALUES(?)");
                ps.setString(1, value);
                int i = ps.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(getParent(), "Thêm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new AddNewBook().setVisible(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Làm mới form
    public void reset() {
        textField_Id.setText(generateBookId());
        textField_Title.setText("");
        textField_ISBN.setText("");
        textField_Edition.setText("");
        textField_Shelf.setText("");
        comboBoxAuthor.setSelectedIndex(0);
        comboBoxSubject.setSelectedIndex(0);
        comboBoxPublisher.setSelectedIndex(0);
        comboBoxCategory.setSelectedIndex(0);
    }

    // Giao diện
    public AddNewBook() {
        setResizable(false);
        setTitle("Thêm Sách Mới");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 480);
        setLocationRelativeTo(this);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAddNew = new JMenu("Thêm Mới");
        mnAddNew.setFont(new Font("Verdana", Font.PLAIN, 13));
        menuBar.add(mnAddNew);

        JMenuItem mntmAuthor = new JMenuItem("Tác giả");
        mntmAuthor.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("Nhập tên tác giả:");
            if (str != null && !str.trim().isEmpty()) addNames("author", str);
        });
        mnAddNew.add(mntmAuthor);

        JMenuItem mntmPublisher = new JMenuItem("Nhà xuất bản");
        mntmPublisher.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("Nhập tên nhà xuất bản:");
            if (str != null && !str.trim().isEmpty()) addNames("publisher", str);
        });
        mnAddNew.add(mntmPublisher);

        JMenuItem mntmSubject = new JMenuItem("Môn học");
        mntmSubject.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("Nhập tên môn học:");
            if (str != null && !str.trim().isEmpty()) addNames("subject", str);
        });
        mnAddNew.add(mntmSubject);

        JMenuItem mntmCategory = new JMenuItem("Thể loại");
        mntmCategory.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("Nhập tên thể loại:");
            if (str != null && !str.trim().isEmpty()) addNames("category", str);
        });
        mnAddNew.add(mntmCategory);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("THÊM SÁCH MỚI");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 18));

        JLabel lblBookId = new JLabel("Mã sách:");
        JLabel lblName = new JLabel("Tên sách:");
        JLabel lblAuthor = new JLabel("Tác giả:");
        JLabel lblSubject = new JLabel("Môn học:");
        JLabel lblPublisher = new JLabel("Nhà xuất bản:");
        JLabel lblCategory = new JLabel("Thể loại:");
        JLabel lblISBN = new JLabel("Mã ISBN:");
        JLabel lblEdition = new JLabel("Ấn bản:");
        JLabel lblShelf = new JLabel("Số kệ:");

        textField_Id = new JTextField(generateBookId());
        textField_Id.setEditable(false);

        textField_Title = new JTextField();
        textField_ISBN = new JTextField();
        textField_Edition = new JTextField();
        textField_Shelf = new JTextField();

        comboBoxAuthor = new JComboBox(DBInfo.getValue("author"));
        comboBoxSubject = new JComboBox(DBInfo.getValue("subject"));
        comboBoxPublisher = new JComboBox(DBInfo.getValue("publisher"));
        comboBoxCategory = new JComboBox(DBInfo.getValue("category"));

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            String id = textField_Id.getText();
            String title = textField_Title.getText();
            String author = comboBoxAuthor.getSelectedItem().toString();
            String subject = comboBoxSubject.getSelectedItem().toString();
            String publisher = comboBoxPublisher.getSelectedItem().toString();
            String category = comboBoxCategory.getSelectedItem().toString();
            String isbn = textField_ISBN.getText();
            String edi = textField_Edition.getText();
            String shelf = textField_Shelf.getText();

            if (title.isEmpty() || author.equals("Select") || subject.equals("Select") || publisher.equals("Select")
                    || category.equals("Select") || isbn.isEmpty() || edi.isEmpty() || shelf.isEmpty()) {
                JOptionPane.showMessageDialog(getParent(), "Vui lòng nhập đầy đủ thông tin!", "Thiếu dữ liệu",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(getParent(), "Xác nhận thêm sách này?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Connection con = DBInfo.conn();
                try {
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO book(bookid, title, author, subject, publisher, category, isbn, edition, shelfNo) VALUES(?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, id);
                    ps.setString(2, title);
                    ps.setString(3, author);
                    ps.setString(4, subject);
                    ps.setString(5, publisher);
                    ps.setString(6, category);
                    ps.setString(7, isbn);
                    ps.setString(8, edi);
                    ps.setString(9, shelf);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(getParent(), "Thêm sách thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(getParent(), "Lỗi khi thêm sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnReset = new JButton("Làm lại");
        btnReset.addActionListener(e -> reset());

        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> setVisible(false));

        GroupLayout gl = new GroupLayout(contentPane);
        contentPane.setLayout(gl);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
                .addComponent(lblTitle)
                .addGroup(gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lblBookId).addComponent(lblName).addComponent(lblAuthor)
                                .addComponent(lblSubject).addComponent(lblPublisher).addComponent(lblCategory))
                        .addGroup(gl.createParallelGroup(Alignment.LEADING)
                                .addComponent(textField_Id).addComponent(textField_Title).addComponent(comboBoxAuthor)
                                .addComponent(comboBoxSubject).addComponent(comboBoxPublisher)
                                .addComponent(comboBoxCategory))
                        .addGroup(gl.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblISBN).addComponent(lblEdition).addComponent(lblShelf))
                        .addGroup(gl.createParallelGroup(Alignment.LEADING)
                                .addComponent(textField_ISBN).addComponent(textField_Edition)
                                .addComponent(textField_Shelf)))
                .addGroup(gl.createSequentialGroup()
                        .addComponent(btnSave)
                        .addComponent(btnReset)
                        .addComponent(btnCancel))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(lblTitle)
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBookId).addComponent(textField_Id)
                        .addComponent(lblISBN).addComponent(textField_ISBN))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblName).addComponent(textField_Title)
                        .addComponent(lblEdition).addComponent(textField_Edition))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAuthor).addComponent(comboBoxAuthor)
                        .addComponent(lblShelf).addComponent(textField_Shelf))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSubject).addComponent(comboBoxSubject))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPublisher).addComponent(comboBoxPublisher))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCategory).addComponent(comboBoxCategory))
                .addGroup(gl.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnSave).addComponent(btnReset).addComponent(btnCancel))
        );
    }
}
