package app;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread {
    private static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter writer;

    public static void setConnection(Socket s, BufferedReader br, PrintWriter pw) throws IOException {
        socket = s;
        reader = br;
        writer = pw;
    }

    public static String sendRequest(String request) throws IOException {
        writer.println(request);
        writer.flush();
        System.out.println("Request sent: " + request);
        return reader.readLine();
    }

    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
