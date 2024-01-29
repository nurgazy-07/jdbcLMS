package java12.convig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcConfig {
    private final static String url = "jdbc:postgresql://localhost:5432/jdbc";
    private final static String name = "postgres";
    private final static String password = "1234";
    public static Connection getConnection(){
        Connection connection = null;
        try {
         connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
