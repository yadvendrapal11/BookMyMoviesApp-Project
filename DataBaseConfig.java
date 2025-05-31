package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    static String url = "jdbc:mysql://localhost:3306/bookmymovies";
    static String username = "root";
    static String password = "Anoop@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url , username , password);

    }
}
