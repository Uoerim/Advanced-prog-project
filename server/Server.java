
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;


public class Server extends Thread {
    private static volatile int usersCount = 0;

    public static void main(String[] args) throws InterruptedException {
        ServerSocket ss = null;
        Connection connection = null;
        try {
            ss = new ServerSocket(1234);
            System.out.println("Server started. Waiting for clients...");
            while (connection == null) {
                Thread.sleep(500);
                connection = SqlConnect.connectToDatabase();
                System.out.println("Failed to connect to database. Retrying...");
            }
            System.out.println("Connected to database");

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected: " + ++usersCount);
                new ClientHandler(s, connection).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ss != null) ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}