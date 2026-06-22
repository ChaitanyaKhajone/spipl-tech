package configaration;
import java.sql.Connection;
import java.sql.DriverManager;
public class config
{
    private static final String URL = "jdbc:mysql://localhost:3306/clinic_db";
    private static final String USER = "clinic";
    private static final String PASSWORD = "clinic@DB#2026";
    public static Connection getConnection()
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL , USER , PASSWORD);
        }
        catch (Exception e)
        {
            System.err.println("\n Error = [ " + e.toString() + " ]\n");
        }
        return con;
    }
}