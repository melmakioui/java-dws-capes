package data.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDatabase {

    Connection getConnection() throws SQLException;


}
