package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql implements ConnectionDatabase  {

    private final String URL = "jdbc:mysql://localhost:3306/moviesCatalogue";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
    
}
