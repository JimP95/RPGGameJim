package SQL;

/**
 * Created by POU on 25-02-2016.
 */
import java.sql.* ;

public class DBHelper
{

    public Connection sqlConnection(){

        String url = "jdbc:mysql://localhost:3306/rpggamedb";
        String username = "jim";
        String password = "password";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println("Got an sqlConnection exception!");
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
