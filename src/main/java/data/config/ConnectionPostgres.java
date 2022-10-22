package data.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres implements ConnectionDatabase {

    private static final String URL = "jdbc:postgresql://localhost/moviesCatalogue?user=user&password=password";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
