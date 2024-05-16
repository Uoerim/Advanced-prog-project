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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProfileController implements Initializable {

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
    private VBox postsArea;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HBox hbox = new HBox();
        hbox.setMinHeight(780);
        hbox.setMinWidth(1376);
        hbox.setStyle("-fx-background-color: #212121;");
        hbox.setAlignment(Pos.CENTER);
        HBox.setHgrow(hbox, javafx.scene.layout.Priority.ALWAYS);
        hbox.getChildren().add(new ImageView(new Image("/resources/assets/load.gif")));
        mainView.getChildren().add(0, hbox);

        profileImage.setImage(new Image(Session.getUser().getPicPath()));
        usernameLabel.setText("@" + Session.getUser().getUsername());
        bioLabel.setText(Session.getUser().getUserBio());
        fullNameLabel.setText(Session.getUser().getFullName());
        friendsCountLabel.setText(String.valueOf(Session.getUser().getFriendList().size()) + " Friends");

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                CompletableFuture<Void> loadPostsFuture = CompletableFuture.runAsync(() -> {
                    loadPosts();
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                loadfriends();
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
            // do nothing
        } else if (event.getSource() == viewFriendsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsRequestsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/FriendsRequestsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void loadPosts() {
        if (Session.getUser().getPosts().isEmpty()) {
            System.out.println(Session.getUser().getPosts());
            System.out.println("No posts");
            return;
        }
        for (Integer postIndex : Session.getUser().getPosts()) {
            Task<Void> task1 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String res2 = sendServerRequest("REQ:" + "GETPOST" + ":" + String.valueOf(postIndex));
                    System.out.println(res2);
                    String arr2[] = res2.split(":");
                    Post post = new Post(arr2[0], arr2[1], arr2[2]);
                    Platform.runLater(() -> {
                        VBox rootVBox = new VBox();
                        rootVBox.setMinHeight(198);
                        rootVBox.setMinWidth(662);
                        rootVBox.setStyle("-fx-border-color: #414141; -fx-background-color: transparent;");

                        HBox postTextHBox = new HBox();
                        postTextHBox.setMinHeight(130);
                        postTextHBox.setMinWidth(662);
                        postTextHBox.setAlignment(javafx.geometry.Pos.CENTER);
                        Label postTextLabel = new Label();
                        postTextLabel.setText(post.getPostText());
                        postTextLabel.setFont(new Font("Arial", 16));
                        postTextLabel.setTextFill(Color.web("#ffffff"));
                        postTextHBox.getChildren().add(postTextLabel);

                        HBox postBtnHBox = new HBox();
                        postBtnHBox.setMinHeight(70);
                        postBtnHBox.setMinWidth(662);
                        postBtnHBox.setAlignment(javafx.geometry.Pos.CENTER);
                        Button viewPostBtn = new Button("View Post");
                        viewPostBtn.setUserData(post.getPostId());
                        viewPostBtn.setPrefHeight(41.0);
                        viewPostBtn.setPrefWidth(158.0);
                        viewPostBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        viewPostBtn.setOnAction((event) -> {
                            Session.setViewPost(post);
                            try {
                                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                                        FXMLLoader.load(
                                                getClass().getResource("../../resources/ViewPostPage.fxml")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        viewPostBtn.setOnMouseEntered((event) -> {
                            viewPostBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        viewPostBtn.setOnMouseExited((event) -> {
                            viewPostBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        postBtnHBox.setSpacing(20);
                        postBtnHBox.getChildren().addAll(viewPostBtn);

                        rootVBox.getChildren().addAll(postTextHBox, postBtnHBox);
                        postsArea.getChildren().add(0, rootVBox);
                    });

                    return null;
                }
            };
            new Thread(task1).start();
        }
    }

    private void loadfriends() {
        if (Session.getUser().getFriendList().isEmpty()) {
            System.out.println("No friends");
            Platform.runLater(() -> {
                mainView.getChildren().remove(0);
            });
            return;
        }
        for (String username : Session.getUser().getFriendList()) {
            Task<Void> task2 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Platform.runLater(() -> {
                        String res3 = sendServerRequest("REQ:" + "GETUSER" + ":" + username);
                        if (res3.charAt(res3.length() - 1) == ':') {
                            res3 += ":";
                        }
                        res3 = res3.replaceAll("::", ":null:");
                        if (res3.charAt(res3.length() - 1) == ':') {
                            res3 += ":";
                        }
                        res3 = res3.replaceAll("::", ":null:");
                        System.out.println(res3);
                        String arr[] = res3.split(":");
                        User user = new User(arr[0], arr[1], arr[2], arr[3], "https://" + arr[4], "0");
                        ArrayList<String> friendList = new ArrayList<>();
                        ArrayList<String> friendRequestList = new ArrayList<>();
                        ArrayList<String> friendRequestSentList = new ArrayList<>();
                        ArrayList<Integer> posts = new ArrayList<>();

                        if (!arr[6].equals("null")) {
                            String[] friendListArray = arr[6].split(",");
                            for (String friend : friendListArray) {
                                friendList.add(friend);
                            }
                            user.setFriendList(friendList);

                        }
                        if (!arr[7].equals("null")) {

                            String[] friendRequestListArray = arr[7].split(",");
                            for (String friendRequest : friendRequestListArray) {
                                friendRequestList.add(friendRequest);
                            }
                            user.setFriendRequestList(friendRequestList);

                        }
                        if (!arr[8].equals("null")) {

                            String[] friendRequestSentListArray = arr[8].split(",");
                            for (String friendRequestSent : friendRequestSentListArray) {
                                friendRequestSentList.add(friendRequestSent);
                            }
                            user.setFriendRequestSentList(friendRequestSentList);
                        }
                        if (!arr[9].equals("null")) {

                            String[] postsArray = arr[9].split(",");
                            for (String post : postsArray) {
                                posts.add(Integer.parseInt(post));
                            }
                            user.setPosts(posts);
                        }
                        HBox rootHBox = new HBox();
                        rootHBox.setMinHeight(100);
                        rootHBox.setMinWidth(270);
                        rootHBox.setStyle("-fx-border-color: #313131; -fx-background-color: transparent;");

                        VBox dataVbox = new VBox();
                        dataVbox.setMinHeight(90);
                        dataVbox.setMinWidth(172);
                        dataVbox.setAlignment(javafx.geometry.Pos.CENTER);
                        Label fullnameL = new Label();
                        fullnameL.setText(user.getFullName());
                        fullnameL.setFont(new Font("Arial", 13));
                        fullnameL.setTextFill(Color.web("#ffffff"));
                        Label usernameL = new Label();
                        usernameL.setText(user.getUsername());
                        usernameL.setFont(new Font("Arial", 12));
                        usernameL.setTextFill(Color.web("#949494"));
                        Button viewBtn = new Button("View");
                        viewBtn.setUserData(user.getUsername());
                        viewBtn.setPrefHeight(27);
                        viewBtn.setPrefWidth(108);
                        viewBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        viewBtn.setOnAction((event) -> {
                            Session.setViewUser(user);
                            System.out.println(user.getPicPath());
                            try {
                                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                                        FXMLLoader.load(
                                                getClass().getResource("../../resources/ViewProfilePage.fxml")));
                            } catch (IOException r) {
                                r.printStackTrace();
                            }
                        });
                        viewBtn.setOnMouseEntered((event) -> {
                            viewBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        viewBtn.setOnMouseExited((event) -> {
                            viewBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        dataVbox.getChildren().addAll(fullnameL, usernameL, viewBtn);

                        ImageView profileImage = new ImageView();
                        profileImage.setFitHeight(90);
                        profileImage.setFitWidth(90);
                        profileImage.setPickOnBounds(true);
                        profileImage.setPreserveRatio(true);
                        profileImage.setImage(new Image(user.getPicPath()));

                        rootHBox.getChildren().addAll(profileImage, dataVbox);
                        friendsArea.getChildren().add(0, rootHBox);
                        System.out.println(Session.getUser().getFriendList().indexOf(username) + " "
                                + Session.getUser().getFriendList().size());
                        if (Session.getUser().getFriendList()
                                .indexOf(username) == Session.getUser().getFriendList().size() - 1) {
                            System.out.println("Friends loaded");
                            mainView.getChildren().remove(0);
                        }
                    });
                    return null;
                }
            };

            new Thread(task2).start();
        }

    }
}
