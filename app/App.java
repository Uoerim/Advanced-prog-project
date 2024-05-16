package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/LoadPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("EngiVerse");
        stage.getIcons().add(new Image("file:resources/assets/logo.png"));
        stage.setResizable(false);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Socket s = null;
            BufferedReader br = null;
            PrintWriter pr = null;
            while (true) {
                try {
                    s = new Socket("localhost", 1234);
                    pr = new PrintWriter(s.getOutputStream());
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    ServerConnection.setConnection(s, br, pr);

                } catch (IOException e) {
                    continue;
                }
                if (s.isConnected()) {
                    System.out.println("Connected to server");
                    break;
                }
            }

            while (true) {
                String message;
                try {
                    System.out.println("1");
                    message = br.readLine();
                    if (message != null) {
                        System.out.println("2");
                        if (!message.equals("FAILED")) {
                            System.out.println("3");
                            Platform.runLater(() -> {
                                try {
                                    stage.close();
                                    Parent root2 = FXMLLoader.load(getClass().getResource("../resources/AuthenticationPage.fxml"));
                                    Scene scene2 = new Scene(root2);
                                    stage.setScene(scene2);
                                    stage.show();
                                    return;
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("4");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.net.Socket;
// import java.net.UnknownHostException;
// import java.util.Scanner;

// public class Main {
// public static void main(String[] args) throws UnknownHostException,
// IOException {
// Socket s = new Socket("localhost", 1234);
// PrintWriter pr = new PrintWriter(s.getOutputStream());

// Scanner sc = new Scanner(System.in);
// String message = sc.nextLine();
// pr.println(message);
// pr.flush();
// }
// }