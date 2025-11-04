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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentPage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentPage frame = new StudentPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentPage() {
        setTitle("Student Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        // chia bố cục 2 hàng 3 cột
        contentPane.setLayout(new GridLayout(2, 3, 10, 10));
        setContentPane(contentPane);

        // Logo bên trái
        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        Image imgLogo = new ImageIcon(this.getClass().getResource("logoMain.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        lblLogo.setIcon(new ImageIcon(imgLogo));
        contentPane.add(lblLogo);

        // Tiêu đề ở giữa
        JLabel lblTitle = new JLabel("Student Page");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        contentPane.add(lblTitle);

        // Ô trống để căn đều layout
        JLabel lblEmpty = new JLabel("");
        contentPane.add(lblEmpty);

        // Nút View Books
        JButton btnViewBooks = new JButton("View Books");
        btnViewBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewBooks().setVisible(true);
            }
        });
        Image viewBooksImg = new ImageIcon(this.getClass().getResource("viewBooks.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnViewBooks.setIcon(new ImageIcon(viewBooksImg));
        contentPane.add(btnViewBooks);

        // Nút View Notice
        JButton btnViewNotice = new JButton("View Notice");
        btnViewNotice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewNotice().setVisible(true);
            }
        });
        Image viewNoticeImg = new ImageIcon(this.getClass().getResource("notice.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnViewNotice.setIcon(new ImageIcon(viewNoticeImg));
        btnViewNotice.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnViewNotice);

        // Nút Issue Book
        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IssueBooks().setVisible(true);
            }
        });
        btnIssueBook.setFont(new Font("Verdana", Font.PLAIN, 15));
        Image issueImg = new ImageIcon(this.getClass().getResource("issue.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnIssueBook.setIcon(new ImageIcon(issueImg));
        contentPane.add(btnIssueBook);

        // Nút Return Book
        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReturnBook().setVisible(true);
            }
        });
        Image returnImg = new ImageIcon(this.getClass().getResource("return-book-1-560407.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnReturnBook.setIcon(new ImageIcon(returnImg));
        btnReturnBook.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnReturnBook);

        // Nút View Static
        JButton btnViewStatic = new JButton("View Static");
        btnViewStatic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentStatic().setVisible(true);
            }
        });
        Image staticImg = new ImageIcon(this.getClass().getResource("Statics.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnViewStatic.setIcon(new ImageIcon(staticImg));
        btnViewStatic.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnViewStatic);

        // Nút Edit Credential
        JButton btnEditCredential = new JButton("Edit Credential");
        btnEditCredential.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PasswordCheck().setVisible(true);
            }
        });
        Image settingImg = new ImageIcon(this.getClass().getResource("setting.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
        btnEditCredential.setIcon(new ImageIcon(settingImg));
        btnEditCredential.setFont(new Font("Verdana", Font.PLAIN, 15));
        contentPane.add(btnEditCredential);
    }
}
