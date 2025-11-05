import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DBInfo {

    static {
        try {
            // Driver MySQL mới hơn
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("⚙️ Đã tải driver MySQL...");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không thể tải driver MySQL!");
            e.printStackTrace();
        }
    }

    // Kết nối cơ sở dữ liệu
    public static Connection conn() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    "Phuoc.@123"
            );
            System.out.println("✅ Kết nối cơ sở dữ liệu thành công...");
        } catch (SQLException e) {
            System.out.println("❌ Kết nối cơ sở dữ liệu thất bại!");
            e.printStackTrace();
        }
        return con;
    }

    // ================= LẤY GIÁ TRỊ CHO COMBOBOX =================
    public static Vector<String> getValue(String name) {
        Vector<String> v = new Vector<>();
        v.add("Chọn");
        Connection con = DBInfo.conn();
        String query = "";

        switch (name.toLowerCase()) {
            case "author":
                query = "SELECT DISTINCT author FROM book WHERE author IS NOT NULL AND author != '' ORDER BY author";
                break;
            case "publisher":
                query = "SELECT DISTINCT publisher FROM book WHERE publisher IS NOT NULL AND publisher != '' ORDER BY publisher";
                break;
            case "subject":
                query = "SELECT DISTINCT subject FROM book WHERE subject IS NOT NULL AND subject != '' ORDER BY subject";
                break;
            case "category":
                query = "SELECT DISTINCT category FROM book WHERE category IS NOT NULL AND category != '' ORDER BY category";
                break;
            default:
                System.out.println("⚠️ Không tìm thấy cột phù hợp cho: " + name);
                return v;
        }

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {

            while (res.next()) {
                String value = res.getString(1);
                if (value != null && !value.trim().isEmpty()) {
                    v.add(value.trim());
                }
            }
            System.out.println("✅ Đã lấy danh sách giá trị cho " + name + ": " + (v.size() - 1) + " mục.");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy dữ liệu cho " + name);
            e.printStackTrace();
        }
        return v;
    }

    // ================= HIỂN THỊ TẤT CẢ SÁCH =================
    public static Vector<Vector> outerVector;
    public static Vector colsName;

    public static void allBooks() throws SQLException {
        colsName = new Vector<>();
        outerVector = new Vector<>();
        Connection con = DBInfo.conn();

        String query = "SELECT * FROM book ORDER BY title";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            colsName.add(rsmd.getColumnName(i));
        }

        while (res.next()) {
            Vector<String> v = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                v.add(res.getString(i));
            }
            outerVector.add(v);
        }
        System.out.println("📚 Đã tải danh sách tất cả sách (" + outerVector.size() + " mục).");
    }

    // ================= TÌM KIẾM SÁCH THEO CỘT =================
    public static Vector<Vector> outerVector1;
    public static Vector colsName1;

    public static void searchBooks_by(String itemName, String values) throws SQLException {
        colsName1 = new Vector<>();
        outerVector1 = new Vector<>();
        Connection con = DBInfo.conn();

        // Chuyển từ tiếng Việt trong giao diện sang tên cột trong DB
        String column = "";
        switch (itemName.toLowerCase()) {
            case "tác giả":
                column = "author";
                break;
            case "chủ đề":
                column = "subject";
                break;
            case "nhà xuất bản":
                column = "publisher";
                break;
            case "thể loại":
                column = "category";
                break;
            default:
                System.out.println("⚠️ Không xác định được cột cho " + itemName);
                return;
        }

        String query = "SELECT * FROM book WHERE " + column + " = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, values);
        ResultSet res = ps.executeQuery();

        ResultSetMetaData rsmd = res.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            colsName1.add(rsmd.getColumnName(i));
        }

        while (res.next()) {
            Vector<String> v1 = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                v1.add(res.getString(i));
            }
            outerVector1.add(v1);
        }

        System.out.println("🔍 Đã tìm kiếm sách theo " + column + " = " + values + " (" + outerVector1.size() + " kết quả).");
    }

    // ================= XEM NGƯỜI DÙNG (THỦ THƯ / GIẢNG VIÊN) =================
    public static Vector<Vector> outerVector2;
    public static Vector colsName2;

    public static void viewLibrarians(String usertype) throws SQLException {
        colsName2 = new Vector<>();
        outerVector2 = new Vector<>();
        Connection con = DBInfo.conn();

        String query = "SELECT * FROM registration WHERE usertype=? ORDER BY name";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usertype);
        ResultSet res = ps.executeQuery();

        ResultSetMetaData rsmd = res.getMetaData();
        int cols = rsmd.getColumnCount() - 3;

        for (int i = 1; i <= cols; i++) {
            colsName2.add(rsmd.getColumnName(i));
        }

        while (res.next()) {
            Vector<String> v2 = new Vector<>();
            for (int i = 1; i <= cols; i++) {
                v2.add(res.getString(i));
            }
            outerVector2.add(v2);
        }

        System.out.println("👥 Đã tải danh sách người dùng loại: " + usertype + " (" + outerVector2.size() + " người).");
    }

    // ================= THÔNG BÁO =================
    public static String value;

    public static String getNotice() {
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM notice";
        value = "";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {

            while (res.next()) {
                value = res.getString(2);
                System.out.println("📢 Thông báo hiện tại: " + value);
            }
        } catch (SQLException e2) {
            System.out.println("❌ Lỗi khi lấy thông báo!");
            e2.printStackTrace();
        }
        return value;
    }
}
