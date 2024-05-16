import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnect extends Thread {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/socialapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection connectToDatabase() throws InterruptedException {
        Connection connection = null;
        boolean connected = false;
        
        while (!connected) { // Retry 3 times
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                connected = true;
            } catch (ClassNotFoundException | SQLException e) {
                if (e instanceof CommunicationsException) {
                    return null;
                } else {
                    e.printStackTrace();
                    break;
                }
            }
            Thread.sleep(1000);
        }
        
        return connection;
    }
}
