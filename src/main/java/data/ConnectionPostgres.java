package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {

    private static final String URL = "jdbc:postgresql://localhost/moviesCatalogue?user=user&password=password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
