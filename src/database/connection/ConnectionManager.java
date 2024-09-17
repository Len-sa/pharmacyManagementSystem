package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
   private static final String userName = "your-user-name";
    private static final String password ="your-password";
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=test_db;encrypt=false;";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("New connection instantiated!");
        }
        return connection;
    }

    public static void closeConnection() {
            try {
                if(connection != null) {
                    connection.close();
                    System.out.println("connection Closed!");
                }
            } catch (SQLException e) {
                System.out.println("Error while trying to close connection." + e.getMessage());
            }
    }
}
