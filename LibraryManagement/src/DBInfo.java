import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DBInfo
{
    static
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("⚙️ Đã tải driver MySQL...");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không thể tải driver MySQL!");
            e.printStackTrace();
        }
    }

    public static Connection conn()
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagement","root","Phuoc.@123");
            System.out.println("✅ Kết nối cơ sở dữ liệu thành công...");
        } catch (SQLException e) {
            System.out.println("❌ Kết nối cơ sở dữ liệu thất bại!");
            e.printStackTrace();
        }
        return con;
    }

    public static Vector<String> getValue(String name)
    {
        Vector<String> v = new Vector<>();
        v.add("Chọn");
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM " + name + " ORDER BY NAME";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next())
            {
                String values = res.getString(2);
                v.add(values);
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy dữ liệu từ bảng " + name);
            e.printStackTrace();
        }
        return v;
    }

    public static Vector<Vector> outerVector;
    public static Vector colsName;
    public static void allBooks() throws SQLException
    {
        colsName = new Vector<>();
        outerVector = new Vector<Vector>();
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM book ORDER BY title";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        ResultSetMetaData rsmd = ps.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++)
        {
            colsName.add(rsmd.getColumnName(i));
        }
        while (res.next())
        {
            Vector v = new Vector<>();
            for (int i = 1; i <= cols; i++)
            {
                v.add(res.getString(i));
            }
            outerVector.add(v);
        }
        System.out.println("📚 Đã tải danh sách tất cả sách.");
    }

    public static Vector<Vector> outerVector1;
    public static Vector colsName1;
    public static void searchBooks_by(String itemName, String values) throws SQLException
    {
        colsName1 = new Vector<>();
        outerVector1 = new Vector<Vector>();
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM book WHERE " + itemName + "=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, values);
        ResultSet res = ps.executeQuery();
        ResultSetMetaData rsmd = ps.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++)
        {
            colsName1.add(rsmd.getColumnName(i));
        }
        while (res.next())
        {
            Vector v1 = new Vector<>();
            for (int i = 1; i <= cols; i++)
            {
                v1.add(res.getString(i));
            }
            outerVector1.add(v1);
        }
        System.out.println("🔍 Đã tìm kiếm sách theo " + itemName + " = " + values);
    }

    public static Vector<Vector> outerVector2;
    public static Vector colsName2;
    public static void viewLibrarians(String usertype) throws SQLException
    {
        colsName2 = new Vector<>();
        outerVector2 = new Vector<Vector>();
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM registration WHERE usertype='" + usertype + "' ORDER BY name";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        ResultSetMetaData rsmd = ps.getMetaData();
        int cols = rsmd.getColumnCount() - 3;
        for (int i = 1; i <= cols; i++)
        {
            colsName2.add(rsmd.getColumnName(i));
        }
        while (res.next())
        {
            Vector v2 = new Vector<>();
            for (int i = 1; i <= cols; i++)
            {
                v2.add(res.getString(i));
            }
            outerVector2.add(v2);
        }
        System.out.println("👥 Đã tải danh sách người dùng loại: " + usertype);
    }

    public static String value;
    public static String getNotice()
    {
        Connection con = DBInfo.conn();
        String query = "SELECT * FROM notice";
        value = "";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next())
            {
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
