package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ViewFriendsRequestController implements Initializable {

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
    private VBox viewFriendsView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                loadFriends();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/HomePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewProfileBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ProfilePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsRequestsBtn) {
            // do nothin
        } else if (event.getSource() == directMessagingBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/DirectMessagePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == settingsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/SettingsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == logoutBtn) {
            Session.setUser(null);
            try {
                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/AuthenticationPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void loadFriends() {
        for (String username : Session.getUser().getFriendRequestList()) {
            Task<Void> task2 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String res2 = sendServerRequest("REQ:" + "GETUSER" + ":" + username);
                    String arr2[] = res2.split(":");
                    User user = new User(arr2[0], arr2[1], arr2[2], arr2[3], arr2[4], "0");
                    Platform.runLater(() -> {
                        HBox rootHbox = new HBox();
                        rootHbox.setMinHeight(100);
                        rootHbox.setMinWidth(200);
                        rootHbox.setStyle("-fx-border-color: white; -fx-background-color: #313131;");
                        rootHbox.setAlignment(javafx.geometry.Pos.CENTER);

                        ImageView profileImage = new ImageView();
                        profileImage.setFitHeight(90);
                        profileImage.setFitWidth(90);
                        profileImage.setPickOnBounds(true);
                        profileImage.setPreserveRatio(true);
                        profileImage.setImage(new Image("https://" + user.getPicPath()));

                        VBox profielDataVbox = new VBox();
                        profielDataVbox.setMinHeight(150);
                        profielDataVbox.setMinWidth(206);
                        profielDataVbox.setAlignment(javafx.geometry.Pos.CENTER);
                        Label usernameLabel = new Label();
                        usernameLabel.setText("@" + user.getUsername());
                        usernameLabel.setFont(new Font("Arial", 13));
                        usernameLabel.setTextFill(Color.web("#727272"));
                        usernameLabel.setStyle("-fx-font-weight: bold;");
                        Label nameLabel = new Label();
                        nameLabel.setText(user.getFullName());
                        nameLabel.setFont(new Font("Arial", 22));
                        nameLabel.setTextFill(Color.web("#ffffff"));
                        profielDataVbox.getChildren().addAll(nameLabel, usernameLabel);

                        VBox profielBtnsVbox = new VBox();
                        profielBtnsVbox.setMinHeight(150);
                        profielBtnsVbox.setMinWidth(225);
                        profielBtnsVbox.setAlignment(javafx.geometry.Pos.CENTER);
                        profielBtnsVbox.setSpacing(10);

                        Button viewUserProfileBtn = new Button("Accept");
                        viewUserProfileBtn.setUserData(user.getUsername());
                        viewUserProfileBtn.setPrefHeight(39.0);
                        viewUserProfileBtn.setPrefWidth(150.0);
                        viewUserProfileBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        viewUserProfileBtn.setOnAction((e) -> {
                            String res3 = sendServerRequest("REQ:" + "ADDFRIEND" + ":" + Session.getUser().getUsername() + ":" + user.getUsername());
                            System.out.println(res3);
                            Session.getUser().getFriendList().add(user.getUsername());
                            Session.getUser().getFriendRequestList().remove(user.getUsername());
                            viewFriendsView.getChildren().remove(rootHbox);
                        });
                        viewUserProfileBtn.setOnMouseEntered((e) -> {
                            viewUserProfileBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        viewUserProfileBtn.setOnMouseExited((e) -> {
                            viewUserProfileBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });

                        Button removeFriendBtn = new Button("Decline");
                        removeFriendBtn.setUserData(user.getUsername());
                        removeFriendBtn.setPrefHeight(39.0);
                        removeFriendBtn.setPrefWidth(150.0);
                        removeFriendBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        removeFriendBtn.setOnAction((e) -> {
                            String res3 = sendServerRequest("REQ:" + "DECLINEFRIEND" + ":" + Session.getUser().getUsername() + ":" + user.getUsername());
                            System.out.println(res3);
                            Session.getUser().getFriendRequestList().remove(user.getUsername());
                            viewFriendsView.getChildren().remove(rootHbox);
                        });
                        removeFriendBtn.setOnMouseEntered((e) -> {
                            removeFriendBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        removeFriendBtn.setOnMouseExited((e) -> {
                            removeFriendBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        profielBtnsVbox.getChildren().addAll(viewUserProfileBtn, removeFriendBtn);

                        rootHbox.getChildren().addAll(profileImage, profielDataVbox, profielBtnsVbox);
                        viewFriendsView.getChildren().add(0, rootHbox);
                    });

                    return null;
                }
            };
            new Thread(task2).start();
        }
    }
}
