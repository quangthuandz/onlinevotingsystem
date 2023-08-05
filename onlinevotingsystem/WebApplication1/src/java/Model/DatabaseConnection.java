package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection conn;

    public DatabaseConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

                              // Connect to the database
                              String url = "jdbc:mysql://localhost:3306/onlinevotingsystem";
                              String username = "root";
                              String password = "123456";
                              conn = DriverManager.getConnection(url, username, password);
                    }
                    catch (ClassNotFoundException ex)
                    {
                              System.out.println("JDBC driver not found");
                    }
                    catch (SQLException ex)
                    {
                              System.out.println("Failed to connect to the database:"+ex.getMessage());
                    }
          }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Failed to close the database connection");
            }
        }
    }
}
