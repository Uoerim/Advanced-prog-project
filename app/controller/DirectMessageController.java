package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import javax.swing.UIDefaults.LazyValue;

import app.ServerConnection;
import app.classes.Post.Post;
import app.classes.User.Session;
import app.classes.User.User;
import app.handlers.SceneHandler;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class DirectMessageController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button viewProfileBtn;
    @FXML
    private Button viewFriendsBtn;
    @FXML
    private Button viewFriendsRequestsBtn;
    @FXML
    private Button directMessagingBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button postBtn;
    @FXML
    private Button viewPostBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Label postTextLabel;
    @FXML
    private TextArea postText;
    @FXML
    private VBox messageArea;
    @FXML
    private VBox friendsArea;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label bioLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label friendsCountLabel;
    @FXML
    private VBox mainView;
    @FXML
    private TextField findUser;
    @FXML
    private Button startChatBtn;
    @FXML
    private TextField messageText;
    @FXML
    private Button sendMessageBtn;
    @FXML
    private Button SearchBtn;
    @FXML
    private ScrollPane scrollPane;

    Thread thread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/HomePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == SearchBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/SearchPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewProfileBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ProfilePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsRequestsBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsRequestPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == directMessagingBtn) {
            // do nothing
        } else if (event.getSource() == settingsBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/SettingsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == logoutBtn) {
            if (thread != null) {
                thread.interrupt();
            }
            Session.setUser(null);
            try {
                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/AuthenticationPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == startChatBtn) {
            if (findUser.getText().equals(Session.getUser().getUsername())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Same User");
                alert.setContentText("You cant message yourself!");
                alert.showAndWait();
                return;
            }
            if (findUser.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty textfield");
                alert.setContentText("You cant talk to no body?");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest(
                    "REQ:CHECKIFMESSAGE" + ":" + Session.getUser().getUsername() + ":" + findUser.getText());
            if (res.equals("NOTFOUND")) {
                // alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("User not found");
                alert.setContentText("The user you are trying to message does not exist.");
                alert.showAndWait();
                return;
            }
            Session.setDmUser(findUser.getText());
            System.out.println(res);
            messageArea.getChildren().clear();
            HBox startHBox = new HBox();
            startHBox.setAlignment(Pos.CENTER);
            Label startLabel = new Label("Started Chat with " + findUser.getText());
            startLabel.setFont(new Font("Arial", 20));
            startLabel.setTextFill(Color.WHITE);
            startHBox.getChildren().add(startLabel);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Chat Started");
            alert.setContentText("You have started a chat with " + findUser.getText());
            alert.showAndWait();
            // messageArea.getChildren().add(startHBox);
            // if (!res.equals("/?Nomessagesyet")) {
            // String[] messages = res.split(",");
            // for (String message : messages) {
            // String[] messageParts = message.split("\\|");
            // if (messageParts[0].equals("u")) {
            // HBox messageHBox = new HBox();
            // messageHBox.setAlignment(Pos.CENTER_RIGHT);
            // messageHBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

            // HBox messageHBox2 = new HBox();
            // messageHBox2.setAlignment(Pos.CENTER);
            // messageHBox2.setPrefHeight(100);
            // messageHBox2.setPrefWidth(200);
            // messageHBox2.setStyle("-fx-background-color: #075ea6; -fx-background-radius:
            // 20px;");

            // Label messageLabel = new Label(messageParts[1]);
            // messageLabel.setFont(new Font("Arial", 20));
            // messageLabel.setTextFill(Color.WHITE);
            // messageHBox2.getChildren().add(messageLabel);

            // messageHBox.getChildren().add(messageHBox2);
            // messageArea.getChildren().add(messageHBox);
            // } else {
            // HBox messageHBox = new HBox();
            // messageHBox.setAlignment(Pos.CENTER_LEFT);
            // messageHBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

            // HBox messageHBox2 = new HBox();
            // messageHBox2.setAlignment(Pos.CENTER);
            // messageHBox2.setPrefHeight(100);
            // messageHBox2.setPrefWidth(200);
            // messageHBox2.setStyle("-fx-background-color: #6e6e6e; -fx-background-radius:
            // 20px;");

            // Label messageLabel = new Label(messageParts[1]);
            // messageLabel.setFont(new Font("Arial", 20));
            // messageLabel.setTextFill(Color.WHITE);
            // messageHBox2.getChildren().add(messageLabel);

            // messageHBox.getChildren().add(messageHBox2);
            // messageArea.getChildren().add(messageHBox);
            // }
            // scrollPane.setVvalue(1.0);
            // }
            // }
            // multithreading for live load messages
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("KKKKKKK");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break; // Exit the loop if the thread is interrupted
                        }
                        String res = sendServerRequest(
                                "REQ:CHECKIFMESSAGE" + ":" + Session.getUser().getUsername() + ":"
                                        + findUser.getText());

                        // Perform the server request and message processing in the background
                        if (res.equals("/?Nomessagesyet")) {
                            continue;
                        }
                        System.out.println("test");
                        Platform.runLater(() -> {

                            String[] messages = res.split(",");
                            messageArea.getChildren().clear();
                            for (String message : messages) {
                                String[] messageParts = message.split("\\|");
                                if (messageParts[0].equals("u")) {
                                    HBox messageHBox = new HBox();
                                    messageHBox.setAlignment(Pos.CENTER_RIGHT);
                                    messageHBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

                                    HBox messageHBox2 = new HBox();
                                    messageHBox2.setAlignment(Pos.CENTER);
                                    messageHBox2.setPrefHeight(100);
                                    messageHBox2.setPrefWidth(200);
                                    messageHBox2
                                            .setStyle("-fx-background-color: #075ea6; -fx-background-radius: 20px;");

                                    Label messageLabel = new Label(messageParts[1]);
                                    messageLabel.setFont(new Font("Arial", 20));
                                    messageLabel.setTextFill(Color.WHITE);
                                    messageHBox2.getChildren().add(messageLabel);

                                    messageHBox.getChildren().add(messageHBox2);
                                    messageArea.getChildren().add(messageHBox);
                                } else {
                                    HBox messageHBox = new HBox();
                                    messageHBox.setAlignment(Pos.CENTER_LEFT);
                                    messageHBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

                                    HBox messageHBox2 = new HBox();
                                    messageHBox2.setAlignment(Pos.CENTER);
                                    messageHBox2.setPrefHeight(100);
                                    messageHBox2.setPrefWidth(200);
                                    messageHBox2
                                            .setStyle("-fx-background-color: #6e6e6e; -fx-background-radius: 20px;");

                                    Label messageLabel = new Label(messageParts[1]);
                                    messageLabel.setFont(new Font("Arial", 20));
                                    messageLabel.setTextFill(Color.WHITE);
                                    messageHBox2.getChildren().add(messageLabel);

                                    messageHBox.getChildren().add(messageHBox2);
                                    messageArea.getChildren().add(messageHBox);
                                }
                            }
                        });
                    }
                }
            });

            thread.start();

        } else if (event.getSource() == sendMessageBtn)

        {
            String res = sendServerRequest("REQ:SENDMESSAGE" + ":" + Session.getUser().getUsername() + ":"
                    + Session.getDmUser() + ":" + messageText.getText());
            if (res.equals("FAILED")) {
                // alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error sending message");
                alert.setContentText("There was an error sending the message. Please try again.");
                alert.showAndWait();
                return;
            }
            HBox messageHBox = new HBox();
            messageHBox.setAlignment(Pos.CENTER_RIGHT);
            messageHBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

            HBox messageHBox2 = new HBox();
            messageHBox2.setAlignment(Pos.CENTER);
            messageHBox2.setPrefHeight(100);
            messageHBox2.setPrefWidth(200);
            messageHBox2.setStyle("-fx-background-color: #075ea6; -fx-background-radius: 20px;");

            Label messageLabel = new Label(messageText.getText());
            messageLabel.setFont(new Font("Arial", 20));
            messageLabel.setTextFill(Color.WHITE);
            messageHBox2.getChildren().add(messageLabel);
            messageHBox.getChildren().add(messageHBox2);
            messageArea.getChildren().add(messageHBox);
        }
    }

    @FXML
    public void handleMouseEntered(MouseEvent event) {
        Node sourceNode = (Node) event.getSource();
        sourceNode.setStyle(
                "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
    }

    @FXML
    public void handleMouseExited(MouseEvent event) {
        Node sourceNode = (Node) event.getSource();
        sourceNode.setStyle(
                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
    }

    public String sendServerRequest(String request) {
        try {
            return ServerConnection.sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERR";
    }
}
