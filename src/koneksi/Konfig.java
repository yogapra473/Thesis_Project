package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Konfig {
    private static Connection connection;

    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/spksiswaprestasi";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("KONEKSI KE DATABASE GAGAL: " + e.getMessage());
        }
        return connection;
    }

    public static Statement createStatement() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = configDB();
        }
        return connection.createStatement();
    }
}
