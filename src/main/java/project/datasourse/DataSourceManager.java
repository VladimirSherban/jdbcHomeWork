package project.datasourse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceManager {
    private static final String URL = "jdbc:postgresql://localhost:5433/skypro";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "S09DFV123abu1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}
